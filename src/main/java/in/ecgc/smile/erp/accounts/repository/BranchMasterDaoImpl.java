package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.model.BranchMaster;
import in.ecgc.smile.erp.accounts.util.BankBranchSqlQueries;
import in.ecgc.smile.erp.accounts.util.BranchMasterSqlQueries;

@Repository
@Transactional
public class BranchMasterDaoImpl implements BranchMasterDao {

	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(BranchMasterDaoImpl.class);


	@Autowired
	public BranchMasterDaoImpl(DataSource dataSource) {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public Integer addBranch(BranchMaster branch) {

		Map<String, Object> BranchNamedParameters = new HashMap<String, Object>();
		 
		BranchNamedParameters.put("logicalLocCode", branch.getLogicalLocCode());	
		BranchNamedParameters.put("branchCode", branch.getBranchCode());
		BranchNamedParameters.put("branchName", branch.getBranchName());
		BranchNamedParameters.put("bankName", branch.getBankName());
		BranchNamedParameters.put("bankBranchAddress", branch.getBankBranchAddress());
		BranchNamedParameters.put("gstinNumber", branch.getGstinNumber());
		BranchNamedParameters.put("gstStateUt", branch.getGstStateUt());
		BranchNamedParameters.put("gstStateUtCode", branch.getGstStateUtCode());
		BranchNamedParameters.put("expenseAccountNumber", branch.getExpenseAccountNumber());
		BranchNamedParameters.put("expenseAccountIfscCode",branch.getExpenseAccountIfscCode());
		BranchNamedParameters.put("expenseAccountsNeftCode", branch.getExpenseAccountsNeftCode());
		BranchNamedParameters.put("collectionAccountNumber", branch.getCollectionAccountNumber());
		BranchNamedParameters.put("collectionAccountIfscCode", branch.getCollectionAccountIfscCode());
		BranchNamedParameters.put("collectionAccountNeftCode", branch.getCollectionAccountNeftCode());
		BranchNamedParameters.put("clientId" , branch.getClientId());
		BranchNamedParameters.put("virtualId", branch.getVirtualId());
		BranchNamedParameters.put("active" , branch.getActive());
		BranchNamedParameters.put("createdBy", branch.getCreatedBy());
		BranchNamedParameters.put("lastUpdatedBy", branch.getLastUpdatedBy());
		BranchNamedParameters.put("metaStatus", branch.getMetaStatus());
		BranchNamedParameters.put("metaRemarks", branch.getMetaRemarks());
		return namedParameterJdbcTemplate.update(BranchMasterSqlQueries.ADD_BRANCH,  BranchNamedParameters);

	}

	@Override
	public List<BranchMaster> listAllBranches() {


		List<BranchMaster> branches = new ArrayList<BranchMaster>();
		branches = namedParameterJdbcTemplate.query(BranchMasterSqlQueries.VIEW_ALL,new RowMapper<BranchMaster>()
		{
			@Override
			public BranchMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
				BranchMaster branch = new BranchMaster();

				
				branch.setLogicalLocCode(rs.getString("logical_loc_cd"));
				branch.setBranchCode(rs.getString("branch_cd"));
				branch.setBranchName(rs.getString("branch_name"));
				branch.setBankName(rs.getString("bank_name"));
				branch.setBankBranchAddress(rs.getString("bank_branch_address"));
				branch.setGstinNumber(rs.getString("gstin_no"));
				branch.setGstStateUt(rs.getString("gststateut"));
				branch.setGstStateUtCode(rs.getString("gststateutcode"));
				branch.setExpenseAccountNumber(rs.getString("expense_acct_number"));
				branch.setExpenseAccountIfscCode(rs.getString("expense_acct_ifsc_cd"));
				branch.setExpenseAccountsNeftCode(rs.getString("expense_acct_neft_cd"));
				branch.setCollectionAccountNumber(rs.getString("collection_acct_number"));
				branch.setCollectionAccountIfscCode(rs.getString("collection_acct_ifsc_cd"));
				branch.setCollectionAccountNeftCode(rs.getString("collection_acct_neft_cd"));
				branch.setCreatedBy(rs.getString("created_by"));
				branch.setMetaStatus(rs.getString("meta_status"));
				branch.setActive(rs.getBoolean("active"));
				branch.setClientId(rs.getString("client_id"));
				branch.setVirtualId(rs.getString("virtual_id"));
				branch.setCreatedDate(rs.getDate("created_dt")); 
				branch.setLastUpdatedBy(rs.getString("last_updated_by"));
				branch.setLastUpdatedDate(rs.getDate("last_updated_dt"));
				branch.setMetaRemarks(rs.getString("meta_remarks"));
								
				return branch;
				}		
		});		
		return branches;
		}

