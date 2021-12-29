/**
 * 
 */
package in.ecgc.smile.erp.accounts.controller;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

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
import in.ecgc.smile.erp.accounts.exception.RecordNotFoundException;
import in.ecgc.smile.erp.accounts.exception.TCodeNotPresentException;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.model.Liability;
import in.ecgc.smile.erp.accounts.model.LiabilityGLMapping;
import in.ecgc.smile.erp.accounts.service.LiabilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CMJ-SENG-LAPTOP9
 *
 */

@RestController
@RequestMapping("/liability")
@Api(value = "Liability service")
@Slf4j
public class LiabilityController {


	@Autowired
	LiabilityService liabilityService;
	
	@PostMapping("/add")
	@ApiOperation(value = "Create liability", response = ResponseEntity.class)
	@ApiResponses(value = @ApiResponse(code = 500,message = "Something went wrong"))
	public ResponseEntity<GlTxnHdr> createLiability(@RequestBody Liability liability) throws RecordNotFoundException, TCodeNotPresentException {
			
		log.info("Received liability obj : {}",liability);
		
		GlTxnHdr glTxnHdr = liabilityService.createLiability(liability);
		if (glTxnHdr!=null) {
			return new ResponseEntity<>(glTxnHdr,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get-mapping/{moduleCd}/{mappingCd}")
	@ApiOperation(value = "View mapping for module", response = ResponseEntity.class)
	ResponseEntity<List<LiabilityGLMapping>> getModuleWiseMapping(
			@PathVariable("moduleCd") String moduleCd,
			@PathVariable("mappingCd") String mappingCd) throws GlTxnNotFoundException{
		
		return new ResponseEntity<List<LiabilityGLMapping>>(liabilityService.getMapping(moduleCd, mappingCd),HttpStatus.OK);
	}
	
	@GetMapping("/get-mapping/{moduleCd}")
	@ApiOperation(value = "View mapping for module", response = ResponseEntity.class)
	ResponseEntity<List<LiabilityGLMapping>> getAllGltxnByFromDt(
			@PathVariable("moduleCd") String moduleCd) throws GlTxnNotFoundException{
		
			return new ResponseEntity<List<LiabilityGLMapping>>(liabilityService.getMapping(moduleCd),HttpStatus.OK);
	}
	
	@GetMapping("/get-all-mapping")
	@ApiOperation(value = "View mapping for module", response = ResponseEntity.class)
	ResponseEntity<List<LiabilityGLMapping>> getAllGltxnByFromDt() throws GlTxnNotFoundException{

		return new ResponseEntity<List<LiabilityGLMapping>>(liabilityService.getMapping(),HttpStatus.OK);
	}
	
	@PostMapping("/add-mapping")
	@ApiOperation(value = "Create liability GL Mapping", response = ResponseEntity.class)
	public ResponseEntity<Integer> addGLTxn(@RequestBody List<LiabilityGLMapping> glMapping) {
		return new ResponseEntity<>(liabilityService.addMApping(glMapping),HttpStatus.CREATED);
	}
	
	@GetMapping("/distinct-module-cd")
	@ApiOperation(value = "View all Mudule Codes", response = ResponseEntity.class)
	ResponseEntity<List<String>> getAllModuleCds(){
		return new ResponseEntity<List<String>>(liabilityService.distinctModuleCd(),HttpStatus.OK);
	}
	

	@GetMapping("getMappingCd-name/{moduleCd}")	
	public ResponseEntity<Map<String,String>> getMappingCdAndMappingNameForModuleCd(@ApiParam (value ="moduleCd", required = true )
		@PathVariable("moduleCd") @NotBlank String moduleCd){
		return new ResponseEntity<Map<String,String>>(liabilityService.getAllMappingCodeAndMappingNameforModuleCd(moduleCd), HttpStatus.OK);
	}
	
	@GetMapping("getApplicableTcodes/{moduleCd}/{mappingCd}")
	@ApiOperation(value = "Get all applicable tcodes")
	public ResponseEntity<List<String>> getApplicableTcodes(
			@ApiParam (value = "moduleCd", required = true) @PathVariable("moduleCd") @NotBlank String moduleCd,
			@ApiParam (value = "mappingCd", required = true) @PathVariable("mappingCd") @NotBlank String mappingCd){
		
		return new ResponseEntity<List<String>>(liabilityService.getRequiredTCodes(moduleCd, mappingCd),HttpStatus.OK);
	}
}
