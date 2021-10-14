package in.ecgc.smile.erp.accounts.integrate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public String savePaymentAdvice(Integer seqNo,Integer moduleCd, String paymentAdviceNo) {
		if(moduleCd == 5) {
			return extPaymentAdviceClient.savePaymentAdvice(seqNo, paymentAdviceNo);
		}else if(moduleCd == 7){
			return extPaymentAdviceMktgClient.savePaymentAdvice(seqNo, paymentAdviceNo);
		}else if(moduleCd == 9) {
			return extPaymentAdviceHRDClient.savePaymentAdvice(seqNo, paymentAdviceNo);
		}else if(moduleCd == 2) {
			return extPaymentAdviceMembershipClient.savePaymentAdvice(seqNo, paymentAdviceNo);
		}
		return null;
	}

}
