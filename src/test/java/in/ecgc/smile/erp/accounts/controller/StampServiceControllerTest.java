package in.ecgc.smile.erp.accounts.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

import in.ecgc.smile.erp.accounts.exception.StampIncompleteDataException;
import in.ecgc.smile.erp.accounts.model.StampParameterModel;
import in.ecgc.smile.erp.accounts.service.StampServiceImpl;

public class StampServiceControllerTest {
	private ObjectMapper mapper;
	private MockMvc mockMvc;

	@Mock
	StampParameterModel stampModel;
	
	@Mock
	StampServiceImpl stampService;
	
	@InjectMocks
	StampServiceController stampController;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(stampController).build();
		mapper= new ObjectMapper();
	} 
	
	@BeforeTest
	public void initStampParameter() {
		stampModel = new StampParameterModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date effectiveDate = new Date();
		
		try {
			 effectiveDate = sdf.parse("2018-04-01");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		stampModel.setDescription("abc");
		stampModel.setFromAmount(2000.0);
		stampModel.setSrNo(1);
		stampModel.setStampAmount(1000.0);
		stampModel.setToAmount(3000.0);
		stampModel.setEffectiveDate(effectiveDate);		
	}
	
	@Test
	public void addTest() throws Exception{
		
		String stamString = mapper.writeValueAsString(stampModel);
		when(stampService.addStampParameter(Mockito.any())).thenReturn(new Integer(1));
		mockMvc.perform(MockMvcRequestBuilders.post("/stamp-parameter/add")
				.content(stamString)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		when(stampService.addStampParameter(Mockito.any())).thenReturn(new Integer(0));
		mockMvc.perform(MockMvcRequestBuilders.post("/stamp-parameter/add")
				.content(stamString)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());
		
		when(stampService.addStampParameter(Mockito.any())).thenThrow(StampIncompleteDataException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/stamp-parameter/add")
				.content(stamString)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void updateStampParameterTest()throws Exception {
		String stamString = mapper.writeValueAsString(stampModel);
		
		when(stampService.updateStampParameter(Mockito.any(), Mockito.any())).thenReturn(stampModel);
		mockMvc.perform(MockMvcRequestBuilders.post("/stamp-parameter/update/{stampCode}",1)
				.content(stamString)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));		
	}
	
	@Test
	public void allStampParameterTest() throws Exception{
		List<StampParameterModel>stampList= new ArrayList<>();
		stampList.add(stampModel);		
		when(stampService.allStampParameter()).thenReturn(stampList);
		mockMvc.perform(MockMvcRequestBuilders.get("/stamp-parameter/viewStampParameter")				
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		when(stampService.allStampParameter()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/stamp-parameter/viewStampParameter")				
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isNoContent());	
		
		}
	
	@Test
	public void viewByStampCodeTest() throws Exception{
		
		when(stampService.viewByStampCode(1)).thenReturn(stampModel);
		mockMvc.perform(MockMvcRequestBuilders.get("/stamp-parameter/view/{stampCode}",1)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk());
		}
	
}
