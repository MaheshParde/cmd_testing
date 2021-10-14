package in.ecgc.smile.erp.accounts.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.model.PaymentAdvice;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceTcodes;
import in.ecgc.smile.erp.accounts.repository.PaymentAdviceDao;

@Service
public class PaymentAdviceServiceImpl implements PaymentAdviceService{

	@Autowired
	PaymentAdviceDao payAdviceDao;
	
	@Override
	public Integer getAdviceNo(String logicalLocCd, String sectionCd, String fiscalYear) {
		return payAdviceDao.getAdviceNo(logicalLocCd, sectionCd, fiscalYear);
	}

	@Override
	public Integer addPaymentAdvice(PaymentAdvice paymentAdvice) {
		Integer res = payAdviceDao.addPaymentAdvice(paymentAdvice);
		if (res == 1) {
			return payAdviceDao.addPaymentAdviceTcodes(paymentAdvice);
		} else {
			return 0;
		}
	}

	@Override
	public List<PaymentAdvice> listAllPaymentAdvice() {
		return payAdviceDao.listAllPaymentAdvice();
	}

	@Override
	public PaymentAdvice getPaymentAdviceByAdviceDtl(String logicalLocCd,String sectionCd,Integer adviceNo) {
		return payAdviceDao.getPaymentAdviceByAdviceDtl(logicalLocCd, sectionCd, adviceNo);
	}

	@Override
	public Integer updateSeqNo(String logicalLocCd, String sectionCd, String fiscalYear, Integer adviceNo) {
		return payAdviceDao.updateSeqNo(logicalLocCd, sectionCd, fiscalYear, adviceNo);
	}

	@Override
	public Integer disablePaymentAdvice(String logicalLocCd,String sectionCd,Integer adviceNo) {
		Integer res =  payAdviceDao.disablePaymentAdvice(logicalLocCd, sectionCd, adviceNo);
		if (res == 1) {
			return payAdviceDao.disablePaymentAdviceTcodes(logicalLocCd, sectionCd, adviceNo);
		} else {
			return 0;
		}
	}

	@Override
	public Integer updatePaymentAdvice(PaymentAdvice paymentAdvice) {
		return  payAdviceDao.updatePaymentAdvice(paymentAdvice);
	}
	
	@Override
	public Integer updatePaymentAdviceTcodes(PaymentAdvice paymentAdvice) {
		return  payAdviceDao.updatePaymentAdviceTcodes(paymentAdvice);
	}

	@Override
	public List<PaymentAdvice> listAllPaymentAdviceTcodes() {
		return payAdviceDao.listAllPaymentAdviceTcodes();
	}

	@Override
	public PaymentAdvice getPaymentAdviceTcodesByAdviceDtl(String logicalLocCd,String sectionCd,Integer adviceNo) {
		return payAdviceDao.getPaymentAdviceTcodesByAdviceDtl(logicalLocCd, sectionCd, adviceNo);
	}

	@Override
	public Integer disablePaymentAdviceTcodes(String logicalLocCd,String sectionCd,Integer adviceNo) {
		return payAdviceDao.disablePaymentAdviceTcodes(logicalLocCd, sectionCd, adviceNo);
	}

	@Override
	public List<PaymentAdvice> getPaymentAdviceByLogicalLocSectionCd(String logicalLocCd, String sectionCd,LocalDate fromDt, LocalDate toDt) {
		return payAdviceDao.getPaymentAdviceByLogicalLocSectionCd(logicalLocCd, sectionCd, fromDt, toDt);
	}

	@Override
	public PaymentAdviceTcodes getPaymentAdviceTcodesDtl(String logicalLocCd, String sectionCd, Integer adviceNo) {
		return payAdviceDao.getPaymentAdviceTcodesDtl(logicalLocCd, sectionCd, adviceNo);
	}

	@Override
	public List<PaymentAdvice> getEntrPaymentAdvice(String logicalLocCd, String sectionCd, String adviceStat) {
		return payAdviceDao.getEntrPaymentAdvice(logicalLocCd, sectionCd, adviceStat);
	}

	@Override
	public Integer takeDecisionOnPaymentAdvice(PaymentAdvice paymentAdvice) {
		return payAdviceDao.takeDecisionOnPaymentAdvice(paymentAdvice);
	}

	
}
