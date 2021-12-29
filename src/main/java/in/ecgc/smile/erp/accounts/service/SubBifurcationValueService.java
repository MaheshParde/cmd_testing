package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;

public interface SubBifurcationValueService{
	
	public List<SubBifurcationValue> getSubBifurcationsDtlList();
	public boolean addSubBifurcationsDtlData(SubBifurcationValue  subBifurcationsDtl);
	public SubBifurcationValue getSubBifurcationsDtlDataById(String bifurcationLevelCode,String bifurcationValueCode );
	boolean updateSubBifurcationsDtlData(String bifurcationLlevelCode,String bifurcationValueCode,
			SubBifurcationValue subBifurcationValue);
	
	String getBifurcationCode(String levelCode);
	 List<SubBifurcationValue> getAllSubBifurcationValueCodeByLevelCode(String levelCode);
	 boolean disableSubBifurcationValue(String bifurcationLevelCode, String bifurcationValueCode);
		List<SubBifurcationValue> findSubBifurcationValueList(String mainGLCode, String subGLCode);


}

