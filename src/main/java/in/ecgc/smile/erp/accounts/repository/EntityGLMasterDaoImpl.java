package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.exception.GLCodeNotFoundException;
import in.ecgc.smile.erp.accounts.exception.InsertEntityGLFailException;
import in.ecgc.smile.erp.accounts.exception.ReadEntityGLFailException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;
import in.ecgc.smile.erp.accounts.util.EntityGLSqlQueries;
import in.ecgc.smile.erp.accounts.util.SubBifurcationsMasterQueries;
import in.ecgc.smile.erp.sys.auth.be.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;


@Repository
@Transactional
@Slf4j
public class EntityGLMasterDaoImpl implements EntityGLMasterDao {
	
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	public EntityGLMasterDaoImpl(DataSource dataSource) {
		
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
//	private static final Logger log = LoggerFactory.getLogger(EntityGLMasterDaoImpl.class);

	/**
	 * Add new GL code
	 * 
	 * DAO implementation to add new GL code into database 
	 * Table :acct_entity_gl_mst
	 * returns 1 if insert operation succeeds returns -1 in case of failure
	 */
	
	
	
	@Override
	public Integer addGLCode(EntityGL entityGL) throws DataAccessException {
		log.info("DAO: Adding new GL Code");
		int rowCount = 0;
		try {
			log.info("DAO: UserInfoService:{}",userInfoService);
			Map<String, Object> GLnamedParameters = new HashMap<String, Object>();
			GLnamedParameters.put("entityGLCd", entityGL.getEntityGlCd());
			GLnamedParameters.put("mainglCd", entityGL.getMainglCd());
			GLnamedParameters.put("subglCd", entityGL.getSubglCd());
			GLnamedParameters.put("glName", entityGL.getGlName());
			GLnamedParameters.put("glIsGroup",entityGL.getGlIsGroup());
			GLnamedParameters.put("glType", entityGL.getGlType());
			GLnamedParameters.put("glSubtype", entityGL.getGlSubtype());
			GLnamedParameters.put("balInd", entityGL.getBalInd());
			GLnamedParameters.put("zeroBalFlg",entityGL.getZeroBalFlg());
			GLnamedParameters.put("active",entityGL.getActive());
			GLnamedParameters.put("personalLedgerLevel", entityGL.getPlLevel());
			GLnamedParameters.put("cashaccount", entityGL.getCashaccount());
			GLnamedParameters.put("cashFlow", entityGL.getCashFlow());
			GLnamedParameters.put("logicalLocCd", entityGL.getLogicalLocCd());
			GLnamedParameters.put("plLevel", entityGL.getPlLevel());
			GLnamedParameters.put("metaRemarks", entityGL.getMetaRemarks());
			GLnamedParameters.put("t1", entityGL.getT1());
			GLnamedParameters.put("t2", entityGL.getT2());
			GLnamedParameters.put("t3", entityGL.getT3());
			GLnamedParameters.put("t4", entityGL.getT4());
			GLnamedParameters.put("t5", entityGL.getT5());
			GLnamedParameters.put("t6", entityGL.getT6());
			GLnamedParameters.put("t7", entityGL.getT7());
			GLnamedParameters.put("t8", entityGL.getT8());
			GLnamedParameters.put("t9", entityGL.getT9());
			GLnamedParameters.put("t10", entityGL.getT10());
			GLnamedParameters.put("t11", entityGL.getT11());
			GLnamedParameters.put("t12", entityGL.getT12());
			GLnamedParameters.put("t13", entityGL.getT13());
			GLnamedParameters.put("t14", entityGL.getT14());
			GLnamedParameters.put("irdaCd", entityGL.getIrdaCd());
			GLnamedParameters.put("irdaBapCd", entityGL.getIrdaBpaCd());
			GLnamedParameters.put("schedule6", entityGL.getSchedule6());
			GLnamedParameters.put("finFormName", entityGL.getFinancialFormName());
			GLnamedParameters.put("oldCd", entityGL.getOldCd());
			GLnamedParameters.put("subBifurcationLevel", entityGL.getSubBifurcationLevel());
			GLnamedParameters.put("createdBy", userInfoService.getUser());
			GLnamedParameters.put("lastUpdatedBy", userInfoService.getUser());
			log.debug("DAO: Adding new Gl Code, ParameterList:{}",GLnamedParameters);
			rowCount = namedParameterJdbcTemplate.update(EntityGLSqlQueries.ADD_GL_CODE, GLnamedParameters);
			
		} catch (Exception e) {
			log.error("Exception occured while inserting new GL code",e);
			throw new InsertEntityGLFailException("Failed to Insert new GL Code" + e.getMessage());
		}
		if (rowCount == 1) {
			log.info("DAO: Added new GL Code with rowCount:{} returning 1",rowCount);
			return 1;
		}
		else {
			log.info("DAO: Added new GL Code with rowCount:{} returning -1",rowCount);
			return -1;
		}
			
	}

	/**
	 * View all GL codes
	 * 
	 * DAO implementation to list all GL codes present in the database
	 * Table :acct_entity_gl_mst
	 * returns list of model EntityGL {@link in.ecgc.smile.erp.accounts.model.EntityGL}
	 */
	@Override
	public List<EntityGL> listAllGLCodes() throws DataAccessException {
		log.info("DAO Reading all GL Codes");
		List<EntityGL> entityGLCodes = new ArrayList<EntityGL>();
		try {
		RowMapper<EntityGL> rowMapper = EntityGLSqlQueries::mapRow;
		entityGLCodes = namedParameterJdbcTemplate.query(EntityGLSqlQueries.ALL_GL_CODES,rowMapper);
		}
		catch(Exception e) {
			log.error("DAO EntityGLMasterDaoImpl#listAllGLCodes Exception occured while reading List of All GL codes",e);
			throw new ReadEntityGLFailException("Failed to read GL Code" + e.getMessage());
		}
		return entityGLCodes;
	}

	/**
	 * Find details of given entityGLCode
	 * 
	 * DAO implementation to find details of gievn GL code present in the database
	 * Table : acct_entity_gl_mst returns model EntityGL {@link in.ecgc.smile.erp.accounts.model.EntityGL}
	 */
	public EntityGL findGLByGLCode(String mainGLCode, String subGLCode) {

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("mainGLCode", mainGLCode);
		paramMap.put("subGLCode", subGLCode);

		try {
			RowMapper<EntityGL> rowMapper = EntityGLSqlQueries::mapRow;
			return  namedParameterJdbcTemplate.queryForObject(EntityGLSqlQueries.LOAD_GLCODE, paramMap,rowMapper);
		} catch (Exception e) {
			log.error("exception in EntityGLMasterDaoImpl#findGLByGLCode : {}", e.getMessage());
			throw new GLCodeNotFoundException("No GL code details found for GL Code for MainGl : "+mainGLCode+" and SubGl : "+subGLCode+"");
		}
	}

	/**
	 * Update details of given entityGLCode
	 * 
	 * DAO implementation to update details of given GL code present in the database
	 * Table : acct_entity_gl_mst 
	 * returns 1 if update operation succeeds returns -1 in case of failure
	 */
	@Override
	public EntityGL updateGLCode(EntityGL entityGL) {
		int rowCount;
		try {
			Map<String, Object> GLnamedParameters = new HashMap<String, Object>();
			
			log.info("updated entity=",entityGL);
			GLnamedParameters.put("entityGLCd", entityGL.getEntityGlCd());
			GLnamedParameters.put("mainglCd", entityGL.getMainglCd());
			GLnamedParameters.put("subglCd", entityGL.getSubglCd());
			GLnamedParameters.put("glName", entityGL.getGlName());
			GLnamedParameters.put("glIsGroup",entityGL.getGlIsGroup());
			GLnamedParameters.put("glType", entityGL.getGlType());
			GLnamedParameters.put("glSubtype", entityGL.getGlSubtype());
			GLnamedParameters.put("balInd", entityGL.getBalInd());
			GLnamedParameters.put("zeroBalFlg",entityGL.getZeroBalFlg());
			GLnamedParameters.put("active",entityGL.getActive());
			GLnamedParameters.put("personalLedgerLevel", entityGL.getPlLevel());
			GLnamedParameters.put("cashaccount", entityGL.getCashaccount());
			GLnamedParameters.put("cashFlow", entityGL.getCashFlow());
			GLnamedParameters.put("logicalLocCd", entityGL.getLogicalLocCd());
			GLnamedParameters.put("plLevel", entityGL.getPlLevel());
			GLnamedParameters.put("metaRemarks", entityGL.getMetaRemarks());
			GLnamedParameters.put("t1", entityGL.getT1());
			GLnamedParameters.put("t2", entityGL.getT2());
			GLnamedParameters.put("t3", entityGL.getT3());
			GLnamedParameters.put("t4", entityGL.getT4());
			GLnamedParameters.put("t5", entityGL.getT5());
			GLnamedParameters.put("t6", entityGL.getT6());
			GLnamedParameters.put("t7", entityGL.getT7());
			GLnamedParameters.put("t8", entityGL.getT8());
			GLnamedParameters.put("t9", entityGL.getT9());
			GLnamedParameters.put("t10", entityGL.getT10());
			GLnamedParameters.put("t11", entityGL.getT11());
			GLnamedParameters.put("t12", entityGL.getT12());
			GLnamedParameters.put("t13", entityGL.getT13());
			GLnamedParameters.put("t14", entityGL.getT14());
			GLnamedParameters.put("irdaCd", entityGL.getIrdaCd());
			GLnamedParameters.put("irdaBapCd", entityGL.getIrdaBpaCd());
			GLnamedParameters.put("schedule6", entityGL.getSchedule6());
			GLnamedParameters.put("finFormName", entityGL.getFinancialFormName());
			GLnamedParameters.put("oldCd", entityGL.getOldCd());
			GLnamedParameters.put("subBifurcationLevel", entityGL.getSubBifurcationLevel());
			GLnamedParameters.put("createdBy", entityGL.getCreatedBy());
			GLnamedParameters.put("lastUpdatedBy", entityGL.getLastUpdatedBy());
			rowCount = namedParameterJdbcTemplate.update(EntityGLSqlQueries.UPDATE_GLCODE, GLnamedParameters);
		} catch (DataAccessException e) {
			log.info("Exception occured while updating new GL code...",e);
			throw new InsertEntityGLFailException("Failed to Insert new GL Code" + e.getMessage());
	
		}
		if (rowCount == 1)
			return entityGL;
		else
			return null;
	}
	/**
	 * Disable given entityGLCode
	 * 
	 * DAO implementation to disable details of given GL code present in the database
	 * Table : acct_entity_gl_mst 
	 * returns 1 if disable operation succeeds returns -1 in case of failure
	 */
	@Override
	public Integer disableGLCode(EntityGL entityGL) {
		int rowCount;
		Map<String, Object> GLnamedParameters = new HashMap<String, Object>();
		
		log.info("updated entity=",entityGL);
		GLnamedParameters.put("entityGLCd", entityGL.getEntityGlCd());
		GLnamedParameters.put("mainglCd", entityGL.getMainglCd());
		GLnamedParameters.put("subglCd", entityGL.getSubglCd());
		rowCount = namedParameterJdbcTemplate.update(EntityGLSqlQueries.DISABLE_GLCODE, GLnamedParameters);
		if (rowCount >= 1)
			return 1;
		else
			return -1;
	}

//	@Override
//	public String findSubBifurcations(String mainGLCode, String subGLCode) {
//	try {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("mainGl", mainGLCode);
//			paramMap.put("subGl",subGLCode);
//			String levelCode;
//			levelCode =namedParameterJdbcTemplate.queryForObject(EntityGLSqlQueries.GET_SUB_BIFURCATION_CDS,paramMap,new RowMapper<String>() {
//			@Override
//			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//					return rs.getString("value");
//				}
//			});
//			System.err.println("inside dao "+ levelCode);
//			return levelCode;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}

	@Override
	public Integer getRegularGLTypesByOpeningDay() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(null,null);
		Integer count;
		try {
			count = namedParameterJdbcOperations.queryForObject(EntityGLSqlQueries.GET_REGULAR_GL_COUNT,params,
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							int num =rs.getInt(1);
							return num;
						}
					});
			return count;
		} catch (Exception e) {
			log.info("Exception in EntityGLMasterDaoImpl#getRegularGLTypesByOpeningDay");
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Integer getConfiguredGLTypesByOpeningDay() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(null,null);
		try {
			Integer count = namedParameterJdbcOperations.queryForObject(EntityGLSqlQueries.GET_CONFIGURED_GL_COUNT,params,
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							int num =rs.getInt(1);
							return num;
						}
					});
			
			return count;
		} catch (Exception e) {
			log.info("Exception in EntityGLMasterDaoImpl#getConfiguredGLTypesByOpeningDay");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EntityGL>  getsubGLCodebyMainGLCode(String mainGLCode) {
		List<EntityGL> entityList = new ArrayList<>(); 
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("mainGLCode", mainGLCode);
		RowMapper<EntityGL> rowMapper = EntityGLSqlQueries::mapRow;
		entityList = namedParameterJdbcTemplate.query(EntityGLSqlQueries.GET_ALL_BY_MAIN_GL_CODE,paramMap, rowMapper);
			
		System.err.println("inside dao "+ entityList);
		return entityList ;
		//return null;
	}

	@Override
	public List<EntityGL> getAllGlByIsGlGroup() {
		log.info("DAO Reading all GL Codes");
		List<EntityGL> entityGLCodes = new ArrayList<EntityGL>();
		try {
		RowMapper<EntityGL> rowMapper = EntityGLSqlQueries::mapRow;
		entityGLCodes = namedParameterJdbcTemplate.query(EntityGLSqlQueries.GET_ALL_GL_IS_GL_NAME,rowMapper);
		}
		catch(Exception e) {
			log.error("DAO EntityGLMasterDaoImpl#listAllGLCodes Exception occured while reading List of All GL codes",e);
			throw new ReadEntityGLFailException("Failed to read GL Code" + e.getMessage());
		}
		return entityGLCodes;
	}
	
	
}


