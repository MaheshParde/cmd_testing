package in.ecgc.smile.erp.accounts.util;

public interface PaymentAdviceSqlQueries {
	
	public static final String GET_SEQ_NO = 
			"SELECT   ecgc_acct_gen_pymtadvcno_func(:logicalLocCd,:sectionCd,:fiscalYear)";
	
	public static final String UPDATE_SEQ_NO = 
			"UPDATE  ecgc_acct_int_pymtadvc_seq_no_tbl set advice_no = (:adviceNo)::numeric"
			+ " WHERE logical_loc_cd = :logicalLocCd and section_cd = :sectionCd and financial_yr =:fiscalYear ";
	
	public static final String ADD_PAYMENT_ADVICE_DTL = 
			"INSERT INTO  ecgc_acct_pymt_advice_dtl "
			+ "(entity_cd, expense_head, logical_loc_cd, section_cd, advice_no, advice_dt, pay_to_type, pymt_party_cd, pymt_party_name, curr_cd, bill_no, bill_dt, bill_iwd_dt, bill_desc, advice_amt, advice_desc, dec_by, dec_dt, dec_rmk, aprv_amt, advice_stat, tds_applicable, tax_rate, surcharge_rate, tax_deducted, fiscal_yr, cess_amt, cess_rate, old_cd, advice_amt_oth_curr_inr, curr_rate, user_name, appr_base_amt, service_tax_amt, oth_amt, pymt_aprv_id, pymt_aprv_name, pymt_year, no_deduction_reason, taxinfo_flg, provision_flg, no_provision_reason, provisional_amt,liability_gl_txn_no,liability_gl_txn_type, created_dt, created_by, meta_status, branch_cd, meta_remarks, del_flag)"
			+ "VALUES(:entityCd,:expenseHead,:logicalLocCd,:sectionCd,:adviceNo,:adviceDate,:payToType,:pymtPartyCd,:pymtPartyName,:currCd,:billNo,:billDt,:billIwdDt,:billDesc,:adviceAmt,:adviceDesc,:decBy,:decDt,:decRemark,:aprvAmt,:adviceStat,:tdsApplicable,:taxRate,:surchargeRate,:taxDeducted,:fiscalYear,:cessAmt,:cessRate,:oldCd,:adviceAmtOtherCurrINR,:currRate,:userName,:apprBaseAmt,:serviceTaxAmt,:othAmt,:pymtAprvId,:pymtAprvName,:pymtYear,:noDeductionReason,:taxInfoFlag,:provisionFlag,:noProvisionReason,:provisionalAmt,:liabilityGlTxnNo, :liabilityGlTxnType,timestamp 'today',:createdBy,:metaStatus,:branchCd,:metaRemarks,false)";
	
	public static final String LIST_ALL_PAYMENT_ADVICE_DTL = 
			"SELECT * FROM  ecgc_acct_pymt_advice_dtl where del_flag = false";
	
	public static final String GET_PAYMENT_ADVICE_BY_ADVICE_DTL =
			"SELECT * FROM  ecgc_acct_pymt_advice_dtl WHERE advice_no = (:adviceNo)::numeric and logical_loc_cd = :logicalLocCd and section_cd = :sectionCd and del_flag = false";
	
	public static final String GET_PAYMENT_ADVICE_BY_LOGICALlOC_SECCODE =
			"SELECT advice_no , advice_amt,advice_desc ,advice_stat , created_dt FROM  ecgc_acct_pymt_advice_dtl WHERE logical_loc_cd = :logicalLocCd and section_cd = :sectionCd  and del_flag = false and advice_dt  between :fromDt and :toDt ";

	public static final String DISABLE_PAYMENT_ADVICE_BY_ADVICE_DTL = 
			"UPDATE  ecgc_acct_pymt_advice_dtl set del_flag = true where advice_no = (:adviceNo)::numeric and logical_loc_cd = :logicalLocCd and section_cd = :sectionCd ";

	public static final String UPDATE_PAYMENT_ADVICE_BY_ADVICE_NO = 
			 "UPDATE  ecgc_acct_pymt_advice_dtl set pay_to_type = :payToType, pymt_party_cd = :pymtPartyCd , curr_cd = :currCd ,advice_amt = :adviceAmt, pymt_year =:pymtYear , advice_desc = :adviceDesc, bill_no = :billNo, bill_dt = :billDt, bill_iwd_dt = :billIwdDt, bill_desc = :billDesc where advice_no = (:adviceNo)::numeric and logical_loc_cd = :logicalLocCd and section_cd = :sectionCd";

	public static final String UPDATE_PAYMENT_ADVICE_TCODES = 
			"UPDATE  ecgc_acct_pymt_advice_tcodes SET t1=:t1, t2=:t2, t3=:t3, t4=:t4, t5=:t5, t6=:t6, t7=:t7, t8=:t8, t9=:t9, t10=:t10, t11=:t11, t12=:t12, ad1=:ad1, ad2=:ad2, ad3=:ad3, ad4=:ad4, entity_cd=:entityCd where advice_no = (:adviceNo)::numeric and logical_loc_cd = :logicalLocCd and section_cd = :sectionCd ";
	
	public static final String ADD_PAYMENT_ADVICE_TCODES_DTL = 
			"INSERT INTO  ecgc_acct_pymt_advice_tcodes "
			+ "(entity_cd,logical_loc_cd, section_cd, advice_no, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, ad1, ad2, ad3, ad4, del_flag) "
			+ "VALUES(:entityCd,:logicalLocCd, :sectionCd, :adviceNo::numeric, :t1, :t2, :t3, :t4, :t5, :t6, :t7, :t8, :t9, :t10, :t11, :t12,:ad1,:ad2,:ad3,:ad4,false)";
	
	public static final String DISABLE_PAYMENT_ADVICE_TCODES_DTL = "UPDATE  ecgc_acct_pymt_advice_tcodes "
			+ "SET del_flag=true WHERE advice_no= :adviceNo::numeric and logical_loc_cd = :logicalLocCd and section_cd = :sectionCd";
	
	public static final String LIST_ALL_PAYMENT_ADVICE_TCODES_DTL = "SELECT * FROM  ecgc_acct_pymt_advice_tcodes where del_flag = false";
	
	public static final String LIST_PAYMENT_ADVICE_TCODES_DTL_BY_ADVICE_DTL = "SELECT * FROM  ecgc_acct_pymt_advice_tcodes WHERE advice_no = (:adviceNo)::numeric and logical_loc_cd = :logicalLocCd and section_cd = :sectionCd";

	public static final String GET_PAYMENT_ADVICE_REQUEST = "SELECT advice_no , logical_loc_cd , section_cd , advice_amt , advice_desc, advice_dt  FROM  ecgc_acct_pymt_advice_dtl WHERE logical_loc_cd = :logicalLocCd AND SECTION_CD = :sectionCd AND ADVICE_STAT = :adviceStat and del_flag = false";

	public static final String TAKE_PAYMENT_ADVICE_DECISION = "update  ecgc_acct_pymt_advice_dtl set advice_stat = :adviceStat, dec_by = :decBy, dec_dt = :decDt, dec_rmk = :decRemark, aprv_amt = :aprvAmt"
			+ " where logical_loc_cd = :logicalLocCd and section_cd = :sectionCd and advice_no =:adviceNo ";
}
