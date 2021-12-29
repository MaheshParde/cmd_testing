package in.ecgc.smile.erp.accounts.integrate.proxy;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtPaymentAdviceClientHRDFallback implements FallbackFactory<ExtPaymentAdviceHRDClient> {

	@Override
	public ExtPaymentAdviceHRDClient create(Throwable cause) {
		
		return new ExtPaymentAdviceHRDClient() {

			@Override
			public String savePaymentAdvice(Integer seqNo, String paymentAdviceNo) {
				log.info("ExtPaymentAdviceHRDClient#savePaymentAdvice is down");
				return null;
			}
			
		};
	}


}
