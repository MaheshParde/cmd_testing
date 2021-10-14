package in.ecgc.smile.erp.accounts.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.exception.InvalidGLTxnDataException;
import in.ecgc.smile.erp.accounts.exception.TCodeNotPresentException;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.DateOperation;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.repository.EntityGLMasterDaoImpl;
import in.ecgc.smile.erp.accounts.repository.GlTxnDao;

@Service
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
		List<GlTxnHdr> glTxnHdrs = null;
		try {
		 glTxnHdrs =txnDao.getAllGlTxn(LogicalLoc);
		 LOGGER.info("GL Txns for "+ LogicalLoc);
		}
		catch(GlTxnNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return glTxnHdrs;
	}
	@Override
	public List<GlTxnHdr> getAllGlTxnHdrs(String LogicalLoc,String glTxnType) {
		List<GlTxnHdr> glTxnHdrs = null;
		try {
			glTxnHdrs =txnDao.getAllGlTxn(LogicalLoc,glTxnType);
			LOGGER.info("GL Txns for "+ LogicalLoc);
		}
		catch(GlTxnNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return glTxnHdrs;
	}
	@Override
	public GlTxnHdr getGlTxn(Integer glTxnNo, String LogicalLoc,String glTxnType) {
		GlTxnHdr glTxn = null;
		try {
			glTxn =txnDao.getGlTxn(glTxnNo,LogicalLoc,glTxnType);
			LOGGER.info("GL Txns for "+ glTxnNo +" "+LogicalLoc);
		}
		catch(GlTxnNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return glTxn;
	}
	@Override
	public Integer addGlTxn(GlTxnHdr glTxnHdr) {
			DateOperation dt = new DateOperation(glTxnHdr.getTxnDt().getMonthValue());
			
		try {
			
			FiscalYearModel fiscalYearModel = fiscalYrService.findCurrentFiscalYear();
			System.err.println(fiscalYearModel);
			System.err.println("glTxnHdr.getFiscalYr() : "+glTxnHdr.getFiscalYr());
			System.err.println("dt.currentMonth : "+dt.currentMonth);
			System.err.println("glTxnHdr.getLogicalLocCd() : "+glTxnHdr.getLogicalLocCd());
			System.err.println("glTxnHdr.getGlTxnType() : "+glTxnHdr.getGlTxnType());
			Calendar calendar = calendarService.getByGlTypeLogicalLoc(glTxnHdr.getFiscalYr(),dt.currentMonth,glTxnHdr.getLogicalLocCd(),glTxnHdr.getGlTxnType());
			System.err.println(calendar);
			if(calendar != null &&(calendar.getClosedStatus().equals('N') || calendar.getClosedStatus().equals('n'))) {
				glTxnHdr.setGlTxnNo(txnDao.getGlTxnNo(glTxnHdr.getLogicalLocCd(),glTxnHdr.getGlTxnType(),glTxnHdr.getFiscalYr()));
				System.err.println("befor valid");
				if(this.isValid(glTxnHdr)) {
					System.err.println("before dao call");
					if(txnDao.addGlTxn(glTxnHdr) >=3 ) {
						LOGGER.info("GL Transaction successsfully done");
						return glTxnHdr.getGlTxnNo();
						}
					return 1;
				}				
			}
			return 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Boolean isValid(GlTxnHdr glTxnHdr) {
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
				if(gl.getT1() != null && gl.getT1().equals('Y') && (dtl.getT1() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("Ti is not present");
				}
				if(gl.getT2() != null && gl.getT2().equals('Y') && (dtl.getT2() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("T2 is not present");
				}
				if(gl.getT3() != null && gl.getT3().equals('Y') && (dtl.getT3() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("T3 is not present");
				}
				if(gl.getT4() != null && gl.getT4().equals('Y') && (dtl.getT4() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("T4 is not present");
				}
				if(gl.getT5() != null && gl.getT5().equals('Y') && (dtl.getT5() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("T5 is not present");
				}
				if(gl.getT6() != null && gl.getT6().equals('Y') && (dtl.getT6() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("T6 is not present");
				}
				if(gl.getT7() != null && gl.getT7().equals('Y') && (dtl.getT7() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("T7 is not present");
				}
				if(gl.getT8() != null && gl.getT8().equals('Y') && (dtl.getT8() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("T8 is not present");
				}
				if(gl.getT9() != null && gl.getT9().equals('Y') && (dtl.getT9() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("T9 is not present");
				}
				if(gl.getT10() != null && gl.getT10().equals('Y') && (dtl.getT10() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("Ti is not present");
				}
				if(gl.getT11() != null && gl.getT11().equals('Y') && (dtl.getT11() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("T11 is not present");
				}
				if(gl.getT12() != null && gl.getT12().equals('Y') && (dtl.getT12() == null || dtl.getT1() =="")) {
					throw new TCodeNotPresentException("T2 is not present");
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
	public Integer updateGlTxnDtl(GlTxnDtl glTxnDtl) throws InvalidGLTxnDataException {
		try {
			Integer result=txnDao.updateGlTxnDtl(glTxnDtl);
			System.out.println(glTxnDtl);
			if(result <= 0)
				throw new InvalidGLTxnDataException("Txn not updated");
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new InvalidGLTxnDataException(e.getMessage());		
		}
	}
	@Override
	public List<GlTxnHdr> getAllGlTxnByFromDtToDt(LocalDate fromDt, LocalDate toDt) throws GlTxnNotFoundException {
		List<GlTxnHdr> glTxnHdrs = null;
		try {
			glTxnHdrs =txnDao.getAllGlTxnByFromDtToDt(fromDt, toDt);
			LOGGER.info("GL Txns form "+ fromDt);
		}
		catch(GlTxnNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return glTxnHdrs;
	}
	
	@Override
	public Integer reverseTransaction(GlTxnHdr glTxnHdr) throws InvalidGLTxnDataException {
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
			System.out.println(reversalTxnNo);
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
			e.printStackTrace();
			throw new InvalidGLTxnDataException(e.getMessage());		
		}
	}
}
