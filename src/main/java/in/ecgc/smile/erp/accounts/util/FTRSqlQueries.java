package in.ecgc.smile.erp.accounts.util;

public interface FTRSqlQueries {

	public static String ADD_FTR_DTL="INSERT INTO ecgc_acct_ftr_req_dtl" + 
									"(ftr_req_no, ftr_req_amt, ftr_req_type, ftr_req_reason, ecib_intlbnk_name,"+
									" ecib_claim_no, exp_eci_date_pym, ecib_type, exp_pol_date_pym, pol_claim_no,"+
									" pol_type, expo_name, ie_cd, created_by, last_updated_by,"+
									" created_dt, last_updated_dt, meta_status, ftr_req_srno, meta_remarks)" + 
									"VALUES(:FTRRequestNo,:FTRRequestAmount, ':FTRRequestType', ':FTRRequestReason',"+
									" ':ecibIntlBankName', ':ecibClaimNo', ':expClaimDatePayment', ':ecibType', ':ecibPolicyDatePayment',"+
									" 'policyClaimNo', ':policyType', 'exporterName', 'ieCode', "+
									" :createdBy,:updatedBy , now(),now(), ':ecgcStatus',:FTRRequestSrNo, ':metaRemark');" + 
									"";
	public static String ADD_FTR_DTL1="INSERT INTO ecgc_acct_ftr_req_dtl" + 
			"(ftr_req_no, ftr_req_amt, ftr_req_type, ftr_req_reason, ecib_intlbnk_name,"+
			" ecib_claim_no, exp_eci_date_pym, ecib_type, exp_pol_date_pym, pol_claim_no,"+
			" pol_type, expo_name, ie_cd, created_by,last_updated_by,"+
			" created_dt, last_updated_dt, meta_status, ftr_req_srno, meta_remarks )" + 
			"VALUES(:FTRRequestNo,:FTRRequestAmount,:FTRRequestType,:FTRRequestReason,"+
			" :ecibIntlBankName,:ecibClaimNo,null,:ecibType,null,"+
			" :policyClaimNo,:policyType,:exporterName,:ieCode, "+
			" :createdBy,:updatedBy , now(),now(),:ecgcStatus,:FTRRequestSrNo,:metaRemark);" + 
			"";

	
	public static String ADD_FTR="INSERT INTO ecgc_acct_ftr_req_hdr(ftr_req_no,ftr_req_dt, ftr_req_branch_cd,"+
								" ftr_req_by, ftr_req_dept_cd, ftr_req_status, logicalloc_cd, ftr_appr_by,"+
								" ftr_trf_dt, ftr_trf_amt, pv_status, created_by, created_dt, last_updated_dt, last_updated_by,"+
								" meta_status, meta_remarks, ftr_type, req_to) VALUES(:ftrReqNo,:ftrReqDt,:ftrReqBranchCd,:ftrReqBy,"+
								" :ftrReqDeptCd, 'P'::bpchar,:logicalLocCd,:ftrApprBy,:ftrTrfDt,:ftrTrfAmt,"+
								" 'P', :createdBy, 'now()', 'now()', :updatedBy,:ecgcStatus,:metaRemarks,'fund transfer', 'HOLOG1');";
	
	
	public static String VIEW_FTR="SELECT ftr_req_no, ftr_req_amt, ftr_req_type, ftr_req_reason, ecib_intlbnk_name,"+
									" ecib_claim_no, exp_eci_date_pym, ecib_type, exp_pol_date_pym, pol_claim_no, pol_type,"+
									" expo_name, ie_cd, logical_loc_cd, created_by, last_updated_by, created_dt, last_updated_dt,"+
									" meta_status, ftr_req_srno, meta_remarks FROM ecgc_acct_ftr_req_dtl where ftr_req_no = :FTRRequestNo and del_flag = false;";
	
//	public static String VIEW_ALL_FTR="SELECT ftr_req_no, ftr_req_amt, ftr_req_type, ftr_req_reason, ecib_intlbnk_name,"+
//									" ecib_claim_no, exp_eci_date_pym, ecib_type, exp_pol_date_pym, pol_claim_no, pol_type,"+
//									" expo_name, ie_cd, logical_loc_cd, branch_cd, created_by, updated_by, created_on, updated_on,"+
//									" meta_status, ftr_req_srno, meta_remarks FROM ecgc_acct_ftr_req_dtl;";

