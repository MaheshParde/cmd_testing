package in.ecgc.smile.erp.accounts.repository;

import static org.mockito.Mockito.when;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import in.ecgc.smile.erp.accounts.model.TcsMaster;
import in.ecgc.smile.erp.accounts.util.TcsMasterSqlQueries;


public class TcsMasterDaoImplTest {
	private MockMvc mockMvc;
	
	@Mock 
	private DataSource dataSource;
	@Mock 
	private JdbcOperations jdbcOperations;
	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Mock
	private TcsMaster tcsMaster;
	
	@Mock 
	private List<TcsMaster> tcsMasterList;
	@Mock
	private Map<String, Object> tcsMap;
	@InjectMocks
	private TcsMasterDaoImpl tcsMasterDaoImpl;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(tcsMasterList).build();

	}
	@BeforeTest
	private void initTcsMaster() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = new Date();
		Date toDate = new Date();
		try {
			 fromDate = sdf.parse("2018-04-01");
			 toDate = sdf.parse("2019-04-01");
		} catch (Exception e) {
			System.out.println(e);
		}
		tcsMaster = new TcsMaster();
		tcsMaster.setAmount(1000.00);
		tcsMaster.setFinYear("2020-2021");
		tcsMaster.setFromAmount(2000.00);
		tcsMaster.setFromDate(fromDate);
		tcsMaster.setLimitEcgc(1000.00);
		tcsMaster.setSex("M");
		tcsMaster.setSurchargePer(11.0f);
		tcsMaster.setSurchargeAmount(4000.00);
		tcsMaster.setToDate(toDate);
		tcsMaster.setToAmount(5000.0);
		tcsMaster.setTaxPercent(10f);
	}

	@Test
	private void initTcsMasterNamedParamaeter() {
		Map<String, Object> tcsMap= new HashMap<String ,Object>();
		tcsMap.put("finYear" , tcsMaster.getFinYear());
		tcsMap.put("fromAmount", tcsMaster.getFromAmount());
		tcsMap.put("toAmount", tcsMaster.getToAmount());
		tcsMap.put("taxPercent", tcsMaster.getTaxPercent());
		tcsMap.put("createdBy" , tcsMaster.getCreatedBy());
		tcsMap.put("createdDate", tcsMaster.getCreatedDate());
		tcsMap.put("modifiedBy", tcsMaster.getModifiedBy()); 
		tcsMap.put("modifiedDate", tcsMaster.getModifiedDate());
		tcsMap.put("amount", tcsMaster.getAmount());
		tcsMap.put("fromDate", tcsMaster.getFromDate());
		tcsMap.put("toDate",tcsMaster.getToDate());
		tcsMap.put("limitEcgc", tcsMaster.getLimitEcgc());
		tcsMap.put("surchargeAmount", tcsMaster.getSurchargeAmount());
		tcsMap.put("surchargePer", tcsMaster.getSurchargePer());
		tcsMap.put("sex", tcsMaster.getSex());				
	}
	@Test
	public void listAllTcsMasterDaoImplTest() {
		when(jdbcOperations.query(TcsMasterSqlQueries.VIEW_ALL, new RowMapper<TcsMaster>() {
			@Override
			public TcsMaster mapRow(ResultSet rs , int rowNum)throws SQLException{
				TcsMaster tcsMaster = new TcsMaster();
				tcsMaster.setAmount(rs.getDouble("amount"));
				tcsMaster.setCreatedBy(rs.getString("created_by"));
				tcsMaster.setCreatedDate(rs.getDate("created_date"));
				tcsMaster.setFinYear(rs.getString("fin_yr"));
				tcsMaster.setFromAmount(rs.getDouble("from_amount"));
				tcsMaster.setFromDate(rs.getDate("from_date"));
				tcsMaster.setLimitEcgc(rs.getDouble("limit_ecgc"));
				tcsMaster.setModifiedBy(rs.getString("modified_by"));
				tcsMaster.setModifiedDate(rs.getDate("modified_date"));
				tcsMaster.setSex(rs.getString("sex"));
				tcsMaster.setSurchargePer(rs.getFloat("surcharge_per"));
				tcsMaster.setSurchargeAmount(rs.getDouble("surcharge_amt"));
				tcsMaster.setToDate(rs.getDate("to_date"));
				tcsMaster.setToAmount(rs.getDouble("to_amount"));
				tcsMaster.setTaxPercent(rs.getFloat("tax_percent"));
				return tcsMaster;
			}
		})).thenReturn(tcsMasterList);
	}
	
	@Test
	private void addTscMasterDaoImplTest() {
		when(namedParameterJdbcTemplate.update(TcsMasterSqlQueries.ADD_TCS, tcsMap))
		.thenReturn(1);
	}
	
	
}