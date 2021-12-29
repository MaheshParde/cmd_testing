package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import javax.validation.Valid;
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

import in.ecgc.smile.erp.accounts.model.TDSMaster;
import in.ecgc.smile.erp.accounts.service.TDSMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/tds-master")
@Api(value = "tds master service")
public class TDSMasterController {

	@Autowired
	TDSMasterService tdsMasterService;
	
	@PostMapping("/add")
	@ApiOperation(value = "Add New tds Detail", response = ResponseEntity.class)
	public ResponseEntity<TDSMaster> addTdsDetails(@Valid @RequestBody TDSMaster tdsMaster) {
		tdsMasterService.addTdsDetails(tdsMaster);
		if (tdsMaster != null)
			return new ResponseEntity<>(tdsMaster, HttpStatus.ACCEPTED);
		else 
			return new ResponseEntity<>(tdsMaster, HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@GetMapping("/view-all")
	@ApiOperation(value = "View all tds details", response = ResponseEntity.class)
	public ResponseEntity<List<TDSMaster>> viewAllTds() {
		List<TDSMaster> list = tdsMasterService.viewAllTds();
		if (list != null)
			return new ResponseEntity<>(list, HttpStatus.OK);
		else 
			return new ResponseEntity<List<TDSMaster>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/view/{fromAmount}/{toAmount}/{sex}/{fiscalYr}")
	@ApiOperation(value = "View budget head by budget head code", response = ResponseEntity.class)
	public ResponseEntity<TDSMaster> find(
			@ApiParam(value = "from amount", required = true) 
			@PathVariable("fromAmount")@NotBlank double fromAmount, 
			@ApiParam(value = "to amount", required = true) 
			@PathVariable("toAmount")@NotBlank double toAmount,
			@ApiParam(value = "gender", required = true) 
			@PathVariable("sex")@NotBlank char sex,
			@ApiParam(value = "Fiscal Year", required = true) 
			@PathVariable("fiscalYr")@NotBlank String fiscalYr){
		TDSMaster tdsMaster = tdsMasterService.find(fromAmount,toAmount,sex,fiscalYr);
		return new ResponseEntity<>(tdsMaster, HttpStatus.OK);
	}
}
