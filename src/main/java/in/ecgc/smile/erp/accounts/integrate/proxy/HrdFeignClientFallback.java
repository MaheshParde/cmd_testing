package in.ecgc.smile.erp.accounts.integrate.proxy;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import in.ecgc.smile.erp.accounts.integrate.model.Employee;

@Component
public class HrdFeignClientFallback implements FallbackFactory<HrdFeignClient>{

	private static final Logger logger = LoggerFactory.getLogger(HrdFeignClientFallback.class);

	@Override
	public HrdFeignClient create(Throwable cause) {
		logger.error("hrd emp mgmt service failed");
		return new HrdFeignClient() {
			
			@Override
			public List<Employee> getEmployeeByOfficeId(String officeId) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Employee getEmployeeById(Integer empNo) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<Employee> getAllEmployees() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	

}
