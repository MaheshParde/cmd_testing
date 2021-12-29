package in.ecgc.smile.erp.accounts.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import in.ecgc.smile.erp.accounts.model.ProvisionGLMapping;

public interface ProvisionSqlQueries {

//	public static final String CREATE_PROVISOIN="INSERT INTO accounts.ecgc_acct_provision_mst "
//			+ "(module_cd, mapping_cd, base_amt, logical_log_cd, emp_cd, bulk_cd, vendor_cd,"
//			+ " nature_of_service, name_of_party, pan_no, gst_no, bill_no, service_from_date,"
//			+ " service_to_date, basic_amt_of_bill, total_amt, net_payable_liability, "
//			+ "tds_rate, tds_amt, txn_dt, fiscal_yr, is_amt_in_inr, curr_cd, curr_name, "
//			+ "exchange_rate, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, ad1, ad2, "
//			+ "ad3, ad4, remarks, user_id)"
//			+ " VALUES(':moduleCd', ':mappingCd', ':baseAmt', ':logicalLogCd', ':employeeCd',"
//			+ " ':bulkCd', ':vendorCd', ':natureOfService', ':nameOfParty', ':panNoOfParty',"
//			+ " ':GSTinBill', ':billNo', ':serviceFromDate', ':ServicetoDate', ':basicAmountOfBill',"
//			+ " ':totalAmount', ':netPayableLiability', ':tdsRate', ':tdsAmount', ':txnDt',"
//			+ " ':fiscalYr', ':isAmtInInr', ':currCode', ':currName', ':exchangeRate',"
//			+ " ':t1', ':t2', ':t3', ':t4', ':t5', ':t6', ':t7', ':t8', ':t9', ':t10',"
//			+ " ':t11', ':t12', ':ad1', ':ad2', ':ad3', ':ad4', ':remarks', ':userId');\r\n"
//			+ "";
	public static final String GET_MAPPING="SELECT * FROM accounts.ecgc_acct_modulewise_provision_gl_mapping;";
	public static final String GET_MAPPING_FOR_MODULE = "select * from accounts.ecgc_acct_modulewise_provision_gl_mapping "
			+ "where module_cd=:moduleCd and mapping_cd=:mappingCd;";
	
	public static final String GET_ALL_MAPPING_FOR_MODULE = "select * from accounts.ecgc_acct_modulewise_provision_gl_mapping "
			+ "where module_cd=:moduleCd;";

	public static final String ADD_MAPPING_FOR_MODULE = "INSERT INTO accounts.ecgc_acct_modulewise_provision_gl_mapping "
			+ "(module_cd, mapping_cd, sr_no, mapping_name, maingl_cd, subgl_cd, dr_cr_flag,"
			+ " amt_calc, remarks, txn_type, sub_biurcation, created_dt, created_by,"
			+ " last_updated_by, last_updated_dt, meta_remarks, meta_status)\r\n"
			+ "VALUES(:moduleCd, :mappingCd,:srNo,:mappingName,:mainGl,:subGl,"
			+ ":drcdFlag,:amtCalc,:remark,:txnType,:subBifurcation,"
			+ " 'now()',:createdBy,:updatedBy, 'now()',:metaRemarks,:status); ";
	
	public static ProvisionGLMapping mapRow(ResultSet rs, int rowNum) throws SQLException  {
		ProvisionGLMapping glMapping = new ProvisionGLMapping();
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
	
	public static Map<String, Object> getParamMapForMapping(ProvisionGLMapping provisionGLMapping){
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", 'V');
		paramMap.put("moduleCd",provisionGLMapping.getModuleCd());
		paramMap.put("mappingCd",provisionGLMapping.getMappingCd());
		paramMap.put("mappingName",provisionGLMapping.getMappingName());
		paramMap.put("mainGl",provisionGLMapping.getMainGL());
		paramMap.put("subGl",provisionGLMapping.getSubGL());			
		paramMap.put("srNo",provisionGLMapping.getSrNo());			
		paramMap.put("amtCalc",provisionGLMapping.getAmtCalc());			
		paramMap.put("drcdFlag",provisionGLMapping.getDrCrFlag());			
		paramMap.put("txnType",provisionGLMapping.getTxnType());			
		paramMap.put("subBifurcation",provisionGLMapping.getSubBifurcation());			
		paramMap.put("remark",provisionGLMapping.getRemarks());			
		paramMap.put("createdBy",provisionGLMapping.getCreatedBy());			
		paramMap.put("updatedBy",provisionGLMapping.getLastUpdatedBy());			
		paramMap.put("metaRemarks",provisionGLMapping.getMetaRemarks());			
		
		return paramMap;
}

	
}

