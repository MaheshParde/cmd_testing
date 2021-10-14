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

import in.ecgc.smile.erp.accounts.model.SubBifurcations;
import in.ecgc.smile.erp.accounts.service.SubBiFurcationMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sub-bifurcations")
@Api(value = "SubBifurcation masterservice")
public class SubBifurcationMasterController {

	@Autowired
	SubBiFurcationMasterService subBifurcationService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SubBifurcationMasterController.class);

	
	@GetMapping("/view-all")
	@ApiOperation(value = "View all Sub bifurcations", response = ResponseEntity.class)
	ResponseEntity<List<SubBifurcations>> getAllSubBifurcations(){
		
		ResponseEntity<List<SubBifurcations>> responseModel ;
		System.err.println("inside controller");
		List<SubBifurcations> subBifurcations= subBifurcationService.getSubBifurcations();
		if ( subBifurcations!= null){
			responseModel = new ResponseEntity<List<SubBifurcations>>(subBifurcations,HttpStatus.OK);
			LOGGER.info("success: response returning list of sub bifurcations"); 
			}
		else {
			responseModel = new ResponseEntity<List<SubBifurcations>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
	return responseModel;
	}
	@GetMapping("/view-all-level")
	@ApiOperation(value = "View all Sub bifurcation levels", response = ResponseEntity.class)
	ResponseEntity<List<String>> getAllSubBifurcationLevels(){
		
		ResponseEntity<List<String>> responseModel ;
		System.err.println("inside controller");
		List<String> subBifurcations= subBifurcationService.getSubBifurcationsLevel();
		if ( subBifurcations!= null){
			responseModel = new ResponseEntity<List<String>>(subBifurcations,HttpStatus.OK);
			LOGGER.info("success: response returning list of sub bifurcation levels"); 
		}
		else {
			responseModel = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	@GetMapping("/view-all/{subbifurcationLevel}")
	@ApiOperation(value = "View Sub bifurcations By Level", response = ResponseEntity.class)
	ResponseEntity<List<SubBifurcations>> getAllSubBifurcationsByLEvel(@PathVariable("subbifurcationLevel")String subBifurcationLevel){
		
		ResponseEntity<List<SubBifurcations>> responseModel ;
		List<SubBifurcations> subBifurcations= subBifurcationService.getSubBifurcationsByLevel(subBifurcationLevel);
		if ( subBifurcations!= null){
			responseModel = new ResponseEntity<List<SubBifurcations>>(subBifurcations,HttpStatus.OK);
			LOGGER.info("success: response returning list of sub bifurcations"); 
		}
		else {
			responseModel = new ResponseEntity<List<SubBifurcations>>(HttpStatus.BAD_REQUEST);
			LOGGER.info("No entries found");
		}
		return responseModel;
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "Create Sub Bifurcaions", response = ResponseEntity.class)
	public ResponseEntity<Integer> addSubBifurcation(@RequestBody SubBifurcations subBifurcations) {
		System.err.println("inside add controller");
		ResponseEntity<Integer> model ;
		Integer result;
		try {
			LOGGER.info(subBifurcations.toString());
			result = subBifurcationService.addSubBifurcation(subBifurcations);
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
			LOGGER.error("Exception thrown- ..Incomplete Data provided");
		}
		return model;
	}
	@PostMapping("/update")
	@ApiOperation(value = "Update Sub Bifurcaions", response = ResponseEntity.class)
	public ResponseEntity<Integer> updateSubbifurcation(@RequestBody SubBifurcations subBifurcations) {
		System.err.println("inside update controller");
		ResponseEntity<Integer> model ;
		Integer result;
		try {
			LOGGER.info(subBifurcations.toString());
			result = subBifurcationService.updatedSubBifurcations(subBifurcations);
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
			LOGGER.error("Exception thrown- ..Incomplete Data provided");
		}
		return model;
	}
}
