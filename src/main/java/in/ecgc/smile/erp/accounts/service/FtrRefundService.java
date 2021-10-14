package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.exception.ImproperFtrDataProvidedException;
import in.ecgc.smile.erp.accounts.model.FTR;

public interface FtrRefundService {
	public Integer addRefundRequest(FTR ftr) throws ImproperFtrDataProvidedException;
	public List<FTR> getAllFTRRequest(String logicalLoc);
	public FTR getFTRRequestDTL(String FTRId );
    public	Integer decisionOnFTRRequest(FTR ftr) throws ImproperFtrDataProvidedException;
}
