package in.ecgc.smile.erp.accounts.integrate.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.integrate.model.ExternalAgency;
import in.ecgc.smile.erp.accounts.integrate.service.CUDService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/cud/mem-subscr/")
@Api(value = "Agency details from CUD membership subscription service")
public class CUDController {

	@Autowired
	CUDService cudService;
	
	@GetMapping(value = "/get-agency-list")
	@ApiOperation(value = "Get All Agency List", response = ResponseEntity.class)
	ResponseEntity<List<ExternalAgency>> getAgencyList(){
		return new ResponseEntity<>(cudService.getExternalAgencyList(),HttpStatus.OK);
	}
	
}
