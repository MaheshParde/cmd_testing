package in.ecgc.smile.erp.accounts.integrate.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.integrate.model.DepartmentMaster;
import in.ecgc.smile.erp.accounts.integrate.model.OfficeMaster;
import in.ecgc.smile.erp.accounts.integrate.model.StateMaster;
import in.ecgc.smile.erp.accounts.integrate.proxy.OrgStructClient;
import in.ecgc.smile.erp.accounts.integrate.service.OrgStructService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/org-struct")
@RequiredArgsConstructor
@Api(value = "API's from ORG-STRUCT")
public class OrgStructController {

	private final OrgStructClient orgStructClient;
	private final OrgStructService orgStructService;

	@GetMapping(value = "/states")
	public ResponseEntity<List<String>> getStatesList() {

		List<String> listOfStates = orgStructClient.getState().stream().map(StateMaster::getStateId)
				.collect(Collectors.toList());
		listOfStates.sort((string1, string2) -> string1.compareTo(string2));
		return new ResponseEntity<>(listOfStates, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-office-details")
	public ResponseEntity<List<OfficeMaster>> getOfficeDetails() {

		System.err.println("----In side getOfficeDetails method----");
		List<OfficeMaster> allOfficeDtls = orgStructService.getOfficeList();
		System.err.println("**** "+allOfficeDtls);
		if (allOfficeDtls != null) {
			return new ResponseEntity<>(allOfficeDtls, HttpStatus.OK);
		}else {
			 return new ResponseEntity<>(allOfficeDtls,HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/get-dept-details")
	public ResponseEntity<List<DepartmentMaster>> getAllDeptDetails() {

		List<DepartmentMaster> allDeptDtls = orgStructService.getDeptDetails();
		if (allDeptDtls != null) {
			return new ResponseEntity<>(allDeptDtls, HttpStatus.OK);
		}else {
			 return new ResponseEntity<>(allDeptDtls,HttpStatus.NO_CONTENT);
		}
	}
}