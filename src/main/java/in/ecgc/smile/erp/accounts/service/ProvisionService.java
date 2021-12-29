package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.exception.TCodeNotPresentException;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.model.Provision;
import in.ecgc.smile.erp.accounts.model.ProvisionGLMapping;

public interface ProvisionService {

	GlTxnHdr createProvision(Provision provision) throws TCodeNotPresentException;
	List<ProvisionGLMapping> getMapping(String moduleCd, String mappingCd);
	List<ProvisionGLMapping> getMapping(String moduleCd);
	List<ProvisionGLMapping> getMapping();
	Integer addMApping(List<ProvisionGLMapping> glMapping);
	
}
