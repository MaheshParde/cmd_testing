package in.ecgc.smile.erp.accounts.controller;

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

import in.ecgc.smile.erp.accounts.model.AutoGLTxnRequestModel;
import in.ecgc.smile.erp.accounts.model.AutoGLTxnRequestModel;
import in.ecgc.smile.erp.accounts.model.AutoGLTxnRequestModel;
import in.ecgc.smile.erp.accounts.model.GLTxnType;
import in.ecgc.smile.erp.accounts.service.AutoGLTxnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auto-gl-txn")
@Api(value = "Automatic GL Transaction service")
public class AutoGLTxnController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoGLTxnController.class);
	
	@Autowired
	AutoGLTxnService autoGLTxnService;
	
	@PostMapping("/add-request")
	@ApiOperation(value = "Add Auto GL txn request", response = ResponseEntity.class)
	public ResponseEntity<Integer> addGlTxnRequest(@Valid @RequestBody AutoGLTxnRequestModel autoGLTxnRequestModel) {

		
		LOGGER.info(autoGLTxnRequestModel.toString());
		Integer result;
		try {
			result = autoGLTxnService.createTxnRequest(autoGLTxnRequestModel);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/get-all-req")
	@ApiOperation(value = "Get all Auto GL Transaction Request", response = ResponseEntity.class)
	public ResponseEntity<List<AutoGLTxnRequestModel>> getAllAutoGLTxnRequest() {
		ResponseEntity<List<AutoGLTxnRequestModel>> model;
		List<AutoGLTxnRequestModel> allAutoGLTxnRequestModel = null ;
		try {
			LOGGER.info("--insede get all AutoGLTxnRequestModel--");
			if ((allAutoGLTxnRequestModel = autoGLTxnService.getAllAutoTxnRequest()) != null) {
				model = new ResponseEntity<List<AutoGLTxnRequestModel>>(allAutoGLTxnRequestModel,HttpStatus.OK);
				LOGGER.debug("--AutoGLTxnRequestModelMasterServiceController--");
				}
			else {
				model = new ResponseEntity<List<AutoGLTxnRequestModel>>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			model = new ResponseEntity<List<AutoGLTxnRequestModel>>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- AutoGLTxnRequestException ..failed to fetch",e);
		}
		return model;
	}
	
	@GetMapping("/get-req/{reqNo}")
	@ApiOperation(value = "Get AutoGLTxnRequest Details", response = ResponseEntity.class)
	public ResponseEntity<AutoGLTxnRequestModel> getAutoGLTxnRequestById(@PathVariable("reqNo") String reqNo) {
		ResponseEntity<AutoGLTxnRequestModel> model;
		AutoGLTxnRequestModel autoGLTxnRequestModelDtl = null;
		try {
			LOGGER.info("--insede get AutoGLTxnRequestModel--");
			if ((autoGLTxnRequestModelDtl = autoGLTxnService.getAllAutoTxnRequestById(reqNo)) != null) {
				model = new ResponseEntity<AutoGLTxnRequestModel>(autoGLTxnRequestModelDtl,HttpStatus.OK);
				LOGGER.debug("--AutoGLTxnRequestServiceController--");
				}
			else {
				model = new ResponseEntity<AutoGLTxnRequestModel>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			model = new ResponseEntity<AutoGLTxnRequestModel>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- AutoGLTxnRequestModelException ..failed to fetch");
			LOGGER.error(e.getMessage());
		}
		return model;


	}
	@PostMapping("/update-request-status")
	@ApiOperation(value = "Update Auto GL txn request status", response = ResponseEntity.class)
	public ResponseEntity<Integer> updateGlTxnRequestStatus(@Valid @RequestBody AutoGLTxnRequestModel autoGLTxnRequestModel) {

		
		LOGGER.info(autoGLTxnRequestModel.toString());
		Integer result;
		try {
			result = autoGLTxnService.updateRequestStatus(autoGLTxnRequestModel.getRequestNo().toString(),autoGLTxnRequestModel.getReqStatus());
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
		}
		
	}


}
