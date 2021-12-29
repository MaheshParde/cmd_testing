package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.ImproperFtrDataProvidedException;
import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FtrDtl;
import in.ecgc.smile.erp.accounts.repository.FTRDao;
import in.ecgc.smile.erp.accounts.repository.FtrRefundDao;

@Service
public class FtrRefundServiceImpl implements FtrRefundService {
	
	@Autowired
	FtrRefundDao ftrRefundDao;
	@Autowired
	FTRDao ftrDao;
	
	public FtrRefundServiceImpl() {
	}

	@Override
	public Integer addRefundRequest(FTR ftr) throws ImproperFtrDataProvidedException {
		ftr.setFtrReqBy(101);
		try {
			int reqNo = ftrDao.getSeq();
			ftr.setFtrReqNo(reqNo);
			ftr.setFtrType("refund");
			if(ftr.getFtrTrfAmt() >=50000 && ftr.getFtrTrfAmt()%10000 == 0) {
				if(ftrRefundDao.addRefundRequest(ftr)==1) {
					int srno =1;
					for (FtrDtl temp : ftr.getFtrDtl()) {
						temp.setFTRRequestNo(ftr.getFtrReqNo());
						temp.setFTRRequestSrNo(srno);
						temp.setLogicalLocCode(ftr.getLogicalLocCode());
						ftrRefundDao.addRefundRequestDtl(temp);
						srno+=1;
					}
				return reqNo;
				}
				else {
					throw new Exception("Request can not added");
				}
			}
			else {
				throw new ImproperFtrDataProvidedException("Data not provided properly");
			}
		}
		catch(ImproperFtrDataProvidedException exception) {
				throw new ImproperFtrDataProvidedException("Data not provided properly");
				
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@Override
	public List<FTR> getAllFTRRequest(String logicalLoc) {
		
		return ftrRefundDao.getAllFTRRequest(logicalLoc);
	}

	@Override
	public FTR getFTRRequestDTL(String FTRId) {
		return ftrDao.findFtrDtl(FTRId);
	}
	
	@Override
	public Integer decisionOnFTRRequest(FTR ftr) throws ImproperFtrDataProvidedException {
		try {
			String logicalLog = "MUMBAILOG1";
			int result = 0;
			if(logicalLog.equalsIgnoreCase(ftr.getReqTo())){
				result = ftrDao.decisionOnFTRRequest(ftr);
			}
			else {
				throw new ImproperFtrDataProvidedException("User have not permitted to take decision on this request");
			}
			 
			if(result != 0) {
			}
			return result;
			
		}
		catch(ImproperFtrDataProvidedException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


}
