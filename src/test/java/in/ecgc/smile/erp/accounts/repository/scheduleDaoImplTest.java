package in.ecgc.smile.erp.accounts.repository;

import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.Schedule;
import in.ecgc.smile.erp.accounts.util.FTRSqlQueries;
import in.ecgc.smile.erp.accounts.util.ScheduleQueries;

public class scheduleDaoImplTest {

	@Mock
	Schedule schedule;
	
	
	@Mock
	private DataSource dataSource;
	
	@Mock
	private JdbcOperations jdbcOperations;
	
	@Mock
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	
	@Mock
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	
	@InjectMocks
	ScheduleDaoImpl dao;
	
	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
		}
	
	@BeforeTest
	public void initSchedule() {
		schedule = new Schedule();
		schedule.setScheduleCode("14");
		schedule.setScheduleItemCode("144");
		schedule.setSrNo(1);
		schedule.setDescription("Exgratia payment in lieu of Bonus");
		schedule.setPrifix("NONE");
		schedule.setTitelDetailLine(true);
		schedule.setTotal("N");
		
	}
	
	@Test
	public void addScheduleTest() {
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
		scheduleNamedParameters.put("createdBy",101);
		scheduleNamedParameters.put("srNo",schedule.getSrNo());
		scheduleNamedParameters.put("prifix",schedule.getPrifix());
		scheduleNamedParameters.put("total",schedule.getTotal());
		when( namedParameterJdbcTemplate.update(ScheduleQueries.GET_SCHEDULE_BY_SCHEDULE_CD1,scheduleNamedParameters)).thenReturn(1);
	}
	
	@Test
	public void getScheduleTest() {
		ArrayList<Schedule> list = new ArrayList<>();
		list.add(schedule);
		String schedule_cd = "14";
		when(jdbcOperations.query(ScheduleQueries.GET_SCHEDULE_BY_SCHEDULE_CD1,new Object[] { schedule_cd } , new RowMapper<Schedule>() {

			@Override
			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
				System.out.println("inside row mapper");
				Schedule schedule = new Schedule();
				schedule.setScheduleCode(rs.getString("schedule_cd"));
				schedule.setScheduleItemCode(rs.getString("schedule_item_cd"));
				schedule.setDescription(rs.getString("description"));;
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
		})).thenReturn(list);
	}
	@Test
	public void getAllScheduleTest() {
		ArrayList<Schedule> list = new ArrayList<>();
		list.add(schedule);
		when(jdbcOperations.query(ScheduleQueries.GET_ALL_SCHEDULE, new RowMapper<Schedule>() {
			
			@Override
			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
				System.out.println("inside row mapper");
				Schedule schedule = new Schedule();
				schedule.setScheduleCode(rs.getString("schedule_cd"));
				schedule.setScheduleItemCode(rs.getString("schedule_item_cd"));
				schedule.setDescription(rs.getString("description"));;
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
		})).thenReturn(list);
	}

	@Test
	public void deleteScheduleTest() {
		String scheduleCode ="14";
		when(jdbcOperations.update(ScheduleQueries.DELETE_SCHEDULE,scheduleCode)).thenReturn(1);
	}
	@Test
	public void updateScheduleTest() {
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
		scheduleNamedParameters.put("createdBy",101);
		scheduleNamedParameters.put("srNo",schedule.getSrNo());
		scheduleNamedParameters.put("prifix",schedule.getPrifix());
		scheduleNamedParameters.put("total",schedule.getTotal());
		when( namedParameterJdbcTemplate.update(ScheduleQueries.UPDATE_SCHEDULE,scheduleNamedParameters)).thenReturn(1);
	}
	

}
