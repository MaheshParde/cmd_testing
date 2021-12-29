package in.ecgc.smile.erp.accounts.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import in.ecgc.smile.erp.accounts.exception.CalendarMonthlyOpeningClosingException;
import in.ecgc.smile.erp.accounts.integrate.model.OfficeMaster;
import in.ecgc.smile.erp.accounts.integrate.service.OrgStructService;
import in.ecgc.smile.erp.accounts.service.CalendarService;
import in.ecgc.smile.erp.accounts.service.EntityGLMasterService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MonthlyCalendarOpeningTsakSchedular {
	
	@Autowired
	CalendarService calendarService;
	
	@Autowired
	EntityGLMasterService entityGLMasterService;
	
	@Autowired
	OrgStructService orgStructService;

	
	    //@Scheduled(cron="0 * * * * *") // Every day every minute
	    //@Scheduled(cron="0 0 8 * * *") //Every day at 8 am
	   //@Scheduled(cron = "${calendar.monthlySchedule}")
	    public void monthlyOpeningClosingRegular() throws InterruptedException {
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    	LocalDateTime now = LocalDateTime.now();  

	    	log.info("MonthlyCalendarOpeningTsakSchedular#monthlyOpeningClosingRegular called at : "+ dtf.format(now));

			
			  Thread t1 = new Thread(new Runnable() {
			  @Override public void run() {
					List<OfficeMaster> officeList = orgStructService.getOfficeList();
					List<String> logicalLocList  = officeList.stream()
													.map(OfficeMaster::getOfficeId)
													.collect(Collectors.toList());
					log.info("Logical location list : {}",logicalLocList);

			  } }); 
			  
			  Thread t2 = new Thread(new Runnable() {
				  @Override public void run() {
						Integer count = entityGLMasterService.getRegularGLTypesByOpeningDay();
						
						if(count!=0) {
			            	 calendarService.monthlyOpeningClosingRegular();
						}else {
							log.error("No calendar to be opened today");
						}

				  } }); 
			  
			 t1.start();
			  	t1.join();
		  	 if(!t1.isAlive()) {
		            //t2.start();// if t1 is finished then t2 will start
		        }	
	    }//end
	    
	    
	    
	    
	    //@Scheduled(cron="0 * * * * *") // Every day every minute
	    //@Scheduled(cron="0 0 8 * * *") //Every day at 8 am
	    //@Scheduled(cron = "${calendar.dailySchedule}")
	    public void monthlyOpeningClosingConfigured() {
	    	   
			/*
			 * Thread t = new Thread(new Runnable() {
			 * 
			 * @Override public void run() { } });
			 * log.info("Before-MonthlyOpeningCalendar"); t.start();
			 */	    	
	    	
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    	LocalDateTime now = LocalDateTime.now();  
	    	
	    	log.info("MonthlyCalendarOpeningTsakSchedular#monthlyOpeningClosingConfigured called at : "+ dtf.format(now));
			
			Integer count = entityGLMasterService.getConfiguredGLTypesByOpeningDay();					
			
			if(count==0) {
            	 calendarService.monthlyOpeningClosingConfigured();
			}else {
				log.error("No calendar to be opened today");
			}

	    }

	}

