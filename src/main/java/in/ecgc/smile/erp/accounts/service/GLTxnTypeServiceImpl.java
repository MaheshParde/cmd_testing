package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.GLTxnTypeAlreadyExistException;
import in.ecgc.smile.erp.accounts.exception.GLTxnTypeCodeNotFoundException;
import in.ecgc.smile.erp.accounts.model.GLTxnType;
import in.ecgc.smile.erp.accounts.repository.GLTxnTypeDao;

/**
 * 'GL transaction type master creation' service implementation
 * 
 * @version 1.0 29-May-2020
 * @author Sanjali Kesarkar
 *
 */
@Service
public class GLTxnTypeServiceImpl implements GLTxnTypeService {

	@Autowired
	GLTxnTypeDao glTxnTypeDao;

	/**
	 * Add new GL transaction type code
	 * 
	 * service implementation to add new GL transaction code into database
	 */
	@Override
	public Boolean addGLTxnTypeCode(GLTxnType glTxnType) {

		if (glTxnTypeDao.findGlTxnTypeByGlTxnTypeCode(glTxnType.getGlTxnType()) == null) {
			int result = glTxnTypeDao.addGLTxnTypeCode(glTxnType);
			if (result == 1)
				return true;
			else
				return false;
		} else
			throw new GLTxnTypeAlreadyExistException("GL transaction type already exists.",
					new String[] { glTxnType.getGlTxnType() });
	}

	/**
	 * Find GL transaction type code details by glTxnTyoeCode
	 * 
	 * service implementation to find details of GL transaction type code
	 * 
	 */
	@Override
	public GLTxnType findGlTxnTypeByGlTxnTypeCode(String glTxnTypeCode) {
		GLTxnType glTxnType = glTxnTypeDao.findGlTxnTypeByGlTxnTypeCode(glTxnTypeCode);
		if (glTxnType == null) {
			throw new GLTxnTypeCodeNotFoundException(
					"No GL transaction type details found with the given GL txn type code",
					new String[] { glTxnTypeCode });
		}
		return glTxnType;
	}

	/**
	 * View all GL transaction type codes
	 * 
	 * service implementation to list all GL transaction type codes
	 * 
	 */
	@Override
	public List<GLTxnType> listAllGLTxnTypeCodes() {
		return glTxnTypeDao.listAllGLTxnTypeCodes();
	}

	/**
	 * Update GL transaction type code details
	 * 
	 * service implementation to update details of GL txn type code
	 * 
	 */
	@Override
	public Integer updateGLTxnTypeCode(String glTxnTypeCode, GLTxnType updatedGlTxnType) {
		GLTxnType glTxnType = glTxnTypeDao.findGlTxnTypeByGlTxnTypeCode(glTxnTypeCode);
		if (glTxnType == null) {
			throw new GLTxnTypeCodeNotFoundException(
					"No GL transaction type details found with the given GL txn type code",
					new String[] { glTxnTypeCode });
		}
		return glTxnTypeDao.updateGLTxnTypeCode(glTxnTypeCode, updatedGlTxnType);
	}
	/**
	 * Disable GL transaction type code details
	 * 
	 * service implementation to disable GL transaction type code
	 * 
	 */ 
	@Override
	public Integer disableGLTxnTypeCode(String glTxnTypeCode) {
		GLTxnType glTxnType = glTxnTypeDao.findGlTxnTypeByGlTxnTypeCode(glTxnTypeCode);
		if (glTxnType == null) {
			throw new GLTxnTypeCodeNotFoundException(
					"No GL transaction type details found with the given GL txn type code",
					new String[] { glTxnTypeCode });
		}
		return glTxnTypeDao.disableGLTxnTypeCode(glTxnTypeCode);	
	}

	@Override
	public List<String> getGLTxnType() {		
		return glTxnTypeDao.getGLTxnType();
	}

}
