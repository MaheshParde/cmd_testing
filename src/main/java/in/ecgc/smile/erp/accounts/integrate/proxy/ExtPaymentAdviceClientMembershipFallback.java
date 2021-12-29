package in.ecgc.smile.erp.accounts.integrate.proxy;

import java.util.List;

import feign.hystrix.FallbackFactory;
import in.ecgc.smile.erp.accounts.integrate.model.ExternalAgency;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtPaymentAdviceClientMembershipFallback implements FallbackFactory<ExtPaymentAdviceMembershipClient> {


	@Override
	public ExtPaymentAdviceMembershipClient create(Throwable cause) {
		return new ExtPaymentAdviceMembershipClient() {

			@Override
			public String savePaymentAdvice(Integer seqNo, String paymentAdviceNo) {
				log.info("--ExtPaymentAdviceClientMembershipFallback#savePaymentAdvice is down--");
				return null;
			}

			@Override
			public List<ExternalAgency> getExternalAgencyList() {
				log.info("--ExtPaymentAdviceClientMembershipFallback#getExternalAgencyList is down--");
				return null;
			}};
	}

}
