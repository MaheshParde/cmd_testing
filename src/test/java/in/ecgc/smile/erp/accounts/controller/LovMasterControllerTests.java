package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.service.LOVMasterService;


public class LovMasterControllerTests {

	private ObjectMapper mapper;
	private MockMvc mockMvc;

	@Mock
	private LOVMaster lovMaster;
	@Mock
	private LOVMasterService lovMasterService;
	@InjectMocks
	LOVMasterController lovMasterController;

	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(lovMasterController).build();
		mapper = new ObjectMapper();
	}

	@BeforeTest
	private void initLovMaster() {
		lovMaster = new LOVMaster();
		lovMaster.setLovCd("ACCT");
		lovMaster.setLovDesc("Income");
		lovMaster.setLovSubCd("Accounts");
		lovMaster.setLovValue("INCM");
	}

	@Test
	public void listAllLovMaster() throws Exception {
		ArrayList<LOVMaster> lovMasterList = new ArrayList<LOVMaster>();
		lovMasterList.add(lovMaster);
		when(lovMasterService.viewallLOVMstEntries()).thenReturn(lovMasterList);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/lov-service/list").contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("Application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath(".lovCd").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".lovSubCd").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".lovValue").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".lovDesc").exists());
	}

	@Test
	public void addLovMasterControllerTest() throws Exception {
		String lovMasters = mapper.writeValueAsString(lovMaster);
		when(lovMasterService.addLovMstEntry(lovMaster)).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/lov-service/add").content(lovMasters)
				.contentType(MediaType.APPLICATION_STREAM_JSON)).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void viewLovEntryTest() throws Exception
	{
		String lovMasters = mapper.writeValueAsString(lovMaster);
		when(lovMasterService.viewLovEntry("ACCT","Accounts","ASST")).thenReturn(lovMaster);
		mockMvc.perform(MockMvcRequestBuilders.get("/lov-service/view/{lovCd}/{lovSubCd}/{lovValue}","ACCT","Accounts","ASST").content(lovMasters)
		.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void getT1CodesTest() throws Exception
	{
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put("Income", "INCM");
		String lovMasters = mapper.writeValueAsString(lovMaster);
		when(lovMasterService.t1Codes()).thenReturn(mapParam);
		mockMvc.perform(MockMvcRequestBuilders.get("/lov-service/lov-get/t1Codes").content(lovMasters)
		.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void getT2CodesTest() throws Exception
	{
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put("Income", "INCM");
		String lovMasters = mapper.writeValueAsString(lovMaster);
		when(lovMasterService.t2Codes()).thenReturn(mapParam);
		mockMvc.perform(MockMvcRequestBuilders.get("/lov-service/lov-get/t2Codes").content(lovMasters)
		.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void modifyLovEntryTest() throws Exception
	{
		String lovMasters = mapper.writeValueAsString(lovMaster);
		when(lovMasterService.modifyLovEntry(lovMaster)).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.put("/lov-service/modify").content(lovMasters)
		.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void getRefNoTest() throws Exception
	{
		when(lovMasterService.getRefNo()).thenReturn(12345);
		mockMvc.perform(MockMvcRequestBuilders.get("/lov-service/get-ref-no")
		.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void disableLovEntryTest() throws Exception
	{
		String lovMasters = mapper.writeValueAsString(lovMaster);
		when(lovMasterService.disableLovEntry("ACCT", "Accounts", "INCM")).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.put("/lov-service/disable/{lovCd}/{lovSubCd}/{lovValue}","ACCT","Accounts","INCM")
		.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void savePaymentAdviceTest() throws Exception
	{
		when(lovMasterService.setPaymentAdviceNo(12345, 202100001)).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.put("/lov-service/save-payment-advice-no/{refNo}/{paymentAdviceNo}","12345","202100001")
		.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
}
