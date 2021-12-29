package in.ecgc.smile.erp.accounts.integrate.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import in.ecgc.smile.erp.accounts.integrate.model.VendorManagement;

//@FeignClient(name = "erp-admin-vendor-mgmt-be",url = "http://kmaster.cdacmumbai.in:31996/",fallbackFactory = VendorMgmtClientFallback.class,configuration = FeignErrorDecoder.class)
@FeignClient(name = "${apigw.service}", contextId = "AdminVendorClient", fallbackFactory = VendorMgmtClientFallback.class,configuration = FeignErrorDecoder.class)
public interface VendorMgmtClient {

	 //Get All  Vendors  Request Made : From BE Service

	//@GetMapping("/admin/vendor/view")
	@GetMapping("/erp-admin-vendor-mgmt-be/admin/vendor/view")
	public List<VendorManagement> getAllVendor();
	
	//@GetMapping("/admin/vendor/view/{vendorCode}")
	@GetMapping("/erp-admin-vendor-mgmt-be/admin/vendor/view/{vendorCode}")
	public VendorManagement getVendorByVendorCode(@PathVariable("vendorCode") String vendorCode);

}
