package in.ecgc.smile.erp.accounts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;
import in.ecgc.smile.erp.accounts.service.SubBifurcationValueService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("/sub-bifurcation-value")
@Api(value = "Sub Bifurcation Value ")
@Slf4j
public class SubBifurcationValueController{
	@Autowired
	private SubBifurcationValueService subBifurcationValueService;
	//private static final Logger LOGGER = LoggerFactory.getLogger(SubBifurcationValueController.class);
	
	@GetMapping("/view-all")
	public ResponseEntity<List<SubBifurcationValue>> getSubBifurcationsDtlList(){
	List<SubBifurcationValue> result= subBifurcationValueService.getSubBifurcationsDtlList();
	log.info("RESULT::{}",result);
	return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/get-by-id/{bifurcationLevelCode}/{bifurcationValueCode}")
	public ResponseEntity<SubBifurcationValue> getSubBifurcationsDtlDataById(
			@PathVariable("bifurcationLevelCode")@Size(min = 3, max = 3,
			message = "Sub Bifurcation Level Code should be of 3 Characters" ) @NotBlank  String bifurcationLevelCode ,
			@PathVariable("bifurcationValueCode")@Size(min = 6, max = 6,
			message = "Sub Bifurcation Value Code should be of 3 Characters") @NotBlank String bifurcationValueCode ){
	
		SubBifurcationValue result= subBifurcationValueService.getSubBifurcationsDtlDataById(bifurcationLevelCode, bifurcationValueCode);
		log.info("RESULT::{}",result);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Boolean> addSubBifurcationsDtlData(@Valid @RequestBody SubBifurcationValue  subBifurcationsDtl) {
	boolean result = subBifurcationValueService.addSubBifurcationsDtlData(subBifurcationsDtl);
	log.info("RESULT::{}",result);
	return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/update/{bifurcationlevelCode}/{bifurcationValueCode}")
	public ResponseEntity<Boolean> updateSubBifurcationsDtlData(
			@PathVariable("bifurcationlevelCode")@Size(min = 3, max = 3,
					message = "Sub Bifurcation Level Code should be of 3 Characters" ) @NotBlank String bifurcationlevelCode,
			@PathVariable("bifurcationValueCode")@Size(min = 6, max = 6,
			message = "Sub Bifurcation Value Code should be of 3 Characters" ) @NotBlank String bifurcationValueCode,
			 @RequestBody SubBifurcationValue  subBifurcationValue) {
	boolean result = subBifurcationValueService.updateSubBifurcationsDtlData(bifurcationlevelCode, bifurcationValueCode, subBifurcationValue);
	log.info("RESULT::{}",result);
	return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PutMapping("/delete/{bifurcationlevelCode}/{bifurcationValueCode}")
	@ApiOperation(value = "Delete Sub Bifurcation Value", response = ResponseEntity.class)
	public ResponseEntity<Boolean> disableSubBifurcationValue(
			@ApiParam(value = "bifurcationlevelCode", required = true)@PathVariable("bifurcationlevelCode")
			@Size(min = 3, max = 3,	message = "Sub Bifurcation Level Code should be of 3 Characters" )
			@NotBlank String bifurcationlevelCode,
			@ApiParam(value = "bifurcationValueCode", required = true)@PathVariable("bifurcationValueCode")
			@Size(min = 6, max = 6,message = "Sub Bifurcation Value Code should be of 3 Characters" )
			@NotBlank String bifurcationValueCode)
			{
		 
			boolean result = subBifurcationValueService.disableSubBifurcationValue(bifurcationlevelCode, bifurcationValueCode);
			return new ResponseEntity<Boolean>(result, HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/get-value-code/{levelCode}")	
	public ResponseEntity<String> getValueCode(
			@PathVariable("levelCode")
			@Size(min = 3, max = 3,	message = "Sub Bifurcation Level Code should be of 3 Characters" )
			@NotBlank String bifurcationlevelCode){
	String result = subBifurcationValueService.getBifurcationCode(bifurcationlevelCode);
	log.info("RESULT::{}",result);
	return new ResponseEntity<>(result,HttpStatus.OK);
	}	
	
	@GetMapping("/valueList/{levelCode}")
	public ResponseEntity<List<SubBifurcationValue>>getValueList(@PathVariable ("levelCode") String levelCode ){
		List< SubBifurcationValue> valueList = new ArrayList<>();
		valueList=subBifurcationValueService.getAllSubBifurcationValueCodeByLevelCode(levelCode);
		return new ResponseEntity<>(valueList, HttpStatus.OK);
	}
	
	@GetMapping("/get-sub-bifurcations/{mainGLCode}/{subGLCode}")
	@ApiOperation(value = "View  by  GL code", response = ResponseModel.class)
	public ResponseEntity<List<SubBifurcationValue>> findSubBifurcations(@PathVariable("mainGLCode")  @Size(min = 4, max = 4, message = "Main GL Should be of 4 Characters") String mainGLCode,
			@PathVariable("subGLCode")  @Size(min = 3, max = 3, message = "Sub GL Should be of 3 Characters") String  subGLCode) {
		    log.info("Controller: Fetching SubBifurcations for MainGL: {} SubGL: {}",mainGLCode,subGLCode);
			return new ResponseEntity<>(subBifurcationValueService.findSubBifurcationValueList(mainGLCode,subGLCode),HttpStatus.OK);
	}
}
