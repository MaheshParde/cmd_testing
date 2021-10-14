package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FtrDtl;

public interface FtrRefundDao {

	public Integer addRefundRequest(FTR ftr);
	public Integer addRefundRequestDtl(FtrDtl ftr);
	public List<FTR> getAllFTRRequest(String logicalLoc);
	public List<FtrDtl> getFTRRequestDtl(String FTRId );	
	public FTR findFtrDtl(String ftrid);
	public Integer decisionOnFTRRequest(FTR ftr);
}
