package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.SubBifurcations;
import in.ecgc.smile.erp.accounts.service.SubBiFurcationMasterService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sub-bifurcations")
@Api(value = "SubBifurcation masterservice")
@Slf4j
public class SubBifurcationMasterController {

	@Autowired
	SubBiFurcationMasterService subBifurcationService;


	@GetMapping("/view-all")
	@ApiOperation(value = "View all Sub bifurcations", response = ResponseEntity.class)
	ResponseEntity<List<SubBifurcations>> getAllSubBifurcations(){
		
		ResponseEntity<List<SubBifurcations>> responseModel ;
		List<SubBifurcations> subBifurcations= subBifurcationService.getSubBifurcations();
		if ( subBifurcations!= null){
			responseModel = new ResponseEntity<List<SubBifurcations>>(subBifurcations,HttpStatus.OK);
			log.info("success: response returning list of sub bifurcations"); 
			}
		else {
			responseModel = new ResponseEntity<List<SubBifurcations>>(HttpStatus.BAD_REQUEST);
			log.info("No entries found");
		}
	return responseModel;
	}
	@GetMapping("/view-all-level")
	@ApiOperation(value = "View all Sub bifurcation levels", response = ResponseEntity.class)
	ResponseEntity<List<String>> getAllSubBifurcationLevels(){
		
		ResponseEntity<List<String>> responseModel ;
		List<String> subBifurcations= subBifurcationService.getSubBifurcationsLevel();
		if ( subBifurcations!= null){
			responseModel = new ResponseEntity<List<String>>(subBifurcations,HttpStatus.OK);
			log.info("success: response returning list of sub bifurcation levels"); 
		}
		else {
			responseModel = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
			log.info("No entries found");
		}
		return responseModel;
	}
	@GetMapping("/view-all/{subbifurcationLevel}")
	@ApiOperation(value = "View Sub bifurcations By Level", response = ResponseEntity.class)
	ResponseEntity <SubBifurcations> getAllSubBifurcationsByLEvel(@PathVariable("subbifurcationLevel")
	@Size(min = 3, max = 3, message = "Sub Bifurcation Level Code should be of 3 Characters")  
	String subBifurcationLevel){
		
		ResponseEntity<SubBifurcations> responseModel ;
		SubBifurcations subBifurcations= subBifurcationService.getSubBifurcationsByLevel(subBifurcationLevel);
		responseModel = new ResponseEntity<SubBifurcations>(subBifurcations,HttpStatus.OK);

//		if ( subBifurcations!= null){
//			responseModel = new ResponseEntity<SubBifurcations>(subBifurcations,HttpStatus.OK);
//			log.info("success: response returning list of sub bifurcations"); 
//		}
//		else {
//			responseModel = new ResponseEntity<sSubBifurcations>(HttpStatus.BAD_REQUEST);
//			log.info("No entries found");
//		}
		return responseModel;
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "Create Sub Bifurcaions", response = ResponseEntity.class)
	public ResponseEntity<Integer> addSubBifurcation(@Valid @RequestBody SubBifurcations subBifurcations) {
		ResponseEntity<Integer> model ;
		Integer result;
		try {
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
			log.error(e.getMessage());
			log.error("Exception thrown- ..Incomplete Data provided");
		}
		return model;
	}
	
	@PutMapping("/update/{subBifurcationLevel}")
	@ApiOperation(value = "Update Sub Bifurcation Level", response = ResponseModel.class)
	public ResponseEntity<SubBifurcations> updateSubBifurcationLevel(
												@PathVariable("subBifurcationLevel") @Size(min = 3, max = 3, message = "Sub Bifurcation Level Should be of 3 Characters") String subBifurcationLevel, 
												
												@RequestBody SubBifurcations updatedSubBifurcations ) {
		log.info("Controller: Update GL Code for Main GL {} Sub GL {}", subBifurcationLevel);
		SubBifurcations currentSubBifurcations = subBifurcationService.getSubBifurcationsByLevel(subBifurcationLevel);
		SubBifurcations modifiedSubBifurcations = subBifurcationService.updatedSubBifurcations(currentSubBifurcations, updatedSubBifurcations);
		return new ResponseEntity<>(modifiedSubBifurcations,HttpStatus.ACCEPTED);
  }
}