	@Override
	public Integer updateBranch(String logicalLocCode, String branchCode, BranchMaster updateBranch) {

		int rowCount;
		try {
			Map<String, Object> BranchNamedParameters = new HashMap<String, Object>();
			 
			BranchNamedParameters.put("logicalLocCode", updateBranch.getLogicalLocCode());	
			BranchNamedParameters.put("branchCode", updateBranch.getBranchCode());
			BranchNamedParameters.put("branchName", updateBranch.getBranchName());
			BranchNamedParameters.put("bankName", updateBranch.getBankName());
			BranchNamedParameters.put("bankBranchAddress", updateBranch.getBankBranchAddress());
			BranchNamedParameters.put("gstinNumber", updateBranch.getGstinNumber());
			BranchNamedParameters.put("gstStateUt", updateBranch.getGstStateUt());
			BranchNamedParameters.put("gstStateUtCode", updateBranch.getGstStateUtCode());
			BranchNamedParameters.put("expenseAccountNumber", updateBranch.getExpenseAccountNumber());
			BranchNamedParameters.put("expenseAccountIfscCode",updateBranch.getExpenseAccountIfscCode());
			BranchNamedParameters.put("expenseAccountsNeftCode", updateBranch.getExpenseAccountsNeftCode());
			BranchNamedParameters.put("collectionAccountNumber", updateBranch.getCollectionAccountNumber());
			BranchNamedParameters.put("collectionAccountIfscCode", updateBranch.getCollectionAccountIfscCode());
			BranchNamedParameters.put("collectionAccountNeftCode", updateBranch.getCollectionAccountNeftCode());
			BranchNamedParameters.put("clientId" , updateBranch.getClientId());
			BranchNamedParameters.put("virtualId", updateBranch.getVirtualId());
			BranchNamedParameters.put("active" , updateBranch.getActive());
			BranchNamedParameters.put("createdBy", updateBranch.getCreatedBy());
			BranchNamedParameters.put("lastUpdatedBy", updateBranch.getLastUpdatedBy());
			BranchNamedParameters.put("metaStatus", updateBranch.getMetaStatus());
			BranchNamedParameters.put("metaRemarks", updateBranch.getMetaRemarks());
			
			rowCount = namedParameterJdbcTemplate.update(BranchMasterSqlQueries.UPDATE_BRANCH_DETAILS,BranchNamedParameters);
			
		} catch (DataAccessException e) {
			LOGGER.info("Exception occured while updating Branch Details...",e);
			return null;
		}
		return rowCount;
		}

	@Override
	public Integer disableBranch(String logicalLocCode, String branchCode) {
		int rowCount;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("logicalLocCode", logicalLocCode);
		paramMap.put("branchCode", branchCode);
		rowCount = namedParameterJdbcTemplate.update(BranchMasterSqlQueries.DISABLE_BRANCH,new MapSqlParameterSource(paramMap));
		return rowCount;
	}

	@Override
	public BranchMaster findBranchByLogicalLocationAndBankCode(String logicalLocCode, String BranchCode) {
		BranchMaster branchMasterList = new  BranchMaster();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("logicalLocCode", logicalLocCode);
		paramMap.put("branchCode", BranchCode);

		try {
			branchMasterList = namedParameterJdbcOperations.queryForObject(BranchMasterSqlQueries.VIEW_BY_LOGICAL_LOC_AND_BRANCH_CODE,
					paramMap,new RowMapper<BranchMaster>() {

				@Override
				public BranchMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
	
					BranchMaster branch = new BranchMaster();				
					branch.setLogicalLocCode(rs.getString("logical_loc_cd"));
					branch.setBranchCode(rs.getString("branch_cd"));
					branch.setBranchName(rs.getString("branch_name"));
					branch.setBankName(rs.getString("bank_name"));
					branch.setBankBranchAddress(rs.getString("bank_branch_address"));
					branch.setGstinNumber(rs.getString("gstin_no"));
					branch.setGstStateUt(rs.getString("gststateut"));
					branch.setGstStateUtCode(rs.getString("gststateutcode"));
					branch.setExpenseAccountNumber(rs.getString("expense_acct_number"));
					branch.setExpenseAccountIfscCode(rs.getString("expense_acct_ifsc_cd"));
					branch.setExpenseAccountsNeftCode(rs.getString("expense_acct_neft_cd"));
					branch.setCollectionAccountNumber(rs.getString("collection_acct_number"));
					branch.setCollectionAccountIfscCode(rs.getString("collection_acct_ifsc_cd"));
					branch.setCollectionAccountNeftCode(rs.getString("collection_acct_neft_cd"));
					branch.setCreatedBy(rs.getString("created_by"));
					branch.setMetaStatus(rs.getString("meta_status"));
					branch.setActive(rs.getBoolean("active"));
					branch.setClientId(rs.getString("client_id"));
					branch.setVirtualId(rs.getString("virtual_id"));
					branch.setCreatedDate(rs.getDate("created_dt")); 
					branch.setLastUpdatedBy(rs.getString("last_updated_by"));
					branch.setLastUpdatedDate(rs.getDate("last_updated_dt"));
					branch.setMetaRemarks(rs.getString("meta_remarks"));
					
					return branch;
					
				}
			});
		} catch (EmptyResultDataAccessException e) {
			branchMasterList = null;
		}
		return branchMasterList;	

	}

}
