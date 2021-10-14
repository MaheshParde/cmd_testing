package in.ecgc.smile.erp.accounts.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import in.ecgc.smile.erp.accounts.model.LiabilityGLMapping;


public interface LiabilityQueries {

	public static final String GET_MAPPING = "SELECT * FROM accounts.ecgc_acct_modulewise_liability_gl_mapping;";
	public static final String GET_MAPPING_FOR_MODULE = "select * from accounts.ecgc_acct_modulewise_liability_gl_mapping where module_cd=:moduleCd and mapping_cd=:mappingCd;";
	public static final String GET_ALL_MAPPING_FOR_MODULE = "select * from accounts.ecgc_acct_modulewise_liability_gl_mapping where module_cd=:moduleCd;";
	public static final String ADD_MAPPING_FOR_MODULE = "INSERT INTO accounts.ecgc_acct_modulewise_liability_gl_mapping "
			+ "(module_cd, mapping_cd, sr_no, mapping_name, maingl_cd, subgl_cd, dr_cr_flag, amt_calc, remarks, txn_type, sub_biurcation, created_dt, created_by, last_updated_by, last_updated_dt, meta_remarks, meta_status)\r\n"
			+ "VALUES(:moduleCd, :mappingCd,:srNo,:mappingName,:mainGl,:subGl,:drcdFlag,:amtCalc,:remark,:txnType,:subBifurcation,"
			+ " 'now()',:createdBy,:updatedBy, 'now()',:metaRemarks,:status); ";
	
	

	public static LiabilityGLMapping mapRow(ResultSet rs, int rowNum) throws SQLException  {
		LiabilityGLMapping glMapping = new LiabilityGLMapping();
		try {
			glMapping.setModuleCd(rs.getString("module_cd"));
			glMapping.setMappingCd(rs.getString("mapping_cd"));
			glMapping.setMappingCd(rs.getString("mapping_name"));
			glMapping.setSrNo(rs.getInt("sr_no"));
			glMapping.setAmtCalc(rs.getFloat("amt_calc"));
			glMapping.setMainGL(rs.getString("maingl_cd"));
			glMapping.setSubGL(rs.getString("subgl_cd"));
			glMapping.setTxnType(rs.getString("txn_type"));
			glMapping.setDrCrFlag(rs.getString("dr_cr_flag"));
			glMapping.setRemarks(rs.getString("remarks"));
			return glMapping;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
		public static Map<String, Object> getParamMapForMapping(LiabilityGLMapping liabilityMapping){
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("status", 'V');
			paramMap.put("moduleCd",liabilityMapping.getModuleCd());
			paramMap.put("mappingCd",liabilityMapping.getMappingCd());
			paramMap.put("mappingName",liabilityMapping.getMappingName());
			paramMap.put("mainGl",liabilityMapping.getMainGL());
			paramMap.put("subGl",liabilityMapping.getSubGL());			
			paramMap.put("srNo",liabilityMapping.getSrNo());			
			paramMap.put("amtCalc",liabilityMapping.getAmtCalc());			
			paramMap.put("drcdFlag",liabilityMapping.getDrCrFlag());			
			paramMap.put("txnType",liabilityMapping.getTxnType());			
			paramMap.put("subBifurcation",liabilityMapping.getSubBifurcation());			
			paramMap.put("remark",liabilityMapping.getRemarks());			
			paramMap.put("createdBy",liabilityMapping.getCreatedBy());			
			paramMap.put("updatedBy",liabilityMapping.getLastUpdatedBy());			
			paramMap.put("metaRemarks",liabilityMapping.getMetaRemarks());			
			
			return paramMap;
	}
	
	
}
