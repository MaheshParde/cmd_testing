package in.ecgc.smile.erp.accounts.integrate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.exception.ErrorResponse;
import in.ecgc.smile.erp.accounts.integrate.model.DepartmentMaster;
import in.ecgc.smile.erp.accounts.integrate.model.OfficeMaster;
import in.ecgc.smile.erp.accounts.integrate.model.PhysicalLocationMaster;
import in.ecgc.smile.erp.accounts.integrate.proxy.OrgStructClient;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrgStructServiceImpl implements OrgStructService{
	
	@Autowired
	OrgStructClient orgStructClient;
	
	@Override
	public List<OfficeMaster> getOfficeList() {
		
		
		try {

			return orgStructClient.getOfficeDetails();

		} catch (ResponseStatusException ex) { // Exception can be handled here or in Controller

			log.error("Got error:{}", ex.getReason());
			try {
				// Extract ErrorResponse from Exception
				ErrorResponse errorResponse = new ObjectMapper().readValue(ex.getReason(), ErrorResponse.class);

				log.error(errorResponse.toString());

				// throw ex; //Throw if need to be handled in Controller again

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	@Override
	public List<PhysicalLocationMaster> getAllPhysicalLoc() {
		try {

			return orgStructClient.viewPhysicalLocationDetails();

		} catch (ResponseStatusException ex) { // Exception can be handled here or in Controller

			log.error("Got error:{}", ex.getReason());
			try {
				// Extract ErrorResponse from Exception
				ErrorResponse errorResponse = new ObjectMapper().readValue(ex.getReason(), ErrorResponse.class);

				log.error(errorResponse.toString());

				// throw ex; //Throw if need to be handled in Controller again

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	@Override
	public List<DepartmentMaster> getDeptDetails() {
		try {

			return orgStructClient.getDept();

		} catch (ResponseStatusException ex) { // Exception can be handled here or in Controller

			log.error("Got error:{}", ex.getReason());
			try {
				// Extract ErrorResponse from Exception
				ErrorResponse errorResponse = new ObjectMapper().readValue(ex.getReason(), ErrorResponse.class);

				log.error(errorResponse.toString());

				// throw ex; //Throw if need to be handled in Controller again

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	

}
