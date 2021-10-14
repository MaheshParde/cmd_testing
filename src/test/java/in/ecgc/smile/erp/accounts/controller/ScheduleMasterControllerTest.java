package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.Schedule;
import in.ecgc.smile.erp.accounts.service.ScheduleServiceImpl;

public class ScheduleMasterControllerTest {

	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@Mock
	Schedule schedule;
	
	@Mock
	ScheduleServiceImpl service;
	
	@InjectMocks
	ScheduleMasterServiceController controller;
	
	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mapper = new ObjectMapper();
	}
	
	@BeforeTest
	public void initSchedule() {
		schedule = new Schedule();
		schedule.setScheduleCode("14");
		schedule.setScheduleItemCode("144");
		schedule.setSrNo(1);
		schedule.setDescription("Exgratia payment in lieu of Bonus");
		schedule.setPrifix("NONE");
		schedule.setTitelDetailLine(true);
		schedule.setTotal("N");
		
	}
	
	@Test
	public void addScheduleTest() throws Exception{
		String schstr = mapper.writeValueAsString(schedule);
		when(service.addSchedule(Mockito.any())).thenReturn(new Integer(1));
		mockMvc.perform(MockMvcRequestBuilders.post("/schedule/add")
		    	.content(schstr)
		    	.contentType(MediaType.APPLICATION_STREAM_JSON))
	       .andExpect(status().isCreated())
	       .andExpect(MockMvcResultMatchers.content().contentType("application/json"));  	
			
	}
	
	@Test
	public void getAlscheduleTest() throws Exception{
		List<Schedule> list = new ArrayList<>();
		list.add(schedule);
		when(service.getAllSchedule()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/schedule/getallschedule")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());
				}
	
	@Test
	public void getScheduleTest() throws Exception{
		String scheduleCode = "14";
		when(service.getScheduleByScheduleCd(scheduleCode)).thenReturn(schedule);
		mockMvc.perform(MockMvcRequestBuilders.get("/schedule/getschedule/{cd}",scheduleCode)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void deleteScheduleTest() throws Exception{
		String scheduleCode = "14";
		when(service.deleteSchedule(scheduleCode)).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/schedule/deleteschedule/{cd}",scheduleCode)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void updateScheduleTest() throws Exception{
		String schstr = mapper.writeValueAsString(schedule);
		when(service.updateSchedule(Mockito.any())).thenReturn(new Integer(1));
		mockMvc.perform(MockMvcRequestBuilders.post("/schedule/update")
		    	.content(schstr)
		    	.contentType(MediaType.APPLICATION_STREAM_JSON))
	       .andExpect(status().isAccepted())
	       .andExpect(MockMvcResultMatchers.content().contentType("application/json"));  	
			
	}
	
}
