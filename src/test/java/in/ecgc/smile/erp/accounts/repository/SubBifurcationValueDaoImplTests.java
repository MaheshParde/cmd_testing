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
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;
import in.ecgc.smile.erp.accounts.util.BankBranchSqlQueries;
import in.ecgc.smile.erp.accounts.util.ReceiptSqlQueries;
import in.ecgc.smile.erp.accounts.util.SubBifurcationValueQueries;
import org.junit.Assert;

public class SubBifurcationValueDaoImplTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MockMvc mockMvc;

	@Mock
	private DataSource dataSource;

	@Mock
	private JdbcOperations jdbcOperations;

	@Mock
	private Map<String, Object> paramSource;

	
	@Mock
	SubBifurcationValue subBifurcationValue; 
	@Mock
	private List<SubBifurcationValue> subValueList ;
	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;	
	@Mock
	SubBifurcationValueDao subValueDao;
	
	@InjectMocks 
	SubBifurcationValueDaoImpl subValueDaoImpl;
	
	@BeforeTest
	public void initSubBifurcationValue()	{
		subBifurcationValue = new SubBifurcationValue();
		subBifurcationValue.setBifurcationLevelCode("AER");
		subBifurcationValue.setBifurcationValue("test");
		subBifurcationValue.setBifurcationValueCode("AER001");
		subBifurcationValue.setSubBifurcationLevel("Freehold - Building & Residential Property (ASST)");
		subBifurcationValue.setCreatedBy("101");
		subBifurcationValue.setLastUpdatedBy("101");
		subBifurcationValue.setMetaRemarks("V");
		subBifurcationValue.setMetaStatus("V");
		subBifurcationValue.setActive('Y');
		
	}
	

	@BeforeTest
	private void initSubBifurcationValueNamedParameter() {

		Map<String, Object> paramSource = new HashMap<String , Object>();
		paramSource.put("bifurcationLevelCode" , subBifurcationValue.getBifurcationLevelCode());			
		paramSource.put("subBifurcationLevel" , subBifurcationValue.getSubBifurcationLevel());			
		paramSource.put("bifurcationValue" , subBifurcationValue.getBifurcationValue());			
		paramSource.put("bifurcationValueCode" , subBifurcationValue.getBifurcationValueCode());
		paramSource.put("createdBy" , subBifurcationValue.getCreatedBy());
		//paramSource.put("createdDt" , subBifurcationValue.getCreatedDt());
		paramSource.put("lastUpdatedBy" , subBifurcationValue.getLastUpdatedBy());
		//paramSource.put("lastUpdatedDt" , subBifurcationValue.getLastUpdatedDt());
		paramSource.put("metaStatus" , subBifurcationValue.getMetaStatus());
		paramSource.put("metaRemarks" , subBifurcationValue.getMetaRemarks());
		paramSource.put("active" ,subBifurcationValue.getActive());
	}
//	
//	@Test
//	public void getSubBifurcationsDtlListTest() {
//		List<SubBifurcationValue> subValues = new ArrayList<SubBifurcationValue>();
//		when(jdbcOperations.query(SubBifurcationValueQueries.GET_SubBifurcationsDtl_DATA, new RowMapper<SubBifurcationValue>() {
//			public SubBifurcationValue mapRow(ResultSet rs, int rowNum) throws SQLException {
//				SubBifurcationValue subBifurcationValue = new SubBifurcationValue();
//				subBifurcationValue.setBifurcationLevelCode(rs.getString("bifurcation_level_code"));
//				subBifurcationValue.setSubBifurcationLevel(rs.getString("sub_bifurcation_level"));
//				subBifurcationValue.setBifurcationValue(rs.getString("bifurcation_value"));
//				subBifurcationValue.setBifurcationValueCode(rs.getString("bifurcation_value_code"));
//				subBifurcationValue.setCreatedBy(rs.getString("created_by"));
//				subBifurcationValue.setLastUpdatedBy(rs.getString("last_updated_by"));
//				subBifurcationValue.setMetaStatus(rs.getString("meta_status"));
//				subBifurcationValue.setMetaRemarks(rs.getString("meta_remarks"));	
//				subBifurcationValue.setActive(rs.getString("active").charAt(0));
//				return subBifurcationValue;
//				}		
//		})).thenReturn(subValues);
//	}
//	
//	@Test
//	public void addSubBifurcationsDtlDataTest() {
//		when(namedParameterJdbcTemplate.update(SubBifurcationValueQueries.ADD_SubBifurcationsDtl_DATA, paramSource ))
//		.thenReturn(1);
//	}
//	
//	@Test
//	public void updateSubBifurcationsDtlDataTest() {
//		when(namedParameterJdbcTemplate.update(SubBifurcationValueQueries.UPDATE_SubBifurcationsDtl_DATA, paramSource))
//		.thenReturn(1);
//	}
//	@Test
//	public void addSubBifurcationsDtlDataTest() throws Exception {
//	  logger.info("Inside SubBifurcationsDtlDaoImplTest - addSubBifurcationsDtlDataTest");
//	 when(namedParameterJdbcTemplate.update(SubBifurcationValueQueries.UPDATE_SubBifurcationsDtl_DATA, paramSource))
//	 .thenReturn(1);
//	Boolean response = subValueDaoImpl.addSubBifurcationsDtlData(subBifurcationValue);
//	Assert.assertEquals(response, true);
//	}
	
	
	
	
}
