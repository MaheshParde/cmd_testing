package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

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

import in.ecgc.smile.erp.accounts.model.ChqDishonorEntry;
import in.ecgc.smile.erp.accounts.service.ChqDishonorEntryServiceImpl;


public class ChqDishonorEntryControllerTest {

	private ObjectMapper mapper;
	private MockMvc mockMvc;

	@Mock
	private ChqDishonorEntry chqDishonorEntry;
	@Mock
	private ChqDishonorEntryServiceImpl chqDishonorEntryServiceImpl;
	@InjectMocks
	ChqDishonorEntryController chqDishonorEntryController;

	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(chqDishonorEntryController).build();
		mapper = new ObjectMapper();
	}

	@BeforeTest
	private void initLovMaster() {
		 chqDishonorEntry = new ChqDishonorEntry();
			//chqDishonorEntry.setDishonorDt(LocalDate.parse("2021-12-23"));
			chqDishonorEntry.setDishonorReason("Test");
			chqDishonorEntry.setFiscalYr("2021-22");
			chqDishonorEntry.setGlTxnNo("2021000002");
			chqDishonorEntry.setInstrumentNo("123456789");
			chqDishonorEntry.setInstrumentType("DD");
			chqDishonorEntry.setLogicalLocCd("PUNELOG1");
			chqDishonorEntry.setOldRcptNo(202100002);
			chqDishonorEntry.setRcptNo(202100002);
	}

	@Test
	public void listChqDishonorEntry() throws Exception {
		ArrayList<ChqDishonorEntry> chqList = new ArrayList<ChqDishonorEntry>();
		chqList.add(chqDishonorEntry);
		when(chqDishonorEntryServiceImpl.getChqDishonorEntryList()).thenReturn(chqList);
		mockMvc.perform(MockMvcRequestBuilders.get("/chq-dishonor-entry/view-all")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("Application/json"));
				
	}
	@Test
	public void updateChqDishonorEntryDataTest() throws Exception {
		String chqString = mapper.writeValueAsString(chqDishonorEntry);
		when(chqDishonorEntryServiceImpl.updateChqDishonorEntryData(chqDishonorEntry)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/chq-dishonor-entry/update").content(chqString)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		when(chqDishonorEntryServiceImpl.updateChqDishonorEntryData(chqDishonorEntry)).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.post("/chq-dishonor-entry/update").content(chqString)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	@Test
	public void addChqDishonorEntryDataTest() throws Exception {
		String chqString = mapper.writeValueAsString(chqDishonorEntry);
		when(chqDishonorEntryServiceImpl.addChqDishonorEntryData(chqDishonorEntry)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/chq-dishonor-entry/add").content(chqString)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		when(chqDishonorEntryServiceImpl.addChqDishonorEntryData(chqDishonorEntry)).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.post("/chq-dishonor-entry/add").content(chqString)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void getChqDishonorEntryDataByChequeNoTest() throws Exception
	{
		String chqString = mapper.writeValueAsString(chqDishonorEntry);
		when(chqDishonorEntryServiceImpl.getChqDishonorEntryByChequeNo("1104400110")).thenReturn(chqDishonorEntry);
		mockMvc.perform(MockMvcRequestBuilders.get("/chq-dishonor-entry/view/{instrumentNo}","1104400110")
				.param("instrumentNo", "1104400110").content(chqString)
		.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
	
	}
	
}
