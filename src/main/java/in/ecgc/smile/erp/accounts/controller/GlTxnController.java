package in.ecgc.smile.erp.accounts.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.exception.GLCodeIncompleteDataException;
import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.service.GlTxnService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/gl-txn")
@Api(value = "GL Transaction service")
public class GlTxnController {

	@Autowired
	GlTxnService txnService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlTxnController.class);

	@GetMapping("/view-all/{logicalloc}")
	@ApiOperation(value = "View all GL codes", response = ResponseEntity.class)
	ResponseEntity<List<GlTxnHdr>> getAllGltxn(@PathVariable("logicalloc") String logicalloc){
		
		ResponseEntity<List<GlTxnHdr>> responseModel ;
		System.err.println("inside controller");
		List<GlTxnHdr> allGlTxn = txnService.getAllGlTxnHdrs(logicalloc);
		if ( allGlTxn != null){
			responseModel = new ResponseEntity<List<GlTxnHdr>>(allGlTxn,HttpStatus.OK);
			System.err.println(allGlTxn);
			LOGGER.info("success: response returning gl txn"); 
			}
		else {
			responseModel = new ResponseEntity<List<GlTxnHdr>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
	return responseModel;
	}
	
	@GetMapping("/view-all/{logicalloc}/{gltxntype}")
	@ApiOperation(value = "View all GL codes", response = ResponseEntity.class)
	ResponseEntity<List<GlTxnHdr>> getAllGltxnByTxnType(
								@PathVariable("logicalloc") String logicalloc,
								@PathVariable("gltxntype") String glTxntype){
		
		ResponseEntity<List<GlTxnHdr>> responseModel ;
		System.err.println("inside controller");
		List<GlTxnHdr> allGlTxn = txnService.getAllGlTxnHdrs(logicalloc,glTxntype);
		if ( allGlTxn != null){
			responseModel = new ResponseEntity<List<GlTxnHdr>>(allGlTxn,HttpStatus.OK);
			System.err.println(allGlTxn);
			LOGGER.info("success: response returning GL txn"); 
		}
		else {
			responseModel = new ResponseEntity<List<GlTxnHdr>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	@GetMapping("/get-txn/{fromDt}/{toDt}")
	@ApiOperation(value = "View all GL codes between dates", response = ResponseEntity.class)
	ResponseEntity<List<GlTxnHdr>> getAllGltxnByFromDt(
			@PathVariable("fromDt") String fromDt,
			@PathVariable("toDt") String toDt) throws GlTxnNotFoundException{
		ResponseEntity<List<GlTxnHdr>> responseModel ;
		System.err.println("inside get-all");
		List<GlTxnHdr> allGlTxn = txnService.getAllGlTxnByFromDtToDt(LocalDate.parse(fromDt),LocalDate.parse(toDt));
		if ( allGlTxn != null){
			responseModel = new ResponseEntity<List<GlTxnHdr>>(allGlTxn,HttpStatus.OK);
			System.err.println(allGlTxn);
			LOGGER.info("success: response returning GL txn"); 
		}
		else {
			responseModel = new ResponseEntity<List<GlTxnHdr>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	@GetMapping("/view/{gltxnno}/{logicalloc}/{gltxntype}")
	@ApiOperation(value = "View all GL codes", response = ResponseEntity.class)
	ResponseEntity<GlTxnHdr> getGltxn(
			@PathVariable("gltxnno") Integer glTxnNo,
			@PathVariable("logicalloc") String logicalLoc,
			@PathVariable("gltxntype") String glTxnType){
		
		ResponseEntity<GlTxnHdr> responseModel ;
		System.err.println("inside controller");
		GlTxnHdr glTxn = txnService.getGlTxn(glTxnNo,logicalLoc,glTxnType);
		if ( glTxn != null){
			responseModel = new ResponseEntity<GlTxnHdr>(glTxn,HttpStatus.OK);
			System.err.println(glTxn);
			try {
				System.err.println(new ObjectMapper().writeValueAsString(glTxn));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LOGGER.info("success: response returning GL txn"); 
		}
		else {
			responseModel = new ResponseEntity<GlTxnHdr>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "Create GL Txn", response = ResponseEntity.class)
	public ResponseEntity<Integer> addGLTxn(@RequestBody GlTxnHdr glTxnHdr) {
		System.err.println("inside add controller");
		ResponseEntity<Integer> model ;
		Integer result;
		try {
			LOGGER.info(glTxnHdr.toString());
			result = txnService.addGlTxn(glTxnHdr);
			
			if(result >=1) {
				model = new ResponseEntity<>(result,HttpStatus.CREATED);
			}else {
				model = new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
			}
		} 
		catch (Exception e) {
			model = new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			LOGGER.error("Exception thrown- GLTxnIncompleteDataException..Incomplete GL Data Data provided");
		}
		return model;
	}
	@PostMapping("/update")
	@ApiOperation(value = "Update GL Txn", response = ResponseModel.class)
	public ResponseEntity<Integer> updateGLTxnDtl(@RequestBody GlTxnDtl glTxnDtl) {
		ResponseEntity<Integer> model ;
		Integer result;
		try {
			result = txnService.updateGlTxnDtl(glTxnDtl);
			if(result >=1) {
				model = new ResponseEntity<>(result,HttpStatus.CREATED);
			}else {
				model = new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
			}
		} 
		catch (Exception e) {
			model = new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return model;
	}
	@PostMapping("/reverse")
	@ApiOperation(value = "GL Txn Reversal", response = ResponseModel.class)
	public ResponseEntity<Integer> reverseTxn(@RequestBody GlTxnHdr glTxnhdr) {
		ResponseEntity<Integer> model ;
		Integer result;
		LOGGER.info("Reversing the transaction......");
		try {
			result = txnService.reverseTransaction(glTxnhdr);
			
			if(result >= 1) {
				model = new ResponseEntity<>(result,HttpStatus.CREATED);
				LOGGER.info("Transaction done -"+result);
			}
			else {
				model = new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
				LOGGER.error("Unable to reverse the transaction ");
			}
		} 
		catch (Exception e) {
			model = new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return model;
	}
}
