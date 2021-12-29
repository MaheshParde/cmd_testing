package in.ecgc.smile.erp.accounts.repository;

import java.util.Date;
import java.util.List;

import in.ecgc.smile.erp.accounts.model.TcsMaster;

public interface TcsMasterDao {

	List<TcsMaster> listAllTcs();
	Integer addTcsMaster(TcsMaster tcsMaster);
	TcsMaster checkExistingData(String finYear,Double fromAmount ,Double toAmount, String sex, Date fromDate , Date toDate); 
}
