package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FtrDtl;
import in.ecgc.smile.erp.accounts.util.FTRSqlQueries;

@Repository
@Transactional
public class FtrRefundDaoImpl implements FtrRefundDao {

	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(FtrRefundDaoImpl.class);

	public FtrRefundDaoImpl(DataSource dataSource) {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		}

	@Override
	public Integer addRefundRequest(FTR ftr) {
		ftr.setEcgcStatus('V');
		try {
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
		FTRNamedParameters1.put("ftrType",ftr.getFtrType());
		FTRNamedParameters1.put("reqTo",ftr.getReqTo());
		LOGGER.info(FTRNamedParameters1.get("ftrReqNo").toString());
		int rowCount = namedParameterJdbcTemplate.update(FTRSqlQueries.ADD_FTR_REFUND_REQ,FTRNamedParameters1);
		
		if(rowCount==1) {
						return 1;
		}
		}
		catch(Exception e) {
			LOGGER.error("Exception Occured",e);
		}
		return -1;


	}

	@Override
	public Integer addRefundRequestDtl(FtrDtl ftr) {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("FTRRequestNo",ftr.getFTRRequestNo());
		FTRNamedParameters.put("FTRRequestAmount",ftr.getFTRRequestAmount());
		FTRNamedParameters.put("FTRRequestType",ftr.getFTRRequestType());
		FTRNamedParameters.put("FTRRequestReason",ftr.getFTRRequestReason());
		FTRNamedParameters.put("FTRRequestSrNo",ftr.getFTRRequestSrNo());
		//FTRNamedParameters.put("branchCode",ftr.getBranchCode());
		FTRNamedParameters.put("logicalLocCode",ftr.getLogicalLocCode());
		FTRNamedParameters.put("updatedBy",ftr.getUpdatedBy());
		FTRNamedParameters.put("updatedOn",ftr.getUpdatedOn());
		FTRNamedParameters.put("ecgcStatus",ftr.getEcgcStatus());
		FTRNamedParameters.put("metaRemark",ftr.getMetaRemarks());
		FTRNamedParameters.put("createdOn",ftr.getCreatedOn());
		FTRNamedParameters.put("createdBy",ftr.getCreatedBy());
		 int rowCount = namedParameterJdbcTemplate.update(FTRSqlQueries.ADD_FTR_REFUND_REQ_DTL,FTRNamedParameters);
		 	if(rowCount == 1)
		 		return 1;
			return 0;

	}

	@Override
	public List<FTR> getAllFTRRequest(String logicalLoc) {
		
		List<FTR> ftr = new ArrayList<FTR>();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("logicalLoc", logicalLoc);
		ftr = namedParameterJdbcOperations.query(
				FTRSqlQueries.VIEW_ALL_FTR_REFUND_BRANCH, paramMap,
				new RowMapper<FTR>() {

					@Override
					public FTR mapRow(ResultSet rs, int rowNum) throws SQLException {
						FTR ftrTemp = new FTR();
						ftrTemp.setFtrReqNo(rs.getInt("ftr_req_no"));
						if(rs.getDate("ftr_req_dt") != null)
						ftrTemp.setFtrReqDt(rs.getDate("ftr_req_dt").toLocalDate());
						ftrTemp.setFtrReqBy(rs.getInt("ftr_req_by"));
						ftrTemp.setFtrReqBranchCd(rs.getString("ftr_req_branch_cd"));
						ftrTemp.setFtrReqDeptCd(rs.getString("ftr_req_dept_cd"));
						ftrTemp.setFtrReqStatus(rs.getString("ftr_req_status"));
						ftrTemp.setLogicalLocCode(rs.getString("logicalloc_cd"));
						ftrTemp.setFtrApprBy(rs.getInt("ftr_appr_by"));
						if(rs.getDate("ftr_trf_dt") != null)
						ftrTemp.setFtrTrfDt(rs.getDate("ftr_trf_dt").toLocalDate());
						ftrTemp.setFtrTrfAmt(rs.getDouble("ftr_trf_amt"));
						ftrTemp.setPvStatus(rs.getString("pv_status"));
						return ftrTemp;
					}
				});
		return ftr;

	}

	@Override
	public List<FtrDtl> getFTRRequestDtl(String ftrId) {
		List<FtrDtl> ftr = new ArrayList<FtrDtl>();
		ftr = jdbcOperations.query(FTRSqlQueries.GET_FTR_DTL,new Object[] { ftrId },
				new RowMapper<FtrDtl>() {

					@Override
					public FtrDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
						FtrDtl ftrTemp = new FtrDtl();
						ftrTemp.setFTRRequestNo(rs.getInt("ftr_req_no"));
						ftrTemp.setFTRRequestAmount(rs.getFloat("ftr_req_amt"));
						ftrTemp.setFTRRequestType(rs.getString("ftr_req_type"));
						ftrTemp.setFTRRequestReason(rs.getString("ftr_req_reason"));
						ftrTemp.setEcibIntlBankNAme(rs.getString("ecib_intlbnk_name"));
						ftrTemp.setEcibClaimNo(rs.getString("ecib_claim_no"));
						if(rs.getDate("exp_eci_date_pym") != null)
						ftrTemp.setExpEcibDatePayment(rs.getDate("exp_eci_date_pym").toLocalDate());
						ftrTemp.setEcibType(rs.getString("ecib_type"));
						if(rs.getDate("exp_pol_date_pym") != null)
						ftrTemp.setExpPolicyDatePAyment(rs.getDate("exp_pol_date_pym").toLocalDate());
						ftrTemp.setPolicyClaimNo(rs.getString("pol_claim_no"));
						ftrTemp.setPolicyType(rs.getString("pol_type"));
						ftrTemp.setExporterName(rs.getString("expo_name"));
						ftrTemp.setIeCode(rs.getString("ie_cd"));
						ftrTemp.setLogicalLocCode(rs.getString("logical_loc_cd"));
						ftrTemp.setFTRRequestSrNo(rs.getInt("ftr_req_srno"));
						
						return ftrTemp;
					}
				});

