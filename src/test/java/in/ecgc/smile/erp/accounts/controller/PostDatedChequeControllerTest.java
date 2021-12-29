package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import in.ecgc.smile.erp.accounts.model.PostDatedCheque;
import in.ecgc.smile.erp.accounts.service.PDCService;


public class PostDatedChequeControllerTest {
	
	private ObjectMapper mapper;
	private MockMvc mockMvc;
	
	@Mock
	private PDCService pdcService;
	
	private PostDatedCheque pdc;
	
	@InjectMocks
	PDCController pdcController;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(pdcController).build();
		mapper = new ObjectMapper();
	}
	
	@BeforeTest
	public void initPostDatedCheque() {
		pdc = new PostDatedCheque();
		
		pdc.setDrawnOn("SBI");
		pdc.setInstrumentAmount(123456.00f);
		pdc.setInstrumentDate(new Date(2020-12-02));
		pdc.setInstrumentNo("123456");
		pdc.setInstrumentStatus('n');
		pdc.setInstrumentType("chq");
		pdc.setLogicalLocCode("PUNE123");
		pdc.setReceivedFromaddr("Pune");
		pdc.setReceivedFromCode("abcd");
		pdc.setReceivedFromName("ANN");
	}
	
	@Test
	public void createPDCEntryTest() throws Exception {
		String pDC = mapper.writeValueAsString(pdc);
		when(pdcService.createPDCEntry(pdc)).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/post-dated-cheque/add")
				.content(pDC)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void viewAllPDCEntry() throws Exception {
		ArrayList<PostDatedCheque> pdcList = new ArrayList<>();
		pdcList.add(pdc);
		when(pdcService.listAllPDC()).thenReturn(pdcList);
		mockMvc.perform(MockMvcRequestBuilders.get("/post-dated-cheque/view-all-entries")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
		.andExpect(MockMvcResultMatchers.jsonPath(".logicalLocCode").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".receivedFromCode").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".receivedFromName").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".receivedFromaddr").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".instrumentType").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".instrumentDate").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".instrumentAmount").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".instrumentStatus").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".instrumentNo").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".drawnOn").exists());
	}
	
	@Test
	public void viewByChequeNo() throws Exception{
		when(pdcService.viewByChequeNo(123456)).thenReturn(pdc);
		mockMvc.perform(MockMvcRequestBuilders.get("/post-dated-cheque/view-by-chq-no/{cheqId}","123456")
		.contentType(MediaType.APPLICATION_STREAM_JSON))
	.andExpect(status().isOk())
	.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
	.andExpect(MockMvcResultMatchers.jsonPath(".logicalLocCode").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".receivedFromCode").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".receivedFromName").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".receivedFromaddr").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".instrumentType").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".instrumentDate").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".instrumentAmount").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".instrumentNo").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".instrumentStatus").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".drawnOn").exists());
	}
	
	
	@Test
	public void viewByChequeStatus() throws Exception{
		ArrayList<PostDatedCheque> pdcList = new ArrayList<>();
		pdcList.add(pdc);
		
		when(pdcService.viewByStatus('n')).thenReturn(pdcList);
		mockMvc.perform(MockMvcRequestBuilders.get("/post-dated-cheque/view-by-chq-status/{chqstatus}",'n')
		.contentType(MediaType.APPLICATION_STREAM_JSON))
	.andExpect(status().isOk())
	.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
	.andExpect(MockMvcResultMatchers.jsonPath(".logicalLocCode").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".receivedFromCode").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".receivedFromName").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".receivedFromaddr").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".instrumentType").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".instrumentDate").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".instrumentAmount").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".instrumentNo").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".instrumentStatus").exists())
	.andExpect(MockMvcResultMatchers.jsonPath(".drawnOn").exists());
	}
}


















