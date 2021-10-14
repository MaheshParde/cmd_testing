package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.SubBifurcations;


public interface SubBifurcationMasterDao {

	List<SubBifurcations> getSubBifurcations() throws Exception;
	List<String> getSubBifurcationLevels() throws Exception;
	List<SubBifurcations> getSubBifurcationsByLevel(String subBifurcationLevel) throws Exception;
	Integer addSubBifurcation(SubBifurcations subBifurcations);
	Integer updatedSubBifurcations(SubBifurcations subBifurcations);
}
