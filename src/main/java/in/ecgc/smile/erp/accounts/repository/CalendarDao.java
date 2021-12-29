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
	Integer openPrev(String logicalLoc,String glTxnType, String status , String month, String fiscalYr);
	Integer monthlyOpeningRegular(String logicalCode,String currMonth,String currentFiscalyr);
	Integer monthlyClosingRegular(String logicalCode,String prevMonth,String currentFiscalyr);
	Integer monthlyOpeningConfigured(String logicalCode,String currMonth,String currentFiscalyr);
	Integer monthlyClosingConfigured(String logicalCode,String prevMonth,String currentFiscalyr);
	Integer dailyClosing(String currMonth,String currentFiscalyr,String logicalCode);
	Integer dailyOpening(String currMonth,String currentFiscalyr,String logicalCode);
	Calendar getByGlTypeLogicalLoc(String fiscalYr, String month, String logicalLocCode,String glTxnType);
	Integer marchClosing(String logicalCode, String currentFiscalyr);
	Boolean marchClosingStatus(String logicalCode, String currentFiscalyr);

}
