package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
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
import in.ecgc.smile.erp.accounts.model.TcsMaster;
import in.ecgc.smile.erp.accounts.service.TcsMasterService;

public class TcsMasterControllerTests {

	private ObjectMapper mapper;
	private MockMvc mockMvc;
	
	@Mock
	private TcsMaster tcsMaster;
	@Mock
	private TcsMasterService tcsMasterService;
	@InjectMocks
	TcsMasterController tcsController;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(tcsController).build();
		mapper = new ObjectMapper();
	}
	
	@BeforeTest
	private void initTcsMaster() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = new Date();
		Date toDate = new Date();
		try {
			 fromDate = sdf.parse("2018-04-01");
			 toDate = sdf.parse("2019-04-01");
		} catch (Exception e) {
			System.out.println(e);
		}
		tcsMaster = new TcsMaster();
		tcsMaster.setAmount(1000.00);
		tcsMaster.setFinYear("2020-2021");
		tcsMaster.setFromAmount(2000.00);
		tcsMaster.setFromDate(fromDate);
		tcsMaster.setLimitEcgc(1000.00);
		tcsMaster.setSex("M");
		tcsMaster.setSurchargePer(11.0f);
		tcsMaster.setSurchargeAmount(4000.00);
		tcsMaster.setToDate(toDate);
		tcsMaster.setToAmount(5000.0);
		tcsMaster.setTaxPercent(10f);
	}
	
	@Test
	public void listAllTcsMasterTest()throws Exception{
		ArrayList<TcsMaster>tcsMasters= new ArrayList<TcsMaster>();
		tcsMasters.add(tcsMaster);
		when(tcsMasterService.listAllTcs()).thenReturn(tcsMasters);
		mockMvc.perform(MockMvcRequestBuilders.get("/tcs-master/view-all")
				.contentType(MediaType.APPLICATION_STREAM_JSON) )
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("Application/json"));
			
		
		when(tcsMasterService.listAllTcs()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/tcs-master/view-all")
				.contentType(MediaType.APPLICATION_STREAM_JSON) )
		.andExpect(status().isNoContent());
	}	
	
	
	@Test
	public void addTcsMasterTest()throws Exception{
		String tcsMasters = mapper.writeValueAsString(tcsMaster);
		when(tcsMasterService.addTcsMaster(tcsMaster)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/tcs-master/add")
				.content(tcsMasters)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
}