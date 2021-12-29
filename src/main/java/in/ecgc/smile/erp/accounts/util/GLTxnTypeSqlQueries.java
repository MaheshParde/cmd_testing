package in.ecgc.smile.erp.accounts.util;

public interface GLTxnTypeSqlQueries {
	public static final String ADD_GL_TXN_TYPE_CODE = 
	"INSERT INTO ecgc_acct_gl_txn_type_mst (txn_type, txn_type_name, description, status, del_flag, config_flag, opening_day, "
		+ "meta_status, created_by, created_dt, meta_remarks)"	 
		+ "VALUES (:glTxnType, :txnTypeName, :description,true,  :status, :isConfigurable, :openingDay, " 
		+ ":metaStatus, :createdBy, now(), :metaRemarks)";
	
	public static final String LOAD_GL_TXN_TYPE_CODE =
	"SELECT * FROM ecgc_acct_gl_txn_type_mst WHERE txn_type = :glTxnType";
	
	public static final String ALL_GL_TXN_TYPE_CODES =
	"select * from ecgc_acct_gl_txn_type_mst order by config_flag desc, txn_type";
	
	public static final String UPDATE_GL_TXN_TYPE_CODE = " UPDATE ecgc_acct_gl_txn_type_mst SET txn_type_name = :txnTypeName, "
			+ "description = :description, status = :status, config_flag = :isConfigurable, opening_day = :openingDay, "
			+ "last_updated_by = :updatedBy, last_updated_dt = now(), "
			+ "meta_remarks = :metaRemarks WHERE txn_type = :glTxnType";
	
	public static final String DISABLE_GL_TXN_TYPE_CODE = "UPDATE ecgc_acct_gl_txn_type_mst SET del_flag = false WHERE txn_type = :glTxnType";
	
	public static final String GL_TXN_TYPE= "select distinct(txn_type) from ecgc_acct_gl_txn_type_mst";
	
}
	