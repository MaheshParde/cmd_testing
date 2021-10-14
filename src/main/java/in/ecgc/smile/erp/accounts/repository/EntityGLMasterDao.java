package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.EntityGL;

public interface EntityGLMasterDao {
	Integer addGLCode(EntityGL entityGL);
	List<EntityGL> listAllGLCodes();
	EntityGL findGLByGLCode(String mainGLCode, String subGLCode);
	List<String> findSubBifurcations(String mainGLCode, String subGLCode) throws Exception;
	EntityGL updateGLCode(EntityGL currentEntityGL);
	Integer disableGLCode(EntityGL entityGL);
}
