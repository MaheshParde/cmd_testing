package in.ecgc.smile.erp.accounts.repository;

import java.util.Date;
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
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.model.TcsMaster;
import in.ecgc.smile.erp.accounts.util.TcsMasterSqlQueries;
@Repository
@Transactional
public class TcsMasterDaoImpl implements TcsMasterDao {

	private JdbcOperations jdbcOperations;
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(TcsMasterDaoImpl.class);

	@Autowired
	public TcsMasterDaoImpl(DataSource dataSource) 
	{
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations =  new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	@Override
	public List<TcsMaster> listAllTcs()throws DataAccessException
	{	
		List<TcsMaster> tcslist  = new ArrayList<TcsMaster>();

		tcslist = namedParameterJdbcTemplate.query(TcsMasterSqlQueries.VIEW_ALL, new RowMapper<TcsMaster>()
		{			
			@Override
			public TcsMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				TcsMaster tcsMaster = new TcsMaster();
				tcsMaster.setAmount(rs.getDouble("amount"));
				tcsMaster.setCreatedBy(rs.getString("created_by"));
				tcsMaster.setCreatedDate(rs.getDate("created_date"));
				tcsMaster.setFinYear(rs.getString("fin_yr"));
				tcsMaster.setFromAmount(rs.getDouble("from_amount"));
				tcsMaster.setFromDate(rs.getDate("from_date"));
				tcsMaster.setLimitEcgc(rs.getDouble("limit_ecgc"));
				tcsMaster.setModifiedBy(rs.getString("last_updated_by"));
				tcsMaster.setModifiedDate(rs.getDate("last_updated_dt"));
				tcsMaster.setSex(rs.getString("sex"));
				tcsMaster.setSurchargePer(rs.getFloat("surcharge_per"));
				tcsMaster.setSurchargeAmount(rs.getDouble("surcharge_amt"));
				tcsMaster.setToDate(rs.getDate("to_date"));
				tcsMaster.setToAmount(rs.getDouble("to_amount"));
				tcsMaster.setTaxPercent(rs.getFloat("tax_percent"));
				return tcsMaster;
			}					
		}); 
		return tcslist;	
	}
	
	@Override
	public TcsMaster checkExistingData(String finYear,Double fromAmount ,Double toAmount, String sex ,Date fromDate ,Date toDate) {
		
		TcsMaster tcsMasterList = new TcsMaster();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finYear", finYear);
		paramMap.put("fromAmount", fromAmount);
		paramMap.put("toAmount", toAmount);
		paramMap.put("sex", sex);
		paramMap.put("fromDate", fromDate);
		paramMap.put("toDate", toDate);
		try {
				tcsMasterList = namedParameterJdbcOperations.queryForObject(TcsMasterSqlQueries.SELECT_EXISTING_DATA,
					paramMap,  new RowMapper<TcsMaster>() {
			@Override
				public TcsMaster mapRow(ResultSet rs, int rowNum) throws SQLException{
					TcsMaster tcsMaster = new TcsMaster();

					tcsMaster.setAmount(rs.getDouble("amount"));
					tcsMaster.setCreatedBy(rs.getString("created_by"));
					tcsMaster.setCreatedDate(rs.getDate("created_date"));
					tcsMaster.setFinYear(rs.getString("fin_yr"));
					tcsMaster.setFromAmount(rs.getDouble("from_amount"));
					tcsMaster.setFromDate(rs.getDate("from_date"));
					tcsMaster.setLimitEcgc(rs.getDouble("limit_ecgc"));
					tcsMaster.setModifiedBy(rs.getString("last_updated_by"));
					tcsMaster.setModifiedDate(rs.getDate("last_updated_dt"));
					tcsMaster.setSex(rs.getString("sex"));
					tcsMaster.setSurchargePer(rs.getFloat("surcharge_per"));
					tcsMaster.setSurchargeAmount(rs.getDouble("surcharge_amt"));
					tcsMaster.setToDate(rs.getDate("to_date"));
					tcsMaster.setToAmount(rs.getDouble("to_amount"));
					tcsMaster.setTaxPercent(rs.getFloat("tax_percent"));
					return tcsMaster;
					
				}
			}); 
		} catch (EmptyResultDataAccessException e) {
			tcsMasterList= null;
		}return tcsMasterList;
	}	
	
	@Override	
	public Integer addTcsMaster(TcsMaster tcsMaster) {
		Map<String, Object> tcsNamedParameter = new HashMap<String, Object>();
		int result= 0;
		TcsMaster tcsMasterExistingList = new TcsMaster();

		LOGGER.info(tcsMasterExistingList.toString());
		tcsNamedParameter.put("finYear" , tcsMaster.getFinYear());
		tcsNamedParameter.put("fromAmount", tcsMaster.getFromAmount());
		tcsNamedParameter.put("toAmount", tcsMaster.getToAmount());
		tcsNamedParameter.put("taxPercent", tcsMaster.getTaxPercent());
		tcsNamedParameter.put("createdBy" , tcsMaster.getCreatedBy());
		tcsNamedParameter.put("createdDate", tcsMaster.getCreatedDate());
		tcsNamedParameter.put("modifiedBy", tcsMaster.getModifiedBy()); 
		tcsNamedParameter.put("modifiedDate", tcsMaster.getModifiedDate());
		tcsNamedParameter.put("amount", tcsMaster.getAmount());
		tcsNamedParameter.put("fromDate", tcsMaster.getFromDate());
		tcsNamedParameter.put("toDate",tcsMaster.getToDate());
		tcsNamedParameter.put("limitEcgc", tcsMaster.getLimitEcgc());
		tcsNamedParameter.put("surchargeAmount", tcsMaster.getSurchargeAmount());
		tcsNamedParameter.put("surchargePer", tcsMaster.getSurchargePer());
		tcsNamedParameter.put("sex", tcsMaster.getSex());
		
		 result = namedParameterJdbcTemplate.update(TcsMasterSqlQueries.ADD_TCS, tcsNamedParameter);
	LOGGER.info(checkExistingData(tcsMaster.getFinYear(), tcsMaster.getFromAmount(),
			tcsMaster.getToAmount(), tcsMaster.getSex() ,tcsMaster.getFromDate(), tcsMaster.getToDate()).toString());

	return result;
	}
}
