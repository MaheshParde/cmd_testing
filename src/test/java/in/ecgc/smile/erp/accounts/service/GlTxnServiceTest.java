package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.repository.GlTxnDao;
import in.ecgc.smile.erp.accounts.repository.GlTxnDaoImpl;

public class GlTxnServiceTest {

	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@Mock
	GlTxnHdr glTxnHdr;
	@Mock
	GlTxnDtl glTxnDtl;
	
	@Mock
	FiscalYearModel fiscalYear;
	@Mock
	EntityGL entityGL;
	
	@Mock
	GlTxnDaoImpl dao;
	
	@Mock
	Calendar calendar;
	
	@Mock
	CalendarService calendarService;
	@Mock
	FiscalYearService fiscalYrService;
	@Mock
	EntityGLMasterService entityGlService;
	
	@InjectMocks
	GlTxnServiceImpl service;
	
	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
	}

	@BeforeTest
	public void initGlTxnHdr() {
		this.glTxnHdr = new GlTxnHdr();
		glTxnHdr.setGlTxnNo(2021000001);
		glTxnHdr.setGlTxnType("AG");
		glTxnHdr.setFiscalYr("2020-21");
		glTxnHdr.setLogicalLocCd("MUMBAILOG1");
		glTxnHdr.setTxnDt(LocalDate.of(2021, 02, 17));
		List<GlTxnDtl> list = new ArrayList<>();
		GlTxnDtl dtl = new GlTxnDtl();
		dtl.setGlTxnNo(2021000001);
		dtl.setGlTxnType("AG");
		dtl.setLogicalLocCd("MUMBAILOG1");
		dtl.setSrNo(1);
		dtl.setTxnAmt(120001.00);
		dtl.setMainGlCd("1700");
		dtl.setSubGlCd("001");
		dtl.setDrCrFlag("cr");
		list.add(dtl);
		dtl.setGlTxnNo(2021000001);
		dtl.setGlTxnType("AG");
		dtl.setLogicalLocCd("MUMBAILOG1");
		dtl.setSrNo(2);
		dtl.setTxnAmt(120001.00);
		dtl.setMainGlCd("3800");
		dtl.setSubGlCd("001");
		dtl.setDrCrFlag("dr");
		list.add(dtl);
		glTxnHdr.setGlTxnDtls(list);
	}

	@BeforeTest
	void initFiscalYear() {
		fiscalYear = new FiscalYearModel();
		fiscalYear.setCurrFiscalYear("2020-21");
		fiscalYear.setCurrFiscalYearStartDt(LocalDate.of(2020,01,03));
		fiscalYear.setPrevFiscalYear("2019-20");
		fiscalYear.setPrevFiscalYearClosedDt(LocalDate.of(2020,01,03));
		fiscalYear.setMetaStatus('V');		
	}
	
	@BeforeTest
	public void initEntityGl() {

		entityGL = new EntityGL();

		entityGL.setEntityGlCd("ECGC");
		entityGL.setActive('Y');
		entityGL.setBalInd("na");
		entityGL.setCashaccount(982348123);
		entityGL.setGlIsGroup('N');
		entityGL.setGlName("BANK");
		entityGL.setMainglCd("1700");
		entityGL.setOldCd("1001");
		entityGL.setSubglCd("003");
	}
	
	@BeforeTest
	public void initCalendar() {
		calendar = new Calendar();
		calendar.setBranchCode("028BO080");
		calendar.setCalendarId("2010");
		calendar.setClosedStatus('n');
		calendar.setConfigFlag(true);
		calendar.setEcgcStatus('y');
		calendar.setFiscalYear("2020-21");
		calendar.setGlTxnType("ECGC");
		calendar.setLogicalLocCode("MUMBAILOG1");
		calendar.setMonth("feb");
		
	}

	@Test
	public void getAllGlTxnHdrsTest() throws Exception{
		List<GlTxnHdr> list = new ArrayList<>();
		list.add(glTxnHdr);
		when(dao.getAllGlTxn(Mockito.any())).thenReturn(list);
		Assert.assertEquals(service.getAllGlTxnHdrs("MUMBAILOG1"),list);
		when(dao.getAllGlTxn(Mockito.any())).thenReturn(null);
		Assert.assertEquals(service.getAllGlTxnHdrs("MUMBAILOG1"),null);		
	}
	@Test
	public void getAllGlTxnHdrsByLogicatiobAndGlTest() throws Exception{
		List<GlTxnHdr> list = new ArrayList<>();
		list.add(glTxnHdr);
		when(dao.getAllGlTxn(Mockito.any(),Mockito.any())).thenReturn(list);
		Assert.assertEquals(service.getAllGlTxnHdrs("MUMBAILOG1","AG"),list);
		when(dao.getAllGlTxn(Mockito.any(),Mockito.any())).thenReturn(null);
		Assert.assertEquals(service.getAllGlTxnHdrs("MUMBAILOG1","AG"),null);		
	}
	@Test
	public void getGlTxnTest() throws Exception{
		when(dao.getGlTxn(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(glTxnHdr);
		Assert.assertEquals(service.getGlTxn(2021000001,"MUMBAILOG1","AG"),glTxnHdr);
		when(dao.getGlTxn(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(null);
		Assert.assertEquals(service.getGlTxn(2021000001,"MUMBAILOG1","AG"),null);		
	}

	@Test(enabled=false)
	public void addGlTxnTest() throws Exception{
		
		when(fiscalYrService.findCurrentFiscalYear()).thenReturn(fiscalYear);
		when(calendarService.getByGlTypeLogicalLoc(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(calendar);
		when(entityGlService.findGLByGLCode(Mockito.any(),Mockito.any())).thenReturn(entityGL);		
		when(dao.addGlTxn(Mockito.any())).thenReturn(1);		
		Assert.assertEquals(service.addGlTxn(glTxnHdr),new  Integer(1));		
	}
	
	@Test
	public void getAllGltxnByFromDtLocTest() throws Exception{
		List<GlTxnHdr> list = new ArrayList<>();
		list.add(glTxnHdr);
		when(dao.getAllGltxnByFromDtLoc(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(list);
		Assert.assertEquals(service.getAllGltxnByFromDtLoc(LocalDate.parse("2021-07-01"),LocalDate.parse("2021-07-02"),"MUMBAILOG1"),list);
		
		when(dao.getAllGltxnByFromDtLoc(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(null);
		Assert.assertEquals(service.getAllGltxnByFromDtLoc(LocalDate.parse("2021-07-01"),LocalDate.parse("2021-07-02"),"MUMBAILOG1"),null);		
	}
	
	@Test
	public void getAllGlTxnByTxnNoTxnTypeLoc() throws Exception{
		List<GlTxnHdr> list = new ArrayList<>();
		list.add(glTxnHdr);
		when(dao.getAllGlTxnByTxnNoTxnTypeLoc(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(list);
		Assert.assertEquals(service.getAllGlTxnByTxnNoTxnTypeLoc(2021000001, "AG", "MUMBAILOG1"),list);
		when(dao.getAllGlTxnByTxnNoTxnTypeLoc(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(null);
		Assert.assertEquals(service.getAllGlTxnByTxnNoTxnTypeLoc(2021000001, "AG", "MUMBAILOG1"),null);
	}
}
