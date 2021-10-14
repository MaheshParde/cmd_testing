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
import in.ecgc.smile.erp.accounts.model.CalendarRequestModel;
import in.ecgc.smile.erp.accounts.util.CalendarRequestQueries;

public class CalendarRequestDaoImplTests {

	private MockMvc mockMvc;

	@Mock
	private DataSource dataSource;

	@Mock
	private JdbcOperations jdbcOperations;

	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcOperations;
	
	@Mock
	private CalendarRequestModel calendarRequestModel;
	@Mock
	private List<CalendarRequestModel> calendarRequestModelList;
	@Mock
	private Map<String, Object> calendarRequestsNamedParam;
	@InjectMocks
	private CalendarDaoImpl calendarDaoImpl;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(calendarDaoImpl).build();
	}
	
	@BeforeTest
	public void initCalendarRequest() {
		calendarRequestModel = new CalendarRequestModel();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date createdOn = new Date();
		Date updatedOn = new Date();
		try {
			 createdOn = sdf.parse("2018-04-01");
			 updatedOn = sdf.parse("2019-04-01");
		} catch (Exception e) {
			System.out.println(e);
		}
		calendarRequestModel.setRequestId(12345);
		  calendarRequestModel.setRequestedBy(6541);
		  calendarRequestModel.setApprovedBy(5214);
		  calendarRequestModel.setFiscalYr("2020-2021");
		  calendarRequestModel.setMonth("March");
		  calendarRequestModel.setGlTxnType("abc");
		  calendarRequestModel.setRequestStatus("Approved");
		  calendarRequestModel.setRemark("remark");
		  calendarRequestModel.setEcgcStatus("T".charAt(0));
		  calendarRequestModel.setCreatedOn(createdOn);
		  calendarRequestModel.setUpdatedOn(updatedOn);
		  calendarRequestModel.setLogicalLocCode("MUM");
		  calendarRequestModel.setMetaRemarks("set");
		  calendarRequestModel.setBranchCode("mum123");
		  calendarRequestModel.setCalendarId("1234Cal");
	}
		
	@BeforeTest
	private void initCalendarRequestNamedParameter() {
		Map<String, Object> calendarRequestNamedParameter = new HashMap<String ,Object>();
		
		  calendarRequestNamedParameter.put("requestId", calendarRequestModel.getRequestId());
		  calendarRequestNamedParameter.put("calendarId", calendarRequestModel.getCalendarId());
		  calendarRequestNamedParameter.put("requestedBy", calendarRequestModel.getRequestedBy());
		  calendarRequestNamedParameter.put("approvedBy", calendarRequestModel.getApprovedBy());
		  calendarRequestNamedParameter.put("fiscalYr", calendarRequestModel.getFiscalYr());
		  calendarRequestNamedParameter.put("month", calendarRequestModel.getMonth());
		  calendarRequestNamedParameter.put("glTxnType", calendarRequestModel.getGlTxnType());
		  calendarRequestNamedParameter.put("status", "P");
		  calendarRequestNamedParameter.put("remark", calendarRequestModel.getRemark());
		  calendarRequestNamedParameter.put("ecgcStatus", 'V');
		  calendarRequestNamedParameter.put("createdOn", calendarRequestModel.getCreatedOn());
		  calendarRequestNamedParameter.put("updatedOn", calendarRequestModel.getUpdatedOn());
		  calendarRequestNamedParameter.put("logicalLocCode", calendarRequestModel.getLogicalLocCode());
		  calendarRequestNamedParameter.put("metaRemarks", calendarRequestModel.getMetaRemarks());
		  calendarRequestNamedParameter.put("branchCode", calendarRequestModel.getBranchCode());
	}
	@Test
	public void listAllCalendarRequests() {
		when(jdbcOperations.query(CalendarRequestQueries.VIEW_CALENDAR_REQUEST, new RowMapper<CalendarRequestModel>() {
			@Override
			public CalendarRequestModel mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				CalendarRequestModel calendarRequestModel = new CalendarRequestModel();
				 calendarRequestModel.setRequestId(rs.getInt("request_id"));
				 calendarRequestModel.setRequestedBy(rs.getInt("requested_by"));
				 calendarRequestModel.setApprovedBy(rs.getInt("approved_by"));
				 calendarRequestModel.setFiscalYr(rs.getString("fiscal_yr"));
				 calendarRequestModel.setMonth(rs.getString("month"));
				 calendarRequestModel.setGlTxnType(rs.getString("gl_txn_type"));
				 calendarRequestModel.setRequestStatus(rs.getString("status"));
				 calendarRequestModel.setRemark(rs.getString("remark"));
				 calendarRequestModel.setEcgcStatus(rs.getString("meta_status").charAt(0));
				  calendarRequestModel.setCreatedOn(rs.getDate("created_dt"));
				  calendarRequestModel.setUpdatedOn(rs.getDate("last_updated_dt"));
				  calendarRequestModel.setLogicalLocCode(rs.getString("logical_loc_cd"));
				  calendarRequestModel.setMetaRemarks(rs.getString("meta_remarks"));
				  calendarRequestModel.setBranchCode(rs.getString("branch_cd"));
				  calendarRequestModel.setCalendarId(rs.getString("calendar_id"));
				  return calendarRequestModel;
			}
		})).thenReturn(calendarRequestModelList);
	}
	
	@Test 
	private void addCalendarRequestDaoImplTest() {
		when(namedParameterJdbcOperations.update(CalendarRequestQueries.GENERATE_CALENDAR_REQUEST, calendarRequestsNamedParam))
		.thenReturn(1);		
	}
	
	@Test
	private void viewRequestByIdTest() {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("reqId", 12345);
		when(namedParameterJdbcOperations.queryForObject(CalendarRequestQueries.VIEW_CALENDAR_REQUEST_BY_ID, paramMap, new RowMapper<CalendarRequestModel>() {
		
			@Override
			public CalendarRequestModel mapRow(ResultSet rs, int rowNum) throws SQLException{
				CalendarRequestModel calendarRequestModel = new CalendarRequestModel();
				
				calendarRequestModel.setRequestId(rs.getInt("request_id"));
				calendarRequestModel.setRequestedBy(rs.getInt("requested_by"));
				calendarRequestModel.setApprovedBy(rs.getInt("approved_by"));
				calendarRequestModel.setFiscalYr(rs.getString("fiscal_yr"));
				calendarRequestModel.setMonth(rs.getString("month"));
				calendarRequestModel.setGlTxnType(rs.getString("gl_txn_type"));
				calendarRequestModel.setRequestStatus(rs.getString("status"));
				calendarRequestModel.setRemark(rs.getString("remark"));
				calendarRequestModel.setEcgcStatus(rs.getString("meta_status").charAt(0));
				calendarRequestModel.setCreatedOn(rs.getDate("created_dt"));
				calendarRequestModel.setUpdatedOn(rs.getDate("last_updated_dt"));
				calendarRequestModel.setLogicalLocCode(rs.getString("logical_loc_cd"));
				calendarRequestModel.setMetaRemarks(rs.getString("meta_remarks"));
				calendarRequestModel.setBranchCode(rs.getString("branch_cd"));
				calendarRequestModel.setCalendarId(rs.getString("calendar_id"));
				return calendarRequestModel;
				}
		} )).thenReturn(calendarRequestModel);
	}
	
	@Test
	public void updateCalendarRequestDaoImplTest() {
		when(namedParameterJdbcOperations.update(CalendarRequestQueries.UPDATE_CALENDAR_REQUEST, calendarRequestsNamedParam))
		.thenReturn(1);
	}
	
	@Test
	private void decisionOnRequestTest() {		
		when(namedParameterJdbcOperations.update(CalendarRequestQueries.UPDATE_CALENDAR_REQUEST1, calendarRequestsNamedParam))
		.thenReturn(1);
		
	}
	
	@Test
	public void getSeqTest() {
		when(namedParameterJdbcOperations.update(CalendarRequestQueries.GET_SEQ, calendarRequestsNamedParam))
		.thenReturn(1);
	}
}
