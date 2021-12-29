package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;
public interface SubBifurcationValueDao{
	public List<SubBifurcationValue> getSubBifurcationsDtlList();
	public SubBifurcationValue getSubBifurcationsDtlDataById(String bifurcationLevelCode, String bifurcationValueCode);
	public boolean addSubBifurcationsDtlData(SubBifurcationValue  subBifurcationsvalue);
	Boolean updateSubBifurcationsDtlData(String bifurcationLlevelCode, String bifurcationValueCode,
			SubBifurcationValue subBifurcationValue);
	boolean disableSubBifurcationValue(String bifurcationLevelCode, String bifurcationValueCode);

	String getBifurcationCode(String levelCode);
 public List<SubBifurcationValue> getAllSubBifurcationValueCodeByLevelCode(String levelCode);
	List<SubBifurcationValue> findSubBifurcationValueList(String mainGLCode, String subGLCode);


}
