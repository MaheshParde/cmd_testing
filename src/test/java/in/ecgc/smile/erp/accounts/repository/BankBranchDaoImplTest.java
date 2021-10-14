package in.ecgc.smile.erp.accounts.repository;

import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.util.BankBranchSqlQueries;

public class BankBranchDaoImplTest {

	
	private MockMvc mockMvc;

	@Mock
	private DataSource dataSource;

	@Mock
	private JdbcOperations jdbcOperations;

	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcOperations;

	@Mock
	private BankBranch bankBranch;

	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Mock
	private List<BankBranch> bankBranchList;
	
	@Mock
	private Map<String, Object> bankBranchNamedParameter;

	@InjectMocks
	private BankBranchDaoImpl bankBranchDaoImpl;

	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bankBranchDaoImpl).build();
	}
	
	@BeforeTest
	public void initBankBranch()
	{
		bankBranch= new BankBranch();
		bankBranch.setBankBranchName("MUMBAI");
		bankBranch.setBankName("PNB");
		bankBranch.setCollectionAccountNumber("1209001500004024");
		bankBranch.setCollectionAccountIfscCode("PUNB0120900");
		bankBranch.setExpenseAccountIfscCode("PUNB0120900");
		bankBranch.setExpenseAccountNumber("1209001500004111");
		bankBranch.setLogicalLocCode("MUM");
		bankBranch.setActive(true);
		bankBranch.setClientId("1234");
		bankBranch.setEcgcStatus("T");
		bankBranch.setMetaRemarks("abc");
		bankBranch.setVirtualId("abc");
		bankBranch.setGstin("123456789123456");
	}

	@BeforeTest
	private void initBankBranchNamedParameters() {

		Map<String, Object> bankBranchNamedParameter = new HashMap<String , Object>();
		bankBranchNamedParameter.put("logicalLocCode", bankBranch.getLogicalLocCode());		
		bankBranchNamedParameter.put("bankName", bankBranch.getBankName());
		bankBranchNamedParameter.put("bankBranchName", bankBranch.getBankBranchName());		
		bankBranchNamedParameter.put("expenseAccountNumber", bankBranch.getExpenseAccountNumber());
		bankBranchNamedParameter.put("expenseAccountIfscCode",bankBranch.getExpenseAccountIfscCode());
		bankBranchNamedParameter.put("collectionAccountNumber", bankBranch.getCollectionAccountNumber());
		bankBranchNamedParameter.put("collectionAccountIfscCode", bankBranch.getCollectionAccountIfscCode());
		bankBranchNamedParameter.put("clientId" , bankBranch.getClientId());
		bankBranchNamedParameter.put("virtualId", bankBranch.getVirtualId());
		bankBranchNamedParameter.put("active" , bankBranch.getActive());
		bankBranchNamedParameter.put("createdBy", bankBranch.getCreatedBy());
		bankBranchNamedParameter.put("lastUpdatedBy", bankBranch.getLastUpdatedBy());
		bankBranchNamedParameter.put("ecgcStatus", bankBranch.getEcgcStatus());
		bankBranchNamedParameter.put("metaRemarks", bankBranch.getMetaRemarks());
		bankBranchNamedParameter.put("gstin", bankBranch.getGstin());				
	}
		
	@Test
	public void listAllBankBranchesTest() {
		List<BankBranch> bankBranches = new ArrayList<BankBranch>();
		when(namedParameterJdbcOperations.query(BankBranchSqlQueries.ALL_BANK_BRANCHES, new RowMapper<BankBranch>() {
			@Override
			public BankBranch mapRow(ResultSet rs,int rowNum) throws SQLException{
				BankBranch bankBranch =  new BankBranch();

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
		})).thenReturn(bankBranchList);
	}
	
	@Test
	private void addBankBranchtest() {
		when(namedParameterJdbcOperations.update(BankBranchSqlQueries.ADD_BANK_BRANCH, bankBranchNamedParameter))
		.thenReturn(1);
	}

	@Test
	public void findBankBranchByLogicalLocAndBankNameDaoImplTest() {
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("logicalLocCode", "MUM");
		paraMap.put("bankName", "PNB");
		when(namedParameterJdbcOperations.queryForObject(BankBranchSqlQueries.LOAD_BANK_BRANCH_DETAILS, paraMap, new RowMapper<BankBranch>() {
			
			@Override
			public BankBranch mapRow(ResultSet rs , int rownum) throws SQLException{
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
		})).thenReturn(bankBranch);
	}
	
	@Test
	public void updateBankBranchDoaImplTest() {
		when(namedParameterJdbcOperations.update(BankBranchSqlQueries.UPDATE_BANK_BRACH, bankBranchNamedParameter))
		.thenReturn(1);
	}
	
	@Test
	public void disableBankBranchDaoImpleTest() {
		when(namedParameterJdbcOperations.update(BankBranchSqlQueries.DISABLE_BANK_BRANCH,
				new MapSqlParameterSource("logicalLocCode","MUM").addValue("bankName", "PNB"))).thenReturn(1);
	}
		
	@Test 
	public void listActiveBankBranches() {
		when(jdbcOperations.query(BankBranchSqlQueries.ALL_ACTIVE_BANK_BRANCHES, new RowMapper<BankBranch>() {
			
			@Override
			public BankBranch mapRow(ResultSet rs  ,int rowNum)throws SQLException{
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
		})).thenReturn(bankBranchList);
	}
	
	
}

