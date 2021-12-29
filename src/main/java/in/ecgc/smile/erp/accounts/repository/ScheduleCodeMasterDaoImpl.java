package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.ScheduleCodeMst;
import in.ecgc.smile.erp.accounts.util.ScheduleQueries;
import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class ScheduleCodeMasterDaoImpl implements ScheduleCodeMasterDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Integer addSchedule(ScheduleCodeMst schedule) {
		//Setting Default values for entity code while adding new code
		schedule.setEntity_cd("ECGC");
		schedule.setMeta_status("V");
		SqlParameterSource sqlParamSource = new BeanPropertySqlParameterSource(schedule);
		//For BeanProperySqlParameterSource to work the placeholder in query should match the attribute names in object
		int rowCount = namedParameterJdbcTemplate.update(ScheduleQueries.ADD_SCHEDULE,sqlParamSource);
		if(rowCount==1)
			return 1;
		return -1;
	}



	@Override
	public ScheduleCodeMst getSchedule(String schedule_cd, String schedule_item_cd) {
		Map<String,Object> sqlParamMap = new HashMap<>();
		sqlParamMap.put("schedule_cd", schedule_cd);
		sqlParamMap.put("schedule_item_cd", schedule_item_cd);
		List<ScheduleCodeMst> schedule = namedParameterJdbcTemplate.query(ScheduleQueries.GET_SCHEDULE_BY_SCHEDULE_CD1, sqlParamMap, 
				new RowMapper<ScheduleCodeMst>() {
			@Override
			public ScheduleCodeMst mapRow(ResultSet rs, int rowNum) throws SQLException {
				ScheduleCodeMst schedule = new ScheduleCodeMst();
				schedule.setSchedule_cd(rs.getString("schedule_cd"));
				schedule.setSchedule_item_cd(rs.getString("schedule_item_cd"));
				schedule.setDescription(rs.getString("description"));
				schedule.setTitle_detail_line(rs.getString("title_detail_line"));
				schedule.setTotal(rs.getString("total"));
				schedule.setPrefix(rs.getString("prefix"));
				schedule.setCreated_by(rs.getString("created_by"));
				schedule.setCreated_dt(rs.getObject("created_dt",LocalDateTime.class));
				schedule.setLast_updated_by(rs.getString("last_updated_by"));
				schedule.setLast_updated_dt(rs.getObject("last_updated_dt",LocalDateTime.class));
				return schedule;
			}
		});
		if(schedule.isEmpty())
			return null;
		return schedule.get(0);
	}



	@Override
	public Integer deleteSchedule(String schedule_cd, String schedule_item_cd) {
		Map<String,Object> sqlParamMap = new HashMap<>();
		sqlParamMap.put("schedule_cd", schedule_cd);
		sqlParamMap.put("schedule_item_cd", schedule_item_cd);
		sqlParamMap.put("meta_status", "I");
		int rowCount = namedParameterJdbcTemplate.update(ScheduleQueries.DELETE_SCHEDULE,sqlParamMap);
		if(rowCount==1) {
			log.info("Dao: Schedule Code Disabled successfully");
			return 1;
		}else {
			log.info("Dao: Schedule Code Not Disabled");
			return -1;
		}
	}



	@Override
	public Integer updateSchedule(ScheduleCodeMst schedule) {
		SqlParameterSource sqlParamSource = new BeanPropertySqlParameterSource(schedule);
		//For BeanProperySqlParameterSource to work the placeholder in query should match the attribute names in object
		int rowCount = namedParameterJdbcTemplate.update(ScheduleQueries.UPDATE_SCHEDULE,sqlParamSource);
		if(rowCount==1) {
			log.info("Dao: Schedule Code updated successfully");
			return 1;
		}else {
			log.info("Dao: Schedule Code Not Updated");
			return -1;
		}
	}



	@Override
	public List<ScheduleCodeMst> getAllSchedule() {
		List<ScheduleCodeMst> allSchedule= new ArrayList<>();
		try {	
		allSchedule = namedParameterJdbcTemplate.query(ScheduleQueries.GET_ALL_SCHEDULE, new RowMapper<ScheduleCodeMst>() {
			
					@Override
					public ScheduleCodeMst mapRow(ResultSet rs, int rowNum) throws SQLException {
						ScheduleCodeMst schedule = new ScheduleCodeMst();
						schedule.setSchedule_cd(rs.getString("schedule_cd"));
						schedule.setSchedule_item_cd(rs.getString("schedule_item_cd"));
						schedule.setDescription(rs.getString("description"));
						schedule.setTitle_detail_line(rs.getString("title_detail_line"));
						schedule.setTotal(rs.getString("total"));
						schedule.setPrefix(rs.getString("prefix"));
						schedule.setCreated_by(rs.getString("created_by"));
						schedule.setCreated_dt(rs.getObject("created_dt",LocalDateTime.class));
						schedule.setLast_updated_by(rs.getString("last_updated_by"));
						schedule.setLast_updated_dt(rs.getObject("last_updated_dt",LocalDateTime.class));
						return schedule;
						}
					});
			 }catch(Exception e) {
				 log.error("Exception while fetching Schedule List from DB:{}",e);
				 return Collections.emptyList();
			 }
		log.info("Schedule List Size returned from DAO Layer::{}",allSchedule.size());
		return allSchedule;
	}
}
