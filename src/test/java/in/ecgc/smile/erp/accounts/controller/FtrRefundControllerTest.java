package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;
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

import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FtrDtl;
import in.ecgc.smile.erp.accounts.service.FTRServiceImpl;
import in.ecgc.smile.erp.accounts.service.FtrRefundServiceImpl;

public class FtrRefundControllerTest {

	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@Mock
	FTR ftr;

	@Mock
	FtrDtl dtl;
	
	@Mock
	FTRServiceImpl ftrService;
	
	@Mock
	FtrRefundServiceImpl ftrRefundService;
	
	@InjectMocks
	FtrRefundController ftrRefundController;

	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(ftrRefundController).build();
		mapper = new ObjectMapper();
	}
	
	@BeforeTest
	public void initFtr() {
		ftr = new FTR();
		ftr.setFtrReqNo(21);
		ftr.setFtrType("refund");
		ftr.setLogicalLocCode("MUMBAILOG1");
		ftr.setFtrReqBranchCd("MUMBAI");
		ftr.setFtrReqBy(101);
		ftr.setFtrTrfAmt(100000.00);
		ftr.setFtrReqDeptCd("ADMIN");
		//ftr.setFtrReqDt(LocalDate.now());
		ftr.setFtrApprBy(102);
		ftr.setFtrReqStatus("P");
		//ftr.setFtrTrfDt(LocalDate.now());
		ftr.setPvStatus("P");		
		
		FtrDtl ftrdtl = new FtrDtl();
		ftrdtl.setFTRRequestNo(21);
		ftrdtl.setFTRRequestAmount((float)50000.00);
		ftrdtl.setFTRRequestReason("Test");
		ftrdtl.setFTRRequestType("ADM");
		ftrdtl.setFTRRequestSrNo(1);
		ftrdtl.setFtrFor("Other");
		List<FtrDtl> list = new ArrayList<FtrDtl>();
		list.add(ftrdtl);
		ftr.setFtrDtl(list);
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																			
	}
	
	@Test
	public void addRefundRequestTest() throws Exception{
		String ftrstr = mapper.writeValueAsString(ftr);
		
//		when(ftrRefundService.addRefundRequest(ftr)).thenReturn(new Integer(21));
		doReturn(21).when(ftrRefundService).addRefundRequest(Mockito.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr-refund/add")
		    	.content(ftrstr)
		    	.contentType(MediaType.APPLICATION_STREAM_JSON))
	       .andExpect(status().isAccepted())
	       .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
			
	}
	
	@Test
	public void getAllFtrREquestTest() throws Exception{
		List<FTR> ftrs = new ArrayList<FTR>();
		ftrs.add(ftr);
		when(ftrRefundService.getAllFTRRequest(Mockito.any())).thenReturn(ftrs);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr-refund/getallFTR/{logicalLoc}","SURAT1")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(".ftrReqNo").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".ftrType").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".logicalLocCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".ftrTrfAmt").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".ftrReqBy").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".ftrReqBy").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".ftrReqDt").exists())
				//.andExpect(MockMvcResultMatchers.jsonPath(".FtrTrfDt").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".ftrReqStatus").exists());
				
	}
	@Test
	public void getFtrDtlTest() throws Exception{
		
		when(ftrRefundService.getFTRRequestDTL(Mockito.any())).thenReturn(ftr);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr-refund/getftr/{ftrReqNo}","21")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath(".ftrReqNo").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".ftrType").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".logicalLocCode").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".ftrTrfAmt").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".ftrReqBy").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".ftrReqBy").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".ftrReqDt").exists())
		//.andExpect(MockMvcResultMatchers.jsonPath(".FtrTrfDt").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".ftrReqStatus").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("ftrDtl").exists());
		
	}
	
	@Test
	public void decisionOnFtrRefundTest() throws Exception{
		String ftrstr = mapper.writeValueAsString(this.ftr);
		
		when(ftrRefundService.decisionOnFTRRequest(Mockito.any())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr-refund/decision")
		    	.content(ftrstr)
		    	.contentType(MediaType.APPLICATION_STREAM_JSON))
	       .andExpect(status().is2xxSuccessful())
	       .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		    	
			
	}
	
}
