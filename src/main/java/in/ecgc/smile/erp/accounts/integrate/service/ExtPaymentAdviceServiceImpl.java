package in.ecgc.smile.erp.accounts.integrate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.PaymentAdviceCreateException;
import in.ecgc.smile.erp.accounts.integrate.proxy.ExtPaymentAdviceClient;
import in.ecgc.smile.erp.accounts.integrate.proxy.ExtPaymentAdviceHRDClient;
import in.ecgc.smile.erp.accounts.integrate.proxy.ExtPaymentAdviceMembershipClient;
import in.ecgc.smile.erp.accounts.integrate.proxy.ExtPaymentAdviceMktgClient;

@Service
public class ExtPaymentAdviceServiceImpl implements ExtPaymentAdviceService {

	@Autowired
	ExtPaymentAdviceClient extPaymentAdviceClient;
	
	@Autowired
	ExtPaymentAdviceMktgClient extPaymentAdviceMktgClient;
	
	@Autowired
	ExtPaymentAdviceHRDClient extPaymentAdviceHRDClient; 
	
	@Autowired
	ExtPaymentAdviceMembershipClient extPaymentAdviceMembershipClient;
	
	@Override
	public String savePaymentAdvice(Integer seqNo,String moduleCd, String paymentAdviceNo) {
		try {
			if(moduleCd.equals("5")) {
				return extPaymentAdviceClient.savePaymentAdvice(seqNo, paymentAdviceNo);
			}else if(moduleCd.equals("MKTBRKG")){
				return extPaymentAdviceMktgClient.savePaymentAdvice(seqNo, paymentAdviceNo);
			}else if(moduleCd.equals("EMPLOANS")) {
				return extPaymentAdviceHRDClient.savePaymentAdvice(seqNo, paymentAdviceNo);
			}else if(moduleCd.equals("CUDMEMSUB")) {
				return extPaymentAdviceMembershipClient.savePaymentAdvice(seqNo, paymentAdviceNo);
			}
			return null;
		} catch (Exception e) {
			throw new PaymentAdviceCreateException("Failed to insert value in module code :"+moduleCd);
		}
	}

}
