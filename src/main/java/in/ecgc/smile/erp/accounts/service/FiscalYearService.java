package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.FiscalYearModel;

/**
 * Fiscal year Service Interface
 * 
 * @version 1.0 15-September-2020
 * @author Sanjali Kesarkar
 */
public interface FiscalYearService {
	
	FiscalYearModel findCurrentFiscalYear();
	List<String> getFiscalYearList();
}
