package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet ;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.exception.CalendarRecordInsertException;
import in.ecgc.smile.erp.accounts.exception.CalendarRecordNotFoundException;
import in.ecgc.smile.erp.accounts.exception.CalendarRecordupdateException;
import in.ecgc.smile.erp.accounts.exception.ConfiguredClosingException;
import in.ecgc.smile.erp.accounts.exception.ConfiguredOpenException;
import in.ecgc.smile.erp.accounts.exception.PreviousMonthopeningException;
import in.ecgc.smile.erp.accounts.exception.RegularClosingException;
import in.ecgc.smile.erp.accounts.exception.RegularOpeningException;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.util.CalendarSqlQueries;
import in.ecgc.smile.erp.sys.auth.be.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
public class CalendarDaoImpl  implements CalendarDao {
	
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(CalendarDaoImpl.class);
	
	@Autowired
	public CalendarDaoImpl(DataSource dataSource) {
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Autowired
	UserInfoService userInfoService;


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

		
		int rowCount;
		try {
			rowCount = namedParameterJdbcTemplate.update(CalendarSqlQueries.ADD_CALENDAR,calendarNamedParameters);
		} catch (Exception e) {
			log.error("Failed to insert calendar record");
			log.error("Exception in CalendarDaoImpl#addCalendar",e.fillInStackTrace());
			throw new CalendarRecordInsertException("Failed to insert calendar record" + e.getMessage());
		}
		
		if(rowCount==1)
			return 1;
		return 0;

	}