	public static String VIEW_ALL_FTR="SELECT * from ecgc_acct_ftr_req_hdr where del_flag = false and ftr_type ='fund transfer';";
	
	public static String VIEW_ALL_PENDING_FTR="SELECT * from ecgc_acct_ftr_req_hdr where ftr_req_status = 'P' and del_flag = false and ftr_type ='fund transfer';";
	
	public static String VIEW_ALL_APPROVED_FTR="SELECT * from ecgc_acct_ftr_req_hdr where ftr_req_status = 'A' and del_flag = false and ftr_type ='fund transfer';";
	
	public static String VIEW_ALL_FTR_BRANCH="SELECT * from ecgc_acct_ftr_req_hdr where logicalloc_cd = :logicalLoc and del_flag = false and ftr_type ='fund transfer';";
	

	public static String GET_FTR_DTL ="SELECT * from ecgc_acct_ftr_req_dtl where del_flag = false and ftr_req_no = ";

	
	public static String GET_FTR_HDR="SELECT * FROM ecgc_acct_ftr_req_hdr WHERE ftr_req_no = :ftrReqNo and del_flag = false";
	
	public static String DECISION_ON_FTR="UPDATE ecgc_acct_ftr_req_hdr SET ftr_req_status=:ftrReqStatus ,ftr_appr_by= :apprBy,last_updated_by=:apprBy,last_updated_dt=now(),meta_remarks=:metaRemarks WHERE ftr_req_no=:ftrReqNo and del_flag = false;";

	public static String UPDATE_FTR_HDR = "UPDATE ecgc_acct_ftr_req_hdr " + 
										  " SET ftr_req_dt=now(), ftr_req_branch_cd=:ftrReqBranchCd, ftr_req_by=:ftrReqBy, ftr_req_dept_cd=:ftrReqDeptCd,"+
										  " ftr_req_status='P'::bpchar, logicalloc_cd=:logicalLocCd, ftr_appr_by=:ftrApprBy, ftr_trf_dt=:ftrTrfDt, ftr_trf_amt=:ftrTrfAmt,"+
										  " pv_status='P', last_updated_dt=now(), last_updated_by=:ftrReqBy, meta_status='V', meta_remarks= :metaRemarks" + 
										  " WHERE ftr_req_no= :ftrReqNo and del_flag = false;";
	public static String GET_SEQ="SELECT ecgc_acct_int_seq_no_func(:seq_coloumn);";

	
//	public static String UPDATE_FTR_DTL=" UPDATE ecgc_acct_ftr_req_dtl" + 
//										" SET ftr_req_amt=:FTRRequestAmount, ftr_req_type=:FTRRequestType, ftr_req_reason=:FTRRequestReason,"+
//										" ecib_intlbnk_name=:ecibIntlBankName, ecib_claim_no=:ecibClaimNo, exp_eci_date_pym=:expEcibDatePayment,"+
//										" ecib_type=:ecibType, exp_pol_date_pym=:expPolicyDatePAyment, pol_claim_no=:policyClaimNo, pol_type=:policyType,"+
//										" expo_name=:exporterName, ie_cd=:ieCode, logical_loc_cd=:logicalLocCode, last_updated_by=:updatedBy,"+
//										" last_updated_dt=now(), meta_status=:ecgcStatus, meta_remarks=:metaRemark where ftr_req_no =:FTRRequestNo and ftr_req_srno =:FTRRequestSrNo and del_flag = false"; 

