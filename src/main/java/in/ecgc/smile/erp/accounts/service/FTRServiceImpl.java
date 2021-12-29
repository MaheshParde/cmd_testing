package in.ecgc.smile.erp.accounts.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.ImproperFtrDataProvidedException;
import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FtrDtl;
import in.ecgc.smile.erp.accounts.repository.FTRDao;
import in.ecgc.smile.erp.accounts.util.FtrReportedToBankExcel;

@Service
public class FTRServiceImpl implements FTRService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FTRServiceImpl.class);

	@Autowired
	FTRDao ftrDao;
	
	@Override
	public Integer addRequest(FTR ftr) throws ImproperFtrDataProvidedException {
		LOGGER.info("--inside add FTR Service--");
		ftr.setFtrReqBy(101);
		try {
			int reqNo = ftrDao.getSeq();
			ftr.setFtrReqNo(reqNo);
			if(ftr.getFtrTrfAmt() >=50000 && ftr.getFtrTrfAmt()%10000 == 0) {
				if(ftrDao.addRequest(ftr)==1) {
					int srno =1;
					for (FtrDtl temp : ftr.getFtrDtl()) {
						temp.setFTRRequestNo(ftr.getFtrReqNo());
						temp.setFTRRequestSrNo(srno);
						temp.setLogicalLocCode(ftr.getLogicalLocCode());
						ftrDao.addFtrDetail(temp);
						srno+=1;
					}
				return reqNo;
				}
				else {
					LOGGER.info("not inserted");
					return 0;
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
			LOGGER.info("Exception Occured",e);
			return null;
		}
		
	}

	@Override
	public List<FTR> getAllFTRRequest() {
		try {
		return ftrDao.getAllFTRRequest();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<FTR> getAllPendingFTRRequest() {
		return ftrDao.getAllPendingFTRRequest();
	}
	
	@Override
	public List<FTR> getAllFTRRequest(String logicalLoc) {
		return ftrDao.getAllFTRRequest(logicalLoc);
	}

	@Override
	public FTR getFTRRequestDTL(String FTRId) {
		return ftrDao.findFtrDtl(FTRId);
	}

	@Override
	public Integer decisionOnFTRRequest(FTR ftr) {
		try {
			int result = ftrDao.decisionOnFTRRequest(ftr); 
			if(result != 0) {
			}
			return result;
			
		}
		catch(Exception e) {
			LOGGER.info("Exception Occured",e);	
		return 0;
		}
	}
	@Override
	public Integer updateFTRRequest(FTR ftr) {
		LOGGER.info(ftr.toString());
		ftr.setFtrReqBy(101);
		double amt=0;
			try {
			/*
			 * FTR oldFtr = ftrDao.findFtrDtl(ftr.getFtrReqNo().toString()); List<FtrDtl>
			 * oldFtrDtl = oldFtr.getFtrDtl(); Iterator<FtrDtl> itr = oldFtrDtl.iterator();
			 * int max=1; while(itr.hasNext()) { FtrDtl temp1 = itr.next();
			 * if(temp1.getFTRRequestSrNo() > max) { max = temp1.getFTRRequestSrNo(); } }
			 */

				Integer oldSrNo = ftrDao.getMaxSrNo(ftr.getFtrReqNo());
				Integer srno=0;
				for (FtrDtl temp : ftr.getFtrDtl()) {
					srno += 1;
					temp.setFTRRequestNo(ftr.getFtrReqNo());
					temp.setBranchCode(ftr.getFtrReqBranchCd());
					temp.setLogicalLocCode(ftr.getLogicalLocCode());
					if(temp.getFTRRequestSrNo() != null) {
						if(ftrDao.updateFtrDtl(temp) != 1){
						return 0;
							}
					}
//					else {
//						temp.setFTRRequestSrNo(srno);
//						ftrDao.addFtrDetail(temp);
//					}
					amt += temp.getFTRRequestAmount();
				}
				while(oldSrNo > srno) {
					ftrDao.deteleFtrReqDtl(ftr.getFtrReqNo(), srno);
					srno +=1;
				}
				ftr.setFtrTrfAmt(amt);
				if(ftrDao.updateFtrhdr(ftr)==1)
					return 1;
			}
			catch(Exception e ) {

				LOGGER.info("Exception Occured",e);
			}
			
		return 0;
	}
	
	@Override
	public Integer deleteFTRRequest(Integer ftrNo) {
		FTR ftr =ftrDao.findFtrDtl(ftrNo.toString());
		if(ftr.getFtrReqStatus().charAt(0) != 'P') {
			return -2;
		}
		return ftrDao.deteleFtrReq(ftrNo);
	}

	@Override
	public Integer decisionOnMultipleFTRRequest(List<FTR> ftrs)  {
		try {
			int result=0;
			for(FTR ftr : ftrs){
				result += ftrDao.decisionOnFTRRequest(ftr); 

			}
			return result;
			
		}
		/*
		 * catch(IOException e) { System.err.println("--Sheet can not be generated--");
		 * return 0; }
		 */
		catch(Exception e) {
			LOGGER.info("Exception Occured",e);
			return 0;
		}
	}

	@Override
	public List<FTR> getAllApprovedFTRRequest() {
		try {
			return ftrDao.getAllApprovedFTRRequest();
		}
		catch(Exception e) {
			LOGGER.info("Exception Occured",e);
			return null;
		}
	}

	@Override
	public Integer generateExcelForFTRRequest(String[] ftrCds, String bank) {
		try {
			List<FTR> ftrs = new ArrayList<FTR>();
			for(String ftrCd : ftrCds) {
				FTR ftr = ftrDao.findFtrDtl(ftrCd);
				ftr.setFtrReqStatus("E");
				ftrs.add(ftr);
			}
			if(this.decisionOnMultipleFTRRequest(ftrs) >=1)
				return FtrReportedToBankExcel.generateSheet(ftrs, bank);
			else
				return 0;
		}
		catch(IOException e) {
			LOGGER.info("Exception Occured",e);

			return 0;
		}
		catch(Exception e) {
			LOGGER.info("Exception Occured",e);

			return 0;
		}
		
	}
	
	
	@Override
	public Integer changeStatusToTransfer(FTR ftr) throws ImproperFtrDataProvidedException {
		try {
			int result = ftrDao.changeStatusToTransfer(ftr);
			if(result >= 1) 
				return 1;
			else
				throw new ImproperFtrDataProvidedException("Transfer date can not be updated");
		}
		catch(ImproperFtrDataProvidedException e) {
			LOGGER.info("Exception Occured",e);
			throw e;
		}
		catch(Exception e) {
			LOGGER.info("Exception Occured",e);

			throw e;
		}
	}
	
}
