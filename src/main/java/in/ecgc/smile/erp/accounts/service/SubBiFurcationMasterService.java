package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.SubBifurcations;

public interface SubBiFurcationMasterService {
	List<SubBifurcations> getSubBifurcations() ;
	List<String> getSubBifurcationsLevel() ;
	SubBifurcations getSubBifurcationsByLevel(String subBifurcationLevel) ;
	Integer addSubBifurcation(SubBifurcations subBifurcations);
	SubBifurcations updatedSubBifurcations(SubBifurcations currentSubBifurcations,
			SubBifurcations updatedSubBifurcations);
	}
