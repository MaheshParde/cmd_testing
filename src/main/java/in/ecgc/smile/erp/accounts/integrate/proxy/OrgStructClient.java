package in.ecgc.smile.erp.accounts.integrate.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import in.ecgc.smile.erp.accounts.integrate.model.DepartmentMaster;
import in.ecgc.smile.erp.accounts.integrate.model.OfficeMaster;
import in.ecgc.smile.erp.accounts.integrate.model.PhysicalLocationMaster;
import in.ecgc.smile.erp.accounts.integrate.model.StateMaster;



//@FeignClient(name = "${apigw.service}", contextId = "OrgStructClient", fallbackFactory = OrgStructClientFallback.class)
//@FeignClient(name = "erp-hrd-org-struct-be", url = "http://kmaster.cdacmumbai.in:31976", contextId = "OrgStructClient")
@FeignClient(name = "erp-sys-apigateway", contextId = "OrgStructClient", fallbackFactory = OrgStructClientFallback.class)
public interface OrgStructClient {

//	@GetMapping("/get-state/A")
	@GetMapping("/erp-hrd-org-struct-be/get-state/A")
	List<StateMaster> getState();
	
//	@GetMapping("/get-office-details/A")
	@GetMapping("/erp-hrd-org-struct-be/get-office-details/A")
	List<OfficeMaster> getOfficeDetails();

	
//	@GetMapping("/physical-location-details/A")
	@GetMapping("/erp-hrd-org-struct-be/physical-location-details/A")
	List<PhysicalLocationMaster> viewPhysicalLocationDetails();
	
//	@GetMapping("/get-dept-details/A")
	@GetMapping("/erp-hrd-org-struct-be/get-dept-details/A")
	public List<DepartmentMaster> getDept();
	
}
