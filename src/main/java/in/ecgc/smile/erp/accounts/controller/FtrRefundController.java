package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

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

import in.ecgc.smile.erp.accounts.exception.ImproperFtrDataProvidedException;
import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.service.FtrRefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ftr-refund")
@Api(value = "Fund transfer refund service")
public class FtrRefundController {
	
	@Autowired
	FtrRefundService ftrRefundService; 

	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FtrRefundController.class);

	@PostMapping("/add")
	@ApiOperation(value = "Add New FTR refund request", response = ResponseEntity.class)
	public ResponseEntity<Integer> addRefundRequest(@RequestBody FTR ftr ){
		ResponseEntity<Integer> model;
		LOGGER.info("--insede add FTR--",ftr);
		try {
			LOGGER.info(ftr.toString());	
			int reqNo=0;
			if ((reqNo = ftrRefundService.addRefundRequest(ftr)) > 0) {
				model = new ResponseEntity<Integer>(reqNo ,HttpStatus.ACCEPTED);
				LOGGER.debug("FtrRefundController");
				}
			else {
				model = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {
			model = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRException ..Incomplete FTR Data provided",e);
		}
		return model;
	}
		
	@GetMapping("/getallFTR/{logicalLoc}")
	@ApiOperation(value = "Get all FTR", response = ResponseEntity.class)
	public ResponseEntity<List<FTR>> getAllFTRForBranch(@PathVariable("logicalLoc") String logicalLoc) {
		LOGGER.info("get branch wise request");
		ResponseEntity<List<FTR>> model;
		List<FTR> allFTR = null ;
		try {
			LOGGER.info("--insede get all FTR--");
			if ((allFTR = ftrRefundService.getAllFTRRequest(logicalLoc)) != null) {
				model = new ResponseEntity<List<FTR>>(allFTR,HttpStatus.OK);
				LOGGER.debug("--FTRMasterServiceController--");
				}
			else {
				model = new ResponseEntity<List<FTR>>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			model = new ResponseEntity<List<FTR>>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRCodeException ..failed to fetch",e);
		}
		return model;
	}

	@GetMapping("/getftr/{ftrReqNo}")
	@ApiOperation(value = "Get FTR refund Details", response = ResponseEntity.class)
	public ResponseEntity<FTR> getFtrDtl(@PathVariable("ftrReqNo") String FTRId) {
		ResponseEntity<FTR> model;
		FTR ftrDtl = null;
		try {
			LOGGER.info("--insede get FTR--");
			if ((ftrDtl = ftrRefundService.getFTRRequestDTL(FTRId)) != null) {
				model = new ResponseEntity<FTR>(ftrDtl,HttpStatus.OK);
				LOGGER.debug("--FTRServiceController--");
				}
			else {
				model = new ResponseEntity<FTR>(HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {
			model = new ResponseEntity<FTR>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRException ..failed to fetch");
			LOGGER.error(e.getMessage());
		}
		return model;


	}
	
	@PostMapping("/decision")
	@ApiOperation(value = "Decision on FTR refung Request", response = ResponseEntity.class)
	public ResponseEntity<Integer> decisionOnFtrRefund(@RequestBody FTR ftr ){
		ResponseEntity<Integer> model;
		//("inside decision FTR");
		try {
			LOGGER.info(ftr.toString());
			if ( ftrRefundService.decisionOnFTRRequest(ftr) == 1) {
				model = new ResponseEntity<Integer>(1,HttpStatus.ACCEPTED);
				LOGGER.debug("FtrRefundController");
				}
			else {
				model= new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
			}
		}
		catch (ImproperFtrDataProvidedException e) {  
			model= new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRException ..Incomplete FTR Data provided");
			e.printStackTrace();
		}
		catch (Exception e) {  
			model= new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRException ..Incomplete FTR Data provided");
			e.printStackTrace();
		}
		return model;
	}
	
	
}
