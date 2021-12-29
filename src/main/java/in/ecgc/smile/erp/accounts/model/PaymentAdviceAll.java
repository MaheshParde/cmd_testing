package in.ecgc.smile.erp.accounts.model;

import lombok.Data;

@Data
public class PaymentAdviceAll {

	private PaymentAdvice paymentAdvice;
	private PaymentAdviceTdsDtl payAdvTdsDtl;
	private PaymentAdviceGstTdsDtl payAdvGstTdsDtl;
	
}
