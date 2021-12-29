package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.GLTxnType;

public interface GLTxnTypeDao {
	Integer addGLTxnTypeCode(GLTxnType glTxnType);
	GLTxnType findGlTxnTypeByGlTxnTypeCode(String glTxnTypeCode);
	List<GLTxnType> listAllGLTxnTypeCodes();	
	Integer updateGLTxnTypeCode(String glTxnTypeCode,GLTxnType updatedGlTxnType);
	Integer disableGLTxnTypeCode(String glTxnTypeCode);
	List<String> getGLTxnType();
//	List<String> getGLTxnLoc();
//	List<GLTxnType> getGLTxnTypeSearch();

}
