package in.ecgc.smile.erp.accounts.integrate.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.integrate.model.Employee;
import in.ecgc.smile.erp.accounts.integrate.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/employee")
@Api(value = "Employee details from HRD")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@GetMapping(value = "/get-employee-list")
	@ApiOperation(value = "Get All Employee List", response = ResponseEntity.class)
	ResponseEntity<List<Employee>> getEmployeeList(){
		List<Employee> employeeList = new ArrayList<Employee>();
		
		try {
			employeeList = empService.getAllEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-employee-by-office-id/{officeId}")
	@ApiOperation(value = "Get All Employee List", response = ResponseEntity.class)
	ResponseEntity<List<Employee>> getEmployeeByOfficeId(@RequestParam("officeId") String officeId){
		List<Employee> employeeList = new ArrayList<Employee>();
		
		try {
			employeeList = empService.getEmployeeByOfficeId(officeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-employee-by-id/{empNo}")
	@ApiOperation(value = "Get All Employee List", response = ResponseEntity.class)
	ResponseEntity<Employee> getEmployeeById(@RequestParam("empNo") Integer empNo){
		Employee employee = new Employee();
		
		try {
			employee = empService.getEmployeeById(empNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	
}
