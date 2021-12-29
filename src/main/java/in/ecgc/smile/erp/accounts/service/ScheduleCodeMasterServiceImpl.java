package in.ecgc.smile.erp.accounts.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.ScheduleCodeException;
import in.ecgc.smile.erp.accounts.model.ScheduleCodeMst;
import in.ecgc.smile.erp.accounts.repository.ScheduleCodeMasterDao;


/**
 * @author FAISAL
 *
 */
@Service
public class ScheduleCodeMasterServiceImpl implements ScheduleCodeMasterService {

	@Autowired
	ScheduleCodeMasterDao scheduleCodeMasterDao;

	/*
	 * @Override public Integer addSchedule(Schedule schedule) throws
	 * ScheduleCodeException { int result = scheduleDao.addSchedule(schedule);
	 * return result; }
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ecgc.erp.accounts.service.ScheduleService#getScheduleByScheduleCD(java.
	 * lang.String)
	 * 
	 * 
	 * @Override public Schedule getScheduleByScheduleCd(String scheduleCode) throws
	 * ScheduleCodeException { Schedule schedule =
	 * scheduleDao.getSchedule(scheduleCode); return schedule; }
	 * 
	 * (non-Javadoc)
	 * 
	 * @see in.ecgc.erp.accounts.service.ScheduleService#getAllSchedule()
	 * 
	 * @Override public List<Schedule> getAllSchedule() {
	 * System.err.println("on schedule Service   ======  "+scheduleDao.
	 * getAllSchedule()); return scheduleDao.getAllSchedule(); }
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.ecgc.erp.accounts.service.ScheduleService#deleteSchedule(java.lang.String)
	 * 
	 * @Override public Integer deleteSchedule(String scheduleCode) throws
	 * ScheduleCodeException {
	 * 
	 * return scheduleDao.deleteSchedule(scheduleCode); }
	 * 
	 * @Override public Integer updateSchedule(Schedule schedule) throws
	 * ScheduleCodeException {
	 * 
	 * return scheduleDao.updateSchedule(schedule); }
	 */
	@Override
	public Integer addSchedule(ScheduleCodeMst scheduleMst) {
		return scheduleCodeMasterDao.addSchedule(scheduleMst);
	}

	@Override
	public ScheduleCodeMst getScheduleByScheduleCd(String schedule_code, String schedule_item_cd) {
		return scheduleCodeMasterDao.getSchedule(schedule_code, schedule_item_cd);
	}

	@Override
	public Integer deleteSchedule(String schedule_code, String schedule_item_cd) {
		return scheduleCodeMasterDao.deleteSchedule(schedule_code, schedule_item_cd);
	}

	@Override
	public Integer updateSchedule(ScheduleCodeMst schedule) {
		return scheduleCodeMasterDao.updateSchedule(schedule);
	}

	@Override
	public List<ScheduleCodeMst> getAllSchedule() {
		return scheduleCodeMasterDao.getAllSchedule();
	}

}
