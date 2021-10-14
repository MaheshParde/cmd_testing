package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.StampIncompleteDataException;
import in.ecgc.smile.erp.accounts.model.StampParameterModel;
import in.ecgc.smile.erp.accounts.repository.StampDao;

/**
 * 
 * Stamp Parameter
 * 
 * @author Amitesh Banerjee
 * @version 1.0 27-April-2020
 *
 */
@Service
public class StampServiceImpl implements StampService{

	private static final Logger LOGGER = LoggerFactory.getLogger(StampServiceImpl.class);

	@Autowired(required=true)
	StampDao stampDao;
	
	@Override
	public Integer addStampParameter(StampParameterModel stampParameter) throws StampIncompleteDataException {

		LOGGER.info("inside service in client side"+stampParameter);
		if(
				stampParameter.getSrNo()== null ||
				stampParameter.getFromAmount() == null ||
				stampParameter.getToAmount() == null ||
				stampParameter.getStampAmount() == null ||
				stampParameter.getDescription().isEmpty()
				)
		{
			LOGGER.info("inside error in service client");
			throw new StampIncompleteDataException("Incomplete Stamp Data!!!");
		}
		return stampDao.addStampParameter(stampParameter);
	}


	@Override
	public List<StampParameterModel> allStampParameter() {
		//List<StampParameterModel> stampParameter = stampDao.allStampParameter();
		//return stampParameter;
		return stampDao.allStampParameter();
	}

	@Override
	public StampParameterModel updateStampParameter(Integer stampCode,
		StampParameterModel stampParameterUpdate) {
		return stampDao.updateStampParameter(stampCode,stampParameterUpdate);
	}


	@Override
	public StampParameterModel viewByStampCode(Integer stampCode) throws StampIncompleteDataException {
		StampParameterModel viewCode = stampDao.viewByStampCode(stampCode);
		if(viewCode == null) {
			throw new StampIncompleteDataException("No Stamp parameters found with the given stamp code");
		}
		return viewCode;
	}


	@Override
	public Integer getStampAmtByFromAndToAmt(Integer receiptAmount) {
	
		return stampDao.getStampAmtByFromAndToAmt(receiptAmount);
	}

}
