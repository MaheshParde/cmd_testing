package in.ecgc.smile.erp.accounts.util;

public interface TcsMasterSqlQueries {

	public static final String VIEW_ALL="SELECT fin_yr, from_amount, to_amount, tax_percent, created_by, created_date, last_updated_by, last_updated_dt, amount, from_date, to_date, limit_ecgc, surcharge_amt, surcharge_per, sex\r\n" + 
			"	FROM ecgc_acct_tcs_mst"; 
	
	
	public static final String ADD_TCS= "INSERT INTO ecgc_acct_tcs_mst(\r\n" + 
			"	fin_yr, from_amount, to_amount, tax_percent, created_by, created_date, last_updated_by,"
			+ " last_updated_dt, amount, from_date, to_date, limit_ecgc, surcharge_amt, surcharge_per, sex)"
			+ "VALUES (:finYear, :fromAmount, :toAmount, :taxPercent, :createdBy, now(), "
			+ ":modifiedBy, now(), :amount, :fromDate, :toDate, :limitEcgc, :surchargeAmount, "
			+ ":surchargePer, :sex)"; 
	
	public static final String CHECK_EXISTING_DATA= "select fin_yr , from_amount , to_amount , sex "
			+ "from ecgc_acct_tcs_mst where"
			+ " fin_yr=:finYear and from_amount =:fromAmount and to_amount=:toAmount and sex=:sex";

	
	public static final String SELECT_EXISTING_DATA= "SELECT * from ecgc_acct_tcs_mst"
			+ " where from_amount BETWEEN :fromAmount and :toAmount and sex=:sex"
			+ " and to_amount between :fromAmount and :toAmount and fin_yr=:finYear and to_date=:toDate and "
			+ "from_date =:fromDate or from_amount <=:fromAmount  and to_amount >=:toAmount and to_date=:toDate and "
			+ "fin_yr=:finYear and sex =:sex and from_date = :fromDate "
			+ "or to_amount = :fromAmount and fin_yr=:finYear and sex=:sex and from_date =:fromDate and to_date=:toDate";
}
