package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.exception.GLCodeIncompleteDataException;
import in.ecgc.smile.erp.accounts.exception.GLCodeNotFoundException;
import in.ecgc.smile.erp.accounts.model.EntityGL;

/**
 * GL master create operation Service Interface
 * 
 * @version 1.0 22-April-2020
 * @author Sanjali Kesarkar
 */
public interface EntityGLMasterService {

	Integer addGLCode(EntityGL entityGL) throws GLCodeIncompleteDataException;
	
	List<EntityGL> listAllGLCodes();
	
	EntityGL findGLByGLCode(String mainGLCode, String subGLCode) throws GLCodeNotFoundException;

	List<String> findSubBifurcations(String mainGLCode, String subGLCode) throws Exception;

	EntityGL updateGLCode(EntityGL currentEntityGL, EntityGL updatedEntityGL);
	
	Integer disableGLCode(EntityGL entityGL);
}
