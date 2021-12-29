package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import in.ecgc.smile.erp.accounts.exception.FtrNotFoundException;
import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.service.GlTxnServiceImpl;

public class GlTxnControllerTest {

	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@Mock
	GlTxnHdr glTxnHdr;
	@Mock
	GlTxnDtl glTxnDtl;
	
	@Mock
	GlTxnServiceImpl service;
	
	@InjectMocks
	GlTxnController controller;
	
	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mapper = new ObjectMapper();
	}
	
	@BeforeTest
	public void initGlTxnHdr() {
		this.glTxnHdr = new GlTxnHdr();
		glTxnHdr.setGlTxnNo(2021000001);
		glTxnHdr.setGlTxnType("AG");
		glTxnHdr.setFiscalYr("2020-21");
		glTxnHdr.setLogicalLocCd("MUMBAILOG1");
		glTxnHdr.setGlTxnDtls(null);
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
	}

	@Test
	public void addGlTxnTest() throws Exception{
		String str = mapper.writeValueAsString(glTxnHdr);
		System.out.println(LocalDate.now());
		when(service.addGlTxn(Mockito.any())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/gl-txn/add")
		    	.content(str)
		    	.contentType(MediaType.APPLICATION_STREAM_JSON))
	       .andExpect(status().isCreated())
	       .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		/*
		when(service.addGlTxn(Mockito.any())).thenReturn(0);
		mockMvc.perform(MockMvcRequestBuilders.post("/gl-txn/add")
		    	.content(str)
		    	.contentType(MediaType.APPLICATION_STREAM_JSON))
	       .andExpect(status().isBadRequest())
	       .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	       */		
	}
	
	//@Test 
	public void getAllGltxn() throws Exception{
		List<GlTxnHdr> list = new ArrayList<>();
		list.add(glTxnHdr);;
		when(service.getAllGlTxnHdrs(Mockito.any())).thenReturn(list);		
		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn/view-all/{logicalloc}","MUMBAILOG1")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());
		
		when(service.getAllGlTxnHdrs(Mockito.any())).thenReturn(null);		
		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn/view-all/{logicalloc}","MUMBAILOG1")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isBadRequest());
		
		when(service.getAllGlTxnHdrs(Mockito.any())).thenReturn(null);		
		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn/view-all/{logicalloc}","MUMBAILOG1")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isBadRequest());
		}
	
	@Test 
	public void getAllGltxnByTxnType() throws Exception{
		List<GlTxnHdr> list = new ArrayList<>();
		list.add(glTxnHdr);;
		when(service.getAllGlTxnHdrs(Mockito.any(),Mockito.any())).thenReturn(list);		
		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn/view-all/{logicalloc}/{gltxntype}","MUMBAILOG1","AG")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());
		
//		when(service.getAllGlTxnHdrs(Mockito.any(),Mockito.any())).thenReturn(null);		
//		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn//view-all/{logicalloc}","MUMBAILOG1")
//				.contentType(MediaType.APPLICATION_STREAM_JSON))
//				.andExpect(status().isBadRequest());
//		
//		when(service.getAllGlTxnHdrs(Mockito.any(),Mockito.any())).thenReturn(null);		
//		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn//view-all/{logicalloc}","MUMBAILOG1")
//				.contentType(MediaType.APPLICATION_STREAM_JSON))
//				.andExpect(status().isBadRequest());
		}
	//@Test 
	public void getGlTxn() throws Exception{
		when(service.getGlTxn(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(glTxnHdr);		
		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn/view/{gltxnno}/{logicalloc}/{gltxntype}","2018000001","MUMBAILOG1","AG")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk());
		
		when(service.getGlTxn(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(null);		
		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn/view/{gltxnno}/{logicalloc}/{gltxntype}","2018000001","MUMBAILOG1","AG")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void getAllGltxnByFromDtLoc() throws Exception{
		List<GlTxnHdr> list = new ArrayList<>();
		list.add(glTxnHdr);
		when(service.getAllGltxnByFromDtLoc(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(list);		
		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn/get-txn/{fromDt}/{toDt}/{logicallocation}","2021-07-01","2021-07-02","MUMBAILOG1")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());
		
//		when(service.getGlTxn(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(null);		
//		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn/get-txn/{fromDt}/{toDt}/{logicallocation}","2021-07-01","2021-07-02","MUMBAILOG1")
//				.contentType(MediaType.APPLICATION_STREAM_JSON))
//				.andExpect(status().isBadRequest());
//		
	}
	
	@Test
	public void getAllGlTxnByTxnNoTxnTypeLoc() throws Exception{
		List<GlTxnHdr> list = new ArrayList<>();
		list.add(glTxnHdr);
		when(service.getAllGlTxnByTxnNoTxnTypeLoc(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn/viewby/{glTxnNo}/{glTxnType}/{logicalLoc}",2018000001,"AG","MUMBAILOG1")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());
		
//		when(service.getAllGlTxnByTxnNoTxnTypeLoc(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(null);
//		mockMvc.perform(MockMvcRequestBuilders.get("/gl-txn/viewby/{glTxnNo}/{glTxnType}/{logicalLoc}",2018000001,"AG","MUMBAILOG1")
//				.contentType(MediaType.APPLICATION_STREAM_JSON))
//				.andExpect(status().isBadRequest());

	}
}
