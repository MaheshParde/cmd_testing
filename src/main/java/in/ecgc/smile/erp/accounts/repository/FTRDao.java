package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FtrDtl;

public interface FTRDao {

	public Integer addRequest(FTR ftr);
	public List<FTR> getAllFTRRequest();
	public List<FTR> getAllPendingFTRRequest();
	public List<FTR> getAllFTRRequest(String logicalLoc);
	public List<FtrDtl> getFTRRequestDtl(String FTRId );	
	public Integer decisionOnFTRRequest(FTR ftr);
	public List<FTR> getAllApprovedFTRRequest();
	public Integer updateFtrhdr(FTR ftr);
	public Integer updateFtrDtl(FtrDtl ftr);
	public FTR findFtrDtl(String ftrid);
	public Integer addFtrDetail(FtrDtl ftr);
	public Integer getSeq();
	public Integer getMaxSrNo(int ftrNo);
	public Integer deteleFtrReq(int ftrNo);
	public Integer deteleFtrReqDtl(int ftrNo,int srNo);
	public Integer changeStatusToTransfer(FTR ftr);
}
