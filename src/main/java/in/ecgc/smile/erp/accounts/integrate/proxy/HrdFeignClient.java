package in.ecgc.smile.erp.accounts.integrate.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import in.ecgc.smile.erp.accounts.integrate.model.Employee;


//@FeignClient(name = "erp-hrd-emp-mgmt-be",url="http://k8master0.ecgcindia.com:31920", contextId = "HrdFeignClient", fallbackFactory = HrdFeignClientFallback.class)
//@FeignClient(name = "${api.gateway}",url="http://10.210.0.140:31335", contextId = "HrdFeignClient", fallbackFactory = HrdFeignClientFallback.class)
//@FeignClient(name = "${apigw.service}", contextId = "HrdFeignClient", fallbackFactory = HrdFeignClientFallback.class)
@FeignClient(name = "erp-hrd-emp-mgmt-be",url="http://kmaster.cdacmumbai.in:31920/", contextId = "HrdFeignClient", fallbackFactory = HrdFeignClientFallback.class)
//@FeignClient(name = "erp-hrd-emp-mgmt-be", contextId = "HrdFeignClient", fallbackFactory = HrdFeignClientFallback.class)
public interface HrdFeignClient {

	
	//@GetMapping("/hrd-profile/list/employees")
	@GetMapping("/hrd-profile/list/employees")
	public List<Employee> getAllEmployees();

	
	//@GetMapping("/hrd-profile/logloc-employee/{officeId}")
	@GetMapping("/hrd-profile/logloc-employee/{officeId}")
	public List<Employee> getEmployeeByOfficeId(@PathVariable("officeId") String officeId);
	
	
	//@GetMapping("/hrd-profile/employees/{empNo}")
	@GetMapping("/hrd-profile/employees/{empNo}")
	public Employee getEmployeeById(@PathVariable("empNo") Integer empNo);
}
