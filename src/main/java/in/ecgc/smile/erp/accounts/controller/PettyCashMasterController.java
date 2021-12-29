package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.exception.GLCodeNotFoundException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.PettyCashMaster;
import in.ecgc.smile.erp.accounts.model.Receipt;
import in.ecgc.smile.erp.accounts.service.EntityGLMasterService;
import in.ecgc.smile.erp.accounts.service.PettyCashMasterService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/petty-cash")
@Api(value = "petty cash master service")
public class PettyCashMasterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PettyCashMasterController.class);
	
	@Autowired
	PettyCashMasterService pettyCashMasterService;
	
	@PostMapping("/add")
	@ApiOperation(value = "Add petty cash details", response = ResponseModel.class)
	public ResponseEntity<Integer> addPettyCashDetails(@RequestBody @Valid PettyCashMaster pettyCashMaster) {
		ResponseEntity<Integer> model ;
					LOGGER.info(pettyCashMaster.toString());
			
			if ( pettyCashMasterService.addPettyCashDetails(pettyCashMaster) >= 1) {
				System.err.println("pettyCashMaster "+pettyCashMaster);
				model = new ResponseEntity<>(pettyCashMaster.getRequisitionNo(),HttpStatus.CREATED);
				LOGGER.info("Data Successsfully added");
				}
			else {
				model = new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
				LOGGER.error("failed to insert data");
			}
		
		return model;
	}
	
	@GetMapping("/view-all")
	@ApiOperation(value = "View all Cash details" ,response = ResponseEntity.class)
	public ResponseEntity<List<PettyCashMaster>> listAllPettyCashDetails(){
		ResponseEntity<List<PettyCashMaster>> model;
		List<PettyCashMaster> list = null ;
		try {
			LOGGER.info("--insede get all PettyCash--");
			if ((list = pettyCashMasterService.listAllPettyCashMaster()) != null) {
				model = new ResponseEntity<List<PettyCashMaster>>(list,HttpStatus.OK);
				LOGGER.debug("--Petty Cash Master Service Controller--");
				}
			else {
				model = new ResponseEntity<List<PettyCashMaster>>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			model = new ResponseEntity<List<PettyCashMaster>>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- Petty Cash ..failed to fetch",e);
		}
		return model;
	}
	
	@GetMapping(value = "/view/{requisitionNo}/{logicalLocCode}")
	@ApiOperation(value = "View details By requisition no", response = ResponseEntity.class)
	public ResponseEntity<PettyCashMaster> findByRequisitionNo(
			@ApiParam(value = "requisitionNo" ,required = true)
	@PathVariable("requisitionNo")Integer requisitionNo,
	@ApiParam(value = "logicalLocCode" ,required = true)
			@PathVariable("logicalLocCode")String logicalLocCode)
	{
		PettyCashMaster pettyCashMaster = pettyCashMasterService.findByRequisitionNo(requisitionNo,logicalLocCode);
		
		LOGGER.info("",pettyCashMaster);
		if(pettyCashMaster != null)
			return new ResponseEntity<PettyCashMaster>(pettyCashMaster , HttpStatus.OK);
		else
			return new ResponseEntity<PettyCashMaster>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/get-pid/{requisitionNo}/{logicalLocCode}")
	@ApiOperation(value = "Get PID", response = ResponseEntity.class)
	public ResponseEntity<Long> getProcessInstanceId(
			@RequestParam("requisitionNo") Integer requisitionNo,
			@RequestParam("logicalLocCode") String logicalLocCode){
		ResponseEntity<Long> responseEntity = null;
		try {
			Long pid = pettyCashMasterService.getProcessInstanceId(requisitionNo,logicalLocCode);
			responseEntity = new ResponseEntity<Long>(pid, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error in getProcessInstanceId", e.fillInStackTrace());
			responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return responseEntity;
	}
	
	@PutMapping(value = "/update-pid/{requisitionNo}/{logicalLocCode}/{pid}")
	@ApiOperation(value = "Get PID", response = ResponseEntity.class)
	public ResponseEntity<Integer> updateProcessInstanceId(
			@RequestParam("requisitionNo") Integer requisitionNo,
			@RequestParam("logicalLocCode") String logicalLocCode,
			@RequestParam("pid") Long pid ){
		ResponseEntity<Integer> responseEntity = null;
		try {
			Integer res = pettyCashMasterService.updateProcessInstanceId(requisitionNo, logicalLocCode, pid);
			responseEntity = new ResponseEntity<Integer>(res,HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error in updateProcessInstanceId", e.fillInStackTrace());
			responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	@PutMapping("/approve/{requisitionNo}/{logicalLocCode}")
	@ApiOperation(value = "approved status", response = ResponseModel.class)
	public ResponseEntity<Integer> approvedStatus(@PathVariable("requisitionNo") Integer requisitionNo, 
												@PathVariable("logicalLocCode") String logicalLocCode ) {
		ResponseEntity<Integer> responseModel; 
		try {
			PettyCashMaster pettyCashMaster = pettyCashMasterService.findByRequisitionNo(requisitionNo,logicalLocCode);
			System.err.println("pettyCashMaster :=" +pettyCashMaster);
				//System.err.println(pettyCashMaster.getReqStatus()); 
				
			if (pettyCashMaster.getReqStatus().equals("Requested")) {
				//System.err.println("in if true");
				pettyCashMasterService.approvedStatus(pettyCashMaster)	;
				responseModel = new ResponseEntity<Integer>(1,HttpStatus.OK);
				LOGGER.info("Request status is approved"); 
				}
			else {
				responseModel= new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
				LOGGER.info("failed to approved status");
			}		
		}
		catch (Exception e)
		{
			responseModel = new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
			LOGGER.error(e.getMessage());
		}
		return responseModel;
	}
	
	@PutMapping(value ="/update/{requisitionNo}/{logicalLocCode}")
	@ApiOperation(value = "update pettyCash" , response = ResponseEntity.class)
	public ResponseEntity<Integer> updatePettyCash(
			@ApiParam(value = "requisitionNo" , required = true)			
			@PathVariable("requisitionNo")@NotBlank Integer requisitionNo,
			@ApiParam(value = "logicalLocCode" , required = true)
			@PathVariable("logicalLocCode")@NotBlank String logicalLocCode,
			@RequestBody PettyCashMaster pettyCashUpdate)
	{
		Integer result = pettyCashMasterService.updatePettyCash(requisitionNo, logicalLocCode, pettyCashUpdate);
		return new ResponseEntity<Integer> (result ,HttpStatus.OK);
	}
}