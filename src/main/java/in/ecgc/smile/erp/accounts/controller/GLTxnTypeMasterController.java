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
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.model.GLTxnType;
import in.ecgc.smile.erp.accounts.service.GLTxnTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/gl-txn-type-master")
@Api(value = "GL Transaction type Master service")
public class GLTxnTypeMasterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GLTxnTypeMasterController.class);

	@Autowired
	GLTxnTypeService glTxnTypeService;

	/**
	 * Add new GL transaction type code
	 * 
	 * @RequestBody {@link in.ecgc.smile.erp.accounts.model.GLTxnType} Model Object
	 */
	@PostMapping()
	@ApiOperation(value = "Add New GL txn type code", response = ResponseEntity.class)
	public ResponseEntity<GLTxnType> addGlTxnTypeCode(@Valid @RequestBody GLTxnType glTxnType) {

		
		LOGGER.info(glTxnType.toString());
		glTxnTypeService.addGLTxnTypeCode(glTxnType);
		return new ResponseEntity<>(glTxnType, HttpStatus.CREATED);
	}

	/**
	 * View GL transaction type code details by glTxnType return details of GL
	 * transaction type code present in the GL transaction type master table
	 */
	@GetMapping("/{glTxnTypeCode}")
	@ApiOperation(value = "View GL transaction type code by GL Transaction type code", response = ResponseEntity.class)
	public ResponseEntity<GLTxnType> findGlTxnTypeByGlTxnTypeCode(
			@ApiParam(value = "GL transaction type Code", required = true) 
			@PathVariable("glTxnTypeCode")@NotBlank @Size(max = 2) String glTxnTypeCode) {
		GLTxnType glTxnType = glTxnTypeService.findGlTxnTypeByGlTxnTypeCode(glTxnTypeCode);
		LOGGER.info(glTxnType.toString());
		return new ResponseEntity<>(glTxnType, HttpStatus.OK);
	}
	/**
	 * View all GL transaction types codes
	 * return List of all GL transaction types codes present in the GL txn type master table
	 */
	@GetMapping()
	@ApiOperation(value = "View all GL transaction type codes", response = ResponseEntity.class)
	public ResponseEntity<List<GLTxnType>> listAllGlTxnTypeCodes() {
		List<GLTxnType> allGlTxnTypeCodes = glTxnTypeService.listAllGLTxnTypeCodes();
		if (allGlTxnTypeCodes != null)
			return new ResponseEntity<>(allGlTxnTypeCodes, HttpStatus.OK);
		else 
			return new ResponseEntity<List<GLTxnType>>(HttpStatus.NO_CONTENT);
	}
	/**
	 * Update GL transaction type code
	 * @PathVariable glTxnTypeCode which is to be updated
	 * @RequestBody GLTxnType model which modified properties
	 */
	@PutMapping("/{glTxnTypeCode}")
	@ApiOperation(value = "Update GL transaction type code", response = ResponseEntity.class)
	public ResponseEntity<Integer> updateGLTxnTypeCode(@ApiParam(value = "GL transaction type Code", required = true)
							@PathVariable("glTxnTypeCode") @NotBlank @Size(max = 3) String glTxnTypeCode, 
							@RequestBody GLTxnType updatedGlTxnType ) {
				
			Integer result = glTxnTypeService.updateGLTxnTypeCode(glTxnTypeCode,updatedGlTxnType);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	/**
	 * disable given GL transaction type code
	 * @PathVariable glTxnTypeCode which is to be deleted
	 */
	@DeleteMapping("/{glTxnTypeCode}")
	@ApiOperation(value = "Delete given GL transaction type code", response = ResponseEntity.class)
	public ResponseEntity<Integer> disableGlTxnTypeCode(@ApiParam(value = "GL transaction type Code", required = true)
												@PathVariable("glTxnTypeCode") String glTxnTypeCode ) {
		 
			Integer result = glTxnTypeService.disableGLTxnTypeCode(glTxnTypeCode);
			return new ResponseEntity<Integer>(result, HttpStatus.ACCEPTED);

	}
	
	
	@GetMapping("/gltype")
	@ApiOperation(value = "Get list of glTxn Type", response = ResponseEntity.class)
	public ResponseEntity<List<String>> getGLTypeList() {
		List<String> glTxnType = glTxnTypeService.getGLTxnType();
		return new ResponseEntity<>(glTxnType, HttpStatus.OK);
	}

	
	
}
