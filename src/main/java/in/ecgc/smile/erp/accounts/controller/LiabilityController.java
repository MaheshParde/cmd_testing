/**
 * 
 */
package in.ecgc.smile.erp.accounts.controller;

import java.time.LocalDate;
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

import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.exception.TCodeNotPresentException;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.model.Liability;
import in.ecgc.smile.erp.accounts.model.LiabilityGLMapping;
import in.ecgc.smile.erp.accounts.service.LiabilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author CMJ-SENG-LAPTOP9
 *
 */

@RestController
@RequestMapping("/liability")
@Api(value = "Liability service")

public class LiabilityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LiabilityController.class);

	@Autowired
	LiabilityService liabilityService;
	
	@PostMapping("/add")
	@ApiOperation(value = "Create liability", response = ResponseEntity.class)
	public ResponseEntity<GlTxnHdr> createLiability(@RequestBody Liability liability) {
		System.err.println("inside add controller");
		ResponseEntity<GlTxnHdr> model ;
		GlTxnHdr result;
		try {
			LOGGER.info(liability.toString());
			result = liabilityService.createLiability(liability);
			if(result != null) {
				model = new ResponseEntity<>(result,HttpStatus.CREATED);
			}else {
				model = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} 
		catch (Exception e) {
			model = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			LOGGER.error("Exception thrown- GLTxnIncompleteDataException..Incomplete GL Data Data provided");
		}
		return model;
	
}
	@GetMapping("/get-mapping/{moduleCd}/{mappingCd}")
	@ApiOperation(value = "View mapping for module", response = ResponseEntity.class)
	ResponseEntity<List<LiabilityGLMapping>> getAllGltxnByFromDt(
			@PathVariable("moduleCd") String moduleCd,
			@PathVariable("mappingCd") String mappingCd) throws GlTxnNotFoundException{
		ResponseEntity<List<LiabilityGLMapping>> responseModel ;
		System.err.println("inside get-all");
		List<LiabilityGLMapping> mapping = liabilityService.getMapping(moduleCd, mappingCd);
		if ( mapping != null){
			responseModel = new ResponseEntity<List<LiabilityGLMapping>>(mapping,HttpStatus.OK);
			LOGGER.info("success: response returning Liability mapping"); 
		}
		else {
			responseModel = new ResponseEntity<List<LiabilityGLMapping>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	@GetMapping("/get-mapping/{moduleCd}")
	@ApiOperation(value = "View mapping for module", response = ResponseEntity.class)
	ResponseEntity<List<LiabilityGLMapping>> getAllGltxnByFromDt(
			@PathVariable("moduleCd") String moduleCd) throws GlTxnNotFoundException{
		ResponseEntity<List<LiabilityGLMapping>> responseModel ;
		System.err.println("inside get");
		List<LiabilityGLMapping> mapping = liabilityService.getMapping(moduleCd);
		if ( mapping != null){
			responseModel = new ResponseEntity<List<LiabilityGLMapping>>(mapping,HttpStatus.OK);
			LOGGER.info("success: response returning Liability mapping"); 
		}
		else {
			responseModel = new ResponseEntity<List<LiabilityGLMapping>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	@GetMapping("/get-all-mapping")
	@ApiOperation(value = "View mapping for module", response = ResponseEntity.class)
	ResponseEntity<List<LiabilityGLMapping>> getAllGltxnByFromDt() throws GlTxnNotFoundException{
		ResponseEntity<List<LiabilityGLMapping>> responseModel ;
		System.err.println("inside get-all");
		List<LiabilityGLMapping> mapping = liabilityService.getMapping();
		if ( mapping != null){
			responseModel = new ResponseEntity<List<LiabilityGLMapping>>(mapping,HttpStatus.OK);
			LOGGER.info("success: response returning Liability mapping"); 
		}
		else {
			responseModel = new ResponseEntity<List<LiabilityGLMapping>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	@PostMapping("/add-mapping")
	@ApiOperation(value = "Create liability GL Mapping", response = ResponseEntity.class)
	public ResponseEntity<Integer> addGLTxn(@RequestBody List<LiabilityGLMapping> glMapping) {
		System.err.println("inside add controller");
		ResponseEntity<Integer> model ;
		Integer result;
		try {
			LOGGER.info(glMapping.toString());
			result = liabilityService.addMApping(glMapping);
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
			LOGGER.error("Exception thrown- MAppingNotCreatedException..Incomplete Data Data provided");
		}
		return model;
	
}

	
}
