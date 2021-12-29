package in.ecgc.smile.erp.accounts.repository;

import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.accounts.model.ChqDishonorEntry;
import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.service.ChqDishonorEntryService;
import in.ecgc.smile.erp.accounts.util.ChqDishonorEntryQueries;
import in.ecgc.smile.erp.accounts.util.LOVQueries;
public class ChqDishonorEntryDaoImplTest{
	
	@Mock
	private DataSource dataSource;
	
	@Mock
	private JdbcOperations jdbcOperations;
	
	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcOperations;
	
	@InjectMocks
	ChqDishonorEntryDaoImpl chqDishonorEntryDaoImpl;
	
	@Mock
	private List<ChqDishonorEntry> list;
	
	@Mock
	private ChqDishonorEntryService  service;
	
	@Mock
	private ChqDishonorEntry chqDishonorEntry;
	
	@Mock
	private Map<String, Object> chqNamedParameter;
	
	@BeforeTest
	public void beforeTest() {	
		MockitoAnnotations.initMocks(this);	
		MockMvcBuilders.standaloneSetup(chqDishonorEntryDaoImpl).build();
		}
	
	@BeforeTest
	public void initObjects() {
	ChqDishonorEntry chqDishonorEntry = new ChqDishonorEntry();
	//chqDishonorEntry.setDishonorDt(LocalDate.parse("2021-12-23"));
	chqDishonorEntry.setDishonorReason("Test");
	chqDishonorEntry.setFiscalYr("2021-22");
	chqDishonorEntry.setGlTxnNo("2021000002");
	chqDishonorEntry.setInstrumentNo("123456789");
	chqDishonorEntry.setInstrumentType("DD");
	chqDishonorEntry.setLogicalLocCd("PUNELOG1");
	chqDishonorEntry.setOldRcptNo(202100002);
	chqDishonorEntry.setRcptNo(202100002);
	}
	
	@Test
	public void addChqDishonorEntryDataTest() throws Exception {
		when(namedParameterJdbcOperations.update(ChqDishonorEntryQueries.ADD_ChqDishonorEntry_DATA, 
				chqNamedParameter)).thenReturn(1);

	}
	@Test
	public void updateChqDishonorEntryDataTest() throws Exception {
		when(namedParameterJdbcOperations.update(ChqDishonorEntryQueries.UPDATE_ChqDishonorEntry_DATA, chqNamedParameter)).thenReturn(1);
	}
	
	@Test
	public void  getChqDishonorEntryListTest() throws Exception {
		when(jdbcOperations.query(ChqDishonorEntryQueries.GET_ChqDishonorEntry_DATA, new RowMapper<ChqDishonorEntry>() {
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
		})).thenReturn(list);
	
	}
	
	@Test
	public void  getChqDishonorEntryDataByChequeNoTest() throws Exception {
		chqNamedParameter.put("instrumentNo", "11110111");
		when(namedParameterJdbcOperations.queryForObject(ChqDishonorEntryQueries.GET_ChqDishonorEntry_DATA, chqNamedParameter, new RowMapper<ChqDishonorEntry>(){
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
		})).thenReturn(chqDishonorEntry);
	}
}
