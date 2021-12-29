package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

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
	
	@Autowired
	CalendarRequestService calendarService;
	
	
	@PostMapping("/add")
	@ApiOperation(value = "Add New Calendar Request", response = ResponseModel.class)
	public ResponseEntity<CalendarRequestModel> generateRequest(@RequestBody CalendarRequestModel requestModel){
		ResponseEntity<CalendarRequestModel> responseEntity;
			if(calendarService.generateRequest(requestModel) == 1) {
				responseEntity=new ResponseEntity<CalendarRequestModel>(requestModel,HttpStatus.CREATED);
				return responseEntity;
			}else {
				return null;
			}
				
		
	}

	
	@ApiOperation(value = "Add New Calendar Request", response = ResponseModel.class)
	@PostMapping("/generate")
	public ResponseEntity<Integer> generateCalRequest(@RequestBody CalendarRequestModel requestModel){
		return new ResponseEntity<Integer>(calendarService.generateCalRequest(requestModel),HttpStatus.CREATED);
	}

	
	@PostMapping("/update/{reqId}")
	@ApiOperation(value = "Update Calendar Request", response = ResponseEntity.class)
	public ResponseEntity<CalendarRequestModel> updateCalendarRequest(
			@ApiParam(value = "Request Id", required = true)
			@PathVariable("reqId") Integer reqId,
			@RequestBody CalendarRequestModel updateCalendarRequest){
		return new ResponseEntity<CalendarRequestModel>(calendarService.updateCalendarRequest(reqId, updateCalendarRequest),HttpStatus.CREATED);
	}
	
	@GetMapping("/viewAllRequest")
	@ApiOperation(value = "View all Calendar Request", response = ResponseEntity.class)
	public ResponseEntity<List<CalendarRequestModel>> viewAllRequest(){

		List<CalendarRequestModel> calendarRequest=calendarService.viewAllRequest();
		
		if(calendarRequest!=null)
		{
			return new ResponseEntity<>(calendarRequest, HttpStatus.OK);
		}else
		{
			return null;
		}
	}
	
	  @GetMapping("/view/{reqId}")
	  @ApiOperation(value = "View Calendar Request by Request Id", response =  ResponseEntity.class)
	  public ResponseEntity<CalendarRequestModel> viewRequestById(@ApiParam(value = "Request Id", required = true)
	  @PathVariable("reqId") Integer reqId) throws CalendarExceptionHandling  {

		  return new ResponseEntity<>( calendarService.viewRequestById(reqId),HttpStatus.OK); 
	  }
	 
	  @PostMapping("/decision")
		public ResponseEntity<Integer> decisionOnCalendarRequest(@RequestBody CalendarRequestModel requestModel){
		  return new ResponseEntity<Integer>(calendarService.decisionOnRequest(requestModel),HttpStatus.OK);
		}

	 
}
