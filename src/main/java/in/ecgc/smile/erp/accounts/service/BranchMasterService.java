package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.BranchMaster;

public interface BranchMasterService {

	Boolean addBranch(BranchMaster branch);
	List<BranchMaster> listAllBranches();
	Integer updateBranch(String logicalLocCode,String branchCode , BranchMaster updateBranch);
	Integer disableBranch(String logicalLocCode,String branchCode );
	
}
