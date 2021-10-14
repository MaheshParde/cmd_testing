package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.SubBifurcations;
import in.ecgc.smile.erp.accounts.util.SubBifurcationsMasterQueries;

@Repository
@Transactional
public class SubBifurcationMasterDaoImpl implements SubBifurcationMasterDao {

	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	@Autowired
	public SubBifurcationMasterDaoImpl(DataSource dataSource) {
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<SubBifurcations> getSubBifurcations() throws Exception {
		try {
			List<SubBifurcations> list;
			RowMapper<SubBifurcations> rowMapper= SubBifurcationsMasterQueries::mapRow;
			list =namedParameterJdbcTemplate.query(SubBifurcationsMasterQueries.GET_ALL_BIFURCATION,rowMapper);
			System.err.println("inside dao "+ list);
			return list;
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
	}

	@Override
	public Integer addSubBifurcation(SubBifurcations subBifurcations) {
		Integer rowNum;
		try {
		Map<String, Object> paramMapForHdr = 	SubBifurcationsMasterQueries.getParamMap(subBifurcations);
		rowNum = namedParameterJdbcTemplate.update(SubBifurcationsMasterQueries.ADD_BIFURCATION, paramMapForHdr);
			return rowNum;		
			}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Integer updatedSubBifurcations(SubBifurcations subBifurcations) {
		Integer rowNum;
		try {
		Map<String, Object> paramMapForHdr = 	SubBifurcationsMasterQueries.getParamMap(subBifurcations);
		rowNum = namedParameterJdbcTemplate.update(SubBifurcationsMasterQueries.UPDATE_BIFURCATION, paramMapForHdr);
			return rowNum;		
			}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<SubBifurcations> getSubBifurcationsByLevel(String subBifurcationLevel) throws Exception {
		try {
			List<SubBifurcations> list;
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("level",subBifurcationLevel);
			RowMapper<SubBifurcations> rowMapper= SubBifurcationsMasterQueries::mapRow;
			list =namedParameterJdbcTemplate.query(SubBifurcationsMasterQueries.GET_BIFURCATION_BY_LEVEL,paramMap,rowMapper);
			System.err.println("inside dao "+ list);
			return list;
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
	}

	@Override
	public List<String> getSubBifurcationLevels() throws Exception {
		try {
			List<String> list;
			list =namedParameterJdbcTemplate.query(SubBifurcationsMasterQueries.GET_BIFURCATION__LEVELS,new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					return rs.getString("sub_bifurcation_level");
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
