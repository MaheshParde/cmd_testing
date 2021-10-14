package in.ecgc.smile.erp.accounts.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.repository.LOVMasterDao;

@Service
public class LOVMasterServiceImpl implements LOVMasterService{
	
	@Autowired(required=true)
	LOVMasterDao lovmstdao;
	
	@Override
	public boolean addLOVMstEntry(LOVMaster lovmst) {
		 
			int result=lovmstdao.addLOVMstEntry(lovmst);
					if(result==1)
					{
						return true;
					}
					else{
						return false;
					}	
		 
	}
	@Override
	public List<LOVMaster> viewallLOVMstEntries() {
		List<LOVMaster> result = lovmstdao.viewallLOVMstEntries();
		return result;
	}
	
	@Override
	public List<LOVMaster> findLovByLovcd(String lov_cd) {
		
		return lovmstdao.getLovByLovcd(lov_cd);
	}
	
	@Override
	public List<Map<String, String>> t1Codes() {
		return lovmstdao.getT1codes();
	}
	@Override
	public List<Map<String, String>> t2Codes() {
		return lovmstdao.getT2codes();
	}
	@Override
	public LOVMaster findLov(String lov_cd, String lov_value, String lov_sub_cd) {
			return lovmstdao.findLov(lov_cd, lov_value, lov_sub_cd);
	}
}
