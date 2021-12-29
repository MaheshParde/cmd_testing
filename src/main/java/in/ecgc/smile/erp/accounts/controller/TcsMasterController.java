package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.model.TcsMaster;
import in.ecgc.smile.erp.accounts.service.TcsMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tcs-master")
@Api(value= "tcs Master service")	
public class TcsMasterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TcsMasterController.class);

	
	@Autowired
	TcsMasterService tcsMasterService;

	@GetMapping("/view-all")
	@ApiOperation(value = "view all tcs masters" , response = ResponseEntity.class)
	public ResponseEntity<List<TcsMaster>> listAllTCs() {
		
		List<TcsMaster> listTcs = tcsMasterService.listAllTcs();
		if(listTcs != null) {
			return new ResponseEntity<>(listTcs , HttpStatus.OK);
			}
		else {
			return new ResponseEntity<List<TcsMaster>>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/add")
	@ApiOperation(value = "add tcs" , response = ResponseEntity.class)
	public ResponseEntity<TcsMaster> addTcs(@Valid @RequestBody TcsMaster tcsMaster)
	{
		LOGGER.info(tcsMaster.toString());
		tcsMasterService.addTcsMaster(tcsMaster);
		return new ResponseEntity<>(tcsMaster,HttpStatus.CREATED);
	}
}
