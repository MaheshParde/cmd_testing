package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
import in.ecgc.smile.erp.accounts.repository.FiscalYearDao;

/**
 * Fiscal year service implementation
 * 
 * @version 1.0 15-September-2020
 * @author Sanjali Kesarkar
 *
 */
@Service
public class FiscalYearServiceImpl implements FiscalYearService {
	@Autowired
	FiscalYearDao fiscalYearDao;
	/**
	 * Find current fiscal year details
	 * 
	 * service implementation to find details of current fiscal year
	 * 
	 */
	@Override
	public FiscalYearModel findCurrentFiscalYear() {
		FiscalYearModel fiscalYearModel = fiscalYearDao.findCurrentFiscalYear();
		if (fiscalYearModel != null) {
			return fiscalYearModel;
		}
		return null;
	}
	/**
	 * Get list of fiscal years
	 * 
	 * service implementation to get list of fiscal years
	 * 
	 */
	@Override
	public List<String> getFiscalYearList() {
		List<String> fiscalYearList = fiscalYearDao.getFiscalYearList();
		if (fiscalYearList != null) {
			return fiscalYearList;
		}
		return null; 
	}
	
}
