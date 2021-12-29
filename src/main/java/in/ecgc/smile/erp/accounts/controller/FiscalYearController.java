package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
import in.ecgc.smile.erp.accounts.service.FiscalYearService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Fiscal Year master REST Controller
 * 
 * @version 1.0 15-September-2020
 * @author Sanjali Kesarkar
 *
 */
@RestController
@RequestMapping("/fiscal-year")
@Api(value = "Fiscal Year service")
public class FiscalYearController {
	
	@Autowired
	FiscalYearService fiscalYearService;
	
	/**
	 * Get current fiscal year details return details of
	 * current fiscal year present in the fiscal year table
	 */
	@GetMapping("/view/current-fiscal-year")
	@ApiOperation(value = "View current fiscal year", response = ResponseEntity.class)
	public ResponseEntity<FiscalYearModel> findCurrentFiscalYear() {
		FiscalYearModel fiscalYearModel = fiscalYearService. findCurrentFiscalYear();
		return new ResponseEntity<>(fiscalYearModel, HttpStatus.OK);
	}
	/**
	 * Get list of fiscal years details return details of
	 * current fiscal year present in the fiscal year table
	 */
	@GetMapping("/list")
	@ApiOperation(value = "Get list of fiscal years", response = ResponseEntity.class)
	public ResponseEntity<List<String>> getFiscalYearList() {
		List<String> fiscalYearList = fiscalYearService.getFiscalYearList();
		return new ResponseEntity<>(fiscalYearList, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	@ApiOperation(value = "Get list of fiscal years", response = ResponseEntity.class)
	public ResponseEntity<String> getTest(HttpServletRequest request, HttpServletResponse response) {
		return new ResponseEntity<>(request.getParameter("date"), HttpStatus.OK);
	}

}
