package in.ecgc.smile.erp.accounts.integrate.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.integrate.model.VendorManagement;

public interface VendorManagementService {
	
	List<VendorManagement> getAllVendors();
	VendorManagement getVendorById(String vendorCode);

}
