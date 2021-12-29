package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.TcsMaster;

public interface TcsMasterService {

	List<TcsMaster> listAllTcs();
	Boolean addTcsMaster(TcsMaster tcsMaster);

}
