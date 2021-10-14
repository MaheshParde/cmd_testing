package in.ecgc.smile.erp.accounts.util;

public interface ScheduleQueries {

	public static final String ADD_SCHEDULE="INSERT INTO ecgc_acct_schedule_mst "+
											"(schedule_cd,schedule_item_cd,description,title_detail_line,updated_by,updated_on"+
											",meta_status,meta_remarks,created_on,created_by,sr_no,prefix,total)"+
											" VALUES (:scheduleCode,:scheduleItemCode,:description,:titelDetailLine,:updatedBy"+
											",:updatedOn,:ecgcStatus,:metaRemark,:createdOn,:createdBy,:srNo,:prifix,:total);";

	public static final String GET_SCHEDULE_BY_SCHEDULE_CD ="SELECT schedule_cd,schedule_item_cd"+
															",description,title_detail_line,updated_by,updated_on,meta_status,meta_remarks,created_on"+
															",created_by,sr_no,prefix,total FROM ecgc_acct_schedule_mst WHERE schedule_cd= :schedule_cd ;";



	public static final String GET_SCHEDULE_BY_SCHEDULE_CD1 ="SELECT * FROM ecgc_acct_schedule_mst WHERE schedule_cd= ? ;";


	
	public static final String GET_ALL_SCHEDULE ="select schedule_cd,schedule_item_cd description,title_detail_line,last_updated_by ,last_updated_dt ,meta_status,\r\n"
			+ "meta_remarks,created_dt\r\n"
			+ ",created_by,prefix,total from accounts.ecgc_acct_schedule_mst;";

	public static final String DELETE_SCHEDULE ="delete from ecgc_acct_schedule_mst where schedule_cd=?;";
	
	public static final String UPDATE_SCHEDULE ="UPDATE ecgc_acct_schedule_mst " + 
			"SET schedule_item_cd=:scheduleItemCode, description=:description, title_detail_line=:titelDetailLine, updated_by=:updatedBy "+
			", updated_on=now(), meta_status=:ecgcStatus, meta_remarks=:metaRemark,sr_no=:srNo, prefix=:prifix, total=:total " + 
			"WHERE schedule_cd=:scheduleCode;";

}
