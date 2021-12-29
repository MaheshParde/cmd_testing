package in.ecgc.smile.erp.accounts.integrate.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

//@FeignClient(name = "erp-mktg-brokerage-fe",url="http://10.212.8.172:11267", contextId = "MarketingFeignClient", fallbackFactory = ExtPaymentAdviceClientMktgFallback.class)
@FeignClient(name = "${apigw.service}", contextId = "MarketingFeignClient", fallbackFactory = ExtPaymentAdviceClientMktgFallback.class)
public interface ExtPaymentAdviceMktgClient {

	@PutMapping("/erp-mktg-brokerage-be/save-payment-advice-no/{seqNo}/{paymentAdviceNo}")
	public String savePaymentAdvice(@PathVariable("seqNo") Integer seqNo,@PathVariable("paymentAdviceNo") String paymentAdviceNo);
}
