package in.ecgc.smile.erp.accounts.integrate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.exception.ErrorResponse;
import in.ecgc.smile.erp.accounts.integrate.model.VendorManagement;
import in.ecgc.smile.erp.accounts.integrate.proxy.VendorMgmtClient;

@Service
public class VendorManagementServiceImpl implements VendorManagementService {
	
	private static final Logger log = LoggerFactory.getLogger(VendorManagementServiceImpl.class);
	
	@Autowired
	private VendorMgmtClient vendorMgmtClient;

	@Override
	public List<VendorManagement> getAllVendors() {
		log.info("--- getAllVendors() method called ---");
		//List<VendorManagement> vendorList=null;
		try {
			
			List<VendorManagement> list=vendorMgmtClient.getAllVendor();
			
			log.info("---> List Of vendor data :{}",list);
			
			return list;
			
		} catch (ResponseStatusException ex) {	//Exception can be handled here or in Controller
			log.error("Got error: {} ",ex.getReason());
			try {
				//Extract ErrorResponse from Exception
				ErrorResponse errorResponse = new ObjectMapper().readValue(ex.getReason(), ErrorResponse.class);
				log.error("Error: {} ",errorResponse);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	

	@Override
	public VendorManagement getVendorById(String vendorCode) {
		log.info("--- getVendorById method called ---");
     try {
			log.info("Vendor code : {}",vendorCode);
			VendorManagement vendor=vendorMgmtClient.getVendorByVendorCode(vendorCode);
			
			log.info("--> 1 Vendor Information : {}",vendor);
			
			return vendor;
			
		} catch (ResponseStatusException ex) {	//Exception can be handled here or in Controller
			log.error("Got error: {} ",ex.getReason());
			try {
				//Extract ErrorResponse from Exception
				ErrorResponse errorResponse = new ObjectMapper().readValue(ex.getReason(), ErrorResponse.class);
				log.error("Error: {} ",errorResponse);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

