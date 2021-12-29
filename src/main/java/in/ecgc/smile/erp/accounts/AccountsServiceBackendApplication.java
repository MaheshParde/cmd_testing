package in.ecgc.smile.erp.accounts;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"in"})
@ConfigurationPropertiesScan(basePackages = {"in.ecgc.smile.erp"})
@EnableFeignClients
@EnableSwagger2
@EnableAspectJAutoProxy // To enable LoggingAspect
public class AccountsServiceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceBackendApplication.class, args);
		
	}
}
