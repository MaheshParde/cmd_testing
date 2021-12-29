package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import in.ecgc.smile.erp.accounts.exception.BankBranchNotFoundException;
import in.ecgc.smile.erp.accounts.exception.InsertEntityGLFailException;
import in.ecgc.smile.erp.accounts.exception.SubBifurcationLevelCodeNotFound;
import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.model.SubBifurcations;
import in.ecgc.smile.erp.accounts.util.BankBranchSqlQueries;
import in.ecgc.smile.erp.accounts.util.EntityGLSqlQueries;
import in.ecgc.smile.erp.accounts.util.SubBifurcationsMasterQueries;
import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class SubBifurcationMasterDaoImpl implements SubBifurcationMasterDao {

	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	@Autowired
	public SubBifurcationMasterDaoImpl(DataSource dataSource) {
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<SubBifurcations> getSubBifurcations()  {
		try {
			List<SubBifurcations> list;
			RowMapper<SubBifurcations> rowMapper= SubBifurcationsMasterQueries::mapRow;
			list =namedParameterJdbcTemplate.query(SubBifurcationsMasterQueries.GET_ALL_BIFURCATION,rowMapper);
			return list;
		}
			catch (Exception e) {
				throw new SubBifurcationLevelCodeNotFound("No Sub Bifurcation details found with the given levelCode  ",
						new String[] {});
			}
	}

	@Override
	public Integer addSubBifurcation(SubBifurcations subBifurcations) {
		Integer rowNum = 0;
		try {
		Map<String, Object> paramMapForHdr = 	SubBifurcationsMasterQueries.getParamMap(subBifurcations);
		rowNum = namedParameterJdbcTemplate.update(SubBifurcationsMasterQueries.ADD_BIFURCATION, paramMapForHdr);
			return rowNum;		
			}
		catch (DataAccessException e) {
			log.error(""+e.getStackTrace());
		//	throw new SubBifurcationLevelCodeNotFound();
		}
		return rowNum;
	}

	@Override
	public SubBifurcations getSubBifurcationsByLevel(String subBifurcationLevel) {
		SubBifurcations  list = new SubBifurcations();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("level",subBifurcationLevel);
		try {
			list = namedParameterJdbcOperations.queryForObject(SubBifurcationsMasterQueries.GET_BIFURCATION_BY_LEVEL,
					paramMap,new RowMapper<SubBifurcations>() {

				@Override
				public SubBifurcations mapRow(ResultSet rs, int rowNum) throws SQLException {
						SubBifurcations subBifurcation = new SubBifurcations();				
					subBifurcation.setSubBifurcationLevel(rs.getString("bifurcation_level_code"));
					subBifurcation.setDescription(rs.getString("description"));
					subBifurcation.setCreatedBy(rs.getString("created_by"));
					subBifurcation.setLastUpdatedBy(rs.getString("last_updated_by"));
					subBifurcation.setMetaRemarks(rs.getString("meta_remarks"));
				
						return subBifurcation;
					}
				
			});
		} catch (Exception e) {
			
			throw new SubBifurcationLevelCodeNotFound("No Sub Bifurcation details found with the given levelCode  ",
					new String[] { subBifurcationLevel});
		}
		return list;	
	}

	@Override
	public List<String> getSubBifurcationLevels() throws Exception {
		try {
			List<String> list;
			list =namedParameterJdbcTemplate.query(SubBifurcationsMasterQueries.GET_BIFURCATION__LEVELS,new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					return rs.getString("bifurcation_level_code");
				}
			});
			return list;
			}
			catch (Exception e) {
//				e.printStackTrace();
				throw new SubBifurcationLevelCodeNotFound("No Sub Bifurcation details found with the given levelCode  ",
						new String[] {});
			}

	}

	@Override
	public SubBifurcations updateSubBifurcationLevel(SubBifurcations subBifurcations) {
		int rowCount;
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			
			log.info("updated entity=",subBifurcations);
			namedParameters.put("subBifurcationLevel", subBifurcations.getSubBifurcationLevel());
			namedParameters.put("description", subBifurcations.getDescription());
			namedParameters.put("lastUpdatedBy", subBifurcations.getLastUpdatedBy());
			
			rowCount = namedParameterJdbcTemplate.update(SubBifurcationsMasterQueries.UPDATE_BIFURCATION, namedParameters);
		} catch (DataAccessException e) {
			log.info("Exception occured while updating new GL code...",e);
			throw new InsertEntityGLFailException("Failed to Insert new GL Code" + e.getMessage());
	
		}
		if (rowCount == 1)
			return subBifurcations;
		else
			return null;
	}

}
