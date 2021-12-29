package in.ecgc.smile.erp.accounts.repository;

import java.util.List;


import in.ecgc.smile.erp.accounts.model.ScheduleCodeMst;

public interface ScheduleCodeMasterDao {

	Integer addSchedule(ScheduleCodeMst schedule);
	ScheduleCodeMst getSchedule(String schedule_cd,String schedule_item_cd);
	List<ScheduleCodeMst> getAllSchedule();
	Integer deleteSchedule(String schedule_cd,String schedule_item_cd);
	Integer updateSchedule(ScheduleCodeMst schedule);
	
}