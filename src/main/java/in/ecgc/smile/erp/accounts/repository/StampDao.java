package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.exception.StampIncompleteDataException;
import in.ecgc.smile.erp.accounts.model.StampParameterModel;

/**
 * 
 * Stamp Parameter
 * 
 * @author Amitesh Banerjee
 * @version 1.0 27-April-2020
 *
 */

public interface StampDao {

	public Integer addStampParameter(StampParameterModel stampParameter);
	
	public StampParameterModel updateStampParameter(Integer stampCode,StampParameterModel stampParameterUpdate);
	
	public List<StampParameterModel> allStampParameter();
	
	public StampParameterModel viewByStampCode(Integer stampCode);
	
	public Integer getStampAmtByFromAndToAmt(Integer receiptAmount);

	//public Integer getStampAmtByFromAndToAmt(Integer fromAmount , Integer toAmount);
}
