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

import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.model.Provision;
import in.ecgc.smile.erp.accounts.model.ProvisionGLMapping;
import in.ecgc.smile.erp.accounts.service.ProvisionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/provision")
@Api(value = "Provision service")
public class ProvisionController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProvisionController.class);

	@Autowired
	ProvisionService provisionService;
	
	@PostMapping("/add")
	@ApiOperation(value = "Create Provision", response = ResponseEntity.class)
	public ResponseEntity<GlTxnHdr> createProvision(@RequestBody Provision provision) {
		System.err.println("inside add controller");
		ResponseEntity<GlTxnHdr> model ;
		GlTxnHdr result;
		try {
			LOGGER.info(provision.toString());
			result = provisionService.createProvision(provision);
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
	ResponseEntity<List<ProvisionGLMapping>> getAllGltxnByFromDt(
			@PathVariable("moduleCd") String moduleCd,
			@PathVariable("mappingCd") String mappingCd) throws GlTxnNotFoundException{
		ResponseEntity<List<ProvisionGLMapping>> responseModel ;
		System.err.println("inside get-all");
		List<ProvisionGLMapping> mapping = provisionService.getMapping(moduleCd, mappingCd);
		if ( mapping != null){
			responseModel = new ResponseEntity<List<ProvisionGLMapping>>(mapping,HttpStatus.OK);
			LOGGER.info("success: response returning Provision mapping"); 
		}
		else {
			responseModel = new ResponseEntity<List<ProvisionGLMapping>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	
	
	
	@GetMapping("/get-mapping/{moduleCd}")
	@ApiOperation(value = "View mapping for module", response = ResponseEntity.class)
	ResponseEntity<List<ProvisionGLMapping>> getAllGltxnByFromDt(
			@PathVariable("moduleCd") String moduleCd) throws GlTxnNotFoundException{
		ResponseEntity<List<ProvisionGLMapping>> responseModel ;
		System.err.println("inside get");
		List<ProvisionGLMapping> mapping = provisionService.getMapping(moduleCd);
		if ( mapping != null){
			responseModel = new ResponseEntity<List<ProvisionGLMapping>>(mapping,HttpStatus.OK);
			LOGGER.info("success: response returning provision mapping"); 
		}
		else {
			responseModel = new ResponseEntity<List<ProvisionGLMapping>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	@GetMapping("/get-all-mapping")
	@ApiOperation(value = "View mapping for module", response = ResponseEntity.class)
	ResponseEntity<List<ProvisionGLMapping>> getAllGltxnByFromDt() throws GlTxnNotFoundException{
		ResponseEntity<List<ProvisionGLMapping>> responseModel ;
		System.err.println("inside get-all");
		List<ProvisionGLMapping> mapping = provisionService.getMapping();
		if ( mapping != null){
			responseModel = new ResponseEntity<List<ProvisionGLMapping>>(mapping,HttpStatus.OK);
			LOGGER.info("success: response returning provision mapping"); 
		}
		else {
			responseModel = new ResponseEntity<List<ProvisionGLMapping>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	@PostMapping("/add-mapping")
	@ApiOperation(value = "Create provosion GL Mapping", response = ResponseEntity.class)
	public ResponseEntity<Integer> addGLTxn(@RequestBody List<ProvisionGLMapping> glMapping) {
		System.err.println("inside add controller");
		ResponseEntity<Integer> model ;
		Integer result;
		try {
			LOGGER.info(glMapping.toString());
			result = provisionService.addMApping(glMapping);
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
