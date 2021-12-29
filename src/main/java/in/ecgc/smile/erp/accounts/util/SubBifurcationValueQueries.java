package in.ecgc.smile.erp.accounts.util;

public interface SubBifurcationValueQueries{
		
	static String ADD_SubBifurcationsDtl_DATA="INSERT INTO ecgc_acct_sub_bifurcations_dtl"
			+ "(bifurcation_level_code,sub_bifurcation_level,bifurcation_value,bifurcation_value_code,created_by,"
			+ "created_dt,last_updated_by,last_updated_dt,meta_status,meta_remarks,active) VALUES (:bifurcationLevelCode,"
			+ ":subBifurcationLevel,:bifurcationValue,:bifurcationValueCode,:createdBy,now(),:lastUpdatedBy,"
			+ "now(),:metaStatus,:metaRemarks,'Y')";
	
	static String GET_SubBifurcationsDtl_DATA="SELECT * FROM ecgc_acct_sub_bifurcations_dtl where active='Y'";
	
	public static final String GET_SUB_BIFURCATION_VALUE_LIST_BY_LEVEL_CODE ="select * from accounts.ecgc_acct_sub_bifurcations_dtl where bifurcation_level_code ="
			+ "(select sub_bifurcation_level from accounts.ecgc_acct_entity_gl_mst where maingl_cd =:mainGl "
			+ "and subgl_cd=:subGl)";
	
//	static String UPDATE_SubBifurcationsDtl_DATA="UPDATE ecgc_acct_sub_bifurcations_dtl"
//			+ " SET bifurcation_value = :bifurcationValue  "
//			+ "WHERE bifurcation_level_code= :bifurcationLevelCode AND bifurcation_value_code = :bifurcationValueCode;";
	
	static String UPDATE_SubBifurcationsDtl_DATA="UPDATE accounts.ecgc_acct_sub_bifurcations_dtl SET "
			+ "sub_bifurcation_level=:subBifurcationLevel,"
			+ "bifurcation_value=:bifurcationValue "
			+ " WHERE bifurcation_level_code=:bifurcationLlevelCode AND bifurcation_value_code=:bifurcationValueCode;";

	
	
	static String GET_SubBifurcationsDtl_DATA_BY_ID= "SELECT * FROM ecgc_acct_sub_bifurcations_dtl "
			+ "WHERE bifurcation_level_code= :bifurcationLeveLCode and bifurcation_value_code = :bifurcationValueCode;";
	
	static String GET_VALUE_CODE= "select max(bifurcation_value_code) as maxvalueCode from accounts.ecgc_acct_sub_bifurcations_dtl easbd  where bifurcation_level_code =:bifurcationLevelCode;";
	
	//static String GET_VALUE_CODE= "select coalesce(max(bifurcation_value_code),'0') as maxvalueCode from accounts.ecgc_acct_sub_bifurcations_dtl easbd where bifurcation_level_code =:bifurcationLevelCode;";
	static String DELETE_VALUE= "update accounts.ecgc_acct_sub_bifurcations_dtl  set active= 'N' where bifurcation_level_code = :bifurcationLevelCode and "
			+ "bifurcation_value_code =:bifurcationValueCode and active= 'Y';";

	static String GET_SUB_BIFURCATOIN_VALUE_CODE_LEVEL_CODE= "select *  from accounts.ecgc_acct_sub_bifurcations_dtl easbd  where bifurcation_level_code = :bifurcationLevelCode";

}
