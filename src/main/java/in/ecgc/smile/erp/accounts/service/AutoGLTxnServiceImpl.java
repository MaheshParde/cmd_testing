package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.model.AutoGLTxnRequestModel;
import in.ecgc.smile.erp.accounts.repository.AutoGLTxnDao;

@Service
public class AutoGLTxnServiceImpl implements AutoGLTxnService{

	@Autowired
	AutoGLTxnDao autoGLTxnDao;
	
	@Override
	public Integer createTxnRequest(AutoGLTxnRequestModel autoGLTxnRequestModel) throws Exception {
		if (autoGLTxnDao.createTxnRequest(autoGLTxnRequestModel) >= 1) 
			return 1;
			else
				throw new Exception("Something Went Worng");
		
	}

	@Override
	public List<AutoGLTxnRequestModel> getAllAutoTxnRequest() {
		return autoGLTxnDao.getAllAutoTxnRequest();
	}

	@Override
	public AutoGLTxnRequestModel getAllAutoTxnRequestById(String requestId) {
		return autoGLTxnDao.getAllAutoTxnRequestById(requestId);
	}

	@Override
	public Integer updateAutoTxnRequest(AutoGLTxnRequestModel autoGLTxnRequestModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateRequestStatus(String reqId, String status) {
		
		return autoGLTxnDao.updateRequestStatus(reqId, status);
	}

	
}
