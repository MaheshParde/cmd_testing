package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.exception.GLCodeIncompleteDataException;
import in.ecgc.smile.erp.accounts.exception.GLCodeNotFoundException;
import in.ecgc.smile.erp.accounts.exception.GLTxnTypeCodeNotFoundException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.GLTxnType;

/**
 * GL Transaction type master operation Service Interface
 * 
 * @version 1.0 28-May-2020
 * @author Sanjali Kesarkar
 */
public interface GLTxnTypeService {
	
	Boolean addGLTxnTypeCode(GLTxnType glTxnType);
	GLTxnType findGlTxnTypeByGlTxnTypeCode(String glTxnTypeCode);
	List<GLTxnType> listAllGLTxnTypeCodes();
	Integer updateGLTxnTypeCode(String glTxnTypeCode, GLTxnType updatedGlTxnType);
	Integer disableGLTxnTypeCode(String glTxnTypeCode);
	
	
	List<String> getGLTxnType();
//	List<String> getGLTxnLoc();
//	List<GLTxnType> getGLTxnTypeSearch();
}
