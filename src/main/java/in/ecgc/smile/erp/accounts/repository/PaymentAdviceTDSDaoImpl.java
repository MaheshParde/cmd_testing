package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.PaymentAdvice;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceGstTdsDtl;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceTcodes;
import in.ecgc.smile.erp.accounts.model.PaymentAdviceTdsDtl;
import in.ecgc.smile.erp.accounts.util.PaymentAdviceSqlQueries;
import in.ecgc.smile.erp.accounts.util.PaymentAdviceTDSSqlQueries;
import net.bytebuddy.asm.Advice.Return;

@Repository
@Transactional
public class PaymentAdviceTDSDaoImpl implements PaymentAdviceTDSDao{

	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentAdviceTDSDaoImpl.class);
	
	
	@Autowired
	public PaymentAdviceTDSDaoImpl(DataSource dataSource) {
		
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	
	@Override
	public  PaymentAdvice getApprovedPaymentAdviceDtl(String logicalLocCd,String sectionCd,Integer adviceNo) {

		PaymentAdvice paymentAdvice = null;
		
		try {
			Map<String, String> param = new HashMap<>();
			param.put("adviceNo", adviceNo.toString());
			param.put("sectionCd", sectionCd);
			param.put("logicalLocCd", logicalLocCd);
			paymentAdvice = namedParameterJdbcTemplate.queryForObject(PaymentAdviceTDSSqlQueries.GET_APPROVED_ADVICE_DETAILS,param,
					new RowMapper<PaymentAdvice>() {

						@Override
						public PaymentAdvice mapRow(ResultSet rs, int rowNum) throws SQLException {

							PaymentAdvice payAdvice = new PaymentAdvice();
							if (rs.getDate("advice_dt")==null) {
								payAdvice.setAdviceDate(null);
							} else {
								payAdvice.setAdviceDate(rs.getDate("advice_dt").toLocalDate());
							}
							payAdvice.setAdviceNo(rs.getInt("advice_no"));
							payAdvice.setCurrCd(rs.getString("curr_cd"));
							payAdvice.setAprvAmt(rs.getDouble("aprv_amt"));
							payAdvice.setPayToType(rs.getString("pay_to_type"));
							payAdvice.setPymtPartyCd(rs.getString("pymt_party_cd"));
							payAdvice.setSectionCd(rs.getString("section_cd"));

							return payAdvice;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			
			LOGGER.info(e.getMessage());
		}
		return paymentAdvice;
	}


	@Override
	public List<PaymentAdvice> getApprovedPaymentAdvices(String logicalLocCd,String sectionCd,LocalDate fromDt, LocalDate toDt) {
		
		List<PaymentAdvice> paymentAdvice = null;
		
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("sectionCd", sectionCd);
			param.put("logicalLocCd", logicalLocCd);
			param.put("fromDt", fromDt);
			param.put("toDt", toDt);
			paymentAdvice = namedParameterJdbcTemplate.query(PaymentAdviceTDSSqlQueries.GET_APPROVED_PAYMENT_ADVICES,param,
				new RowMapper<PaymentAdvice>() {

					@Override
					public PaymentAdvice mapRow(ResultSet rs, int rowNum) throws SQLException {

						PaymentAdvice payAdvice = new PaymentAdvice();
						payAdvice.setAdviceAmt(rs.getDouble("advice_amt"));
						payAdvice.setAdviceAmtOtherCurrINR(rs.getDouble("advice_amt_oth_curr_inr"));
						payAdvice.setAdviceDesc(rs.getString("advice_desc"));
						if (rs.getDate("advice_dt")==null) {
							payAdvice.setAdviceDate(null);
						} else {
							payAdvice.setAdviceDate(rs.getDate("advice_dt").toLocalDate());
						}
						payAdvice.setAdviceNo(rs.getInt("advice_no"));
						payAdvice.setAdviceStatus(rs.getString("advice_stat"));
						payAdvice.setApprBaseAmt(rs.getDouble("appr_base_amt"));
						payAdvice.setAprvAmt(rs.getDouble("aprv_amt"));
						payAdvice.setBranchCd(rs.getString("branch_cd"));
						payAdvice.setCessAmt(rs.getDouble("cess_amt"));
						payAdvice.setCessRate(rs.getDouble("cess_rate"));
						payAdvice.setCreatedBy(rs.getString("created_by"));
						if (rs.getDate("created_dt")==null) {
							payAdvice.setCreatedDate(null);
						} else {
							payAdvice.setCreatedDate(rs.getDate("created_dt").toLocalDate());
						}
						payAdvice.setCurrCd(rs.getString("curr_cd"));
						payAdvice.setCurrRate(rs.getInt("curr_rate"));
						payAdvice.setDecRemark(rs.getString("dec_rmk"));
						payAdvice.setDecBy(rs.getString("dec_by"));
						if (rs.getDate("dec_dt")==null) {
							payAdvice.setDecDt(null);
						} else {
							payAdvice.setDecDt(rs.getDate("dec_dt").toLocalDate());
						}
						payAdvice.setBillNo(rs.getString("bill_no"));
						payAdvice.setBillDesc(rs.getString("bill_desc"));
						if (rs.getDate("bill_dt")==null) {
							payAdvice.setBillDt(null);
						} else {
							payAdvice.setBillDt(rs.getDate("bill_dt").toLocalDate());
						}
						if (rs.getDate("bill_iwd_dt")==null) {
							payAdvice.setBillIwdDt(null);
						} else {
							payAdvice.setBillIwdDt(rs.getDate("bill_iwd_dt").toLocalDate());
						}
						payAdvice.setDelFlag(rs.getBoolean("del_flag"));
						payAdvice.setEntityCd(rs.getString("entity_cd"));
						payAdvice.setExpenseHead(rs.getString("expense_head"));
						payAdvice.setFiscalYear(rs.getString("fiscal_yr"));
						payAdvice.setLogicalLocCd(rs.getString("logical_loc_cd"));
						payAdvice.setMetaRemarks(rs.getString("meta_remarks"));
						payAdvice.setMetaStatus(rs.getString("meta_status"));
						payAdvice.setNoDeductionReason(rs.getString("no_deduction_reason"));
						payAdvice.setNoProvisionReason(rs.getString("no_provision_reason"));
						payAdvice.setOldCd(rs.getString("old_cd"));
						payAdvice.setOthAmt(rs.getDouble("oth_amt"));
						payAdvice.setPayToType(rs.getString("pay_to_type"));
						if(rs.getString("provision_flg")==null)
						{
							payAdvice.setProvisionFlag(' ');
						}
						else
							payAdvice.setProvisionFlag(rs.getString("provision_flg").charAt(0));
						payAdvice.setProvisionalAmt(rs.getDouble("provisional_amt"));
						payAdvice.setPymtAprvId(rs.getInt("pymt_aprv_id"));
						payAdvice.setPymtAprvName(rs.getString("pymt_aprv_name"));
						payAdvice.setPymtPartyCd(rs.getString("pymt_party_cd"));
						payAdvice.setPymtPartyName(rs.getString("pymt_party_name"));
						payAdvice.setPymtYear(rs.getString("pymt_year"));
						payAdvice.setSectionCd(rs.getString("section_cd"));
						payAdvice.setServiceTaxAmt(rs.getDouble("service_tax_amt"));
						payAdvice.setSurchargeRate(rs.getDouble("surcharge_rate"));
						payAdvice.setTaxDeducted(rs.getDouble("tax_deducted"));
						payAdvice.setTaxRate(rs.getDouble("tax_rate"));
						if(rs.getString("taxinfo_flg")==null)
						{
							payAdvice.setTaxInfoFlag(' ');
						}
						else
							payAdvice.setTaxInfoFlag(rs.getString("taxinfo_flg").charAt(0));
						
						if(rs.getString("tds_applicable")==null)
						{
							payAdvice.setTdsApplicable(' ');
						}
						else
							payAdvice.setTdsApplicable(rs.getString("tds_applicable").charAt(0));
						payAdvice.setUserName(rs.getString("user_name"));

						return payAdvice;
					}
				});
	} catch (EmptyResultDataAccessException e) {
		
		LOGGER.info(e.getMessage());
	}
	return paymentAdvice;
		
	}


	@Override
	public Integer updatePaymentAdviceRCAppliacble(PaymentAdvice paymentAdvice) {
		Map<String, Object> param;
		param = new HashMap<>();
		try {
			
			param.put("entityCd", paymentAdvice.getEntityCd());
			param.put("expenseHead", paymentAdvice.getExpenseHead());
			param.put("logicalLocCd", paymentAdvice.getLogicalLocCd());
			param.put("sectionCd", paymentAdvice.getSectionCd());
			param.put("adviceNo", paymentAdvice.getAdviceNo());
			param.put("tdsApplicable", paymentAdvice.getTdsApplicable());
			param.put("taxRate", paymentAdvice.getTaxRate());
			param.put("surchargeRate", paymentAdvice.getSurchargeRate());
			param.put("taxDeducted", paymentAdvice.getTaxDeducted());
			param.put("cessAmt", paymentAdvice.getCessAmt());
			param.put("cessRate", paymentAdvice.getCessRate());
			param.put("oldCd", paymentAdvice.getOldCd());
			param.put("adviceAmtOtherCurrINR", paymentAdvice.getAdviceAmtOtherCurrINR());
			param.put("currRate", paymentAdvice.getCurrRate());
			param.put("userName", paymentAdvice.getUserName());
			param.put("apprBaseAmt", paymentAdvice.getApprBaseAmt());
			param.put("serviceTaxAmt", paymentAdvice.getServiceTaxAmt());
			param.put("othAmt", paymentAdvice.getOthAmt());
			param.put("noDeductionReason", paymentAdvice.getNoDeductionReason());
			param.put("taxInfoFlag", paymentAdvice.getTaxInfoFlag());
		} catch (DuplicateKeyException e) {
			LOGGER.info(e.getMessage());
		}
		return namedParameterJdbcTemplate.update(PaymentAdviceTDSSqlQueries.UPDATE_PAYMENT_ADVICE_RC_APPLICABLE, param);

	}

	@Override
	public Integer updatePaymentAdviceTdsNOTAppliacble(PaymentAdvice paymentAdvice) {
		Map<String, Object> param;
		param = new HashMap<>();
		try {
			param.put("entityCd", paymentAdvice.getEntityCd());
			param.put("expenseHead", paymentAdvice.getExpenseHead());
			param.put("logicalLocCd", paymentAdvice.getLogicalLocCd());
			param.put("sectionCd", paymentAdvice.getSectionCd());
			param.put("adviceNo", paymentAdvice.getAdviceNo());
			param.put("tdsApplicable", paymentAdvice.getTdsApplicable());
			param.put("taxInfoFlag", paymentAdvice.getTaxInfoFlag());
			param.put("noDeductReason", paymentAdvice.getNoDeductionReason());
			param.put("taxRate", paymentAdvice.getTaxRate());
			param.put("taxDeducted", paymentAdvice.getTaxDeducted());
			param.put("serviceTaxAmt", paymentAdvice.getServiceTaxAmt());
			param.put("surchargeRate", paymentAdvice.getSurchargeRate());
			param.put("apprBaseAmt", paymentAdvice.getApprBaseAmt());
			param.put("otherAmt", paymentAdvice.getOthAmt());
			
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return namedParameterJdbcOperations.queryForObject(PaymentAdviceTDSSqlQueries.UPDATE_PAYMENT_ADVICE_TDS_NOT_APPLICABLE, param,
				new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		});

	}

	
	@Override
	public Integer addPaymentAdviceTDSDtl(PaymentAdviceTdsDtl payAdvTdsDtl) {

		Map<String, Object> param;
		param = new HashMap<>();
		
		try {
			param.put("entityCd",payAdvTdsDtl.getEntityCd());
			param.put("logicalLocCd",payAdvTdsDtl.getLogicalLocCd());
			param.put("sectionCd",payAdvTdsDtl.getSectionCd());
			param.put("adviceNo",payAdvTdsDtl.getAdviceNo());
			param.put("paymentNo",payAdvTdsDtl.getPaymentNo());
			param.put("payToType",payAdvTdsDtl.getPayToType());
			param.put("pymtPartyCd",payAdvTdsDtl.getPymtPartyCd());
			param.put("fiscalYr",payAdvTdsDtl.getFiscalYr());
			param.put("taxAmt",payAdvTdsDtl.getTaxAmt());
			param.put("surchargeAmt",payAdvTdsDtl.getSurchargeAmt());
			param.put("remittanceRefNo",payAdvTdsDtl.getRemittanceRefNo());
			param.put("status",payAdvTdsDtl.getStatus());
			param.put("tdsFromAdviceNo",payAdvTdsDtl.getTdsFromAdviceNo());
			param.put("tdsFromSecCd",payAdvTdsDtl.getTdsFromSecCd());
			param.put("createdBy",payAdvTdsDtl.getCreatedBy());
			param.put("cessAmt",payAdvTdsDtl.getCessAmt());
			param.put("oldCd",payAdvTdsDtl.getOldCd());
			param.put("tdsSection",payAdvTdsDtl.getTdsSection());
			param.put("tdsDesc",payAdvTdsDtl.getTdsDesc());
			param.put("tdsRate",payAdvTdsDtl.getTdsRate());
			param.put("othRateRemarks",payAdvTdsDtl.getOthRateRemarks());
			param.put("apprBaseAmt",payAdvTdsDtl.getApprovedBaseAmt());
			param.put("serviceTaxAmt",payAdvTdsDtl.getServiceTaxAmt());
			param.put("otherAmt",payAdvTdsDtl.getOtherAmt());
			param.put("revChargeApp",payAdvTdsDtl.getRevChargeApp());
			param.put("natureOfService",payAdvTdsDtl.getNatureOfService());
			param.put("rcServiceTaxAmt",payAdvTdsDtl.getRcServiceTaxAmt());
			param.put("rcPrimEduCessAmt",payAdvTdsDtl.getRcPrimEduCessAmt());
			param.put("rcSecEduCessAmt",payAdvTdsDtl.getRcSecEduCessAmt());
			param.put("tdsRemarks",payAdvTdsDtl.getTdsRemarks());
			param.put("rcServiceTaxRate",payAdvTdsDtl.getRcServiceTaxRate());
			param.put("rcServiceTaxPerc",payAdvTdsDtl.getRcServiceTaxPerc());
			param.put("rcSwachBharatCessAmt",payAdvTdsDtl.getRcSwachBharatCessAmt());
			param.put("rcKrishiKalyanCessAmt",payAdvTdsDtl.getRcKrishiKalyanCessAmt());
			param.put("gstType",payAdvTdsDtl.getGstType());
			param.put("cgstTax",payAdvTdsDtl.getCgstTax());
			param.put("sgstTax",payAdvTdsDtl.getSgstTax());
			param.put("igsttax",payAdvTdsDtl.getIgsttax());
			param.put("utgstTax",payAdvTdsDtl.getUtgstTax());
			param.put("cgstTaxAmt",payAdvTdsDtl.getCgstTaxAmt());
			param.put("sgstTaxAmt",payAdvTdsDtl.getSgstTaxAmt());
			param.put("igstTaxAmt",payAdvTdsDtl.getIgstTaxAmt());
			param.put("utgstTaxAmt",payAdvTdsDtl.getUtgstTaxAmt());
			param.put("gstSelState",payAdvTdsDtl.getGstSelState());
			param.put("invoiceNo",payAdvTdsDtl.getInvoiceNo());
			param.put("createdDt",payAdvTdsDtl.getCreatedDt());
			param.put("metaStatus","V");
			param.put("metaRemarks",payAdvTdsDtl.getMetaRemarks());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return namedParameterJdbcTemplate.update(PaymentAdviceTDSSqlQueries.INSERT_PAYMENT_ADVICE_TDS_DTL, param);
	}


	@Override
	public Integer addPaymentAdviceGSTTDSDtl(PaymentAdviceGstTdsDtl payAdvGstTdsDtl) {
		
		Map<String, Object> param;
		param = new HashMap<>();
		
		try {
			param.put("entityCd",payAdvGstTdsDtl.getEntityCd());
			param.put("logicalLocCd",payAdvGstTdsDtl.getLogicalLocCd());
			param.put("sectionCd",payAdvGstTdsDtl.getSectionCd());
			param.put("adviceNo",payAdvGstTdsDtl.getAdviceNo());
			param.put("paymentNo",payAdvGstTdsDtl.getPaymentNo());
			param.put("payToType",payAdvGstTdsDtl.getPayToType());
			param.put("pymtPartyCd",payAdvGstTdsDtl.getPymtPartyCd());
			param.put("fiscalYr",payAdvGstTdsDtl.getFiscalYr());
			param.put("status",payAdvGstTdsDtl.getStatus());
			param.put("createdBy",payAdvGstTdsDtl.getCreatedBy());
			param.put("approvedBaseAmt",payAdvGstTdsDtl.getApprovedBaseAmt());
			param.put("stateVendor",payAdvGstTdsDtl.getStateVendor());
			param.put("gstVen",payAdvGstTdsDtl.getGstVen());
			param.put("stateSupply",payAdvGstTdsDtl.getStateSupply());
			param.put("cgstTdsRate",payAdvGstTdsDtl.getCgstTdsRate());
			param.put("cgstTdsAmt",payAdvGstTdsDtl.getCgstTdsAmt());
			param.put("sgstTdsRate",payAdvGstTdsDtl.getSgstTdsRate());
			param.put("sgstTdsAmt",payAdvGstTdsDtl.getSgstTdsAmt());
			param.put("igstTdsRate",payAdvGstTdsDtl.getIgstTdsRate());
			param.put("igstTdsAmt",payAdvGstTdsDtl.getIgstTdsAmt());
			param.put("utgstTdsRate",payAdvGstTdsDtl.getUtgstTdsRate());
			param.put("utgstTdsAmt",payAdvGstTdsDtl.getUtgstTdsAmt());
			param.put("gstTdsApplicable",payAdvGstTdsDtl.getGstTdsApplicable());
			param.put("taxRate",payAdvGstTdsDtl.getTaxRate());
			param.put("gstTdsType",payAdvGstTdsDtl.getGstTdsType());
			param.put("createdDt",payAdvGstTdsDtl.getCreatedDt());
			param.put("metaStatus","V");
			param.put("metaRemarks",payAdvGstTdsDtl.getMetaRemarks());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return namedParameterJdbcTemplate.update(PaymentAdviceTDSSqlQueries.INSERT_PAYMENT_ADVICE_GST_TDS_DTL, param); 
	}


	@Override
	public PaymentAdviceTdsDtl getPaymentAdviceTDSDtl(String logicalLocCd, String sectionCd, Integer adviceNo) {
		
			PaymentAdviceTdsDtl payAdviceTdsDtl = null;
		
		
			try {
				Map<String, String> param = new HashMap<>();
				param.put("adviceNo", adviceNo.toString());
				param.put("sectionCd", sectionCd);
				param.put("logicalLocCd", logicalLocCd);
				payAdviceTdsDtl = namedParameterJdbcTemplate.queryForObject(PaymentAdviceTDSSqlQueries.GET_PAYMENT_ADVICE_TDS_DTL,param,
						new RowMapper<PaymentAdviceTdsDtl>() {

							@Override
							public PaymentAdviceTdsDtl mapRow(ResultSet rs, int rowNum) throws SQLException {

								PaymentAdviceTdsDtl payAdvTdsDtl = new PaymentAdviceTdsDtl();
								payAdvTdsDtl.setEntityCd(rs.getString("entity_cd"));
								payAdvTdsDtl.setLogicalLocCd(rs.getString("logicalloc_cd"));
								payAdvTdsDtl.setSectionCd(rs.getString("section_cd"));
								payAdvTdsDtl.setAdviceNo(rs.getInt("advice_no"));
								payAdvTdsDtl.setPaymentNo(rs.getInt("pymt_no"));
								payAdvTdsDtl.setPayToType(rs.getString("pay_to_type"));
								payAdvTdsDtl.setPymtPartyCd(rs.getString("pymt_party_cd"));
								payAdvTdsDtl.setFiscalYr(rs.getString("fiscal_yr"));
								payAdvTdsDtl.setTaxAmt(rs.getDouble("tax_amt"));
								payAdvTdsDtl.setSurchargeAmt(rs.getDouble("surcharge_amt"));
								payAdvTdsDtl.setRemittanceRefNo(rs.getInt("remittance_ref_no"));
								payAdvTdsDtl.setStatus(rs.getString("status"));
								payAdvTdsDtl.setTdsFromAdviceNo(rs.getInt("tds_from_advice_no"));
								payAdvTdsDtl.setTdsFromSecCd(rs.getString("tds_from_section_cd"));
								payAdvTdsDtl.setCreatedBy(rs.getString("created_by"));
								payAdvTdsDtl.setCessAmt(rs.getDouble("cess_amt"));
								payAdvTdsDtl.setOldCd(rs.getString("old_cd"));
								payAdvTdsDtl.setTdsSection(rs.getString("tds_section"));
								payAdvTdsDtl.setTdsDesc(rs.getString("tds_desc"));
								payAdvTdsDtl.setTdsRate(rs.getDouble("tds_rate"));
								payAdvTdsDtl.setOthRateRemarks(rs.getString("oth_rate_rmrks"));
								payAdvTdsDtl.setApprovedBaseAmt(rs.getDouble("appr_base_amt"));
								payAdvTdsDtl.setServiceTaxAmt(rs.getDouble("service_tax_amt"));
								payAdvTdsDtl.setOtherAmt(rs.getDouble("other_amt"));
								payAdvTdsDtl.setRevChargeApp(rs.getString("rev_chrg_app").charAt(0));
								payAdvTdsDtl.setNatureOfService(rs.getString("nature_of_service"));
								payAdvTdsDtl.setRcServiceTaxAmt(rs.getDouble("rc_service_tax_amt"));
								payAdvTdsDtl.setRcPrimEduCessAmt(rs.getDouble("rc_prim_educess_amt"));
								payAdvTdsDtl.setRcSecEduCessAmt(rs.getDouble("rc_sec_educess_amt"));
								payAdvTdsDtl.setTdsRemarks(rs.getString("tds_remarks"));
								payAdvTdsDtl.setRcServiceTaxRate(rs.getDouble("rc_service_tax_rate"));
								payAdvTdsDtl.setRcServiceTaxPerc(rs.getDouble("rc_service_tax_perc"));
								payAdvTdsDtl.setRcSwachBharatCessAmt(rs.getDouble("rc_swach_bha_cess_amt"));
								payAdvTdsDtl.setRcKrishiKalyanCessAmt(rs.getDouble("rc_krishi_kal_cess_amt"));
								payAdvTdsDtl.setGstType(rs.getString("gst_type"));
								payAdvTdsDtl.setCgstTax(rs.getDouble("cgst_tax"));
								payAdvTdsDtl.setSgstTax(rs.getDouble("sgst_tax"));
								payAdvTdsDtl.setIgsttax(rs.getDouble("igst_tax"));
								payAdvTdsDtl.setUtgstTax(rs.getDouble("utgst_tax"));
								payAdvTdsDtl.setCgstTaxAmt(rs.getDouble("cgst_tax_amt"));
								payAdvTdsDtl.setSgstTaxAmt(rs.getDouble("sgst_tax_amt"));
								payAdvTdsDtl.setIgstTaxAmt(rs.getDouble("igst_tax_amt"));
								payAdvTdsDtl.setUtgstTaxAmt(rs.getDouble("utgst_tax_amt"));
								payAdvTdsDtl.setGstSelState(rs.getString("gst_sel_state"));
								payAdvTdsDtl.setInvoiceNo(rs.getString("invoiceno"));
								payAdvTdsDtl.setCreatedDt(rs.getDate("created_dt").toLocalDate());
								payAdvTdsDtl.setMetaStatus(rs.getString("meta_status"));
								payAdvTdsDtl.setMetaRemarks(rs.getString("meta_remarks"));
								return payAdvTdsDtl;
							}
							
				});
						return payAdviceTdsDtl;
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			return null;
	}


	@Override
	public PaymentAdviceGstTdsDtl getPaymentAdviceGSTTDSDtl(String logicalLocCd, String sectionCd, Integer adviceNo) {

		PaymentAdviceGstTdsDtl payAdviceGstTdsDtl = null;
		
		
		Map<String, String> param = new HashMap<>();
		param.put("adviceNo", adviceNo.toString());
		param.put("sectionCd", sectionCd);
		param.put("logicalLocCd", logicalLocCd);
		
		try {
			payAdviceGstTdsDtl = namedParameterJdbcTemplate.queryForObject(PaymentAdviceTDSSqlQueries.GET_PAYMENT_ADVICE_TDS_DTL,param,
					new RowMapper<PaymentAdviceGstTdsDtl>() {

						@Override
						public PaymentAdviceGstTdsDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
							PaymentAdviceGstTdsDtl payAdvGstTdsDtl = new PaymentAdviceGstTdsDtl();
							
							payAdvGstTdsDtl.setEntityCd(rs.getString("entity_cd"));
							payAdvGstTdsDtl.setLogicalLocCd(rs.getString("logicalloc_cd"));
							payAdvGstTdsDtl.setSectionCd(rs.getString("section_cd"));
							payAdvGstTdsDtl.setAdviceNo(rs.getInt("advice_no"));
							payAdvGstTdsDtl.setPaymentNo(rs.getInt("pymt_no"));
							payAdvGstTdsDtl.setPayToType(rs.getString("pay_to_type"));
							payAdvGstTdsDtl.setPymtPartyCd(rs.getString("pymt_party_cd"));
							payAdvGstTdsDtl.setFiscalYr(rs.getString("fiscal_yr"));
							payAdvGstTdsDtl.setStatus(rs.getString("status"));
							payAdvGstTdsDtl.setCreatedBy(rs.getString("created_by"));
							payAdvGstTdsDtl.setApprovedBaseAmt(rs.getDouble("appr_base_amt"));
							payAdvGstTdsDtl.setStateVendor(rs.getString("state_vendor"));
							payAdvGstTdsDtl.setGstVen(rs.getString("gstven"));
							payAdvGstTdsDtl.setStateSupply(rs.getString("state_supply"));
							payAdvGstTdsDtl.setCgstTdsRate(rs.getDouble("cgst_perc"));
							payAdvGstTdsDtl.setCgstTdsAmt(rs.getDouble("cgst_tds_amount"));
							payAdvGstTdsDtl.setSgstTdsRate(rs.getDouble("sgst_perc"));
							payAdvGstTdsDtl.setSgstTdsAmt(rs.getDouble("sgst_tds_amount"));
							payAdvGstTdsDtl.setIgstTdsRate(rs.getDouble("igst_perc"));
							payAdvGstTdsDtl.setIgstTdsAmt(rs.getDouble("igst_tds_amount"));
							payAdvGstTdsDtl.setUtgstTdsRate(rs.getDouble("utgst_perc"));
							payAdvGstTdsDtl.setUtgstTdsAmt(rs.getDouble("utgst_tds_amount"));
							payAdvGstTdsDtl.setGstTdsApplicable(rs.getString("gst_tds_appl").charAt(0));
							payAdvGstTdsDtl.setTaxRate(rs.getDouble("rate_tax"));
							payAdvGstTdsDtl.setGstTdsType(rs.getString("gst_tds_type"));
							payAdvGstTdsDtl.setCreatedDt(rs.getDate("created_dt").toLocalDate());
							payAdvGstTdsDtl.setMetaStatus(rs.getString("meta_status"));
							payAdvGstTdsDtl.setMetaRemarks(rs.getString("meta_remarks"));
							return payAdvGstTdsDtl;
						}
					});
			return payAdviceGstTdsDtl;
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		return null;
	}


	
	
}
