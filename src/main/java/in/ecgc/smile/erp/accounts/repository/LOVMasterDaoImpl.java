package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.util.BankBranchSqlQueries;
import in.ecgc.smile.erp.accounts.util.LOVQueries;


@Repository
public class LOVMasterDaoImpl  implements LOVMasterDao{
	

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	
	@Autowired
	public LOVMasterDaoImpl(DataSource dataSource) {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
//*******************************************************************************************************
	@Override
	public Integer addLOVMstEntry(LOVMaster lovmst) {
		int insertQueryStatus = 0;
		Timestamp ts = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("lov_cd",lovmst.getLov_cd());
		paramMap.put("lov_sub_cd",lovmst.getLov_sub_cd());
		paramMap.put("lov_value",lovmst.getLov_value());
		paramMap.put("lov_desc",lovmst.getLov_desc());	
		paramMap.put("created_dt",ts);
		insertQueryStatus= namedParameterJdbcTemplate.update(LOVQueries.INSERT_LOV_MST_ENTRY_DETAILS,paramMap);
		return insertQueryStatus;
		
	}

	@Override
	public List<LOVMaster> viewallLOVMstEntries() {
		List<LOVMaster> lovMaster;
		lovMaster = jdbcOperations.query(LOVQueries.GET_ALL_LOV_MST_ENTRIES, new ResultSetExtractor<List<LOVMaster>>(){
		@Override
		public List<LOVMaster> extractData(ResultSet rs) throws SQLException, DataAccessException  {
			List<LOVMaster> lovMaster = new ArrayList<LOVMaster>(); 
				while (rs.next()) {
					LOVMaster lovData = new LOVMaster();
					lovData.setLov_cd(rs.getString("lov_cd"));
					lovData.setLov_sub_cd(rs.getString("lov_sub_cd"));
					lovData.setLov_value(rs.getString("lov_value"));
					lovData.setLov_desc(rs.getString("lov_desc"));
					lovMaster.add(lovData);
				}
				return lovMaster;
			}
		});
		return lovMaster;
	}

	@Override
	public List<LOVMaster> getLovByLovcd(String lovcd) {
		    SqlParameterSource parameters = new MapSqlParameterSource("ids",lovcd);
		 
		    List<LOVMaster> lovlist = namedParameterJdbcTemplate.query(
		      "SELECT * FROM accounts.ecgc_acct_lov_mst WHERE  lov_cd IN (:ids)", 
		      parameters, 
		      (rs, rowNum) -> new LOVMaster(rs.getString("lov_cd"),rs.getString("lov_sub_cd"),rs.getString("lov_value"),rs.getString("lov_desc"),rs.getTimestamp("created_dt"),rs.getString("created_by"),rs.getTimestamp("last_updated_dt"),rs.getString("last_updated_by")));
		 
		    return lovlist;
		}
	
	@Override
	public List<Map<String,String>> getT1codes() {

		List<Map<String,String>> t1Codes = new ArrayList<Map<String,String>>();

		t1Codes = jdbcOperations.query(LOVQueries.GET_T1_CODE_LIST, new RowMapper<Map<String, String>>() {

			@Override
			public Map<String,String> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, String> t1 = new HashMap<String, String>();
				
				t1.put(rs.getString("lov_desc"), rs.getString("lov_value"));
				
				return t1;
			}
		});
		
		return t1Codes;
	}

	@Override
	public List<Map<String,String>> getT2codes() {

		List<Map<String,String>> t2Codes = new ArrayList<Map<String,String>>();

		t2Codes = jdbcOperations.query(LOVQueries.GET_T2_CODE_LIST, new RowMapper<Map<String, String>>() {

			@Override
			public Map<String,String> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, String> t2 = new HashMap<String, String>();
				
				t2.put(rs.getString("lov_desc"), rs.getString("lov_value"));
				
				return t2;
			}
		});
		return t2Codes;
	}

	@Override
	public LOVMaster findLov(String lov_cd,  String lov_value, String lov_sub_cd) {
		LOVMaster lovMaster = new  LOVMaster();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lov_cd", lov_cd);
		paramMap.put("lov_value", lov_value);
		paramMap.put(lov_sub_cd, lov_sub_cd);
		

		try {
			lovMaster = namedParameterJdbcOperations.queryForObject(LOVQueries.FIND_LOV,
					paramMap,new RowMapper<LOVMaster>() {

				@Override
				public LOVMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
	
					LOVMaster lovMaster = new LOVMaster();				
					lovMaster.setLov_value(rs.getString("lov_value"));
					lovMaster.setLov_sub_cd(rs.getString("lov_sub_cd"));
					lovMaster.setLov_cd(rs.getString("lov_cd"));
					lovMaster.setLov_desc(rs.getString("lov_desc"));
					lovMaster.setLov_value(rs.getString("lov_value"));
					lovMaster.setCreated_by(rs.getString("created_by"));
					lovMaster.setCreated_dt(rs.getTimestamp("created_dt"));
					lovMaster.setLast_updated_by(rs.getString("last_updated_by"));
					lovMaster.setLast_updated_dt(rs.getTimestamp("last_updated_dt"));
					
					return lovMaster;
					
				}
			});
		} catch (EmptyResultDataAccessException e) {
			lovMaster = null;
		}
		return lovMaster;	
	}

}
 