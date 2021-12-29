package in.ecgc.smile.erp.accounts.repository;

import java.util.List;
import java.util.Map;

import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.LiabilityGLMapping;

public interface LiabilityDao {

	List<LiabilityGLMapping> getMAppingList();
	List<LiabilityGLMapping> getMAppingListforModule(String moduleCd);
	List<LiabilityGLMapping> getMAppingListforModule(String moduleCd, String mappingCd);
	Integer addGlMapping(List<LiabilityGLMapping> glMaping);
	Map<String,String> getAllMappingCodeforModuleCd(String moduleCd);
	List<String> distinctModuleCd();
	Map<String,String> getAllMappingCodeAndMappingNameforModuleCd(String moduleCd);
	List<EntityGL> getRequiredTCodes(String moduleCd, String mappingCd);
}
