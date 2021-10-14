package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.PettyCashMaster;

public interface PettyCashMasterService {

	Integer addPettyCashDetails(PettyCashMaster pettyCashMaster);
	
	List<PettyCashMaster> listAllPettyCashMaster();
	
	Integer approvedStatus(PettyCashMaster pettyCashMaster);
	
	PettyCashMaster findByRequisitionNo(Integer requisitionNo,String logicalLocCode);

}
