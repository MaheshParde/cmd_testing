package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.ScheduleCodeException;
import in.ecgc.smile.erp.accounts.model.Schedule;
import in.ecgc.smile.erp.accounts.repository.ScheduleDao;

/**
 * @author FAISAL
 *
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleDao scheduleDao;
	
	@Override
	public Integer addSchedule(Schedule schedule) throws ScheduleCodeException {
		int result = scheduleDao.addSchedule(schedule);
		return  result;
	}

	/* (non-Javadoc)
	 * @see in.ecgc.erp.accounts.service.ScheduleService#getScheduleByScheduleCD(java.lang.String)
	 */
	@Override
	public Schedule getScheduleByScheduleCd(String scheduleCode) throws ScheduleCodeException {
		Schedule schedule = scheduleDao.getSchedule(scheduleCode);
		return schedule;
	}

	/* (non-Javadoc)
	 * @see in.ecgc.erp.accounts.service.ScheduleService#getAllSchedule()
	 */
	@Override
	public List<Schedule> getAllSchedule() throws ScheduleCodeException {
		
		return scheduleDao.getAllSchedule();
	}

	/* (non-Javadoc)
	 * @see in.ecgc.erp.accounts.service.ScheduleService#deleteSchedule(java.lang.String)
	 */
	@Override
	public Integer deleteSchedule(String scheduleCode) throws ScheduleCodeException {

		return scheduleDao.deleteSchedule(scheduleCode);
	}

	@Override
	public Integer updateSchedule(Schedule schedule) throws ScheduleCodeException {
		
		return scheduleDao.updateSchedule(schedule);
	}
	
	

	
}
