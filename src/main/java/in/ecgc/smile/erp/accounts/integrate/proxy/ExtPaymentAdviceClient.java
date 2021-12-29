package in.ecgc.smile.erp.accounts.integrate.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "erp-accounts-budget-be",url="http://localhost:11197", contextId = "BudgetFeignClient", fallbackFactory = ExtPaymentAdviceClientFallback.class)
public interface ExtPaymentAdviceClient {

	@PutMapping("/lov/save-payment-advice-no/{seqNo}/{paymentAdviceNo}")
	public String savePaymentAdvice(@PathVariable("seqNo") Integer seqNo,@PathVariable("paymentAdviceNo") String paymentAdviceNo);
}
