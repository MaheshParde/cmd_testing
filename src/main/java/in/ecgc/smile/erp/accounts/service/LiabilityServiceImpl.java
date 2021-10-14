/**
 * 
 */
package in.ecgc.smile.erp.accounts.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.TCodeNotPresentException;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.model.Liability;
import in.ecgc.smile.erp.accounts.model.LiabilityGLMapping;
import in.ecgc.smile.erp.accounts.repository.LiabilityDao;

/**
 * @author CMJ-SENG-LAPTOP9
 *
 */
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
			System.out.println(truncatedAmt);
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
		System.err.println("Gl headr"+ glTxnHdr);
		Integer glTxnNo = glTxnServive.addGlTxn(glTxnHdr);
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
	

}
