package in.ecgc.smile.erp.accounts.util ;

public interface CalendarSqlQueries {

	public static final String ADD_CALENDAR = "INSERT INTO ecgc_acct_calendar_mst" + 
			"(branch_cd, logical_loc_cd, gl_txn_type, fiscal_yr, \"month\", closed_status,"+
			" calendar_id, meta_status, meta_remarks, created_by, last_updated_by, created_dt, last_updated_dt)" + 
			"VALUES(':branchCd', ':logicalLocCd', ':glTxnType', ':fiscalYear', ':month', ':closedStatus',"+
			" ':calendarID', ':ecgcStatus', ':metaRemarks', :createdBy,:updatedBy, now(), now())";
	
	public static final String GET_CALENDAR = " SELECT * FROM ecgc_acct_calendar_mst WHERE calendar_id = :calendarId";
	public static final String GET_CALENDAR_BY_GL_MON_LOGICALLOC = " select * from accounts.ecgc_acct_calendar_mst "
			+ " where fiscal_yr=:fiscalYr and month=:month and gl_txn_type=:glTxnType and logical_loc_cd=:logicalLocCode;";
	
	public static final String GET_ALL_CALENDAR = " SELECT * FROM ecgc_acct_calendar_mst";
	
	// Query for get by location_month_fiscalyr is written in CalendardaoImpl of BE

	public static final String GET_CALENDAR_FOR_MONTH ="SELECT * FROM accounts.ecgc_acct_calendar_mst "
			+ "where fiscal_yr = :fiscalYr and month = :month order by (\r\n" + 
			"case gl_txn_type when 'IB' then 1 when 'JV' then 2 when 'PV' then 3 when 'AF' then 4 when 'AG' then 5 when 'AP' then 6 when 'MP' then 7\r\n" + 
			"when 'PC' then 8 when 'R1' then 9 when 'RE' then 10 when 'RV' then 11 when 'SE' then 12 end)"; 
	
	public static final String GET_CALENDAR_FOR_MONTH_LOGICAL_LOC = "SELECT * FROM accounts.ecgc_acct_calendar_mst "
	+ "where fiscal_yr = :fiscalYr and month = :month and logical_loc_cd = :logicalLocCode order by (\r\n" + 
			"case gl_txn_type when 'IB' then 1 when 'JV' then 2 when 'PV' then 3 when 'AF' then 4 when 'AG' then 5 when 'AP' then 6 when 'MP' then 7\r\n" + 
			"when 'PC' then 8 when 'R1' then 9 when 'RE' then 10 when 'RV' then 11 when 'SE' then 12 end)";
	public static final String Update_Status_1 = 
			"UPDATE ecgc_acct_calendar_mst SET closed_status=:status WHERE calendar_id=:first";
	
	public static final String Update_Status_2 = 
			"UPDATE ecgc_acct_calendar_mst SET closed_status=:status WHERE calendar_id=:second";
	
	public static final String Update_last_yr =
			"UPDATE ecgc_acct_calendar_mst SET closed_status=:status WHERE calendar_id=:prevyr";
	
	public static final String Update =
			"UPDATE ecgc_acct_calendar_mst SET closed_status=:status WHERE logical_loc_cd=:logicalLoc and gl_txn_type=:glTxnType and month=:month and fiscal_yr=:fiscalYr";
	public static final String MONTHLY_OPENING="select ecgc_acct_calendar_monthly_opening_func(:currentMonth,:currentFiscalYr,:logicalCode)";
	public static final String MONTHLY_CLOSING="select ecgc_acct_calendar_monthly_closing_func(:currentMonth,:currentFiscalyr,:logicalCode)";
	public static final String OPENING="select ecgc_acct_calendar_opening_func(:currentMonth,:currentFiscalyr,:logicalCode)";
}
