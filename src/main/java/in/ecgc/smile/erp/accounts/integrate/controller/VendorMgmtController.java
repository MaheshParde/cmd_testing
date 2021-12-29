package in.ecgc.smile.erp.accounts.integrate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.integrate.model.VendorManagement;
import in.ecgc.smile.erp.accounts.integrate.service.VendorManagementService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
public class VendorMgmtController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VendorMgmtController.class);

	@Autowired
	private VendorManagementService vendorMgmtService;
	
	@ApiOperation(value = "view all Vendor ", response = VendorManagement.class, responseContainer = "List")
	@GetMapping(value = "/vendor/view")
	public ResponseEntity<List<VendorManagement>> getAllVendor() {
		LOGGER.info("Entered into getAllVendor() method");

		List<VendorManagement> response = vendorMgmtService.getAllVendors();
		
		LOGGER.info("-->List of Vendor values:{}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "View  Vendor details")
	@GetMapping(value = "/vendor/view/{vendorCode}")
	public ResponseEntity<VendorManagement> getVendorByVendorCode(@PathVariable("vendorCode") String vendorCode) {
		LOGGER.info("---Vendor code :{}", vendorCode);
		LOGGER.info("--- getVendorByVendorCode method called--");
		VendorManagement vendorManagement = vendorMgmtService.getVendorById(vendorCode);

		return new ResponseEntity<>(vendorManagement, HttpStatus.OK);
	}

}
