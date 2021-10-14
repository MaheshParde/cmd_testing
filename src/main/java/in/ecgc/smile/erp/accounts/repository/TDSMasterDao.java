package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.TDSMaster;

public interface TDSMasterDao {

	Integer addTdsDetails(TDSMaster tdsMaster);
	List<TDSMaster> viewAllTds();
	TDSMaster checkExistingTdsDetail(Double fromAmount, Double toAmount, char sex, String fiscalYr);
}
