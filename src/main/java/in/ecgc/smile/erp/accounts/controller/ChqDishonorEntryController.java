package in.ecgc.smile.erp.accounts.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.model.ChqDishonorEntry;
import in.ecgc.smile.erp.accounts.service.ChqDishonorEntryService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PathVariable;
@RestController
@Api(value = "Cheque Dishonor Entry")
@Slf4j
@RequestMapping("/chq-dishonor-entry")
public class ChqDishonorEntryController{
	
	@Autowired
	private ChqDishonorEntryService chqDishonorEntryservice;
	
	@GetMapping("/view-all")
	@ApiOperation(value = "List all Cheque Dishonor Details", response = ResponseModel.class)
	public ResponseEntity<List<ChqDishonorEntry>> getChqDishonorEntryList(){
		List<ChqDishonorEntry> result= chqDishonorEntryservice.getChqDishonorEntryList();
		log.info("RESULT::{}",result);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "Add Cheque Dishonor Details", response = ResponseModel.class)
	public ResponseEntity<Boolean> addChqDishonorEntryData(@RequestBody ChqDishonorEntry  chqDishonorEntry) {
		boolean result = chqDishonorEntryservice.addChqDishonorEntryData(chqDishonorEntry);
		log.info("RESULT::{}",result);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	@PostMapping("/update")
	@ApiOperation(value = "Update Cheque Dishonor Details", response = ResponseModel.class)
	public ResponseEntity<Boolean> updateChqDishonorEntryData(@RequestBody ChqDishonorEntry  chqDishonorEntry) {
		boolean result = chqDishonorEntryservice.updateChqDishonorEntryData(chqDishonorEntry);
		log.info("RESULT::{}",result);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/view/{instrumentNo}")
	@ApiOperation(value = "Get Cheque Dishonor Details By Instrument No", response = ResponseModel.class)
	public ResponseEntity<ChqDishonorEntry> getChqDishonorEntryDataByChequeNo(@PathVariable("instrumentNo") String instrumentNo){
		ChqDishonorEntry result= chqDishonorEntryservice.getChqDishonorEntryByChequeNo(instrumentNo);
		log.info("RESULT::{}",result);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
}
