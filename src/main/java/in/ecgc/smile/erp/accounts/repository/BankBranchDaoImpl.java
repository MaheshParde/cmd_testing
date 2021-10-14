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
import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.util.BankBranchSqlQueries;

@Repository
@Transactional
public class BankBranchDaoImpl implements BankBranchDao {

	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(BankBranchDaoImpl.class);

	@Autowired
	public BankBranchDaoImpl(DataSource dataSource) {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	@Override
	public Integer disableBankBranch(String logicalLocCode,String bankName )
	{
		int rowCount;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("logicalLocCode", logicalLocCode);
		paramMap.put("bankName", bankName);
		rowCount = namedParameterJdbcTemplate.update(BankBranchSqlQueries.DISABLE_BANK_BRANCH,new MapSqlParameterSource(paramMap));
		return rowCount;
	}

	
	@Override
	public Integer addBankBranch(BankBranch bankBranch)
	{
		Map<String, Object> bankBranchNamedParameters = new HashMap<String, Object>();
		bankBranch.setEcgcStatus("V");

		bankBranchNamedParameters.put("logicalLocCode", bankBranch.getLogicalLocCode());		
		bankBranchNamedParameters.put("bankName", bankBranch.getBankName());
		bankBranchNamedParameters.put("bankBranchName", bankBranch.getBankBranchName());		
		bankBranchNamedParameters.put("expenseAccountNumber", bankBranch.getExpenseAccountNumber());
		bankBranchNamedParameters.put("expenseAccountIfscCode",bankBranch.getExpenseAccountIfscCode());
		bankBranchNamedParameters.put("collectionAccountNumber", bankBranch.getCollectionAccountNumber());
		bankBranchNamedParameters.put("collectionAccountIfscCode", bankBranch.getCollectionAccountIfscCode());
		bankBranchNamedParameters.put("clientId" , bankBranch.getClientId());
		bankBranchNamedParameters.put("virtualId", bankBranch.getVirtualId());
		bankBranchNamedParameters.put("active" , bankBranch.getActive());
		bankBranchNamedParameters.put("createdBy", bankBranch.getCreatedBy());
		bankBranchNamedParameters.put("lastUpdatedBy", bankBranch.getLastUpdatedBy());
		bankBranchNamedParameters.put("ecgcStatus", bankBranch.getEcgcStatus());
		bankBranchNamedParameters.put("metaRemarks", bankBranch.getMetaRemarks());
		bankBranchNamedParameters.put("gstin", bankBranch.getGstin());
		return namedParameterJdbcTemplate.update(BankBranchSqlQueries.ADD_BANK_BRANCH, bankBranchNamedParameters);
		
	}

	@Override
	public List<BankBranch> listAllBankBranches() throws DataAccessException
	{
		List<BankBranch> bankBranches = new ArrayList<BankBranch>();
		bankBranches = namedParameterJdbcTemplate.query(BankBranchSqlQueries.ALL_BANK_BRANCHES,new RowMapper<BankBranch>()
		{
			@Override
			public BankBranch mapRow(ResultSet rs, int rowNum) throws SQLException {
				BankBranch bankBranch = new BankBranch();
				
				bankBranch.setLogicalLocCode(rs.getString("logical_loc_cd"));
				bankBranch.setBankBranchName(rs.getString("bank_branch_name"));
				bankBranch.setBankName(rs.getString("bank_name" ));
				bankBranch.setExpenseAccountNumber(rs.getString("expense_acct_number"));
				bankBranch.setExpenseAccountIfscCode(rs.getString("expense_acct_ifsc_cd"));
				bankBranch.setCollectionAccountNumber(rs.getString("collection_acct_number"));
				bankBranch.setCollectionAccountIfscCode(rs.getString("collection_acct_ifsc_cd"));
				bankBranch.setCreatedBy(rs.getString("created_by"));
				bankBranch.setEcgcStatus(rs.getString("ecgc_status"));
				bankBranch.setActive(rs.getBoolean("active"));
				bankBranch.setClientId(rs.getString("client_id"));
				bankBranch.setVirtualId(rs.getString("virtual_id"));
				bankBranch.setCreatedDate(rs.getDate("created_dt")); 
				bankBranch.setLastUpdatedBy(rs.getString("last_updated_by"));
				bankBranch.setLastUpdatedDate(rs.getDate("last_updated_dt"));
				bankBranch.setMetaRemarks(rs.getString("meta_remarks"));
				bankBranch.setGstin(rs.getString("gstin"));
				
				LOGGER.info(bankBranch.getCollectionAccountNumber());
				
				return bankBranch;
				}		
		});		
		return bankBranches;
	}
	
	@Override
	public Integer updateBankBranch(String logicalLocCode,String bankName, BankBranch updateBankBranch )
	{
		int rowCount;
		try {
			Map<String, Object> bankBranchNamedParameters = new HashMap<String, Object>();
			bankBranchNamedParameters.put("logicalLocCode", logicalLocCode);
			bankBranchNamedParameters.put("bankName", bankName);
			bankBranchNamedParameters.put("bankBranchName", updateBankBranch.getBankBranchName());		
			bankBranchNamedParameters.put("expenseAccountNumber", updateBankBranch.getExpenseAccountNumber());
			bankBranchNamedParameters.put("expenseAccountIfscCode",updateBankBranch.getExpenseAccountIfscCode());
			bankBranchNamedParameters.put("collectionAccountNumber", updateBankBranch.getCollectionAccountNumber());
			bankBranchNamedParameters.put("collectionAccountIfscCode", updateBankBranch.getCollectionAccountIfscCode());
			bankBranchNamedParameters.put("clientId" , updateBankBranch.getClientId());
			bankBranchNamedParameters.put("virtualId", updateBankBranch.getVirtualId());
			bankBranchNamedParameters.put("active" , updateBankBranch.getActive());
			bankBranchNamedParameters.put("createdBy", updateBankBranch.getCreatedBy());
			bankBranchNamedParameters.put("lastUpdatedBy", updateBankBranch.getLastUpdatedBy());
			bankBranchNamedParameters.put("ecgcStatus", updateBankBranch.getEcgcStatus());
			bankBranchNamedParameters.put("metaRemarks", updateBankBranch.getMetaRemarks());
			bankBranchNamedParameters.put("gstin", updateBankBranch.getGstin());
			
			rowCount = namedParameterJdbcTemplate.update(BankBranchSqlQueries.UPDATE_BANK_BRACH,bankBranchNamedParameters);
			
		} catch (DataAccessException e) {
			LOGGER.info("Exception occured while updating Bank Banch Details...",e);
			return null;
		}
		return rowCount;
	}
	@Override
	public BankBranch findBankByLogicalLocationAndBankName(String logicalLocCode ,  String bankName) 
	{
		BankBranch bankBranchList = new  BankBranch();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("logicalLocCode", logicalLocCode);
		paramMap.put("bankName", bankName);

		try {
			bankBranchList = namedParameterJdbcOperations.queryForObject(BankBranchSqlQueries.LOAD_BANK_BRANCH_DETAILS,
					paramMap,new RowMapper<BankBranch>() {

				@Override
				public BankBranch mapRow(ResultSet rs, int rowNum) throws SQLException {
	
					BankBranch bankBranch = new BankBranch();				
					bankBranch.setLogicalLocCode(rs.getString("logical_loc_cd"));
					bankBranch.setBankBranchName(rs.getString("bank_branch_name"));
					bankBranch.setBankName(rs.getString("bank_name" ));
					bankBranch.setExpenseAccountNumber(rs.getString("expense_acct_number"));
					bankBranch.setExpenseAccountIfscCode(rs.getString("expense_acct_ifsc_cd"));
					bankBranch.setCollectionAccountNumber(rs.getString("collection_acct_number"));
					bankBranch.setCollectionAccountIfscCode(rs.getString("collection_acct_ifsc_cd"));
					bankBranch.setCreatedBy(rs.getString("created_by"));
					bankBranch.setEcgcStatus(rs.getString("ecgc_status"));
					bankBranch.setActive(rs.getBoolean("active"));
					bankBranch.setClientId(rs.getString("client_id"));
					bankBranch.setVirtualId(rs.getString("virtual_id"));
					bankBranch.setCreatedDate(rs.getDate("created_dt"));
					bankBranch.setLastUpdatedBy(rs.getString("last_updated_by"));
					bankBranch.setLastUpdatedDate(rs.getDate("last_updated_dt"));
					bankBranch.setMetaRemarks(rs.getString("meta_remarks"));
					bankBranch.setGstin(rs.getString("gstin"));
					return bankBranch;
					
				}
			});
		} catch (EmptyResultDataAccessException e) {
			bankBranchList = null;
		}
		return bankBranchList;	
	}

	@Override
    public List<BankBranch> listActiveBankBranches() throws DataAccessException {
		List<BankBranch> bankBranchList = new ArrayList<BankBranch>();
		bankBranchList = namedParameterJdbcTemplate.query(BankBranchSqlQueries.ALL_ACTIVE_BANK_BRANCHES,
				new RowMapper<BankBranch>() {

					@Override
					public BankBranch mapRow(ResultSet rs, int rowNum) throws SQLException {
						BankBranch bankBranch = new BankBranch();
						
						bankBranch.setLogicalLocCode(rs.getString("logical_loc_cd"));
						bankBranch.setBankBranchName(rs.getString("bank_branch_name"));
						bankBranch.setBankName(rs.getString("bank_name" ));
						bankBranch.setExpenseAccountNumber(rs.getString("expense_acct_number"));
						bankBranch.setExpenseAccountIfscCode(rs.getString("expense_acct_ifsc_cd"));
						bankBranch.setCollectionAccountNumber(rs.getString("collection_acct_number"));
						bankBranch.setCollectionAccountIfscCode(rs.getString("collection_acct_ifsc_cd"));
						bankBranch.setCreatedBy(rs.getString("created_by"));
						bankBranch.setEcgcStatus(rs.getString("ecgc_status"));
						bankBranch.setActive(rs.getBoolean("active"));
						bankBranch.setClientId(rs.getString("client_id"));
						bankBranch.setVirtualId(rs.getString("virtual_id"));
						bankBranch.setCreatedDate(rs.getDate("created_dt"));
						bankBranch.setLastUpdatedBy(rs.getString("last_updated_by"));
						bankBranch.setLastUpdatedDate(rs.getDate("last_updated_dt"));
						bankBranch.setMetaRemarks(rs.getString("meta_remarks"));
						bankBranch.setGstin(rs.getString("gstin"));
						return bankBranch;
					}
				});
		return bankBranchList;
		}
	@Override
	public String getGstinByLogicalLoc(String logicalLocCode) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("logicalLocCode", logicalLocCode);
		String gstiNumber ="";
		try {
			
			gstiNumber= namedParameterJdbcOperations.queryForObject(BankBranchSqlQueries.SELECT_GSTIN_BY_LOGICAL_LOC,
					paramMap,new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	
					String gstin= "";
					gstin= rs.getString("gstin");
					return gstin ;
					
				}
			});
		} catch (EmptyResultDataAccessException e) {
			gstiNumber = "";
		}
		return gstiNumber;	
	}

	
}