package in.ecgc.smile.erp.accounts.integrate.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

//while deploying the image.
@FeignClient(name = "erp-sys-apigateway", contextId = "ExtPaymentAdviceMembershipClient", fallbackFactory = ExtPaymentAdviceClientMembershipFallback.class)
//@FeignClient(name = "erp-cud-membership-subscr-be", url="http://10.212.9.30", contextId = "ExtPaymentAdviceMembershipClient", fallbackFactory = ExtPaymentAdviceClientMembershipFallback.class)

//In dev/dev-secured profile
//@FeignClient(name = "erp-sys-apigateway",url="http://kmaster.cdacmumbai.in:31335", contextId = "ExtPaymentAdviceHRDClient", fallbackFactory = ExtPaymentAdviceClientHRDFallback.class)

//for local
//@FeignClient(name = "erp-hrd-emp-loans-be",url="http://10.210.9.210:11112", contextId = "HRDFeignClient", fallbackFactory = ExtPaymentAdviceClientHRDFallback.class)
public interface ExtPaymentAdviceMembershipClient {
	
	//for local
//	@PutMapping( "/emp-loans/save-payment-advice-no/{seqNo}/{paymentAdviceNo}")
	
	//for other environments
	@PutMapping( "erp-cud-membership-subscr-be/accounts/save-payment-advice-no/{seqNo}/{paymentAdviceNo}")
	public String savePaymentAdvice(@PathVariable("seqNo") Integer seqNo,@PathVariable("paymentAdviceNo") String paymentAdviceNo);
}
