package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet ;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.util.CalendarSqlQueries;

@Repository
@Transactional
public class CalendarDaoImpl  implements CalendarDao {
	
	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(CalendarDaoImpl.class);
	
	@Autowired
	public CalendarDaoImpl(DataSource dataSource) {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	

	@Override
	public Integer addCalendar(Calendar calendar) {
		calendar.setEcgcStatus('V');
		Map<String, Object> calendarNamedParameters = new HashMap<String, Object>();
		calendarNamedParameters.put("calendarID", calendar.getCalendarId());
		calendarNamedParameters.put("branchCd", calendar.getBranchCode());
		calendarNamedParameters.put("logicalLocCd", calendar.getLogicalLocCode());
		calendarNamedParameters.put("glTxnType", calendar.getGlTxnType());
		calendarNamedParameters.put("txnTypeName", calendar.getTxnTypeName());
		calendarNamedParameters.put("fiscalYear", calendar.getFiscalYear());
		calendarNamedParameters.put("month", calendar.getMonth());
		calendarNamedParameters.put("closedStatus","y");
		calendarNamedParameters.put("ecgcStatus",calendar.getEcgcStatus());
		calendarNamedParameters.put("updatedBy",calendar.getUpdatedBy());
		calendarNamedParameters.put("updatedOn",calendar.getUpdatedOn());
		calendarNamedParameters.put("ecgcStatus",calendar.getEcgcStatus());
		calendarNamedParameters.put("metaRemarks",calendar.getMetaRemarks());
		calendarNamedParameters.put("createdOn",calendar.getCreatedOn());
		calendarNamedParameters.put("createdBy",calendar.getCreatedBy());
		int rowCount = namedParameterJdbcTemplate.update(CalendarSqlQueries.ADD_CALENDAR,calendarNamedParameters);
		if(rowCount==1)
		return 1;
		return -1;

	}

	@Override
	public Calendar getCalendar(String calendarId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("calendarId",calendarId);
		Calendar calendar = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.GET_CALENDAR,paramMap , new RowMapper<Calendar>() {
			
			@Override
			public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
				LOGGER.info("inside row Mapper");
				Calendar calendar = new Calendar();
				calendar.setCalendarId(rs.getString("calendar_id"));
				calendar.setBranchCode(rs.getString("branch_cd"));
				calendar.setLogicalLocCode(rs.getString("logical_loc_cd"));
				calendar.setFiscalYear(rs.getString("fiscal_yr"));
				calendar.setMonth(rs.getString("month"));
				calendar.setUpdatedBy(rs.getString("last_updated_by"));
				calendar.setUpdatedOn(rs.getTimestamp("last_updated_dt"));
				calendar.setGlTxnType(rs.getString("gl_txn_type"));
				calendar.setTxnTypeName(rs.getString("txn_type_name"));
				calendar.setConfigFlag(rs.getString("config_flag").charAt(0));
				if(rs.getString("closed_status") !=null)
					calendar.setClosedStatus(rs.getString("closed_status").charAt(0));

				if(rs.getString("meta_status") !=null)
					calendar.setEcgcStatus(rs.getString("meta_status").charAt(0));
				calendar.setMetaRemarks(rs.getString("meta_remarks"));
				calendar.setCreatedOn(rs.getTimestamp("created_dt"));
				calendar.setCreatedBy(rs.getString("created_by"));
				return calendar;
			}
		});
		if(calendar == null)
	return null;
		return calendar;

	}

	@Override
	public List<Calendar> getAllCalendar(String fiscalYr, String month) {
		
		List<Calendar> calendarList;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fiscalYr", fiscalYr);
		paramMap.put("month", month);
		calendarList = namedParameterJdbcOperations.query(CalendarSqlQueries.GET_CALENDAR_FOR_MONTH,paramMap,
				
				new RowMapper<Calendar>() {

					@Override
					public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {

						Calendar calendar = new Calendar();
						calendar.setCalendarId(rs.getString("calendar_id"));
						calendar.setBranchCode(rs.getString("branch_cd"));
						calendar.setLogicalLocCode(rs.getString("logical_loc_cd"));
						calendar.setFiscalYear(rs.getString("fiscal_yr"));
						calendar.setMonth(rs.getString("month"));
						calendar.setGlTxnType(rs.getString("gl_txn_type"));
						calendar.setTxnTypeName(rs.getString("txn_type_name"));
						calendar.setConfigFlag(rs.getString("config_flag").charAt(0));
						calendar.setUpdatedBy(rs.getString("last_updated_by"));
						calendar.setUpdatedOn(rs.getTimestamp("last_updated_dt"));
						if(rs.getString("closed_status") !=null)
							calendar.setClosedStatus(rs.getString("closed_status").charAt(0));

						if(rs.getString("meta_status") !=null)
							calendar.setEcgcStatus(rs.getString("meta_status").charAt(0));
						calendar.setMetaRemarks(rs.getString("meta_remarks"));
						calendar.setCreatedOn(rs.getTimestamp("created_dt"));
						calendar.setCreatedBy(rs.getString("created_by"));
						return calendar;
					}
				});
		return calendarList;	
		
	}

	@Override
	public Integer deleteCalendar(String calendarId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Calendar> getByFiscalYr(String fiscalYr, String month, String logicalLocCode) {
		
		
		List<Calendar> calendarList;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fiscalYr", fiscalYr);
		paramMap.put("month", month);
		paramMap.put("logicalLocCode", logicalLocCode);
		
		LOGGER.info("before get Calendar");
		LOGGER.info(fiscalYr+"  " + month+ " " + logicalLocCode);
		
		calendarList = namedParameterJdbcOperations.query(CalendarSqlQueries.GET_CALENDAR_FOR_MONTH_LOGICAL_LOC,paramMap,
				
				new RowMapper<Calendar>() {

					
					@Override
					public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
						Calendar calendar = new Calendar();
						calendar.setCalendarId(rs.getString("calendar_id"));
						calendar.setBranchCode(rs.getString("branch_cd"));
						calendar.setLogicalLocCode(rs.getString("logical_loc_cd"));
						calendar.setFiscalYear(rs.getString("fiscal_yr"));
						calendar.setMonth(rs.getString("month"));
						calendar.setGlTxnType(rs.getString("gl_txn_type"));
						calendar.setTxnTypeName(rs.getString("txn_type_name"));
						calendar.setConfigFlag(rs.getString("config_flag").charAt(0));
						calendar.setUpdatedBy(rs.getString("last_updated_by"));
						calendar.setUpdatedOn(rs.getTimestamp("last_updated_dt"));
						if(rs.getString("closed_status") !=null)
							calendar.setClosedStatus(rs.getString("closed_status").charAt(0));

						if(rs.getString("meta_status") !=null)
							calendar.setEcgcStatus(rs.getString("meta_status").charAt(0));
						calendar.setMetaRemarks(rs.getString("meta_remarks"));
						calendar.setCreatedOn(rs.getTimestamp("created_dt"));
						calendar.setCreatedBy(rs.getString("created_by"));
						return calendar;
					}
				});
		return calendarList;
	}


	@Override
	public List<Calendar> getPrevFiscalYr(String fiscalYr, String logicalLocCode) {
		return null;
	}


	@Override
	public Integer updateStatus1(String first, String status) {
		
		Integer count = 0;
		
		Map<String, String> updateParam = new HashMap<>();
		updateParam.put("first", first);
		updateParam.put("status", status);
		
		LOGGER.info("Inside dao1  First : "+first+"status :"+status);
		
		count = namedParameterJdbcTemplate.update(CalendarSqlQueries.Update_Status_1, updateParam);
		
		return count;
	}


	@Override
	public Integer updateStatus2(String second, String status) {
		Integer count = 0;
		
		Map<String, String> updateParam = new HashMap<>();
		updateParam.put("second", second);
		updateParam.put("status", status);
		count = namedParameterJdbcTemplate.update(CalendarSqlQueries.Update_Status_2, updateParam);
		
		return count;
	}


	@Override
	public Integer updatePrev(String prevyr, String status) {
		
		Integer count = 0;
		
		Map<String, String> updateParam = new HashMap<>();
		updateParam.put("prevyr", prevyr);
		updateParam.put("status", status);
		
		try {
			count = namedParameterJdbcTemplate.update(CalendarSqlQueries.Update_last_yr, updateParam);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	@Override
	public Integer openPrev(String logicalLoc, String glTxnType, String status , String month, String fiscalYr) {
		
		Integer count = 0;
		Map<String, String> updateParam = new HashMap<>();
		updateParam.put("glTxnType", glTxnType);
		updateParam.put("month", month);
		updateParam.put("fiscalYr", fiscalYr);
		updateParam.put("logicalLoc", logicalLoc);
		updateParam.put("status", status);
		
		
		count = namedParameterJdbcTemplate.update(CalendarSqlQueries.Update, updateParam);
		return count;
	}


	@Override
	public Integer monthlyOpening(String logicalCode,String currMonth,String currentFiscalyr) {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("logicalCode",logicalCode);
		FTRNamedParameters.put("currentMonth",currMonth);
		FTRNamedParameters.put("currentFiscalYr",currentFiscalyr);
		
		Integer result = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.MONTHLY_OPENING,FTRNamedParameters,
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						int num =rs.getInt(1);
						return num;
					}
				});
		return result;
	}


	@Override
	public Integer dailyClosing(String currMonth,String currentFiscalyr,String logicalCode) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("currentMonth",currMonth);
		parameters.put("currentFiscalyr",currentFiscalyr);
		parameters.put("logicalCode",logicalCode);
		Integer result = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.MONTHLY_CLOSING,parameters,
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						int num =rs.getInt(1);
						return num;
					}
				});
		return result;
	}
	@Override
	public Integer dailyOpening(String currMonth,String currentFiscalyr,String logicalCode) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("currentMonth",currMonth);
		parameters.put("currentFiscalyr",currentFiscalyr);
		parameters.put("logicalCode",logicalCode);
		Integer result = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.OPENING,parameters,
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						int num =rs.getInt(1);
						return num;
					}
				});
		return result;
	}
	
	@Override
	public Calendar getByGlTypeLogicalLoc(String fiscalYr, String month, String logicalLocCode,String glTxnType) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fiscalYr",fiscalYr);
		paramMap.put("month",month);
		paramMap.put("logicalLocCode",logicalLocCode);
		paramMap.put("glTxnType",glTxnType);
		System.err.println(paramMap);
		Calendar calendar = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.GET_CALENDAR_BY_GL_MON_LOGICALLOC,paramMap , new RowMapper<Calendar>() {
			
			@Override
			public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
				LOGGER.info("inside row Mapper");
				Calendar calendar = new Calendar();
				calendar.setCalendarId(rs.getString("calendar_id"));
				calendar.setBranchCode(rs.getString("branch_cd"));
				calendar.setLogicalLocCode(rs.getString("logical_loc_cd"));
				calendar.setFiscalYear(rs.getString("fiscal_yr"));
				calendar.setMonth(rs.getString("month"));
				calendar.setUpdatedBy(rs.getString("last_updated_by"));
				calendar.setUpdatedOn(rs.getTimestamp("last_updated_dt"));
				calendar.setGlTxnType(rs.getString("gl_txn_type"));
				calendar.setTxnTypeName(rs.getString("txn_type_name"));
				if(rs.getString("config_flag") != null)
				calendar.setConfigFlag(rs.getString("config_flag").charAt(0));
				if(rs.getString("closed_status") !=null)
					calendar.setClosedStatus(rs.getString("closed_status").charAt(0));

				if(rs.getString("meta_status") !=null)
					calendar.setEcgcStatus(rs.getString("meta_status").charAt(0));
				calendar.setMetaRemarks(rs.getString("meta_remarks"));
				calendar.setCreatedOn(rs.getTimestamp("created_dt"));
				calendar.setCreatedBy(rs.getString("created_by"));
				return calendar;
			}
		});
		if(calendar == null)
	return null;
		return calendar;

	}



	
	
}
