package in.ecgc.smile.erp.accounts.integrate.controller;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import in.ecgc.smile.erp.accounts.integrate.service.ExtPaymentAdviceService;
import in.ecgc.smile.erp.accounts.model.PaymentAdvice;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceTcodes;
import in.ecgc.smile.erp.accounts.service.PaymentAdviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ext-payment-advice")
@Api(value = "Payment advice controller")
public class ExtPaymentAdviceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExtPaymentAdviceController.class);
	
	@Autowired
	PaymentAdviceService payAdviceService;
	
	@Autowired
	ExtPaymentAdviceService extPaymentAdviceService;
	
	@GetMapping(value = "/getSeqNo/{logicalLocCd}/{sectionCd}/{fiscalYear}")
	@ApiOperation(value = "Get current sequence number for payment advice no", response = ResponseEntity.class)
	public ResponseEntity<Integer> getSeqNo(
			@ApiParam(value = "Logical Location Code", required = true)
			@PathVariable("logicalLocCd") @NotBlank String logicalLocCd,
			@ApiParam(value = "Section Code", required = true)
			@PathVariable("sectionCd") @NotBlank String sectionCd,
			@ApiParam(value = "Fiscal Year", required = true)
			@PathVariable("fiscalYear") @NotBlank String fiscalYear){
		
		Integer seqNo = payAdviceService.getAdviceNo(logicalLocCd, sectionCd, fiscalYear);
		
		if (seqNo != null) {
			return new ResponseEntity<Integer>(seqNo, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping(value = "/updateSeqNo/{logicalLocCd}/{sectionCd}/{fiscalYear}/{adviceNo}")
	@ApiOperation(value = "Update new sequence number for payment advice no", response = ResponseEntity.class)
	public ResponseEntity<Integer> updateSeqNo(
			@ApiParam(value = "Logical Location Code", required = true)
			@PathVariable("logicalLocCd") @NotBlank String logicalLocCd,
			@ApiParam(value = "Section Code", required = true)
			@PathVariable("sectionCd") @NotBlank String sectionCd,
			@ApiParam(value = "Fiscal Year", required = true)
			@PathVariable("fiscalYear") @NotBlank String fiscalYear,
			@ApiParam(value = "Advice Number", required = true)
			@PathVariable("adviceNo") @NotBlank Integer adviceNo){
		
		
		Integer res = payAdviceService.updateSeqNo(logicalLocCd, sectionCd, fiscalYear, adviceNo);
		
		if (res == 1) {
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	@PostMapping(value = "/add")
	@ApiOperation(value = "Create new payment advice entry", response = ResponseEntity.class)
	public ResponseEntity<Integer> addPaymentAdvice(
			@Valid @RequestBody PaymentAdvice payAdvice){
		
		log.info("Inside ExtPaymentAdviceController#addPaymentAdvice");
		
		Integer adviceNo = payAdviceService.addPaymentAdvice(payAdvice);
		
		log.info("Result after saving pay adv object in db : "+adviceNo);
		
		if (adviceNo!=null) {
				return new ResponseEntity<Integer>(adviceNo, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/save-payment-advice/{seqNo}/{moduleCd}/{payAdviceNo}")
	public ResponseEntity<String> savePayAdvNo(@PathVariable("seqNo") @NotBlank Integer seqNo,
			@PathVariable("moduleCd") @NotBlank String moduleCd,
			@PathVariable("payAdviceNo") @NotBlank String payAdviceNo){
		log.info("inside AccountsController#savePaymentAdvice : ref no & advice no :"+seqNo+","+moduleCd+","+payAdviceNo);
		String extRes = extPaymentAdviceService.savePaymentAdvice(seqNo,moduleCd, payAdviceNo);
		if (extRes!=null) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/getAll")
	@ApiOperation(value = "Get all payment advices", response = ResponseEntity.class)
	public ResponseEntity<List<PaymentAdvice>> listAllPaymentAdvices(){
		
		List<PaymentAdvice> allPayAdvice = payAdviceService.listAllPaymentAdvice();
		
		if (allPayAdvice != null) {
			return new ResponseEntity<List<PaymentAdvice>>(allPayAdvice, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PaymentAdvice>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/payTcodes/getAll")
	@ApiOperation(value = "Get all payment advices", response = ResponseEntity.class)
	public ResponseEntity<List<PaymentAdvice>> listAllPaymentAdviceTcodes(){
		
		List<PaymentAdvice> allPayAdviceTcodes = payAdviceService.listAllPaymentAdviceTcodes();
		
		if (allPayAdviceTcodes != null) {
			return new ResponseEntity<List<PaymentAdvice>>(allPayAdviceTcodes, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PaymentAdvice>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/get/{logicalLocCd}/{sectionCd}/{adviceNo}")
	@ApiOperation(value = "Get payment advice by advice number", response = ResponseEntity.class)
	public ResponseEntity<PaymentAdvice> getPaymentAdviceByAdviceDtl(
			@ApiParam(value = "Logical Location Code", required = true)
			@PathVariable("logicalLocCd") @NotBlank String logicalLocCd,
			@ApiParam(value = "Section Code", required = true)
			@PathVariable("sectionCd") @NotBlank String sectionCd,
			@ApiParam(value = "Advice Number", required = true)
			@PathVariable("adviceNo") @NotBlank Integer adviceNo){
		
		LOGGER.info("Get Method...");
		
		PaymentAdviceTcodes payTcodes = new PaymentAdviceTcodes();
		PaymentAdvice payAdvice = new PaymentAdvice(payTcodes);

		payTcodes = payAdviceService.getPaymentAdviceTcodesDtl(logicalLocCd, sectionCd, adviceNo);
		LOGGER.info("Get PayTcodes : "+payTcodes);
		payAdvice= payAdviceService.getPaymentAdviceByAdviceDtl(logicalLocCd, sectionCd, adviceNo);
		LOGGER.info("Get PayAdvice : "+payAdvice);
		payAdvice.setPayTcodes(payTcodes);
		
		if (payAdvice != null) {
			return new ResponseEntity<PaymentAdvice>(payAdvice, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/get/{logicalLocCd}/{sectionCd}/{fromDt}/{toDt}")
	@ApiOperation(value = "Get payment advice by advice number", response = ResponseEntity.class)
	public ResponseEntity<List<PaymentAdvice>> getPaymentAdviceByLogicalLocSectionCd(
			@ApiParam(value = "Logical Location Code", required = true)
			@PathVariable("logicalLocCd") @NotBlank String logicalLocCd,
			@ApiParam(value = "Section Code", required = true)
			@PathVariable("sectionCd") @NotBlank String sectionCd,
			@ApiParam(value = "From date", required = true)
			@PathVariable("fromDt") @NotBlank String fromDtStr,
			@ApiParam(value = "To date", required = true)
			@PathVariable("toDt") @NotBlank String toDtStr){
		
		
		 LocalDate fromDt = LocalDate.parse(fromDtStr);
		 LocalDate toDt = LocalDate.parse(toDtStr);
		
		List<PaymentAdvice> payAdviceList = payAdviceService.getPaymentAdviceByLogicalLocSectionCd(logicalLocCd, sectionCd,fromDt,toDt);
		
		if (payAdviceList != null) {
			return new ResponseEntity<List<PaymentAdvice>>(payAdviceList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PaymentAdvice>>(HttpStatus.NO_CONTENT);
		}
	}

	
	@PutMapping(value = "/disable/{logicalLocCd}/{sectionCd}/{adviceNo}")
	@ApiOperation(value = "Disable the payment advice entry from the table", response = ResponseEntity.class)
	public ResponseEntity<Integer> disablePaymentAdvice(
			@ApiParam(value = "Advice Number", required = true)
			@PathVariable("adviceNo") @NotBlank Integer adviceNo,
			@ApiParam(value = "Logical Location Code", required = true)
			@PathVariable("logicalLocCd") @NotBlank String logicalLocCd,
			@ApiParam(value = "Section Code", required = true)
			@PathVariable("sectionCd") @NotBlank String sectionCd){
		
		Integer res = payAdviceService.disablePaymentAdvice(logicalLocCd, sectionCd, adviceNo);
		
		if (res == 1) {
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	@PutMapping(value = "/update")
	@ApiOperation(value = "Update payment advice", response = ResponseEntity.class)
	public ResponseEntity<Integer> updatePaymentAdvice(@Valid @RequestBody PaymentAdvice payAdvice){
		
		LOGGER.info("Modifies Obj : : "+payAdvice);
		
		Integer res = payAdviceService.updatePaymentAdvice(payAdvice);
		
		if (res == 1) {
			Integer res1 = payAdviceService.updatePaymentAdviceTcodes(payAdvice);
			if(res1 == 1) {
				return new ResponseEntity<Integer>(res, HttpStatus.OK);
			}else
			{
				return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
			}
			
		} else {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/getEntered/{logicalLocCd}/{sectionCd}/{adviceStat}")
	@ApiOperation(value = "Get payment advice entered status list", response = ResponseEntity.class)
	public ResponseEntity<List<PaymentAdvice>> getEnteredPaymentAdvice(
			@ApiParam(value = "Logical Location Code", required = true)
			@PathVariable("logicalLocCd") @NotBlank String logicalLocCd,
			@ApiParam(value = "Section Code", required = true)
			@PathVariable("sectionCd") @NotBlank String sectionCd,
			@ApiParam(value = "Advice Status", required = true)
			@PathVariable("adviceStat") @NotBlank String adviceStat){
		
		List<PaymentAdvice> entrPayAdviceList = new ArrayList<PaymentAdvice>();
		
		entrPayAdviceList = payAdviceService.getEntrPaymentAdvice(logicalLocCd, sectionCd, adviceStat);
		
		if (entrPayAdviceList != null) {
			return new ResponseEntity<>(entrPayAdviceList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping(value = "/takeDecision")
	@ApiOperation(value = "Take decision on payment advice", response = ResponseEntity.class)
	public ResponseEntity<Integer> takePaymentAdviceDecision(@Valid @RequestBody PaymentAdvice payAdvice){
		
		Integer res = payAdviceService.takeDecisionOnPaymentAdvice(payAdvice);
		
		if (res == 1) {
			return new ResponseEntity<Integer>(res , HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
