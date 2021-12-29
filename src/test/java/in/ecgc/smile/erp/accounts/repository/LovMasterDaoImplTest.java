package in.ecgc.smile.erp.accounts.repository;

import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.util.LOVQueries;


public class LovMasterDaoImplTest {

	private MockMvc mockMvc;
	@Mock
	private DataSource dataSource;
	@Mock
	private JdbcOperations jdbcOperations;
	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcOperations;
	@Mock
	private LOVMaster lovMaster;
	@Mock
	private List<LOVMaster> lovMasterList;
	@Mock
	private Map<String, Object> lovMasterNamedParameter;
	@InjectMocks
	private LOVMasterDaoImpl lovMasterDaoImpl;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(lovMasterDaoImpl).build();
	}
	@BeforeTest
	private void initLovMaster() {
		lovMaster  = new LOVMaster();
		lovMaster.setLovCd("PL");
		lovMaster.setLovDesc("previledge Leave");
		lovMaster.setLovSubCd("pl");
		lovMaster.setLovValue("PL");
	}

		
	@BeforeTest
	private void initLovMasterNamedParameter() {
		Map<String, Object> lovMap =  new HashMap<String, Object>();		
		lovMap.put("lov_cd",lovMaster.getLovCd());
		lovMap.put("lov_sub_cd",lovMaster.getLovSubCd());
		lovMap.put("lov_value",lovMaster.getLovValue());
		lovMap.put("lov_desc",lovMaster.getLovDesc());	
	}
	@Test
	public void listAllLovMasterDaoImplTest() {
		when(jdbcOperations.query(LOVQueries.GET_ALL_LOV_MST_ENTRIES, new RowMapper<LOVMaster>() {
			@Override
			public LOVMaster mapRow(ResultSet rs, int rowNum)throws SQLException{
				LOVMaster lovMaster = new LOVMaster();				
				lovMaster.setLovCd(rs.getString("lov_cd"));
				lovMaster.setLovSubCd(rs.getString("lov_sub_cd"));
				lovMaster.setLovValue(rs.getString("lov_value"));
				lovMaster.setLovDesc(rs.getString("lov_desc"));
				return lovMaster;				
			}
		})).thenReturn(lovMasterList);
	}
	@Test
	private void addLovMasterDaoImplTest() {
		when(namedParameterJdbcOperations.update(LOVQueries.INSERT_LOV_MST_ENTRY_DETAILS, lovMasterNamedParameter))
		.thenReturn(1);
	}
	
	
}
