package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.CalendarRequestModel;

public interface CalendarRequestDao {
	
	public Integer generateRequest(CalendarRequestModel requestModel);
	public Integer decisionOnRequest(CalendarRequestModel requestModel);
	
	public CalendarRequestModel updateCalendarRequest(Integer reqId, CalendarRequestModel updateModel);
	
	public List<CalendarRequestModel> viewAllRequest();
	
	public CalendarRequestModel viewRequestById(Integer reqId);

}
