package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.BankBranch;

public interface BankBranchDao {
	
	Integer addBankBranch(BankBranch bankBranch);
	 BankBranch findBankByLogicalLocationAndBankName(String logicalLocCode ,  String bankName);
	List<BankBranch> listAllBankBranches();
	Integer updateBankBranch(String logicalLocCode,String bankName , BankBranch updateBankbranch);
   Integer disableBankBranch(String logicalLocCode,String bankName );
   List<BankBranch> listActiveBankBranches();
   String getGstinByLogicalLoc(String logicalLocCode);



}
