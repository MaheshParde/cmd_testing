package in.ecgc.smile.erp.accounts.service;

import java.time.LocalDateTime;
import java.util.List ;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.integrate.model.OfficeMaster;
import in.ecgc.smile.erp.accounts.integrate.service.OrgStructService;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.DateOperation;
import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
import in.ecgc.smile.erp.accounts.repository.CalendarDao;
import in.ecgc.smile.erp.accounts.repository.FiscalYearDao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalendarServiceImpl implements CalendarService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalendarServiceImpl.class);

	@Autowired
	CalendarDao calendarDao;
	
	@Autowired
	FiscalYearDao fiscalYearDao;

	@Autowired
	OrgStructService orgStructService;
	
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
			return calendarDao.getByFiscalYr(fiscalYr, month, logicalLocCode);
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
	public Integer monthlyOpeningClosingRegular() {
			
		LOGGER.info("Monthly opening and closing activity for all branches and HO except PV, JV and IB");
		
		List<OfficeMaster> officeList = orgStructService.getOfficeList();
		List<String> logicalLocList  = officeList.stream()
										.map(OfficeMaster::getOfficeId)
										.collect(Collectors.toList());
		log.info("Logical location list : {}",logicalLocList);
		
		LocalDateTime now = LocalDateTime.now();
			//LocalDateTime now = LocalDateTime.of(2021, 12, 1, 11, 50);
  	    DateOperation dateOperation = new DateOperation(now.getMonthValue());
  	   
  	    FiscalYearModel fiscalYr = fiscalYearDao.findCurrentFiscalYear();
  	    LOGGER.info("Current month : {} and Previous month : {} and Fiscal yr : {}", dateOperation.currentMonth, dateOperation.prevMonth,fiscalYr.toString());
  	    for(String logicalLoc : logicalLocList) {
  	    	LOGGER.info(logicalLoc);
  	    	if(dateOperation.prevMonth.equalsIgnoreCase("mar") ) {
  	    		LOGGER.info("Previous month is march so only opening activity will happen!");
  	    		calendarDao.monthlyOpeningRegular(logicalLoc,dateOperation.currentMonth,fiscalYr.getCurrFiscalYear());
  	    	}
  	    	else {
  	    		LOGGER.info("Previous month is {} so both opening and closing activity will happen!",dateOperation.prevMonth);
	  	    	calendarDao.monthlyOpeningRegular(logicalLoc,dateOperation.currentMonth,fiscalYr.getCurrFiscalYear());
	  	    	calendarDao.monthlyClosingRegular(logicalLoc,dateOperation.prevMonth, fiscalYr.getCurrFiscalYear());
	  	    	}
  	    }
  	    return 1;
	}

	
	
	@Override
	public Integer monthlyOpeningClosingConfigured()
	{
		LOGGER.info("Monthly opening and closing activity for all branches and HO except PV, JV and IB");
		
		List<OfficeMaster> officeList = orgStructService.getOfficeList();
		List<String> logicalLocList  = officeList.stream()
										.map(OfficeMaster::getOfficeId)
										.collect(Collectors.toList());
		log.info("Logical location list : {}",logicalLocList);

		
		LocalDateTime now = LocalDateTime.now();
			//LocalDateTime now = LocalDateTime.of(2021, 4, 1, 11, 50);
  	    DateOperation dateOperation = new DateOperation(now.getMonthValue());
  	   
  	    FiscalYearModel fiscalYr = fiscalYearDao.findCurrentFiscalYear();
  	    LOGGER.info("Current month : {} and Previous month : {} and Fiscal yr : {}", dateOperation.currentMonth, dateOperation.prevMonth,fiscalYr.toString());
  	    for(String logicalLoc : logicalLocList) {
  	    	LOGGER.info(logicalLoc);
  	    	if(dateOperation.prevMonth.equalsIgnoreCase("mar") ) {
  	    		LOGGER.info("Previous month is march so only opening activity will happen!");
  	    		calendarDao.monthlyOpeningConfigured(logicalLoc,dateOperation.currentMonth,fiscalYr.getCurrFiscalYear());
  	    	}
  	    	else {
  	    		LOGGER.info("Previous month is {} so both opening and closing activity will happen!",dateOperation.prevMonth);
	  	    	calendarDao.monthlyOpeningConfigured(logicalLoc,dateOperation.currentMonth,fiscalYr.getCurrFiscalYear());
	  	    	calendarDao.monthlyClosingConfigured(logicalLoc,dateOperation.prevMonth, fiscalYr.getCurrFiscalYear());
	  	    	}
  	    }
  	    return 1;

	}
	
	@Override
	public Integer dailyOpeningClosing(List<String> logicalCode) {
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
	
	@Override
	public void scheduleDemo() {
		System.out.println("Schedule Demo");
	}

	@Override
	public Calendar getByGlTypeLogicalLoc(String fiscalYr, String month, String logicalLocCode, String glTxnType) {
		return calendarDao.getByGlTypeLogicalLoc(fiscalYr, month, logicalLocCode, glTxnType);
	}

	
	

	@Override
	public Integer marchClosing(String logicalCode, String currentFiscalyr) {
		LOGGER.info("Inside CalendarMasterService#marchClosing ");
	 return	calendarDao.marchClosing(logicalCode, currentFiscalyr);
				
		
	}

	@Override
	public Boolean marchClosingStatus(String logicalCode, String currentFiscalyr) {
		LOGGER.info("Inside CalendarMasterService#marchClosingStatus ");
		 return	calendarDao.marchClosingStatus(logicalCode, currentFiscalyr);
	}




}
