package in.ecgc.smile.erp.accounts.repository;

import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
import in.ecgc.smile.erp.accounts.util.FiscalYearSqlQueries;

public class FiscalyearDaoImplTest {

	@Mock
	FiscalYearModel fiscalYear;
	
	@InjectMocks  
	FiscalYearDaoImpl dao;
	
	@Mock
	private DataSource dataSource;
	
	@Mock
	private JdbcOperations jdbcOperations;
	
	@Mock
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	
	@Mock
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	
	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeTest
	void initFiscalYear() {
		fiscalYear = new FiscalYearModel();
		fiscalYear.setCurrFiscalYear("2020-21");
		fiscalYear.setCurrFiscalYearStartDt(LocalDate.of(2020,01,03));
		fiscalYear.setPrevFiscalYear("2019-20");
		fiscalYear.setPrevFiscalYearClosedDt(LocalDate.of(2020,01,03));
		fiscalYear.setMetaStatus('V');		
	}
	 @Test 
	 public void findCurrentFiscalYearTest() {
		 Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("status", 'V');
		 when(namedParameterJdbcOperations.queryForObject(FiscalYearSqlQueries.GET_CURRENT_FISCAL_YEAR,
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
					})).thenReturn(fiscalYear);
	 }
	 @Test 
	 public void getFiscalYearListTest() {
		 List<String> fiscalYearList = new ArrayList<String>();
		 when(namedParameterJdbcTemplate.query(FiscalYearSqlQueries.ALL_FISCAL_YR,
					new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {

					return rs.getString("curr_fisc_yr");

				}
			})).thenReturn(fiscalYearList);
	 }
	

}
