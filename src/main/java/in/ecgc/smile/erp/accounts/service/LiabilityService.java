/**
 * 
 */
package in.ecgc.smile.erp.accounts.service;

import java.util.List;
import java.util.Map;

import in.ecgc.smile.erp.accounts.exception.TCodeNotPresentException;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.model.Liability;
import in.ecgc.smile.erp.accounts.model.LiabilityGLMapping;

/**
 * @author CMJ-SENG-LAPTOP9
 *
 */
public interface LiabilityService {
	GlTxnHdr createLiability(Liability liability) throws TCodeNotPresentException;
	List<LiabilityGLMapping> getMapping(String moduleCd, String mappingCd);
	List<LiabilityGLMapping> getMapping(String moduleCd);
	List<LiabilityGLMapping> getMapping();
	Integer addMApping(List<LiabilityGLMapping> glMapping);
	Map<String,String> getAllMappingCodeforModuleCd(String moduleCd);
	List<String> distinctModuleCd();
	Map<String,String> getAllMappingCodeAndMappingNameforModuleCd(String moduleCd);
	List<String> getRequiredTCodes(String moduleCd, String mappingCd);
}
