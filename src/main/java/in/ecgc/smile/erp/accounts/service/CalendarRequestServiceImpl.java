package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.CalendarExceptionHandling;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.CalendarRequestModel;
import in.ecgc.smile.erp.accounts.model.DateOperation;
import in.ecgc.smile.erp.accounts.repository.CalendarDao;
import in.ecgc.smile.erp.accounts.repository.CalendarRequestDao;

@Service
public class CalendarRequestServiceImpl implements CalendarRequestService {


	@Autowired
	CalendarRequestDao requestDao;
	
	@Autowired
	CalendarDao calendarDao;

	
	@Override
	public Integer generateRequest(CalendarRequestModel requestModel)  {
		Calendar calendar = calendarDao.getCalendar(requestModel.getCalendarId());
		if (calendar!=null) {
			requestModel.setGlTxnType(calendar.getGlTxnType());
			requestModel.setFiscalYr(calendar.getFiscalYear());
			requestModel.setLogicalLocCode(calendar.getLogicalLocCode());
			requestModel.setMonth(calendar.getMonth());
			return requestDao.generateRequest(requestModel);
		} else {
			return null;
		}
	}
	
	@Override
	public Integer generateCalRequest(CalendarRequestModel requestModel){
		Calendar calendar = calendarDao.getCalendar(requestModel.getCalendarId());
		if (calendar!=null) {
			requestModel.setGlTxnType(calendar.getGlTxnType());
			requestModel.setFiscalYr(calendar.getFiscalYear());
			requestModel.setLogicalLocCode(calendar.getLogicalLocCode());
			requestModel.setMonth(calendar.getMonth());
			return requestDao.generateRequest(requestModel);
		}else {
			return null;
		}
	}


	@Override
	public CalendarRequestModel updateCalendarRequest(Integer reqId, CalendarRequestModel updateModel) {

		return requestDao.updateCalendarRequest(reqId, updateModel);
	}

	@Override
	public List<CalendarRequestModel> viewAllRequest() {

		return requestDao.viewAllRequest();
	}

	@Override
	public CalendarRequestModel viewRequestById(Integer reqId) throws CalendarExceptionHandling{

		CalendarRequestModel calendarModel = requestDao.viewRequestById(reqId);
		if(calendarModel==null) {
			throw new CalendarExceptionHandling("No request found with the given Request ID");
		}
		return calendarModel;
	}
	
	public Integer decisionOnRequest(CalendarRequestModel requestModel) {
		
		if(requestModel.getRequestStatus().equalsIgnoreCase("A")) {

			requestDao.decisionOnRequest(requestModel);
			calendarDao.updateStatus1(requestModel.getCalendarId(),"N");
			Calendar calendar = calendarDao.getCalendar(requestModel.getCalendarId());
			DateOperation dateOperation = new DateOperation(calendar.getMonth().substring(0, 3));
			calendar.setMonth(dateOperation.nextMonth);
			calendarDao.openPrev(calendar.getLogicalLocCode(),calendar.getGlTxnType(),"Y",calendar.getMonth(),calendar.getFiscalYear());
			return 1;
		}
		else
		{
			return	requestDao.decisionOnRequest(requestModel);
		}
	}

	
}

