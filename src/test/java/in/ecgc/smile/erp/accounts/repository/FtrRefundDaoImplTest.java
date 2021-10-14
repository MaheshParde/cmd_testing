package in.ecgc.smile.erp.accounts.repository;

import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FtrDtl;
import in.ecgc.smile.erp.accounts.util.FTRSqlQueries;
import org.testng.Assert;

public class FtrRefundDaoImplTest {

	private MockMvc mockMvc;

	@Mock
	FTR ftr;

	@Mock
	FtrDtl dtl;
	
	@Mock
	private DataSource dataSource;
	
	@Mock
	private JdbcOperations jdbcOperations;
	
	@Mock
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	
	@Mock
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@InjectMocks
	private FtrRefundDaoImpl dao;
	

	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeTest
	public void InitFtr() {
		ftr = new FTR();
		ftr.setFtrReqNo(21);
		ftr.setFtrType("Fund Transfer");
		ftr.setLogicalLocCode("HOLOG1");
		ftr.setFtrReqBranchCd("HO");
		ftr.setFtrReqBy(101);
		ftr.setFtrTrfAmt(100000.00);
		ftr.setFtrReqDeptCd("ADMIN");
		ftr.setFtrReqDt(LocalDate.now());
		ftr.setFtrApprBy(102);
		ftr.setFtrReqStatus("P");
		ftr.setFtrTrfDt(LocalDate.now());
		ftr.setPvStatus("P");	
		ftr.setMetaRemarks("Test");
		ftr.setUpdatedBy("101");
		ftr.setReqTo("MUMBAILOG1");
		
		FtrDtl ftrdtl = new FtrDtl();
		ftrdtl.setFTRRequestNo(21);
		ftrdtl.setFTRRequestAmount((float)50000.00);
		ftrdtl.setFTRRequestReason("Test");
		ftrdtl.setFTRRequestType("ADM");
		ftrdtl.setFTRRequestSrNo(1);
		ftrdtl.setFtrFor("Other");
		List<FtrDtl> list = new ArrayList<FtrDtl>();
		list.add(ftrdtl);
		ftr.setFtrDtl(list);
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																			
	}
	
	@BeforeTest
	public void initFtrDtl() {
		dtl.setFTRRequestNo(21);
		dtl.setFTRRequestAmount((float)50000.00);
		dtl.setFTRRequestReason("Test");
		dtl.setFTRRequestType("ADM");
		dtl.setFTRRequestSrNo(1);
		dtl.setFtrFor("Other");
	}
	
	@Test
	public void addRefundRequestTest() {
		Map<String, Object> FTRNamedParameters1 = new HashMap<String, Object>();
		FTRNamedParameters1.put("ftrReqNo",ftr.getFtrReqNo());
		FTRNamedParameters1.put("ftrReqDt",ftr.getFtrReqDt());
		FTRNamedParameters1.put("ftrReqBranchCd",ftr.getFtrReqBranchCd());
		FTRNamedParameters1.put("ftrReqBy",ftr.getFtrReqBy());
		FTRNamedParameters1.put("ftrReqDeptCd",ftr.getFtrReqDeptCd());
		FTRNamedParameters1.put("ftrReqStatus",ftr.getFtrReqStatus());
		FTRNamedParameters1.put("logicalLocCd",ftr.getLogicalLocCode());
		FTRNamedParameters1.put("ftrApprBy",ftr.getFtrApprBy());
		FTRNamedParameters1.put("ftrTrfDt",ftr.getFtrTrfDt());
		FTRNamedParameters1.put("ftrTrfAmt",ftr.getFtrTrfAmt());
		FTRNamedParameters1.put("pvStatus",ftr.getPvStatus());
		FTRNamedParameters1.put("updatedBy",ftr.getUpdatedBy());
		FTRNamedParameters1.put("updatedOn",ftr.getUpdatedOn());
		FTRNamedParameters1.put("ecgcStatus",ftr.getEcgcStatus());
		FTRNamedParameters1.put("metaRemarks",ftr.getMetaRemarks());
		FTRNamedParameters1.put("createdOn",ftr.getCreatedOn());
		FTRNamedParameters1.put("createdBy",ftr.getCreatedBy());
		FTRNamedParameters1.put("reqTo",ftr.getReqTo());
		when(namedParameterJdbcTemplate.update(FTRSqlQueries.ADD_FTR_REFUND_REQ,FTRNamedParameters1)).thenReturn(1);
	//	Assert.assertEquals(dao.addRefundRequest(ftr),new Integer(1));
	}
	
	@Test
	public void addRefundRequestDtlTest() {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("FTRRequestNo",dtl.getFTRRequestNo());
		FTRNamedParameters.put("FTRRequestAmount",dtl.getFTRRequestAmount());
		FTRNamedParameters.put("FTRRequestType",dtl.getFTRRequestType());
		FTRNamedParameters.put("FTRRequestReason",dtl.getFTRRequestReason());
		when(namedParameterJdbcTemplate.update(FTRSqlQueries.ADD_FTR_REFUND_REQ_DTL,FTRNamedParameters)).thenReturn(1);
		
	}
	
