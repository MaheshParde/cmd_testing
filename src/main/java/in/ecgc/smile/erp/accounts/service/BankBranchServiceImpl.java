package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.BankBranchNotFoundException;
import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.repository.BankBranchDao;

@Service
public class BankBranchServiceImpl implements BankBranchService{

	@Autowired
	BankBranchDao bankBranchDao;
	
	@Override
	public Boolean addBankBranch(BankBranch bankBranch) {
		
		int result = bankBranchDao.addBankBranch(bankBranch);
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<BankBranch> listAllBankBranches(){
		return bankBranchDao.listAllBankBranches();
	}
	
	@Override
	public Integer updateBankBranch(String logicalLocCode,String bankName , BankBranch updateBankbranch)
	{
		BankBranch bankBranch = bankBranchDao.findBankByLogicalLocationAndBankName(logicalLocCode, bankName);
		if (bankBranch == null) {
			throw new BankBranchNotFoundException(
					"No bank branch details found with the given location code and bank name ",
					new String[] { logicalLocCode , bankName});
		}
		return bankBranchDao.updateBankBranch(logicalLocCode, bankName, updateBankbranch);
	}

	@Override
	public BankBranch findBankByLogicalLocationAndBankName(String logicalLocCode ,  String bankName)
	{
		BankBranch bankBranch = bankBranchDao.findBankByLogicalLocationAndBankName(logicalLocCode, bankName);
		if (bankBranch == null) {
			throw new BankBranchNotFoundException(
					"No bank branch details found with the given location code and bank name ",
					new String[] {logicalLocCode,bankName });
		}
		return bankBranch;
	}
	
	@Override
	public Integer disableBankBranch(String logicalLocCode,String bankName )
	{
		BankBranch bankBranch = bankBranchDao.findBankByLogicalLocationAndBankName(logicalLocCode, bankName);
		if (bankBranch == null) {
			throw new BankBranchNotFoundException(
					"No bank branch details found with the given location code and bank name ",
					new String[] { logicalLocCode,bankName });
		}
		return bankBranchDao.disableBankBranch(logicalLocCode, bankName);		
	}
	@Override
	public List<BankBranch> listActiveBankBranches()
	{
		return bankBranchDao.listActiveBankBranches();	
	}

	@Override
	public  String getGstinByLogicalLoc(String logicalLocCode) {
		
		return bankBranchDao.getGstinByLogicalLoc(logicalLocCode);
	}
	
}
