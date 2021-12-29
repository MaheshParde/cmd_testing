package in.ecgc.smile.erp.accounts.service.sts;

import java.util.List;

public interface StateTransitionService {

	public Long generateProcessInstance(String userId, String processCd, String moduleCd, String startStateName) ;
	
	public String updateProcessInstance(Long processInstanceId, String status, String currentUserId);

	public List<Long> fetchActiveProcessInstances(String stateName, String userId, String processCd, String moduleCd); 
}
