package in.ecgc.smile.erp.accounts.util;

public interface ReceiptSqlQueries {
	
	public static final String ADD_RECEIPT= "INSERT INTO ecgc_acct_rcpts("
			+ "logical_loc_cd, rcpt_no, rcpt_dt, iwd_no, remarks, rcvd_from_cd, rcvd_from_name, "
			+ "rcvd_from_addr, stamp_amt, instrument_type, instrument_no, instrument_dt, drawn_on, gl_txn_type,"
			+ " gl_txn_no, rcvd_from_type, rcpt_amt, gl_flg, fiscal_yr, old_rcpt_no, gl_txn_no_old, created_by, "
			+ "created_dt, last_updated_by, last_updated_dt, meta_status, meta_remarks ,"
			+ " pay_in_slip, entity_cd, product_description, hsn, uom, qty, rate, amount, taxable_discount,"
			+ "customer_gst_no, ecgc_gst_no, invoice_no, cgst_amount, sgst_amount, igst_amount, utgst_amount,"
			+ "cgst_tax_per, sgst_tax_per, igst_tax_per, utgst_tax_per, expo_state, branch_state, tax_type,"
			+ "old_invoice_no, update_flg, sez)" 			
			+ "	VALUES (:logicalLocCode, :receiptNumber, :receiptDate, :iwdNumber, :remarks,"
			+ " :receivedFromCode, :receivedFromName, :receivedFromAddress, :stampAmount, :instrumentType, :instrumentNumber,"
			+ " :instrumentDate, :drawnOn, :glTxnType, :glTxnNumber, :receivedfromType, :receiptAmount, :glFlag, :fiscalYear, "
			+ ":oldReceiptNumber, :glTxnNumberOld, :createdBy, now(), :lastUpdatedBy, now(), :metaStatus,"
			+ " :metaRemarks ,:payInSlip, 'ECGC', :productDescription, :hsn, :uom, :qty, :rate,"
			+ ":amount, :taxableDiscount, :customerGstNo, :ecgcGstNo, :invoiceNo, :cgstAmount,:sgstAmount,:igstAmount,"
			+ ":utgstAmount, :cgstTaxPer, :sgstTaxPer, :igstTaxPer, :utgstTaxPer, :expoState, :branchState,"
			+ ":taxType, :oldInvoiceNo, :updateFlag, :sez)";
	
	public static String GET_SEQ="select ecgc_acct_gen_rcptno_func(:logicalLocCode,:fiscalYear);";
	public static String UPDATE_SEQ_TBL="UPDATE ecgc_acct_int_rcpt_seq_no_tbl"
			+ "	SET rcpt_no=:receiptNumber where fiscal_yr =:fiscalYear and logical_loc_cd =:logicalLocCode;";

	
	public static String GET_TAX_INVOICE_NO= "SELECT accounts.ecgc_acct_gen_tax_invoice_no_func(:logicalLocCode,:fiscalYear);";
	public static String UPDATE_TAX_INVOICE_TBL="UPDATE accounts.ecgc_acct_rcpts_tax_invoice_no SET invoice_no=:invoiceNo where fiscal_yr=:fiscalYear and\r\n"
			+ "state_name in(select state from accounts.ecgc_acct_logical_loc_mst where logical_loc_cd=:logicalLocCode); ;";
	

	public static String GET_BS_INVOICE_NO= "SELECT accounts.ecgc_acct_gen_bs_invoice_no_func(:logicalLocCode,:fiscalYear);";
	public static String UPDATE_BS_INVOICE_TBL="UPDATE accounts.ecgc_acct_rcpts_bs_invoice_no SET invoice_no=:invoiceNo where fiscal_yr=:fiscalYear and "
			+ "state_name in(select state from accounts.ecgc_acct_logical_loc_mst where logical_loc_cd=:logicalLocCode);";

	
	public static final String VIEW_ALL_RECEIPTS = "select * from accounts.ecgc_acct_rcpts;";
	public static final String VIEW_BY_LOGICAL_LOC_CODE= "select * from accounts.ecgc_acct_rcpts where logical_loc_cd = :logicalLocCode;";
	
	public static final String VIEW_BY_LOGICAL_LOC_CODE_AND_RECEIPT_NO="select  * from accounts.ecgc_acct_rcpts where logical_loc_cd =:logicalLocCode and rcpt_no=:receiptNumber;";
	
	public static final String MODIFT_RECEIPT = "update accounts.ecgc_acct_rcpts set remarks =:remarks where logical_loc_cd = :logicalLocCode and rcpt_no =:receiptNumber;";
	
	public static final String GET_STATES= "select stateut_name from ecgc_acct_gst_stateut;";
	
	public static final String UPDATE_PRINT_FLAG =" update accounts.ecgc_acct_rcpts set pflag =:printFlag where logical_loc_cd = :logicalLocCode and rcpt_no =:receiptNumber;";

	public static final String GET_FLAG_BY_LOGICAL_LOC_CODE_AND_RECEIPT_NO = "select pflag from accounts.ecgc_acct_rcpts where logical_loc_cd = :logicalLocCode and rcpt_no=:receiptNumber;";
	
}