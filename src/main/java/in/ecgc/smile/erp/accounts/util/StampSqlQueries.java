package in.ecgc.smile.erp.accounts.util;

/**
 * 
 * Stamp Parameter
 * 
 * @author Amitesh Banerjee
 * @version 1.0 27-April-2020
 *
 */

public class StampSqlQueries {
	
	/*
	 * public static final String ADD_STAMP_PARAMETER =
	 * "INSERT INTO acct_stamp_amt_param (sr_no, from_amt, to_amt, stamp_amt, description,"
	 * + " effective_dt, active," + " ecgc_status," +
	 * " created_by, created_on, updated_by, updated_on," + " meta_remarks)" +
	 * "	VALUES (:srNo, :fromAmount, :toAmount, :stampAmount, :description," +
	 * " :effectiveDate, :active," + " :ecgcStatus," +
	 * " :createdBy, now(), :updatedBy, now()," + " :metaRemarks)";
	 
	 *
	 */
	
	public static final String ADD_STAMP_PARAMETER = 
			"INSERT INTO ecgc_acct_stamp_parameter_mst (sr_no, from_amt, to_amt, stmp_amt, description, "
				+ " effective_dt, active) "		 
				+ "	VALUES (:srNo, :fromAmount, :toAmount, :stampAmount, :description," 
				+ " :effectiveDate, 'Y' "
				+ " )";
	
	public static final String UPDATE_STAMP_PARAMETER = "UPDATE ecgc_acct_stamp_parameter_mst SET from_amt= :fromAmount, "
			+"to_amt= :toAmount, stmp_amt= :stampAmount, description = :description,"
			+"last_updated_by= :updatedBy, last_updated_dt= now(), meta_remarks= :metaRemarks where sr_no=:stampCode";
	
	public static final String VIEW_STAMP_PARAMETER = "SELECT sr_no, from_amt, to_amt, stmp_amt, description,"
			+" effective_dt, active, ecgc_status, created_by, created_dt, last_updated_by, last_updated_dt, meta_remarks"
			+" FROM ecgc_acct_stamp_parameter_mst";
	
	/*
	 * public static final String VIEW_STAMP_PARAMETER_BY_ID =
	 * "SELECT sr_no, from_amt, to_amt, stamp_amt, description,"
	 * +" effective_dt, active, ecgc_status, created_by, created_on, updated_on, updated_by, meta_remarks"
	 * +" FROM acct_stamp_amt_param where sr_no=:stampCode";
	 */
	public static final String VIEW_STAMP_PARAMETER_BY_ID ="SELECT * from ecgc_acct_stamp_parameter_mst WHERE sr_no=:stampCode";
	

}
