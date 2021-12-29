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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.ecgc.smile.erp.accounts.model.Receipt;
import in.ecgc.smile.erp.accounts.model.States;
import in.ecgc.smile.erp.accounts.repository.ReceiptDao;
import in.ecgc.smile.erp.accounts.service.ReceiptService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/receipt")
@Api(value = "Receipt service")
@Slf4j
public class ReceiptController {
	//private static final Logger log = LoggerFactory.getLogger(Receipt.class);

	@Autowired
	ReceiptService receiptService;
	@Autowired
	ReceiptDao receiptDao;
	
	
	@PostMapping("/add")
	@ApiOperation(value = "add receipt detail", response = ResponseModel.class)
	public ResponseEntity<Integer> addReceipt(@Valid @RequestBody Receipt receipt) {
	//	System.err.println("inside add controller");
		ResponseEntity<Integer> model ;
		Integer result;
		try {
			result = receiptService.addReceipt(receipt);
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
			log.error("Exception thrown- GLTxnIncompleteDataException..Incomplete GL Data Data provided");
		}
		return model;
	}

	@GetMapping("/view-states")
	@ApiOperation(value="view all states" ,response = ResponseEntity.class)
	public ResponseEntity<List<States>>getAllStates(){
		List<States> stateList= receiptService.getAllStates();
	//	System.out.println("States Are :: "+stateList);
		if (stateList != null) {
			return new ResponseEntity<>(stateList ,HttpStatus.OK);		
		}else {
			return new ResponseEntity<List<States>>(HttpStatus.NO_CONTENT);
		}		
	}
	
	@GetMapping("/view-all")
	@ApiOperation(value = "View all Receipts" ,response = ResponseEntity.class)
	public ResponseEntity<List<Receipt>> listAllReceipts(){
		
	System.err.println("view all..........");	
	List<Receipt> allReceipts = receiptService.listAllReceipts();
	
		if (allReceipts != null) {
			return new ResponseEntity<>(allReceipts ,HttpStatus.OK);
			
			
		}else {
			return new ResponseEntity<List<Receipt>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/viewReceipt/{logicalLocCode}")
	@ApiOperation(value = "view receipt by logicalLocCode")
	public ResponseEntity<List<Receipt>>viewByLogicalLocCode(
			@ApiParam(value = "logicalLocCode" ,required = true)
			@PathVariable("logicalLocCode")String logicalLocCode){
		List<Receipt> recieptList= receiptService.viewByLogicalLocCode(logicalLocCode);
		if(recieptList!= null) {
			return new ResponseEntity<>(recieptList, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<Receipt>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/view/{logicalLocCode}/{receiptNumber}")
	@ApiOperation(value = "view receipt by logicalLocCode and receiptNumber")
	public ResponseEntity<Receipt>viewByLogicalLocCodeAndReceiptNo(
			@ApiParam(value = "logicalLocCode", required = true)
			@PathVariable("logicalLocCode") @NotBlank String logicalLocCode,
			@ApiParam(value = "receiptNumber", required = true)
			@PathVariable("receiptNumber")@NotBlank Integer receiptNumber
			)
	{
		Receipt receiptDetails = receiptService.viewByLogicalLocCodeAndReceiptNo(logicalLocCode, receiptNumber);
		log.info("",receiptDetails);
		return new ResponseEntity<Receipt>(receiptDetails , HttpStatus.OK);
	}
	
	@PutMapping(value ="/update/{logicalLocCode}/{receiptNumber}")
	@ApiOperation(value = "update receipt" , response = ResponseEntity.class)
	public ResponseEntity<Integer> updateReceipt(
			@ApiParam(value = "logicalLocCode" , required = true)
			@PathVariable("logicalLocCode")@NotBlank String logicalLocCode,
			@ApiParam(value = "receiptNumber" , required = true)			
			@PathVariable("receiptNumber")@NotBlank Integer receiptNumber,
			@RequestBody Receipt receiptUpdate)
	{
		Integer result = receiptService.updateReceipt(logicalLocCode, receiptNumber, receiptUpdate);
		return new ResponseEntity<Integer> (result ,HttpStatus.OK);
	}
	
	@PutMapping(value ="/update-flag/{logicalLocCode}/{receiptNumber}/{printFlag}")
	@ApiOperation(value = "update receipt" , response = ResponseEntity.class)
	public ResponseEntity<Integer> updatePrintFlag(
			@ApiParam(value = "logicalLocCode" , required = true)
			@PathVariable("logicalLocCode")@NotBlank String logicalLocCode,
			@ApiParam(value = "receiptNumber" , required = true)			
			@PathVariable("receiptNumber")@NotBlank Integer receiptNumber,
			@ApiParam(value = "printFlag" , required = true)			
			@PathVariable("printFlag")@NotBlank String printFlag)
	{
		System.err.println(logicalLocCode +" "+receiptNumber);
		Integer result = receiptService.updatePrintFlag(logicalLocCode, receiptNumber, printFlag);
		System.err.println("controller"+result);
		return new ResponseEntity<Integer> (result ,HttpStatus.OK);
	}
	
	@GetMapping(value="/get-flag/{logicalLocCode}/{receiptNumber}")
	@ApiOperation(value="Get Print flag",response = ResponseEntity.class)
	public ResponseEntity<String> getFlag(
			@ApiParam(value = "logicalLocCode" , required = true)
			@PathVariable("logicalLocCode")@NotBlank String logicalLocCode,
			@ApiParam(value = "receiptNumber" , required = true)			
			@PathVariable("receiptNumber")@NotBlank Integer receiptNumber)
	{
		System.err.println(logicalLocCode +" "+receiptNumber);
		String result = receiptService.getFlag(logicalLocCode, receiptNumber);
		System.err.println("controller"+result);
		return new ResponseEntity<String> (result ,HttpStatus.OK);
	}
}