		return null;
	}

	public FTR findFtrDtl(String ftrid) {

		LOGGER.info("inside FTR Detail");
		FTR ftr = findFtrById(ftrid);
		List<FtrDtl> ftrDtl = null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ftrRequestNo", ftrid);

		try {
			ftrDtl = namedParameterJdbcOperations.query(FTRSqlQueries.GET_FTR_DTL.concat(ftrid),
					new RowMapper<FtrDtl>() {

						@Override
						public FtrDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
							FtrDtl ftrTemp = new FtrDtl();
							ftrTemp.setFTRRequestNo(rs.getInt("ftr_req_no"));
							ftrTemp.setFTRRequestAmount(rs.getFloat("ftr_req_amt"));
							ftrTemp.setFTRRequestType(rs.getString("ftr_req_type"));
							ftrTemp.setFTRRequestReason(rs.getString("ftr_req_reason"));
							ftrTemp.setEcibIntlBankNAme(rs.getString("ecib_intlbnk_name"));
							ftrTemp.setEcibClaimNo(rs.getString("ecib_claim_no"));
							if(rs.getDate("exp_eci_date_pym") != null)
							ftrTemp.setExpEcibDatePayment(rs.getDate("exp_eci_date_pym").toLocalDate());
							ftrTemp.setEcibType(rs.getString("ecib_type"));
							if(rs.getDate("exp_pol_date_pym") != null)
							ftrTemp.setExpPolicyDatePAyment(rs.getDate("exp_pol_date_pym").toLocalDate());
							ftrTemp.setPolicyClaimNo(rs.getString("pol_claim_no"));
							ftrTemp.setPolicyType(rs.getString("pol_type"));
							ftrTemp.setExporterName(rs.getString("expo_name"));
							ftrTemp.setIeCode(rs.getString("ie_cd"));
							ftrTemp.setLogicalLocCode(rs.getString("logical_loc_cd"));
							ftrTemp.setFTRRequestSrNo(rs.getInt("ftr_req_srno"));
							
							return ftrTemp;
						}
						});
		} catch (EmptyResultDataAccessException e) {
			ftr = null;
		}
		ftr.setFtrDtl(ftrDtl);
		return ftr;
	}

	
	public FTR findFtrById(String ftrReqNo) {

		FTR ftr;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ftrReqNo",Integer.parseInt(ftrReqNo));

		try {
			ftr = namedParameterJdbcOperations.queryForObject(FTRSqlQueries.GET_FTR_HDR, paramMap,
					new RowMapper<FTR>() {

						@Override
						public FTR mapRow(ResultSet rs, int rowNum) throws SQLException {
							FTR ftrTemp = new FTR();
							ftrTemp.setFtrReqNo(rs.getInt("ftr_req_no"));
							if(rs.getDate("ftr_req_dt") != null)
							ftrTemp.setFtrReqDt(rs.getDate("ftr_req_dt").toLocalDate());
							ftrTemp.setFtrReqBy(rs.getInt("ftr_req_by"));
							ftrTemp.setFtrReqBranchCd(rs.getString("ftr_req_branch_cd"));
							ftrTemp.setFtrReqDeptCd(rs.getString("ftr_req_dept_cd"));
							ftrTemp.setFtrReqStatus(rs.getString("ftr_req_status"));
							ftrTemp.setLogicalLocCode(rs.getString("logicalloc_cd"));
							ftrTemp.setFtrApprBy(rs.getInt("ftr_appr_by"));
							if(rs.getDate("ftr_trf_dt") != null)
							ftrTemp.setFtrTrfDt(rs.getDate("ftr_trf_dt").toLocalDate());
							ftrTemp.setFtrTrfAmt(rs.getDouble("ftr_trf_amt"));
							ftrTemp.setPvStatus(rs.getString("pv_status"));
							return ftrTemp;			
						}
					});
		} catch (EmptyResultDataAccessException e) {
			ftr = null;
		}
		return ftr;
	}
	
	@Override
	public Integer decisionOnFTRRequest(FTR ftr) {
		LOGGER.info("inside FTR Request Decision dao");
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("ftrReqNo",ftr.getFtrReqNo());
		FTRNamedParameters.put("ftrReqStatus",ftr.getFtrReqStatus());
		FTRNamedParameters.put("logicalLog",ftr.getReqTo());
		if(ftr.getMetaRemarks() != null)
			FTRNamedParameters.put("ftrReqStatus",ftr.getFtrReqStatus().charAt(0));
		FTRNamedParameters.put("metaRemarks",ftr.getMetaRemarks());
		FTRNamedParameters.put("apprBy",ftr.getFtrApprBy());
		int rowCount = namedParameterJdbcTemplate.update(FTRSqlQueries.DECISION_ON_FTR,FTRNamedParameters);
		if(rowCount < 1)
			return -1;
		return 1;
	}
	

}
