/**
 * 
 */
package in.ecgc.smile.erp.accounts.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.TCodeNotPresentException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.model.Liability;
import in.ecgc.smile.erp.accounts.model.LiabilityGLMapping;
import in.ecgc.smile.erp.accounts.repository.LiabilityDao;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CMJ-SENG-LAPTOP9
 *
 */
@Slf4j
@Service
public class LiabilityServiceImpl implements LiabilityService {

	@Autowired
	LiabilityDao liabilityDao;
	
	@Autowired
	GlTxnService glTxnServive;
	
	@Override
	public GlTxnHdr createLiability(Liability liability) throws TCodeNotPresentException {
		GlTxnHdr glTxnHdr = new GlTxnHdr();
		glTxnHdr.setEntityCd("ECGC");
		List<LiabilityGLMapping> mappingList = liabilityDao.getMAppingListforModule(liability.getModuleCd(),liability.getMappingCd());
		
		glTxnHdr.setLogicalLocCd(liability.getLogicalLogCd());
		//glTxnHdr.setTxnDt(liability.getTxnDt());
		glTxnHdr.setTxnDt(LocalDate.now());
		glTxnHdr.setCreatedBy(liability.getUserId().toString());
		
		Double amt =0.0;
		
		if(!liability.getIsAmtInInr()) {
			amt = liability.getBaseAmt() * liability.getExchangeRate();
			amt = BigDecimal.valueOf(amt)
				    .setScale(2, RoundingMode.HALF_UP)
				    .doubleValue();
		}
		else {
			amt = liability.getBaseAmt();
		}
		
		String txnType= null;
		List<GlTxnDtl> glTxnDtls = new ArrayList<>();
		for(LiabilityGLMapping glMapping : mappingList) {
			GlTxnDtl dtl = new GlTxnDtl();
			dtl.setGlTxnType(glMapping.getTxnType());
			dtl.setDrCrFlag(glMapping.getDrCrFlag());
			dtl.setLogicalLocCd(liability.getLogicalLogCd());
			dtl.setMainGlCd(glMapping.getMainGL());
			dtl.setSubGlCd(glMapping.getSubGL());
			dtl.setSubBiFurcationCd(glMapping.getSubBifurcation());
			dtl.setCreatedBy(liability.getUserId().toString());
			Double amt1 = amt * glMapping.getAmtCalc();
			Double truncatedAmt = BigDecimal.valueOf(amt1)
			    .setScale(2, RoundingMode.HALF_UP)
			    .doubleValue();
			dtl.setTxnAmt(truncatedAmt);
			dtl.setT1(liability.getT1());
			dtl.setT2(liability.getT2());
			dtl.setT3(liability.getT3());
			dtl.setT4(liability.getT4());
			dtl.setT5(liability.getT5());
			dtl.setT6(liability.getT6());
			dtl.setT7(liability.getT7());
			dtl.setT8(liability.getT8());
			dtl.setT9(liability.getT9());
			dtl.setT10(liability.getT10());
			dtl.setT11(liability.getT11());
			dtl.setT11(liability.getT12());
			dtl.setAd1(liability.getAd1());
			dtl.setAd2(liability.getAd2());
			dtl.setAd3(liability.getAd3());
			dtl.setAd4(liability.getAd4());
			glTxnDtls.add(dtl);
			
			txnType= glMapping.getTxnType();
		}
		glTxnHdr.setGlTxnType(txnType);
		glTxnHdr.setFiscalYr(liability.getFiscalYr());
		glTxnHdr.setGlTxnDtls(glTxnDtls);
		log.info("Gl headr : {}", glTxnHdr);
		Integer glTxnNo;
			glTxnNo = glTxnServive.addGlTxn(glTxnHdr);
		if(glTxnNo >0) {
			glTxnHdr.setGlTxnNo(glTxnNo);
			glTxnHdr.setGlTxnDtls(null);
			return glTxnHdr;
		}
		return null;
		
	}

	@Override
	public List<LiabilityGLMapping> getMapping(String moduleCd, String mappingCd) {
		try {
		List<LiabilityGLMapping> mappingList = liabilityDao.getMAppingListforModule(moduleCd,mappingCd);
		return mappingList;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LiabilityGLMapping> getMapping() {
		try {
			List<LiabilityGLMapping> mappingList = liabilityDao.getMAppingList();
			return mappingList;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;	}

	@Override
	public List<LiabilityGLMapping> getMapping(String moduleCd) {
		try {
			List<LiabilityGLMapping> mappingList = liabilityDao.getMAppingListforModule(moduleCd);
			return mappingList;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;	
			}

	@Override
	public Integer addMApping(List<LiabilityGLMapping> glMapping) {
		return liabilityDao.addGlMapping(glMapping);
	}

	@Override
	public List<String> distinctModuleCd() {
		return liabilityDao.distinctModuleCd();
	}
	
	@Override
	public Map<String,String> getAllMappingCodeforModuleCd(String moduleCd){
		return liabilityDao.getAllMappingCodeforModuleCd(moduleCd);
	}

	@Override
	public Map<String,String> getAllMappingCodeAndMappingNameforModuleCd(String moduleCd){
		return liabilityDao.getAllMappingCodeAndMappingNameforModuleCd(moduleCd);
	}

	@Override
	public List<String> getRequiredTCodes(String moduleCd, String mappingCd) {
		List<EntityGL> entityGlList = liabilityDao.getRequiredTCodes(moduleCd, mappingCd);
		List<String> tcodes = new ArrayList<>();
		for(EntityGL entityGl : entityGlList) {
			log.info("inside LiabilityServiceImpl#getRequiredTCodes, Main GL code : {}, Sub GL code : {}", entityGl.getMainglCd(), entityGl.getSubglCd());
				if(entityGl.getT1().equals('Y')) {
					tcodes.add("t1");
				} if(entityGl.getT2().equals('Y')) {
					tcodes.add("t2");
				} if(entityGl.getT3().equals('Y')) {
					tcodes.add("t3");
				} if(entityGl.getT4().equals('Y')) {
					tcodes.add("t4");
				} if(entityGl.getT5().equals('Y')) {
					tcodes.add("t5");
				} if(entityGl.getT6().equals('Y')) {
					tcodes.add("t6");
				} if(entityGl.getT7().equals('Y')) {
					tcodes.add("t7");
				} if(entityGl.getT8().equals('Y')) {
					tcodes.add("t8");
				} if(entityGl.getT9().equals('Y')) {
					tcodes.add("t9");
				} if(entityGl.getT10().equals('Y')) {
					tcodes.add("t10");
				} if(entityGl.getT11().equals('Y')) {
					tcodes.add("t11");
				} if(entityGl.getT12().equals('Y')) {
					tcodes.add("t12");
				}
		}
		
		  tcodes = tcodes.stream() .distinct() .collect(Collectors.toList());
		 
		return tcodes;
	}
}
