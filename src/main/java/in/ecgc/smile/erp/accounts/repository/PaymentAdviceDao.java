package in.ecgc.smile.erp.accounts.repository;

import java.time.LocalDate;
import java.util.List;

import in.ecgc.smile.erp.accounts.model.PaymentAdvice;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceTcodes;

public interface PaymentAdviceDao {

	Integer getAdviceNo(String logicalLocCd,String sectionCd,String fiscalYear);
	public Integer updateSeqNo(String logicalLocCd,String sectionCd,String fiscalYear, Integer adviceNo);
	public Integer addPaymentAdvice(PaymentAdvice paymentAdvice);
	public Integer addPaymentAdviceTcodes(PaymentAdvice paymentAdvice);
	public List<PaymentAdvice> listAllPaymentAdvice();
	public List<PaymentAdvice> listAllPaymentAdviceTcodes();
	PaymentAdvice getPaymentAdviceByAdviceDtl(String logicalLocCd,String sectionCd,Integer adviceNo);
	List<PaymentAdvice> getPaymentAdviceByLogicalLocSectionCd(String logicalLocCd,String sectionCd,LocalDate fromDt, LocalDate toDt);
	PaymentAdvice getPaymentAdviceTcodesByAdviceDtl(String logicalLocCd,String sectionCd,Integer adviceNo);
	public Integer disablePaymentAdvice(String logicalLocCd,String sectionCd,Integer adviceNo);
	public Integer disablePaymentAdviceTcodes(String logicalLocCd,String sectionCd,Integer adviceNo);
	public Integer updatePaymentAdvice(PaymentAdvice paymentAdvice);
	PaymentAdviceTcodes getPaymentAdviceTcodesDtl(String logicalLocCd, String sectionCd, Integer adviceNo);
	Integer updatePaymentAdviceTcodes(PaymentAdvice paymentAdvice);
	List<PaymentAdvice> getEntrPaymentAdvice(String logicalLocCd,String sectionCd,String adviceStat);
	Integer takeDecisionOnPaymentAdvice(PaymentAdvice paymentAdvice);
}
