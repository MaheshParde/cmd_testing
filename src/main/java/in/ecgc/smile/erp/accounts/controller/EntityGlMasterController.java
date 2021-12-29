package in.ecgc.smile.erp.accounts.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;
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
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;
import in.ecgc.smile.erp.accounts.service.EntityGLMasterService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/gl-master")
@Api(value = "GL Master service")
@Slf4j

public class EntityGlMasterController {

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
		log.info("Controller: Adding new GL Code {}",entityGL);
		return new ResponseEntity<>(entityGLMasterService.addGLCode(entityGL),HttpStatus.CREATED);
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
												@PathVariable("mainGLCode") @Size(min = 4, max = 4, message = "Main GL Should be of 4 Characters") String mainGLCode, 
												@PathVariable("subGLCode") @Size(min = 3, max = 3, message = "Sub GL Should be of 3 Characters") String subGLCode, 
												@RequestBody EntityGL updatedEntityGL ) {
		log.info("Controller: Update GL Code for Main GL {} Sub GL {}", mainGLCode,subGLCode);
		EntityGL currentEntityGL = entityGLMasterService.findGLByGLCode(mainGLCode,subGLCode);
		EntityGL modifiedEntity = entityGLMasterService.updateGLCode(currentEntityGL, updatedEntityGL);
		return new ResponseEntity<>(modifiedEntity,HttpStatus.ACCEPTED);	
	}

	/**
	 * View all GL codes
	 * return List of all GL codes present in the GL master table
	 * @return {@link in.ecgc.smile.erp.accounts.util} ResponseModel Class Object
	 */
	@GetMapping("/view-all")
	@ApiOperation(value = "View all GL codes", response = ResponseModel.class)
	public ResponseEntity<List<EntityGL>> listAllGLCodes() {
		log.info("Controller: Reading all GL Codes");
		return new ResponseEntity<>(entityGLMasterService.listAllGLCodes(),HttpStatus.OK);
	}
	/**
	 * disable given GL code
	 * @PathVariable entityGLCode which is to be deleted
	 * return entityGLCode which is deleted
	 * @return {@link in.ecgc.smile.erp.accounts.util} ResponseModel Class Object
	 */
	@DeleteMapping("/delete/{mainGLCode}/{subGLCode}")
	@ApiOperation(value = "Delete given GL code", response = ResponseModel.class)
	public ResponseEntity<Integer> disableGLCode(@PathVariable("mainGLCode") @Size(min = 4, max = 4, message = "Main GL Should be of 4 Characters") String mainGLCode, 
												@PathVariable("subGLCode") @Size(min = 3, max = 3, message = "Sub GL Should be of 3 Characters") String subGLCode ) {
		log.info("Controller: Disable GL Code for Main GL {} Sub GL {}", mainGLCode,subGLCode);
		EntityGL entityGL = entityGLMasterService.findGLByGLCode(mainGLCode,subGLCode);
		return new ResponseEntity<>(entityGLMasterService.disableGLCode(entityGL),HttpStatus.OK);
	}
	
	/**
	 * View GL code details by entityGLCode
	 * return details of GL code present in the GL master table
	 * @return {@link in.ecgc.smile.erp.accounts.util} ResponseModel Class Object
	 */
	@GetMapping("/view/{mainGLCode}/{subGLCode}")
	@ApiOperation(value = "View  by  GL code", response = ResponseModel.class)
	public ResponseEntity<EntityGL> findGLByEntityGLCode(@PathVariable("mainGLCode")  @Size(min = 4, max = 4, message = "Main GL Should be of 4 Characters") String mainGLCode,
												@PathVariable("subGLCode")  @Size(min = 3, max = 3, message = "Sub GL Should be of 3 Characters") String  subGLCode) {
		log.info("Controller: Read GL Code for Main GL {} Sub GL {}", mainGLCode,subGLCode);
		return new ResponseEntity<>(entityGLMasterService.findGLByGLCode(mainGLCode,subGLCode),HttpStatus.OK);
	}
	
	

	@GetMapping("/get-all-by-mainGLCode/{mainGlCode}")
	@ApiOperation(value = "View all GL codes", response = ResponseModel.class)
	public ResponseEntity<List<EntityGL>> getAllMainGLCodes(@PathVariable("mainGlCode") String mainGlCode ) {
		log.info("Controller: Reading all GL Codes");
		return new ResponseEntity<>(entityGLMasterService.getsubGLCodebyMainGLCode(mainGlCode),HttpStatus.OK);
	}
	
	@GetMapping("/view-all-isglname")
	@ApiOperation(value = "View all GL codes", response = ResponseModel.class)
	public ResponseEntity<List<EntityGL>> listAllGLbyIsGlName() {
		log.info("Controller: Reading all GL Codes");
		return new ResponseEntity<>(entityGLMasterService.getAllGlByIsGlGroup(),HttpStatus.OK);
	}
	
	
}


