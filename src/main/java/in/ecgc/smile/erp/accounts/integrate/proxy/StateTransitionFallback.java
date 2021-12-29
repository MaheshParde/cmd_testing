package in.ecgc.smile.erp.accounts.integrate.proxy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * State Transition Client Class
 * @author Aakash Narayane
 *
 */
@Component
public class StateTransitionFallback implements FallbackFactory<StateTransitionClient> {
	private static final Logger LOGGER = LoggerFactory.getLogger(StateTransitionFallback.class);

	@Override
	public StateTransitionClient create(Throwable cause) {
		LOGGER.error("State Transition Service is down!");
		LOGGER.info("Cause {} =>",cause);
		return null;
	}

	
}
