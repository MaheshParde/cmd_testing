package in.ecgc.smile.erp.accounts.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openxmlformats.schemas.drawingml.x2006.main.STSystemColorVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import in.ecgc.smile.erp.accounts.exception.BankBranchNotFoundException;
import in.ecgc.smile.erp.accounts.exception.SubBifurcationLevelCodeNotFound;
import in.ecgc.smile.erp.accounts.exception.SubBifurcationValueNotFoundException;
import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;
import in.ecgc.smile.erp.accounts.service.SubBiFurcationMasterService;
import in.ecgc.smile.erp.accounts.util.BankBranchSqlQueries;
import in.ecgc.smile.erp.accounts.util.SubBifurcationValueQueries;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
@Repository
@Slf4j
public class SubBifurcationValueDaoImpl implements SubBifurcationValueDao
{
	@Autowired SubBiFurcationMasterService subMasterService; 
//	private static final Logger log = LoggerFactory.getLogger(SubBifurcationValueDaoImpl.class);
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public  SubBifurcationValueDaoImpl(DataSource dataSource)
	 {
	namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	public MapSqlParameterSource getSubBifurcationsDtlParamsMap(SubBifurcationValue subBifurcationsDtl){
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("bifurcationLevelCode" , subBifurcationsDtl.getBifurcationLevelCode());			
			paramSource.addValue("subBifurcationLevel" , subBifurcationsDtl.getSubBifurcationLevel());			
			paramSource.addValue("bifurcationValue" , subBifurcationsDtl.getBifurcationValue());			
			paramSource.addValue("bifurcationValueCode" ,subBifurcationsDtl.getBifurcationValueCode() );
			paramSource.addValue("createdBy" , subBifurcationsDtl.getCreatedBy());
			paramSource.addValue("createdDt" , subBifurcationsDtl.getCreatedDt());
			paramSource.addValue("lastUpdatedBy" , subBifurcationsDtl.getLastUpdatedBy());
			paramSource.addValue("lastUpdatedDt" , subBifurcationsDtl.getLastUpdatedDt());
			paramSource.addValue("metaStatus" , subBifurcationsDtl.getMetaStatus());
			paramSource.addValue("metaRemarks" , subBifurcationsDtl.getMetaRemarks());
			paramSource.addValue("active", subBifurcationsDtl.getActive());
			return paramSource;
	}
	@Override
	public List<SubBifurcationValue> getSubBifurcationsDtlList(){
		List<SubBifurcationValue> list = new ArrayList<SubBifurcationValue>();
	try {
		list = namedParameterJdbcTemplate.query(SubBifurcationValueQueries.GET_SubBifurcationsDtl_DATA,new RowMapper<SubBifurcationValue>()
		{
			@Override
			public SubBifurcationValue mapRow(ResultSet rs, int rowNum) throws SQLException {
				SubBifurcationValue subBifurcationValue = new SubBifurcationValue();
				subBifurcationValue.setBifurcationLevelCode(rs.getString("bifurcation_level_code"));
				subBifurcationValue.setSubBifurcationLevel(rs.getString("sub_bifurcation_level"));
				subBifurcationValue.setBifurcationValue(rs.getString("bifurcation_value"));
				subBifurcationValue.setBifurcationValueCode(rs.getString("bifurcation_value_code"));
				subBifurcationValue.setCreatedBy(rs.getString("created_by"));
				subBifurcationValue.setLastUpdatedBy(rs.getString("last_updated_by"));
				subBifurcationValue.setMetaStatus(rs.getString("meta_status"));
				subBifurcationValue.setMetaRemarks(rs.getString("meta_remarks"));	
				subBifurcationValue.setActive(rs.getString("active").charAt(0));
				return subBifurcationValue;
				}		
		});	}catch (Exception e) {
			list = null;
			throw new SubBifurcationValueNotFoundException();
			}	
		return list;		
	}	
	
	public SubBifurcationValue getSubBifurcationsDtlDataById(String bifurcationLevelCode, String bifurcationValueCode){
			log.info("inside SubBifurcationsDtlDaoImpl  -  getSubBifurcationsDtlDatById()");
			Map<String, Object>paramsMap= new HashMap<>();
			paramsMap.put("bifurcationLeveLCode", bifurcationLevelCode);
			paramsMap.put("bifurcationValueCode", bifurcationValueCode );
			SubBifurcationValue  subBifurcationsDtl;
			try {
				 String sql = SubBifurcationValueQueries.GET_SubBifurcationsDtl_DATA_BY_ID;
				  subBifurcationsDtl= namedParameterJdbcTemplate.query(sql, paramsMap,new SubBifurcationsDtlResultSetExtractor() );
				  if(subBifurcationsDtl !=null) {
				  return subBifurcationsDtl;}
				  else {
						log.error("exception in subBifurcationDao#getSubBifurcationsDtlDataById ");
					  throw new SubBifurcationValueNotFoundException();
				  }
			} catch (DataAccessException e) {
				//subBifurcationsDtl = null;
				log.error("exception in subBifurcationDao#getSubBifurcationsDtlDataById : {}", e.getMessage());
				throw new SubBifurcationValueNotFoundException();
			}
//			return subBifurcationsDtl;
	}	
	@Override
	public boolean addSubBifurcationsDtlData(SubBifurcationValue subBifurcationsDtl){
		log.info("inside SubBifurcationsDtlDaoImpl  -  addSubBifurcationsDtlData()");
		List<String> levelList=  new ArrayList<>();
		levelList = subMasterService.getSubBifurcationsLevel();
		String level=  subBifurcationsDtl.getBifurcationLevelCode() ;
		System.err.println( "provided level data is +++++++++++++++++++"+ level);
		if(levelList.contains(level) ) {
			System.err.println("Index of entered level "+levelList.contains(level) );
				try {
					String sql =SubBifurcationValueQueries.ADD_SubBifurcationsDtl_DATA;		
					if(namedParameterJdbcTemplate.update(sql,getSubBifurcationsDtlParamsMap(subBifurcationsDtl))>0){
						return true;
					}
					else{
//						return false;
						throw new SubBifurcationLevelCodeNotFound();
					}	
				} catch (DataAccessException e) {
					log.error("ERROR: DAO Module {}", e.getMessage());
					throw new SubBifurcationLevelCodeNotFound();
				}
		}else {
			throw new SubBifurcationLevelCodeNotFound();
		}		
	}
	
	@Override
	public Boolean updateSubBifurcationsDtlData(String bifurcationLlevelCode , String bifurcationValueCode, SubBifurcationValue  subBifurcationValue)
	{				int rowCount;
				Map<String, Object> param = new HashMap<String, Object>();
				//param.put("logicalLocCode", logicalLocCode);
				param.put("bifurcationLlevelCode", subBifurcationValue.getBifurcationLevelCode());
				param.put("subBifurcationLevel", subBifurcationValue.getSubBifurcationLevel());		
				param.put("bifurcationValue", subBifurcationValue.getBifurcationValue());
				param.put("bifurcationValueCode", subBifurcationValue.getBifurcationValueCode());
				try {		
					rowCount = namedParameterJdbcTemplate.update(SubBifurcationValueQueries.UPDATE_SubBifurcationsDtl_DATA,param);
				System.out.println("result of update is ::: " +rowCount );
				if (rowCount >0) {
					return true;
				}else
					return false;
				} catch (Exception e) {
					log.info("Exception occured while updating sub BifurcationDetails...",e);		
					throw new SubBifurcationLevelCodeNotFound("sub Bifurcation Level Code Not Found with the given detail",	
							new String[] {  bifurcationLlevelCode , bifurcationValueCode});
				}					
	}
	
	@Override
	public boolean disableSubBifurcationValue(String bifurcationLevelCode, String bifurcationValueCode) {
		int rowCount;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bifurcationLevelCode", bifurcationLevelCode);
		paramMap.put("bifurcationValueCode", bifurcationValueCode);
		SubBifurcationValue subBifurcationValue = new SubBifurcationValue();
		subBifurcationValue = getSubBifurcationsDtlDataById(bifurcationLevelCode, bifurcationValueCode);
		// String sql = SubBifurcationValueQueries.UPDATE_SubBifurcationsDtl_DATA;			 
		if(subBifurcationValue != null) {
				try {
					rowCount = namedParameterJdbcTemplate.update(SubBifurcationValueQueries.DELETE_VALUE,new MapSqlParameterSource(paramMap));
				
				if (rowCount>0) {
					return true;
				}else
					return false;
				}catch (Exception e) {
					throw new SubBifurcationValueNotFoundException();
				}
			}else {
				throw new SubBifurcationValueNotFoundException();
			}
		}
	@Override
	public String getBifurcationCode(String levelCode) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bifurcationLevelCode", levelCode);
		String bifurcationValueCode ="";
		try {			
			bifurcationValueCode= namedParameterJdbcTemplate.queryForObject(SubBifurcationValueQueries.GET_VALUE_CODE,
					paramMap,new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {	
					String valueCode= "";
					valueCode= rs.getString("maxvalueCode");
					System.err.println( "returned VAlue Code is " +valueCode);
					if (valueCode == null) {
						valueCode = levelCode +"001";
						return valueCode;
					}
					else {					
						String padded = String.format("%03d" , Integer.parseInt(valueCode.substring(3))+1);
						valueCode =  levelCode +padded; 
						return valueCode;
					}					
				}
			});
		} catch (EmptyResultDataAccessException e) {
			bifurcationValueCode = "";
			throw new SubBifurcationLevelCodeNotFound("Details not found with the given Level Code");
		}
		return bifurcationValueCode;	
		
	}
	@Override
	public List<SubBifurcationValue> getAllSubBifurcationValueCodeByLevelCode(String levelCode) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bifurcationLevelCode", levelCode);
		List<SubBifurcationValue> valueList = new ArrayList<>();
		
		valueList= namedParameterJdbcTemplate.query(SubBifurcationValueQueries.GET_SUB_BIFURCATOIN_VALUE_CODE_LEVEL_CODE,paramMap, new RowMapper() {
			@Override
			public SubBifurcationValue mapRow(ResultSet rs, int rowNum) throws SQLException {
				SubBifurcationValue subBifurcationValue = new SubBifurcationValue();
				subBifurcationValue.setBifurcationLevelCode(rs.getString("bifurcation_level_code"));
				subBifurcationValue.setSubBifurcationLevel(rs.getString("sub_bifurcation_level"));
				subBifurcationValue.setBifurcationValue(rs.getString("bifurcation_value"));
				subBifurcationValue.setBifurcationValueCode(rs.getString("bifurcation_value_code"));
				subBifurcationValue.setCreatedBy(rs.getString("created_by"));
				subBifurcationValue.setLastUpdatedBy(rs.getString("last_updated_by"));
				subBifurcationValue.setMetaStatus(rs.getString("meta_status"));
				subBifurcationValue.setMetaRemarks(rs.getString("meta_remarks"));	
				subBifurcationValue.setActive(rs.getString("active").charAt(0));
				return subBifurcationValue;
				}		
		});
		return valueList;
	}
	
	@Override
	public List<SubBifurcationValue> findSubBifurcationValueList(String mainGLCode, String subGLCode) {
	try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mainGl", mainGLCode);
			paramMap.put("subGl",subGLCode);
			List<SubBifurcationValue> valueList= new ArrayList<>();
			valueList =namedParameterJdbcTemplate.query(SubBifurcationValueQueries.GET_SUB_BIFURCATION_VALUE_LIST_BY_LEVEL_CODE,paramMap,new RowMapper<SubBifurcationValue>() {
			@Override
				public SubBifurcationValue mapRow(ResultSet rs, int rowNum) throws SQLException {
					SubBifurcationValue subBifurcationValue = new SubBifurcationValue();
					subBifurcationValue.setBifurcationLevelCode(rs.getString("bifurcation_level_code"));
					subBifurcationValue.setSubBifurcationLevel(rs.getString("sub_bifurcation_level"));
					subBifurcationValue.setBifurcationValue(rs.getString("bifurcation_value"));
					subBifurcationValue.setBifurcationValueCode(rs.getString("bifurcation_value_code"));
					subBifurcationValue.setCreatedBy(rs.getString("created_by"));
					subBifurcationValue.setLastUpdatedBy(rs.getString("last_updated_by"));
					subBifurcationValue.setMetaStatus(rs.getString("meta_status"));
					subBifurcationValue.setMetaRemarks(rs.getString("meta_remarks"));	
					subBifurcationValue.setActive(rs.getString("active").charAt(0));
					return subBifurcationValue;
					}		
			});
			System.err.println("inside dao "+ valueList);
			return valueList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
}




