package in.ecgc.smile.erp.accounts.integrate.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.integrate.model.DepartmentMaster;
import in.ecgc.smile.erp.accounts.integrate.model.OfficeMaster;
import in.ecgc.smile.erp.accounts.integrate.model.PhysicalLocationMaster;


public interface OrgStructService {

	public List<OfficeMaster> getOfficeList();
	public List<PhysicalLocationMaster> getAllPhysicalLoc();
	public List<DepartmentMaster> getDeptDetails();
	
}
