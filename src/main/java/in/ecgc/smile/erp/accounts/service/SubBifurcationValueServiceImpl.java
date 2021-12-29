package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.accounts.exception.SubBifurcationValueNotFoundException;
import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;
import in.ecgc.smile.erp.accounts.repository.SubBifurcationValueDao;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class SubBifurcationValueServiceImpl implements SubBifurcationValueService{
	
	
	//private static final Logger log = LoggerFactory.getLogger(SubBifurcationValueServiceImpl.class);
	@Autowired
	SubBifurcationValueDao subBifurcationValueDao;
	
	@Override
	public List<SubBifurcationValue> getSubBifurcationsDtlList(){
			try {			
			return subBifurcationValueDao.getSubBifurcationsDtlList();
		} catch (Exception e) {			
				 log.error("ERROR: Service getSubBifurcationsDtlList() {}", e.getMessage());
				 throw new SubBifurcationValueNotFoundException();
				 
			}
	}
	
	@Override
	public SubBifurcationValue getSubBifurcationsDtlDataById(String bifurcationLevelCode, String bifurcationValueCode ){
		System.err.println(subBifurcationValueDao.getSubBifurcationsDtlDataById(bifurcationLevelCode,bifurcationValueCode));
	try {
		return subBifurcationValueDao.getSubBifurcationsDtlDataById(bifurcationLevelCode,bifurcationValueCode);

	}catch (Exception e) {
		throw new SubBifurcationValueNotFoundException();
	}
		//return subBifurcationValueDao.getSubBifurcationsDtlDataById(bifurcationLevelCode,bifurcationValueCode);
	}
	
	@Override
	public boolean addSubBifurcationsDtlData(SubBifurcationValue  subBifurcationValue){		
			return subBifurcationValueDao.addSubBifurcationsDtlData(subBifurcationValue);
	}
	
	@Override
	public boolean updateSubBifurcationsDtlData(String bifurcationLlevelCode , String bifurcationValueCode, SubBifurcationValue  subBifurcationValue){
//		int result =  subBifurcationValueDao.updateSubBifurcationsDtlData(bifurcationLlevelCode, bifurcationValueCode, subBifurcationValue);
//		if(result ==1) {
//			return true;
//		}else
//			return false;
		
		return  subBifurcationValueDao.updateSubBifurcationsDtlData(bifurcationLlevelCode, bifurcationValueCode, subBifurcationValue);

	}

	@Override
	public boolean disableSubBifurcationValue(String bifurcationLevelCode, String bifurcationValueCode) {
		return subBifurcationValueDao.disableSubBifurcationValue(bifurcationLevelCode, bifurcationValueCode);
	}

	@Override
	public String getBifurcationCode(String levelCode) {
		return subBifurcationValueDao.getBifurcationCode(levelCode);
	}

	@Override
	public List<SubBifurcationValue> getAllSubBifurcationValueCodeByLevelCode(String levelCode) {
		// TODO Auto-generated method stub
		return subBifurcationValueDao.getAllSubBifurcationValueCodeByLevelCode(levelCode);
	}
	
	@Override
	public List<SubBifurcationValue> findSubBifurcationValueList(String mainGLCode, String subGLCode) {
	
		return subBifurcationValueDao.findSubBifurcationValueList(mainGLCode, subGLCode);
	}
}
