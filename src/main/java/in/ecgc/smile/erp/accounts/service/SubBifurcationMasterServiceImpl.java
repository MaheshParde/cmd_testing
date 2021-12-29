package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.exception.SubBifurcationLevelCodeNotFound;
import in.ecgc.smile.erp.accounts.exception.SubBifurcationValueNotFoundException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.SubBifurcations;
import in.ecgc.smile.erp.accounts.repository.SubBifurcationMasterDao;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubBifurcationMasterServiceImpl implements SubBiFurcationMasterService {

	@Autowired
	SubBifurcationMasterDao subBifurcationDao;
	
	@Override
	public List<SubBifurcations> getSubBifurcations() {
		log.info("Inside getSubBifurcations Method");		
		List<SubBifurcations> list = null;
		try {
			 list = subBifurcationDao.getSubBifurcations();
			 System.err.println(new ObjectMapper().writeValueAsString(list.get(0)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public Integer addSubBifurcation(SubBifurcations subBifurcations) {
		log.info("Inside addSubBifurcations Method");
		Integer result = subBifurcationDao.addSubBifurcation(subBifurcations);
		return result;
	}


	@Override
	public SubBifurcations updatedSubBifurcations(SubBifurcations currentSubBifurcations, SubBifurcations updatedSubBifurcations) {
			
			currentSubBifurcations.setSubBifurcationLevel(updatedSubBifurcations.getSubBifurcationLevel());
			currentSubBifurcations.setDescription(updatedSubBifurcations.getDescription());
			currentSubBifurcations.setLastUpdatedBy(updatedSubBifurcations.getLastUpdatedBy());
			
			
			
			log.info("after setting updated values to currentSubBifurcations = " + currentSubBifurcations);
			return subBifurcationDao.updateSubBifurcationLevel(currentSubBifurcations);
		}

	

	@Override
	public SubBifurcations getSubBifurcationsByLevel(String subBifurcationLevel) {
			log.info("Inside getSubBifurcationsByLevel Method");
		
		SubBifurcations list = null;
		try {
			 list = subBifurcationDao.getSubBifurcationsByLevel(subBifurcationLevel);
		} catch (Exception e) {
			throw new SubBifurcationLevelCodeNotFound();
		}
		return list;
	}

	@Override
	public List<String> getSubBifurcationsLevel() {
		log.info("Inside getSubBifurcations Method");
		
		List<String> list = null;
		try {
			 list = subBifurcationDao.getSubBifurcationLevels();
		} catch (Exception e) {
			throw new SubBifurcationLevelCodeNotFound();
		}
		return list;
	}

}
