package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.Calendar;

public interface CalendarDao {

	Integer addCalendar(Calendar calendar);
	Calendar getCalendar(String calendarId);
	List<Calendar> getAllCalendar(String fiscalYr, String month) ;
	Integer deleteCalendar(String calendarId);
	List<Calendar> getByFiscalYr(String fiscalYr, String month, String logicalLocCode);
	List<Calendar> getPrevFiscalYr(String fiscalYr, String logicalLocCode);
	Integer updateStatus1(String first,String status);
	Integer updateStatus2(String second,String status);
	Integer updatePrev(String prevyr,String status);
	public Integer openPrev(String logicalLoc,String glTxnType, String status , String month, String fiscalYr);
	Integer monthlyOpening(String logicalCode,String currMonth,String currentFiscalyr);
	Integer dailyClosing(String currMonth,String currentFiscalyr,String logicalCode);
	Integer dailyOpening(String currMonth,String currentFiscalyr,String logicalCode);
	Calendar getByGlTypeLogicalLoc(String fiscalYr, String month, String logicalLocCode,String glTxnType);

}
