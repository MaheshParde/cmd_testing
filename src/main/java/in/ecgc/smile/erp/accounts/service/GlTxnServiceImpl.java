package in.ecgc.smile.erp.accounts.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.CalendarRecordNotFoundException;
import in.ecgc.smile.erp.accounts.exception.GLTxnDBFailedException;
import in.ecgc.smile.erp.accounts.exception.InvalidGLTxnDataException;
import in.ecgc.smile.erp.accounts.exception.TCodeNotPresentException;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.DateOperation;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.repository.GlTxnDao;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GlTxnServiceImpl implements GlTxnService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlTxnServiceImpl.class);

	@Autowired
	GlTxnDao txnDao;
	
	@Autowired
	EntityGLMasterService entityGlMstService;
	
	@Autowired
	CalendarService calendarService;
	
	@Autowired
	FiscalYearService fiscalYrService;
	
	@Override
	public List<GlTxnHdr> getAllGlTxnHdrs(String LogicalLoc) {
		log.info("Inside GlTxnServiceImpl#getAllGlTxnHdrs");
			return txnDao.getAllGlTxn(LogicalLoc);
	}
	
	@Override
	public List<GlTxnHdr> getAllGlTxnHdrs(String LogicalLoc,String glTxnType) {
		log.info("Inside GlTxnServiceImpl#getAllGlTxnHdrs");
			return txnDao.getAllGlTxn(LogicalLoc,glTxnType);
	}
	
	@Override
	public GlTxnHdr getGlTxn(Integer glTxnNo, String LogicalLoc,String glTxnType) {
		log.info("Inside GlTxnServiceImpl#getGlTxn");
				return txnDao.getGlTxn(glTxnNo,LogicalLoc,glTxnType);
	}
	@Override
	public Integer addGlTxn(GlTxnHdr glTxnHdr) {
		log.info("Inside GlTxnServiceImpl#addGlTxn");
			DateOperation dt = new DateOperation(glTxnHdr.getTxnDt().getMonthValue());
		
			Integer srno =0;
			
			FiscalYearModel fiscalYearModel = fiscalYrService.findCurrentFiscalYear();
			
			log.info("{}",fiscalYearModel);
			log.info("glTxnHdr.getFiscalYr() : {}",glTxnHdr.getFiscalYr());
			log.info("dt.currentMonth : {}",dt.currentMonth);
			log.info("glTxnHdr.getLogicalLocCd() : {}",glTxnHdr.getLogicalLocCd());
			log.info("glTxnHdr.getGlTxnType() : {}",glTxnHdr.getGlTxnType());
			
			//Get GLTXN seq no.
			glTxnHdr.setGlTxnNo(txnDao.getGlTxnNo(glTxnHdr.getLogicalLocCd(),glTxnHdr.getGlTxnType(),glTxnHdr.getFiscalYr()));
			
			for(GlTxnDtl dtl : glTxnHdr.getGlTxnDtls()) {
				srno +=1;	
				dtl.setSrNo(srno);
			}
			
			String fiscalYr = glTxnHdr.getFiscalYr();
			String currMon = dt.currentMonth;
			String logicalLoc = glTxnHdr.getLogicalLocCd();
			String glTxnType = glTxnHdr.getGlTxnType();
		
			Calendar calendar = calendarService.getByGlTypeLogicalLoc(fiscalYr,currMon,logicalLoc,glTxnType);
			
			log.info("Calendar : {}",calendar);
			
			if(calendar != null &&(calendar.getClosedStatus().equals('N') || calendar.getClosedStatus().equals('n'))) {
				//glTxnHdr.setGlTxnNo(txnDao.getGlTxnNo(glTxnHdr.getLogicalLocCd(),glTxnHdr.getGlTxnType(),glTxnHdr.getFiscalYr()));
				for(GlTxnDtl dtl : glTxnHdr.getGlTxnDtls()) {
					srno +=1;	
					dtl.setSrNo(srno);
				}
				if(this.isValid(glTxnHdr)) {
					if(txnDao.addGlTxn(glTxnHdr) >=3 ) {					
						 Integer res = txnDao.updateGlTxnNo(glTxnHdr.getLogicalLocCd(), glTxnHdr.getGlTxnType(), glTxnHdr.getFiscalYr(), glTxnHdr.getGlTxnNo());
						Integer res_bal =  txnDao.updateDrCrBrbal(glTxnHdr.getGlTxnDtls(), fiscalYr, glTxnHdr.getTxnDt());
						Integer res_bal_yr = txnDao.updateDrCrBrbalYr(glTxnHdr.getGlTxnDtls(), fiscalYr, glTxnHdr.getTxnDt()); 
						 if (res == 1) {
							 LOGGER.info("GL Transaction successsfully done");
							 return glTxnHdr.getGlTxnNo();
							} else {
								txnDao.deleteGlTxnDtl(glTxnHdr.getGlTxnNo());
								txnDao.deleteGlTxnHdr(glTxnHdr.getGlTxnNo());								
								throw new GLTxnDBFailedException("Failed to add GL transaction details for LogicalLocation: "+glTxnHdr.getLogicalLocCd()+", TxnType: "+glTxnHdr.getGlTxnType()+", Fiscal Year:"+glTxnHdr.getFiscalYr());
							}
						}
				}else {
					throw new GLTxnDBFailedException("Validation failed for GL transaction header");
				}				
			}else {
				throw new CalendarRecordNotFoundException("Calendar is not opened for the Fiscal year : "+fiscalYr+", Month : "+currMon+", Logical location : "+logicalLoc+" and Transaction type : "+glTxnType+"");
			}			
			return 0;
	}

	public Boolean isValid(GlTxnHdr glTxnHdr) {
		log.info("Inside GlTxnServiceImpl#isValid");
		try {
			Double txnamt= 0.00;
			Integer srno =0;
			if(glTxnHdr.getFiscalYr() == null || glTxnHdr.getGlTxnType() ==null ||
					glTxnHdr.getLogicalLocCd() ==null || glTxnHdr.getTxnDt() == null)
				throw new InvalidGLTxnDataException("Incomplete Gl Transaction data");
			for(GlTxnDtl dtl : glTxnHdr.getGlTxnDtls()) {
				srno +=1;	
				dtl.setSrNo(srno);
				
				EntityGL gl = entityGlMstService.findGLByGLCode(dtl.getMainGlCd(),dtl.getSubGlCd());
				if(gl!=null) {
					if(gl.getT1() != null && gl.getT1().equals('Y') && (dtl.getT1() == null || dtl.getT1() =="")) {
						throw new TCodeNotPresentException("T1 is not present");
					}
					if(gl.getT2() != null && gl.getT2().equals('Y') && (dtl.getT2() == null || dtl.getT2() =="")) {
						throw new TCodeNotPresentException("T2 is not present");
					}
					if(gl.getT3() != null && gl.getT3().equals('Y') && (dtl.getT3() == null || dtl.getT3() =="")) {
						throw new TCodeNotPresentException("T3 is not present");
					}
					if(gl.getT4() != null && gl.getT4().equals('Y') && (dtl.getT4() == null || dtl.getT4() =="")) {
						throw new TCodeNotPresentException("T4 is not present");
					}
					if(gl.getT5() != null && gl.getT5().equals('Y') && (dtl.getT5() == null || dtl.getT5() =="")) {
						throw new TCodeNotPresentException("T5 is not present");
					}
					if(gl.getT6() != null && gl.getT6().equals('Y') && (dtl.getT6() == null || dtl.getT6() =="")) {
						throw new TCodeNotPresentException("T6 is not present");
					}
					if(gl.getT7() != null && gl.getT7().equals('Y') && (dtl.getT7() == null || dtl.getT7() =="")) {
						throw new TCodeNotPresentException("T7 is not present");
					}
					if(gl.getT8() != null && gl.getT8().equals('Y') && (dtl.getT8() == null || dtl.getT8() =="")) {
						throw new TCodeNotPresentException("T8 is not present");
					}
					if(gl.getT9() != null && gl.getT9().equals('Y') && (dtl.getT9() == null || dtl.getT9() =="")) {
						throw new TCodeNotPresentException("T9 is not present");
					}
					if(gl.getT10() != null && gl.getT10().equals('Y') && (dtl.getT10() == null || dtl.getT10() =="")) {
						throw new TCodeNotPresentException("T10 is not present");
					}
					if(gl.getT11() != null && gl.getT11().equals('Y') && (dtl.getT11() == null || dtl.getT11() =="")) {
						throw new TCodeNotPresentException("T11 is not present");
					}
					if(gl.getT12() != null && gl.getT12().equals('Y') && (dtl.getT12() == null || dtl.getT12() =="")) {
						throw new TCodeNotPresentException("T12 is not present");
					}
					if(dtl.getDrCrFlag().equalsIgnoreCase("dr")) {
						txnamt -= dtl.getTxnAmt();
					}
					else if(dtl.getDrCrFlag().equalsIgnoreCase("cr")) {
						txnamt += dtl.getTxnAmt();
					}
					else {
						throw new InvalidGLTxnDataException("DR/CR Flag missing ");
					}
				}else {
					return false;
				}
			}
			if(txnamt >=1)
				throw new InvalidGLTxnDataException("Debit Credit not matched ");
			return true;
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}
	@Override
	public Integer updateGlTxnDtl(GlTxnDtl glTxnDtl){
			log.info("Inside GlTxnServiceImpl#updateGlTxnDtl");
		return txnDao.updateGlTxnDtl(glTxnDtl);
	}
	@Override
	public List<GlTxnHdr> getAllGlTxnByFromDtToDt(LocalDate fromDt, LocalDate toDt) {
			log.info("Inside GlTxnServiceImpl#getAllGlTxnByFromDtToDt");
		return txnDao.getAllGlTxnByFromDtToDt(fromDt, toDt);
	}
	
	@Override
	public Integer reverseTransaction(GlTxnHdr glTxnHdr) {
		log.info("Inside GlTxnServiceImpl#reverseTransaction");
		Integer reversalTxnNo = 0;
		Integer result = 0;
		try {
			GlTxnHdr reversalTxnHdr = new GlTxnHdr();
			reversalTxnHdr.setGlTxnType("RV");
			reversalTxnHdr.setLogicalLocCd(glTxnHdr.getLogicalLocCd());
			reversalTxnHdr.setReference(glTxnHdr.getReference());
			reversalTxnHdr.setTxnDt(LocalDate.now());
			reversalTxnHdr.setLastUpdatedBy("101");
			reversalTxnHdr.setFiscalYr(glTxnHdr.getFiscalYr());
			List<GlTxnDtl> list = new ArrayList<>();
			for(GlTxnDtl dtl : glTxnHdr.getGlTxnDtls()) {
				dtl.setGlTxnType("RV");
				if(dtl.getDrCrFlag().equalsIgnoreCase("Dr")) {
					dtl.setDrCrFlag("Cr");
				}
				else {
					dtl.setDrCrFlag("Dr");
				}
				list.add(dtl);
			}
			reversalTxnHdr.setGlTxnDtls(list);
			 
			reversalTxnNo = this.addGlTxn(reversalTxnHdr);
			log.info("reversal txn no. {}",reversalTxnNo);
			if(reversalTxnNo > 0)
			{
				glTxnHdr.setRevarsalGlTxnNo(reversalTxnNo);
				glTxnHdr.setRevarsalGlTxnType("RV");
				result = txnDao.updateHdrOnRevarsal(glTxnHdr);
			}
			if(result > 0)
				return reversalTxnNo;
			else
				throw new InvalidGLTxnDataException("Transaction not updated");
		}
		catch(Exception e) {
			throw new InvalidGLTxnDataException("Exception in reverse transaction : "+e.getMessage());		
		}
	}
	@Override
	public BigDecimal getTotalAmt(String mainGlCd , String subGlCd) {
		log.info("Inside GlTxnServiceImpl#getTotalAmt");
		return txnDao.getTotalAmt( mainGlCd ,  subGlCd);	
	}
	
	@Override
	public BigDecimal getTotalCreditAmt(String mainGlCd , String subGlCd)  {
		log.info("Inside GlTxnServiceImpl#getTotalCreditAmt");
		return txnDao.getTotalCreditAmt( mainGlCd ,  subGlCd);	
	}
	
	@Override
	public List<GlTxnHdr> getAllGltxnByFromDtLoc(LocalDate fromDt, LocalDate toDt,String logicallocation) {
			log.info("Inside GlTxnServiceImpl#getAllGltxnByFromDtLoc");
		return txnDao.getAllGltxnByFromDtLoc(fromDt, toDt,logicallocation);
	}
	
	@Override
	public List<GlTxnHdr> getAllGlTxnByTxnNoTxnTypeLoc(Integer glTxnNo, String glTxnType, String logicalLoc) {
		log.info("Inside GlTxnServiceImpl#getAllGlTxnByTxnNoTxnTypeLoc");
		return txnDao.getAllGlTxnByTxnNoTxnTypeLoc(glTxnNo, glTxnType, logicalLoc);
	}
}
