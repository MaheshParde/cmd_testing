package in.ecgc.smile.erp.accounts.repository;

import java.util.List;
import java.util.Map;

import in.ecgc.smile.erp.accounts.model.LOVMaster;

public interface LOVMasterDao {
	
	Integer addLOVMstEntry(LOVMaster lovmst);
	
	List<LOVMaster> viewallLOVMstEntries();
	
	List<LOVMaster> getLovByLovcd(String lovcd);
	
	List<Map<String, String>> getT1codes();
	
	List<Map<String, String>> getT2codes();
	
	LOVMaster findLov(String lov_cd, String lov_value , String lov_sub_cd); 
}
