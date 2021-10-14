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
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.StampParameterModel;
import in.ecgc.smile.erp.accounts.util.BankBranchSqlQueries;
import in.ecgc.smile.erp.accounts.util.StampSqlQueries;

/**
 * 
 * Stamp Parameter
 * 
 * @author Amitesh Banerjee
 * @version 1.0 27-April-2020
 *
 */

@Repository
@Transactional
public class StampDaoImpl implements StampDao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StampDaoImpl.class);

	  @Autowired
	  private JdbcOperations jdbcOperations; 
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	  private NamedParameterJdbcTemplate namedParameterJdbcOperations; 
	  @Autowired
	  NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	  
	  @Autowired 
	  public StampDaoImpl(DataSource dataSource) { 
		  jdbcTemplate = new JdbcTemplate(dataSource); 
	  namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource); 
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
						 stampParameter.setEffectiveDate(rs.getDate("effective_dt"));
						 stampParameter.setActive(rs.getBoolean("active"));
						 stampParameter.setCreatedBy(rs.getString("created_by"));
						 stampParameter.setCreatedOn(rs.getDate("created_dt"));
						 stampParameter.setUpdatedBy(rs.getString("last_updated_by"));
						 stampParameter.setUpdatedOn(rs.getDate("last_updated_dt"));
						 //stampParameter.setEcgcStatus(rs.getString("ecgc_status").charAt(0));
						 stampParameter.setMetaRemarks(rs.getString("meta_remarks")); return
						 stampParameter;
	    }
	  
	//Add Stamp Parameter  
	@Override
	public Integer addStampParameter(StampParameterModel stampParameter) {
		Map<String, Object> StampnamedParameters = new HashMap<String, Object>();
		
		StampnamedParameters.put("srNo", stampParameter.getSrNo());
		StampnamedParameters.put("fromAmount", stampParameter.getFromAmount());
		StampnamedParameters.put("toAmount", stampParameter.getToAmount());
		StampnamedParameters.put("stampAmount", stampParameter.getStampAmount());
		StampnamedParameters.put("description", stampParameter.getDescription());
		StampnamedParameters.put("effectiveDate", stampParameter.getEffectiveDate());
		/*
		 * if( stampParameter.getActive() == true) StampnamedParameters.put("active",
		 * 'Y'); else StampnamedParameters.put("active", 'N');
		 * StampnamedParameters.put("createdBy", stampParameter.getCreatedBy());
		 * StampnamedParameters.put("createdOn", stampParameter.getCreatedOn());
		 * StampnamedParameters.put("updatedBy", stampParameter.getUpdatedBy());
		 * StampnamedParameters.put("updatedOn", stampParameter.getUpdatedOn());
		 * StampnamedParameters.put("ecgcStatus", 'V');
		 * StampnamedParameters.put("metaRemarks", stampParameter.getMetaRemarks());
		 */
		
		int rowCount = namedParameterJdbcTemplate.update(StampSqlQueries.ADD_STAMP_PARAMETER, StampnamedParameters);
		
		if (rowCount == 1)
			return 1;
		else 
			return -1;
		
	}

	@Override
	public StampParameterModel updateStampParameter(Integer stampCode,StampParameterModel stampParameterUpdate) {
		
		Map<String, Object> StampnamedParameters = new HashMap<String, Object>();
		LOGGER.info("Updated Value"+stampParameterUpdate);
		StampnamedParameters.put("stampCode", stampCode);
		StampnamedParameters.put("fromAmount", stampParameterUpdate.getFromAmount());
		StampnamedParameters.put("toAmount", stampParameterUpdate.getToAmount());
		StampnamedParameters.put("stampAmount", stampParameterUpdate.getStampAmount());
		StampnamedParameters.put("description", stampParameterUpdate.getDescription());
		StampnamedParameters.put("updatedBy", stampParameterUpdate.getUpdatedBy());
		StampnamedParameters.put("updatedOn", stampParameterUpdate.getUpdatedOn());
		StampnamedParameters.put("metaRemarks", stampParameterUpdate.getMetaRemarks());
		
		int rowCount = namedParameterJdbcTemplate.update(StampSqlQueries.UPDATE_STAMP_PARAMETER, StampnamedParameters);
		if (rowCount == 1)
			return stampParameterUpdate;
		else 
			return null;
	}

	@Override
	public List<StampParameterModel> allStampParameter() {
		
		List<StampParameterModel> stampParameter = jdbcOperations.query(StampSqlQueries.VIEW_STAMP_PARAMETER, 
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

		@Override
		public StampParameterModel viewByStampCode(Integer stampCode) {

			StampParameterModel viewCode;
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("stampCode", stampCode);
			
			try {
				viewCode = namedParameterJdbcOperations.queryForObject(StampSqlQueries.
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
								 stampParameter.setEffectiveDate(rs.getDate("effective_dt"));
								 stampParameter.setActive(rs.getBoolean("active"));
								 stampParameter.setCreatedBy(rs.getString("created_by"));
								 stampParameter.setCreatedOn(rs.getDate("created_dt"));
								 stampParameter.setUpdatedBy(rs.getString("last_updated_by"));
								 stampParameter.setUpdatedOn(rs.getDate("last_updated_dt"));
								 //stampParameter.setEcgcStatus(rs.getString("ecgc_status").charAt(0));
								 stampParameter.setMetaRemarks(rs.getString("meta_remarks")); 
								 return stampParameter;
							}
						});
			} catch (EmptyResultDataAccessException e) {
				viewCode = null;
			}
			return viewCode;
		}


		@Override
		//public Integer getStampAmtByFromAndToAmt(Integer fromAmount, Integer toAmount) {

		public Integer getStampAmtByFromAndToAmt(Integer receiptAmount) {
			Map<String, Object> paramMap = new HashMap<>();
			//paramMap.put("fromAmount", fromAmount);
			//paramMap.put("toAmount", toAmount);
			paramMap.put("receiptAmount", receiptAmount);
			
			String query = "select stmp_amt from ecgc_acct_stamp_parameter_mst where "+ receiptAmount+" between from_amt and to_amt" ;
					
			Integer stampAmont =0;
			try {
				
				stampAmont= namedParameterJdbcOperations.queryForObject(query,
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
