package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.model.TDSMaster;
import in.ecgc.smile.erp.accounts.service.TDSMasterService;

public class TdsMasterControllerTests {
	
	private ObjectMapper mapper;
	private MockMvc mockMvc;

	@Mock
	private TDSMaster tdsMaster;
	@Mock
	TDSMaster tdsNull;
	@InjectMocks
	TDSMasterController tdsMasterController;
	@Mock
	private TDSMasterService tdsMasterService;
	

	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(tdsMasterController).build();
		mapper = new ObjectMapper();
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
		tdsMaster = new TDSMaster();
		tdsMaster.setAmount(1000.00);
		tdsMaster.setFiscalYr("2020-2021");
		tdsMaster.setFromAmount(2000.00);
		tdsMaster.setFromDt(fromDate);
		tdsMaster.setLimitEcgc(1000.00);
		tdsMaster.setSex('M');
		tdsMaster.setSurchargePer(11.0f);
		tdsMaster.setSurchargeAmt(4000.00);
		tdsMaster.setToDt(toDate);
		tdsMaster.setToAmount(5000.0);
		tdsMaster.setTaxPer(10f);
	}	
	@Test
	public void viewAllTds()throws Exception{
		ArrayList<TDSMaster>tdsMasters= new ArrayList<TDSMaster>();
		tdsMasters.add(tdsMaster);
		when(tdsMasterService.viewAllTds()).thenReturn(tdsMasters);
		mockMvc.perform(MockMvcRequestBuilders.get("/tds-master/view-all")
				.contentType(MediaType.APPLICATION_STREAM_JSON) )
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("Application/json"));			
		
		when(tdsMasterService.viewAllTds()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/tds-master/view-all")
				.contentType(MediaType.APPLICATION_STREAM_JSON) )
		.andExpect(status().isNoContent());
	}	
	
	
	@Test
	public void addTdsMasterTest()throws Exception{
		String tdsMasters = mapper.writeValueAsString(tdsMaster);
		when(tdsMasterService.addTdsDetails(tdsMaster)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/tds-master/add")				
				.content(tdsMasters)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isAccepted())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));	
	}
	
	
	@Test
	public void find() throws Exception {
		ArrayList<TDSMaster>tdsMasters= new ArrayList<TDSMaster>();
		tdsMasters.add(tdsMaster);
		
		when(tdsMasterService.find(2000.00, 5000.00, 'M', "2020-2021")).thenReturn(tdsMaster);
		mockMvc.perform(MockMvcRequestBuilders.get("/tds-master/view/2000.00/5000.0/M/2020-2021")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("Application/json"));
		
	}
}
