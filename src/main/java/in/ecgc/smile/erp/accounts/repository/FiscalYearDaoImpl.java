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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
import in.ecgc.smile.erp.accounts.model.GLTxnType;
import in.ecgc.smile.erp.accounts.util.FiscalYearSqlQueries;
import in.ecgc.smile.erp.accounts.util.GLTxnTypeSqlQueries;

/**
 * Fiscal year DAO implementation
 * 
 * @version 1.0 15-September-2020
 * @author Sanjali Kesarkar
 *
 */
@Repository
@Transactional
public class FiscalYearDaoImpl implements FiscalYearDao {
	
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(FiscalYearDaoImpl.class);

	@Autowired
	public FiscalYearDaoImpl(DataSource dataSource) {
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	/**
	 * Find details of current fiscal year
	 * 
	 * DAO implementation to find details of current fiscal year present
	 * in the database Table :acct_fiscal_yr returns model FiscalYearModel
	 * {@link in.ecgc.smile.erp.accounts.model.FiscalYearModel}
	 */
	@Override
	public FiscalYearModel findCurrentFiscalYear() {
		FiscalYearModel fiscalYearModel;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", 'V');
		try {
			fiscalYearModel = namedParameterJdbcOperations.queryForObject(FiscalYearSqlQueries.GET_CURRENT_FISCAL_YEAR,
					paramMap,
					new RowMapper<FiscalYearModel>() {

						@Override
						public FiscalYearModel mapRow(ResultSet rs, int rowNum) throws SQLException {
							FiscalYearModel fiscalYearModel = new FiscalYearModel();

							fiscalYearModel.setCurrFiscalYear(rs.getString("curr_fisc_yr"));
							fiscalYearModel.setCurrFiscalYearStartDt(rs.getDate("curr_fisc_yr_start_dt").toLocalDate());
							fiscalYearModel.setPrevFiscalYear(rs.getString("prev_fisc_yr"));
							fiscalYearModel.setPrevFiscalYearClosedDt(rs.getDate("prev_fisc_yr_closed_dt").toLocalDate());
							fiscalYearModel.setMetaStatus(rs.getString("meta_status").charAt(0));
							fiscalYearModel.setCreatedBy(rs.getString("created_by"));
							fiscalYearModel.setCreatedDt(rs.getDate("created_dt"));
							fiscalYearModel.setLastUpdatedBy(rs.getString("last_updated_by"));
							fiscalYearModel.setLastUpdatedDt(rs.getDate("last_updated_dt"));
							fiscalYearModel.setMetaRemarks(rs.getString("meta_remarks"));
							return fiscalYearModel;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			fiscalYearModel = null;
		}
		return fiscalYearModel;
	}
	/**
	 * Get all fiscal years
	 * 
	 * DAO implementation to get all fiscal years present in the
	 * database Table :acct_fiscal_yr returns list of model FiscalYearModel
	 * {@link in.ecgc.smile.erp.accounts.model.FiscalYearModel}
	 */
	@Override
	public List<String> getFiscalYearList() throws DataAccessException {
		List<String> fiscalYearList = new ArrayList<String>();
		fiscalYearList = namedParameterJdbcTemplate.query(FiscalYearSqlQueries.ALL_FISCAL_YR,
				new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {

						return rs.getString("curr_fisc_yr");

					}
				});
		return fiscalYearList;
	}
}
