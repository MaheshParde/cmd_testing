package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.model.SubBifurcations;
import in.ecgc.smile.erp.accounts.repository.SubBifurcationMasterDao;

@Service
public class SubBifurcationMasterServiceImpl implements SubBiFurcationMasterService {

	@Autowired
	SubBifurcationMasterDao subBifurcationDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubBifurcationMasterServiceImpl.class);
	@Override
	public List<SubBifurcations> getSubBifurcations() {
		LOGGER.info("Inside getSubBifurcations Method");
		
		List<SubBifurcations> list = null;
		try {
			 list = subBifurcationDao.getSubBifurcations();
			 System.err.println(new ObjectMapper().writeValueAsString(list.get(0)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Integer addSubBifurcation(SubBifurcations subBifurcations) {
		LOGGER.info("Inside addSubBifurcations Method");
		Integer result = subBifurcationDao.addSubBifurcation(subBifurcations);
		return result;
	}

	@Override
	public Integer updatedSubBifurcations(SubBifurcations subBifurcations) {
		LOGGER.info("Inside updateSubBifurcations Method");
		Integer result = subBifurcationDao.updatedSubBifurcations(subBifurcations);
		return result;
	}

	@Override
	public List<SubBifurcations> getSubBifurcationsByLevel(String subBifurcationLevel) {
LOGGER.info("Inside getSubBifurcationsByLevel Method");
		
		List<SubBifurcations> list = null;
		try {
			 list = subBifurcationDao.getSubBifurcationsByLevel(subBifurcationLevel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> getSubBifurcationsLevel() {
		LOGGER.info("Inside getSubBifurcations Method");
		
		List<String> list = null;
		try {
			 list = subBifurcationDao.getSubBifurcationLevels();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
