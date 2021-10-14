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

import in.ecgc.smile.erp.accounts.model.GLTxnType;
import in.ecgc.smile.erp.accounts.util.BankBranchSqlQueries;
import in.ecgc.smile.erp.accounts.util.GLTxnTypeSqlQueries;

/**
 * 'GL transaction type master creation' DAO implementation
 * 
 * @version 1.0 29-May-2020
 * @author Sanjali Kesarkar
 *
 */
@Repository
@Transactional
public class GLTxnTypeDaoImpl implements GLTxnTypeDao {

	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(GLTxnTypeDaoImpl.class);

	@Autowired
	public GLTxnTypeDaoImpl(DataSource dataSource) {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * Add new GL transaction type code
	 * 
	 * DAO implementation to add new GL transaction type code into database Table
	 * :acct_gl_txn_type_mst
	 */
	@Override
	public Integer addGLTxnTypeCode(GLTxnType glTxnType) {
		Map<String, Object> GLTxnTypenamedParameters = new HashMap<String, Object>();
		glTxnType.setMetaStatus('V');

		GLTxnTypenamedParameters.put("glTxnType", glTxnType.getGlTxnType());
		GLTxnTypenamedParameters.put("txnTypeName", glTxnType.getTxnTypeName());
		GLTxnTypenamedParameters.put("description", glTxnType.getDescription());
		GLTxnTypenamedParameters.put("active", glTxnType.getActive());
		GLTxnTypenamedParameters.put("isConfigurable", glTxnType.getIsConfigurable());
		GLTxnTypenamedParameters.put("openingDay", glTxnType.getOpeningDay());
		GLTxnTypenamedParameters.put("metaStatus", glTxnType.getMetaStatus());
		GLTxnTypenamedParameters.put("createdBy", glTxnType.getCreatedBy());
		GLTxnTypenamedParameters.put("metaRemarks", glTxnType.getMetaRemarks());

		return namedParameterJdbcTemplate.update(GLTxnTypeSqlQueries.ADD_GL_TXN_TYPE_CODE, GLTxnTypenamedParameters);
	}

	/**
	 * Find details of given glTxnTypeCode
	 * 
	 * DAO implementation to find details of given GL transaction type code present
	 * in the database Table :acct_gl_txn_type_mst returns model GLTxnType
	 * {@link in.ecgc.smile.erp.accounts.model.GLTxnType}
	 */
	public GLTxnType findGlTxnTypeByGlTxnTypeCode(String glTxnTypeCode) {

		GLTxnType glTxnType;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glTxnType", glTxnTypeCode);

		try {
			glTxnType = namedParameterJdbcOperations.queryForObject(GLTxnTypeSqlQueries.LOAD_GL_TXN_TYPE_CODE, paramMap,
					new RowMapper<GLTxnType>() {

						@Override
						public GLTxnType mapRow(ResultSet rs, int rowNum) throws SQLException {
							GLTxnType glTxnType = new GLTxnType();

							glTxnType.setGlTxnType(rs.getString("txn_type"));
							glTxnType.setTxnTypeName(rs.getString("txn_type_name"));
							glTxnType.setDescription(rs.getString("description"));
							glTxnType.setActive(rs.getBoolean("del_flag"));
							glTxnType.setIsConfigurable(rs.getBoolean("config_flag"));
							glTxnType.setOpeningDay(rs.getInt("opening_day"));
							glTxnType.setCreatedBy(rs.getString("created_by"));
							glTxnType.setCreatedDt(rs.getDate("created_dt"));
							glTxnType.setLastUpdatedBy(rs.getString("last_updated_by"));
							glTxnType.setLastUpdatedDt(rs.getDate("last_updated_dt"));
							glTxnType.setMetaRemarks(rs.getString("meta_remarks"));

							return glTxnType;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			glTxnType = null;
		}
		return glTxnType;
	}

	/**
	 * View all GL transaction type codes
	 * 
	 * DAO implementation to list all GL transaction type codes present in the
	 * database Table :acct_gl_txn_type_mst returns list of model GLTxnType
	 * {@link in.ecgc.smile.erp.accounts.model.GLTxnType}
	 */
	@Override
	public List<GLTxnType> listAllGLTxnTypeCodes() throws DataAccessException {
		List<GLTxnType> glTxnTypeCodes = new ArrayList<GLTxnType>();
		glTxnTypeCodes = namedParameterJdbcTemplate.query(GLTxnTypeSqlQueries.ALL_GL_TXN_TYPE_CODES,
				new RowMapper<GLTxnType>() {

					@Override
					public GLTxnType mapRow(ResultSet rs, int rowNum) throws SQLException {
						GLTxnType glTxnType = new GLTxnType();

						glTxnType.setGlTxnType(rs.getString("txn_type"));
						glTxnType.setTxnTypeName(rs.getString("txn_type_name"));
						glTxnType.setDescription(rs.getString("description"));
						glTxnType.setActive(rs.getBoolean("del_flag"));
						glTxnType.setIsConfigurable(rs.getBoolean("config_flag"));
						glTxnType.setOpeningDay(rs.getInt("opening_day"));
						//glTxnType.setMetaStatus(rs.getString("meta_status").charAt(0));
						glTxnType.setCreatedBy(rs.getString("created_by"));
						glTxnType.setCreatedDt(rs.getDate("created_dt"));
						glTxnType.setLastUpdatedBy(rs.getString("last_updated_by"));
						glTxnType.setLastUpdatedDt(rs.getDate("last_updated_dt"));
						glTxnType.setMetaRemarks(rs.getString("meta_remarks"));

						return glTxnType;
					}
				});
		return glTxnTypeCodes;
	}

	/**
	 * Update details of given entityGLCode
	 * 
	 * DAO implementation to update details of given GL code present in the database
	 * Table : acct_entity_gl_mst returns 1 if update operation succeeds returns -1
	 * in case of failure
	 */
	@Override
	public Integer updateGLTxnTypeCode(String glTxnTypeCode, GLTxnType updatedGlTxnType) {
		int rowCount;

		try {
			Map<String, Object> GLTxnTypenamedParameters = new HashMap<String, Object>();

			GLTxnTypenamedParameters.put("glTxnType", glTxnTypeCode);
			GLTxnTypenamedParameters.put("txnTypeName", updatedGlTxnType.getTxnTypeName());
			GLTxnTypenamedParameters.put("description", updatedGlTxnType.getDescription());
			GLTxnTypenamedParameters.put("active", updatedGlTxnType.getActive());
			GLTxnTypenamedParameters.put("isConfigurable", updatedGlTxnType.getIsConfigurable());
			GLTxnTypenamedParameters.put("openingDay", updatedGlTxnType.getOpeningDay());
			GLTxnTypenamedParameters.put("updatedBy", updatedGlTxnType.getLastUpdatedBy());
			GLTxnTypenamedParameters.put("metaRemarks", updatedGlTxnType.getMetaRemarks());

			rowCount = namedParameterJdbcTemplate.update(GLTxnTypeSqlQueries.UPDATE_GL_TXN_TYPE_CODE,
					GLTxnTypenamedParameters);
			LOGGER.info( "RowCount",rowCount);

		} catch (DataAccessException e) {
			LOGGER.info("Exception occured while updating new GL code...",e.getMessage());
			return null;
		}
		return rowCount;
	}

	/**
	 * Disable given entityGLCode
	 * 
	 * DAO implementation to disable details of given GL code present in the
	 * database Table : acct_entity_gl_mst returns 1 if disable operation succeeds
	 * returns -1 in case of failure
	 */
	@Override
	public Integer disableGLTxnTypeCode(String GlTxnTypeCode) {
		int rowCount;
		rowCount = namedParameterJdbcTemplate.update(GLTxnTypeSqlQueries.DISABLE_GL_TXN_TYPE_CODE,
				new MapSqlParameterSource("glTxnType", GlTxnTypeCode));
		return rowCount;

	}

	@Override
	public List<String> getGLTxnType() {
		Map<String, Object> paramMap = new HashMap<>();
	//	paramMap.put("logicalLocCode", logicalLocCode);
		List<String> glTxnType=  new ArrayList<String>();
		glTxnType= namedParameterJdbcOperations.query(GLTxnTypeSqlQueries.GL_TXN_TYPE, new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {

					return rs.getString("txn_type");

				}
			});
				return glTxnType;	
	}
}
