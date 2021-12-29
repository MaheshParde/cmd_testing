package in.ecgc.smile.erp.accounts.integrate.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import in.ecgc.smile.erp.accounts.integrate.model.ExternalAgency;

//while deploying the image.
@FeignClient(name = "erp-cud-membership-subscr-be", fallbackFactory = ExtPaymentAdviceClientMembershipFallback.class)

//for local
//@FeignClient(name = "erp-cud-membership-subscr-be", url="http://10.212.9.30", contextId = "ExtPaymentAdviceMembershipClient", fallbackFactory = ExtPaymentAdviceClientMembershipFallback.class)

//In dev/dev-secured profile
//@FeignClient(name = "${apigw.service}",url="http://kmaster.cdacmumbai.in:31335", contextId = "ExtPaymentAdviceMembershipClient", fallbackFactory = ExtPaymentAdviceClientMembershipFallback.class)
public interface ExtPaymentAdviceMembershipClient {
	
	//for local
	//@PutMapping( "/emp-loans/save-payment-advice-no/{seqNo}/{paymentAdviceNo}")
	//for other environments
	
	@PutMapping("/accounts/save-payment-advice-no/{seqNo}/{paymentAdviceNo}")
	//@PutMapping("/erp-cud-membership-subscr-be/accounts/save-payment-advice-no/{seqNo}/{paymentAdviceNo}")
	public String savePaymentAdvice(@PathVariable("seqNo") Integer seqNo,@PathVariable("paymentAdviceNo") String paymentAdviceNo);

	
	@GetMapping("/erp-cud-membership-subscr-be/external-agency-list")
	//@GetMapping("/external-agency-list")
	List<ExternalAgency> getExternalAgencyList();
}
