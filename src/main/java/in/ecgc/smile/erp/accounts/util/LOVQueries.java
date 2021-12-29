package in.ecgc.smile.erp.accounts.util;

public interface LOVQueries {
	
	public static final String GET_T1_CODE_LIST = "select lov_sub_cd , lov_value , lov_desc from accounts.ecgc_acct_lov_mst ealm where lov_sub_cd IN ('T1')";
	
	public static final String GET_T2_CODE_LIST = "select lov_sub_cd , lov_value , lov_desc from accounts.ecgc_acct_lov_mst ealm where lov_sub_cd IN ('T2')";

	public static final String GET_ALL_LOV_MST_ENTRIES="SELECT lov_cd,lov_sub_cd,lov_value,lov_desc,created_dt,del_flag"
			+ " FROM ecgc_acct_lov_mst";

	public static final String INSERT_LOV_MST_ENTRY_DETAILS = "INSERT INTO ecgc_acct_lov_mst"
			+ "(lov_cd,lov_sub_cd,lov_value,lov_desc, created_by,created_dt,del_flag )"
			+ "VALUES(:lovCd,:lovSubCd,:lovValue,:lovDesc,:createdBy, now(),:delFlag)";

	public static final String GET_LOV_MST_ENTRY = "SELECT * FROM ecgc_acct_lov_mst WHERE  lov_cd = :lovCd "
			+ "AND lov_sub_cd = :lovSubCd AND lov_value = :lovValue";
	
	public static final String UPDATE_LOV_MST_ENTRY = "UPDATE ecgc_acct_lov_mst SET lov_desc = :lovDesc , del_flag = :delFlag , last_updated_by = :lastUpdatedBy, last_updated_dt = now()" 
			+ " WHERE lov_cd = :lovCd AND lov_sub_cd = :lovSubCd AND lov_value = :lovValue ";

	public static final String DISABLE_LOV_ENTRY = "UPDATE ecgc_acct_lov_mst SET del_flag = true , last_updated_by = :lastUpdatedBy, last_updated_dt = now()"
			+ " WHERE lov_cd = :lovCd AND lov_sub_cd = :lovSubCd AND lov_value = :lovValue";


	//for temporary purpose
	
	public static final String GET_REF_NO = "select ecgc_acct_get_seq_payment_advice_func()";
	
	public static final String SET_ADVICE_NO = "update ecgc_acct_payment_advice_mst set payment_advice_no = :payment_advice_no::numeric"
			+ " where reference_no = :ref_no::numeric "; 

}

