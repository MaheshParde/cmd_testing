package in.ecgc.smile.erp.accounts.integrate.proxy;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import in.ecgc.smile.erp.accounts.integrate.model.DepartmentMaster;
import in.ecgc.smile.erp.accounts.integrate.model.OfficeMaster;
import in.ecgc.smile.erp.accounts.integrate.model.PhysicalLocationMaster;
import in.ecgc.smile.erp.accounts.integrate.model.StateMaster;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrgStructClientFallback implements FallbackFactory<OrgStructClient> {

	@Override
	public OrgStructClient create(Throwable cause) {

		return new OrgStructClient() {

			@Override
			public List<StateMaster> getState() {
				log.info("Service to fetch states is down due to {}", cause.getMessage());
				return Collections.emptyList();
			}

			@Override
			public List<OfficeMaster> getOfficeDetails() {
				log.info("Service to fetch states is down due to {}", cause.getMessage());
				return Collections.emptyList();
			}

			@Override
			public List<PhysicalLocationMaster> viewPhysicalLocationDetails() {
				log.info("Service to fetch states is down due to {}", cause.getMessage());
				return Collections.emptyList();
			}

			@Override
			public List<DepartmentMaster> getDept() {
				log.info("Service to fetch states is down due to {}", cause.getMessage());
				return Collections.emptyList();
			}
		};
	}

}
