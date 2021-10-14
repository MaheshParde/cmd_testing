package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.poi.openxml4j.opc.internal.ContentType;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.model.TcsMaster;
import in.ecgc.smile.erp.accounts.service.LOVMasterService;

public class LovMasterControllerTests {

	private  ObjectMapper mapper ;
	private MockMvc mockMvc;
	
	@Mock
	private LOVMaster lovMaster;
	@Mock
	private LOVMasterService lovMasterService;
	@InjectMocks
	LOVMasterController lovMasterController;
	
	@Mock
	List<LOVMaster> lovMasterList; 
	
	@BeforeTest 
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(lovMasterController).build();
		mapper = new ObjectMapper();
	}

	@BeforeTest
	private void initLovMaster() {
		lovMaster  = new LOVMaster();
		lovMaster.setLov_cd("PL");
		lovMaster.setLov_desc("previledge Leave");
		lovMaster.setLov_sub_cd("pl");
		lovMaster.setLov_value("PL");
		
		lovMasterList = new ArrayList<LOVMaster>();
		lovMasterList.add(lovMaster);
	}
		
	@Test
	public void listAllLovMaster()throws Exception{
		
		when(lovMasterService.viewallLOVMstEntries()).thenReturn(lovMasterList);
		mockMvc.perform(MockMvcRequestBuilders.get("/lov-service/lov-list")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("Application/json"));
		
	}
	@Test 
	public void viewLov() throws Exception{
		when(lovMasterService.findLovByLovcd("PL")).thenReturn(lovMasterList);
		mockMvc.perform(MockMvcRequestBuilders.get("/lov-service/lov-view/?lov_cd=PL")
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				)
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("Application/json"));
	}

	@Test
	public void addLovMasterControllerTest()throws Exception{
		String lovMasters = mapper.writeValueAsString(lovMaster);
		when(lovMasterService.addLOVMstEntry(lovMaster)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/lov-service/lov")
				.content(lovMasters)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
}
