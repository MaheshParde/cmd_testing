package in.ecgc.smile.erp.accounts.integrate.proxy;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtPaymentAdviceClientMktgFallback implements FallbackFactory<ExtPaymentAdviceMktgClient>  {@Override
	public ExtPaymentAdviceMktgClient create(Throwable cause) {
		return new ExtPaymentAdviceMktgClient() {

			@Override
			public String savePaymentAdvice(Integer seqNo, String paymentAdviceNo) {
				log.info("ExtPaymentAdviceMktgClient#savePaymentAdvice is down");
				return null;
			}
			
		};
	}


}
