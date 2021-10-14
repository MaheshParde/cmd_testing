package in.ecgc.smile.erp.accounts.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.TCodeNotPresentException;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.model.Provision;
import in.ecgc.smile.erp.accounts.model.ProvisionGLMapping;
import in.ecgc.smile.erp.accounts.repository.ProvisionDao;

@Service
public class ProvisionServiceImpl implements ProvisionService{
	@Autowired
	GlTxnService glTxnService;
	@Autowired
	ProvisionDao provisionDao;
	
	@Override
	public GlTxnHdr createProvision(Provision provision) throws TCodeNotPresentException {
		GlTxnHdr glTxnHdr = new GlTxnHdr();
		glTxnHdr.setEntityCd("ECGC");
		List<ProvisionGLMapping> mappingList = provisionDao.getMAppingListforModule(provision.getModuleCd(),provision.getMappingCd());
		glTxnHdr.setLogicalLocCd(provision.getLogicalLogCd());
		//glTxnHdr.setTxnDt(provision.getTxnDt());
		glTxnHdr.setTxnDt(LocalDate.now());
		glTxnHdr.setCreatedBy(provision.getUserId().toString());
		Double amt =0.0;
		if(!provision.getIsAmtInInr()) {
			amt = provision.getBaseAmt() * provision.getExchangeRate();
			amt = BigDecimal.valueOf(amt)
				    .setScale(2, RoundingMode.HALF_UP)
				    .doubleValue();
		}
		else {
			amt = provision.getBaseAmt();
		}
		String txnType= null;
		List<GlTxnDtl> glTxnDtls = new ArrayList<>();
		for(ProvisionGLMapping glMapping : mappingList) {
			GlTxnDtl dtl = new GlTxnDtl();
			dtl.setGlTxnType(glMapping.getTxnType());
			dtl.setDrCrFlag(glMapping.getDrCrFlag());
			dtl.setLogicalLocCd(provision.getLogicalLogCd());
			dtl.setMainGlCd(glMapping.getMainGL());
			dtl.setSubGlCd(glMapping.getSubGL());
			dtl.setSubBiFurcationCd(glMapping.getSubBifurcation());
			dtl.setCreatedBy(provision.getUserId().toString());
			Double amt1 = amt * glMapping.getAmtCalc();
			Double truncatedAmt = BigDecimal.valueOf(amt1)
			    .setScale(2, RoundingMode.HALF_UP)
			    .doubleValue();
			System.out.println(truncatedAmt);
			dtl.setTxnAmt(truncatedAmt);
			dtl.setT1(provision.getT1());
			dtl.setT2(provision.getT2());
			dtl.setT3(provision.getT3());
			dtl.setT4(provision.getT4());
			dtl.setT5(provision.getT5());
			dtl.setT6(provision.getT6());
			dtl.setT7(provision.getT7());
			dtl.setT8(provision.getT8());
			dtl.setT9(provision.getT9());
			dtl.setT10(provision.getT10());
			dtl.setT11(provision.getT11());
			dtl.setT11(provision.getT12());
			dtl.setAd1(provision.getAd1());
			dtl.setAd2(provision.getAd2());
			dtl.setAd3(provision.getAd3());
			dtl.setAd4(provision.getAd4());
			glTxnDtls.add(dtl);
			
			txnType= glMapping.getTxnType();
		}
		glTxnHdr.setGlTxnType(txnType);
		glTxnHdr.setFiscalYr(provision.getFiscalYr());
		glTxnHdr.setGlTxnDtls(glTxnDtls);
		Integer glTxnNo = glTxnService.addGlTxn(glTxnHdr);
		if(glTxnNo >0) {
			glTxnHdr.setGlTxnNo(glTxnNo);
			glTxnHdr.setGlTxnDtls(null);
			return glTxnHdr;
		}
		return null;
	}

	@Override
	public List<ProvisionGLMapping> getMapping(String moduleCd, String mappingCd) {
		try {
			List<ProvisionGLMapping> mappingList = provisionDao.getMAppingListforModule(moduleCd,mappingCd);
			return mappingList;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<ProvisionGLMapping> getMapping(String moduleCd) {
		try {
			List<ProvisionGLMapping> mappingList = provisionDao.getMAppingListforModule(moduleCd);
			return mappingList;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;	
		}

	@Override
	public List<ProvisionGLMapping> getMapping() {
		try {
			List<ProvisionGLMapping> mappingList = provisionDao.getMAppingList();
			return mappingList;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	

	@Override
	public Integer addMApping(List<ProvisionGLMapping> glMapping) {
		return provisionDao.addGlMapping(glMapping);
	}

	
}
