package in.ecgc.smile.erp.accounts.repository;

import static org.mockito.Mockito.when;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import in.ecgc.smile.erp.accounts.model.Receipt;
import in.ecgc.smile.erp.accounts.util.ReceiptSqlQueries;

public class ReceiptDaoImplTests {
	private MockMvc mockMvc;
	@Mock
	private DataSource dataSource;
	@Mock
	private JdbcOperations jdbcOperations;
	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcOperations;		
	@Mock
	private Receipt receipt;
	@Mock
	private List<Receipt> receipts;
	@Mock
	private Map<String, Object> receiptNamedParameter;
	@InjectMocks
	private ReceiptDaoImpl receiptDaoImpl;
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(receiptDaoImpl).build();
	}
	@BeforeTest
	public void initReceipt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date receiptDate = new Date();
		Date instrumentDate = new Date();		
		try {
			 receiptDate = sdf.parse("2018-04-01");
			 instrumentDate = sdf.parse("2019-04-01");			
		} catch (Exception e) {
			System.out.println(e);
		}
		receipt = new Receipt();
		receipt.setLogicalLocCode("MUM");
		receipt.setReceivedFromCode("abc");
		receipt.setReceiptAmount(2000.00);
		receipt.setFiscalYear("2018");
		receipt.setReceiptNumber(100);
		receipt.setIwdNumber("abc100");
		//receipt.setReceiptDate(receiptDate);
		receipt.setRemarks("final");
		receipt.setReceivedFromName("Super");
		receipt.setReceivedFromAddress("22 street road");
		receipt.setStampAmount(1000.00);
		receipt.setInstrumentType("NEFT");
		receipt.setInstrumentNumber("123456");
		//receipt.setInstrumentDate(instrumentDate);
		receipt.setDrawnOn("2019-04-01");
		receipt.setGlTxnNumberOld(13452);
		receipt.setGlFlag("N");
		receipt.setOldReceiptNumber(1245);
		receipt.setPayInSlip("submitted");
		receipt.setMetaRemarks("abc");
		receipt.setMetaStatus("T");		
	}
	
	@BeforeTest
	public void initReceiptNamedParameter() {
		receiptNamedParameter.put("logicalLocCode" ,receipt.getLogicalLocCode());
		receiptNamedParameter.put("receiptNumber", receipt.getReceiptNumber());
		receiptNamedParameter.put("receiptDate", receipt.getReceiptDate());
		receiptNamedParameter.put("iwdNumber", receipt.getIwdNumber());
		receiptNamedParameter.put("remarks",receipt.getRemarks() );
		receiptNamedParameter.put("recievedFromCode", receipt.getReceivedFromCode());
		receiptNamedParameter.put("recievedFromName", receipt.getReceivedFromName());
		receiptNamedParameter.put("recievedFromAddress", receipt.getReceivedFromAddress());
		receiptNamedParameter.put("fiscalYear", receipt.getFiscalYear());
		receiptNamedParameter.put("oldReceiptNumber", receipt.getOldReceiptNumber());
		receiptNamedParameter.put("glTxnNumberOld",receipt.getGlTxnNumberOld());
		receiptNamedParameter.put("metaStatus", receipt.getMetaStatus());
		receiptNamedParameter.put("metaRemarks", receipt.getMetaRemarks());
		receiptNamedParameter.put("glFlag", receipt.getGlFlag());
		receiptNamedParameter.put("stampAmount", receipt.getStampAmount());
		receiptNamedParameter.put("instrumentType", receipt.getInstrumentType());
		receiptNamedParameter.put("instrumentNumber", receipt.getInstrumentNumber());
		receiptNamedParameter.put("instrumentDate" , receipt.getInstrumentDate());
		receiptNamedParameter.put("drawnOn", receipt.getDrawnOn());
		receiptNamedParameter.put("glTxnType", receipt.getGlTxnType());
		receiptNamedParameter.put("glTxnNumber", receipt.getGlTxnNumber());
		receiptNamedParameter.put("recievedfromType" , receipt.getReceivedfromType());
		receiptNamedParameter.put("receiptAmount", receipt.getReceiptAmount());
		receiptNamedParameter.put("payingSlipReport", receipt.getPayInSlip());
	}
	@Test
	public void listALlReceiptsDaoImplTest() {
		when(jdbcOperations.query(ReceiptSqlQueries.VIEW_ALL_RECEIPTS, new RowMapper<Receipt>() {
			@Override
			public Receipt mapRow(ResultSet rs, int rowNum)throws SQLException{
				receipt = new Receipt();
				receipt.setLogicalLocCode(rs.getString("logical_loc_cd"));
				receipt.setGlFlag(rs.getString("gl_flg"));
				receipt.setMetaRemarks(rs.getString("meta_remarks"));
				receipt.setDrawnOn(rs.getString("drawn_on"));
				receipt.setMetaStatus(rs.getString("meta_status"));
				receipt.setFiscalYear(rs.getString("fiscal_yr"));
				//receipt.setGlTxnNumber(rs.getString("gl_txn_no"));
				receipt.setGlTxnNumberOld(rs.getInt("gl_txn_no_old"));
				receipt.setGlTxnType(rs.getString("gl_txn_type"));
				//receipt.setInstrumentDate(rs.getDate("instrument_dt"));
				receipt.setInstrumentNumber(rs.getString("instrument_no"));
				receipt.setInstrumentType(rs.getString("instrument_type"));
				receipt.setStampAmount(rs.getDouble("stamp_amt"));
				receipt.setRemarks(rs.getString("remarks"));
				receipt.setReceivedfromType(rs.getString("rcvd_from_type"));
				receipt.setReceivedFromName(rs.getString("rcvd_from_name"));
				receipt.setReceivedFromCode(rs.getString("rcvd_from_cd"));
				receipt.setReceivedFromAddress(rs.getString("rcvd_from_addr"));
				receipt.setReceiptNumber(rs.getInt("rcpt_no"));
				//receipt.setReceiptDate(rs.getDate("rcpt_dt"));
				receipt.setReceiptAmount(rs.getDouble("rcpt_amt"));
				receipt.setIwdNumber(rs.getString("iwd_no"));
				receipt.setPayInSlip(rs.getString("paying_slip_report"));
				receipt.setOldReceiptNumber(rs.getInt("old_rcpt_no"));
				return receipt;
			}
		})).thenReturn(receipts);		
	}
	

	@Test 
	public void viewReceiptBylogicalLocCode() {
		Map<String , Object> paraMap = new HashMap<>();
		paraMap.put("logicalLocCode","MUMBAILOG1");
		//paraMap.put("receiptNumber", "2021000002");
		when(namedParameterJdbcOperations.queryForObject(ReceiptSqlQueries.VIEW_BY_LOGICAL_LOC_CODE_AND_RECEIPT_NO, paraMap, new RowMapper<Receipt>() {
			@Override
			public Receipt mapRow(ResultSet rs, int rowNum)throws SQLException{
				Receipt receipt = new Receipt();
				receipt.setGlFlag(rs.getString("gl_flg"));
				receipt.setReceiptNumber(rs.getInt("rcpt_no"));
				receipt.setMetaRemarks(rs.getString("meta_remarks"));
				receipt.setDrawnOn(rs.getString("drawn_on"));
				receipt.setMetaStatus(rs.getString("meta_status"));
				receipt.setFiscalYear(rs.getString("fiscal_yr"));
				//receipt.setGlTxnNumber(rs.getString("gl_txn_no"));
				receipt.setGlTxnNumberOld(rs.getInt("gl_txn_no_old"));
				receipt.setGlTxnType(rs.getString("gl_txn_type"));
				//receipt.setInstrumentDate(rs.getDate("instrument_dt"));
				receipt.setInstrumentNumber(rs.getString("instrument_no"));
				receipt.setInstrumentType(rs.getString("instrument_type"));
				receipt.setStampAmount(rs.getDouble("stamp_amt"));
				receipt.setRemarks(rs.getString("remarks"));
				receipt.setReceivedfromType(rs.getString("rcvd_from_type"));
				receipt.setReceivedFromName(rs.getString("rcvd_from_name"));
				receipt.setReceivedFromCode(rs.getString("rcvd_from_cd"));
				receipt.setReceivedFromAddress(rs.getString("rcvd_from_addr"));
				receipt.setReceiptNumber(rs.getInt("rcpt_no"));
				//receipt.setReceiptDate(rs.getDate("rcpt_dt"));
				receipt.setReceiptAmount(rs.getDouble("rcpt_amt"));
				receipt.setIwdNumber(rs.getString("iwd_no"));
				receipt.setPayInSlip(rs.getString("paying_slip_report"));
				receipt.setOldReceiptNumber(rs.getInt("old_rcpt_no"));
				return receipt;
			}
		})).thenReturn(receipt);
	}	
	
	
	@Test 
	public void viewReceiptBylogicalLocAndReceiptNumber() {
		Map<String , Object> paraMap = new HashMap<>();
		paraMap.put("logicalLocCode","MUMBAILOG1");
		paraMap.put("receiptNumber", "2021000002");
		when(namedParameterJdbcOperations.queryForObject(ReceiptSqlQueries.VIEW_BY_LOGICAL_LOC_CODE_AND_RECEIPT_NO, paraMap, new RowMapper<Receipt>() {
			@Override
			public Receipt mapRow(ResultSet rs, int rowNum)throws SQLException{
				Receipt receipt = new Receipt();
				receipt.setGlFlag(rs.getString("gl_flg"));
				receipt.setMetaRemarks(rs.getString("meta_remarks"));
				receipt.setDrawnOn(rs.getString("drawn_on"));
				receipt.setMetaStatus(rs.getString("meta_status"));
				receipt.setFiscalYear(rs.getString("fiscal_yr"));
				//receipt.setGlTxnNumber(rs.getString("gl_txn_no"));
				receipt.setGlTxnNumberOld(rs.getInt("gl_txn_no_old"));
				receipt.setGlTxnType(rs.getString("gl_txn_type"));
				//receipt.setInstrumentDate(rs.getDate("instrument_dt"));
				receipt.setInstrumentNumber(rs.getString("instrument_no"));
				receipt.setInstrumentType(rs.getString("instrument_type"));
				receipt.setStampAmount(rs.getDouble("stamp_amt"));
				receipt.setRemarks(rs.getString("remarks"));
				receipt.setReceivedfromType(rs.getString("rcvd_from_type"));
				receipt.setReceivedFromName(rs.getString("rcvd_from_name"));
				receipt.setReceivedFromCode(rs.getString("rcvd_from_cd"));
				receipt.setReceivedFromAddress(rs.getString("rcvd_from_addr"));
				receipt.setReceiptNumber(rs.getInt("rcpt_no"));
				//receipt.setReceiptDate(rs.getDate("rcpt_dt"));
				receipt.setReceiptAmount(rs.getDouble("rcpt_amt"));
				receipt.setIwdNumber(rs.getString("iwd_no"));
				receipt.setPayInSlip(rs.getString("paying_slip_report"));
				receipt.setOldReceiptNumber(rs.getInt("old_rcpt_no"));
				return receipt;
			}
		})).thenReturn(receipt);
	}	
	@Test
	public void updateReceiptDaoImplTest() {
		when(namedParameterJdbcOperations.update(ReceiptSqlQueries.MODIFT_RECEIPT, receiptNamedParameter))
		.thenReturn(1);		
	}
	@Test 
	private void addReceiptDaoImplTest() {
		when(namedParameterJdbcOperations.update(ReceiptSqlQueries.ADD_RECEIPT, receiptNamedParameter))
		.thenReturn(1);
		}
	
}