	@Override
	public Calendar getCalendar(String calendarId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("calendarId",calendarId);
		
		
		Calendar calendar = null;
		try {
			calendar = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.GET_CALENDAR,paramMap , new RowMapper<Calendar>() {
				@Override
				public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException{
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
					calendar.setConfigFlag(rs.getBoolean("config_flag"));
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
		} catch(Exception e) {
			log.error("Failed to fetch calendar record for calendar Id : {}",calendarId);
			log.error("Exception in CalendarDaoImpl#getCalendar",e.fillInStackTrace());
			throw new CalendarRecordNotFoundException("Failed to fetch calendat record for calendar Id : "+calendarId+"" + e.getMessage());
		}
		
		
		if(calendar == null)
				return null;
		return calendar;

	}

	@Override
	public List<Calendar> getAllCalendar(String fiscalYr, String month) {
		
		List<Calendar> calendarList = new ArrayList<Calendar>() ;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fiscalYr", fiscalYr);
		paramMap.put("month", month);
		
		
		try {
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
							calendar.setConfigFlag(rs.getBoolean("config_flag"));
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
		}catch (Exception e) {
			log.error("Failed to fetch calendar record for FiscalYr : {} and Month : {}",fiscalYr,month);
			log.error("Exception in CalendarDaoImpl#getCalendar",e.fillInStackTrace());
			throw new CalendarRecordNotFoundException("Failed to fetch calendar record for FiscalYr : "+fiscalYr+" and Month :"+month+""+ e.getMessage());
		}
		
		if(calendarList.isEmpty()) {
			log.error("Failed to fetch calendar record for FiscalYr : {} and Month : {}",fiscalYr,month);
			log.error("Exception in CalendarDaoImpl#getCalendar");
			throw new CalendarRecordNotFoundException("Failed to fetch calendar record for FiscalYr : "+fiscalYr+" and Month :"+month);
		}else {
			return calendarList;
		}
		
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
		
		try {
			calendarList = namedParameterJdbcTemplate.query(CalendarSqlQueries.GET_CALENDAR_FOR_MONTH_LOGICAL_LOC,paramMap,
					
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
							calendar.setConfigFlag(rs.getBoolean("config_flag"));
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
		}catch (Exception e) {
			log.error("Failed to fetch calendar record for FiscalYr : {} and Month : {}, and logicalLocCd : {} ",fiscalYr,month,logicalLocCode);
			log.error("Exception in CalendarDaoImpl#getByFiscalYr",e.fillInStackTrace());
			throw new CalendarRecordNotFoundException("Failed to fetch calendar record for FiscalYr : "+fiscalYr+" and Month :"+month+" and logicalLocCd : "+logicalLocCode+""+ e.getMessage());
		}
		if(calendarList.isEmpty()) {
			log.error("Failed to fetch calendar record for FiscalYr : {} and Month : {}, and logicalLocCd : {} ",fiscalYr,month,logicalLocCode);
			log.error("Exception in CalendarDaoImpl#getByFiscalYr");
			throw new CalendarRecordNotFoundException("Failed to fetch calendar record for FiscalYr : "+fiscalYr+" and Month :"+month+" and logicalLocCd : "+logicalLocCode);

		}else {
			return calendarList;
		}
		
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
		
		try {
			count = namedParameterJdbcTemplate.update(CalendarSqlQueries.Update_Status_1, updateParam);
		} catch (Exception e) {
			log.error("Failed to update calendar status for Id : {} ",first);
			log.error("Exception in CalendarDaoImpl#updateStatus1",e.fillInStackTrace());
			throw new CalendarRecordupdateException("Failed to update calendar status for Id : "+first+""+ e.getMessage());
		}
		
		return count;
	}


	@Override
	public Integer updateStatus2(String second, String status) {
		Integer count = 0;
		
		Map<String, String> updateParam = new HashMap<>();
		updateParam.put("second", second);
		updateParam.put("status", status);
		
		try {
			count = namedParameterJdbcTemplate.update(CalendarSqlQueries.Update_Status_2, updateParam);
		} catch (Exception e) {
			log.error("Failed to update calendar status for Id : {} ",second);
			log.error("Exception in CalendarDaoImpl#updateStatus2",e.fillInStackTrace());
			throw new CalendarRecordupdateException("Failed to update calendar status for Id : "+second+""+ e.getMessage());
		}
		
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
		} catch (Exception e) {
			log.error("Failed to update calendar status for Id : {} ",prevyr);
			log.error("Exception in CalendarDaoImpl#updatePrev",e.fillInStackTrace());
			throw new CalendarRecordupdateException("Failed to update calendar status for Id : "+prevyr+""+ e.getMessage());
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
		
		
		try {
			count = namedParameterJdbcTemplate.update(CalendarSqlQueries.Update, updateParam);
		} catch (Exception e) {
			log.error("Failed to open previous month calendar for logicalLoc: {}, glTranxType : {}, status : {}, month : {}, year : {} ",logicalLoc,glTxnType,status,month,fiscalYr);
			log.error("Exception in CalendarDaoImpl#openPrev",e.fillInStackTrace());
			throw new PreviousMonthopeningException("Failed to open previous month calendar for logicalLoc: "+logicalLoc+", glTranxType : "+glTxnType+", status : "+status+", month : "+month+", year : "+fiscalYr+" "+ e.getMessage());
		}
		return count;
	}


	@Override
	public Integer monthlyOpeningRegular(String logicalCode,String currMonth,String currentFiscalyr) {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("logicalCode",logicalCode);
		FTRNamedParameters.put("currentMonth",currMonth);
		FTRNamedParameters.put("currentFiscalYr",currentFiscalyr);
		
		Integer result;
		try {
			result = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.MONTHLY_OPENING_REGULAR,FTRNamedParameters,
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							int num =rs.getInt(1);
							return num;
						}
					});
		} catch (Exception e) {
			log.error("Failed to open regular month calendar for logicalLoc: {}, month : {}, year : {} ",logicalCode,currMonth,currentFiscalyr);
			log.error("Exception in CalendarDaoImpl#monthlyOpeningRegular",e.fillInStackTrace());
			throw new RegularOpeningException("Failed to open regular month calendar for logicalLoc: "+logicalCode+",  month : "+currMonth+", year : "+currentFiscalyr+" "+ e.getMessage());
		}
		return result;
	}

