package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.ScheduleCodeMst;

public interface ScheduleCodeMasterService {

	Integer addSchedule(ScheduleCodeMst scheduleMst);
	ScheduleCodeMst getScheduleByScheduleCd(String schedule_code,String schedule_item_cd);
	List<ScheduleCodeMst> getAllSchedule() ;
	Integer deleteSchedule(String schedule_code,String schedule_item_cd);
	Integer updateSchedule(ScheduleCodeMst schedule);
}
