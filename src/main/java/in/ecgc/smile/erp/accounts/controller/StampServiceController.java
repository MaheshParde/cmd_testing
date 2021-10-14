package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.exception.StampIncompleteDataException;
import in.ecgc.smile.erp.accounts.model.StampParameterModel;
import in.ecgc.smile.erp.accounts.service.StampService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * Stamp Parameter
 * 
 * @author Amitesh Banerjee
 * @version 1.0 28-April-2020
 *
 */

@RestController
@RequestMapping("/stamp-parameter")
@Api(value = "Stamp Parameter Service")
public class StampServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StampServiceController.class);
	
	@Autowired
	StampService stampService;
	
	@RequestMapping("/")
	public String test()
	{
		LOGGER.info("INSIDE CONTROLLER");
		return "hello";
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "Add New Stamp Parameter", response = ResponseEntity.class)
	public ResponseEntity<StampParameterModel> addStampParameter(@RequestBody StampParameterModel stampParameter){
		ResponseEntity<StampParameterModel> responseModel;
		LOGGER.info("INSIDE ADD CONTROLLER");
		try {
			
			LOGGER.info(stampParameter.toString());
			if ( stampService.addStampParameter(stampParameter) == 1) {
				responseModel=new ResponseEntity<StampParameterModel>(stampParameter,HttpStatus.CREATED);
				}
			else {
				responseModel=new ResponseEntity<StampParameterModel>(HttpStatus.BAD_REQUEST);
			}
		} catch (StampIncompleteDataException e) {
			responseModel=new ResponseEntity<StampParameterModel>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- StampIncompleteDataException..Incomplete stamp parameter Data provided");
		}
		return responseModel;
	}
	
	@PostMapping("/update/{stampCode}")
	@ApiOperation(value = "Update Stamp Parameter", response = ResponseEntity.class)
	public ResponseEntity<StampParameterModel> updateStampParameter(
			@ApiParam(value = "Stamp Code", required = true)
			@PathVariable("stampCode") Integer stampCode,
			@RequestBody StampParameterModel stampParameterUpdate){
		LOGGER.info("INSIDE UPDATE CONTROLLER");
		StampParameterModel modifiedStamp = stampService.updateStampParameter(stampCode, stampParameterUpdate);
		return new ResponseEntity<StampParameterModel>(modifiedStamp,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewStampParameter")
	@ApiOperation(value = "View all Stamp codes", response = ResponseEntity.class)
	public ResponseEntity<List<StampParameterModel>> allStampParameter(){
		LOGGER.info("--Inside Get all stamp parameter--");
		LOGGER.error("--Fetching Stamp Parameter data--");
		List<StampParameterModel> stampParameter=stampService.allStampParameter();
		LOGGER.info("",stampParameter);
		if(stampParameter!=null)
		{
			return new ResponseEntity<>(stampParameter, HttpStatus.OK);
		}else
		{
			return new ResponseEntity<List<StampParameterModel>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/view/{stampCode}")
	  
	  @ApiOperation(value = "View Stamp Parameter by Stamp Code", response =
	  ResponseEntity.class) 
	  public ResponseEntity<StampParameterModel> viewByStampCode(
	  @ApiParam(value = "Stamp Code", required = true)
	  @PathVariable("stampCode") Integer stampCode
	  ) throws StampIncompleteDataException{
	  	StampParameterModel stampModel = stampService.viewByStampCode(stampCode);
		LOGGER.info(stampModel.toString());
		return new ResponseEntity<>(stampModel, HttpStatus.OK); 
	  }
	
	@GetMapping("/view-stamp-amt/{receiptAmount}")
	@ApiOperation(value = "get stamp Amount by from and to amount", response = ResponseEntity.class)
	public ResponseEntity<Integer>getStampAmtByFromAndToAmt(
			@ApiParam(value = "receiptAmount", required = true)@PathVariable("receiptAmount") @NotBlank Integer receiptAmount)          

			//@ApiParam(value = "fromAmount", required = true)@PathVariable("fromAmount") @NotBlank Integer fromAmount,          
			//@ApiParam(value = "toAmount", required = true)@PathVariable("toAmount") @NotBlank Integer toAmount)
	{
		Integer	stampAmt=  stampService.getStampAmtByFromAndToAmt(receiptAmount);
		return new ResponseEntity<>(stampAmt, HttpStatus.OK);
	}
	
	

}
