package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.SubBifurcations;


public interface SubBifurcationMasterDao {

	List<SubBifurcations> getSubBifurcations() throws Exception;
	List<String> getSubBifurcationLevels() throws Exception;
	SubBifurcations getSubBifurcationsByLevel(String subBifurcationLevel) throws Exception;
	Integer addSubBifurcation(SubBifurcations subBifurcations);
	SubBifurcations updateSubBifurcationLevel(SubBifurcations currentSubBifurcations);

}
