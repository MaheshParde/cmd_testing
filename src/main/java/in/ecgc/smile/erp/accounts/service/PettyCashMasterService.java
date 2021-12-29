package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.PettyCashMaster;
import in.ecgc.smile.erp.accounts.model.Receipt;


public interface PettyCashMasterService {

	Integer addPettyCashDetails(PettyCashMaster pettyCashMaster);
	
	List<PettyCashMaster> listAllPettyCashMaster();
	
	Integer approvedStatus(PettyCashMaster pettyCashMaster);
	
	PettyCashMaster findByRequisitionNo(Integer requisitionNo,String logicalLocCode);
	
	Long getProcessInstanceId(Integer requisitionNo,String logicalLocCode);
	
	
	Integer updateProcessInstanceId(Integer requisitionNo,String logicalLocCode, Long processInstanceId);
	
	Integer updatePettyCash(Integer requisitionNo,String logicalLocCode , PettyCashMaster pettyCashUpdate); 

}
