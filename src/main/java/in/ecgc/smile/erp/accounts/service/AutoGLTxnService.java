package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.AutoGLTxnRequestModel;

public interface AutoGLTxnService {
	Integer createTxnRequest(AutoGLTxnRequestModel autoGLTxnRequestModel) throws Exception;
	List<AutoGLTxnRequestModel> getAllAutoTxnRequest();
	AutoGLTxnRequestModel getAllAutoTxnRequestById(String requestId);
	Integer updateAutoTxnRequest(AutoGLTxnRequestModel autoGLTxnRequestModel);
	Integer updateRequestStatus(String reqId, String status);
}
