package in.ecgc.smile.erp.accounts;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import in.ecgc.smile.erp.accounts.util.MonthlyCalendarOpeningTsakSchedular;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"in"})
@EnableFeignClients
@EnableSwagger2
@EnableAspectJAutoProxy // To enable LoggingAspect
public class AccountsServiceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceBackendApplication.class, args);
		
	}
}
