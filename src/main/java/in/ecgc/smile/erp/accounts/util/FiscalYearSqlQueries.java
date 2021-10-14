package in.ecgc.smile.erp.accounts.util;

public interface FiscalYearSqlQueries {
	
	public static final String GET_CURRENT_FISCAL_YEAR =
	"SELECT * FROM ecgc_acct_fiscal_yr where meta_status = :status";
	
	public static final String ALL_FISCAL_YR =
	"SELECT distinct(curr_fisc_yr) FROM ecgc_acct_fiscal_yr";
}