	/*in this query logical location is not mapped */
	public static String UPDATE_FTR_DTL=" UPDATE ecgc_acct_ftr_req_dtl" + 
			" SET ftr_req_amt=:FTRRequestAmount, ftr_req_type=:FTRRequestType, ftr_req_reason=:FTRRequestReason,"+
			" ecib_intlbnk_name=:ecibIntlBankName, ecib_claim_no=:ecibClaimNo, exp_eci_date_pym=:expEcibDatePayment,"+
			" ecib_type=:ecibType, exp_pol_date_pym=:expPolicyDatePAyment, pol_claim_no=:policyClaimNo, pol_type=:policyType,"+
			" expo_name=:exporterName, ie_cd=:ieCode, last_updated_by=:updatedBy,"+
			" last_updated_dt=now(), meta_status=:ecgcStatus, meta_remarks=:metaRemark where ftr_req_no =:FTRRequestNo and ftr_req_srno =:FTRRequestSrNo and del_flag = false"; 


	public static String DELETE_DTL_REQUEST = "select ecgc_acct_ftr_req_del_func(:ftrReqNo);";
	
	public static String GET_MAX_SRNO = "select max(ftr_req_srno) from ecgc_acct_ftr_req_dtl where ftr_req_no= :ftrNo and del_flag = false;";
	public static String DELETE_FTR_DTL = "UPDATE ecgc_acct_ftr_req_dtl set del_flag = true where ftr_req_no= :ftrNo and ftr_req_srno = :srNo";

	public static String ADD_FTR_REFUND_REQ="INSERT INTO ecgc_acct_ftr_req_hdr(ftr_req_no,ftr_req_dt, ftr_req_branch_cd,"+
			" ftr_req_by, ftr_req_dept_cd, ftr_req_status, logicalloc_cd, ftr_appr_by,"+
			" ftr_trf_dt, ftr_trf_amt, pv_status, created_by, created_dt, last_updated_dt, last_updated_by,"+
			" meta_status, meta_remarks, ftr_type, req_to) VALUES(:ftrReqNo,:ftrReqDt,:ftrReqBranchCd,:ftrReqBy,"+
			" :ftrReqDeptCd, 'P'::bpchar,:logicalLocCd,:ftrApprBy,:ftrTrfDt,:ftrTrfAmt,"+
			" 'P', :createdBy, 'now()', 'now()', :updatedBy,:ecgcStatus,:metaRemarks,:ftrType,:reqTo);";


	public static String ADD_FTR_REFUND_REQ_DTL="INSERT INTO ecgc_acct_ftr_req_dtl" + 
			"(ftr_req_no, ftr_req_amt, ftr_req_type, ftr_req_reason,logical_loc_cd, created_by,last_updated_by,"+
			" created_dt, last_updated_dt, meta_status, ftr_req_srno, meta_remarks )" + 
			"VALUES(:FTRRequestNo,:FTRRequestAmount,:FTRRequestType,:FTRRequestReason,"+
			" :logicalLocCode, "+
			" :createdBy,:updatedBy , now(),now(),:ecgcStatus,:FTRRequestSrNo,:metaRemark);" + 
			"";

	public static String VIEW_ALL_FTR_REFUND_BRANCH="SELECT * from ecgc_acct_ftr_req_hdr where logicalloc_cd = :logicalLoc and del_flag = false and ftr_type ='refund';";

	public static String GET_FTR_REFUND_DTL ="SELECT * from ecgc_acct_ftr_req_dtl where del_flag = false and ftr_req_no = ";
	
	public static String GET_FTR_REFUND_HDR="SELECT * FROM ecgc_acct_ftr_req_hdr WHERE ftr_req_no = :ftrReqNo and del_flag = false";
	
	public static String DECISION_ON_FTR_REFUND="UPDATE ecgc_acct_ftr_req_hdr SET ftr_req_status=:ftrReqStatus ,ftr_appr_by= :apprBy,last_updated_by=:apprBy,last_updated_dt=now(),meta_remarks=:metaRemarks WHERE ftr_req_no=:ftrReqNo and logicallog_cd=:logicalLog and del_flag = false;";
	public static String SET_STATUS_TO_TRF="UPDATE ecgc_acct_ftr_req_hdr SET ftr_req_status=:ftrReqStatus, ftr_trf_dt =:trfDt,last_updated_by=:updatedBy,last_updated_dt=now(),meta_remarks=:metaRemarks WHERE ftr_req_no=:ftrReqNo and del_flag = false;";
	 
}