class SubBifurcationsDtlMapper implements RowMapper<SubBifurcationValue> {
	
	@Override
	public SubBifurcationValue mapRow(ResultSet rs, int rowNum) throws SQLException {
	SubBifurcationValue subBifurcationsDtl = new SubBifurcationValue();
		subBifurcationsDtl.setBifurcationLevelCode(rs.getString("bifurcation_level_code"));
		subBifurcationsDtl.setSubBifurcationLevel(rs.getString("sub_bifurcation_level"));
		subBifurcationsDtl.setBifurcationValue(rs.getString("bifurcation_value"));
		subBifurcationsDtl.setBifurcationValueCode(rs.getString("bifurcation_value_code"));
		subBifurcationsDtl.setCreatedBy(rs.getString("created_by"));
		//subBifurcationsDtl.setCreatedDt(rs.getDate("created_dt"));
		subBifurcationsDtl.setLastUpdatedBy(rs.getString("last_updated_by"));
	//	subBifurcationsDtl.setLastUpdatedDt(rs.getDate("last_updated_dt"));
		subBifurcationsDtl.setMetaStatus(rs.getString("meta_status"));
		subBifurcationsDtl.setMetaRemarks(rs.getString("meta_remarks"));
		subBifurcationsDtl.setActive(rs.getString("active").charAt(0));
		return subBifurcationsDtl;
	}
	}

