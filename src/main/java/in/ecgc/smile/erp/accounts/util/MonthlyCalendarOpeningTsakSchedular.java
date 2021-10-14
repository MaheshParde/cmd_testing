package in.ecgc.smile.erp.accounts.util;

import java.util.List;
import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import in.ecgc.smile.erp.accounts.exception.CalendarMonthlyOpeningClosingException;
import in.ecgc.smile.erp.accounts.service.CalendarService;



public class MonthlyCalendarOpeningTsakSchedular {
	
	@Autowired
	CalendarService calendarService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(MonthlyCalendarOpeningTsakSchedular.class);

	//    @Scheduled(cron = "0 * * * * ?")
	    @Scheduled(cron = "${calendar.monthlySchedule}")
	    public void monthlyOpening() throws CalendarMonthlyOpeningClosingException{
	    	Thread t = new Thread(new Runnable() {
				
	    		
	    		 
				@Override
				public void run() {
					try {
		        		  List<String> logicalLoc = new ArrayList<String>();
		        		  logicalLoc.add("HOLOG1");
		        		  logicalLoc.add("MUMBAILOG1");
		        		  logicalLoc.add("THANELOG1");
		        		  logicalLoc.add("SURATLOG1");
		            	 calendarService.monthlyOpening(logicalLoc);
		            	 LOGGER.debug("MonthlyOpeningCalendar");
		        		 }
					
					  catch(CalendarMonthlyOpeningClosingException exception) {
					  LOGGER.error(exception.getMessage()); }
					 
		        		 catch(Exception e) {
		        			 LOGGER.error(e.getMessage());
		        			 e.printStackTrace();
		        		 }

				}
			});
	    	LOGGER.info("Before-MonthlyOpeningCalendar");
	    	t.start();
	    }
	    
	    @Scheduled(cron = "${calendar.dailySchedule}")
	    public void dailyCalendarOpeningClosing() throws CalendarMonthlyOpeningClosingException{
	    	Thread t = new Thread(new Runnable() {
				
	    		
	    		 
				@Override
				public void run() {
					try {
		        		  List<String> logicalLoc = new ArrayList<String>();
		        		  logicalLoc.add("HOLOG1");
		        		  logicalLoc.add("MUMBAILOG1");
		        		  logicalLoc.add("THANELOG1");
		        		  logicalLoc.add("SURATLOG1");
		            	 calendarService.dailyOpeningClosing(logicalLoc);
		            	 LOGGER.debug("DailyCalendarOpeningCalendar");
		        		 }
					
					  catch(CalendarMonthlyOpeningClosingException exception) {
					  LOGGER.error(exception.getMessage()); }
					 
		        		 catch(Exception e) {
		        			 LOGGER.error(e.getMessage());
		        			 e.printStackTrace();
		        		 }

				}
			});
	    	LOGGER.info("Before-DailyCalendarOpeningCalendar");
	    	t.start();
	    }

	    }

