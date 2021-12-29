package in.ecgc.smile.erp.accounts.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;
import in.ecgc.smile.erp.accounts.repository.EntityGLMasterDao;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class EntityGLMasterServiceImpl implements EntityGLMasterService {
	//private static final Logger log = LoggerFactory.getLogger(EntityGLMasterServiceImpl.class);

	
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
	public Integer addGLCode(EntityGL entityGL){
		log.info("Service: Adding new GL Code");
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
		log.info("Service: Reading all GL Codes");
		List<EntityGL> list= entityGLMasterDao.listAllGLCodes();
		if(list == null) {
			log.info("Service: Reading all GL Codes , Returning empty GL Code List");
			return null;
		}
		else 
			return list;
	}
	
	/**
	 * Find GL code details by entityGLCode
	 * 
	 * service implementation to find details of GL code
	 * 
	 */ 
	@Override
	public EntityGL findGLByGLCode(String mainGLCode, String subGLCode){
		return entityGLMasterDao.findGLByGLCode(mainGLCode,subGLCode);
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
		currentEntityGL.setBalInd(updatedEntityGL.getBalInd());
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
		currentEntityGL.setT13(updatedEntityGL.getT13());
		currentEntityGL.setT14(updatedEntityGL.getT14());
		
		log.info("after setting updated values to currentEntityGL = " + currentEntityGL);
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
		
		if(entityGL.getGlIsGroup().equals('N') || entityGL.getGlIsGroup().equals('n'))
			return entityGLMasterDao.disableGLCode(entityGL);
		else
			return -1;
	
	}

//	@Override
//	public String findSubBifurcations(String mainGLCode, String subGLCode)  {
//		log.info("Inside getSubBifurcations Method");
//		
//		String list = entityGLMasterDao.findSubBifurcations(mainGLCode, subGLCode);
//		//log.error("+++++++++++Bifurcation level Code is +++++++" + list);
//		if(list==null)
//			return null;
//		else
//			return list;
//	}

	@Override
	public Integer getRegularGLTypesByOpeningDay() {
		return entityGLMasterDao.getRegularGLTypesByOpeningDay();
	}

	@Override
	public Integer getConfiguredGLTypesByOpeningDay() {
		return entityGLMasterDao.getConfiguredGLTypesByOpeningDay();
	}

	@Override
	public 	List<EntityGL> getsubGLCodebyMainGLCode(String mainGLCode)
 {
		System.out.println("main GL codes Are ===="+entityGLMasterDao.getsubGLCodebyMainGLCode(mainGLCode));
		return entityGLMasterDao.getsubGLCodebyMainGLCode(mainGLCode);
	}

	@Override
	public List<EntityGL> getAllGlByIsGlGroup() {
		return entityGLMasterDao.getAllGlByIsGlGroup();
	}

	

}
