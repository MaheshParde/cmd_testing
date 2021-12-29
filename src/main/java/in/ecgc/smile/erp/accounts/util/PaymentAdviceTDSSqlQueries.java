package in.ecgc.smile.erp.accounts.util;

public interface PaymentAdviceTDSSqlQueries {
	
	public static final String GET_APPROVED_PAYMENT_ADVICES = "select * from accounts.ecgc_acct_pymt_advice_dtl where  logical_loc_cd = :logicalLocCd and section_cd = :sectionCd  and advice_dt  between :fromDt and :toDt  and advice_stat = 'APRV' and del_flag = false";
	
	public static final String GET_APPROVED_ADVICE_DETAILS = 
			"SELECT section_cd ,curr_cd , advice_dt , advice_no , pay_to_type , pymt_party_cd , aprv_amt "
			+ "FROM ecgc_acct_pymt_advice_dtl where section_cd = :sectionCd and advice_no = (:adviceNo)::numeric and logical_loc_cd = :logicalLocCd and advice_stat = 'APRV' ";
	//When TDS is NOT Applicable
	
	  public static final String UPDATE_PAYMENT_ADVICE_TDS_NOT_APPLICABLE =
	  "update accounts.ecgc_acct_pymt_advice_dtl set tds_applicable = default ,no_deduction_reason=:noDeductReason, tax_rate= NULL ,surcharge_rate=NULL ,"
	  + "tax_deducted=NULL,appr_base_amt=NULL, service_tax_amt=NULL, oth_amt=NULL ,taxinfo_flg = NULL,  last_updated_dt = timestamp 'today' where section_cd = :sectionCd  and advice_no = (:adviceNo)::numeric and logical_loc_cd = :logicalLocCd and advice_stat = 'APRV'";
	 

	
	// public static final String UPDATE_PAYMENT_ADVICE_TDS_NOT_APPLICABLE = "select accounts.ecgc_acct_set_default_null(:logicalLocCd,:sectionCd , (:adviceNo)::numeric, :noDeductReason, :taxInfoFlag)";      
	 
	
	//When TDS is Applicable and Reverse Charge is Applicable
	public static final String UPDATE_PAYMENT_ADVICE_RC_APPLICABLE = "UPDATE accounts.ecgc_acct_pymt_advice_dtl "
			+ " SET  tds_applicable=:tdsApplicable, tax_rate= :taxRate , surcharge_rate=null, tax_deducted= :taxDeducted, cess_amt=null, cess_rate=null, old_cd=null, advice_amt_oth_curr_inr=null, curr_rate=null, user_name=null, appr_base_amt=:apprBaseAmt, service_tax_amt=0, oth_amt=0, no_deduction_reason=:noDeductionReason, taxinfo_flg='Y', last_updated_dt= timestamp 'today'"
			+ " WHERE section_cd = :sectionCd  and advice_no = (:adviceNo)::numeric and logical_loc_cd = :logicalLocCd and advice_stat = 'APRV' ";
	
	
	public static final String INSERT_PAYMENT_ADVICE_TDS_DTL = ""
			+ "INSERT INTO accounts.ecgc_acct_tds_dtl "
			+ "(entity_cd, logicalloc_cd, section_cd, advice_no, pymt_no, pay_to_type, pymt_party_cd, fiscal_yr, tax_amt, surcharge_amt, remittance_ref_no, status, tds_from_advice_no, tds_from_section_cd, created_by, cess_amt, old_cd, tds_section, tds_desc, tds_rate, oth_rate_rmrks, appr_base_amt, service_tax_amt, other_amt, rev_chrg_app, nature_of_service, rc_service_tax_amt, rc_prim_educess_amt, rc_sec_educess_amt, tds_remarks, rc_service_tax_rate, rc_service_tax_perc, rc_swach_bha_cess_amt, rc_krishi_kal_cess_amt, gst_type, cgst_tax, sgst_tax, igst_tax, utgst_tax, cgst_tax_amt, sgst_tax_amt, igst_tax_amt, utgst_tax_amt, gst_sel_state, invoiceno, created_dt, meta_status, meta_remarks) "
			+ "VALUES(:entityCd, :logicalLocCd, :sectionCd, (:adviceNo)::numeric, null, :payToType, :pymtPartyCd, :fiscalYr, :taxAmt, null, null, :status, :tdsFromAdviceNo, :tdsFromSecCd, :createdBy, :cessAmt, :oldCd, :tdsSection, :tdsDesc, :tdsRate, :othRateRemarks, :apprBaseAmt, :serviceTaxAmt, :otherAmt,:revChargeApp, :natureOfService, :rcServiceTaxAmt, :rcPrimEduCessAmt, :rcSecEduCessAmt, :tdsRemarks, :rcServiceTaxRate, :rcServiceTaxPerc, :rcSwachBharatCessAmt, :rcKrishiKalyanCessAmt, :gstType, :cgstTax, :sgstTax, :igsttax, :utgstTax, :cgstTaxAmt, :sgstTaxAmt, :igstTaxAmt, :utgstTaxAmt, :gstSelState, :invoiceNo, timestamp 'today',  :metaStatus, :metaRemarks)"
			+ "";
	
	public static final String INSERT_PAYMENT_ADVICE_GST_TDS_DTL = "INSERT INTO accounts.ecgc_acct_gst_tds_dtl "
			+ "(entity_cd, logicalloc_cd, section_cd, advice_no, pymt_no, pay_to_type, pymt_party_cd, fiscal_yr, status, created_by,  appr_base_amt, state_vendor, gstven, state_supply, cgst_perc, cgst_tds_amount, sgst_perc, sgst_tds_amount, igst_perc, igst_tds_amount, utgst_perc, utgst_tds_amount, gst_tds_appl, rate_tax, gst_tds_type, created_dt, meta_status, meta_remarks) "
			+ "VALUES(:entityCd, :logicalLocCd, :sectionCd, (:adviceNo)::numeric, :paymentNo, :payToType, :pymtPartyCd, :fiscalYr, :status, :createdBy,  :approvedBaseAmt, :stateVendor, :gstVen, :stateSupply, :cgstTdsRate, :cgstTdsAmt, :sgstTdsRate, :sgstTdsAmt, :igstTdsRate, :igstTdsAmt, :utgstTdsRate, :utgstTdsAmt, :gstTdsApplicable, :taxRate, :gstTdsType, timestamp 'today',:metaStatus, :metaRemarks)"
			+ "";
	
	public static final String GET_PAYMENT_ADVICE_TDS_DTL =  "select * from accounts.ecgc_acct_gst_tds_dtl where  logicalloc_cd = :logicalLocCd and section_cd = :sectionCd  and advice_no = (:adviceNo)::numeric";
	
	public static final String GET_PAYMENT_ADVICE_GST_TDS_DTL = "select * from accounts.ecgc_acct_gst_tds_dtl where  logicalloc_cd = :logicalLocCd and section_cd = :sectionCd  and advice_no = (:adviceNo)::numeric ";
	
}

	
