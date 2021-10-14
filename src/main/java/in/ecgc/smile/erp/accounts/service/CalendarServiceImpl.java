package in.ecgc.smile.erp.accounts.service;

import java.time.LocalDateTime;

import java.util.List ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.CalendarMonthlyOpeningClosingException;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.DateOperation;
import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
import in.ecgc.smile.erp.accounts.repository.CalendarDao;
import in.ecgc.smile.erp.accounts.repository.FiscalYearDao;

@Service
public class CalendarServiceImpl implements CalendarService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalendarServiceImpl.class);

	@Autowired
	CalendarDao calendarDao;
	
	@Autowired
	FiscalYearDao fiscalYearDao;

	
	@Override
	public Integer addCalendar(Calendar calendar) {
		return calendarDao.addCalendar(calendar);
	}

	@Override
	public Calendar getCalendar(String calendarID) {
		return calendarDao.getCalendar(calendarID);
	}

	@Override
	public List<Calendar> getAllCalendar(String fiscalYr, String month) {
		return calendarDao.getAllCalendar(fiscalYr,month);
	}

	@Override
	public Integer deleteCalendar(String calendarId) {
		return calendarDao.deleteCalendar(calendarId);
	}

	@Override
	public List<Calendar> getByFiscalYr(String fiscalYr, String month, String logicalLocCode) {
		try {
			return calendarDao.getByFiscalYr(fiscalYr, month, logicalLocCode);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public Integer updateStatus1(String first, String status) {
		return calendarDao.updateStatus1(first, status);
	}

	@Override
	public Integer updateStatus2(String second, String status) {
		return calendarDao.updateStatus2(second, status);
	}

	@Override
	public Integer updatePrev(String prevyr, String status) {
		return calendarDao.updatePrev(prevyr, status);
	}

	@Override
	public Integer monthlyOpening(List<String> logicalCode) throws CalendarMonthlyOpeningClosingException {
		try {  
		LocalDateTime now = LocalDateTime.now();
  	    DateOperation dateOperation = new DateOperation(now.getMonthValue());
  	    LOGGER.info(dateOperation.currentMonth);
  	    FiscalYearModel fiscalYr = fiscalYearDao.findCurrentFiscalYear();
  	    LOGGER.info(fiscalYr.toString());
  	    for(String temp : logicalCode) {
  	    	LOGGER.info(temp);
  	    	calendarDao.monthlyOpening(temp,dateOperation.currentMonth,fiscalYr.getCurrFiscalYear());
  	    }
  	    return 1;
  	   }
		catch(Exception e) {
			e.printStackTrace();
			CalendarMonthlyOpeningClosingException clex = new CalendarMonthlyOpeningClosingException("CalendarMonthlyOpeningClosingException");
			throw clex;
		}
	}

	@Override
	public Integer dailyOpeningClosing(List<String> logicalCode) throws CalendarMonthlyOpeningClosingException {
		try {  
			LocalDateTime now = LocalDateTime.now();
	  	    DateOperation dateOperation = new DateOperation(now.getMonthValue());
	  	    LOGGER.info(dateOperation.currentMonth);
	  	    FiscalYearModel fiscalYr = fiscalYearDao.findCurrentFiscalYear();
	  	    LOGGER.info(fiscalYr.toString());
	  	    for(String logCd : logicalCode) {
	  	    	LOGGER.info(logCd);
	  	    	if(dateOperation.prevMonth.equalsIgnoreCase("mar") && logCd.equalsIgnoreCase("HOLOG1")) {
	  	    		calendarDao.dailyOpening(dateOperation.currentMonth,fiscalYr.getCurrFiscalYear(),logCd);
	  	    	}
	  	    	else {
	  	    		calendarDao.dailyClosing(dateOperation.prevMonth,fiscalYr.getCurrFiscalYear(),logCd);
	  	    		calendarDao.dailyOpening(dateOperation.currentMonth,fiscalYr.getCurrFiscalYear(),logCd);
	  	    	}
	  	    }
	  	    return 1;
	  	   }
			catch(Exception e) {
				e.printStackTrace();
				CalendarMonthlyOpeningClosingException clex = new CalendarMonthlyOpeningClosingException("CalendarMonthlyOpeningClosingException");
				throw clex;
			}
	}
	
	@Override
	public void scheduleDemo() {
		System.out.println("Schedule Demo");
	}

	@Override
	public Calendar getByGlTypeLogicalLoc(String fiscalYr, String month, String logicalLocCode, String glTxnType) {
		return calendarDao.getByGlTypeLogicalLoc(fiscalYr, month, logicalLocCode, glTxnType);
	}



}
