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
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.PettyCashMaster;
import in.ecgc.smile.erp.accounts.model.Receipt;
import in.ecgc.smile.erp.accounts.util.GLSqlQueries;
import in.ecgc.smile.erp.accounts.util.PettyCashMasterSqlQueries;
import in.ecgc.smile.erp.accounts.util.ReceiptSqlQueries;


@Repository
@Transactional
public class PettyCashMasterDaoImpl implements PettyCashMasterDao {
	
	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(PettyCashMasterDaoImpl.class);
	
	public PettyCashMasterDaoImpl(DataSource dataSource)  {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public Integer addPettyCashDetails(PettyCashMaster pettyCashMaster) {
		
			int rowNum;
			Map<String, Object> pettyCashNamedParameter =new HashMap<String , Object>();
			try{
				pettyCashNamedParameter.put("logicalLocCode" ,pettyCashMaster.getLogicalLocCode());
				pettyCashNamedParameter.put("cashAmt",pettyCashMaster.getCashAmt());
				pettyCashNamedParameter.put("requestDt", pettyCashMaster.getRequestDt());
				pettyCashNamedParameter.put("remark",pettyCashMaster.getRemark() );
				pettyCashNamedParameter.put("requisitionNo", pettyCashMaster.getRequisitionNo());
				pettyCashNamedParameter.put("fiscalYr", pettyCashMaster.getFiscalYr());
				pettyCashNamedParameter.put("createdBy" ,pettyCashMaster.getCreatedBy());
				pettyCashNamedParameter.put("createdDt" , pettyCashMaster.getCreatedDt());
				pettyCashNamedParameter.put("entityCd", pettyCashMaster.getEntityCd());
				
				rowNum=namedParameterJdbcTemplate.update(PettyCashMasterSqlQueries.ADD_Petty_Cash_Details, pettyCashNamedParameter);
				if(rowNum != 0) {
					namedParameterJdbcOperations.update(PettyCashMasterSqlQueries.UPDATE_SEQ_TBL, pettyCashNamedParameter);				
					return 1;}
				else 
					return 0;
				
			}
			catch(DataAccessException e) {
				LOGGER.info("Exception occured while inserting new pettyCashMaster Data...",e);
				return -1; }
		}
	

	@Override
	public Integer getRequisitionNo(String logicalLocCode, String fiscalYr) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("logicalLocCode",logicalLocCode);
		param.put("fiscalYr",fiscalYr);
		Integer seq = namedParameterJdbcOperations.queryForObject(PettyCashMasterSqlQueries.GET_SEQ,param,
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
	public List<PettyCashMaster> listAllPettyCash() {
		List<PettyCashMaster> pettyCashMasters = new ArrayList<PettyCashMaster>();
		pettyCashMasters = namedParameterJdbcTemplate.query(PettyCashMasterSqlQueries.VIEW_ALL, new RowMapper<PettyCashMaster>() {
			@Override
			public PettyCashMaster mapRow(ResultSet rs, int rowNum) throws SQLException{
				PettyCashMaster pettyCashMaster = new PettyCashMaster();
				
				pettyCashMaster.setRequisitionNo(rs.getInt("requisition_no"));
				pettyCashMaster.setLogicalLocCode(rs.getString("logical_loc_cd"));
				pettyCashMaster.setCashAmt(rs.getDouble("cash_amt"));
				pettyCashMaster.setCreatedBy(rs.getString("created_by"));
				if(rs.getDate("created_dt") != null)
				pettyCashMaster.setCreatedDt(rs.getDate("created_dt").toLocalDate());
				pettyCashMaster.setLastUpdatedBy(rs.getString("last_updated_by"));
				if(rs.getDate("last_updated_dt") != null)
				pettyCashMaster.setLastUpdatedDt(rs.getDate("last_updated_dt").toLocalDate());
				pettyCashMaster.setEntityCd(rs.getString("entity_cd"));
				pettyCashMaster.setFiscalYr(rs.getString("fiscal_yr"));
				pettyCashMaster.setRemark(rs.getString("remark"));
				if(rs.getDate("request_dt") != null)
				pettyCashMaster.setRequestDt(rs.getDate("request_dt").toLocalDate());
				pettyCashMaster.setReqStatus(rs.getString("req_status"));
								
				return pettyCashMaster;				
			}
		});
		
		
		return pettyCashMasters;

	}

	@Override
	public PettyCashMaster findByRequisitionNo(Integer requisitionNo, String logicalLocCode) {
		
		PettyCashMaster pettyCash = new PettyCashMaster();
		 Map<String, Object> param = new HashMap<>();
		 param.put("requisitionNo", requisitionNo);
		 param.put("logicalLocCode", logicalLocCode);
		 
		 try {
			 pettyCash = namedParameterJdbcOperations.queryForObject(PettyCashMasterSqlQueries.FIND_BY_REQUI_NO, param, new RowMapper<PettyCashMaster>(){

				@Override
				public PettyCashMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					PettyCashMaster pettyCashMaster = new PettyCashMaster();
					
					pettyCashMaster.setRequisitionNo(rs.getInt("requisition_no"));
					pettyCashMaster.setLogicalLocCode(rs.getString("logical_loc_cd"));
					pettyCashMaster.setCashAmt(rs.getDouble("cash_amt"));
					pettyCashMaster.setCreatedBy(rs.getString("created_by"));
					pettyCashMaster.setCreatedDt(rs.getDate("created_dt").toLocalDate());
					pettyCashMaster.setEntityCd(rs.getString("entity_cd"));
					pettyCashMaster.setFiscalYr(rs.getString("fiscal_yr"));
					pettyCashMaster.setRemark(rs.getString("remark"));
					pettyCashMaster.setRequestDt(rs.getDate("request_dt").toLocalDate());
					
					return pettyCashMaster;
				}
			
			 });
		 }catch (EmptyResultDataAccessException e) {
			pettyCash = null;
		}
		return pettyCash;
	}

	@Override
	public Integer approvedStatus(PettyCashMaster pettyCashMaster) {
		int rowCount;
		Map<String, Object> param = new HashMap<String, Object>();
		
		LOGGER.info("updated=",pettyCashMaster);
		param.put("requisitionNo", pettyCashMaster.getRequisitionNo());
		param.put("logicalLocCode", pettyCashMaster.getLogicalLocCode());
		
		rowCount = namedParameterJdbcTemplate.update(PettyCashMasterSqlQueries.APPROVED_STS, param);
		
		if (rowCount >= 1)
			return 1;
		else
			return -1;
			
	}
	
}

