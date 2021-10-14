package in.ecgc.smile.erp.accounts.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.exception.InvalidGLTxnDataException;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.LiabilityGLMapping;
import in.ecgc.smile.erp.accounts.util.GlTxnSqlQueries;
import in.ecgc.smile.erp.accounts.util.LiabilityQueries;

@Repository
@Transactional
public class LiablityDaoImpl implements LiabilityDao {

	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	@Autowired
	public LiablityDaoImpl(DataSource dataSource) {
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<LiabilityGLMapping> getMAppingList() {
		List<LiabilityGLMapping> list = null;
		try {
			RowMapper<LiabilityGLMapping> rowMapper= LiabilityQueries::mapRow;
			list =namedParameterJdbcTemplate.query(LiabilityQueries.GET_MAPPING,rowMapper);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<LiabilityGLMapping> getMAppingListforModule(String moduleCd, String mappingCd) {
		List<LiabilityGLMapping> list = null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("moduleCd",moduleCd);
		paramMap.put("mappingCd",mappingCd);
		try {
			RowMapper<LiabilityGLMapping> rowMapper= LiabilityQueries::mapRow;
			list =namedParameterJdbcTemplate.query(LiabilityQueries.GET_MAPPING_FOR_MODULE,paramMap,rowMapper);
			System.err.println("inside dao "+ list);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<LiabilityGLMapping> getMAppingListforModule(String moduleCd) {
		List<LiabilityGLMapping> list = null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("moduleCd",moduleCd);
		try {
			RowMapper<LiabilityGLMapping> rowMapper= LiabilityQueries::mapRow;
			list =namedParameterJdbcTemplate.query(LiabilityQueries.GET_ALL_MAPPING_FOR_MODULE,paramMap,rowMapper);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Integer addGlMapping(List<LiabilityGLMapping> glMaping) {
		Integer rowNum =0;
		try {
			for(LiabilityGLMapping glmp:glMaping) {
				Map<String,Object> paramMap = LiabilityQueries.getParamMapForMapping(glmp);
				rowNum += namedParameterJdbcOperations.update(LiabilityQueries.ADD_MAPPING_FOR_MODULE, paramMap);
				}
			if(rowNum >=2) {
			
			return rowNum;
		}
		else {
			throw new Exception("invalid data provided");
		}
			}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

}
