package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.ProvisionGLMapping;

public interface ProvisionDao {

	List<ProvisionGLMapping> getMAppingList();
	List<ProvisionGLMapping> getMAppingListforModule(String moduleCd);
	List<ProvisionGLMapping> getMAppingListforModule(String moduleCd, String mappingCd);
	Integer addGlMapping(List<ProvisionGLMapping> glMaping);
	
}
