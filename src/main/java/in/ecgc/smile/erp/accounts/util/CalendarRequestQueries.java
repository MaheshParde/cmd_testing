package in.ecgc.smile.erp.accounts.util;

public class CalendarRequestQueries {

	public static final String GENERATE_CALENDAR_REQUEST = 
			"INSERT INTO ecgc_acct_calendar_request (request_id, fiscal_yr, month,"
					+ " gl_txn_type, status, meta_status,"
					+ " remark, created_dt, last_updated_dt, logical_loc_cd,"
					+ " meta_remarks, branch_cd,calendar_id )"		 
					+ "	VALUES (:requestId, :fiscalYr, :month," 
					+ " :glTxnType, :status," 
					+ " :ecgcStatus,"
					+ " :remark, now(), now(), :logicalLocCode,"
					+ " :metaRemarks, :branchCode , :calendarId)";
	
	public static final String UPDATE_CALENDAR_REQUEST = "UPDATE ecgc_acct_calendar_request SET "
			+"branch_cd= :branchCode, fiscal_yr= :fiscalYr, month = :month,"
			+"gl_txn_type= :glTxnType, status= :status, remark= :remark, "
			+ "last_updated_dt= now(), logical_loc_cd= :logicalLocCode, meta_remarks= :metaRemarks where request_id= :requestId";
	
	public static final String UPDATE_CALENDAR_REQUEST1 = "UPDATE ecgc_acct_calendar_request SET "
			+" status= :status, remark= :remark, "
			+ "last_updated_dt= now(), meta_remarks= :metaRemarks where request_id= :requestId";
	
	public static final String VIEW_CALENDAR_REQUEST = "SELECT * from ecgc_acct_calendar_request where status !='A' and status != 'R';";
	
	public static final String VIEW_CALENDAR_REQUEST_BY_ID = "SELECT * from ecgc_acct_calendar_request WHERE request_id=:requestId ";
	
	public static final String GET_SEQ="SELECT ecgc_acct_int_seq_no_func(:seq_coloumn);";
}
