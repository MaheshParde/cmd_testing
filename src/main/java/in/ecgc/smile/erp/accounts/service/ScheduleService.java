package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.exception.ScheduleCodeException;
import in.ecgc.smile.erp.accounts.model.Schedule;

public interface ScheduleService {

	Integer addSchedule(Schedule schedule) throws ScheduleCodeException;
	Schedule getScheduleByScheduleCd(String scheduleCode) throws ScheduleCodeException ;
	List<Schedule> getAllSchedule() throws ScheduleCodeException;
	Integer deleteSchedule(String ScheduleCode)  throws ScheduleCodeException;
	Integer updateSchedule(Schedule schedule) throws ScheduleCodeException;
}
