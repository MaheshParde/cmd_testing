/**
 * 
 */
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FtrDtl;
import in.ecgc.smile.erp.accounts.service.FTRService;
import in.ecgc.smile.erp.accounts.util.FTRSqlQueries;

/**
 * @author CMJ-SENG-LAPTOP9
 *
 */
public class FtrDaoImplTest {


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

	@InjectMocks
	private FTRDaoImpl ftrDao;

	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeTest
	public void InitFtr() {
		ftr = new FTR();
		ftr.setFtrReqNo(21);
		ftr.setFtrType("Fund Transfer");
		ftr.setLogicalLocCode("MUMBAILOG1");
		ftr.setFtrReqBranchCd("MUMBAI");
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
	public void getAllFTRRequestTest() {
		List<FTR> list = new ArrayList<FTR>();
		list.add(ftr);
		when(jdbcOperations.query(FTRSqlQueries. VIEW_ALL_FTR,
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
	public void addRequestTest() {
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
		when(namedParameterJdbcOperations.update(FTRSqlQueries.ADD_FTR,FTRNamedParameters1)).thenReturn(1);
	}
	
	@Test
	public void addFtrDetailTest() {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("FTRRequestNo",dtl.getFTRRequestNo());
		FTRNamedParameters.put("FTRRequestAmount",dtl.getFTRRequestAmount());
		FTRNamedParameters.put("FTRRequestType",dtl.getFTRRequestType());
		FTRNamedParameters.put("FTRRequestReason",dtl.getFTRRequestReason());
		when(namedParameterJdbcOperations.update(FTRSqlQueries.ADD_FTR_DTL1,FTRNamedParameters)).thenReturn(1);
		
	}
	
	@Test
	public void getAllPendingFTRRequestTest() {
		ArrayList<FTR> list = new ArrayList<FTR>();
		list.add(ftr);
		when(jdbcOperations.query(FTRSqlQueries.VIEW_ALL_PENDING_FTR,
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
	public void getAllFTRRequestByLogicalLocTest() {
		ArrayList<FTR> list = new ArrayList<FTR>();
		list.add(ftr);
		String logicalLoc = "MUMBAILOG1";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("logicalLoc", logicalLoc);
		when(namedParameterJdbcOperations.query(FTRSqlQueries.VIEW_ALL_FTR_BRANCH,paramMap,
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
		when(jdbcOperations.query(FTRSqlQueries.GET_FTR_DTL, new Object[] {ftrId},
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
	public void decisionOnFTRRequestTest() {
		Map<String, Object> FTRNamedParameters1 = new HashMap<String, Object>();
		FTRNamedParameters1.put("ftrReqNo",ftr.getFtrReqNo());
		FTRNamedParameters1.put("ftrReqStatus",ftr.getFtrReqStatus());
		FTRNamedParameters1.put("ftrApprBy",ftr.getFtrApprBy());
		FTRNamedParameters1.put("ftrTrfDt",ftr.getFtrTrfDt());
		when(namedParameterJdbcOperations.update(FTRSqlQueries.DECISION_ON_FTR,FTRNamedParameters1)).thenReturn(1);
	}
	
	@Test
	public void findFtrDtlTest() {
		List<FtrDtl> list = new ArrayList<FtrDtl>();
		String ftrId = "21";
		when(namedParameterJdbcOperations.query(FTRSqlQueries.GET_FTR_DTL.concat(ftrId),
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
	public void findFtrByIdTest() {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ftrReqNo",21);
		List<FTR> ftr1 = new ArrayList<>();
		ftr1.add(ftr);
		when(namedParameterJdbcOperations.query(FTRSqlQueries.GET_FTR_HDR, paramMap,
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
	public void updateFtrhdrTest() {
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
		when(namedParameterJdbcOperations.update(FTRSqlQueries.UPDATE_FTR_HDR,FTRNamedParameters1)).thenReturn(1);
	}
	
	@Test
	public void updateFtrdtlTest() {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
			System.err.println(ftr);
		FTRNamedParameters.put("FTRRequestNo",dtl.getFTRRequestNo());
		FTRNamedParameters.put("FTRRequestAmount",dtl.getFTRRequestAmount());
		FTRNamedParameters.put("FTRRequestType",dtl.getFTRRequestType());
		FTRNamedParameters.put("FTRRequestReason",dtl.getFTRRequestReason());
		FTRNamedParameters.put("ecibIntlBankName",dtl.getEcibIntlBankNAme());
		FTRNamedParameters.put("ecibClaimNo", dtl.getEcibClaimNo());
		FTRNamedParameters.put("ecibType",dtl.getEcibType());
		FTRNamedParameters.put("expPolicyDatePAyment",dtl.getExpPolicyDatePAyment());
		FTRNamedParameters.put("expEcibDatePayment",dtl.getExpEcibDatePayment());
		FTRNamedParameters.put("policyType",dtl.getPolicyType());
		FTRNamedParameters.put("policyClaimNo",dtl.getPolicyClaimNo());
		FTRNamedParameters.put("exporterName",dtl.getExporterName());
		FTRNamedParameters.put("ieCode",dtl.getIeCode());
		FTRNamedParameters.put("FTRRequestSrNo",dtl.getFTRRequestSrNo());
		FTRNamedParameters.put("logicalLocCode",dtl.getLogicalLocCode());
		when(namedParameterJdbcOperations.update(FTRSqlQueries.UPDATE_FTR_DTL,FTRNamedParameters)).thenReturn(1);
	}
	
	@Test
	public void deteleFtrReqTest() {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("ftrReqNo",21);
		when(namedParameterJdbcOperations.queryForObject(FTRSqlQueries.DELETE_DTL_REQUEST,FTRNamedParameters,
				new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int num =rs.getInt(1);
				return num;
			}})
).thenReturn(1);
	}

	@Test
	public void getSeqTest() {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("seq_coloumn","ecgc_acct_ftr_req_seq");
		when(namedParameterJdbcOperations.queryForObject(FTRSqlQueries.GET_MAX_SRNO,FTRNamedParameters,
				new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int num =rs.getInt(1);
				return num;
			}})
).thenReturn(1);
	}
	
	@Test
	public void deteleFtrReqDtlTest() {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("ftrNo",21);
		FTRNamedParameters.put("srNo",1);
		when(namedParameterJdbcOperations.queryForObject(FTRSqlQueries.DELETE_FTR_DTL,FTRNamedParameters,
				new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int num =rs.getInt(1);
				return num;
			}})
).thenReturn(1);
	}
	
	@Test
	public void getAllApprovedFTRRequestTest() {
		ArrayList<FTR> list = new ArrayList<FTR>();
		list.add(ftr);
		when(jdbcOperations.query(FTRSqlQueries.VIEW_ALL_APPROVED_FTR,
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
	
	/*
	 * @Test public void changeStatusToTransferTest() { Map<String, Object>
	 * FTRNamedParameters = new HashMap<String, Object>();
	 * FTRNamedParameters.put("ftrReqNo",ftr.getFtrReqNo());
	 * FTRNamedParameters.put("ftrReqStatus",ftr.getFtrReqStatus().charAt(0));
	 * FTRNamedParameters.put("metaRemarks",ftr.getMetaRemarks());
	 * FTRNamedParameters.put("trfDt",ftr.getFtrTrfDt());
	 * FTRNamedParameters.put("updatedBy",ftr.getUpdatedBy());
	 * when(namedParameterJdbcOperations.update(FTRSqlQueries.DECISION_ON_FTR,
	 * FTRNamedParameters)).thenReturn(1); }
	 */

}
