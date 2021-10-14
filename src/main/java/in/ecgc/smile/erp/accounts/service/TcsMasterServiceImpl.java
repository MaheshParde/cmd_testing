package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.model.TcsMaster;
import in.ecgc.smile.erp.accounts.repository.TcsMasterDao;
@Service
public class TcsMasterServiceImpl implements TcsMasterService {

	@Autowired
	TcsMasterDao tcsMasterDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TcsMasterServiceImpl.class);

	@Override
	public List<TcsMaster> listAllTcs(){
		return tcsMasterDao.listAllTcs();
	}

	@Override
	public Boolean addTcsMaster(TcsMaster tcsMaster) {
		if(tcsMasterDao.checkExistingData(tcsMaster.getFinYear(), tcsMaster.getFromAmount()	, tcsMaster.getToAmount(),tcsMaster.getSex(),tcsMaster.getFromDate(),tcsMaster.getToDate())!= null) {
				LOGGER.info("Slab already exist!!!");	
				return false;
			}
		else if (tcsMaster.getFromDate().after(tcsMaster.getToDate()) ) {
		LOGGER.info("from Date should be less than To Date");
				return false;
			}
		else if (tcsMaster.getFromAmount() >= tcsMaster.getToAmount()) {
				LOGGER.info("from Amount should be less than To Amount");
				return false;
			}
		else { 
				 int result = tcsMasterDao.addTcsMaster(tcsMaster);
				 if (result == 1 ) {return true;}
				 else {
					 return false;
				}
				 				
			} 
		}

}
