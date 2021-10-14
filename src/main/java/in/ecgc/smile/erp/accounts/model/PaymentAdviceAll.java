package in.ecgc.smile.erp.accounts.model;

import lombok.Data;

@Data
public class PaymentAdviceAll {

	private PaymentAdvice paymentAdvice;
	private PaymentAdviceTdsDtl payAdvTdsDtl;
	private PaymentAdviceGstTdsDtl payAdvGstTdsDtl;
	
	public PaymentAdviceAll(PaymentAdvice paymentAdvice, PaymentAdviceTdsDtl payAdvTdsDtl,
			PaymentAdviceGstTdsDtl payAdvGstTdsDtl) {
		super();
		this.paymentAdvice = paymentAdvice;
		this.payAdvTdsDtl = payAdvTdsDtl;
		this.payAdvGstTdsDtl = payAdvGstTdsDtl;
	}
	
	
}
