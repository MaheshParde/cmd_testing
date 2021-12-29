package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.exception.FtrNotFoundException;
import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FtrDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.service.FTRService;
import in.ecgc.smile.erp.accounts.service.FTRServiceImpl;


public class FtrControllerTest extends AbstractTestNGSpringContextTests {

	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@Mock
	FTR ftr;


	
	@Mock
	FtrDtl dtl;
	
	@Mock
	FTRService ftrService;

	@InjectMocks
	FTRController ftrController;

	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(ftrController).build();
		mapper = new ObjectMapper();
	}
	
	@BeforeTest
	public void initFtr() {
		ftr = new FTR();
		ftr.setFtrReqNo(21);
		ftr.setFtrType("Fund Transfer");
		ftr.setLogicalLocCode("MUMBAILOG1");
		ftr.setFtrReqBranchCd("MUMBAI");
		ftr.setFtrReqBy(101);
		ftr.setFtrTrfAmt(100000.00);
		ftr.setFtrReqDeptCd("ADMIN");
		//ftr.setFtrReqDt(LocalDate.of(2021,02,9));
		ftr.setFtrApprBy(102);
		ftr.setFtrReqStatus("P");
		//ftr.setFtrTrfDt(LocalDate.of(2021,02,9));
		ftr.setPvStatus("P");		
		
		FtrDtl ftrdtl = new FtrDtl();
		ftrdtl.setFTRRequestNo(21);
		ftrdtl.setFTRRequestAmount((float)50000.00);
		ftrdtl.setFTRRequestReason("Test");
		ftrdtl.setFTRRequestType("ADM");
		ftrdtl.setFTRRequestSrNo(1);
		ftrdtl.setBranchCode("MUMBAI");
		ftrdtl.setLogicalLocCode("MUMBAILOG1");
		ftrdtl.setFtrFor("Other");
		List<FtrDtl> list = new ArrayList<FtrDtl>();
		list.add(ftrdtl);
		ftr.setFtrDtl(list);
		
//	gltxnDtl.
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																			
	}
	
	
	@Test
	public void addFtrTest() throws Exception{
		String ftrstr = mapper.writeValueAsString(ftr);
		System.out.println(LocalDate.now());
		when(ftrService.addRequest(Mockito.any())).thenReturn(21);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/add")
		    	.content(ftrstr)
		    	.contentType(MediaType.APPLICATION_STREAM_JSON))
	       .andExpect(status().isAccepted())
	       .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
			
		/*
		 * when(ftrService.addRequest(Mockito.any())).thenReturn(0);
		 * mockMvc.perform(MockMvcRequestBuilders.post("/ftr/add") .content(ftrstr)
		 * .contentType(MediaType.APPLICATION_STREAM_JSON))
		 * .andExpect(status().isBadRequest());
		 * 
		 * when(ftrService.addRequest(Mockito.any())).thenThrow(FtrNotFoundException.
		 * class); mockMvc.perform(MockMvcRequestBuilders.post("/ftr/add")
		 * .content(ftrstr) .contentType(MediaType.APPLICATION_STREAM_JSON))
		 * .andExpect(status().isBadRequest());
		 */			
	}
	
	@Test 
	public void getFtrDtl() throws Exception{
		when(ftrService.getFTRRequestDTL("21")).thenReturn(ftr);		
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/getftr/{ftrReqNo}","21")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());
		
		when(ftrService.getFTRRequestDTL("21")).thenReturn(null);		
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/getftr/{ftrReqNo}","21")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isBadRequest());
		
		when(ftrService.getFTRRequestDTL("21")).thenThrow(FtrNotFoundException.class);		
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/getftr/{ftrReqNo}","21")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isBadRequest());
		}
	@Test
	public void getAllPendingFTR() throws Exception{
		List<FTR> ftrs = new ArrayList<FTR>();
		ftrs.add(ftr);
		when(ftrService.getAllPendingFTRRequest()).thenReturn(ftrs);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/get-pending")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());
		
		when(ftrService.getAllPendingFTRRequest()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/get-pending")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isNotFound());
		
		when(ftrService.getAllPendingFTRRequest()).thenThrow(FtrNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/get-pending")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isBadRequest());
}

	@Test
	public void getAllFTR() throws Exception{
		List<FTR> ftrs = new ArrayList<FTR>();
		ftrs.add(ftr);
		when(ftrService.getAllFTRRequest()).thenReturn(ftrs);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/getallFTR")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk())	;
		
		when(ftrService.getAllFTRRequest()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/getallFTR")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isNotFound());
		
		when(ftrService.getAllFTRRequest()).thenThrow(FtrNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/getallFTR")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isBadRequest());
		
	}
	@Test
	public void getAllApprovedFTRRequest() throws Exception{
		List<FTR> ftrs = new ArrayList<FTR>();
		ftrs.add(ftr);
		when(ftrService.getAllApprovedFTRRequest()).thenReturn(ftrs);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/get-approved")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());
		
		when(ftrService.getAllApprovedFTRRequest()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/get-approved")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isNotFound());
		
		when(ftrService.getAllApprovedFTRRequest()).thenThrow(FtrNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/get-approved")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isBadRequest());
}

	@Test
	public void getAllFTRForBranch() throws Exception{
		List<FTR> ftrs = new ArrayList<FTR>();
		ftrs.add(ftr);
		String logicalLoc = "SURAT1";
		when(ftrService.getAllFTRRequest(logicalLoc)).thenReturn(ftrs);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/getallFTR/{logicalLoc}",logicalLoc)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());
		
		when(ftrService.getAllFTRRequest(logicalLoc)).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/getallFTR/{logicalLoc}",logicalLoc)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isNotFound());
		
		when(ftrService.getAllFTRRequest(logicalLoc)).thenThrow(FtrNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.get("/ftr/getallFTR/{logicalLoc}",logicalLoc)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void decisionOnFtrTest() throws Exception{
		String ftrstr = mapper.writeValueAsString(this.ftr);
		
		when(ftrService.decisionOnFTRRequest(Mockito.any())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/decision")
		    	.content(ftrstr)
		    	.contentType(MediaType.APPLICATION_STREAM_JSON))
	       .andExpect(status().is2xxSuccessful())
	       .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		    
		when(ftrService.decisionOnFTRRequest(Mockito.any())).thenReturn(0);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/decision")
		    	.content(ftrstr)
		    	.contentType(MediaType.APPLICATION_STREAM_JSON))
	       .andExpect(status().isBadRequest());
		
		when(ftrService.decisionOnFTRRequest(Mockito.any())).thenThrow(FtrNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/decision")
		    	.content(ftrstr)
		    	.contentType(MediaType.APPLICATION_STREAM_JSON))
	       .andExpect(status().isBadRequest());
	}
	@Test
	public void decisionOnMultipleFtrTest() throws Exception{
		List<FTR> list = new ArrayList<>();
		list.add(ftr);
		String ftrstr = mapper.writeValueAsString(list);
		
		when(ftrService.decisionOnMultipleFTRRequest(Mockito.any())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/mul-decision")
				.content(ftrstr)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		when(ftrService.decisionOnMultipleFTRRequest(Mockito.any())).thenReturn(0);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/mul-decision")
				.content(ftrstr)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());
		
		when(ftrService.decisionOnMultipleFTRRequest(Mockito.any())).thenThrow(FtrNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/mul-decision")
				.content(ftrstr)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void updateFtrTest() throws Exception{
		String ftrstr = mapper.writeValueAsString(ftr);
		
		when(ftrService.updateFTRRequest(Mockito.any())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/update")
				.content(ftrstr)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		when(ftrService.updateFTRRequest(Mockito.any())).thenReturn(0);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/update")
				.content(ftrstr)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());
		
		when(ftrService.updateFTRRequest(Mockito.any())).thenThrow(FtrNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/update")
				.content(ftrstr)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());		
	}
	
	@Test
	public void deleteFtrTest() throws Exception {
		String ftrReqNo = "21";
		when(ftrService.deleteFTRRequest(Mockito.any())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/ftr/delete/{ftrReqNo}",ftrReqNo)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));	
	}
	@Test
	public void generateExcelForFtrTest() throws Exception{
		String[] ftrCds = {"21"}; 
		
		when(ftrService.generateExcelForFTRRequest(Mockito.any(),Mockito.any())).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/generate-excel/{bank}/{ftrCds}","BOB",ftrCds)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));		
		
		when(ftrService.generateExcelForFTRRequest(Mockito.any(),Mockito.any())).thenReturn(0);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/generate-excel/{bank}/{ftrCds}","BOB",ftrCds)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());		

		when(ftrService.generateExcelForFTRRequest(Mockito.any(),Mockito.any())).thenThrow(FtrNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/ftr/generate-excel/{bank}/{ftrCds}","BOB",ftrCds)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());		
	}
	
	/*
	 * @Test public void updateFtrTrfStatus() throws Exception { String ftrstr =
	 * mapper.writeValueAsString(ftr);
	 * 
	 * when(ftrService.changeStatusToTransfer(ftr)).thenReturn(1);
	 * mockMvc.perform(MockMvcRequestBuilders.post("/ftr/update-trf-status/21")
	 * .content(ftrstr) .contentType(MediaType.APPLICATION_STREAM_JSON))
	 * .andExpect(status().isAccepted())
	 * .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	 * 
	 * // when(ftrService.changeStatusToTransfer(Mockito.any())).thenReturn(0); //
	 * mockMvc.perform(MockMvcRequestBuilders.post("/ftr/update-trf-status/21") //
	 * .content(ftrstr) // .contentType(MediaType.APPLICATION_STREAM_JSON)) //
	 * .andExpect(status().isBadRequest()); // //
	 * when(ftrService.changeStatusToTransfer(Mockito.any())).thenThrow(
	 * ImproperFtrDataProvidedException.class); //
	 * mockMvc.perform(MockMvcRequestBuilders.post("/ftr/update-trf-status/21") //
	 * .content(ftrstr) // .contentType(MediaType.APPLICATION_STREAM_JSON)) //
	 * .andExpect(status().isBadRequest()); //
	 * 
	 * 
	 * }
	 */	
}


