package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.exception.RecordNotFoundException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.model.LiabilityGLMapping;
import in.ecgc.smile.erp.accounts.util.LiabilityQueries;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		RowMapper<LiabilityGLMapping> rowMapper= LiabilityQueries::mapRow;
		try {
			list =namedParameterJdbcTemplate.query(LiabilityQueries.GET_MAPPING_FOR_MODULE,paramMap,rowMapper);
		}
		catch (Exception e) {
			log.error("Exception in getting Mapping list for Module Code : {} and Mapping code : {}",
					moduleCd, mappingCd);
			log.error("Exception in GlTxnDaoImpl#updateGlTxnNo",e.fillInStackTrace());
			throw new RecordNotFoundException("Exception in getting Mapping list for Module Code : "+moduleCd+" and Mapping code : "+mappingCd+" : " + e.getMessage());
		}
		if(list.isEmpty()) {
			log.error("Exception in getting Mapping list for Module Code : {} and Mapping code : {}",
					moduleCd, mappingCd);
			log.error("Exception in GlTxnDaoImpl#updateGlTxnNo");
			throw new RecordNotFoundException("Exception in getting Mapping list for Module Code : "+moduleCd+" and Mapping code : "+mappingCd);

		}
		System.err.println("inside dao "+ list);
		return list;
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
	public List<String> distinctModuleCd() {
	
			List<String> list;
			list =namedParameterJdbcTemplate.query(LiabilityQueries.GET_DISTINCT_MODULE_CD,new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					return rs.getString("module_cd");
				}
			});
			return list;
			
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

	@Override
	public Map<String,String> getAllMappingCodeforModuleCd(String moduleCd) {

		Map<String,String> mappingCdList = new HashedMap<>();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("moduleCd", moduleCd);

//		List<String> list = new ArrayList<>();
		Map<String,String> query = namedParameterJdbcOperations.query(LiabilityQueries.GET_MAPPING_CD_FOR_MODULE_CD, paramMap, 
				(ResultSet rs ) -> {
					
					HashMap<String, String> map = new HashMap<>();
					
					  while(rs.next()){
						  map.put(rs.getString("mapping_cd"),rs.getString("mapping_name"));
				        }
					return map;
				});
		mappingCdList = query;
		
				
		return mappingCdList;
	}

	@Override
	public Map<String,String> getAllMappingCodeAndMappingNameforModuleCd(String moduleCd) {

		Map<String,String> mappingCdList = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("moduleCd", moduleCd);
		
		Map<String,String> query = namedParameterJdbcOperations.query(LiabilityQueries.GET_MAPPING_CD_NAME_FOR_MODULE_CD, paramMap, 
				(ResultSet rs ) -> {
					
					HashMap<String, String> map = new HashMap<>();
					
					  while(rs.next()){
						  map.put(rs.getString("mapping_cd"),rs.getString("mapping_name"));
				        }
					return map;
				});
		mappingCdList = query;
		
				
		return mappingCdList;
	}

	@Override
	public List<EntityGL> getRequiredTCodes(String moduleCd, String mappingCd) {
		
		List<EntityGL> entityGlList = new ArrayList<>();
		
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			
			paramMap.put("moduleCd",moduleCd);
			paramMap.put("mappingCd", mappingCd);
			
			entityGlList = namedParameterJdbcTemplate.query(LiabilityQueries.GET_APPLICABLE_TCODES, paramMap, new RowMapper<EntityGL>() {

				@Override
				public EntityGL mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					EntityGL entityGl = new EntityGL();
					
					entityGl.setMainglCd(rs.getString("maingl_cd"));
					entityGl.setSubglCd(rs.getString("subgl_cd"));
					entityGl.setT1(rs.getString("t1").charAt(0));
					entityGl.setT2(rs.getString("t2").charAt(0));
					entityGl.setT3(rs.getString("t3").charAt(0));
					entityGl.setT4(rs.getString("t4").charAt(0));
					entityGl.setT5(rs.getString("t5").charAt(0));
					entityGl.setT6(rs.getString("t6").charAt(0));
					entityGl.setT7(rs.getString("t7").charAt(0));
					entityGl.setT8(rs.getString("t8").charAt(0));
					entityGl.setT9(rs.getString("t9").charAt(0));
					entityGl.setT10(rs.getString("t10").charAt(0));
					entityGl.setT11(rs.getString("t11").charAt(0));
					entityGl.setT12(rs.getString("t12").charAt(0));
					
					
					return entityGl;
				}
				
			});
		} catch (DataAccessException e) {
			throw new RecordNotFoundException("inside LiablityDaoImpl#getRequiredTCodes, record not found for Module code : "+moduleCd+" and Mapping code : "+mappingCd+" !");
		}
		
		return entityGlList;
	}
	
}
