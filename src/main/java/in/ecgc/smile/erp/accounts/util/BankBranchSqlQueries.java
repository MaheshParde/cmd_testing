package in.ecgc.smile.erp.accounts.util;

public interface BankBranchSqlQueries {

	
	public static final String ADD_BANK_BRANCH=	"INSERT INTO ecgc_acct_bank_branch_mst(\r\n" + 
			"	logical_loc_cd, bank_name, bank_branch_name, expense_acct_number, expense_acct_ifsc_cd,"
			+ " collection_acct_number, collection_acct_ifsc_cd, client_id, virtual_id, active, created_by, "
			+ "created_dt, last_updated_by, last_updated_dt, ecgc_status, meta_remarks, gstin)\r\n" + 
			"	VALUES (:logicalLocCode, :bankName, :bankBranchName, :expenseAccountNumber, :expenseAccountIfscCode,"
			+ " :collectionAccountNumber, :collectionAccountIfscCode, :clientId, :virtualId, :active, :createdBy, now(),"
			+ " :lastUpdatedBy, now(), :ecgcStatus, :metaRemarks, :gstin)";
					
	public static final String UPDATE_BANK_BRACH="UPDATE ecgc_acct_bank_branch_mst\r\n" + 
			"	SET bank_branch_name=:bankBranchName, "
			+ "expense_acct_number=:expenseAccountNumber,"
			+ " expense_acct_ifsc_cd=:expenseAccountIfscCode,"
			+ " collection_acct_number=:collectionAccountNumber,"
			+ " collection_acct_ifsc_cd=:collectionAccountIfscCode,"
			+ " client_id=:clientId,"
			+ " virtual_id=:virtualId,"
			+ " active=:active,"
			+ " gstin =:gstin,"
			+ " created_by=:createdBy,"
			+ " created_dt=now(),"
			+ " last_updated_by=:lastUpdatedBy,"
			+ " last_updated_dt=now(), "
			+ "ecgc_status=:ecgcStatus, "
			+ "meta_remarks=:metaRemarks"
			+ "	WHERE logical_loc_cd= :logicalLocCode and bank_name=:bankName";
	
	public static final  String ALL_BANK_BRANCHES= "SELECT * FROM ecgc_acct_bank_branch_mst";
	
	public static final String LOAD_BANK_BRANCH_DETAILS ="select * from ecgc_acct_bank_branch_mst where "
			+ "logical_loc_cd=:logicalLocCode and bank_name=:bankName";

	public static final  String DISABLE_BANK_BRANCH="UPDATE ecgc_acct_bank_branch_mst SET active = false "
			+ "WHERE logical_loc_cd =:logicalLocCode"
			+ " and bank_name=:bankName";		
	public static final String ALL_ACTIVE_BANK_BRANCHES =
			"select * from ecgc_acct_bank_branch_mst where active = 'true'";
	
	public static final String SELECT_GSTIN_BY_LOGICAL_LOC= "select distinct gstin  from ecgc_acct_bank_branch_mst where "
			+ "logical_loc_cd=:logicalLocCode";
}

