package in.ecgc.smile.erp.accounts.repository;

import java.util.List;
import java.util.Map;

import in.ecgc.smile.erp.accounts.model.LOVMaster;

public interface LOVMasterDao {
	
	Integer addLovMstEntry(LOVMaster lovmst);
	
	Map<String, String> getT1codes() ;
	
	Map<String, String> getT2codes() ;


	List<LOVMaster> viewallLOVMstEntries();

	LOVMaster viewLovEntry(String lovCd, String lovSubCd, String lovValue);

	int modifyLovEntry(LOVMaster lov);

	Integer disableLovEntry(String lovCd, String lovSubCd, String lovValue);
	
	//for temp 
	Integer getRefNo();
	
	Integer setPaymentAdviceNo(Integer refNo, Integer paymentAdviceNo);
}
