package in.ecgc.smile.erp.accounts.util;

public interface LOVQueries {
	
	public static final String GET_ALL_LOV_MST_ENTRIES="SELECT lov_cd,lov_sub_cd,lov_value,lov_desc,created_dt"
			+ " FROM ecgc_acct_lov_mst";



	public static final String INSERT_LOV_MST_ENTRY_DETAILS = "INSERT INTO ecgc_acct_lov_mst"
			+ "(lov_cd,lov_sub_cd,lov_value,lov_desc,created_dt)"
			+ "VALUES(:lov_cd,:lov_sub_cd,:lov_value,:lov_desc,:created_dt)";

	public static final String GET_T1_CODE_LIST = "select lov_desc ,ltrim(lov_value,'T1-') as lov_value  from accounts.ecgc_acct_lov_mst ealm where lov_value like 'T1%'";
	
	public static final String FIND_LOV = "select * from accounts.ecgc_acct_lov_mst where lov_cd =:lov_cd and lov_sub_cd =lov_sub_cd and lov_value =:lov_value  ; ";
	
	public static final String GET_T2_CODE_LIST = "select lov_desc ,ltrim(lov_value,'T2-') as lov_value  from accounts.ecgc_acct_lov_mst ealm where lov_value like 'T2%'";

}

