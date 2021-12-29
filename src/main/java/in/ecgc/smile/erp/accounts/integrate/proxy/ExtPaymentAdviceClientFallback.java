package in.ecgc.smile.erp.accounts.integrate.proxy;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtPaymentAdviceClientFallback implements FallbackFactory<ExtPaymentAdviceClient> {

	@Override
	public ExtPaymentAdviceClient create(Throwable cause) {
		return new ExtPaymentAdviceClient() {

			@Override
			public String savePaymentAdvice(Integer seqNo, String paymentAdviceNo) {
				log.info("ExtPaymentAdviceClient#savePaymentAdvice is down");
				return null;
			}
			
		};
	}

	
}
