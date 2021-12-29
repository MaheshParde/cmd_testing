package in.ecgc.smile.erp.accounts.integrate.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

//while deploying the image.
//@FeignClient(name = "${apigw.service}", contextId = "ExtPaymentAdviceHRDClient", fallbackFactory = ExtPaymentAdviceClientHRDFallback.class)

//In dev/dev-secured profile
//@FeignClient(name = "erp-sys-apigateway",url="http://kmaster.cdacmumbai.in:31335", contextId = "ExtPaymentAdviceHRDClient", fallbackFactory = ExtPaymentAdviceClientHRDFallback.class)

//for local
@FeignClient(name = "erp-hrd-emp-loans-be",url="http://10.210.9.23:11113", contextId = "HRDFeignClient", fallbackFactory = ExtPaymentAdviceClientHRDFallback.class)
public interface ExtPaymentAdviceHRDClient {
	
	//for local
	@PutMapping( "/emp-loans/save-payment-advice-no/{seqNo}/{paymentAdviceNo}")
	
	//for other environments
	//@PutMapping( "/erp-hrd-emp-loans-be/emp-loans/save-payment-advice-no/{seqNo}/{paymentAdviceNo}")
	public String savePaymentAdvice(@PathVariable("seqNo") Integer seqNo,@PathVariable("paymentAdviceNo") String paymentAdviceNo);
}
