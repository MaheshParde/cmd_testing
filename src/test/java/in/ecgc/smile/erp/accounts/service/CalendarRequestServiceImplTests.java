package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bouncycastle.operator.jcajce.JcaAlgorithmParametersConverter;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.exception.CalendarExceptionHandling;
import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.CalendarRequestModel;
import in.ecgc.smile.erp.accounts.model.DateOperation;
import in.ecgc.smile.erp.accounts.repository.CalendarDao;
import in.ecgc.smile.erp.accounts.repository.CalendarDaoImpl;
import in.ecgc.smile.erp.accounts.repository.CalendarRequestDao;

public class CalendarRequestServiceImplTests {

	
	private MockMvc mockMvc;
	@Mock
	CalendarRequestDao calendarRequestDao;
	@Mock
	public Calendar calendar;
	
	@Mock
	CalendarDao calendarDao;
	@Mock
	DateOperation dateOperation;
	
	
	@Mock
	private CalendarServiceImpl calendarServiceImpl;
	@Mock
	private CalendarRequestModel calendarRequestModel;
	@InjectMocks
	CalendarRequestServiceImpl calendarRequestServiceImpl;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(calendarRequestServiceImpl).build();
	}
	
	@BeforeTest
	public void initCalendarRequestModel() {
		this.calendarRequestModel = new CalendarRequestModel();		
		this.calendarRequestModel.setRequestId(12345);
		this.calendarRequestModel.setRequestedBy(6541);
		this.calendarRequestModel.setApprovedBy(5214);
		this.calendarRequestModel.setFiscalYr("2020-2021");
		this.calendarRequestModel.setMonth("Jan");
		this.calendarRequestModel.setGlTxnType("PV");
		this.calendarRequestModel.setRequestStatus("A");
		this.calendarRequestModel.setRemark("remark");
		this.calendarRequestModel.setEcgcStatus("T".charAt(0));
		this.calendarRequestModel.setLogicalLocCode("MUMBAILOG01");
		this.calendarRequestModel.setMetaRemarks("Test");
		this.calendarRequestModel.setBranchCode("MUMBAI01");
		this.calendarRequestModel.setCalendarId("1234");
	}
	
	@BeforeTest
	public void initCalendar() {
		calendar = new Calendar();
		calendar.setBranchCode("MUM");
		calendar.setCalendarId("1234");
		calendar.setClosedStatus("T".charAt(0));
		calendar.setConfigFlag(false);
		calendar.setDescription("Calendar");
		calendar.setEcgcStatus("T".charAt(0));
		calendar.setFiscalYear("2018-2019");
		calendar.setGlTxnType("PV");
		calendar.setLogicalLocCode("MUMBAILOG01");
		calendar.setMetaRemarks("T");
		calendar.setMonth("september");
		calendar.setTxnTypeName("Payments");
}
	
	@Test
	public void generateRequest() throws CalendarExceptionHandling {
		when(calendarDao.getCalendar("1234")).thenReturn(calendar);
		when(calendarRequestDao.generateRequest(calendarRequestModel)).thenReturn(1);
		when(calendarServiceImpl.getCalendar(calendarRequestModel.getCalendarId())).thenReturn(calendar);
		Assert.assertEquals(calendarRequestServiceImpl.generateRequest(calendarRequestModel),new Integer(1));
	}
	
	@Test(expectedExceptions = CalendarExceptionHandling.class)
	public void getRequestByIdTest()throws CalendarExceptionHandling {
		when(calendarRequestDao.viewRequestById(12345)).thenReturn(calendarRequestModel) ;
		Assert.assertEquals(calendarRequestServiceImpl.viewRequestById(12345),calendarRequestModel);
		
		when(calendarRequestDao.viewRequestById(12345)).thenReturn(null) ;
		Assert.assertEquals(calendarRequestServiceImpl.viewRequestById(12345),calendarRequestModel);
	}
	
	@Test
	public void listAllCalendarRequestsServiceImplTest() {
		List<CalendarRequestModel> calendarRequestModels = new ArrayList<>();
		calendarRequestModels.add(calendarRequestModel);
		when(calendarRequestDao.viewAllRequest()).thenReturn(calendarRequestModels);
		Assert.assertEquals(calendarRequestServiceImpl.viewAllRequest(), calendarRequestModels);
	}
	
	@Test
	public void updateCalendarRequestServiceImplTest() {
		when(calendarRequestDao.updateCalendarRequest(12345, calendarRequestModel)).thenReturn(calendarRequestModel);
		Assert.assertEquals(calendarRequestServiceImpl.updateCalendarRequest(12345, calendarRequestModel),calendarRequestModel);
	}
	
	@Test
	public void decisionOnRequestServiceImplTest() {
		//when(calendarRequestModel.getRequestStatus().equalsIgnoreCase("A")).thenReturn(true);
		when(calendarRequestDao.decisionOnRequest(calendarRequestModel)).thenReturn(1);
		when(calendarDao.updateStatus1(calendarRequestModel.getCalendarId(), "N")).thenReturn(1);
		when(calendarDao.getCalendar(Mockito.any())).thenReturn(calendar);
		
		when(calendarDao.openPrev("MUMBAILOG01", "PV", "N", "september", "2018-2019")).thenReturn(1);
		Assert.assertEquals(calendarRequestServiceImpl.decisionOnRequest(calendarRequestModel),new Integer(1));
		
		this.calendarRequestModel.setRequestStatus("B");
		Assert.assertEquals(calendarRequestServiceImpl.decisionOnRequest(calendarRequestModel),new Integer(1));
		
//		when(calendarRequestDao.decisionOnRequest(calendarRequestModel)).thenReturn(0);
//		when(calendarDao.updateStatus1(calendarRequestModel.getCalendarId(), "N")).thenReturn(1);
//		when(calendarDao.openPrev("MUMBAILOG01", "PV", "N", "sep", "2018-2019")).thenReturn(1);
//		//when(calendarRequestModel.getRequestStatus().equalsIgnoreCase("A")).thenReturn(false);
//		Assert.assertEquals(calendarRequestServiceImpl.decisionOnRequest(calendarRequestModel), new Integer(1));
		}
	}
