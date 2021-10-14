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

import in.ecgc.smile.erp.accounts.exception.GLCodeIncompleteDataException;
import in.ecgc.smile.erp.accounts.exception.GLCodeNotFoundException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.service.EntityGLMasterService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/gl-master")
@Api(value = "GL Master service")
public class EntityGlMasterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityGlMasterController.class);

	@Autowired
	EntityGLMasterService entityGLMasterService;

	/**
	 * Add new GL code
	 * 
	 * @RequestBody {@link in.ecgc.smile.erp.accounts.model.EntityGL} Model Object
	 * @return {@link in.ecgc.smile.erp.accounts.util} ResponseModel Class Object
	 */
	@PostMapping("/add")
	@ApiOperation(value = "Add New GL code", response = ResponseModel.class)
	public ResponseEntity<Integer> addGLCode(@RequestBody @Valid EntityGL entityGL) {
		ResponseEntity<Integer> model ;
		try {
			LOGGER.info(entityGL.toString());
			if ( entityGLMasterService.addGLCode(entityGL) == 1) {
				model = new ResponseEntity<>(1,HttpStatus.CREATED);
				LOGGER.debug("GL CODE ADDED");
				}
			else {
				model = new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
				LOGGER.error("failed to insert new GL code");
			}
		} catch (GLCodeIncompleteDataException e) {
			model = new ResponseEntity(0,HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			LOGGER.error("Exception thrown- GLCodeIncompleteDataException..Incomplete GL code Data provided");
		}
		return model;
	}
	
	/**
	 * Update GL code
	 * @PathVariable entityGLCode which is to be updated
	 * @RequestBody entity GL model which modified properties
	 * @return {@link in.ecgc.smile.erp.accounts.util} ResponseModel Class Object
	 */
	@PutMapping("/update/{mainGLCode}/{subGLCode}")
	@ApiOperation(value = "Update GL code", response = ResponseModel.class)
	public ResponseEntity<EntityGL> updateGLCode(
												@PathVariable("mainGLCode") String mainGLCode, 
												@PathVariable("subGLCode") String subGLCode, 
												@RequestBody EntityGL updatedEntityGL ) {
		
		ResponseEntity<EntityGL> responseModel;
		
		try {
			EntityGL currentEntityGL = entityGLMasterService.findGLByGLCode(mainGLCode,subGLCode);
			LOGGER.info("Updated details of GL code = " + mainGLCode+ subGLCode + "are : " + updatedEntityGL.toString());
			EntityGL modifiedEntity = entityGLMasterService.updateGLCode(currentEntityGL, updatedEntityGL);
			
			if ( modifiedEntity != null) {
				responseModel = new ResponseEntity<EntityGL>(modifiedEntity,HttpStatus.ACCEPTED);
				LOGGER.info("GL code is updated"); 
				}
			else {
				responseModel =new ResponseEntity<EntityGL>(HttpStatus.BAD_REQUEST);
				LOGGER.info("failed to update GL code");
			}
			
		}
		catch (GLCodeNotFoundException e)
		{
			responseModel = new ResponseEntity<EntityGL>(HttpStatus.BAD_REQUEST);
			LOGGER.error(e.getMessage());
			LOGGER.error("Exception thrown- GLCodeNotFoundException..service layer operation-entityGLMasterService.findGLByEntityGLCode()");
		}
		return responseModel;
	}

	/**
	 * View all GL codes
	 * return List of all GL codes present in the GL master table
	 * @return {@link in.ecgc.smile.erp.accounts.util} ResponseModel Class Object
	 */
	@GetMapping("/view-all")
	@ApiOperation(value = "View all GL codes", response = ResponseModel.class)
	public ResponseEntity<List<EntityGL>> listAllGLCodes() {
		ResponseEntity<List<EntityGL>> responseModel ;
			List<EntityGL> allGLCodes = entityGLMasterService.listAllGLCodes();
			if ( allGLCodes.isEmpty() == false ){
				responseModel = new ResponseEntity<List<EntityGL>>(allGLCodes,HttpStatus.OK);
				LOGGER.info("success: response returning all GL codes"); 
				}
			else {
				responseModel = new ResponseEntity<List<EntityGL>>(HttpStatus.BAD_REQUEST);
				LOGGER.info("No entries found");
			}
		return responseModel;
	}
	/**
	 * disable given GL code
	 * @PathVariable entityGLCode which is to be deleted
	 * return entityGLCode which is deleted
	 * @return {@link in.ecgc.smile.erp.accounts.util} ResponseModel Class Object
	 */
	@DeleteMapping("/delete/{mainGLCode}/{subGLCode}")
	@ApiOperation(value = "Delete given GL code", response = ResponseModel.class)
	public ResponseEntity<Integer> disableGLCode(@PathVariable("mainGLCode") String mainGLCode, 
												@PathVariable("subGLCode") String subGLCode ) {
		ResponseEntity<Integer> responseModel; 
		try {
			EntityGL entityGL = entityGLMasterService.findGLByGLCode(mainGLCode,subGLCode);
			LOGGER.info("Details of GL code = " + mainGLCode+subGLCode + "are : " + entityGL.toString());
			
			if ( entityGLMasterService.disableGLCode(entityGL) == 1) {
				responseModel = new ResponseEntity<Integer>(1,HttpStatus.OK);
				LOGGER.info("GL code is disabled"); 
				}
			else {
				responseModel= new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
				LOGGER.info("failed to disable GL code");
			}		
		}
		catch (GLCodeNotFoundException e)
		{
			responseModel = new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
			LOGGER.error(e.getMessage());
			LOGGER.error("Exception thrown- GLCodeNotFoundException..service layer operation#disableGLCode-EntityGLMasterService.findGLByEntityGLCode()");
		}
		return responseModel;
	}
	
	/**
	 * View GL code details by entityGLCode
	 * return details of GL code present in the GL master table
	 * @return {@link in.ecgc.smile.erp.accounts.util} ResponseModel Class Object
	 */
	@GetMapping("/view/{mainGLCode}/{subGLCode}")
	@ApiOperation(value = "View  by  GL code", response = ResponseModel.class)
	public ResponseEntity<EntityGL> findGLByEntityGLCode(@PathVariable("mainGLCode") String mainGLCode,
												@PathVariable("subGLCode") String  subGLCode) {
		ResponseEntity<EntityGL> responseModel;
		try {
				responseModel = new ResponseEntity<EntityGL>(entityGLMasterService.findGLByGLCode(mainGLCode,subGLCode),HttpStatus.OK);
				//responseModel = new ResponseEntity<EntityGL>(entityGLMasterService.findGLByGLCode(mainGLCode,subGLCode),HttpStatus.OK);
				LOGGER.info("success: response returning details of GL code"); 
			}
		catch (GLCodeNotFoundException e) {
			responseModel = new ResponseEntity<EntityGL>(HttpStatus.BAD_REQUEST);
			LOGGER.error(e.getMessage());
			LOGGER.error("Exception thrown- GLCodeNotFoundException..service layer operation#findGLByEntityGLCodes-EntityGLMasterService.findGLByEntityGLCode()");
		}
		return responseModel;
	}
	@GetMapping("/get-sub-bifurcations/{mainGLCode}/{subGLCode}")
	@ApiOperation(value = "View  by  GL code", response = ResponseModel.class)
	public ResponseEntity<List<String>> findSubBifurcations(@PathVariable("mainGLCode") String mainGLCode,
			@PathVariable("subGLCode") String  subGLCode) {
			ResponseEntity<List<String>> responseModel;
		try {
			responseModel = new ResponseEntity<List<String>>(entityGLMasterService.findSubBifurcations(mainGLCode,subGLCode),HttpStatus.OK);
			//responseModel = new ResponseEntity<EntityGL>(entityGLMasterService.findGLByGLCode(mainGLCode,subGLCode),HttpStatus.OK);
			LOGGER.info("success: response returning details of GL code"); 
		}
		catch (GLCodeNotFoundException e) {
			responseModel = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			LOGGER.error(e.getMessage());
			LOGGER.error("Exception thrown- GLCodeNotFoundException..service layer operation#findGLByEntityGLCodes-EntityGLMasterService.findGLByEntityGLCode()");
		}
		catch (Exception e) {
			responseModel = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			LOGGER.error(e.getMessage());
			LOGGER.error("Exception thrown- GLCodeNotFoundException..service layer operation#findGLByEntityGLCodes-EntityGLMasterService.findGLByEntityGLCode()");// TODO: handle exception
		}
		return responseModel;
	}

}


