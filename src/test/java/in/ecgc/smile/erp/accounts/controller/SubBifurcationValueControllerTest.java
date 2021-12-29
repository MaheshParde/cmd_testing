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
import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;
import in.ecgc.smile.erp.accounts.service.SubBifurcationValueService;

public class SubBifurcationValueControllerTest {

private ObjectMapper mapper;
	
	private MockMvc mockMvc;
	@InjectMocks
	SubBifurcationValueController subController; 
	@Mock
	SubBifurcationValue subBifurcationValue; 
	@Mock
	SubBifurcationValueService valueService; 
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(subController).build();
		mapper = new ObjectMapper();
	}
	@BeforeTest
	public void initSubBifurcationValue()	{
		subBifurcationValue = new SubBifurcationValue();
		subBifurcationValue.setBifurcationLevelCode("AER");
		subBifurcationValue.setBifurcationValue("test");
		subBifurcationValue.setBifurcationValueCode("AER001");
		subBifurcationValue.setSubBifurcationLevel("Freehold - Building & Residential Property (ASST)");
	}
	@Test
	public void getAllSubBifurcationValues()throws Exception{
		ArrayList<SubBifurcationValue>valueList = new ArrayList<SubBifurcationValue>();
		valueList.add(subBifurcationValue);
		when(valueService.getSubBifurcationsDtlList()).thenReturn(valueList);
		mockMvc.perform(MockMvcRequestBuilders.get("/sub-bifurcation-value/view-all") 
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());		
		
	}
	@Test
	public void addSubBifurcationValue() throws Exception{
		String subString  = mapper.writeValueAsString(subBifurcationValue);
		when(valueService.addSubBifurcationsDtlData(subBifurcationValue)).thenReturn(true);		
		mockMvc.perform(MockMvcRequestBuilders.post("/sub-bifurcation-value/add")
				.content(subString)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
			
	}
	@Test
	private void getByIdTest()throws Exception{
		when(valueService.getSubBifurcationsDtlDataById("AER", "AER001")).thenReturn(subBifurcationValue);
		mockMvc.perform(MockMvcRequestBuilders.get("/sub-bifurcation-value/get-by-id/{bifurcationLevelCode}/{bifurcationValueCode}","AER","AER001" )
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath(".bifurcationLevelCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".bifurcationValueCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".bifurcationValue").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".subBifurcationLevel").exists());
		}
	
	@Test
	private void getBifurcationValueCode()throws Exception {
		String valueCode = "";
		when(valueService.getBifurcationCode("AER")).thenReturn(valueCode);
		mockMvc.perform(MockMvcRequestBuilders.get("/sub-bifurcation-value/get-value-code/{levelCode}", "AER")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));
	}
	

	@Test
	private void updateSubBiurcationTest() throws Exception {
		String subValueString = mapper.writeValueAsString(subBifurcationValue);
		
		when(valueService.updateSubBifurcationsDtlData("AER","AER001" , subBifurcationValue)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/sub-bifurcation-value/update/{bifurcationlevelCode}/{bifurcationValueCode}","AER", "AER001")
				.content(subValueString)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
	}
	
	
}
