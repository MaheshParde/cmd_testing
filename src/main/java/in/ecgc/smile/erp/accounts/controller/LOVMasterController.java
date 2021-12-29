package in.ecgc.smile.erp.accounts.controller;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.service.LOVMasterService;

import in.ecgc.smile.erp.sys.auth.be.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/lov-service")
@Api(value = "LOV Service")
@Slf4j
public class LOVMasterController {
	
	@Autowired(required = true)
	LOVMasterService lovService;

	/*
	 * @Autowired(required = true) HRDService hrdService;
	 */
	
	@Autowired 
	private UserInfoService userInfo;
	
	@GetMapping("/user-info")
	public void userInfoDetails()
	{
		
		  log.info("ApplicableDepartmentForSession {}{}: "+userInfo.
		  getApplicableDepartmentForSession());
		  log.info("ApplicableDesignationForSession {}{}: "+userInfo.
		  getApplicableDesignationForSession());
		  log.info("ApplicableOfficeTypeForSession {}: "+userInfo.
		  getApplicableOfficeTypeForSession());
		  log.info("ApplicableRankForSession {}: "+userInfo.
		  getApplicableRankForSession());
		  log.info("AZP {}: "+userInfo.getAZP());
		  log.info("Email {}: "+userInfo.getEmail());
		  log.info("EmployeeId {}: "+userInfo.getEmployeeId());
		  log.info("Name {}: "+userInfo.getName());
		  log.info("PrimaryDepartment {}: "+userInfo.getPrimaryDepartment());
		  log.info("PrimaryDesignation {}: "+userInfo.getPrimaryDesignation());
		  log.info("PrimaryOffice {}: "+userInfo.getPrimaryOffice());
		  log.info("PrimaryOfficeType {}: "+userInfo.getPrimaryOfficeType());
		  log.info("PrimaryRank {}: "+userInfo.getPrimaryRank());
		  log.info("User {}: "+userInfo.getUser());
		  log.info("CurrentAdditionalChargeList {}: "+userInfo.
		  getCurrentAdditionalChargeList());
		  
	}
	
	@ApiOperation(value = "Add LOV entry")
	@PostMapping("/add")
	public ResponseEntity<Integer> addLOVMstEntry(@RequestBody LOVMaster lovmst) {
		System.err.println(lovmst);
		int result = 0;
		try {
			result = lovService.addLovMstEntry(lovmst);
		} catch (DataIntegrityViolationException e) {
			e.getMessage();
			result = -1;
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiOperation(value = "View LOV entries", response = List.class)
	@GetMapping("/list")
	public ResponseEntity<List<LOVMaster>> viewAlllovList() {
		ResponseEntity<List<LOVMaster>> responseEntity = null;
		
		try {
			List<LOVMaster> lovList = lovService.viewallLOVMstEntries();
			responseEntity = new ResponseEntity<>(lovList, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in viewAlllovList", e.fillInStackTrace());
			responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@ApiOperation(value = "View LOV entry")
	@GetMapping("/view/{lovCd}/{lovSubCd}/{lovValue}")
	public ResponseEntity<LOVMaster> viewLovEntry(@PathVariable("lovCd") String lovCd, @PathVariable("lovSubCd") String lovSubCd, @PathVariable("lovValue") String lovValue) {
		ResponseEntity<LOVMaster> responseEntity = null;
		try {
			LOVMaster lov = lovService.viewLovEntry(lovCd, lovSubCd, lovValue);
			responseEntity = new ResponseEntity<>( lov, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in viewLovEntry", e.fillInStackTrace());
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "Get T1 codes")
	@GetMapping("/lov-get/t1Codes")
	public ResponseEntity<Map<String, String>> getT1Codes() {
		log.info("Inside LOV master controller to get T1 codes");
			return new ResponseEntity<Map<String, String>>(lovService.t1Codes(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get T2 codes")
	@GetMapping("/lov-get/t2Codes")
	public ResponseEntity<Map<String, String>> getT2Codes() {
		log.info("Inside LOV master controller to get T2 codes");
			return new ResponseEntity<Map<String, String>>(lovService.t2Codes(), HttpStatus.OK);
	}


	@ApiOperation(value = "Modify LOV entry")
	@PutMapping("/modify")
	public ResponseEntity<Integer> modifyLovEntry(@RequestBody LOVMaster lov) {
		ResponseEntity<Integer> responseEntity = null;
		try {
			int result = lovService.modifyLovEntry(lov);
			responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in modifyLovEntry", e.fillInStackTrace());
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@ApiOperation(value = "Disable LOV entry")
	@PutMapping("/disable/{lovCd}/{lovSubCd}/{lovValue}")
	public ResponseEntity<Integer> disableLovEntry(@PathVariable("lovCd") String lovCd, @PathVariable("lovSubCd") String lovSubCd, @PathVariable("lovValue") String lovValue) {
		ResponseEntity<Integer> responseEntity = null;
		try {
			Integer result = lovService.disableLovEntry(lovCd, lovSubCd, lovValue);
			responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in disableLovEntry", e.fillInStackTrace());
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@ApiOperation(value = "Get Payment advice Reference Number")
	@GetMapping("/get-ref-no")
	public ResponseEntity<Integer> getRefNo() {
		ResponseEntity<Integer> responseEntity = null;
		try {
			Integer result = lovService.getRefNo();
			responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in getRefNo", e.fillInStackTrace());
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		return responseEntity;
	}

	
	@ApiOperation(value = "Add Payment advice no")
	@PutMapping("/save-payment-advice-no/{refNo}/{paymentAdviceNo}")
	public ResponseEntity<Integer> savePaymentAdvice(@PathVariable("refNo") Integer refNo,@PathVariable("paymentAdviceNo") Integer paymentAdviceNo) {
		Integer result = lovService.setPaymentAdviceNo(refNo,paymentAdviceNo);
		Integer paymentAdvice = null;
		if(result==1) {
			paymentAdvice = paymentAdviceNo;
			return new ResponseEntity<>(paymentAdvice, HttpStatus.OK);
		}else
			return new ResponseEntity<>(-1, HttpStatus.EXPECTATION_FAILED);	
	}	

}
