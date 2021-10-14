package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.exception.ImproperFtrDataProvidedException;
import in.ecgc.smile.erp.accounts.model.FTR;

public interface FTRService {

	public Integer addRequest(FTR ftr) throws ImproperFtrDataProvidedException;
	public List<FTR> getAllFTRRequest();
	public List<FTR> getAllPendingFTRRequest();
	public List<FTR> getAllApprovedFTRRequest();
	public List<FTR> getAllFTRRequest(String logicalLoc);
	public FTR getFTRRequestDTL(String FTRId );	
	public Integer decisionOnFTRRequest(FTR ftr) throws ImproperFtrDataProvidedException;
	public Integer decisionOnMultipleFTRRequest(List<FTR> ftrs) throws ImproperFtrDataProvidedException;
	public Integer updateFTRRequest(FTR ftr) throws ImproperFtrDataProvidedException;
	public Integer deleteFTRRequest(Integer ftrNo);
	public Integer generateExcelForFTRRequest(String[] ftrs , String bank);
	public Integer changeStatusToTransfer(FTR ftr) throws ImproperFtrDataProvidedException; 
	
}
