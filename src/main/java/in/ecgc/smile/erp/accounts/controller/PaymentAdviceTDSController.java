package in.ecgc.smile.erp.accounts.controller;

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

import in.ecgc.smile.erp.accounts.model.PaymentAdvice;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceAll;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceGstTdsDtl;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceTdsDtl;
import in.ecgc.smile.erp.accounts.service.PaymentAdviceTDSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/payment-advice-tds")
@Api(value = "Payment advice TDS controller")
public class PaymentAdviceTDSController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentAdviceController.class);
	
	@Autowired
	PaymentAdviceTDSService payAdviceTdsService;
	
	@GetMapping(value = "/get/{logicalLocCd}/{sectionCd}/{adviceNo}")
	@ApiOperation(value = "Get approved payment advice by advice number", response = ResponseEntity.class)
	public ResponseEntity<PaymentAdvice> getApprovedPaymentAdviceDtl(
			@ApiParam(value = "Logical Location Code", required = true)
			@PathVariable("logicalLocCd") @NotBlank String logicalLocCd,
			@ApiParam(value = "Section Code", required = true)
			@PathVariable("sectionCd") @NotBlank String sectionCd,
			@ApiParam(value = "Advice Number", required = true)
			@PathVariable("adviceNo") @NotBlank Integer adviceNo){
		
		LOGGER.info("Get Method...");
		
		PaymentAdvice payAdvice = new PaymentAdvice();

		payAdvice= payAdviceTdsService.getApprovedPaymentAdviceDtl(logicalLocCd, sectionCd, adviceNo);
		LOGGER.info("Get PayAdvice : "+payAdvice);
		
		if (payAdvice != null) {
			return new ResponseEntity<PaymentAdvice>(payAdvice, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping(value = "/get-approved-list/{logicalLocCd}/{sectionCd}/{fromDt}/{toDt}")
	@ApiOperation(value = "Get All Approved Payment Advices", response = ResponseEntity.class)
	public ResponseEntity<List<PaymentAdvice>> getApprovedPaymentAdvices(
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
		
		List<PaymentAdvice> payAdviceList = new ArrayList<>();
		
		payAdviceList = payAdviceTdsService.getApprovedPaymentAdvices( logicalLocCd, sectionCd, fromDt,  toDt);
		if (payAdviceList != null) {
			return new ResponseEntity<List<PaymentAdvice>>(payAdviceList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping(value = "/updateNotApplicable")
	@ApiOperation(value = "Update Payment Advice When TDS is NOT Applicable", response = ResponseEntity.class)
	public ResponseEntity<Integer> updatePaymentAdviceTdsNOTAppliacble(@Valid @RequestBody PaymentAdvice paymentAdvice){
		
		Integer res = payAdviceTdsService.updatePaymentAdviceTdsNOTAppliacble(paymentAdvice);
		
		System.err.println("function return is : "+res);
		
		if (res == 1) {
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value = "/insert-tds-dtl")
	@ApiOperation(value = "Insert Payment Advice TDS details When TDS is Applicable", response = ResponseEntity.class)
	public ResponseEntity<Integer> createPaymentAdviceTDS(@Valid @RequestBody PaymentAdviceAll payAdvAll){
		
		Integer res3;
		Integer res1;
		Integer res2;
		
		Integer rcy = 0;
		Integer rcygsty = 0;
		Integer rcygstn = 0;
		
		System.err.println(payAdvAll.toString());
		
		System.err.println("is RC APP ? :"+payAdvAll.getPayAdvTdsDtl().getRevChargeApp());
		System.err.println("is GST APP ? :"+payAdvAll.getPayAdvGstTdsDtl().getGstTdsApplicable());
		if (payAdvAll.getPayAdvTdsDtl().getRevChargeApp() == 'Y') {
			res1 =	payAdviceTdsService.updatePaymentAdviceRCAppliacble(payAdvAll.getPaymentAdvice());
			res2 =  payAdviceTdsService.addPaymentAdviceTDSDtl(payAdvAll.getPayAdvTdsDtl());
			System.err.println("01 : RES1 : "+res1+", RES2 : "+res2);
			if (res1 == 1){
				if (res2 == 1) {
					rcy = 1;
				}else {
					rcy = -1;
				}
				
			} else {
				rcy = -1;
			}
		} else if(payAdvAll.getPayAdvTdsDtl().getRevChargeApp() == 'N') {
			if (payAdvAll.getPayAdvGstTdsDtl().getGstTdsApplicable() == 'Y') {
				res1 =	payAdviceTdsService.updatePaymentAdviceRCAppliacble(payAdvAll.getPaymentAdvice());
				res2 =  payAdviceTdsService.addPaymentAdviceTDSDtl(payAdvAll.getPayAdvTdsDtl());
				res3 =  payAdviceTdsService.addPaymentAdviceGSTTDSDtl(payAdvAll.getPayAdvGstTdsDtl());
				System.err.println("02 : RES1 : "+res1+", RES2 : "+res2+", RES3 : "+res3);
				if (res1 == 1) {
					rcygsty = 1;
				} else if (res2 == 1) {
					rcygsty = 1;
				} else if (res3 == 1) {
					rcygsty = 1;
				} else {
					rcygsty = -1;
				}
			}else if (payAdvAll.getPayAdvGstTdsDtl().getGstTdsApplicable() == 'N') {
				res1 =	payAdviceTdsService.updatePaymentAdviceRCAppliacble(payAdvAll.getPaymentAdvice());
				res2 =  payAdviceTdsService.addPaymentAdviceTDSDtl(payAdvAll.getPayAdvTdsDtl());
				System.err.println("03 : RES1 : "+res1+", RES2 : "+res2);
				if (res1 == 1 ) {
					rcygstn = 1;
				} else if (res2 == 1 ) {
					rcygstn = 1;
				} else {
					rcygstn = -1;
				}
			}
		}
		
		if (rcy == 1 ) {
			System.err.println("04 : RCY : "+rcy);
			return new ResponseEntity<Integer>(new Integer(1), HttpStatus.OK);
		} else if (rcygsty == 1) {
			System.err.println("04 :  RCYGSTY : "+rcygsty);
			return new ResponseEntity<Integer>(new Integer(1), HttpStatus.OK);
		} else if (rcygstn == 1) {
			System.err.println("04 : RCYGSTN : "+rcygstn);
			return new ResponseEntity<Integer>(new Integer(1), HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(new Integer(-1),HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/get-payAdv-tds-dtl/{logicalLocCd}/{sectionCd}/{adviceNo}")
	@ApiOperation(value = "Get Payment Advice TDS Dtl", response = ResponseEntity.class)
	public ResponseEntity<PaymentAdviceTdsDtl> getPayAdvTdsDtl(
			@ApiParam(value = "Logical Location Code", required = true)
			@PathVariable("logicalLocCd") @NotBlank String logicalLocCd,
			@ApiParam(value = "Section Code", required = true)
			@PathVariable("sectionCd") @NotBlank String sectionCd,
			@ApiParam(value = "Advice Number", required = true)
			@PathVariable("adviceNo") @NotBlank Integer adviceNo){
		
		PaymentAdviceTdsDtl payAdvTdsDtl = new PaymentAdviceTdsDtl();
		
		payAdvTdsDtl = payAdviceTdsService.getPaymentAdviceTDSDtl(logicalLocCd, sectionCd, adviceNo);
		
		if (payAdvTdsDtl != null) {
			return new ResponseEntity<PaymentAdviceTdsDtl>(payAdvTdsDtl, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/get-payAdv-gst-tds-dtl/{logicalLocCd}/{sectionCd}/{adviceNo}")
	@ApiOperation(value = "Get Payment Advice GST TDS Dtl", response = ResponseEntity.class)
	public ResponseEntity<PaymentAdviceGstTdsDtl> getPayAdvGstTdsDtl(
			@ApiParam(value = "Logical Location Code", required = true)
			@PathVariable("logicalLocCd") @NotBlank String logicalLocCd,
			@ApiParam(value = "Section Code", required = true)
			@PathVariable("sectionCd") @NotBlank String sectionCd,
			@ApiParam(value = "Advice Number", required = true)
			@PathVariable("adviceNo") @NotBlank Integer adviceNo){
		
		PaymentAdviceGstTdsDtl payAdvGstTdsDtl = new PaymentAdviceGstTdsDtl();
		
		payAdvGstTdsDtl = payAdviceTdsService.getPaymentAdviceGSTTDSDtl(logicalLocCd, sectionCd, adviceNo);
		
		if (payAdvGstTdsDtl != null) {
			return new ResponseEntity<PaymentAdviceGstTdsDtl>(payAdvGstTdsDtl, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
