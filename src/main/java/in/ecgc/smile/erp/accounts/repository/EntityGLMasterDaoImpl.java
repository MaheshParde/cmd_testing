package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.util.GLSqlQueries;
import in.ecgc.smile.erp.accounts.util.SubBifurcationsMasterQueries;


@Repository
@Transactional
public class EntityGLMasterDaoImpl implements EntityGLMasterDao {

	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityGLMasterDaoImpl.class);

	@Autowired
	public EntityGLMasterDaoImpl(DataSource dataSource) {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * Add new GL code
	 * 
	 * DAO implementation to add new GL code into database 
	 * Table :acct_entity_gl_mst
	 * returns 1 if insert operation succeeds returns -1 in case of failure
	 */
	
	
	
	@Override
	public Integer addGLCode(EntityGL entityGL) throws DataAccessException {
		int rowCount;
		try {
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
			GLnamedParameters.put("irdaCd", entityGL.getIrdaCd());
			GLnamedParameters.put("irdaBapCd", entityGL.getIrdaBpaCd());
			GLnamedParameters.put("schedule6", entityGL.getSchedule6());
			GLnamedParameters.put("finFormName", entityGL.getFinancialFormName());
			GLnamedParameters.put("oldCd", entityGL.getOldCd());
			GLnamedParameters.put("subBifurcationLevel", entityGL.getSubBifurcationLevel());
			GLnamedParameters.put("createdBy", entityGL.getCreatedBy());
			GLnamedParameters.put("lastUpdatedBy", entityGL.getLastUpdatedBy());
			rowCount = namedParameterJdbcTemplate.update(GLSqlQueries.ADD_GL_CODE, GLnamedParameters);
		} catch (DataAccessException e) {
			LOGGER.info("Exception occured while inserting new GL code...",e);
			return -1;
		}
		if (rowCount == 1)
			return 1;
		else
			return -1;
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
		List<EntityGL> entityGLCodes = new ArrayList<EntityGL>();
		try {
		RowMapper<EntityGL> rowMapper = GLSqlQueries::mapRow;
		entityGLCodes = namedParameterJdbcOperations.query(GLSqlQueries.ALL_GL_CODES,rowMapper);
		}
		catch(Exception e) {
			e.printStackTrace();
			entityGLCodes = null;
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

		EntityGL entityGL;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mainGLCode", mainGLCode);
		paramMap.put("subGLCode", subGLCode);

		try {
			RowMapper<EntityGL> rowMapper = GLSqlQueries::mapRow;
			entityGL = namedParameterJdbcOperations.queryForObject(GLSqlQueries.LOAD_GLCODE, paramMap,rowMapper);
		} catch (EmptyResultDataAccessException e) {
			entityGL = null;
			e.printStackTrace();
		}

		return entityGL;
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
			
			LOGGER.info("updated entity=",entityGL);
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
			GLnamedParameters.put("irdaCd", entityGL.getIrdaCd());
			GLnamedParameters.put("irdaBapCd", entityGL.getIrdaBpaCd());
			GLnamedParameters.put("schedule6", entityGL.getSchedule6());
			GLnamedParameters.put("finFormName", entityGL.getFinancialFormName());
			GLnamedParameters.put("oldCd", entityGL.getOldCd());
			GLnamedParameters.put("subBifurcationLevel", entityGL.getSubBifurcationLevel());
			GLnamedParameters.put("createdBy", entityGL.getCreatedBy());
			GLnamedParameters.put("lastUpdatedBy", entityGL.getLastUpdatedBy());
			rowCount = namedParameterJdbcTemplate.update(GLSqlQueries.UPDATE_GLCODE, GLnamedParameters);
		} catch (DataAccessException e) {
			LOGGER.info("Exception occured while updating new GL code...",e);
			return null;
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
		
		LOGGER.info("updated entity=",entityGL);
		GLnamedParameters.put("entityGLCd", entityGL.getEntityGlCd());
		GLnamedParameters.put("mainglCd", entityGL.getMainglCd());
		GLnamedParameters.put("subglCd", entityGL.getSubglCd());
		
		if (entityGL.getGlIsGroup().equals('N') || entityGL.getGlIsGroup().equals('n')) {
			rowCount = namedParameterJdbcTemplate.update(
			GLSqlQueries.DISABLE_GLCODE, GLnamedParameters);
		}
		else{
			rowCount = namedParameterJdbcTemplate.update(GLSqlQueries.DISABLE_GLCODE, GLnamedParameters);
		}
		
		if (rowCount >= 1)
			return 1;
		else
			return -1;
			
	}

	@Override
	public List<String> findSubBifurcations(String mainGLCode, String subGLCode) throws Exception {
		try {
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			
			paramMap.put("mainGl", mainGLCode);
			paramMap.put("subGl",subGLCode);
			
			List<String> list;
			list =namedParameterJdbcTemplate.query(GLSqlQueries.GET_SUB_BIFURCATION_CDS,paramMap,new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					return rs.getString("value");
				}
			});
			System.err.println("inside dao "+ list);
			return list;
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}

	}	

}
