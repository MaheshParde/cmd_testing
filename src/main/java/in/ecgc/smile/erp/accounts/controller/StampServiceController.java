package in.ecgc.smile.erp.accounts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.exception.StampIncompleteDataException;
import in.ecgc.smile.erp.accounts.model.StampParameterModel;
import in.ecgc.smile.erp.accounts.service.StampService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Stamp Parameter
 * 
 * @author Amitesh Banerjee
 * @version 1.0 28-April-2020
 *
 */

@Slf4j
@RestController
@RequestMapping("/stamp-parameter")
@Api(value = "Stamp Parameter Service")
public class StampServiceController {
	
	
	@Autowired
	StampService stampService;
	
	@PostMapping("/add")
	@ApiOperation(value = "Add New Stamp Parameter", response = ResponseEntity.class)
	public ResponseEntity<StampParameterModel> addStampParameter(@Valid @RequestBody StampParameterModel stampParameter){
		log.info("INSIDE STAMP PARAMETER ADD CONTROLLER");
		Integer res = stampService.addStampParameter(stampParameter);
			if ( res == 1) {
				return new ResponseEntity<StampParameterModel>(stampParameter,HttpStatus.CREATED);
			}
		return null;
	}
	
	@PostMapping("/update/{stampCode}")
	@ApiOperation(value = "Update Stamp Parameter", response = ResponseEntity.class)
	public ResponseEntity<StampParameterModel> updateStampParameter(
			@ApiParam(value = "Stamp Code", required = true)
			@PathVariable("stampCode") @NotBlank Integer stampCode,
			@Valid
			@RequestBody StampParameterModel stampParameterUpdate){
		log.info("INSIDE STAMP PARAMETER UPDATE CONTROLLER");
		StampParameterModel modifiedStamp = stampService.updateStampParameter(stampCode, stampParameterUpdate);
		return new ResponseEntity<StampParameterModel>(modifiedStamp,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewStampParameter")
	@ApiOperation(value = "View all Stamp codes", response = ResponseEntity.class)
	public ResponseEntity<List<StampParameterModel>> allStampParameter(){
		log.info("INSIDE GET ALL STAMP PARAMETER CONTROLLER");
	//	log.error("--Fetching Stamp Parameter data--");
		List<StampParameterModel> stampParameterlist = new ArrayList<>();
		stampParameterlist = stampService.allStampParameter();
		log.info("Stamp Parameter list return by controller {}",stampParameterlist);
		return new ResponseEntity<>(stampParameterlist, HttpStatus.OK);
	}
	
	@GetMapping("/view/{stampCode}")
	@ApiOperation(value = "View Stamp Parameter by Stamp Code", response = ResponseEntity.class) 
	  public ResponseEntity<StampParameterModel> viewByStampCode(
	  @ApiParam(value = "Stamp Code", required = true)
	  @PathVariable("stampCode") @NotBlank Integer stampCode){
		log.info("INSIDE GET STAMP PARAMETER BY STAMPCODE CONTROLLER");
	  	StampParameterModel stampModel = stampService.viewByStampCode(stampCode);
		log.info("Stamp Parameter return by controller {}",stampModel.toString());
		return new ResponseEntity<>(stampModel, HttpStatus.OK); 
	  }
	
	@GetMapping("/view-stamp-amt/{receiptAmount}")
	@ApiOperation(value = "get stamp Amount by from and to amount", response = ResponseEntity.class)
	public ResponseEntity<Integer>getStampAmtByFromAndToAmt(
			@ApiParam(value = "receiptAmount", required = true)@PathVariable("receiptAmount") @NotBlank Integer receiptAmount)          
	{
		log.info("INSIDE GET STAMP AMOUNT BY RECEIPT AMOUNT CONTROLLER");
		Integer	stampAmt=  stampService.getStampAmtByFromAndToAmt(receiptAmount);
		return new ResponseEntity<>(stampAmt, HttpStatus.OK);
	}
	
	

}
