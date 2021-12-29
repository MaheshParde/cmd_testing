package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.exception.CalendarExceptionHandling;
import in.ecgc.smile.erp.accounts.model.CalendarRequestModel;

public interface CalendarRequestService {
	
	public Integer generateRequest(CalendarRequestModel requestModel);
	
	public Integer generateCalRequest(CalendarRequestModel requestModel);
	
	public CalendarRequestModel updateCalendarRequest(Integer reqId, CalendarRequestModel updateModel);
	
	public List<CalendarRequestModel> viewAllRequest();
	
	public CalendarRequestModel viewRequestById(Integer reqId) throws CalendarExceptionHandling;
	
	public Integer decisionOnRequest(CalendarRequestModel requestModel);

}