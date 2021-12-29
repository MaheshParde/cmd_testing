package in.ecgc.smile.erp.accounts.controller;
	
import java.util.List;	
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

import in.ecgc.smile.erp.accounts.service.BankBranchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bank-branch-master")
@Api(value = "Bank Branch Master service")
@Slf4j
public class BankBranchMasterController {
		//private static final Logger log = LoggerFactory.getLogger(BankBranchMasterController.class);
	
		@Autowired
		BankBranchService bankBranchService;
		
	@PostMapping("/add")
	@ApiOperation(value= "Add new Bank Branch ", response = ResponseEntity.class)
	public ResponseEntity<BankBranch> addBankBranch(@Valid @RequestBody BankBranch bankBranch)
		{	
			log.info(bankBranch.toString());
			bankBranchService.addBankBranch(bankBranch);
			return new ResponseEntity<>(bankBranch, HttpStatus.CREATED);
		
	}		
		
	@GetMapping("/view-all")
	@ApiOperation(value = "View all Bank Branches", response = ResponseEntity.class)
	public ResponseEntity<List<BankBranch>> listAllBankBranches(){
		List<BankBranch> allBankBranches = bankBranchService.listAllBankBranches();
		if (allBankBranches != null) {
			return new ResponseEntity<>(allBankBranches, HttpStatus.OK);
		}else {

			return new ResponseEntity<List<BankBranch>>(HttpStatus.NO_CONTENT);
			
	}
		}
	
	@PutMapping("/update/{logicalLocCode}/{bankName}")
	@ApiOperation(value = "Update bank Branch detail by logical location code ", response = ResponseEntity.class)
	public ResponseEntity<Integer> updateBankBranch(
			@ApiParam(value = "logical location", required = true)@PathVariable("logicalLocCode") @NotBlank String logicalLocCode, 
			@ApiParam(value = "bank Name", required = true)@PathVariable("bankName") @NotBlank String bankName, 
			 @RequestBody BankBranch updateBankBranch ) {
			Integer result = bankBranchService.updateBankBranch(logicalLocCode, bankName, updateBankBranch);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
			
		@GetMapping("/view/{logicalLocCode}/{bankName}")
		@ApiOperation(value = "View bank Details By bank name and Logical location", response = ResponseEntity.class)
		public ResponseEntity<BankBranch>findBankByLogicalLocationAndBankName(
				@ApiParam(value = "logical location", required = true)@PathVariable("logicalLocCode") @NotBlank String logicalLocCode,
				@ApiParam(value = "bank Name", required = true)@PathVariable("bankName") @NotBlank String bankName)
		{
			BankBranch bankBranchList= bankBranchService.findBankByLogicalLocationAndBankName(logicalLocCode, bankName);
			log.info(bankBranchList.toString());
			return new ResponseEntity<>(bankBranchList, HttpStatus.OK);
		}
		
		
		@DeleteMapping("/delete/{logicalLocCode}/{bankName}")
		@ApiOperation(value = "Delete given bank branch head code", response = ResponseEntity.class)
		public ResponseEntity<Integer> disableBankBranch(
				@ApiParam(value = "logical location", required = true)@PathVariable("logicalLocCode") @NotBlank String logicalLocCode,
				@ApiParam(value = "bank Name", required = true)@PathVariable("bankName") @NotBlank String bankName)
				{
			 
				Integer result = bankBranchService.disableBankBranch(logicalLocCode, bankName);
				return new ResponseEntity<Integer>(result, HttpStatus.ACCEPTED);
	
		}
		@GetMapping("/active/view-all")
		@ApiOperation(value = "View all active bank Branches", response = ResponseEntity.class)
				public ResponseEntity<List<BankBranch>> listActiveBankBranches() {
					List<BankBranch> allActiveBankBranches = bankBranchService.listActiveBankBranches();
					if (allActiveBankBranches != null)
						return new ResponseEntity<>(allActiveBankBranches, HttpStatus.OK);
					else 
						return new ResponseEntity<List<BankBranch>>(HttpStatus.NO_CONTENT);
				}
					
		
		@GetMapping("/view-gstin/{logicalLocCode}")
		@ApiOperation(value = "View bank Details By bank name and Logical location", response = ResponseEntity.class)
		public ResponseEntity<String>viewGstinByLogicalLocation(
				@ApiParam(value = "logicallocation", required = true)@PathVariable("logicalLocCode") @NotBlank String logicalLocCode)
		{
			String gstiNumber= bankBranchService.getGstinByLogicalLoc(logicalLocCode);
			return new ResponseEntity<>(gstiNumber, HttpStatus.OK);
		}
		
}
