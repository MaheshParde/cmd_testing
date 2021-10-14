package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.PostDatedCheque;
import in.ecgc.smile.erp.accounts.util.PDCSqlQueries;

@Repository
@Transactional
public class PDCDaoImpl implements PDCDao{

	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(PDCDaoImpl.class);
	
	@Autowired
	public PDCDaoImpl(DataSource dataSource) {
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public Integer createPDCEntry(PostDatedCheque pdc) {
		
		Map<String,Object> pdcEntry = new HashMap<String,Object>();
		
		pdcEntry.put("logicalLocCode",pdc.getLogicalLocCode() );
		pdcEntry.put("receivedFromCode",pdc.getReceivedFromCode() );
		pdcEntry.put("receivedFromName",pdc.getReceivedFromName() );
		pdcEntry.put("receivedFromaddr",pdc.getReceivedFromaddr() );
		pdcEntry.put("instrumentType",pdc.getInstrumentType() );
		pdcEntry.put("instrumentNo",pdc.getInstrumentNo() );
		pdcEntry.put("instrumentDate",pdc.getInstrumentDate() );
		pdcEntry.put("instrumentAmount",pdc.getInstrumentAmount() );
		pdcEntry.put("instrumentStatus", 'n');
		pdcEntry.put("drawnOn",pdc.getDrawnOn() );
		pdcEntry.put("metaStatus",pdc.getMetaStatus() );
		pdcEntry.put("metaRemarks",pdc.getMetaRemarks() );
		pdcEntry.put("createdBy",pdc.getCreatedBy() );
		pdcEntry.put("createdDt",pdc.getCreatedDt() );
		
		return namedParameterJdbcTemplate.update(PDCSqlQueries.CREATE_PDC_ENTRY, pdcEntry);
	}

	@Override
	public List<PostDatedCheque> listAllPDC() {
		
		List<PostDatedCheque> allPDC;
		try {
			allPDC = namedParameterJdbcTemplate.query(PDCSqlQueries.VIEW_ALL_PDC, new RowMapper<PostDatedCheque>() {

				@Override
				public PostDatedCheque mapRow(ResultSet rs, int rowNum) throws SQLException {

					PostDatedCheque pdc = new PostDatedCheque();
					pdc.setSrNo(rs.getInt("sr_no"));
					pdc.setLogicalLocCode(rs.getString("logical_loc_cd"));
					pdc.setReceivedFromCode(rs.getString("rcvd_from_cd"));
					pdc.setReceivedFromName(rs.getString("rcvd_from_name"));
					pdc.setReceivedFromaddr(rs.getString("rcvd_from_addr"));
					pdc.setInstrumentType(rs.getString("instrument_type"));
					pdc.setInstrumentNo(rs.getString("instrument_no"));
					pdc.setInstrumentAmount(rs.getFloat("instrument_amount"));
					pdc.setInstrumentDate(rs.getDate("instrument_dt"));
					pdc.setInstrumentStatus(rs.getString("instrument_status").charAt(0));
					pdc.setDrawnOn(rs.getString("drawn_on"));
					pdc.setCreatedBy(rs.getString("created_by"));
					pdc.setCreatedDt(rs.getDate("created_dt"));
					pdc.setLastUpdatedBy(rs.getString("last_updated_by"));
					pdc.setLastUpdatedDt(rs.getDate("last_updated_dt"));
					//pdc.setMetaStatus(rs.getString("meta_status").charAt(0));
					pdc.setMetaRemarks(rs.getString("meta_remarks"));

					return pdc;
				}
			});
		} catch (EmptyResultDataAccessException e) {
			allPDC = null;
			LOGGER.info(e.getMessage());
		}
		return allPDC;
	}

	@Override
	public PostDatedCheque viewByChequeNo(Integer cheqId) {
		PostDatedCheque pdc;
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("sr_no", cheqId);
			pdc = namedParameterJdbcTemplate.queryForObject(PDCSqlQueries.VIEW_BY_CHQ_NO, param,
					new RowMapper<PostDatedCheque>() {

						@Override
						public PostDatedCheque mapRow(ResultSet rs, int rowNum) throws SQLException {

							PostDatedCheque pdc = new PostDatedCheque();
							pdc.setSrNo(rs.getInt("sr_no"));
							pdc.setLogicalLocCode(rs.getString("logical_loc_cd"));
							pdc.setReceivedFromCode(rs.getString("rcvd_from_cd"));
							pdc.setReceivedFromName(rs.getString("rcvd_from_name"));
							pdc.setReceivedFromaddr(rs.getString("rcvd_from_addr"));
							pdc.setInstrumentType(rs.getString("instrument_type"));
							pdc.setInstrumentNo(rs.getString("instrument_no"));
							pdc.setInstrumentAmount(rs.getFloat("instrument_amount"));
							pdc.setInstrumentDate(rs.getDate("instrument_dt"));
							pdc.setInstrumentStatus(rs.getString("instrument_status").charAt(0));
							pdc.setDrawnOn(rs.getString("drawn_on"));
							pdc.setCreatedBy(rs.getString("created_by"));
							pdc.setCreatedDt(rs.getDate("created_dt"));
							pdc.setLastUpdatedBy(rs.getString("last_updated_by"));
							pdc.setLastUpdatedDt(rs.getDate("last_updated_dt"));
							//pdc.setMetaStatus(rs.getString("meta_status").charAt(0));
							pdc.setMetaRemarks(rs.getString("meta_remarks"));

							return pdc;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			pdc = null;
			LOGGER.info(e.getMessage());
		}
		return pdc;
	}

	@Override
	public List<PostDatedCheque> viewByStatus(Character status) {
		
		List<PostDatedCheque> pdcByStatus;
		
		try {
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("instrumentStatus", status);
			pdcByStatus = namedParameterJdbcTemplate.query(PDCSqlQueries.VIEW_BY_STATUS, param,
					new RowMapper<PostDatedCheque>() {

						@Override
						public PostDatedCheque mapRow(ResultSet rs, int rowNum) throws SQLException {

							PostDatedCheque pdc = new PostDatedCheque();
							pdc.setSrNo(rs.getInt("sr_no"));
							pdc.setLogicalLocCode(rs.getString("logical_loc_cd"));
							pdc.setReceivedFromCode(rs.getString("rcvd_from_cd"));
							pdc.setReceivedFromName(rs.getString("rcvd_from_name"));
							pdc.setReceivedFromaddr(rs.getString("rcvd_from_addr"));
							pdc.setInstrumentType(rs.getString("instrument_type"));
							pdc.setInstrumentNo(rs.getString("instrument_no"));
							pdc.setInstrumentAmount(rs.getFloat("instrument_amount"));
							pdc.setInstrumentDate(rs.getDate("instrument_dt"));
							pdc.setInstrumentStatus(rs.getString("instrument_status").charAt(0));
							pdc.setDrawnOn(rs.getString("drawn_on"));
							pdc.setCreatedBy(rs.getString("created_by"));
							pdc.setCreatedDt(rs.getDate("created_dt"));
							pdc.setLastUpdatedBy(rs.getString("last_updated_by"));
							pdc.setLastUpdatedDt(rs.getDate("last_updated_dt"));
							//pdc.setMetaStatus(rs.getString("meta_status").charAt(0));
							pdc.setMetaRemarks(rs.getString("meta_remarks"));

							return pdc;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			pdcByStatus = null;
			LOGGER.info(e.getMessage());
		}
		return pdcByStatus;
	}

	@Override
	public Integer changeStatus(String chqNo, PostDatedCheque pdc) {
		
		int rowCount = 0;
		
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("instrumentStatus", pdc.getInstrumentStatus());
			param.put("instrumentNo", pdc.getInstrumentNo());
			param.put("lastUpdatedBy", pdc.getLastUpdatedBy());
			
			rowCount = namedParameterJdbcTemplate.update(PDCSqlQueries.CHANGE_STATUS, param);
			
		} catch (DataAccessException e) {
			LOGGER.info(e.getMessage());
		}
		return rowCount;
	}

	@Override
	public PostDatedCheque checkUnique(String chequeNo, String chequeDate) {
		PostDatedCheque pdc;
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("instrumentNo", chequeNo);
			param.put("instrument_dt", chequeDate);
			pdc = namedParameterJdbcTemplate.queryForObject(PDCSqlQueries.CHECK_UNIQUE_ENTRY, param,
					new RowMapper<PostDatedCheque>() {

						@Override
						public PostDatedCheque mapRow(ResultSet rs, int rowNum) throws SQLException {

							PostDatedCheque pdc = new PostDatedCheque();
							pdc.setSrNo(rs.getInt("sr_no"));
							pdc.setLogicalLocCode(rs.getString("logical_loc_cd"));
							pdc.setReceivedFromCode(rs.getString("rcvd_from_cd"));
							pdc.setReceivedFromName(rs.getString("rcvd_from_name"));
							pdc.setReceivedFromaddr(rs.getString("rcvd_from_addr"));
							pdc.setInstrumentType(rs.getString("instrument_type"));
							pdc.setInstrumentNo(rs.getString("instrument_no"));
							pdc.setInstrumentAmount(rs.getFloat("instrument_amount"));
							pdc.setInstrumentDate(rs.getDate("instrument_dt"));
							pdc.setInstrumentStatus(rs.getString("instrument_status").charAt(0));
							pdc.setDrawnOn(rs.getString("drawn_on"));
							pdc.setCreatedBy(rs.getString("created_by"));
							pdc.setCreatedDt(rs.getDate("created_dt"));
							pdc.setLastUpdatedBy(rs.getString("last_updated_by"));
							pdc.setLastUpdatedDt(rs.getDate("last_updated_dt"));
							//pdc.setMetaStatus(rs.getString("meta_status").charAt(0));
							pdc.setMetaRemarks(rs.getString("meta_remarks"));

							return pdc;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			pdc = null;
			LOGGER.info(e.getMessage());
		}
		return pdc;
	}

}
