package in.ecgc.smile.erp.accounts.util;

import java.util.Date;

public interface BranchMasterSqlQueries {

	public final String VIEW_ALL="select * from accounts.ecgc_acct_branch_mst;";
	
	public final String ADD_BRANCH= "INSERT INTO accounts.ecgc_acct_branch_mst "
			+ "(logical_loc_cd, branch_cd, branch_name, bank_name, bank_branch_address, "
			+ "gstin_no, gststateut, gststateutcode, expense_acct_number, "
			+ "expense_acct_ifsc_cd, expense_acct_neft_cd, collection_acct_number,"
			+ " collection_acct_ifsc_cd, collection_acct_neft_cd, client_id, virtual_id,"
			+ " active, created_by, created_dt, last_updated_by, last_updated_dt,"
			+ " meta_status, meta_remarks)"
			+ "VALUES(:logicalLocCode, :branchCode, :branchName, :bankName, :bankBranchAddress,"
			+ " :gstinNumber, :gstStateUt, :gstStateUtCode, :expenseAccountNumber,:expenseAccountIfscCode, "
			+ " :expenseAccountsNeftCode, :collectionAccountNumber, :collectionAccountIfscCode,"
			+ " :collectionAccountNeftCode, :clientId, :virtualId, :active, :createdBy, "
			+ " now(), :lastUpdatedBy,now(), :metaStatus,:metaRemarks);";
	
	public final String UPDATE_BRANCH_DETAILS= "UPDATE accounts.ecgc_acct_branch_mst "
			+ "SET branch_name=:branchName, bank_name=:bankName, "			
			+ "bank_branch_address=:bankBranchAddress, "
			+ "gstin_no=:gstinNumber, gststateut=:gstStateUt,"
			+ " gststateutcode=:gstStateUtCode, expense_acct_number=:expenseAccountNumber,"
			+ " expense_acct_ifsc_cd=:expenseAccountIfscCode,"
			+ " expense_acct_neft_cd=:expenseAccountsNeftCode, collection_acct_number=:collectionAccountNumber, "
			+ "collection_acct_ifsc_cd=:collectionAccountIfscCode,"
			+ " collection_acct_neft_cd=:collectionAccountNeftCode, "
			+ "client_id=:clientId, virtual_id=:virtualId, active=:active, "
			+ "created_by=:createdBy, created_dt=now(), last_updated_by=:lastUpdatedBy,"
			+ " last_updated_dt=now(), meta_status=:metaStatus, meta_remarks=:metaRemarks "
			+ "WHERE logical_loc_cd=:logicalLocCode AND branch_cd=:branchCode;";
	
	public final String DISABLE_BRANCH="UPDATE accounts.ecgc_acct_branch_mst SET active = false "
			+ "WHERE logical_loc_cd =:logicalLocCode and branch_cd=:branchCode;";		
	
	
	public final String VIEW_BY_LOGICAL_LOC_AND_BRANCH_CODE="select * from accounts.ecgc_acct_branch_mst "
			+ "  where logical_loc_cd =:logicalLocCode and branch_cd =:branchCode;";
	
}
