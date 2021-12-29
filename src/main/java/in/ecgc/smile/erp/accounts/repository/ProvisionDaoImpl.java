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

import in.ecgc.smile.erp.accounts.model.ProvisionGLMapping;
import in.ecgc.smile.erp.accounts.util.ProvisionSqlQueries;
@Repository
@Transactional
public class ProvisionDaoImpl implements ProvisionDao{

	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public ProvisionDaoImpl(DataSource dataSource) {
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	 
	@Override
	public List<ProvisionGLMapping> getMAppingList() {
		List<ProvisionGLMapping> list = null;
		try {
			RowMapper<ProvisionGLMapping> rowMapper= ProvisionSqlQueries::mapRow;
			list =namedParameterJdbcTemplate.query(ProvisionSqlQueries.GET_MAPPING,rowMapper);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProvisionGLMapping> getMAppingListforModule(String moduleCd) {
		List<ProvisionGLMapping> list = null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("moduleCd",moduleCd);
		try {
			RowMapper<ProvisionGLMapping> rowMapper= ProvisionSqlQueries::mapRow;
			list =namedParameterJdbcTemplate.query(ProvisionSqlQueries.GET_ALL_MAPPING_FOR_MODULE,paramMap,rowMapper);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

	@Override
	public List<ProvisionGLMapping> getMAppingListforModule(String moduleCd, String mappingCd) {
		List<ProvisionGLMapping> list = null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("moduleCd",moduleCd);
		paramMap.put("mappingCd",mappingCd);
		try {
			RowMapper<ProvisionGLMapping> rowMapper=ProvisionSqlQueries::mapRow;
			list =namedParameterJdbcTemplate.query(ProvisionSqlQueries.GET_MAPPING_FOR_MODULE,paramMap,rowMapper);
			System.err.println("inside dao "+ list);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer addGlMapping(List<ProvisionGLMapping> glMaping) {
		Integer rowNum =0;
		try {
			for(ProvisionGLMapping glmp:glMaping) {
				Map<String,Object> paramMap = ProvisionSqlQueries.getParamMapForMapping(glmp);
				rowNum += namedParameterJdbcOperations.update(ProvisionSqlQueries.ADD_MAPPING_FOR_MODULE, paramMap);
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
