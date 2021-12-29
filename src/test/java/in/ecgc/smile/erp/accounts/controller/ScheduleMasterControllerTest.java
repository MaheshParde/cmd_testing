
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

import in.ecgc.smile.erp.accounts.model.ScheduleCodeMst;
import in.ecgc.smile.erp.accounts.service.ScheduleCodeMasterServiceImpl;

public class ScheduleMasterControllerTest {

	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@Mock
	ScheduleCodeMst schedule;

	@Mock
	ScheduleCodeMasterServiceImpl service;

	@InjectMocks
	ScheduleCodeMasterController controller;

	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mapper = new ObjectMapper();
	}

	@BeforeTest
	public void initSchedule() {
		schedule = new ScheduleCodeMst();
		schedule.setSchedule_cd("14");
		schedule.setSchedule_item_cd("14.4");
		schedule.setDescription("Exgratia payment in lieu of Bonus");
		schedule.setTitle_detail_line("N");
		schedule.setTotal("Y");
		schedule.setPrefix("NONE");
	}

	@Test
	public void addScheduleTest() throws Exception {
		String schstr = mapper.writeValueAsString(schedule);
		when(service.addSchedule(Mockito.any())).thenReturn(new Integer(1));
		mockMvc.perform(MockMvcRequestBuilders.post("/schedule-mst").content(schstr)
				.contentType(MediaType.APPLICATION_STREAM_JSON)).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}

	@Test
	public void getAllscheduleTest() throws Exception {
		List<ScheduleCodeMst> list = new ArrayList<>();
		list.add(schedule);
		when(service.getAllSchedule()).thenReturn(list);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/schedule-mst").contentType(MediaType.APPLICATION_STREAM_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void getScheduleTest() throws Exception {
		when(service.getScheduleByScheduleCd("14","14.4")).thenReturn(schedule);
		mockMvc.perform(MockMvcRequestBuilders.get("/schedule-mst/{schedule_cd}/{schedule_item_cd}", "14","14.4")
				.contentType(MediaType.APPLICATION_STREAM_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteScheduleTest() throws Exception {
		when(service.deleteSchedule("14","14.4")).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/schedule-mst/{schedule_cd}/{schedule_item_cd}", "14","14.4")
				.contentType(MediaType.APPLICATION_STREAM_JSON)).andExpect(status().isOk());
	}

	@Test
	public void updateScheduleTest() throws Exception {
		String scheduleString = mapper.writeValueAsString(schedule);
		when(service.updateSchedule(Mockito.any())).thenReturn(new Integer(1));
		mockMvc.perform(MockMvcRequestBuilders.put("/schedule-mst").content(scheduleString)
				.contentType(MediaType.APPLICATION_STREAM_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
}
