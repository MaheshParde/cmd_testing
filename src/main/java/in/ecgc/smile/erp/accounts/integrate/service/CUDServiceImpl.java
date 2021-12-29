package in.ecgc.smile.erp.accounts.integrate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.integrate.model.ExternalAgency;
import in.ecgc.smile.erp.accounts.integrate.proxy.ExtPaymentAdviceMembershipClient;

@Service
public class CUDServiceImpl implements CUDService{

	@Autowired
	ExtPaymentAdviceMembershipClient extPaymentAdviceMembershipClient;
	
	@Override
	public List<ExternalAgency> getExternalAgencyList() {
		return extPaymentAdviceMembershipClient.getExternalAgencyList();
	}
}
