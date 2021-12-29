package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import in.ecgc.smile.erp.accounts.exception.ImproperFtrDataProvidedException;
import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.service.FTRService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/ftr")
@Api(value = "ftr service")
public class FTRController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FTRController.class);
	@Autowired
	FTRService FTRService; 
	
	
	@PostMapping("/add")
	@ApiOperation(value = "Add New FTR", response = ResponseEntity.class)
	public ResponseEntity<Integer> addFTR(@RequestBody FTR ftr ){
		ResponseEntity<Integer> model;
		
		LOGGER.info("--insede add FTR--",ftr);
		try {
			LOGGER.info(ftr.toString());
			int reqNo=0;
			if ((reqNo = FTRService.addRequest(ftr)) > 0) {
				model = new ResponseEntity<Integer>(reqNo ,HttpStatus.ACCEPTED);
				LOGGER.debug("FTRController");
				}
			else {
				model = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {
			model = new ResponseEntity<Integer>(HttpStatus.BAD_GATEWAY);
			e.printStackTrace();
			LOGGER.error("Exception thrown- FTRException ..Incomplete FTR Data provided");
		}
		return model;
	}
	
	@GetMapping("/getftr/{ftrReqNo}")
	@ApiOperation(value = "Get FTR Details", response = ResponseEntity.class)
	public ResponseEntity<FTR> getFtrDtl(@PathVariable("ftrReqNo") String FTRId) {
		ResponseEntity<FTR> model;
		FTR ftrDtl = null;
		try {
			LOGGER.info("--insede get FTR--");
			if ((ftrDtl = FTRService.getFTRRequestDTL(FTRId)) != null) {
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

	@GetMapping("/getallFTR")
	@ApiOperation(value = "Get all FTR", response = ResponseEntity.class)
	public ResponseEntity<List<FTR>> getAllFTR() {
		ResponseEntity<List<FTR>> model;
		List<FTR> allFTR =null ;
		try {
			LOGGER.info("--insede get all FTR--");
			
			if ((allFTR = FTRService.getAllFTRRequest())!= null) {
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
	
	@GetMapping("/get-pending")
	@ApiOperation(value = "Get all pending FTR", response = ResponseEntity.class)
	public ResponseEntity<List<FTR>> getAllPendingFTR() {
		ResponseEntity<List<FTR>> model;
		List<FTR> allFTR = null ;
		try {
			LOGGER.info("--insede get all FTR--");
			if ((allFTR = FTRService.getAllPendingFTRRequest()) != null) {
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
	
	@GetMapping("/get-approved")
	@ApiOperation(value = "Get all approved FTR", response = ResponseEntity.class)
	public ResponseEntity<List<FTR>> getAllApprovedFTR() {
		ResponseEntity<List<FTR>> model;
		List<FTR> allFTR = null ;
		try {
			LOGGER.info("--insede get all approved FTR--");
			if ((allFTR = FTRService.getAllApprovedFTRRequest()) != null) {
				model = new ResponseEntity<List<FTR>>(allFTR,HttpStatus.OK);
				LOGGER.debug("--FTRMasterServiceController--");
				}
			else {
				model = new ResponseEntity<List<FTR>>(HttpStatus.NOT_FOUND);
				LOGGER.info("--no approved FTR found--");
			}
		}
		catch (Exception e) {
			model = new ResponseEntity<List<FTR>>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRCodeException ..failed to fetch",e);
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
			if ((allFTR = FTRService.getAllFTRRequest(logicalLoc)) != null) {
				model = new ResponseEntity<List<FTR>>(allFTR,HttpStatus.OK);
				LOGGER.debug("--FTRMasterServiceController--");
				LOGGER.info(allFTR.toString());
				
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
	
	@PostMapping("/decision")
	@ApiOperation(value = "Decision on FTR Request", response = ResponseEntity.class)
	public ResponseEntity<Integer> decisionOnFtr(@RequestBody FTR ftr ){
		ResponseEntity<Integer> model;
		LOGGER.info("inside decision FTR");
		try {
			LOGGER.info(ftr.toString());
			if ( FTRService.decisionOnFTRRequest(ftr) == 1) {
				model = new ResponseEntity<Integer>(1,HttpStatus.ACCEPTED);
				LOGGER.debug("FTRController");
				}
			else {
				model= new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {  
			model= new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRException ..Incomplete FTR Data provided",e);
		}
		return model;
	}
	
	@PostMapping("/mul-decision")
	@ApiOperation(value = "Decision on Multiple FTR Request", response = ResponseEntity.class)
	public ResponseEntity<Integer> decisionOnMultipleFtr(@RequestBody List<FTR> ftrs ){
		ResponseEntity<Integer> model;
		try {
			LOGGER.info(ftrs.toString());
			if ( FTRService.decisionOnMultipleFTRRequest(ftrs) >= 1) {
				model = new ResponseEntity<Integer>(1,HttpStatus.ACCEPTED);
				LOGGER.debug("FTRController");
				}
			else {
				model= new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {  
			model= new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRException ..Incomplete FTR Data provided",e);
		}
		return model;
	}
		
	
	@PostMapping("/update")
	@ApiOperation(value = "Update FTR Request", response = ResponseEntity.class)
	public ResponseEntity<Integer> updateFtr(@RequestBody FTR ftr ){
		ResponseEntity<Integer> model;
		try {
			LOGGER.info(ftr.toString());
			if ( FTRService.updateFTRRequest(ftr) == 1) {
				model = new ResponseEntity<Integer>(1,HttpStatus.ACCEPTED);
				LOGGER.debug("FTRController");
				}
			else {
				model= new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {  
			model= new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRException ..Incomplete FTR Data provided",e);
		}
		return model;
	}
	
	@DeleteMapping("/delete/{ftrReqNo}")
	@ApiOperation(value = "Delete FTR Request", response = ResponseEntity.class)
	public ResponseEntity<Integer> deleteFtr(@ApiParam(value = "Fund Transfer Request", required = true)
												@PathVariable("ftrReqNo") String ftrReqNo ) {
		 
			Integer result = FTRService.deleteFTRRequest(Integer.parseInt(ftrReqNo));
			
			return new ResponseEntity<Integer>(result, HttpStatus.ACCEPTED);

	}
	@PostMapping("/generate-excel/{bank}/{ftrCds}")
	@ApiOperation(value = "Generate excel for FTR Request", response = ResponseEntity.class)
	public ResponseEntity<Integer> generateExcelForFtr(@PathVariable("bank") String bank , @PathVariable("ftrCds") String[] ftrCds ){
		ResponseEntity<Integer> model;
		try {
			LOGGER.info("generate-excel");
			if ( FTRService.generateExcelForFTRRequest(ftrCds, bank) >= 1) {
				model = new ResponseEntity<Integer>(1,HttpStatus.ACCEPTED);
				LOGGER.debug("FTRController");
				}
			else {
				model= new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {  
			model= new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRException ..Something Went Wrong",e);
		}
		return model;
	}
	
	@PostMapping("/update-trf-status/{ftrReqNo}")
	@ApiOperation(value = "Update FTR Transfer Status", response = ResponseEntity.class)
	public ResponseEntity<Integer> updateFtrTrfStatus(@RequestBody GlTxnHdr gltxn,@PathVariable("ftrReqNo")String ftrReqNo ){
		ResponseEntity<Integer> model;
		try {
			LOGGER.info(gltxn.toString());
			if (1==1) {
				FTR ftr = new FTR();
				ftr.setFtrReqNo(Integer.parseInt(ftrReqNo));
				ftr.setFtrReqStatus("T");
				ftr.setFtrTrfDt(gltxn.getTxnDt());
				Double trfAmt = 0.0;
				for(GlTxnDtl temp : gltxn.getGlTxnDtls()) {
					if(temp.getDrCrFlag().equalsIgnoreCase("dr"))
						trfAmt += temp.getTxnAmt();
				}
				ftr.setFtrTrfAmt(trfAmt);
				if(FTRService.changeStatusToTransfer(ftr) >= 1) {
					model = new ResponseEntity<Integer>(1,HttpStatus.ACCEPTED);
					LOGGER.debug("FTRController");	
				}
			else {
				model= new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
			}
			}
			else {
				model= new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
			}
		}
		catch(ImproperFtrDataProvidedException e) {
			model= new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRException .."+ e.getMessage());
		}
		catch (Exception e) {  
			model= new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- FTRException ..Something Went wrong");
		}
		return model;
	}
	
	
	
}
