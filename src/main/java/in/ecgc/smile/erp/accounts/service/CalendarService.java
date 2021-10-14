package in.ecgc.smile.erp.accounts.service;

import java.util.List ;

import in.ecgc.smile.erp.accounts.exception.CalendarMonthlyOpeningClosingException;
import in.ecgc.smile.erp.accounts.model.Calendar;

public interface CalendarService {

	Integer addCalendar(Calendar calendar);
	Calendar getCalendar(String calendarID);
	List<Calendar> getAllCalendar(String fiscalYr, String month);
	Integer deleteCalendar(String calendarId);
	List<Calendar> getByFiscalYr(String fiscalYr, String month, String logicalLocCode);
	Calendar getByGlTypeLogicalLoc(String fiscalYr, String month, String logicalLocCode,String glTxnType);
	Integer updateStatus1(String first, String status);
	Integer updateStatus2(String second, String status);
	Integer updatePrev(String prevyr , String status);
	Integer monthlyOpening(List<String> logicalCode) throws CalendarMonthlyOpeningClosingException;
	Integer dailyOpeningClosing(List<String> logicalCode) throws CalendarMonthlyOpeningClosingException;
	public void scheduleDemo();

}
