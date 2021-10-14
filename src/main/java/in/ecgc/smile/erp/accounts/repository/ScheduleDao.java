package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.Schedule;

public interface ScheduleDao {

	Integer addSchedule(Schedule schedule);
	Schedule getSchedule(String scheduleCode);
	List<Schedule> getAllSchedule();
	Integer deleteSchedule(String scheduleCode);
	Integer updateSchedule(Schedule schedule);
	
}