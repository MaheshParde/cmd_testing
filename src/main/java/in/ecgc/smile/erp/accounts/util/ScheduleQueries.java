package in.ecgc.smile.erp.accounts.util;

public interface ScheduleQueries {

	public static final String ADD_SCHEDULE="INSERT INTO ecgc_acct_schedule_mst "+
											"(entity_cd, schedule_cd,schedule_item_cd,description,title_detail_line,last_updated_by,last_updated_dt"+
											",meta_status,meta_remarks,created_dt,created_by,prefix,total)"+
											" VALUES (:entity_cd,:schedule_cd,:schedule_item_cd,:description,:title_detail_line,:last_updated_by"+
											",now(),:meta_status,:meta_remarks,now(),:created_by,:prefix,:total);";

	public static final String GET_SCHEDULE_BY_SCHEDULE_CD ="SELECT schedule_cd,schedule_item_cd"+
															",description,title_detail_line,last_updated_by,last_updated_dt,meta_status,meta_remarks,created_dt"+
															",created_by,prefix,total FROM ecgc_acct_schedule_mst WHERE schedule_cd= :schedule_cd ;";



	public static final String GET_SCHEDULE_BY_SCHEDULE_CD1 ="SELECT * FROM ecgc_acct_schedule_mst WHERE schedule_cd = :schedule_cd and schedule_item_cd = :schedule_item_cd ;";


	
	public static final String GET_ALL_SCHEDULE ="select schedule_cd,schedule_item_cd ,description,title_detail_line,last_updated_by ,last_updated_dt ,meta_status,\r\n"
			+ "meta_remarks,created_dt\r\n"
			+ ",created_by,prefix,total from accounts.ecgc_acct_schedule_mst;";

	public static final String DELETE_SCHEDULE ="update ecgc_acct_schedule_mst set meta_status = :meta_status where schedule_cd=:schedule_cd and schedule_item_cd = :schedule_item_cd;";
	
	public static final String UPDATE_SCHEDULE ="UPDATE ecgc_acct_schedule_mst " + 
			"SET description=:description, title_detail_line=:title_detail_line, last_updated_by=:last_updated_by "+
			", last_updated_dt=now(), meta_status=:meta_status, meta_remarks=:meta_remarks, prefix=:prefix, total=:total " + 
			"WHERE schedule_cd=:schedule_cd and schedule_item_cd=:schedule_item_cd ";

}
