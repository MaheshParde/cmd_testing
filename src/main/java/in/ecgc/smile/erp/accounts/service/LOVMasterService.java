package in.ecgc.smile.erp.accounts.service;

import java.util.List;
import java.util.Map;

import in.ecgc.smile.erp.accounts.model.LOVMaster;

public interface LOVMasterService {

boolean addLOVMstEntry(LOVMaster lovmst);
	
	List<LOVMaster> viewallLOVMstEntries();

	List<LOVMaster> findLovByLovcd(String lov_cd);
	
	List<Map<String, String>> t1Codes();
	
	List<Map<String, String>> t2Codes();
	LOVMaster findLov(String lov_cd, String lov_value , String lov_sub_cd); 

}
