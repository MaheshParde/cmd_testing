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
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.Schedule;
import in.ecgc.smile.erp.accounts.util.ScheduleQueries;

@Repository
@Transactional
public class ScheduleDaoImpl implements ScheduleDao {

	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleDaoImpl.class);

	@Autowired
	public ScheduleDaoImpl(DataSource dataSource) {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	
	
	@Override
	public Integer addSchedule(Schedule schedule) {
		schedule.setEcgcStatus('V');
		Map<String, Object> scheduleNamedParameters = new HashMap<String, Object>();
		scheduleNamedParameters.put("scheduleCode", schedule.getScheduleCode());
		scheduleNamedParameters.put("scheduleItemCode", schedule.getScheduleItemCode());
		scheduleNamedParameters.put("description", schedule.getDescription());
		if(schedule.isTitelDetailLine())
		scheduleNamedParameters.put("titelDetailLine","Y");
		else
			scheduleNamedParameters.put("titelDetailLine","N");
		scheduleNamedParameters.put("updatedBy",schedule.getUpdatedBy());
		scheduleNamedParameters.put("updatedOn",schedule.getUpdatedOn());
		scheduleNamedParameters.put("ecgcStatus",schedule.getEcgcStatus());
		scheduleNamedParameters.put("metaRemark",schedule.getMetaRemark());
		scheduleNamedParameters.put("createdOn",schedule.getCreatedOn());
		scheduleNamedParameters.put("createdBy",schedule.getCreatedBy());
		scheduleNamedParameters.put("srNo",schedule.getSrNo());
		scheduleNamedParameters.put("prifix",schedule.getPrifix());
		scheduleNamedParameters.put("total",schedule.getTotal());
		int rowCount = namedParameterJdbcTemplate.update(ScheduleQueries.ADD_SCHEDULE,scheduleNamedParameters);
		if(rowCount==1)
		return 1;
		return -1;
	}

	@Override
	public Schedule getSchedule(String schedule_cd) {
			LOGGER.info("inside dao "+ schedule_cd);
			List<Schedule> schedule = jdbcOperations.query(ScheduleQueries.GET_SCHEDULE_BY_SCHEDULE_CD1,new Object[] { schedule_cd } , new RowMapper<Schedule>() {
			
				@Override
				public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
					LOGGER.info("inside row mapper");
					Schedule schedule = new Schedule();
					schedule.setScheduleCode(rs.getString("schedule_cd"));
					schedule.setScheduleItemCode(rs.getString("schedule_item_cd"));
					schedule.setDescription(rs.getString("description"));
					if(rs.getString("title_detail_line")=="Y")
						schedule.setTitelDetailLine(true);
					else
						schedule.setTitelDetailLine(false);
					schedule.setUpdatedBy(rs.getString("updated_by"));
					schedule.setUpdatedOn(rs.getTimestamp("updated_on"));
					if(rs.getString("ecgc_status") !=null)
						schedule.setEcgcStatus(rs.getString("ecgc_status").charAt(0));
					schedule.setMetaRemark(rs.getString("meta_remarks"));
					schedule.setCreatedOn(rs.getTimestamp("created_on"));
					schedule.setCreatedBy(rs.getString("created_by"));
					schedule.setSrNo(rs.getInt("sr_no"));
					schedule.setPrifix(rs.getString("prefix"));
					schedule.setTotal(rs.getString("total"));
					return schedule;
				}
			});
			if(schedule.isEmpty())
		return null;
			return schedule.get(0);
	}

	@Override
	public List<Schedule> getAllSchedule() {
			List<Schedule> allSchedule = jdbcOperations.query(ScheduleQueries.GET_ALL_SCHEDULE, new RowMapper<Schedule>() {
			
					@Override
					public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
						Schedule schedule = new Schedule();
						schedule.setScheduleCode(rs.getString("schedule_cd"));
						schedule.setScheduleItemCode(rs.getString("schedule_item_cd"));
						schedule.setDescription(rs.getString("description"));
						if(rs.getString("title_detail_line")=="Y")
							schedule.setTitelDetailLine(true);
						else
							schedule.setTitelDetailLine(false);
						schedule.setUpdatedBy(rs.getString("updated_by"));
						schedule.setUpdatedOn(rs.getTimestamp("updated_on"));
						schedule.setEcgcStatus(rs.getString("ecgc_status").charAt(0));
						schedule.setMetaRemark(rs.getString("meta_remarks"));
						schedule.setCreatedOn(rs.getTimestamp("created_on"));
						schedule.setCreatedBy(rs.getString("created_by"));
						schedule.setSrNo(rs.getInt("sr_no"));
						schedule.setPrifix(rs.getString("prefix"));
						schedule.setTotal(rs.getString("total"));
						return schedule;
						}
					});
		if(allSchedule.isEmpty())
	return null;
		return allSchedule;
	}

	@Override
	public Integer deleteSchedule(String scheduleCode) {
		int result = jdbcOperations.update(ScheduleQueries.DELETE_SCHEDULE,scheduleCode);
		if(result == 1)
		return result;
		return -1;
	}
	@Override
	public Integer updateSchedule(Schedule schedule) {
		schedule.setEcgcStatus('V');
		Map<String, Object> scheduleNamedParameters = new HashMap<String, Object>();
		scheduleNamedParameters.put("scheduleCode", schedule.getScheduleCode());
		scheduleNamedParameters.put("scheduleItemCode", schedule.getScheduleItemCode());
		scheduleNamedParameters.put("description", schedule.getDescription());
		if(schedule.isTitelDetailLine())
		scheduleNamedParameters.put("titelDetailLine","Y");
		else
			scheduleNamedParameters.put("titelDetailLine","N");
		scheduleNamedParameters.put("updatedBy",schedule.getUpdatedBy());
		scheduleNamedParameters.put("updatedOn",schedule.getUpdatedOn());
		scheduleNamedParameters.put("ecgcStatus",schedule.getEcgcStatus());
		scheduleNamedParameters.put("metaRemark",schedule.getMetaRemark());
		scheduleNamedParameters.put("srNo",schedule.getSrNo());
		scheduleNamedParameters.put("prifix",schedule.getPrifix());
		scheduleNamedParameters.put("total",schedule.getTotal());
		int rowCount = namedParameterJdbcTemplate.update(ScheduleQueries.UPDATE_SCHEDULE,scheduleNamedParameters);
		if(rowCount==1)
		return 1;
		return -1;
	}
	
	

	
}
