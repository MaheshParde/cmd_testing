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

import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.util.LOVQueries;
import in.ecgc.smile.erp.sys.auth.be.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class LOVMasterDaoImpl  implements LOVMasterDao{
	

	@Autowired 
	private UserInfoService userInfo;
	
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

	@Override
	public Integer addLovMstEntry(LOVMaster lovmst) {
		int result = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lovCd", lovmst.getLovCd());
		paramMap.put("lovSubCd", lovmst.getLovSubCd());
		paramMap.put("lovValue", lovmst.getLovValue());
		paramMap.put("lovDesc", lovmst.getLovDesc());
		paramMap.put("createdBy", userInfo.getUser());
		paramMap.put("delFlag", lovmst.getDelFlag());
		result = namedParameterJdbcTemplate.update(LOVQueries.INSERT_LOV_MST_ENTRY_DETAILS, paramMap);
		return result;
	}

	@Override
	public List<LOVMaster> viewallLOVMstEntries() {
		List<LOVMaster> lovMaster;
		lovMaster = jdbcOperations.query(LOVQueries.GET_ALL_LOV_MST_ENTRIES, new ResultSetExtractor<List<LOVMaster>>() {
			@Override
			public List<LOVMaster> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<LOVMaster> lovMaster = new ArrayList<LOVMaster>();
				while (rs.next()) {
					LOVMaster lovData = new LOVMaster();
					lovData.setLovCd(rs.getString("lov_cd"));
					lovData.setLovSubCd(rs.getString("lov_sub_cd"));
					lovData.setLovValue(rs.getString("lov_value"));
					lovData.setLovDesc(rs.getString("lov_desc"));
					lovData.setDelFlag(rs.getBoolean("del_flag"));
					lovMaster.add(lovData);
				}
				return lovMaster;
			}
		});
		return lovMaster;
	}

	@Override
	public LOVMaster viewLovEntry(String lovCd, String lovSubCd, String lovValue) {

		LOVMaster lovMaster;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lovCd", lovCd);
		paramMap.put("lovSubCd", lovSubCd);
		paramMap.put("lovValue", lovValue);
		
		try {
			lovMaster = namedParameterJdbcOperations.queryForObject(LOVQueries.GET_LOV_MST_ENTRY, paramMap,
					new RowMapper<LOVMaster>() {

						@Override
						public LOVMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
							LOVMaster lovMaster = new LOVMaster();

							lovMaster.setLovCd(rs.getString("lov_cd"));
							lovMaster.setLovSubCd(rs.getString("lov_sub_cd"));
							lovMaster.setLovValue(rs.getString("lov_value"));
							lovMaster.setLovDesc(rs.getString("lov_desc"));		
							lovMaster.setDelFlag(rs.getBoolean("del_flag"));
							return lovMaster;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			lovMaster = null;
		}
		return lovMaster;
	}

	@Override
	public int modifyLovEntry(LOVMaster lov) {
		int modifyQueryStatus = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lovCd", lov.getLovCd());
		paramMap.put("lovSubCd", lov.getLovSubCd());
		paramMap.put("lovValue", lov.getLovValue());
		paramMap.put("lovDesc", lov.getLovDesc());
		paramMap.put("delFlag", lov.getDelFlag());
		paramMap.put("lastUpdatedBy",userInfo.getUser());
		modifyQueryStatus = namedParameterJdbcTemplate.update(LOVQueries.UPDATE_LOV_MST_ENTRY, paramMap);
		return modifyQueryStatus;
	}

	@Override
	public Integer disableLovEntry(String lovCd, String lovSubCd, String lovValue) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lovCd", lovCd);
		paramMap.put("lovSubCd", lovSubCd);
		paramMap.put("lovValue", lovValue);
		paramMap.put("lastUpdatedBy",userInfo.getUser());
		
		return namedParameterJdbcTemplate.update(LOVQueries.DISABLE_LOV_ENTRY, paramMap);
	}

	@Override
	public Integer setPaymentAdviceNo(Integer refNo,Integer paymentAdviceNo) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("ref_no", refNo);
		param.put("payment_advice_no", paymentAdviceNo);
		return namedParameterJdbcTemplate.update(LOVQueries.SET_ADVICE_NO, param);
	}
	
	@Override
	public Map<String,String> getT1codes() {

		Map<String,String> t1Codes = new HashMap<>();

		try {
			t1Codes = namedParameterJdbcOperations.query(LOVQueries.GET_T1_CODE_LIST,
					(ResultSet rs)->{
						HashMap<String, String> t1 = new HashMap<>();
						
						  while(rs.next()){
							  t1.put(rs.getString("lov_desc"),rs.getString("lov_value"));
					        }
						return t1;
					});
			log.info("T1 code list is : {}" ,t1Codes);
				return t1Codes;
		} catch (DataAccessException e) {
			log.info("T1 code list is empty : {}" ,e.getMessage());
		}
		return null;	
				
	}

	@Override
	public Map<String,String> getT2codes() {

		Map<String,String> t2Codes = new HashMap<>();

		try {
			t2Codes = namedParameterJdbcOperations.query(LOVQueries.GET_T2_CODE_LIST,
					(ResultSet rs)->{
						HashMap<String, String> t2 = new HashMap<>();
						
						  while(rs.next()){
							  t2.put(rs.getString("lov_desc"),rs.getString("lov_value"));
					        }
						return t2;
					});
			log.info("T2 code list is : {}" ,t2Codes);
				return t2Codes;
		} catch (DataAccessException e) {
			log.info("T1 code list is empty : {}" ,e.getMessage());
		}	
		return null;
	}


	@Override
	public Integer getRefNo() {
		Integer refNo;
		Map<String,String> param = new HashMap<>();
		
		try {
			refNo = namedParameterJdbcTemplate.queryForObject(LOVQueries.GET_REF_NO,param,
					new RowMapper<Integer>() {

						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getInt(1);
						}
					});
		} catch (EmptyResultDataAccessException e) {
			refNo = null;
		}
		
		return refNo;
	}

	

}
 