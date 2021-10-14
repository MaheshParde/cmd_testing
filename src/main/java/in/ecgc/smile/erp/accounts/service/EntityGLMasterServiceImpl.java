package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.GLCodeIncompleteDataException;
import in.ecgc.smile.erp.accounts.exception.GLCodeNotFoundException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.repository.EntityGLMasterDao;


@Service
public class EntityGLMasterServiceImpl implements EntityGLMasterService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EntityGLMasterServiceImpl.class);

	
	@Autowired
	EntityGLMasterDao entityGLMasterDao;

	/**
	 * Add new GL code
	 * 
	 * service implementation to add new GL code into database throws
	 * GLCodeImcompleteDataException{@link in.ecgc.erp.accounts.exception.GLCodeImcompleteDataException}
	 * if input data is invalid.
	 */
	@Override
	public Integer addGLCode(EntityGL entityGL) throws GLCodeIncompleteDataException {
		if ( entityGL.getMainglCd() == null || entityGL.getSubglCd() == null
				|| entityGL.getGlName().isEmpty() || entityGL.getGlType().isEmpty() || entityGL.getGlSubtype().isEmpty())
			if (entityGL.getGlIsGroup().equals('N') || entityGL.getGlIsGroup().equals('n'))
				if (entityGL.getPlLevel() != null) {
					throw new GLCodeIncompleteDataException("Incomplete GL code Data!");
				}
		return entityGLMasterDao.addGLCode(entityGL);
	}

	/**
	 * View all GL codes
	 * 
	 * service implementation to list all GL codes
	 * 
	 */ 	
	@Override
	public List<EntityGL> listAllGLCodes() {
		return entityGLMasterDao.listAllGLCodes();
	}
	
	/**
	 * Find GL code details by entityGLCode
	 * 
	 * service implementation to find details of GL code
	 * 
	 */ 
	@Override
	public EntityGL findGLByGLCode(String mainGLCode, String subGLCode) throws GLCodeNotFoundException {
		EntityGL entityGL = entityGLMasterDao.findGLByGLCode(mainGLCode,subGLCode);
		if(entityGL == null) {
			throw new GLCodeNotFoundException("No GL code details found with the given entityGL code");
		}
		return entityGL;
	}

	/**
	 * Update GL code details
	 * 
	 * service implementation to update details of GL code
	 * 
	 */ 
	@Override
	public EntityGL updateGLCode(EntityGL currentEntityGL, EntityGL updatedEntityGL) {
		
		currentEntityGL.setGlName(updatedEntityGL.getGlName());
		currentEntityGL.setGlType(updatedEntityGL.getGlType());
		currentEntityGL.setGlSubtype(updatedEntityGL.getGlSubtype());
		currentEntityGL.setBalInd(updatedEntityGL.getBalInd());
		currentEntityGL.setZeroBalFlg(updatedEntityGL.getZeroBalFlg());
		currentEntityGL.setCashaccount(updatedEntityGL.getCashaccount());
		currentEntityGL.setMetaRemarks(updatedEntityGL.getMetaRemarks());
		currentEntityGL.setLastUpdatedBy(updatedEntityGL.getLastUpdatedBy());
		currentEntityGL.setMetaRemarks(updatedEntityGL.getMetaRemarks());
		currentEntityGL.setOldCd(updatedEntityGL.getOldCd());
		currentEntityGL.setCashFlow(updatedEntityGL.getCashFlow());
		currentEntityGL.setPlLevel(updatedEntityGL.getPlLevel());
		currentEntityGL.setIrdaBpaCd(updatedEntityGL.getIrdaBpaCd());
		currentEntityGL.setIrdaCd(updatedEntityGL.getIrdaCd());
		currentEntityGL.setSchedule6(updatedEntityGL.getSchedule6());
		currentEntityGL.setFinancialFormName(updatedEntityGL.getFinancialFormName());
		currentEntityGL.setSubBifurcationLevel(updatedEntityGL.getSubBifurcationLevel());
		currentEntityGL.setT1(updatedEntityGL.getT1());
		currentEntityGL.setT2(updatedEntityGL.getT2());
		currentEntityGL.setT3(updatedEntityGL.getT3());
		currentEntityGL.setT4(updatedEntityGL.getT4());
		currentEntityGL.setT5(updatedEntityGL.getT5());
		currentEntityGL.setT6(updatedEntityGL.getT6());
		currentEntityGL.setT7(updatedEntityGL.getT7());
		currentEntityGL.setT8(updatedEntityGL.getT8());
		currentEntityGL.setT9(updatedEntityGL.getT9());
		currentEntityGL.setT10(updatedEntityGL.getT10());
		currentEntityGL.setT11(updatedEntityGL.getT11());
		currentEntityGL.setT12(updatedEntityGL.getT12());
		
		LOGGER.info("after setting updated values to currentEntityGL = " + currentEntityGL);
		return entityGLMasterDao.updateGLCode(currentEntityGL);
	}
	/**
	 * Disable GL code details
	 * 
	 * service implementation to disable GL code
	 * 
	 */ 
	@Override
	public Integer disableGLCode(EntityGL entityGL) {
		
		return entityGLMasterDao.disableGLCode(entityGL);
	
	}

	@Override
	public List<String> findSubBifurcations(String mainGLCode, String subGLCode) throws Exception {
		LOGGER.info("Inside getSubBifurcations Method");
		
		List<String> list = null;
		try {
			 list = entityGLMasterDao.findSubBifurcations(mainGLCode, subGLCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
