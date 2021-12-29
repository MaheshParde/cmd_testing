package in.ecgc.smile.erp.accounts.integrate.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.integrate.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	List<Employee> getEmployeeByOfficeId(String officeId);
	Employee getEmployeeById(Integer empNo);
}
