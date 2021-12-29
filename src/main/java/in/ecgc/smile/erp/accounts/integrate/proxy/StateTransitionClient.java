package in.ecgc.smile.erp.accounts.integrate.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * State Transition Client Class
 * @author Aakash Narayane
 *
 */
//in local
@FeignClient(name = "erp-sys-apigateway", url = "http://10.210.0.140:31335",contextId = "StateTransitionFeignClient", fallback = StateTransitionFallback.class)
//@FeignClient(name = "erp-bs-sts-be", url = "http://kmaster.cdacmumbai.in:31444/",contextId = "StateTransitionFeignClient", fallback = StateTransitionFallback.class)
//@FeignClient(name = "erp-bs-sts-be", url = "http://10.210.0.223:8888",contextId = "StateTransitionFeignClient", fallback = StateTransitionFallback.class)

// Enable this for SIT
//@FeignClient(name = "${apigw.service}", contextId = "StateTransitionClient", fallbackFactory = StateTransitionFallback.class)
public interface StateTransitionClient  {

	@PostMapping("/erp-bs-sts-be/basesystem/state-transition-service/process-instance")
	//@PostMapping("/basesystem/state-transition-service/process-instance")
	public Long generateProcessInstance(@RequestParam(required = true) String userId, @RequestParam(required = true)String processCd, @RequestParam(required = true)String moduleCd, @RequestParam(required = false)String startStateName);
	
	@PutMapping("/erp-bs-sts-be/basesystem/state-transition-service/update-process-instance")
	//@PutMapping("/basesystem/state-transition-service/update-process-instance")
	public String updateProcessInstance(@RequestParam(required = true)Long processInstanceId, @RequestParam(required = true) String status, @RequestParam(required = true)String currentUserId);

	@GetMapping("/erp-bs-sts-be/basesystem/state-transition-service/process-instances/active") 	
	//@GetMapping("/basesystem/state-transition-service/process-instances/active") 	
	public List<Long> fetchActiveProcessInstances(@RequestParam(required = true) String stateName, @RequestParam(required = true) String userId, @RequestParam(required = true) String processCd, @RequestParam(required = true) String moduleCd);
	
}
