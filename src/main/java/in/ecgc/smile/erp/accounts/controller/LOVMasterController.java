package in.ecgc.smile.erp.accounts.controller;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.service.LOVMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/lov-service")
@Api(value = "LOV Service")
public class LOVMasterController {
	
	private static final Logger LOGGER = LoggerFactory.
			getLogger(LOVMasterController.class);
	
	@Autowired(required = true)
	LOVMasterService lovservice;
	
	@ApiOperation(value = "Add LOV entry")
	@PostMapping("/lov")
	public ResponseEntity<Boolean> addLOVMstEntry(@RequestBody LOVMaster lovmst)
	{
		LOGGER.info("addLOVMstEntry:{}",lovmst);
		boolean result= lovservice.addLOVMstEntry(lovmst);
		 return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "View LOV entries", response = List.class)
	@GetMapping("/lov-list")
	public ResponseEntity<List<LOVMaster>> viewAlllovList() {
		List<LOVMaster> lovList = lovservice.viewallLOVMstEntries();
		LOGGER.info("viewAlllovList:{}", lovList);	
		return new ResponseEntity<>(lovList,HttpStatus.OK);
	}
	
	@ApiOperation(value = "View LOV entry")
	@GetMapping("/lov-view/")
	public ResponseEntity<?> viewLov(@RequestParam("lov_cd") String lov_cd) {
		LOGGER.info("Inside BE Controller Lov View Lov by lov cd");
		List<LOVMaster> lovmst = lovservice.findLovByLovcd(lov_cd);
		LOGGER.info("Lov record is " + lovmst);
			return new ResponseEntity<>(lovmst, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get T1 codes")
	@GetMapping("/lov-get/t1Codes")
	public ResponseEntity<List<Map<String, String>>> getT1Codes() {
		LOGGER.info("Inside LOV master controller to get T1 codes");
		List<Map<String, String>> t1Codes = lovservice.t1Codes();
		
		if (t1Codes.size() > 0) {
			LOGGER.info("T1 codes are	 " + t1Codes + "Size : "+t1Codes.size());
			return new ResponseEntity<List<Map<String, String>>>(t1Codes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@ApiOperation(value = "Get T2 codes")
	@GetMapping("/lov-get/t2Codes")
	public ResponseEntity<List<Map<String, String>>> getT2Codes() {
		LOGGER.info("Inside LOV master controller to get T2 codes");
		List<Map<String, String>> t2Codes = lovservice.t2Codes();
		
		if (t2Codes.size() > 0) {
			LOGGER.info("T2 codes are	 " + t2Codes + "Size : "+t2Codes.size());
			return new ResponseEntity<List<Map<String, String>>>(t2Codes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@ApiOperation(value = "find lov")
	@GetMapping("/findlov/{lov_cd}/{lov_value}/{lov_sub_cd}")
	public  ResponseEntity<LOVMaster> findLov(
			@ApiParam (value ="lov_cd", required=true)@PathVariable("lov_cd") @NotBlank String lov_cd,
			@ApiParam(value= "lov_value",  required=true) @PathVariable("lov_value")@NotBlank String lov_value,
			@ApiParam(value = "lov_sub_cd", required = true)@PathVariable("lov_sub_cd")@NotBlank String lov_sub_cd)
	{
	 LOVMaster lovMaster = new LOVMaster();
	 lovMaster = lovservice.findLov(lov_cd, lov_value, lov_sub_cd);
	 if (lovMaster==null) {
		 System.err.println("noe values  founds");
		 
	 }else {
		 System.err.println("lov values are  : "+ lovMaster);
	 }
	 return new ResponseEntity<LOVMaster>(lovMaster, HttpStatus.ACCEPTED);
	}
			

}
