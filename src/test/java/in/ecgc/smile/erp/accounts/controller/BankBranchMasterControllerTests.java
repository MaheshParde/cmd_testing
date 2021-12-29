package in.ecgc.smile.erp.accounts.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.service.BankBranchService;

public class BankBranchMasterControllerTests {
	private ObjectMapper mapper;
	
	private MockMvc mockMvc;
	
	@Mock
	private BankBranchService bankBranchService;

	@Mock
	private BankBranch bankBranch;
	
	@InjectMocks
	BankBranchMasterController bankBranchMasterController; 
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bankBranchMasterController).build();
		mapper = new ObjectMapper();
	}
	@BeforeTest
	public void initBankBranch()
	{
		bankBranch= new BankBranch();
		bankBranch.setBankBranchName("Mumbai");
		bankBranch.setBankName("PNB");
		bankBranch.setCollectionAccountNumber("1209001509004024");
		bankBranch.setCollectionAccountIfscCode("PUNB0120900");
		bankBranch.setExpenseAccountIfscCode("PUNB0120900");
		bankBranch.setExpenseAccountNumber("1209001500004111");
		bankBranch.setLogicalLocCode("MUM");
		bankBranch.setActive(true);
		bankBranch.setClientId("1234");
		bankBranch.setEcgcStatus("T");
		bankBranch.setMetaRemarks("abc");
		bankBranch.setVirtualId("abc");
		bankBranch.setGstin("123456789123456");
	}
	
	
	@Test
	public void listAllBankBranchTest()throws Exception{
		ArrayList<BankBranch>bankBranchList = new ArrayList<BankBranch>();
		bankBranchList.add(bankBranch);
		when(bankBranchService.listAllBankBranches()).thenReturn(bankBranchList);
		mockMvc.perform(MockMvcRequestBuilders.get("/bank-branch-master/view-all") 
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());		
		when(bankBranchService.listAllBankBranches()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/bank-branch-master/view-all") 
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isNoContent());
		
	}
		
	@Test
	private void listActiveBankBranchesTest() throws Exception{
		ArrayList<BankBranch> bankBranchList = new ArrayList<BankBranch>();
		bankBranchList.add(bankBranch);
		when(bankBranchService.listActiveBankBranches()).thenReturn(bankBranchList);
		mockMvc.perform(MockMvcRequestBuilders.get("/bank-branch-master/active/view-all")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		
		when(bankBranchService.listActiveBankBranches()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/bank-branch-master/active/view-all")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isNoContent());		
	}
	
	@Test
	public void addBankBranchTest() throws Exception{
		String bankBranches  = mapper.writeValueAsString(bankBranch);
		when(bankBranchService.addBankBranch(bankBranch)).thenReturn(true);		
		mockMvc.perform(MockMvcRequestBuilders.post("/bank-branch-master/add")
				.content(bankBranches)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
			
	}
	
	@Test
	private void viewGstinByLogicalLocation()throws Exception {
		String gstin = "";
		when(bankBranchService.getGstinByLogicalLoc("MUM")).thenReturn(gstin);
		mockMvc.perform(MockMvcRequestBuilders.get("/bank-branch-master/view-gstin/{logicalLocCode}", "MUM")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));
	}
	
	
	@Test
	private void findBankByLogicalLocationAndBankNameTest()throws Exception{
		when(bankBranchService.findBankByLogicalLocationAndBankName("MUM", "PNB")).thenReturn(bankBranch);
		mockMvc.perform(MockMvcRequestBuilders.get("/bank-branch-master/view/{logicalLocCode}/{bankName}","MUM","PNB" )
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath(".logicalLocCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".bankName").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".bankBranchName").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".expenseAccountNumber").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".expenseAccountIfscCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".collectionAccountNumber").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".collectionAccountIfscCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".gstin").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".virtualId").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".clientId").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".active").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".ecgcStatus").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".metaRemarks").exists());
		}
	
	@Test
	private void updateBankBranchTest() throws Exception {
		String bankBranches = mapper.writeValueAsString(bankBranch);
		
		when(bankBranchService.updateBankBranch("MUM","PNB" , bankBranch)).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.put("/bank-branch-master/update/{logicalLocCode}/{bankName}","MUM", "PNB")
				.content(bankBranches)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
	}
	
	@Test
	private void disableBankBranchTest() throws Exception {
		when(bankBranchService.disableBankBranch("MUM", "PNB")).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/bank-branch-master/delete/{logicalLocCode}/{bankName}", "MUM" , "PNB")
				.contentType(MediaType.APPLICATION_STREAM_JSON ))
		.andExpect(status().isAccepted())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
	}

}
