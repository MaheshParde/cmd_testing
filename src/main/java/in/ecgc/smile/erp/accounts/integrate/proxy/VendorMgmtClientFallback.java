package in.ecgc.smile.erp.accounts.integrate.proxy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import feign.hystrix.FallbackFactory;
import in.ecgc.smile.erp.accounts.integrate.model.VendorManagement;

public class VendorMgmtClientFallback  implements FallbackFactory<VendorMgmtClient>{

	private static final Logger LOGGER = LoggerFactory.getLogger(VendorMgmtClientFallback.class);

	@Override
	public VendorMgmtClient create(Throwable cause) {
		
		return new VendorMgmtClient() {

			@Override
			public List<VendorManagement> getAllVendor() {
				List<VendorManagement> vendors = null;
				LOGGER.info("--fallback getAllVendor--");
				return vendors;
			}
			@Override
			public VendorManagement getVendorByVendorCode(String vendorCode) {
				LOGGER.info("--fallback getVendorByVendorCode() method--");
				return null;
			}
		};
	}
}

