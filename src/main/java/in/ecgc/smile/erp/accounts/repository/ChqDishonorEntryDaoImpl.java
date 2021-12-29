package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import in.ecgc.smile.erp.accounts.exception.RecordNotFoundException;

import in.ecgc.smile.erp.accounts.model.ChqDishonorEntry;

import in.ecgc.smile.erp.accounts.util.ChqDishonorEntryQueries;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
@Repository
@Slf4j
public class ChqDishonorEntryDaoImpl implements ChqDishonorEntryDao
{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	public  ChqDishonorEntryDaoImpl(DataSource dataSource)
	 {
	namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<ChqDishonorEntry> getChqDishonorEntryList(){
	log.info("inside ChqDishonorEntryDaoImpl  -  getChqDishonorEntryList()");
	List<ChqDishonorEntry> chqDishonorEntryList = new ArrayList<ChqDishonorEntry>();
	chqDishonorEntryList = namedParameterJdbcTemplate.query(ChqDishonorEntryQueries.GET_ChqDishonorEntry_DATA,
			new RowMapper<ChqDishonorEntry>() {

				@Override
				public ChqDishonorEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					ChqDishonorEntry chqDishonorEntry = new ChqDishonorEntry();
					
					chqDishonorEntry.setLogicalLocCd(rs.getString("logical_loc_cd"));
					chqDishonorEntry.setRcptNo(rs.getInt("rcpt_no"));
					if(rs.getDate("dishonor_dt") != null)
					chqDishonorEntry.setDishonorDt(rs.getDate("dishonor_dt").toLocalDate());
					chqDishonorEntry.setInstrumentNo(rs.getString("instrument_no"));
					chqDishonorEntry.setInstrumentType(rs.getString("instrument_type"));
					chqDishonorEntry.setDishonorReason(rs.getString("dishonor_reason"));
					chqDishonorEntry.setGlTxnNo(rs.getString("gl_txn_no"));
					chqDishonorEntry.setFiscalYr(rs.getString("fiscal_yr"));
					chqDishonorEntry.setCreatedBy(rs.getString("created_by"));	
					if(rs.getDate("created_dt") != null)
					chqDishonorEntry.setCreatedDt(rs.getDate("created_dt").toLocalDate());
					chqDishonorEntry.setLastUpdatedBy(rs.getString("last_updated_by"));
					if(rs.getDate("last_updated_dt") != null)
					chqDishonorEntry.setLastUpdatedDt(rs.getDate("last_updated_dt").toLocalDate());
					chqDishonorEntry.setMetaStatus(rs.getString("meta_status"));
					chqDishonorEntry.setMetaRemarks(rs.getString("meta_remarks"));
					System.err.println("chqDishonorEntry "+chqDishonorEntry);
					return chqDishonorEntry;
				}
			});
	System.err.println("chqDishonorEntryList "+chqDishonorEntryList);
	return chqDishonorEntryList;
	}
	
	
	@Override
	public boolean addChqDishonorEntryData(ChqDishonorEntry  chqDishonorEntry){
	log.info("inside ChqDishonorEntryDaoImpl  -  addChqDishonorEntryData()");
	
	try {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("logicalLocCd", chqDishonorEntry.getLogicalLocCd());
		param.put("dishonorDt", chqDishonorEntry.getDishonorDt());
		param.put("dishonorReason", chqDishonorEntry.getDishonorReason());
		param.put("rcptNo", chqDishonorEntry.getRcptNo());
		param.put("instrumentNo", chqDishonorEntry.getInstrumentNo());
		param.put("instrumentType", chqDishonorEntry.getInstrumentType());
		param.put("fiscalYr", chqDishonorEntry.getFiscalYr());
		param.put("createdBy", chqDishonorEntry.getCreatedBy());
		param.put("createdDt", chqDishonorEntry.getCreatedDt());
		
		
			int rowCount = namedParameterJdbcTemplate.update(ChqDishonorEntryQueries.ADD_ChqDishonorEntry_DATA,param);
			if(rowCount==1) {
							return true;
			}
		}catch(Exception e) {
			log.error("Exception Occured",e.getMessage());
		}
		return false;
	}
	@Override
	public boolean updateChqDishonorEntryData(ChqDishonorEntry  chqDishonorEntry){
	log.info("inside ChqDishonorEntryDaoImpl  -  updateChqDishonorEntryData()");
	try {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("logicalLocCd", chqDishonorEntry.getLogicalLocCd());
		param.put("dishonorDt", chqDishonorEntry.getDishonorDt());
		param.put("dishonorReason", chqDishonorEntry.getDishonorReason());
		param.put("rcptNo", chqDishonorEntry.getRcptNo());
		param.put("instrumentNo", chqDishonorEntry.getInstrumentNo());
		param.put("instrumentType", chqDishonorEntry.getInstrumentType());
		param.put("fiscalYr", chqDishonorEntry.getFiscalYr());
		param.put("lastUpdatedBy", chqDishonorEntry.getLastUpdatedBy());
		param.put("lastUpdatedDt", chqDishonorEntry.getLastUpdatedDt());
		
		
		int rowCount = namedParameterJdbcTemplate.update(ChqDishonorEntryQueries.UPDATE_ChqDishonorEntry_DATA,param);
			if(rowCount==1) {
							return true;
			}
		}catch(Exception e) {
			log.error("Exception Occured",e.getMessage());
		}
		return false;
	}

	@Override
	public ChqDishonorEntry getChqDishonorEntryByChequeNo(String instrumentNo) {
		ChqDishonorEntry chqDishonorEntry = new ChqDishonorEntry();
		Map<String, Object> paraMmap = new HashMap<>();
		paraMmap.put("instrumentNo", instrumentNo);
		
		
		try {
			chqDishonorEntry= namedParameterJdbcOperations.queryForObject(ChqDishonorEntryQueries.GET_ChqDishonorEntry_BY_CHEQUE_NO, paraMmap, new RowMapper<ChqDishonorEntry>() {
				@Override
				public ChqDishonorEntry mapRow(ResultSet rs, int rowNum) throws SQLException{
					ChqDishonorEntry chequeDishonorEntry = new ChqDishonorEntry();
					chequeDishonorEntry.setLogicalLocCd(rs.getString("logical_loc_cd"));
					chequeDishonorEntry.setDishonorDt(rs.getDate("dishonor_dt").toLocalDate());
					chequeDishonorEntry.setDishonorReason(rs.getString("dishonor_reason"));
					chequeDishonorEntry.setMetaRemarks(rs.getString("meta_remarks"));
					chequeDishonorEntry.setCreatedBy( rs.getString("created_by"));
					chequeDishonorEntry.setCreatedDt(rs.getDate("created_dt").toLocalDate());
					chequeDishonorEntry.setMetaStatus(rs.getString("meta_status"));
					chequeDishonorEntry.setFiscalYr(rs.getString("fiscal_yr"));
					chequeDishonorEntry.setGlTxnNo(rs.getString("gl_txn_no"));
					chequeDishonorEntry.setInstrumentNo(rs.getString("instrument_no"));
					chequeDishonorEntry.setInstrumentType(rs.getString("instrument_type"));
					chequeDishonorEntry.setLastUpdatedBy(rs.getString("last_updated_by"));
					chequeDishonorEntry.setRcptNo(rs.getInt("rcpt_no"));
					chequeDishonorEntry.setOldRcptNo(rs.getInt("old_rcpt_no"));
					return chequeDishonorEntry;
				}
			});
		} catch (Exception e) {
			chqDishonorEntry = null;	
			throw new RecordNotFoundException(); }
		return chqDishonorEntry;
	}
	
}

