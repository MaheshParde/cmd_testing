
package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import in.ecgc.smile.erp.accounts.model.ScheduleCodeMst;
import in.ecgc.smile.erp.accounts.repository.ScheduleCodeMasterDao;

public class ScheduleServiceImplTest {

	
	@Mock
	ScheduleCodeMst schedule;

	@Mock
	ScheduleCodeMasterDao dao;

	@InjectMocks
	ScheduleCodeMasterServiceImpl service;

	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
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
	public void addScheduleTest(){
		when(dao.addSchedule(schedule)).thenReturn(1);
		Assert.assertEquals(service.addSchedule(schedule), new Integer(1));
	}

	@Test
	public void getScheduleByScheduleCdTest(){
		when(dao.getSchedule("14", "14.4")).thenReturn(schedule);
		Assert.assertEquals(service.getScheduleByScheduleCd("14","14.4"), schedule);
	}

	@Test
	public void getAllScheduleTest(){
		List<ScheduleCodeMst> list = new ArrayList<>();
		list.add(schedule);
		when(dao.getAllSchedule()).thenReturn(list);
		Assert.assertEquals(service.getAllSchedule(), list);
	}

	@Test
	public void deleteScheduleTest(){
		when(dao.deleteSchedule("14", "14.4")).thenReturn(1);
		Assert.assertEquals(service.deleteSchedule("14","14.4"), new Integer(1));
	}

	@Test
	public void updateScheduleTest(){
		when(dao.updateSchedule(schedule)).thenReturn(1);
		Assert.assertEquals(service.updateSchedule(schedule), new Integer(1));
	}

}
