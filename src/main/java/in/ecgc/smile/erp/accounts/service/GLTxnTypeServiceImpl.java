package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.GLTxnTypeAlreadyExistException;
import in.ecgc.smile.erp.accounts.exception.GLTxnTypeCodeNotFoundException;
import in.ecgc.smile.erp.accounts.model.GLTxnType;
import in.ecgc.smile.erp.accounts.repository.GLTxnTypeDao;
import lombok.extern.slf4j.Slf4j;

/**
 * 'GL transaction type master creation' service implementation
 * 
 * @version 1.0 29-May-2020
 * @author Sanjali Kesarkar
 *
 */
@Service
@Slf4j
public class GLTxnTypeServiceImpl implements GLTxnTypeService {

	@Autowired
	GLTxnTypeDao glTxnTypeDao;
	
	@Autowired
	GLTxnTypeDao glTxnLocDao;

	/**
	 * Add new GL transaction type code
	 * 
	 * service implementation to add new GL transaction code into database
	 */
	@Override
	public Boolean addGLTxnTypeCode(GLTxnType glTxnType) {
		log.info("Service Layer Add New GL Txn Type");
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
		log.info("Service Layer Find GL Txn Type for : {}", glTxnTypeCode);
		GLTxnType glTxnType = glTxnTypeDao.findGlTxnTypeByGlTxnTypeCode(glTxnTypeCode);
		if (glTxnType == null) {
			log.info("Service Layer Find GL Txn Type for : {} found null", glTxnTypeCode);
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
		log.info("Service Layer Find All GL Txn Types");
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
		log.info("Service Layer Update GL Txn Type for : {}",glTxnTypeCode);
		GLTxnType glTxnType = glTxnTypeDao.findGlTxnTypeByGlTxnTypeCode(glTxnTypeCode);
		if (glTxnType == null) {
			log.info("Service Layer Update GL Txn Type No Record Found for : {}",glTxnTypeCode);
			throw new GLTxnTypeCodeNotFoundException(
					"No GL transaction type details found with the given GL txn type code",
					new String[] { glTxnTypeCode });
		}
		log.info("Service Layer Update GL Txn Type Record Found for : {} Updating",glTxnTypeCode);
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
		log.info("Service Layer Disable GL Txn Type for : {}",glTxnTypeCode);
		GLTxnType glTxnType = glTxnTypeDao.findGlTxnTypeByGlTxnTypeCode(glTxnTypeCode);
		if (glTxnType == null) {
			log.info("Service Layer Disable GL Txn Type No Record Found for : {}",glTxnTypeCode);
			throw new GLTxnTypeCodeNotFoundException(
					"No GL transaction type details found with the given GL txn type code",
					new String[] { glTxnTypeCode });
		}
		return glTxnTypeDao.disableGLTxnTypeCode(glTxnTypeCode);	
	}

//	@Override
//	public List<GLTxnType> getGLTxnTypeSearch() {		
//		log.info("Service Layer Get Names of All GL Txn Types");
//		System.err.println("------****glTxnType--------"+glTxnTypeDao.getGLTxnTypeSearch());
//		return glTxnTypeDao.getGLTxnTypeSearch();
//	}
	
	@Override
	public List<String> getGLTxnType() {		
		log.info("Service Layer Get Names of All GL Txn Types");
		System.err.println("------****glTxnType--------"+glTxnTypeDao.getGLTxnType());
		return glTxnTypeDao.getGLTxnType();
	}

	/*@Override
	public List<String> getGLTxnLoc() {
		log.info("Service Layer Get Names of All GL Txn Location");
		
		System.err.println("glTxnType--------"+glTxnLocDao.getGLTxnLoc());
		return glTxnLocDao.getGLTxnLoc();
	}
*/
}
