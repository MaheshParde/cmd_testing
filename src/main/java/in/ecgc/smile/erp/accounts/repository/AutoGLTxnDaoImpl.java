/**
 * 
 */
package in.ecgc.smile.erp.accounts.repository;

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

import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.exception.InvalidGLTxnDataException;
import in.ecgc.smile.erp.accounts.model.AutoGLTxnRequestModel;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.util.AutoGLTxnQueries;
import in.ecgc.smile.erp.accounts.util.GlTxnSqlQueries;

/**
 * @author CMJ-SENG-LAPTOP9
 *
 */
@Repository
@Transactional
public class AutoGLTxnDaoImpl implements AutoGLTxnDao {
	
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	@Autowired
	public AutoGLTxnDaoImpl(DataSource dataSource) {
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	@Override
	public Integer createTxnRequest(AutoGLTxnRequestModel autoGLTxnRequestModel) throws Exception {
		Integer rowNum;
		try {
		Map<String, Object> paramMap = AutoGLTxnQueries.getParamMapForMapping(autoGLTxnRequestModel);
		rowNum = namedParameterJdbcTemplate.update(AutoGLTxnQueries.ADD_REQUEST, paramMap);
		if(rowNum >0) {
			return rowNum;
		}
		else {
			throw new Exception("Unable to Insert into DB");
		}
			}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public List<AutoGLTxnRequestModel> getAllAutoTxnRequest() {
		try {
			RowMapper<AutoGLTxnRequestModel> rowMapper= AutoGLTxnQueries::mapRow;
			List<AutoGLTxnRequestModel> glTxnReqs =null; 
			glTxnReqs = namedParameterJdbcTemplate.query(AutoGLTxnQueries.GET_ALL,rowMapper);
			return glTxnReqs;
			}
			catch (Exception e) {
				e.printStackTrace();
		
			}
		return null;
	}

	@Override
	public AutoGLTxnRequestModel getAllAutoTxnRequestById(String requestId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", 'V');
		Integer reqId = Integer.parseInt(requestId);
		paramMap.put("reqId",reqId);
		try {
		RowMapper<AutoGLTxnRequestModel> rowMapper= AutoGLTxnQueries::mapRow;
		AutoGLTxnRequestModel glTxnReq =null; 
		glTxnReq = namedParameterJdbcTemplate.queryForObject(AutoGLTxnQueries.GET_REQ_BY_ID,paramMap,rowMapper);
		return glTxnReq;
		}
		catch (Exception e) {
			e.printStackTrace();
	
		}
		return null;
	}

	@Override
	public Integer updateAutoTxnRequest(AutoGLTxnRequestModel autoGLTxnRequestModel) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer updateRequestStatus(String reqId, String status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status",status);
		Integer reqstId = Integer.parseInt(reqId);
		paramMap.put("reqId",reqstId);
		try {
			Integer rowNum = namedParameterJdbcTemplate.update(AutoGLTxnQueries.UPDATE_REQ_STATUS, paramMap);
			if(rowNum >0) {
				return rowNum;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
