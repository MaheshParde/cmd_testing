package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.model.BranchMaster;
import in.ecgc.smile.erp.accounts.service.BranchMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/branch-master")
@Api(value = "Branch Master service")
	
public class BranchMasterController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BranchMasterController.class);
	
	@Autowired
	BranchMasterService branchMasterService;

	@PostMapping("/add")
	@ApiOperation(value= "Add new Branch Details", response = ResponseEntity.class)
	public ResponseEntity<BranchMaster> addBranch(@Valid @RequestBody BranchMaster branchMaster)
		{	
			LOGGER.info(branchMaster.toString());
			branchMasterService.addBranch(branchMaster);
			return new ResponseEntity<>(branchMaster, HttpStatus.CREATED);		
	}		
		
	@GetMapping("/view-all")
	@ApiOperation(value = "View all Branches", response = ResponseEntity.class)
	public ResponseEntity<List<BranchMaster>> listAllBranches(){
		List<BranchMaster> allBranches = branchMasterService.listAllBranches();
		if (allBranches != null) {
			return new ResponseEntity<>(allBranches, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<BranchMaster>>(HttpStatus.NO_CONTENT);
			}
		}
	@PutMapping("/update/{logicalLocCode}/{branchCode}")
	@ApiOperation(value = "Update Branch detail by logical location code ", response = ResponseEntity.class)
	public ResponseEntity<Integer> updateBranch(
			@ApiParam(value = "logicallocation", required = true)@PathVariable("logicalLocCode") @NotBlank String logicalLocCode, 
			@ApiParam(value = "branchCode", required = true)@PathVariable("branchCode") @NotBlank String branchCode, 
			@RequestBody BranchMaster updateBranch ) {
			Integer result = branchMasterService.updateBranch(logicalLocCode, branchCode, updateBranch);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
			}	

	@DeleteMapping("/delete/{logicalLocCode}/{branchCode}")
	@ApiOperation(value = "Delete Branch", response = ResponseEntity.class)
	public ResponseEntity<Integer> disableBranch(
			@ApiParam(value = "logicalLocation", required = true)@PathVariable("logicalLocCode") @NotBlank String logicalLocCode,
			@ApiParam(value = "BranchCode", required = true)@PathVariable("branchCode") @NotBlank String branchCode)
			{		 
			Integer result = branchMasterService.disableBranch(logicalLocCode, branchCode);
			return new ResponseEntity<Integer>(result, HttpStatus.ACCEPTED);
	}	
}
