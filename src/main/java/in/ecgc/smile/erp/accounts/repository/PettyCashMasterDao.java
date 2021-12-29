package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.PettyCashMaster;
import in.ecgc.smile.erp.accounts.model.Receipt;


public interface PettyCashMasterDao {
	
	Integer	addPettyCashDetails(PettyCashMaster pettyCashMaster);

	Integer getRequisitionNo(String logicalLocCode, String fiscalYr);

	List<PettyCashMaster> listAllPettyCash();
	
	PettyCashMaster findByRequisitionNo(Integer requisitionNo,String logicalLocCode);
	
	Integer updatePettyCash(Integer requisitionNo,String logicalLocCode,  PettyCashMaster pettyCashUpdate);

	Integer approvedStatus(PettyCashMaster pettyCashMaster);
	
	Long getProcessInstanceId(Integer requisitionNo,String logicalLocCode);
	
	Integer updateProcessInstanceId(Integer requisitionNo,String logicalLocCode, Long processInstanceId);
}
