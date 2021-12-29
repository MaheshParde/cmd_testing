package in.ecgc.smile.erp.accounts.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import in.ecgc.smile.erp.accounts.model.AutoGLTxnRequestModel;

public interface AutoGLTxnQueries {

	public static final String ADD_REQUEST ="INSERT INTO accounts.ecgc_acct_auto_txn_req "
			+ "(req_id, module_cd, account_cd, base_amt, logical_loc_cd, txn_dt, fiscal_yr, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, ad1, ad2, ad3, ad4, "+
			" remarks, created_by, created_dt, last_updated_by, last_updated_dt, meta_status, meta_remarks, req_status, advance_amt, bill_amt) "
			+ " VALUES(ecgc_acct_int_seq_no_func('auto_gl_txn_req'),:moduleCd,:accountCd,:baseAmt,:logicalLogCd,:txnDt,"+
			"  :fiscalYr,:t1,:t2,:t3,:t4,:t5,:t6,:t7,:t8,:t9,:t10,:t11,:t12,:ad1,:ad2,:ad3,:ad4,:remark,:createdBy,now(),:updatedBy,now(),'V',:metaRemarks, 'P', :advanceAmt,:billAmt);";

	public static final String GET_ALL ="select * from accounts.ecgc_acct_auto_txn_req where req_status='P'"; 
	public static final String GET_REQ_BY_ID ="select * from accounts.ecgc_acct_auto_txn_req where req_id=:reqId "; 
	
	public static final String UPDATE_REQ_STATUS ="UPDATE accounts.ecgc_acct_auto_txn_req "
			+ " SET  req_status=:status WHERE req_id=:reqId ;";
	
	
public static AutoGLTxnRequestModel mapRow(ResultSet rs, int rowNum) throws SQLException  {
	AutoGLTxnRequestModel model = new AutoGLTxnRequestModel();
	try {
		model.setRequestNo(rs.getInt("req_id"));
		model.setModuleCd(rs.getString("module_cd"));
		model.setAccountCd(rs.getString("account_cd"));
		model.setBaseAmt(rs.getDouble("base_amt"));
		model.setAdvanceAmt(rs.getDouble("advance_amt"));
		model.setBillAmt(rs.getDouble("bill_amt"));
		model.setFiscalYr(rs.getString("fiscal_yr"));
		model.setLogicalLogCd(rs.getString("logical_loc_cd"));
		model.setReqStatus(rs.getString("req_status"));
		if(rs.getDate("txn_dt") != null)
			model.setTxnDt(rs.getDate("txn_dt").toLocalDate());
		model.setT1(rs.getString("t1"));
		model.setT2(rs.getString("t2"));
		model.setT3(rs.getString("t3"));
		model.setT4(rs.getString("t4"));
		model.setT5(rs.getString("t5"));
		model.setT6(rs.getString("t6"));
		model.setT7(rs.getString("t7"));
		model.setT8(rs.getString("t8"));
		model.setT9(rs.getString("t9"));
		model.setT10(rs.getString("t10"));
		model.setT11(rs.getString("t11"));
		model.setT12(rs.getString("t12"));
		if(rs.getDate("ad1") != null)
			model.setAd1(rs.getDate("ad1").toLocalDate());
		if(rs.getDate("ad2") != null)
			model.setAd2(rs.getDate("ad2").toLocalDate());
		if(rs.getDate("ad3") != null)
			model.setAd3(rs.getDate("ad3").toLocalDate());
		if(rs.getDate("ad4") != null)
			model.setAd4(rs.getDate("ad4").toLocalDate());
		model.setRemarks(rs.getString("remarks"));
		return model;
	}
	catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}

public static Map<String, Object> getParamMapForMapping(AutoGLTxnRequestModel model){
	Map<String, Object> paramMap = new HashMap<>();
	paramMap.put("status", 'V');
	paramMap.put("moduleCd",model.getModuleCd());
	paramMap.put("accountCd",model.getAccountCd());
	paramMap.put("baseAmt", model.getBaseAmt());
	paramMap.put("billAmt", model.getBillAmt());
	paramMap.put("advanceAmt", model.getAdvanceAmt());
	paramMap.put("logicalLogCd", model.getLogicalLogCd());
	paramMap.put("txnDt", model.getTxnDt());
	paramMap.put("fiscalYr", model.getFiscalYr());
	paramMap.put("t1", model.getT1());
	paramMap.put("t2", model.getT2());
	paramMap.put("t3", model.getT3());
	paramMap.put("t4", model.getT4());
	paramMap.put("t5", model.getT5());
	paramMap.put("t6", model.getT6());
	paramMap.put("t7", model.getT7());
	paramMap.put("t8", model.getT8());
	paramMap.put("t9", model.getT9());
	paramMap.put("t10", model.getT10());
	paramMap.put("t11", model.getT11());
	paramMap.put("t12", model.getT12());
	paramMap.put("ad1", model.getAd1());
	paramMap.put("ad2", model.getAd2());
	paramMap.put("ad3", model.getAd3());
	paramMap.put("ad4", model.getAd4());
	paramMap.put("remark",model.getRemarks());			
	paramMap.put("createdBy",model.getCreatedBy());			
	paramMap.put("updatedBy",model.getLastUpdatedBy());			
	paramMap.put("metaRemarks",model.getMetaRemarks());			
	
	return paramMap;
}

}