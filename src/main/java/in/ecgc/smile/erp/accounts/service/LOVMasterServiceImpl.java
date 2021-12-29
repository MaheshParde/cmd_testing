package in.ecgc.smile.erp.accounts.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.LOVMstEntryNotFound;
import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.repository.LOVMasterDao;


@Service
public class LOVMasterServiceImpl implements LOVMasterService{
	
	@Autowired(required=true)
	LOVMasterDao lovmstdao;
	
	@Override
	public int addLovMstEntry(LOVMaster lovmst) {
		 
			return lovmstdao.addLovMstEntry(lovmst);
		 
	}
	@Override
	public List<LOVMaster> viewallLOVMstEntries() {
		List<LOVMaster> result = lovmstdao.viewallLOVMstEntries();
		return result;
	}
	
	@Override
	public LOVMaster viewLovEntry(String lovCd, String lovSubCd, String lovValue) {
		return lovmstdao.viewLovEntry(lovCd, lovSubCd, lovValue);
	}
	
	@Override
	public int modifyLovEntry(LOVMaster lov) {
		return lovmstdao.modifyLovEntry(lov);
	}
	@Override
	public Integer disableLovEntry(String lovCd, String lovSubCd, String lovValue) {

		LOVMaster lov = viewLovEntry(lovCd, lovSubCd, lovValue);
		if(lov == null) {
			throw new LOVMstEntryNotFound("No LOV detail found for the given LOV Code",
					new String[] {lovCd});
			
		}else
			return lovmstdao.disableLovEntry(lovCd, lovSubCd, lovValue);	
	}
	
	@Override
	public Map<String, String> t1Codes() {
		return lovmstdao.getT1codes();
	}
	
	@Override
	public Map<String, String> t2Codes() {
		return lovmstdao.getT2codes();
	}

	
	@Override
	public Integer setPaymentAdviceNo(Integer refNo,Integer paymentAdviceNo) {
		return lovmstdao.setPaymentAdviceNo(refNo,paymentAdviceNo);
	}
	@Override
	public Integer getRefNo() {
		return lovmstdao.getRefNo();
	}
}
