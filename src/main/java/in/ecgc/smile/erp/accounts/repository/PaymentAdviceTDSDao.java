package in.ecgc.smile.erp.accounts.repository;

import java.time.LocalDate;
import java.util.List;

import in.ecgc.smile.erp.accounts.model.PaymentAdvice;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceGstTdsDtl;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceTcodes;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceTdsDtl;

public interface PaymentAdviceTDSDao {

	List<PaymentAdvice> getApprovedPaymentAdvices(String logicalLocCd,String sectionCd,LocalDate fromDt, LocalDate toDt);
	PaymentAdvice getApprovedPaymentAdviceDtl(String logicalLocCd,String sectionCd,Integer adviceNo);
	Integer updatePaymentAdviceTdsNOTAppliacble(PaymentAdvice paymentAdvice);
	Integer updatePaymentAdviceRCAppliacble(PaymentAdvice paymentAdvice);
	Integer addPaymentAdviceTDSDtl(PaymentAdviceTdsDtl payAdvTdsDtl);
	Integer addPaymentAdviceGSTTDSDtl(PaymentAdviceGstTdsDtl payAdvGstTdsDtl);
	PaymentAdviceTdsDtl getPaymentAdviceTDSDtl(String logicalLocCd,String sectionCd,Integer adviceNo);
	PaymentAdviceGstTdsDtl getPaymentAdviceGSTTDSDtl(String logicalLocCd,String sectionCd,Integer adviceNo);
}
