package in.ecgc.smile.erp.accounts.service;

import java.util.List;
import in.ecgc.smile.erp.accounts.model.TDSMaster;

public interface TDSMasterService {
	
	Boolean addTdsDetails(TDSMaster tdsMaster);
	List<TDSMaster> viewAllTds();
	TDSMaster find(double fromAmount, double toAmount, char sex,String fiscalYr);
	
}
