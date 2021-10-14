package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import javax.validation.Valid;

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
				model = new ResponseEntity<>(1,HttpStatus.CREATED);
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

	@PutMapping("/approve/{requisitionNo}/{logicalLocCode}")
	@ApiOperation(value = "approved status", response = ResponseModel.class)
	public ResponseEntity<Integer> approvedStatus(@PathVariable("requisitionNo") Integer requisitionNo, 
												@PathVariable("logicalLocCode") String logicalLocCode ) {
		ResponseEntity<Integer> responseModel; 
		try {
			PettyCashMaster pettyCashMaster = pettyCashMasterService.findByRequisitionNo(requisitionNo,logicalLocCode);
			System.err.println("pettyCashMaster :=" +pettyCashMaster);
						
			if ( pettyCashMasterService.approvedStatus(pettyCashMaster) >= 1) {
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
}
