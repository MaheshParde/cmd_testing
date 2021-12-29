package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ecgc.smile.erp.accounts.model.Temp_Exporter;
import in.ecgc.smile.erp.accounts.model.Temp_TDS_DTL;
import in.ecgc.smile.erp.accounts.service.Temp_ExternalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping(value = "/ext-service")
@Api(value = "Temporary Controller")
public class Temp_ExternalController {

	@Autowired
	Temp_ExternalService externalService;
	
	
	@GetMapping(value = "/exporter/getList/{logicalLocCd}")
	@ApiOperation(value = "Get exporter list")
	public ResponseEntity<List<Temp_Exporter>> getExporterList(@PathVariable("logicalLocCd") String logicalLocCd){
		List<Temp_Exporter> expCode = externalService.getExporterList();
		
		return new ResponseEntity<List<Temp_Exporter>>(expCode,HttpStatus.OK);
	}
	
	@GetMapping(value = "/exporter/tdsDtl")
	@ApiOperation(value = "Get TDS details")
	public ResponseEntity<Temp_TDS_DTL> getTDSDtl(){
		Temp_TDS_DTL tdsDtls = externalService.getTDSDtl();
		return new ResponseEntity<Temp_TDS_DTL>(tdsDtls,HttpStatus.OK);
	}
}
