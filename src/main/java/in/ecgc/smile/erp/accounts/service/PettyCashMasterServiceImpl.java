package in.ecgc.smile.erp.accounts.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.model.PettyCashMaster;
import in.ecgc.smile.erp.accounts.repository.PettyCashMasterDao;
import in.ecgc.smile.erp.sys.auth.be.service.UserInfoService;

@Service
public class PettyCashMasterServiceImpl implements PettyCashMasterService{
	
	@Autowired
	PettyCashMasterDao pettyCashMasterDao;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Override
	public Integer addPettyCashDetails(PettyCashMaster pettyCashMaster) {
	   
	    
	    if(userInfoService.getUser() == null)
	    	pettyCashMaster.setCreatedBy("ACCOUNTS");
	    else
	    	pettyCashMaster.setCreatedBy(userInfoService.getUser());
	  
		pettyCashMaster.setRequisitionNo(pettyCashMasterDao.getRequisitionNo(pettyCashMaster.getLogicalLocCode(), pettyCashMaster.getFiscalYr()));;
		
		int result = pettyCashMasterDao.addPettyCashDetails(pettyCashMaster);
		System.err.println(pettyCashMaster.getRequisitionNo());

		if (result == 1)
			return pettyCashMaster.getRequisitionNo();
		else
			return 0;
	}

	@Override
	public List<PettyCashMaster> listAllPettyCashMaster() {
		
		try {
			return pettyCashMasterDao.listAllPettyCash();
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public PettyCashMaster findByRequisitionNo(Integer requisitionNo,String logicalLocCode) {
		
		return pettyCashMasterDao.findByRequisitionNo(requisitionNo, logicalLocCode);
	}

	@Override
	public Integer approvedStatus(PettyCashMaster pettyCashMaster) {
		
		return pettyCashMasterDao.approvedStatus(pettyCashMaster);
	}

}
