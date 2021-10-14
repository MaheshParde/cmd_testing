package in.ecgc.smile.erp.accounts.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.accounts.exception.CalendarExceptionHandling;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.CalendarRequestModel;
import in.ecgc.smile.erp.accounts.service.CalendarRequestService;
import in.ecgc.smile.erp.accounts.service.CalendarServiceImpl;


public class CalenderRequestControllerTests {

	private ObjectMapper mapper;
	private MockMvc mockMvc;
	@Mock
	private CalendarRequestService calendarRequestService;
	
	@Mock
	private Calendar calendar;
	@Mock
	private CalendarServiceImpl calendarServiceImpl; 
		
	@Mock
	private CalendarRequestModel calendarRequestModel;
	@InjectMocks
	CalendarRequestController calendarRequestController;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(calendarRequestController).build();
		mapper = new ObjectMapper();		
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
		
	@Test
	public void testTest() throws Exception {
		mockMvc.perform(get("/"))
		.andDo(print())
		.andReturn()
		;
		
	}
	
	@Test
	public void listAllRequests()throws Exception{
		ArrayList<CalendarRequestModel>calendarRequests = new ArrayList<CalendarRequestModel>();
		calendarRequests.add(calendarRequestModel);
		when(calendarRequestService.viewAllRequest()).thenReturn(calendarRequests);
		mockMvc.perform(MockMvcRequestBuilders.get("/calendar-request/viewAllRequest")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
				
		when(calendarRequestService.viewAllRequest()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/calendar-request/viewAllRequest")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isNoContent());
	}
	
	@Test
	public void addRequestTest()throws Exception{
		String CalendarRequests= mapper.writeValueAsString(calendarRequestModel);
		when(calendarRequestService.generateRequest(calendarRequestModel)).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/calendar-request/add")
				.content(CalendarRequests)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());
		
		when(calendarRequestService.generateRequest(Mockito.any())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/calendar-request/add")
				.content(CalendarRequests)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		when(calendarRequestService.generateRequest(Mockito.any())).thenThrow(CalendarExceptionHandling.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/calendar-request/add")
				.content(CalendarRequests)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	private void updateRequestTests()throws Exception{
		String calendarRequests = mapper.writeValueAsString(calendarRequestModel);
		when(calendarRequestService.updateCalendarRequest(12345,calendarRequestModel)).thenReturn(calendarRequestModel);
		mockMvc.perform(MockMvcRequestBuilders.post("/calendar-request/update/{reqId}", 12345)
				.content(calendarRequests)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isCreated());
	}
	
	@Test
	public void viewRequestByIdTest()throws Exception{
		when(calendarRequestService.viewRequestById(12345)).thenReturn(calendarRequestModel);
		mockMvc.perform(MockMvcRequestBuilders.get("/calendar-request/view/{reqId}", 12345)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
		.andExpect(MockMvcResultMatchers.jsonPath(".requestId").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".calendarId").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".requestedBy").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".approvedBy").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".fiscalYr").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".month").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".glTxnType").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".requestStatus").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".remark").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".ecgcStatus").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".createdOn").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".updatedOn").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".logicalLocCode").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".metaRemarks").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".branchCode").exists());
	}
	

	@Test
	private void decisionOnRequestTests()throws Exception{
		String calendarRequests = mapper.writeValueAsString(calendarRequestModel);
		
		when(calendarRequestService.decisionOnRequest(calendarRequestModel)).thenReturn(0);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/calendar-request/decision")
				.content(calendarRequests)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());
		
		when(calendarRequestService.decisionOnRequest(Mockito.any())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/calendar-request/decision")
				.content(calendarRequests)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isAccepted())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		  when(calendarRequestService.decisionOnRequest(Mockito.any())).thenThrow(CalendarExceptionHandling.class);
		  mockMvc.perform(MockMvcRequestBuilders.post("/calendar-request/decision")
		  .content(calendarRequests) .contentType(MediaType.APPLICATION_STREAM_JSON))
		  .andExpect(status().isOk()) .andDo(print());
		 
	}
}
