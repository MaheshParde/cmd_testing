package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;

public interface EntityGLMasterDao {
	Integer addGLCode(EntityGL entityGL);
	List<EntityGL> listAllGLCodes();
	EntityGL findGLByGLCode(String mainGLCode, String subGLCode);
//	String findSubBifurcations(String mainGLCode, String subGLCode);
	EntityGL updateGLCode(EntityGL currentEntityGL);
	Integer disableGLCode(EntityGL entityGL);
	Integer getRegularGLTypesByOpeningDay();
	Integer getConfiguredGLTypesByOpeningDay();
	List<EntityGL> getsubGLCodebyMainGLCode(String mainGLCode);

	List<EntityGL> getAllGlByIsGlGroup();
	
}
