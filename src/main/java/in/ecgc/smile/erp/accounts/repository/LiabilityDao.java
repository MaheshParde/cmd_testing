package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.LiabilityGLMapping;

public interface LiabilityDao {

	List<LiabilityGLMapping> getMAppingList();
	List<LiabilityGLMapping> getMAppingListforModule(String moduleCd);
	List<LiabilityGLMapping> getMAppingListforModule(String moduleCd, String mappingCd);
	Integer addGlMapping(List<LiabilityGLMapping> glMaping);
}
