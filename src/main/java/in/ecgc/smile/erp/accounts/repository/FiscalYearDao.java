package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.FiscalYearModel;

public interface FiscalYearDao {
	
	FiscalYearModel findCurrentFiscalYear();
	List<String> getFiscalYearList();
}
