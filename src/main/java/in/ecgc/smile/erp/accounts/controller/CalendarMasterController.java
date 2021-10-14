package in.ecgc.smile.erp.accounts.controller;

import java.util.ArrayList;
import java.util.List;

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

import in.ecgc.smile.erp.accounts.exception.CalendarMonthlyOpeningClosingException;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.DateOperation;
import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
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
	FiscalYearService fiscalYearService;
	private static final Logger LOGGER = LoggerFactory.getLogger(EntityGlMasterController.class);
	
	/*
	@PostMapping("/add")
	@ApiOperation(value = "Add New Calendar", response = ResponseModel.class)
	public ResponseModel<Integer> Addcalendar(@RequestBody Calendar calendar ){
		ResponseModel<Integer> model = new ResponseModel<Integer>();
		System.out.println("inside add Calendar");
		try {
			LOGGER.info(calendar.toString());
			if ( calendarService.addCalendar(calendar) == 1) {
				model.setData(1);
				model.setStatusMessage("Calendar added successfully");
				model.setHttpStatus(HttpStatus.OK);
				LOGGER.debug("CalendarMasterServiceController");
				}
			else {
				model.setHttpStatus(HttpStatus.BAD_REQUEST);
				model.setStatusMessage("failed to add Calendar");
			}
		}
		catch (Exception e) {
			model.setHttpStatus(HttpStatus.BAD_REQUEST);
			model.setStatusMessage(e.getMessage());
			LOGGER.error("Exception thrown- calendarCodeException ..Incomplete calendar Data provided");
		}
		return model;
	}
	*/
	
	@PostMapping("/add")
	@ApiOperation(value = "Add New Calendar", response = ResponseEntity.class)
	public ResponseEntity<Calendar> addCalender(@Valid @RequestBody Calendar calender){
		calendarService.addCalendar(calender);
		return new ResponseEntity<>(calender,HttpStatus.OK);
	}
	
	@GetMapping("/getcalendar/{cd}")
	@ApiOperation(value = "Get Calendar", response = ResponseModel.class)
	public ResponseEntity<Calendar> getCalendar(@PathVariable("cd") String calendarCode) {
		ResponseEntity<Calendar> model;
		Calendar calendar = null;
		FiscalYearModel fiscalYearModel = fiscalYearService.findCurrentFiscalYear();
		try {
			LOGGER.info("--insede get calendar--");
			if ((calendar = calendarService.getCalendar(calendarCode)) != null) {
				model = new ResponseEntity<Calendar>(calendar,HttpStatus.OK);
//				model.setData(calendar);
//				model.setStatusMessage("Calenar details successfully fetched");
//				model.setHttpStatus(HttpStatus.OK);
				LOGGER.debug("--CalendarMasterServiceController--");
				}
			else {
				model = new ResponseEntity<Calendar>(HttpStatus.BAD_REQUEST);
//				model.setHttpStatus(HttpStatus.BAD_REQUEST);
//				model.setStatusMessage("failed to get details");
			}
		}
		catch (Exception e) {
			model = new ResponseEntity<Calendar>(HttpStatus.BAD_REQUEST);
//			model.setHttpStatus(HttpStatus.BAD_REQUEST);
//			model.setStatusMessage(e.getMessage());
			LOGGER.error("Exception thrown- CalendarException ..failed to fetch");
			LOGGER.error(e.getMessage());
		}
		return model;


	}

	
	@GetMapping("/get/{fiscalYr}/{month}")
	@ApiOperation(value = "Get all calendar", response = ResponseModel.class)
	public ResponseEntity<List<Calendar>> getAllCalendar(@PathVariable String fiscalYr,@PathVariable String month) {
		ResponseEntity<List<Calendar>> model;
		List<Calendar> allCalendar = null ;
		try {
			LOGGER.info("--insede get all calendar--");
			FiscalYearModel fiscalYearModel = fiscalYearService.findCurrentFiscalYear(); 
			fiscalYr = fiscalYearModel.getCurrFiscalYear();
			 month = "sep";
			
			if ((allCalendar = calendarService.getAllCalendar(fiscalYr,month)) != null) {
//				model.setData(allCalendar);
//				model.setStatusMessage(" successfully fetched all Calendar");
//				model.setHttpStatus(HttpStatus.OK);
				model = new ResponseEntity<List<Calendar>>(allCalendar,HttpStatus.OK);
				LOGGER.debug("--calendarMasterServiceController--");
				}
			else {
//				model.setHttpStatus(HttpStatus.BAD_REQUEST);
//				model.setStatusMessage("failed to get details");
				model = new ResponseEntity<List<Calendar>>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
//			model.setHttpStatus(HttpStatus.BAD_REQUEST);
//			model.setStatusMessage(e.getMessage());
			model = new ResponseEntity<List<Calendar>>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- calendarCodeException ..failed to fetch");
		}
		return model;
	}
	
	@DeleteMapping("/deletecalendar/{cd}")
	@ApiOperation(value = "Delete calendar", response = ResponseModel.class)
	public ResponseModel<Integer> deletecalendar(@PathVariable("cd") String calendarCode) {
		ResponseModel<Integer> model = new ResponseModel<Integer>();
		try {
			LOGGER.info("--insede delete calendar--");
			if ((calendarService.deleteCalendar(calendarCode)) == 1) {
				model.setData(1);
				model.setStatusMessage("calendar successfully deleted");
				model.setHttpStatus(HttpStatus.OK);
				LOGGER.debug("--calendarMasterServiceController--");
				}
			else {
				model.setData(-1);
				model.setHttpStatus(HttpStatus.BAD_REQUEST);
				model.setStatusMessage("failed ");
			}
		}
		catch (Exception e) {
			model.setHttpStatus(HttpStatus.BAD_REQUEST);
			model.setStatusMessage(e.getMessage());
			LOGGER.error("Exception thrown- calendarCodeException ..failed to delete");
		}
		return model;


	}
/*
	@GetMapping("/get/{fiscalYr}/{month}/{logicalLocCode}")
	public ResponseEntity<List<List<Calendar>>> getByFiscalYr(@PathVariable("fiscalYr") String fiscalYr,
			@PathVariable("month") String month, @PathVariable("logicalLocCode") String logicalLocCode){
		
		
		List<List<Calendar>> calList = new ArrayList<>();
		DateOperation dateop = new DateOperation(month);
		String currentMonth = dateop.currentMonth;
		String prevMonth = dateop.prevMonth;
		
		Integer prevfiscalyr = Integer.valueOf(fiscalYr) - 1;
		
		List<Calendar> allCalendar = calendarService.getByFiscalYr(fiscalYr, currentMonth, logicalLocCode);
		List<Calendar> allCalendar2 = calendarService.getByFiscalYr(fiscalYr, prevMonth, logicalLocCode);
		List<Calendar> allCalendar3 = calendarService.getByFiscalYr(prevfiscalyr.toString(), "mar", logicalLocCode);
		
		calList.add(allCalendar);
		calList.add(allCalendar2);
		calList.add(allCalendar3);
		
		if (allCalendar != null)
			return new ResponseEntity<>(calList ,HttpStatus.OK);
		else 
			return new ResponseEntity<List<List<Calendar>>>(HttpStatus.NO_CONTENT);
	}
	*/
	
	@GetMapping("/get/{fiscalYr}/{month}/{logicalLocCode}")
	public ResponseEntity<List<Calendar>> getByFiscalYr(@PathVariable("fiscalYr") String fiscalYr,
			@PathVariable("month") String month, @PathVariable("logicalLocCode") String logicalLocCode){
	
		List<Calendar> allCalendar = calendarService.getByFiscalYr(fiscalYr, month, logicalLocCode);
		
		if (allCalendar != null)
			return new ResponseEntity<>(allCalendar ,HttpStatus.OK);
		else 
			return new ResponseEntity<List<Calendar>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/update/{first}/{second}/{status}")
	public Integer updateStatus(@PathVariable("first") String first,@PathVariable("second") String second,
			@PathVariable("status") String status){
		
		LOGGER.info("inside update status");
		Integer res1 = null;
		Integer res2 = null;
		
		if(status.equals("open"))
		{
			res1 = calendarService.updateStatus1(first,"Y");
			res2 = calendarService.updateStatus2(second, "N");
		} 
		else if(status.equals("close")) 
		{
			res1 = calendarService.updateStatus1(first,"N");
			res2 = calendarService.updateStatus2(second, "Y");
		}
		
			return res1;
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
	
	@GetMapping("/monthlyopening")
	@ApiOperation(value = "Monthly calendar opening", response = ResponseEntity.class)
	public ResponseEntity<Integer> monthlyOpening() {
		try {
			LOGGER.info("--Monthly calendar opening --");
			ResponseEntity<Integer> model;
			List<String> logicalLog = new ArrayList<String>();
			logicalLog.add("MUMBAILOG1");
			logicalLog.add("HOLOG1");
			logicalLog.add("THANELOG1");
			if(calendarService.monthlyOpening(logicalLog) == 1) {
				model = new ResponseEntity<Integer>(1,HttpStatus.ACCEPTED);
			}
			else {
				model = new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
			}
			LOGGER.debug("--Monthly calendar opening --");
			return model;

		}
		catch(CalendarMonthlyOpeningClosingException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
		}
				
	}
}
