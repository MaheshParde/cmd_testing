package in.ecgc.smile.erp.accounts.service.sts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.integrate.proxy.StateTransitionClient;




@Service
public class StateTransitionServiceImpl implements StateTransitionService{

	@Autowired
	private StateTransitionClient stateTransitionClient;

	@Override 	
	public Long generateProcessInstance(String userId, String processCd, String moduleCd, String startStateName) { 
		return stateTransitionClient.generateProcessInstance(userId, processCd, moduleCd, startStateName); 	
	}

	@Override
	public String updateProcessInstance(Long processInstanceId, String status, String currentUserId) {
		return stateTransitionClient.updateProcessInstance(processInstanceId, status, currentUserId);
	}

	@Override 	
	public List<Long> fetchActiveProcessInstances(String stateName, String userId, String processCd, String moduleCd) {
		return stateTransitionClient.fetchActiveProcessInstances(stateName, userId, processCd, moduleCd); 	
	}

}
