package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.model.BranchMaster;

@Repository
@Transactional
public interface BranchMasterDao {	
	Integer addBranch(BranchMaster branch);
	List<BranchMaster> listAllBranches();
	Integer updateBranch(String logicalLocCode,String branchCode , BranchMaster updateBranch);
	Integer disableBranch(String logicalLocCode,String branchCode );
	BranchMaster findBranchByLogicalLocationAndBankCode(String logicalLocCode ,String BranchCode);

}
