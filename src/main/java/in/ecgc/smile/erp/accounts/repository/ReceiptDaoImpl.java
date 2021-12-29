package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.NamedParameterJdbcOperationsDependsOnPostProcessor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.exception.ReceiptNotFoundException;
import in.ecgc.smile.erp.accounts.model.Receipt;
import in.ecgc.smile.erp.accounts.model.States;
import in.ecgc.smile.erp.accounts.util.ReceiptSqlQueries;

@Repository
@Transactional
public class ReceiptDaoImpl implements ReceiptDao {
	
	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptDaoImpl.class);

	public ReceiptDaoImpl(DataSource dataSource)  {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public Integer addReceipt(Receipt receipt){
		int rowNum;
		Map<String, Object> receiptNamedParameter =new HashMap<String , Object>();
		try{
			receiptNamedParameter.put("logicalLocCode" ,receipt.getLogicalLocCode());
			receiptNamedParameter.put("receiptNumber", receipt.getReceiptNumber());
			receiptNamedParameter.put("receiptDate", receipt.getReceiptDate());
			receiptNamedParameter.put("iwdNumber", receipt.getIwdNumber());
			receiptNamedParameter.put("remarks",receipt.getRemarks() );
			receiptNamedParameter.put("receivedFromCode", receipt.getReceivedFromCode());
			receiptNamedParameter.put("receivedFromName", receipt.getReceivedFromName());
			receiptNamedParameter.put("receivedFromAddress", receipt.getReceivedFromAddress());
			receiptNamedParameter.put("fiscalYear", receipt.getFiscalYear());
			receiptNamedParameter.put("oldReceiptNumber", receipt.getOldReceiptNumber());
			receiptNamedParameter.put("glTxnNumberOld",receipt.getGlTxnNumberOld());
			receiptNamedParameter.put("createdBy" ,receipt.getCreatedBy());
			receiptNamedParameter.put("createdDate" , receipt.getCreatedDate());
			receiptNamedParameter.put("lastUpdatedBy" , receipt.getLastUpdatedBy());
			receiptNamedParameter.put("lastUpdatedDate", receipt.getLastUpdatedDate());
			receiptNamedParameter.put("metaStatus", receipt.getMetaStatus());
			receiptNamedParameter.put("metaRemarks", receipt.getMetaRemarks());
			receiptNamedParameter.put("glFlag", receipt.getGlFlag());
			receiptNamedParameter.put("stampAmount", receipt.getStampAmount());
			receiptNamedParameter.put("instrumentType", receipt.getInstrumentType());
			receiptNamedParameter.put("instrumentNumber", receipt.getInstrumentNumber());
			receiptNamedParameter.put("instrumentDate" ,(receipt.getInstrumentDate()));
			receiptNamedParameter.put("drawnOn", receipt.getDrawnOn());
			receiptNamedParameter.put("glTxnType", receipt.getGlTxnType());
			receiptNamedParameter.put("glTxnNumber", receipt.getGlTxnNumber());
			receiptNamedParameter.put("receivedfromType" , receipt.getReceivedfromType());
			receiptNamedParameter.put("receiptAmount", receipt.getReceiptAmount());
			receiptNamedParameter.put("payInSlip", receipt.getPayInSlip());
			receiptNamedParameter.put("entityCd", receipt.getEntityCd());
			receiptNamedParameter.put("productDescription", receipt.getProductDescription());
			receiptNamedParameter.put("hsn", receipt.getHsn());
			receiptNamedParameter.put("uom", receipt.getUom());
			receiptNamedParameter.put("qty", receipt.getQty());
			receiptNamedParameter.put("rate", receipt.getRate());
			receiptNamedParameter.put("amount", receipt.getAmount());
			receiptNamedParameter.put("discount", receipt.getDiscount());
			receiptNamedParameter.put("taxableDiscount", receipt.getTaxableDiscount());
			receiptNamedParameter.put("customerGstNo", receipt.getCustomerGstNo());
			receiptNamedParameter.put("ecgcGstNo", receipt.getEcgcGstNo());
			receiptNamedParameter.put("invoiceNo", receipt.getInvoiceNo());
			receiptNamedParameter.put("igstAmount", receipt.getIgstAmount());
			receiptNamedParameter.put("cgstAmount", receipt.getCgstAmount());
			receiptNamedParameter.put("sgstAmount", receipt.getSgstAmount());
			receiptNamedParameter.put("utgstAmount", receipt.getUtgstAmount());
			receiptNamedParameter.put("cgstTaxPer", receipt.getCgstTaxPer());
			receiptNamedParameter.put("sgstTaxPer", receipt.getSgstTaxPer());
			receiptNamedParameter.put("igstTaxPer", receipt.getIgstTaxPer());
			receiptNamedParameter.put("utgstTaxPer", receipt.getUtgstTaxPer());
			receiptNamedParameter.put("expoState", receipt.getExpoState());
			receiptNamedParameter.put("branchState", receipt.getBranchState());
			receiptNamedParameter.put("taxType", receipt.getTaxType());
			receiptNamedParameter.put("oldInvoiceNo", receipt.getOldInvoiceNo());
			receiptNamedParameter.put("updateFlag", receipt.getUpdateFlag());
			receiptNamedParameter.put("sez", receipt.getSez());
			
			rowNum=namedParameterJdbcTemplate.update(ReceiptSqlQueries.ADD_RECEIPT, receiptNamedParameter);
			if(rowNum !=0) {
				namedParameterJdbcOperations.update(ReceiptSqlQueries.UPDATE_SEQ_TBL, receiptNamedParameter);				
				if(receipt.getSgstAmount()!= 0 || receipt.getIgstAmount()!=0 || receipt.getCgstAmount()!=0 || receipt.getUtgstAmount()!=0)
				{
					namedParameterJdbcOperations.update(ReceiptSqlQueries.UPDATE_TAX_INVOICE_TBL, receiptNamedParameter);
				//	System.out.println("tax invoice table update");
				}
				else {
					namedParameterJdbcOperations.update(ReceiptSqlQueries.UPDATE_BS_INVOICE_TBL, receiptNamedParameter);
					//System.out.println("bill of supply table update");
				}
				
				System.out.println(receipt.getInvoiceNo());
				return 1;
				}
		}
		
		catch(DataAccessException e) {
			LOGGER.info("Exception occured while inserting new Receipt Data...",e);
			return -1; 
			}
		return 0;
	}
	
	
	@Override
	public 	List<Receipt> listAllReceipts() throws DataAccessException
	{
		List<Receipt> receipts = new ArrayList<Receipt>();
		receipts = namedParameterJdbcTemplate.query(ReceiptSqlQueries.VIEW_ALL_RECEIPTS, new RowMapper<Receipt>() {
			@Override
			public Receipt mapRow(ResultSet rs, int rowNum) throws SQLException{
				Receipt receipt = new Receipt();
				receipt.setLogicalLocCode(rs.getString("logical_loc_cd"));
				receipt.setGlFlag(rs.getString("gl_flg"));
				receipt.setMetaRemarks(rs.getString("meta_remarks"));
				receipt.setCreatedBy( rs.getString("created_by"));
				if(rs.getDate("created_dt") !=null)
				receipt.setCreatedDate(rs.getDate("created_dt").toLocalDate());
				receipt.setDrawnOn(rs.getString("drawn_on"));
				receipt.setMetaStatus(rs.getString("meta_status"));
				receipt.setFiscalYear(rs.getString("fiscal_yr"));
				receipt.setGlTxnNumber(rs.getInt("gl_txn_no"));
				receipt.setGlTxnNumberOld(rs.getInt("gl_txn_no_old"));
				receipt.setGlTxnType(rs.getString("gl_txn_type"));
				if(rs.getDate("instrument_dt") !=null)
				receipt.setInstrumentDate(rs.getDate("instrument_dt").toLocalDate());
				receipt.setInstrumentNumber(rs.getString("instrument_no"));
				receipt.setInstrumentType(rs.getString("instrument_type"));
				receipt.setLastUpdatedBy(rs.getString("last_updated_by"));
				receipt.setStampAmount(rs.getDouble("stamp_amt"));
				receipt.setRemarks(rs.getString("remarks"));
				receipt.setReceivedfromType(rs.getString("rcvd_from_type"));
				receipt.setReceivedFromName(rs.getString("rcvd_from_name"));
				receipt.setReceivedFromCode(rs.getString("rcvd_from_cd"));
				receipt.setReceivedFromAddress(rs.getString("rcvd_from_addr"));
				receipt.setReceiptNumber(rs.getInt("rcpt_no"));
				if(rs.getDate("rcpt_dt") !=null)
					receipt.setReceiptDate(rs.getDate("rcpt_dt").toLocalDate());
				receipt.setReceiptAmount(rs.getDouble("rcpt_amt"));
				receipt.setIwdNumber(rs.getString("iwd_no"));
				receipt.setPayInSlip(rs.getString("pay_in_slip"));
				receipt.setOldReceiptNumber(rs.getInt("old_rcpt_no"));
				receipt.setEntityCd(rs.getString("entity_cd"));
				receipt.setProductDescription(rs.getString("product_description"));
				receipt.setHsn(rs.getDouble("hsn"));
				receipt.setUom(rs.getDouble("uom"));
				receipt.setQty(rs.getDouble("qty"));
				receipt.setRate(rs.getDouble("rate"));
				receipt.setAmount(rs.getDouble("amount"));
				receipt.setDiscount(rs.getDouble("discount"));
				receipt.setTaxableDiscount(rs.getDouble("taxable_discount"));
				receipt.setCustomerGstNo(rs.getString("customer_gst_no"));
				receipt.setEcgcGstNo(rs.getString("ecgc_gst_no"));
				receipt.setInvoiceNo(rs.getString("invoice_no"));
				receipt.setCgstAmount(rs.getDouble("cgst_amount"));
				receipt.setSgstAmount(rs.getDouble("sgst_amount"));
				receipt.setIgstAmount(rs.getDouble("igst_amount"));
				receipt.setUtgstAmount(rs.getDouble("utgst_amount"));
				receipt.setCgstTaxPer(rs.getDouble("cgst_tax_per"));
				receipt.setSgstTaxPer(rs.getDouble("sgst_tax_per"));
				receipt.setIgstTaxPer(rs.getDouble("igst_tax_per"));
				receipt.setUtgstTaxPer(rs.getDouble("utgst_tax_per"));
				receipt.setExpoState(rs.getString("expo_state"));
				receipt.setBranchState(rs.getString("branch_state"));
				receipt.setTaxType(rs.getString("tax_type"));
				receipt.setOldInvoiceNo(rs.getString("old_invoice_no"));
				receipt.setUpdateFlag(rs.getString("update_flg"));
				receipt.setSez(rs.getString("sez"));
				
				return receipt;				
			}
		});
		
		
		return receipts;
	}	


	@Override
	public Integer getReceiptNo(String logicalLocCode, String fiscalYr) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("logicalLocCode",logicalLocCode);
		param.put("fiscalYear",fiscalYr);
		Integer seq = namedParameterJdbcOperations.queryForObject(ReceiptSqlQueries.GET_SEQ,param,
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						int num =rs.getInt(1);
						return num;
					}
				});
		return seq;
	}
	
	@Override
	public String getTaxInvoiceNo(String logicalLocCode, String fiscalYr) {
		Map<String, Object>param =  new HashMap<String, Object>()		;
		param.put("logicalLocCode", logicalLocCode);
		param.put("fiscalYear", fiscalYr);
		
		String invoiceNo= namedParameterJdbcOperations.queryForObject(ReceiptSqlQueries.GET_TAX_INVOICE_NO ,param,
				new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException{
				String num= rs.getString(1);				
				return num;
			}
		});
		System.out.println("invoice number from dao getINvoiceNO method "+invoiceNo);
		 return invoiceNo;		
	}
	
	@Override
	public String getBSInvoiceNo(String logicalLocCode, String fiscalYr) {
		Map<String, Object>param =  new HashMap<String, Object>()		;
		param.put("logicalLocCode", logicalLocCode);
		param.put("fiscalYear", fiscalYr);
		
		String invoiceNo= namedParameterJdbcOperations.queryForObject(ReceiptSqlQueries.GET_BS_INVOICE_NO ,param,
				new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException{
				String num= rs.getString(1);				
				return num;
			}
		});
		System.out.println("invoice number from dao getINvoiceNO method "+invoiceNo);
		 return invoiceNo;		
	}
	
	public List<Receipt> viewByLogicalLocationCode(String logicalLocCode) {
		
		List<Receipt> receiptList = new ArrayList<Receipt>();
		Map<String, Object> paraMmap = new HashMap<>();
		paraMmap.put("logicalLocCode", logicalLocCode);
		try {
			receiptList= namedParameterJdbcTemplate.query(ReceiptSqlQueries.VIEW_BY_LOGICAL_LOC_CODE, paraMmap, new RowMapper<Receipt>() {
				@Override
				public Receipt mapRow(ResultSet rs, int rowNum) throws SQLException{
					Receipt receipt = new Receipt();
					receipt.setLogicalLocCode(rs.getString("logical_loc_cd"));
					receipt.setGlFlag(rs.getString("gl_flg"));
					receipt.setMetaRemarks(rs.getString("meta_remarks"));
					receipt.setCreatedBy( rs.getString("created_by"));
					if(rs.getDate("created_dt") !=null)
					receipt.setCreatedDate(rs.getDate("created_dt").toLocalDate());
					receipt.setDrawnOn(rs.getString("drawn_on"));
					receipt.setMetaStatus(rs.getString("meta_status"));
					receipt.setFiscalYear(rs.getString("fiscal_yr"));
					receipt.setGlTxnNumber(rs.getInt("gl_txn_no"));
					receipt.setGlTxnNumberOld(rs.getInt("gl_txn_no_old"));
					receipt.setGlTxnType(rs.getString("gl_txn_type"));
					if(rs.getDate("instrument_dt") !=null)
					receipt.setInstrumentDate(rs.getDate("instrument_dt").toLocalDate());
					receipt.setInstrumentNumber(rs.getString("instrument_no"));
					receipt.setInstrumentType(rs.getString("instrument_type"));
					receipt.setLastUpdatedBy(rs.getString("last_updated_by"));
					receipt.setStampAmount(rs.getDouble("stamp_amt"));
					receipt.setRemarks(rs.getString("remarks"));
					receipt.setReceivedfromType(rs.getString("rcvd_from_type"));
					receipt.setReceivedFromName(rs.getString("rcvd_from_name"));
					receipt.setReceivedFromCode(rs.getString("rcvd_from_cd"));
					receipt.setReceivedFromAddress(rs.getString("rcvd_from_addr"));
					receipt.setReceiptNumber(rs.getInt("rcpt_no"));
					//receipt.setReceiptId(rs.getInt("rcpt_id"));
					if(rs.getDate("rcpt_dt") !=null)
					receipt.setReceiptDate(rs.getDate("rcpt_dt").toLocalDate());
					receipt.setReceiptAmount(rs.getDouble("rcpt_amt"));
					receipt.setIwdNumber(rs.getString("iwd_no"));
					receipt.setOldReceiptNumber(rs.getInt("old_rcpt_no"));
					receipt.setPayInSlip(rs.getString("pay_in_slip"));
					receipt.setEntityCd(rs.getString("entity_cd"));
					receipt.setProductDescription(rs.getString("product_description"));
					receipt.setHsn(rs.getDouble("hsn"));
					receipt.setUom(rs.getDouble("uom"));
					receipt.setQty(rs.getDouble("qty"));
					receipt.setRate(rs.getDouble("rate"));
					receipt.setAmount(rs.getDouble("amount"));
					receipt.setDiscount(rs.getDouble("discount"));
					receipt.setTaxableDiscount(rs.getDouble("taxable_discount"));
					receipt.setCustomerGstNo(rs.getString("customer_gst_no"));
					receipt.setEcgcGstNo(rs.getString("ecgc_gst_no"));
					receipt.setInvoiceNo(rs.getString("invoice_no"));
					receipt.setCgstAmount(rs.getDouble("cgst_amount"));
					receipt.setSgstAmount(rs.getDouble("sgst_amount"));
					receipt.setIgstAmount(rs.getDouble("igst_amount"));
					receipt.setUtgstAmount(rs.getDouble("utgst_amount"));
					receipt.setCgstTaxPer(rs.getDouble("cgst_tax_per"));
					receipt.setSgstTaxPer(rs.getDouble("sgst_tax_per"));
					receipt.setIgstTaxPer(rs.getDouble("igst_tax_per"));
					receipt.setUtgstTaxPer(rs.getDouble("utgst_tax_per"));
					receipt.setExpoState(rs.getString("expo_state"));
					receipt.setBranchState(rs.getString("branch_state"));
					receipt.setTaxType(rs.getString("tax_type"));
					receipt.setOldInvoiceNo(rs.getString("old_invoice_no"));
					receipt.setUpdateFlag(rs.getString("update_flg"));
					receipt.setSez(rs.getString("sez"));
					return receipt;
				}
			});
		} catch (Exception e) {
			receiptList = null;		
			throw new ReceiptNotFoundException();
		}
		return receiptList;
		
	}

	@Override
	public Receipt viewByLogicalLocCodeAndReceiptNo(String logicalLocCode, Integer receiptNumber) {

		Receipt receiptList = new Receipt();
		Map<String, Object> paraMmap = new HashMap<>();
		paraMmap.put("logicalLocCode", logicalLocCode);
		paraMmap.put("receiptNumber", receiptNumber);
		
		try {
			receiptList= namedParameterJdbcOperations.queryForObject(ReceiptSqlQueries.VIEW_BY_LOGICAL_LOC_CODE_AND_RECEIPT_NO, paraMmap, new RowMapper<Receipt>() {
				@Override
				public Receipt mapRow(ResultSet rs, int rowNum) throws SQLException{
					Receipt receipt = new Receipt();
					receipt.setLogicalLocCode(rs.getString("logical_loc_cd"));
					receipt.setGlFlag(rs.getString("gl_flg"));
					receipt.setMetaRemarks(rs.getString("meta_remarks"));
					receipt.setCreatedBy( rs.getString("created_by"));
					if(rs.getDate("created_dt") !=null)
					receipt.setCreatedDate(rs.getDate("created_dt").toLocalDate());
					receipt.setDrawnOn(rs.getString("drawn_on"));
					receipt.setMetaStatus(rs.getString("meta_status"));
					receipt.setFiscalYear(rs.getString("fiscal_yr"));
					receipt.setGlTxnNumber(rs.getInt("gl_txn_no"));
					receipt.setGlTxnNumberOld(rs.getInt("gl_txn_no_old"));
					receipt.setGlTxnType(rs.getString("gl_txn_type"));
					if(rs.getDate("instrument_dt") !=null)
					receipt.setInstrumentDate(rs.getDate("instrument_dt").toLocalDate());
					receipt.setInstrumentNumber(rs.getString("instrument_no"));
					receipt.setInstrumentType(rs.getString("instrument_type"));
					receipt.setLastUpdatedBy(rs.getString("last_updated_by"));
					receipt.setStampAmount(rs.getDouble("stamp_amt"));
					receipt.setRemarks(rs.getString("remarks"));
					receipt.setReceivedfromType(rs.getString("rcvd_from_type"));
					receipt.setReceivedFromName(rs.getString("rcvd_from_name"));
					receipt.setReceivedFromCode(rs.getString("rcvd_from_cd"));
					receipt.setReceivedFromAddress(rs.getString("rcvd_from_addr"));
					receipt.setReceiptNumber(rs.getInt("rcpt_no"));
					//receipt.setReceiptId(rs.getInt("rcpt_id"));
					if(rs.getDate("rcpt_dt") !=null)
					receipt.setReceiptDate(rs.getDate("rcpt_dt").toLocalDate());
					receipt.setReceiptAmount(rs.getDouble("rcpt_amt"));
					receipt.setIwdNumber(rs.getString("iwd_no"));
					receipt.setOldReceiptNumber(rs.getInt("old_rcpt_no"));
					receipt.setPayInSlip(rs.getString("pay_in_slip"));
					receipt.setEntityCd(rs.getString("entity_cd"));
					receipt.setProductDescription(rs.getString("product_description"));
					receipt.setHsn(rs.getDouble("hsn"));
					receipt.setUom(rs.getDouble("uom"));
					receipt.setQty(rs.getDouble("qty"));
					receipt.setRate(rs.getDouble("rate"));
					receipt.setAmount(rs.getDouble("amount"));
					receipt.setDiscount(rs.getDouble("discount"));
					receipt.setTaxableDiscount(rs.getDouble("taxable_discount"));
					receipt.setCustomerGstNo(rs.getString("customer_gst_no"));
					receipt.setEcgcGstNo(rs.getString("ecgc_gst_no"));
					receipt.setInvoiceNo(rs.getString("invoice_no"));
					receipt.setCgstAmount(rs.getDouble("cgst_amount"));
					receipt.setSgstAmount(rs.getDouble("sgst_amount"));
					receipt.setIgstAmount(rs.getDouble("igst_amount"));
					receipt.setUtgstAmount(rs.getDouble("utgst_amount"));
					receipt.setCgstTaxPer(rs.getDouble("cgst_tax_per"));
					receipt.setSgstTaxPer(rs.getDouble("sgst_tax_per"));
					receipt.setIgstTaxPer(rs.getDouble("igst_tax_per"));
					receipt.setUtgstTaxPer(rs.getDouble("utgst_tax_per"));
					receipt.setExpoState(rs.getString("expo_state"));
					receipt.setBranchState(rs.getString("branch_state"));
					receipt.setTaxType(rs.getString("tax_type"));
					receipt.setOldInvoiceNo(rs.getString("old_invoice_no"));
					receipt.setUpdateFlag(rs.getString("update_flg"));
					receipt.setSez(rs.getString("sez"));
					return receipt;
				}
			});
		} catch (Exception e) {
			receiptList = null;	
			throw new ReceiptNotFoundException();
			
		}
		return receiptList;
	}

	@Override
	public Integer updateReceipt(String logicalLocCode, Integer receiptNumber, Receipt receiptUpdate) {
			int rowCount;
		try {
			Map<String, Object>receiptNamedParameter = new HashMap<String ,Object>();
			receiptNamedParameter.put("logicalLocCode",logicalLocCode);
			receiptNamedParameter.put("receiptNumber",receiptNumber );
			receiptNamedParameter.put("receiptDate", receiptUpdate.getReceiptDate());
			receiptNamedParameter.put("iwdNumber",receiptUpdate.getIwdNumber());
			receiptNamedParameter.put("remarks", receiptUpdate.getRemarks());
			receiptNamedParameter.put("receivedFromCode", receiptUpdate.getReceivedFromCode());
			receiptNamedParameter.put("receivedFromName", receiptUpdate.getReceivedFromName());
			receiptNamedParameter.put("receivedFromAddress", receiptUpdate.getReceivedFromAddress());
			receiptNamedParameter.put("receivedfromType", receiptUpdate.getReceivedfromType());
			receiptNamedParameter.put("stampAmount", receiptUpdate.getStampAmount());
			receiptNamedParameter.put("metaRemarks", receiptUpdate.getMetaRemarks());	
			receiptNamedParameter.put("instrumentType", receiptUpdate.getInstrumentType());
			receiptNamedParameter.put("instrumentNumber" , receiptUpdate.getInstrumentNumber());
			receiptNamedParameter.put("instrumentDate", receiptUpdate.getInstrumentDate());
			receiptNamedParameter.put("drawnOn", receiptUpdate.getDrawnOn());
			receiptNamedParameter.put("glTxnType", receiptUpdate.getGlTxnType());
			receiptNamedParameter.put("glTxnNumber", receiptUpdate.getGlTxnNumber());
			receiptNamedParameter.put("receiptAmount", receiptUpdate.getReceiptAmount());
			receiptNamedParameter.put("glFlag", receiptUpdate.getGlFlag());
			receiptNamedParameter.put("glTxnNumberOld", receiptUpdate.getGlTxnNumberOld());
			receiptNamedParameter.put("oldReceiptNumber", receiptUpdate.getOldReceiptNumber());
			receiptNamedParameter.put("fiscalYear", receiptUpdate.getFiscalYear());
			receiptNamedParameter.put("createdBy", receiptUpdate.getCreatedBy());
			receiptNamedParameter.put("createdDate", receiptUpdate.getCreatedDate());
			receiptNamedParameter.put("lastUpdatedBy", receiptUpdate.getLastUpdatedBy());
			receiptNamedParameter.put("lastUpdatedDate", receiptUpdate.getLastUpdatedDate());
			receiptNamedParameter.put("metaStatus", receiptUpdate.getMetaStatus());
			receiptNamedParameter.put("payInSlip", receiptUpdate.getPayInSlip());
			receiptNamedParameter.put("entityCd", receiptUpdate.getEntityCd());
			receiptNamedParameter.put("productDescription", receiptUpdate.getProductDescription());
			receiptNamedParameter.put("hsn", receiptUpdate.getHsn());
			receiptNamedParameter.put("uom", receiptUpdate.getUom());
			receiptNamedParameter.put("qty", receiptUpdate.getQty());
			receiptNamedParameter.put("rate", receiptUpdate.getRate());
			receiptNamedParameter.put("amount",receiptUpdate.getAmount());
			receiptNamedParameter.put("discount", receiptUpdate.getDiscount());
			receiptNamedParameter.put("taxableDiscount", receiptUpdate.getTaxableDiscount());
			receiptNamedParameter.put("customerGstNo", receiptUpdate.getCustomerGstNo());
			receiptNamedParameter.put("ecgcGstNo", receiptUpdate.getEcgcGstNo());
			receiptNamedParameter.put("invoiceNo", receiptUpdate.getInvoiceNo());
			receiptNamedParameter.put("cgstAmount", receiptUpdate.getCgstAmount());
			receiptNamedParameter.put("sgstAmount", receiptUpdate.getSgstAmount());
			receiptNamedParameter.put("utgstAmount", receiptUpdate.getUtgstAmount());
			receiptNamedParameter.put("igstAmount", receiptUpdate.getIgstAmount());
			receiptNamedParameter.put("cgstTaxPer", receiptUpdate.getCgstTaxPer());
			receiptNamedParameter.put("sgstTaxPer", receiptUpdate.getSgstTaxPer());
			receiptNamedParameter.put("igstTaxPer", receiptUpdate.getIgstTaxPer());
			receiptNamedParameter.put("utgstTaxPer", receiptUpdate.getUtgstTaxPer());
			receiptNamedParameter.put("expoState", receiptUpdate.getExpoState());
			receiptNamedParameter.put("branchState", receiptUpdate.getBranchState());
			receiptNamedParameter.put("taxType", receiptUpdate.getTaxType());
			receiptNamedParameter.put("oldInvoiceNo", receiptUpdate.getOldInvoiceNo());
			receiptNamedParameter.put("updateFlag", receiptUpdate.getUpdateFlag());
			receiptNamedParameter.put("sez", receiptUpdate.getSez());
			
			rowCount = namedParameterJdbcTemplate.update(ReceiptSqlQueries.MODIFT_RECEIPT, receiptNamedParameter);
			LOGGER.info("RowCount",rowCount);

		} catch (DataAccessException e) {
			LOGGER.info("Exception occured while updating Receipt",e);
			return null;
		}
		return rowCount;
	}

	@Override
	public List<States> getAllStates() {

		List<States> stateList = new ArrayList<States>();
		stateList = namedParameterJdbcTemplate.query("select stateut_name from ecgc_acct_gst_stateut", new RowMapper<States>() {
			@Override
			public States mapRow(ResultSet rs, int rowNum) throws SQLException{
				States states = new States();
				//states.setSrno(rs.getInt("sr_no"));				
				states.setStateUtName(rs.getString("stateut_name"));
				return states;				
			}
		});
		return stateList;
	}
	
	@Override
	public Integer updatePrintFlag(String logicalLocCode, Integer receiptNumber, String printFlag) {
			int rowCount;
		try {
			Map<String, Object>receiptNamedParameter = new HashMap<String ,Object>();
			receiptNamedParameter.put("logicalLocCode",logicalLocCode);
			receiptNamedParameter.put("receiptNumber",receiptNumber );
			receiptNamedParameter.put("printFlag",printFlag);
			
			rowCount = namedParameterJdbcTemplate.update(ReceiptSqlQueries.UPDATE_PRINT_FLAG, receiptNamedParameter);
			
			LOGGER.info("RowCount",rowCount);

		} catch (DataAccessException e) {
			LOGGER.info("Exception occured while updating Receipt",e);
			return null;
		}
		System.err.println("dao"+rowCount);
		return rowCount;
	}
	

	@Override
	public String getFlag(String logicalLocCode, Integer receiptNumber) {

		Receipt receiptList = new Receipt();
		Map<String, Object> paraMmap = new HashMap<>();
		paraMmap.put("logicalLocCode", logicalLocCode);
		paraMmap.put("receiptNumber", receiptNumber);
		
		try {
			receiptList= namedParameterJdbcOperations.queryForObject(ReceiptSqlQueries.GET_FLAG_BY_LOGICAL_LOC_CODE_AND_RECEIPT_NO, paraMmap, new RowMapper<Receipt>() {
				@Override
				public Receipt mapRow(ResultSet rs, int rowNum) throws SQLException{
					Receipt receipt = new Receipt();
					receipt.setPrintFlag(rs.getString("pflag"));
					
					return receipt;
				}
			});
		} catch (EmptyResultDataAccessException e) {
			receiptList = null;			
		}
		return receiptList.getPrintFlag();
	}
}
