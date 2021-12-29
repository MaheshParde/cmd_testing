package in.ecgc.smile.erp.accounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import in.ecgc.smile.erp.accounts.util.MonthlyCalendarOpeningTsakSchedular;

@Configuration
//@EnableScheduling
public class ScheduleConfig {

	
	  @Bean public MonthlyCalendarOpeningTsakSchedular getScheduler() { return new
	  MonthlyCalendarOpeningTsakSchedular(); }
	 
}
