package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.TDSMaster;
import in.ecgc.smile.erp.accounts.util.TDSMasterSqlQuries;

@Repository
@Transactional
public class TDSMasterDaoImpl implements TDSMasterDao{

		
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
		
	public TDSMasterDaoImpl(DataSource dataSource) {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
	}
	@Override
	public Integer addTdsDetails(TDSMaster tdsMaster)  {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fiscalYr",tdsMaster.getFiscalYr());
		param.put("fromAmount", tdsMaster.getFromAmount());
		param.put("toAmount",tdsMaster.getToAmount());
		param.put("fromDt",tdsMaster.getFromDt());
		param.put("toDt", tdsMaster.getToDt());
		param.put("taxPer",tdsMaster.getTaxPer());
		param.put("amount", tdsMaster.getAmount());
		param.put("limitEcgc",tdsMaster.getLimitEcgc());
		param.put("surchargeAmt",tdsMaster.getSurchargeAmt());
		param.put("surchargePer",tdsMaster.getSurchargePer());
		param.put("sex",tdsMaster.getSex());
		param.put("createdDt", tdsMaster.getCreatedDt());
		return namedParameterJdbcTemplate.update(TDSMasterSqlQuries.ADD_TDS_DETAILS, param);
	}
	
	

	@Override
	public List<TDSMaster> viewAllTds() {
		List<TDSMaster> list = namedParameterJdbcTemplate.query(TDSMasterSqlQuries.LOAD_ALL_TDS, 
				new RowMapper<TDSMaster>() {
			
		@Override
		public TDSMaster mapRow(ResultSet rs, int rowNum) throws SQLException{
		
			TDSMaster tdsMaster = new TDSMaster();
			
			tdsMaster.setFiscalYr(rs.getString("fiscal_yr"));
			tdsMaster.setFromAmount(rs.getDouble("from_amount"));
			tdsMaster.setToAmount(rs.getDouble("to_amount"));
			tdsMaster.setFromDt(rs.getDate("from_dt"));
			tdsMaster.setToDt(rs.getDate("to_dt"));
			tdsMaster.setTaxPer(rs.getFloat("tax_percent"));
			tdsMaster.setCreatedBy(rs.getString("created_by"));
			tdsMaster.setCreatedDt(rs.getDate("created_dt"));
			tdsMaster.setLastUpdatedBy(rs.getString("last_updated_by"));
			tdsMaster.setLastUpdatedDt(rs.getDate("last_updated_dt"));
			tdsMaster.setAmount(rs.getDouble("amount"));
			tdsMaster.setLimitEcgc(rs.getDouble("limit_ecgc"));
			tdsMaster.setSurchargeAmt(rs.getDouble("surcharge_amt"));
			tdsMaster.setSurchargePer(rs.getFloat("surcharge_per"));
			tdsMaster.setSex(rs.getString("sex").charAt(0));
			
			return tdsMaster;
		  }
		
		});
	return list;
}
	
	@Override
	public TDSMaster checkExistingTdsDetail(Double fromAmount, Double toAmount, char sex, String fiscalYr) {
		
		TDSMaster tdsList = new TDSMaster();
		Map<String, Object> param = new HashMap<>();
		param.put("fromAmount", fromAmount);
		param.put("toAmount", toAmount);
		param.put("sex", sex);
		param.put("fiscalYr", fiscalYr);
		
		try {
			tdsList = namedParameterJdbcOperations.queryForObject(TDSMasterSqlQuries.SELECT_EXISTING_DATA,param,
				new RowMapper<TDSMaster>() {
			
		@Override
		public TDSMaster mapRow(ResultSet rs, int rowNum) throws SQLException{
		
			TDSMaster tdsMaster = new TDSMaster();
			
			tdsMaster.setFiscalYr(rs.getString("fiscal_yr"));
			tdsMaster.setFromAmount(rs.getDouble("from_amount"));
			tdsMaster.setToAmount(rs.getDouble("to_amount"));
			tdsMaster.setFromDt(rs.getDate("from_dt"));
			tdsMaster.setToDt(rs.getDate("to_dt"));
			tdsMaster.setTaxPer(rs.getFloat("tax_percent"));
			tdsMaster.setCreatedBy(rs.getString("created_by"));
			tdsMaster.setCreatedDt(rs.getDate("created_dt"));
			tdsMaster.setLastUpdatedBy(rs.getString("last_updated_by"));
			tdsMaster.setLastUpdatedDt(rs.getDate("last_updated_dt"));
			tdsMaster.setAmount(rs.getDouble("amount"));
			tdsMaster.setLimitEcgc(rs.getDouble("limit_ecgc"));
			tdsMaster.setSurchargeAmt(rs.getDouble("surcharge_amt"));
			tdsMaster.setSurchargePer(rs.getFloat("surcharge_per"));
			tdsMaster.setSex(rs.getString("sex").charAt(0));
			
			return tdsMaster;
		  }
		});
		}catch (EmptyResultDataAccessException e) {
			tdsList = null;}
		
	return tdsList;
}
}