	@Test
	public void getAllFTRRequestTest() {
		ArrayList<FTR> list = new ArrayList<FTR>();
		list.add(ftr);
		String logicalLoc = "MUMBAILOG1";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("logicalLoc", logicalLoc);
		when(namedParameterJdbcOperations.query(
				FTRSqlQueries.VIEW_ALL_FTR_REFUND_BRANCH, paramMap,
				new RowMapper<FTR>() {

			@Override
			public FTR mapRow(ResultSet rs, int rowNum) throws SQLException {
				FTR ftrTemp = new FTR();
				ftrTemp.setFtrReqNo(rs.getInt("ftr_req_no"));
				ftrTemp.setFtrReqStatus(rs.getString("ftr_req_status"));
				ftrTemp.setLogicalLocCode(rs.getString("logicalloc_cd"));
				ftrTemp.setFtrTrfAmt(rs.getDouble("ftr_trf_amt"));
				ftrTemp.setPvStatus(rs.getString("pv_status"));
				return ftrTemp;
			}
		})).thenReturn(list);
	}
	
	@Test
	public void getFTRRequestDtlTest() {
		List<FtrDtl> list = new ArrayList<FtrDtl>();
		String ftrId = "21";
		when(jdbcOperations.query(FTRSqlQueries.GET_FTR_DTL,new Object[] { ftrId },
				new RowMapper<FtrDtl>() {

			@Override
			public FtrDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
				FtrDtl ftrTemp = new FtrDtl();
				ftrTemp.setFTRRequestNo(rs.getInt("ftr_req_no"));
				ftrTemp.setFTRRequestAmount(rs.getFloat("ftr_req_amt"));
				ftrTemp.setFTRRequestType(rs.getString("ftr_req_type"));
				ftrTemp.setFTRRequestReason(rs.getString("ftr_req_reason"));
				ftrTemp.setLogicalLocCode(rs.getString("logical_loc_cd"));
				ftrTemp.setFTRRequestSrNo(rs.getInt("ftr_req_srno"));
				return ftrTemp;
			}
		})).thenReturn(list);
	}
	
	@Test
	public void findFtrDtlTest() {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ftrReqNo",21);
		List<FTR> ftr1 = new ArrayList<>();
		ftr1.add(ftr);
		String ftrid = "21";
		when(namedParameterJdbcOperations.query(FTRSqlQueries.GET_FTR_DTL.concat(ftrid),
				new RowMapper<FTR>() {

			@Override
			public FTR mapRow(ResultSet rs, int rowNum) throws SQLException {
				FTR ftrTemp = new FTR();
				ftrTemp.setFtrReqNo(rs.getInt("ftr_req_no"));
				ftrTemp.setFtrReqStatus(rs.getString("ftr_req_status"));
				ftrTemp.setLogicalLocCode(rs.getString("logicalloc_cd"));
				ftrTemp.setFtrTrfAmt(rs.getDouble("ftr_trf_amt"));
				ftrTemp.setPvStatus(rs.getString("pv_status"));
				return ftrTemp;
			}
		})).thenReturn(ftr1);
	
	}
	
	@Test
	public void findFtrByIdTest() {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ftrReqNo",21);
		when(namedParameterJdbcOperations.queryForObject(FTRSqlQueries.GET_FTR_HDR, paramMap,
				new RowMapper<FTR>() {

			@Override
			public FTR mapRow(ResultSet rs, int rowNum) throws SQLException {
				FTR ftrTemp = new FTR();
				ftrTemp.setFtrReqNo(rs.getInt("ftr_req_no"));
				ftrTemp.setFtrReqStatus(rs.getString("ftr_req_status"));
				ftrTemp.setLogicalLocCode(rs.getString("logicalloc_cd"));
				ftrTemp.setFtrTrfAmt(rs.getDouble("ftr_trf_amt"));
				ftrTemp.setPvStatus(rs.getString("pv_status"));
				return ftrTemp;
			}
		})).thenReturn(ftr);
	
	}
	
	@Test
	public void decisionOnFTRRequestTest() {
		Map<String, Object> FTRNamedParameters1 = new HashMap<String, Object>();
		FTRNamedParameters1.put("ftrReqNo",ftr.getFtrReqNo());
		FTRNamedParameters1.put("ftrReqStatus",ftr.getFtrReqStatus());
		FTRNamedParameters1.put("ftrApprBy",ftr.getFtrApprBy());
		FTRNamedParameters1.put("ftrTrfDt",ftr.getFtrTrfDt());
		when(namedParameterJdbcTemplate.update(FTRSqlQueries.DECISION_ON_FTR,FTRNamedParameters1)).thenReturn(1);
	}


	  


	
}
