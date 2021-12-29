package in.ecgc.smile.erp.accounts.util;

public interface TDSMasterSqlQuries {
	
	public  static final String ADD_TDS_DETAILS= "INSERT INTO ecgc_acct_tds_mst(fiscal_yr,"
			+ " from_amount, to_amount,from_dt, to_dt, tax_percent, amount, limit_ecgc, surcharge_amt,"
			+ " surcharge_per, sex, created_dt)"
			+ " VALUES(:fiscalYr, :fromAmount, :toAmount, :fromDt,"
			+ " :toDt, :taxPer, :amount, :limitEcgc, :surchargeAmt, :surchargePer, :sex, now())";
			
	
	public  static final String LOAD_ALL_TDS ="SELECT * FROM ecgc_acct_tds_mst";
	
	public static final String SELECT_EXISTING_DATA="SELECT * FROM ecgc_acct_tds_mst"
			+ " WHERE from_amount between :fromAmount and :toAmount"
			+ " or to_amount between :fromAmount and :toAmount or "
			+ "from_amount <= :fromAmount AND to_amount > :toAmount or sex = :sex and fiscal_yr = :fiscalYr";
}
