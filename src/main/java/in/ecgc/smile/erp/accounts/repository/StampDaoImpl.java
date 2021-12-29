package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.exception.DatabaseOperationFailException;
import in.ecgc.smile.erp.accounts.exception.RecordNotFoundException;
import in.ecgc.smile.erp.accounts.model.StampParameterModel;
import in.ecgc.smile.erp.accounts.util.StampSqlQueries;
import in.ecgc.smile.erp.sys.auth.be.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Stamp Parameter
 * 
 * @author Amitesh Banerjee
 * @version 1.0 27-April-2020
 *
 */
@Slf4j
@Repository
@Transactional
public class StampDaoImpl implements StampDao{
	
	  @Autowired
	  NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	  
	  @Autowired
	  UserInfoService userInfoService;
	  
	  @Autowired 
	  public StampDaoImpl(DataSource dataSource) { 
	  namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);  
	  }
	 
	
	  public StampParameterModel setValueToObject(ResultSet rs,StampParameterModel stampParameter) 
	    		throws SQLException
	    {
						 stampParameter.setSrNo(rs.getInt("sr_no"));
						 stampParameter.setFromAmount(rs.getDouble("from_amt"));
						 stampParameter.setToAmount(rs.getDouble("to_amt"));
						 stampParameter.setStampAmount(rs.getDouble("stmp_amt"));
						 stampParameter.setDescription(rs.getString("description"));
						 if(rs.getDate("effective_dt") != null)
							 stampParameter.setEffectiveDate(rs.getDate("effective_dt").toLocalDate());
						 stampParameter.setActive(rs.getBoolean("active"));
						 stampParameter.setCreatedBy(rs.getString("created_by"));
						 if(rs.getDate("created_dt")!=null)
							 stampParameter.setCreatedOn(rs.getTimestamp("created_dt").toLocalDateTime());
						 else
							 stampParameter.setCreatedOn(null);
						 stampParameter.setUpdatedBy(rs.getString("last_updated_by"));
						 if(rs.getDate("last_updated_dt")!=null)
							 stampParameter.setUpdatedOn(rs.getTimestamp("last_updated_dt").toLocalDateTime());
						 else
							 stampParameter.setUpdatedOn(null);
						 //stampParameter.setEcgcStatus(rs.getString("ecgc_status").charAt(0));
						 stampParameter.setMetaRemarks(rs.getString("meta_remarks")); 
						 return stampParameter;
	    }
	  
	//Add Stamp Parameter  
	@Override
	public Integer addStampParameter(StampParameterModel stampParameter) {
		log.info("INSIDE STAMP PARAMETER ADD DAO");
		int rowCount = 0; 
		try
		{
		Map<String, Object> StampnamedParameters = new HashMap<String, Object>();
//		StampnamedParameters.put("srNo", stampParameter.getSrNo());
		StampnamedParameters.put("fromAmount", stampParameter.getFromAmount());
		StampnamedParameters.put("toAmount", stampParameter.getToAmount());
		StampnamedParameters.put("stampAmount", stampParameter.getStampAmount());
		StampnamedParameters.put("description", stampParameter.getDescription());
		StampnamedParameters.put("effectiveDate", stampParameter.getEffectiveDate());
		StampnamedParameters.put("createdBy", userInfoService.getUser());
		StampnamedParameters.put("createdOn", LocalDateTime.now());
		
//		StampnamedParameters.put("updatedBy", stampParameter.getUpdatedBy());
//		StampnamedParameters.put("updatedOn", stampParameter.getUpdatedOn());
//		StampnamedParameters.put("ecgcStatus", 'V');
//		StampnamedParameters.put("metaRemarks", stampParameter.getMetaRemarks());
//		if( stampParameter.getActive() == true) 
//		StampnamedParameters.put("active",'Y'); 
//	    else 
//		StampnamedParameters.put("active", 'N');
		 		
		rowCount = namedParameterJdbcTemplate.update(StampSqlQueries.ADD_STAMP_PARAMETER, StampnamedParameters);
		}
		catch (DataIntegrityViolationException e) {
			log.error("DataIntegrityViolationException Exception in StampDaoImpl#addStampParameter",e.fillInStackTrace());
			throw new DatabaseOperationFailException("From amt , to amt already exist in Database");
		}
		catch (Exception e) {
			log.error("Exception in StampDaoImpl#addStampParameter",e.fillInStackTrace());
			throw new DatabaseOperationFailException("Failed to perform Datbase Operation" + e.getMessage());
		}
		return rowCount;	
	}

	@Override
	public StampParameterModel updateStampParameter(Integer stampCode,StampParameterModel stampParameterUpdate) {
		log.info("INSIDE STAMP PARAMETER UPDATE DAO");
		int rowCount = 0;
		try
		{
		Map<String, Object> StampnamedParameters = new HashMap<String, Object>();
		log.info("Updated Value"+stampParameterUpdate);
		StampnamedParameters.put("stampCode", stampCode);
		StampnamedParameters.put("fromAmount", stampParameterUpdate.getFromAmount());
		StampnamedParameters.put("toAmount", stampParameterUpdate.getToAmount());
		StampnamedParameters.put("stampAmount", stampParameterUpdate.getStampAmount());
		StampnamedParameters.put("description", stampParameterUpdate.getDescription());
		StampnamedParameters.put("effectiveDate", stampParameterUpdate.getEffectiveDate());
		StampnamedParameters.put("updatedBy", userInfoService.getUser());
		StampnamedParameters.put("updatedOn", LocalDateTime.now());
		StampnamedParameters.put("metaRemarks", stampParameterUpdate.getMetaRemarks());
//		StampnamedParameters.put("active",'Y'); 
		if( stampParameterUpdate.getActive() == true) 
		StampnamedParameters.put("active",'Y'); 
	    else 
		StampnamedParameters.put("active", 'N');
		rowCount = namedParameterJdbcTemplate.update(StampSqlQueries.UPDATE_STAMP_PARAMETER, StampnamedParameters);
		if (rowCount == 1)
			return stampParameterUpdate;
		else 
			return null;
		}
		catch (DataIntegrityViolationException e) {
			log.error("DataIntegrityViolationException Exception in StampDaoImpl#addStampParameter",e.fillInStackTrace());
			throw new DatabaseOperationFailException("FROM AMT , TO AMT already exist in Database");
		}
		catch (Exception e) {
			log.error("Exception in StampDaoImpl#UpdateStampParameter",e.fillInStackTrace());
			throw new DatabaseOperationFailException("Failed to perform Datbase Operation" + e.getMessage());			
		}
	}

	@Override
	public List<StampParameterModel> allStampParameter() {		
		log.info("INSIDE GET ALL STAMP PARAMETER DAO");
		try
		{ 
		List<StampParameterModel> stampParameter = namedParameterJdbcTemplate.query(StampSqlQueries.VIEW_STAMP_PARAMETER, 
				new ResultSetExtractor<List<StampParameterModel>>() {

					@Override
					public List<StampParameterModel> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<StampParameterModel> listofStamp = new ArrayList<StampParameterModel>();
						while(rs.next())
						{
							StampParameterModel stampCode = new StampParameterModel();
							listofStamp.add(setValueToObject(rs, stampCode));
						}
					    return listofStamp;
					}
				});
		return stampParameter;
		}
		catch (Exception e) {
			log.error("Exception in StampDaoImpl#ViewAllStampParameter",e.fillInStackTrace());
			throw new DatabaseOperationFailException("Failed to perform Datbase Operation" + e.getMessage());
		}
	}

		@Override
		public StampParameterModel viewByStampCode(Integer stampCode) {
			log.info("INSIDE GET STAMP PARAMETER BY STAMPCODE DAO");
			StampParameterModel viewCode =null;
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("stampCode", stampCode);
			
			try {
				viewCode = namedParameterJdbcTemplate.queryForObject(StampSqlQueries.
						VIEW_STAMP_PARAMETER_BY_ID, paramMap,
						new RowMapper<StampParameterModel>() {

							@Override
							public StampParameterModel mapRow(ResultSet rs, int rowNum) throws SQLException {
								StampParameterModel stampParameter = new StampParameterModel();
								stampParameter.setSrNo(rs.getInt("sr_no"));
								 stampParameter.setFromAmount(rs.getDouble("from_amt"));
								 stampParameter.setToAmount(rs.getDouble("to_amt"));
								 stampParameter.setStampAmount(rs.getDouble("stmp_amt"));
								 stampParameter.setDescription(rs.getString("description"));
								 if(rs.getDate("effective_dt") != null)
									 stampParameter.setEffectiveDate(rs.getDate("effective_dt").toLocalDate());
								 stampParameter.setActive(rs.getBoolean("active"));
								 stampParameter.setCreatedBy(rs.getString("created_by"));
								 if(rs.getDate("created_dt")!=null)
									 stampParameter.setCreatedOn(rs.getTimestamp("created_dt").toLocalDateTime());
								 else
									 stampParameter.setCreatedOn(null);
								 stampParameter.setUpdatedBy(rs.getString("last_updated_by"));
								 if(rs.getDate("last_updated_dt")!=null)
									 stampParameter.setUpdatedOn(rs.getTimestamp("last_updated_dt").toLocalDateTime());
								 else
									 stampParameter.setUpdatedOn(null);
								 //stampParameter.setEcgcStatus(rs.getString("ecgc_status").charAt(0));
								 stampParameter.setMetaRemarks(rs.getString("meta_remarks")); 
								 return stampParameter;
							}
						});
			} catch (EmptyResultDataAccessException e) {
				throw new RecordNotFoundException("No Stamp parameters found with the given stamp code");
			}
			return viewCode;
		}


		@Override
		//public Integer getStampAmtByFromAndToAmt(Integer fromAmount, Integer toAmount) {
		public Integer getStampAmtByFromAndToAmt(Integer receiptAmount) {
			log.info("INSIDE GET STAMP AMOUNT BY RECEIPT AMOUNT DAO");
			Map<String, Object> paramMap = new HashMap<>();
			//paramMap.put("fromAmount", fromAmount);
			//paramMap.put("toAmount", toAmount);
			paramMap.put("receiptAmount", receiptAmount);
			
			String query = "select stmp_amt from ecgc_acct_stamp_parameter_mst where "+ receiptAmount+" between from_amt and to_amt" ;
					
			Integer stampAmont =0;
			try {
				
				stampAmont= namedParameterJdbcTemplate.queryForObject(query,
						paramMap,new RowMapper<Integer>() {

					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
						int stampAmount=0;
						stampAmount= rs.getInt("stmp_amt");
						return stampAmount ;
						
					}
				});
			} catch (EmptyResultDataAccessException e) {
				stampAmont = 0;
			}
			return stampAmont;	

		}
	
}
