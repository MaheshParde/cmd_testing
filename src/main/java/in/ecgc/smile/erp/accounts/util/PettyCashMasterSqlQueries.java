package in.ecgc.smile.erp.accounts.util;

public interface PettyCashMasterSqlQueries {

	public static final String ADD_PETTY_CASH_DETAILS = "INSERT INTO ecgc_acct_petty_cash(entity_cd,"
			+ "request_dt,cash_amt,remark,requisition_no,logical_loc_cd,fiscal_yr,req_status,created_by,created_dt,last_updated_dt)"
			+ "VALUES('ECGC',now(),:cashAmt,:remark,:requisitionNo,:logicalLocCode,:fiscalYr,'Requested',:createdBy, now(),now())";
	
	public static final String GET_SEQ ="SELECT ecgc_acct_gen_requisitionno_func(:logicalLocCode,:fiscalYr)";

	public static final String UPDATE_SEQ_TBL ="UPDATE ecgc_acct_int_requisition_seq_no_tbl"
			+ "	SET requisition_no=:requisitionNo where fiscal_yr =:fiscalYr and logical_loc_cd =:logicalLocCode;";

	public static final String VIEW_ALL = "SELECT * FROM ecgc_acct_petty_cash";

	public static final String FIND_BY_REQUI_NO = "SELECT * FROM ecgc_acct_petty_cash WHERE requisition_no= :requisitionNo"
			+ " AND logical_loc_cd=:logicalLocCode" ;
	
	public static final String APPROVED_STS ="UPDATE ecgc_acct_petty_cash SET req_status='Approved',last_updated_dt = now()"
			+ " WHERE requisition_no= :requisitionNo  AND logical_loc_cd=:logicalLocCode";
	
	public static final String UPDATE_PETTY_CASH_DETAILS = "UPDATE ecgc_acct_petty_cash SET req_status='Requested',request_dt=:requestDt, cash_amt =:cashAmt, remark=:remark, logical_loc_cd=:logicalLocCode, fiscal_yr=:fiscalYr, last_updated_dt=now(),last_updated_by=:lastUpdatedBy "
			+ " WHERE requisition_no =:requisitionNo and logical_loc_cd =:logicalLocCode ";
	
	public static final String GET_PROCESS_INSTANCE_ID = "SELECT process_instance_id from ecgc_acct_petty_cash where requisition_no= :requisitionNo "
			+ "AND logical_loc_cd=:logicalLocCode";

	public static final String UPDATE_PROCESS_INSTANCE_ID = "Update ecgc_acct_petty_cash set process_instance_id = :processInstanceId where requisition_no= :requisitionNo"
			+ "	AND logical_loc_cd=:logicalLocCode";
			
	

}
