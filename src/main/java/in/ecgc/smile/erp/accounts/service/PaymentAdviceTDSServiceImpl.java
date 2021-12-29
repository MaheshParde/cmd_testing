package in.ecgc.smile.erp.accounts.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.model.PaymentAdvice;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceGstTdsDtl;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceTcodes;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceTdsDtl;
import in.ecgc.smile.erp.accounts.repository.PaymentAdviceDao;
import in.ecgc.smile.erp.accounts.repository.PaymentAdviceTDSDao;

@Service
public class PaymentAdviceTDSServiceImpl implements PaymentAdviceTDSService{

	@Autowired
	PaymentAdviceTDSDao payAdviceTdsDao;

	@Override
	public PaymentAdvice getApprovedPaymentAdviceDtl(String logicalLocCd, String sectionCd, Integer adviceNo) {
		return payAdviceTdsDao.getApprovedPaymentAdviceDtl(logicalLocCd, sectionCd, adviceNo);
	}

	@Override
	public List<PaymentAdvice> getApprovedPaymentAdvices(String logicalLocCd,String sectionCd,LocalDate fromDt, LocalDate toDt) {
		return payAdviceTdsDao.getApprovedPaymentAdvices( logicalLocCd, sectionCd, fromDt,  toDt);
	}

	@Override
	public Integer updatePaymentAdviceTdsNOTAppliacble(PaymentAdvice paymentAdvice) {
		return payAdviceTdsDao.updatePaymentAdviceTdsNOTAppliacble(paymentAdvice);
	}

	@Override
	public Integer updatePaymentAdviceRCAppliacble(PaymentAdvice paymentAdvice) {
		return payAdviceTdsDao.updatePaymentAdviceRCAppliacble(paymentAdvice);
	}

	@Override
	public Integer addPaymentAdviceTDSDtl(PaymentAdviceTdsDtl payAdvTdsDtl) {
		return payAdviceTdsDao.addPaymentAdviceTDSDtl(payAdvTdsDtl);
	}

	@Override
	public Integer addPaymentAdviceGSTTDSDtl(PaymentAdviceGstTdsDtl payAdvGstTdsDtl) {
		return payAdviceTdsDao.addPaymentAdviceGSTTDSDtl(payAdvGstTdsDtl);
	}

	@Override
	public PaymentAdviceTdsDtl getPaymentAdviceTDSDtl(String logicalLocCd, String sectionCd, Integer adviceNo) {
		return payAdviceTdsDao.getPaymentAdviceTDSDtl(logicalLocCd, sectionCd, adviceNo);
	}

	@Override
	public PaymentAdviceGstTdsDtl getPaymentAdviceGSTTDSDtl(String logicalLocCd, String sectionCd, Integer adviceNo) {
		return payAdviceTdsDao.getPaymentAdviceGSTTDSDtl(logicalLocCd, sectionCd, adviceNo);
	}
}