class SubBifurcationsDtlResultSetExtractor implements ResultSetExtractor<SubBifurcationValue> {	
	
	@Override
	public SubBifurcationValue extractData(ResultSet rs) throws SQLException,DataAccessException {
	SubBifurcationValue subBifurcationValue = null;
	if (rs.next()) {
		subBifurcationValue = new SubBifurcationValue();
		subBifurcationValue.setBifurcationLevelCode(rs.getString("bifurcation_level_code"));
		subBifurcationValue.setSubBifurcationLevel(rs.getString("sub_bifurcation_level"));
		subBifurcationValue.setBifurcationValue(rs.getString("bifurcation_value"));
		subBifurcationValue.setBifurcationValueCode(rs.getString("bifurcation_value_code"));
		subBifurcationValue.setCreatedBy(rs.getString("created_by"));
		//subBifurcationValue.setCreatedDt(rs.getDate("created_dt"));
		subBifurcationValue.setLastUpdatedBy(rs.getString("last_updated_by"));
		//subBifurcationValue.setLastUpdatedDt(rs.getDate("last_updated_dt"));
		subBifurcationValue.setMetaStatus(rs.getString("meta_status"));
		subBifurcationValue.setMetaRemarks(rs.getString("meta_remarks"));
		subBifurcationValue.setActive(rs.getString("active").charAt(0));
		
	}return subBifurcationValue;
	}
	
	
	
}