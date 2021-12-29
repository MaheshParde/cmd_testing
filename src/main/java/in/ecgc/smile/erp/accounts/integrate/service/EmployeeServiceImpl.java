package in.ecgc.smile.erp.accounts.integrate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.integrate.model.Employee;
import in.ecgc.smile.erp.accounts.integrate.proxy.HrdFeignClient;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private HrdFeignClient empFeignClient;
	
	@Override
	public List<Employee> getAllEmployees() {
		return empFeignClient.getAllEmployees();
	}

	@Override
	public List<Employee> getEmployeeByOfficeId(String officeId) {
		return empFeignClient.getEmployeeByOfficeId(officeId);
	}

	@Override
	public Employee getEmployeeById(Integer empNo) {
		return empFeignClient.getEmployeeById(empNo);
	}

}
