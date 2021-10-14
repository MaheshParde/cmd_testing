package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.exception.CalendarExceptionHandling;
import in.ecgc.smile.erp.accounts.model.CalendarRequestModel;
import in.ecgc.smile.erp.accounts.service.CalendarRequestService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/calendar-request")
@Api(value = "Calendar Request Service")
public class CalendarRequestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CalendarRequestController.class);
	
	@Autowired
	CalendarRequestService calendarService;
	
	@RequestMapping("/")
	public String test()
	{
		return "hello from calendar service";
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "Add New Calendar Request", response = ResponseModel.class)
	public ResponseEntity<CalendarRequestModel> generateRequest(@RequestBody CalendarRequestModel requestModel){
		ResponseEntity<CalendarRequestModel> responseEntity;
		LOGGER.info("INSIDE CONTROLLER");
		try {
			//LOGGER.info("Data"+requestModel.toString());
			LOGGER.info("model",requestModel.toString());
			if ( calendarService.generateRequest(requestModel) == 1) {
				responseEntity=new ResponseEntity<CalendarRequestModel>(requestModel,HttpStatus.CREATED);
				}
			else {
				responseEntity=new ResponseEntity<CalendarRequestModel>(HttpStatus.BAD_REQUEST);
			}
		} catch (CalendarExceptionHandling e) {
			responseEntity=new ResponseEntity<CalendarRequestModel>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- CalendarExceptionHandling..Incomplete calendar request Data provided");
		}
		return responseEntity;
	}

	@PostMapping("/update/{reqId}")
	@ApiOperation(value = "Update Calendar Request", response = ResponseEntity.class)
	public ResponseEntity<CalendarRequestModel> updateCalendarRequest(
			@ApiParam(value = "Request Id", required = true)
			@PathVariable("reqId") Integer reqId,
			@RequestBody CalendarRequestModel updateCalendarRequest){
		LOGGER.info("INSIDE UPDATE CONTROLLER");
		CalendarRequestModel updateRequest = calendarService.updateCalendarRequest(reqId, updateCalendarRequest);
		return new ResponseEntity<CalendarRequestModel>(updateRequest,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewAllRequest")
	@ApiOperation(value = "View all Calendar Request", response = ResponseEntity.class)
	public ResponseEntity<List<CalendarRequestModel>> viewAllRequest(){
		LOGGER.info("--Inside Get all Calendar Request--");
		LOGGER.error("--Fetching Calendar Request data--");
		List<CalendarRequestModel> calendarRequest=calendarService.viewAllRequest();
		
		if(calendarRequest!=null)
		{
			LOGGER.info(calendarRequest.toString());
			return new ResponseEntity<>(calendarRequest, HttpStatus.OK);
		}else
		{
			return new ResponseEntity<List<CalendarRequestModel>>(HttpStatus.NO_CONTENT);
		}
	}
	
	  @GetMapping("/view/{reqId}")
	  @ApiOperation(value = "View Calendar Request by Request Id", response =
	  ResponseEntity.class)
	  public ResponseEntity<CalendarRequestModel>
	  viewRequestById(
	  @ApiParam(value = "Request Id", required = true)
	  @PathVariable("reqId") Integer reqId) throws CalendarExceptionHandling 
	  {
	  LOGGER.info("request Id ",reqId); 
	  CalendarRequestModel viewRequest = calendarService.viewRequestById(reqId);
	  LOGGER.info("data",viewRequest.toString());
	  return new ResponseEntity<>(viewRequest,HttpStatus.OK); 
	  }
	 
	  @PostMapping("/decision")
		public ResponseEntity<Integer> decisionOnCalendarRequest(@RequestBody CalendarRequestModel requestModel){
			LOGGER.info("inside descision FTR");
			ResponseEntity<Integer> responseEntity = null;
			try {
				LOGGER.info("Calendar -- Decision on Reqqest");
				if (calendarService.decisionOnRequest(requestModel) > 0) {
					LOGGER.debug("CaledarRequestController");
					return new ResponseEntity<Integer>(1,HttpStatus.ACCEPTED);
					}
				else {
					return new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
				}
			}
			catch (Exception e) {
				LOGGER.error("Exception thrown-CalendarException ..Incomplete FTR Data provided");
				e.printStackTrace();
			}
			return null;
		}

	 
}
