package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.exception.ScheduleCodeException;
import in.ecgc.smile.erp.accounts.model.Schedule;
import in.ecgc.smile.erp.accounts.repository.ScheduleDaoImpl;

public class ScheduleServiceImplTest {

	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@Mock
	Schedule schedule;
	
	@Mock
	ScheduleDaoImpl dao;
	
	@InjectMocks
	ScheduleServiceImpl service;
	
	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
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
	public void addScheduleTest() throws ScheduleCodeException {
		when(dao.addSchedule(schedule)).thenReturn(1);
		Assert.assertEquals(service.addSchedule(schedule),new Integer(1));
	}
	@Test
	public void getScheduleByScheduleCdTest() throws ScheduleCodeException {
		when(dao.getSchedule("14")).thenReturn(schedule);
		Assert.assertEquals(service.getScheduleByScheduleCd("14"),schedule);
	}
	
	@Test
	public void getAllScheduleTest() throws ScheduleCodeException {
		List<Schedule> list = new ArrayList<>();
		list.add(schedule);
		when(dao.getAllSchedule()).thenReturn(list);
		Assert.assertEquals(service.getAllSchedule(),list);
	}
	@Test
	public void deleteScheduleTest() throws ScheduleCodeException {
		String scheduleCode ="14";
		when(dao.deleteSchedule(scheduleCode)).thenReturn(1);
		Assert.assertEquals(service.deleteSchedule(scheduleCode),new Integer(1));
	}
	
	@Test
	public void updateScheduleTest() throws ScheduleCodeException {
		when(dao.updateSchedule(schedule)).thenReturn(1);
		Assert.assertEquals(service.updateSchedule(schedule),new Integer(1));
	}


}