	@Override
	public Integer monthlyClosingRegular(String logicalCode, String prevMonth, String currentFiscalyr) {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("logicalCode",logicalCode);
		FTRNamedParameters.put("prevMonth",prevMonth);
		FTRNamedParameters.put("currentFiscalYr",currentFiscalyr);
		
		Integer result;
		try {
			result = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.MONTHLY_CLOSING_REGULAR,FTRNamedParameters,
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							int num =rs.getInt(1);
							return num;
						}
					});
		} catch (Exception e) {
			log.error("Failed to close regular month calendar for logicalLoc: {}, month : {}, year : {} ",logicalCode,prevMonth,currentFiscalyr);
			log.error("Exception in CalendarDaoImpl#monthlyClosingRegular",e.fillInStackTrace());
			throw new RegularClosingException("Failed to close regular month calendar for logicalLoc: "+logicalCode+",  month : "+prevMonth+", year : "+currentFiscalyr+" "+ e.getMessage());
		}
		return result;

	}

	@Override
	public Integer monthlyOpeningConfigured(String logicalCode, String currMonth, String currentFiscalyr) {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("logicalCode",logicalCode);
		FTRNamedParameters.put("currentMonth",currMonth);
		FTRNamedParameters.put("currentFiscalYr",currentFiscalyr);
		
		Integer result;
		try {
			result = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.MONTHLY_OPENING_CONFIGURED,FTRNamedParameters,
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							int num =rs.getInt(1);
							return num;
						}
					});
		} catch (Exception e) {
			log.error("Failed to open configured month calendar for logicalLoc: {}, month : {}, year : {} ",logicalCode,currMonth,currentFiscalyr);
			log.error("Exception in CalendarDaoImpl#monthlyOpeningConfigured",e.fillInStackTrace());
			throw new ConfiguredOpenException("Failed to open configured month calendar for logicalLoc: "+logicalCode+",  month : "+currMonth+", year : "+currentFiscalyr+" "+ e.getMessage());
		}
		return result;
	}


	@Override
	public Integer monthlyClosingConfigured(String logicalCode, String prevMonth, String currentFiscalyr) {
		Map<String, Object> FTRNamedParameters = new HashMap<String, Object>();
		FTRNamedParameters.put("logicalCode",logicalCode);
		FTRNamedParameters.put("prevMonth",prevMonth);
		FTRNamedParameters.put("currentFiscalYr",currentFiscalyr);
		
		Integer result;
		try {
			result = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.MONTHLY_CLOSING_CONFIGURED,FTRNamedParameters,
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							int num =rs.getInt(1);
							return num;
						}
					});
		} catch (Exception e) {
			log.error("Failed to close configured month calendar for logicalLoc: {}, month : {}, year : {} ",logicalCode,prevMonth,currentFiscalyr);
			log.error("Exception in CalendarDaoImpl#monthlyClosingRegular",e.fillInStackTrace());
			throw new ConfiguredClosingException("Failed to close configured month calendar for logicalLoc: "+logicalCode+",  month : "+prevMonth+", year : "+currentFiscalyr+" "+ e.getMessage());
		}
		return result;

	}


	@Override
	public Integer dailyClosing(String currMonth,String currentFiscalyr,String logicalCode) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("currentMonth",currMonth);
		parameters.put("currentFiscalyr",currentFiscalyr);
		parameters.put("logicalCode",logicalCode);
	
		Integer result;
		try {
			result = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.MONTHLY_CLOSING_CONFIGURED,parameters,
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							int num =rs.getInt(1);
							return num;
						}
					});
		} catch (Exception e) {
			log.error("Failed to close configured month calendar for logicalLoc: {}, month : {}, year : {} ",logicalCode,currMonth,currentFiscalyr);
			log.error("Exception in CalendarDaoImpl#dailyClosing",e.fillInStackTrace());
			throw new ConfiguredClosingException("Failed to close configured month calendar for logicalLoc: "+logicalCode+",  month : "+currMonth+", year : "+currentFiscalyr+" "+ e.getMessage());
		}
		return result;
	}
	@Override
	public Integer dailyOpening(String currMonth,String currentFiscalyr,String logicalCode) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("currentMonth",currMonth);
		parameters.put("currentFiscalyr",currentFiscalyr);
		parameters.put("logicalCode",logicalCode);
		Integer result;
		try {
			result = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.OPENING,parameters,
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							int num =rs.getInt(1);
							return num;
						}
					});
		} catch (Exception e) {
			log.error("Failed to open configured month calendar for logicalLoc: {}, month : {}, year : {} ",logicalCode,currMonth,currentFiscalyr);
			log.error("Exception in CalendarDaoImpl#dailyOpening",e.fillInStackTrace());
			throw new ConfiguredOpenException("Failed to open configured month calendar for logicalLoc: "+logicalCode+",  month : "+currMonth+", year : "+currentFiscalyr+" "+ e.getMessage());
		}
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
	
		Calendar calendar =  new Calendar();
		try {
			calendar = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.GET_CALENDAR_BY_GL_MON_LOGICALLOC,paramMap , new RowMapper<Calendar>() {
				
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
						calendar.setConfigFlag(rs.getBoolean("config_flag"));
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
		} catch (Exception e) {
		//	calendar =null;
			log.info("Failed to fetch calendar record for FiscalYr : {} and Month : {}, and logicalLocCd : {}, and GlTxnType ",fiscalYr,month,logicalLocCode,glTxnType);
			log.info("Exception in CalendarDaoImpl#getByGlTypeLogicalLoc",e.fillInStackTrace());
			log.error("calander in exception is :: {} ",calendar);
			throw new CalendarRecordNotFoundException("Failed to fetch calendar record for FiscalYr : "+fiscalYr+" and Month :"+month+" and logicalLocCd : "+logicalLocCode+" and Gl TXN type : "+glTxnType+" " +e.getMessage());
		}
		
		if(calendar == null)
			return null;
		return calendar;

	}

	@Override
	public Integer marchClosing(String logicalCode, String currentFiscalyr) {
		LOGGER.info("Dao Parameter logicalCode {}   ",logicalCode);
		LOGGER.info("Dao Parameter currentFiscalyr {}   ",currentFiscalyr);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("logicalLocCd",logicalCode);
		paraMap.put("fiscalYear",currentFiscalyr);
		paraMap.put("lastupdatedBy", userInfoService.getUser());
//		paraMap.put("lastupdatedBy", "1229");
		Integer result = null;
		try {
			result = namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.CLOSE_MARCH_CALENDAR,paraMap,
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							int num =rs.getInt(1);
							return num;
						}
					});
			
		} catch (Exception e) {
			log.error("Failed to close march calendar for logicalLoc: {}, year : {} ",logicalCode,currentFiscalyr);
			log.error("Exception in CalendarDaoImpl#monthlyClosingRegular",e.fillInStackTrace());
			throw new RegularClosingException("Failed to close march calendar for logicalLoc: "+logicalCode+",  year : "+currentFiscalyr+" "+ e.getMessage());
		}
		return result;

	}



	@Override
	public Boolean marchClosingStatus(String logicalCode, String currentFiscalyr) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fiscalYr",currentFiscalyr);
		parameters.put("logicalCode",logicalCode);
	
		
		try {
		namedParameterJdbcOperations.queryForObject(CalendarSqlQueries.GET_MARCH_CALENDAR_STATUS,parameters,
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							int num =rs.getInt(1);
							return num;
						}
					});
		} catch (Exception e) {
			log.error("Failed to get march calendar status for logicalLoc: {},  year : {} ",logicalCode,currentFiscalyr);
			log.error("Exception in CalendarDaoImpl#marchClosingStatus",e.fillInStackTrace());
	//		throw new ConfiguredClosingException("Failed to get march calendar status for logicalLoc: "+logicalCode+", year : "+currentFiscalyr+" "+ e.getMessage());
			return false;
		}
			return true;
	}

}
