package in.ecgc.smile.erp.accounts.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.integrate.model.OfficeMaster;
import in.ecgc.smile.erp.accounts.integrate.service.OrgStructService;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.service.CalendarService;
import in.ecgc.smile.erp.accounts.service.FiscalYearService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
@RestController
@RequestMapping("/calendar")
@Api(value = "Calendar Master service")

public class CalendarMasterController {

	@Autowired
	CalendarService calendarService; 
	
	@Autowired
	OrgStructService orgStructService;
	
	@Autowired
	FiscalYearService fiscalYearService;
	private static final Logger LOGGER = LoggerFactory.getLogger(EntityGlMasterController.class);
	
	@PostMapping("/add")
	@ApiOperation(value = "Add New Calendar", response = ResponseEntity.class)
	public ResponseEntity<String> addCalender(@Valid @RequestBody Calendar calender){
		Integer calId =  calendarService.addCalendar(calender);
			if(calId==1)
				return new ResponseEntity<>(calender.getCalendarId(),HttpStatus.OK);
			return null;
	}
	
	@GetMapping("/getcalendar/{cd}")
	@ApiOperation(value = "Get Calendar", response = ResponseModel.class)
	public ResponseEntity<Calendar> getCalendar(@PathVariable("cd") String calendarCode) {
		return new ResponseEntity<Calendar>(calendarService.getCalendar(calendarCode),HttpStatus.OK);
	}

	
	@GetMapping("/get/{fiscalYr}/{month}")
	@ApiOperation(value = "Get all calendar", response = ResponseModel.class)
	public ResponseEntity<List<Calendar>> getAllCalendar(@PathVariable String fiscalYr,@PathVariable String month) {
		return new ResponseEntity<List<Calendar>>(calendarService.getAllCalendar(fiscalYr,month),HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecalendar/{cd}")
	@ApiOperation(value = "Delete calendar", response = ResponseModel.class)
	public ResponseEntity<Integer> deletecalendar(@PathVariable("cd") String calendarCode) {
		return new ResponseEntity<Integer>(calendarService.deleteCalendar(calendarCode),HttpStatus.OK);
	}
	
	@GetMapping("/get/{fiscalYr}/{month}/{logicalLocCode}")
	public ResponseEntity<List<Calendar>> getByFiscalYr(@PathVariable("fiscalYr") String fiscalYr,
			@PathVariable("month") String month, @PathVariable("logicalLocCode") String logicalLocCode){
		
			return new ResponseEntity<List<Calendar>>(calendarService.getByFiscalYr(fiscalYr, month, logicalLocCode),HttpStatus.OK);
	}
	
	@GetMapping("/update/{first}/{second}/{status}")
	public Integer updateStatus(@PathVariable("first") String first,@PathVariable("second") String second,
			@PathVariable("status") String status){
		
		LOGGER.info("inside update status");
		
		if(status.equals("open"))
		{
			calendarService.updateStatus1(first,"Y");
			calendarService.updateStatus2(second, "N");
		} 
		else if(status.equals("close")) 
		{
			calendarService.updateStatus1(first,"N");
			calendarService.updateStatus2(second, "Y");
		}
		
			return new Integer(1);
	}
	
	
	@GetMapping("/update/{prevyr}/{status}")
	public Integer updateprv(@PathVariable("prevyr") String prevyr,
			@PathVariable("status") String status) {
		
		Integer res = null;
		
		if(status.equals("open"))
		{
			LOGGER.info("If Open Called");;
			res = calendarService.updatePrev(prevyr,"Y");
		} 
		else if(status.equals("close")) 
		{
			LOGGER.info("if Close called");
			res = calendarService.updatePrev(prevyr,"N");
		}
		
		return res;
	}
	
	//Monthly opening and closing working (Except PV,JV and IB)
	@GetMapping("/monthlyopeningclosing/regular")
	@ApiOperation(value = "Monthly calendar opening", response = ResponseEntity.class)
	public ResponseEntity<Integer> monthlyOpeningClosingRegular() {
			LOGGER.info("--Monthly calendar opening --");
			return new ResponseEntity<Integer>(calendarService.monthlyOpeningClosingRegular(),HttpStatus.OK);
	}
	
	//Monthly opening and closing working (For PV,JV and IB)
	@GetMapping("/monthlyopeningclosing/configured")
	@ApiOperation(value = "Monthly calendar opening", response = ResponseEntity.class)
	public ResponseEntity<Integer> monthlyOpeningClosingConfigured() {
			LOGGER.info("--Monthly calendar opening --");
			return new ResponseEntity<Integer>(calendarService.monthlyOpeningClosingConfigured(),HttpStatus.OK);

		}

	
	@GetMapping("/listLocations")
	public ResponseEntity<List<String>> listAllHeaders() {
		List<OfficeMaster> officeList = orgStructService.getOfficeList();
		List<String> logicalLocList  = officeList.stream()
										.map(OfficeMaster::getOfficeId)
										.collect(Collectors.toList());
		LOGGER.info("Logical location list : {}",logicalLocList);
		return new ResponseEntity<>(logicalLocList,HttpStatus.OK);
	}
	
	@GetMapping("/marchClose/{logicalCode}/{currentFiscalyr}")
	@ApiOperation(value = "March calendar closing", response = ResponseEntity.class)
	public ResponseEntity<Integer> marchClosing(@PathVariable("logicalCode") String logicalCode,
			@PathVariable("currentFiscalyr") String currentFiscalyr){
			LOGGER.info("Inside CalendarMasterController#marchClosing ");
//			LOGGER.info("Parameter logicalCode {}   ",logicalCode);
//			LOGGER.info("Parameter currentFiscalyr {}   ",currentFiscalyr);
			return new ResponseEntity<Integer>(calendarService.marchClosing(logicalCode, currentFiscalyr),HttpStatus.OK) ;
	}
	
	@GetMapping("/marchCloseStatus/{logicalCode}/{currentFiscalyr}")
	@ApiOperation(value = "March calendar closing status", response = ResponseEntity.class)
	public ResponseEntity<Boolean> marchClosingStatus(@PathVariable("logicalCode") String logicalCode,
			@PathVariable("currentFiscalyr") String currentFiscalyr){
			LOGGER.info("Inside CalendarMasterController#marchClosingStatus ");
			LOGGER.info("Parameter logicalCode {}   ",logicalCode);
			LOGGER.info("Parameter currentFiscalyr {}   ",currentFiscalyr);
			return new ResponseEntity<Boolean>(calendarService.marchClosingStatus(logicalCode, currentFiscalyr),HttpStatus.OK) ;
	}

	
	@GetMapping("/getByGlType/{fiscalYr}/{month}/{logicalLocCode}/{glTxnType}")
	public ResponseEntity<Calendar> getByFiscalYr(@PathVariable("fiscalYr") String fiscalYr,
			@PathVariable("month") String month, @PathVariable("logicalLocCode") String logicalLocCode , 
			@PathVariable("glTxnType") String glTxnType){
		
			return new ResponseEntity<Calendar>(calendarService.getByGlTypeLogicalLoc(fiscalYr, month, logicalLocCode, glTxnType),HttpStatus.OK);
	}
	
	
}
