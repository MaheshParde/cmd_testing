package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.DataAlreadyExist;
import in.ecgc.smile.erp.accounts.exception.FromAmountIsGreaterThanToAmount;
import in.ecgc.smile.erp.accounts.exception.FromDateIsGreaterThanToDate;
import in.ecgc.smile.erp.accounts.model.TDSMaster;
import in.ecgc.smile.erp.accounts.repository.TDSMasterDao;
@Service
public class TDSMasterServiceImpl implements TDSMasterService {
	
	@Autowired
	TDSMasterDao tdsMasterDao;
	
	@Override
	public Boolean addTdsDetails(TDSMaster tdsMaster) {
		
		if(tdsMasterDao.checkExistingTdsDetail(tdsMaster.getFromAmount(),tdsMaster.
		  getToAmount(),tdsMaster.getSex(),tdsMaster.getFiscalYr()) !=null) 
			throw new DataAlreadyExist("Data is Already Exist",new Object[] { }); 
		   
		else if(tdsMaster.getFromDt().after(tdsMaster.getToDt())) 
			throw new FromDateIsGreaterThanToDate("from date is greater than to date", new Object[]
					{tdsMaster.getFromDt()});
		  
		else if(tdsMaster.getFromAmount() >= tdsMaster.getToAmount()) 
			throw new FromAmountIsGreaterThanToAmount("from amount is greater than to amount", new
		 Object[] { tdsMaster.getFromDt()}); 
		
		else{
			int result = tdsMasterDao.addTdsDetails(tdsMaster);
			if (result == 1)
				return true;
			else
				return false;
			} 
			
}
	
	@Override
	public List<TDSMaster> viewAllTds() {
		return tdsMasterDao.viewAllTds();
	}

	@Override
	public TDSMaster find(double fromAmount, double toAmount, char sex, String fiscalYr) {
		
		TDSMaster tdsMaster=tdsMasterDao.checkExistingTdsDetail(fromAmount,toAmount,sex,fiscalYr);
		 
		return tdsMaster;
	}
}
