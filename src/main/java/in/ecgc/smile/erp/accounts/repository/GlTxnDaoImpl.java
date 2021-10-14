package in.ecgc.smile.erp.accounts.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.exception.InvalidGLTxnDataException;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.model.DateOperation;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.util.GlTxnSqlQueries;

@Repository
@Transactional
public class GlTxnDaoImpl implements GlTxnDao {

	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
//	GlTxnDaoImpl txnDao; 
	

	@Autowired
	public GlTxnDaoImpl(DataSource dataSource) {
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<GlTxnHdr> getAllGlTxn(String logicalLoc) throws GlTxnNotFoundException {
		List<GlTxnHdr> glTxnHdrs =null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", 'V');
		paramMap.put("logicalLoc",logicalLoc);
		try {
		RowMapper<GlTxnHdr> rowMapper= GlTxnSqlQueries::mapRow;
		glTxnHdrs =namedParameterJdbcTemplate.query(GlTxnSqlQueries.GET_ALL_GL_TXN_HDR,paramMap,rowMapper);
		System.err.println("inside dao "+ glTxnHdrs);
		return glTxnHdrs;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new GlTxnNotFoundException(e.getMessage());
		}
		
	}
	@Override
	public List<GlTxnHdr> getAllGlTxn(String logicalLoc , String glTxnType) throws GlTxnNotFoundException {
		List<GlTxnHdr> glTxnHdrs =null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", 'V');
		paramMap.put("logicalLoc",logicalLoc);
		paramMap.put("gltxntype",glTxnType);
		try {
			RowMapper<GlTxnHdr> rowMapper= GlTxnSqlQueries::mapRow;
			glTxnHdrs =namedParameterJdbcTemplate.query(GlTxnSqlQueries.GET_ALL_GL_TXN_HDR_BY_TXNTYPE_AND_LOCATION,paramMap,rowMapper);
			System.err.println("inside dao "+ glTxnHdrs);
			return glTxnHdrs;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new GlTxnNotFoundException(e.getMessage());
		}
		
	}

	@Override
	public GlTxnHdr getGlTxn(Integer glTxnNo, String logicalLoc, String glTxnType) throws GlTxnNotFoundException {
		GlTxnHdr glTxn ;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", 'V');
		paramMap.put("logicalLoc",logicalLoc);
		paramMap.put("glTxnType",glTxnType);
		paramMap.put("glTxnNo",glTxnNo);
		try {
			RowMapper<GlTxnHdr> rowMapper= GlTxnSqlQueries::mapRow;
			
			RowMapper<GlTxnDtl> rowMapperForDtl= GlTxnSqlQueries::mapRowForDtl;
			glTxn = namedParameterJdbcOperations.queryForObject(GlTxnSqlQueries.GET_TXN_HDR_BY_ID,paramMap,rowMapper);
			glTxn.setGlTxnDtls(
					namedParameterJdbcOperations.query(GlTxnSqlQueries.GET_TXN_DTL_BY_ID,paramMap,rowMapperForDtl));
			System.err.println("inside dao "+ glTxn);
			return glTxn;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new GlTxnNotFoundException(e.getMessage());
		}
	}
	
	@Override
	public List<GlTxnHdr> getAllGlTxnByFromDtToDt(LocalDate fromDt , LocalDate toDt) throws GlTxnNotFoundException {
		List<GlTxnHdr> glTxnHdrs =null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", 'V');
		paramMap.put("fromDt",fromDt);
		paramMap.put("toDt",toDt);
		try {
			RowMapper<GlTxnHdr> rowMapper= GlTxnSqlQueries::mapRow;
			glTxnHdrs =namedParameterJdbcTemplate.query(GlTxnSqlQueries.GET_TXN_BY_FROM_TO_DT,paramMap,rowMapper);
			System.err.println("inside dao "+ glTxnHdrs);
			return glTxnHdrs;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new GlTxnNotFoundException(e.getMessage());
		}
		
	}
	
	@Override
	public Integer addGlTxn(GlTxnHdr glTxnHdr) throws SQLException{
		Integer rowNum;
		try {
			
//			System.err.println("="+glTxnHdr.getLogicalLocCd());
//			System.err.println("="+glTxnHdr.getGlTxnType());
//			System.err.println("="+glTxnHdr.getFiscalYr());
			
		glTxnHdr.setGlTxnNo(getGlTxnNo(glTxnHdr.getLogicalLocCd(), glTxnHdr.getGlTxnType(), glTxnHdr.getFiscalYr()));
		System.err.println("==="+glTxnHdr.getGlTxnNo());
		Map<String, Object> paramMapForHdr = GlTxnSqlQueries.getParamMapForHdr(glTxnHdr);
		rowNum = namedParameterJdbcTemplate.update(GlTxnSqlQueries.ADD_GL_TXN_HDR, paramMapForHdr);
		if(rowNum >0) {
			for(GlTxnDtl glDtl:glTxnHdr.getGlTxnDtls()) {
				Map<String,Object> paramMapForDtl = GlTxnSqlQueries.getParamMapForDtl(glDtl);
				paramMapForDtl.put("glTxnNo",glTxnHdr.getGlTxnNo());
				rowNum += namedParameterJdbcOperations.update(GlTxnSqlQueries.ADD_GL_TXN_DTL, paramMapForDtl);
				}

			return rowNum;
		}
		else {
			
			return 0;
		}
			}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	} 
	
	@Override
	public Integer getGlTxnNo(String logcalLoc,String glTxnType,String fiscalYr) {
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		System.err.println(logcalLoc+glTxnType+fiscalYr);
		namedParameters.put("logicalLoc",logcalLoc);
		namedParameters.put("glTxnType",glTxnType);
		namedParameters.put("fiscalYr",fiscalYr);
		Integer seq = namedParameterJdbcOperations.queryForObject(GlTxnSqlQueries.GET_SEQ,namedParameters,
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						int num =rs.getInt(1);
						return num;
					}
				});
		System.err.println("seq fun"+seq);
		return seq;

	}

	@Override
	public Integer updateGlTxnDtl(GlTxnDtl glTxnDtl) throws SQLException {
		Integer rowNum=0;
		try {
		Map<String, Object> paramMapForDtl = GlTxnSqlQueries.getParamMapForDtl(glTxnDtl);
		rowNum = namedParameterJdbcTemplate.update(GlTxnSqlQueries.UPDATE_GL_TXN, paramMapForDtl);
		return rowNum;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Integer updateHdrOnRevarsal(GlTxnHdr glTxnHdr)
			throws InvalidGLTxnDataException {
		Integer rowNum=0;
		try {
		Map<String, Object> paramMapForHdr = GlTxnSqlQueries.getParamMapForHdr(glTxnHdr);
		System.out.println(paramMapForHdr);
		rowNum = namedParameterJdbcTemplate.update(GlTxnSqlQueries.UPDATE_HDR_FOR_REVARSAL, paramMapForHdr);
		return rowNum;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Integer updateGLBrBal(GlTxnDtl glTxnDtl) {
		
		return 0;
	}

	@Override
	public Integer updateDrCrBrbal(List<GlTxnDtl> glTxnDtls,String fiscalYr,LocalDate txnDt) throws InvalidGLTxnDataException {
		int rowNum = 0;
		DateOperation dt = new DateOperation(txnDt.getMonthValue());
		for(GlTxnDtl glDtl : glTxnDtls) {
			Map<String,Object> paramMap = GlTxnSqlQueries.getParamMapForDtl(glDtl);
			paramMap.put("logicalLoc",glDtl.getLogicalLocCd());
			paramMap.put("mainGlCd",glDtl.getMainGlCd());
			paramMap.put("subGlCd",glDtl.getSubGlCd());
			paramMap.put("fiscalYr",fiscalYr);
			paramMap.put("month",dt.currentMonth);
			paramMap.put("amt",glDtl.getTxnAmt());
			if(glDtl.getDrCrFlag().equalsIgnoreCase("Cr"))
				rowNum += namedParameterJdbcOperations.update(GlTxnSqlQueries.UPDATE_CURR_CR_BAL, paramMap);
			else
				rowNum += namedParameterJdbcOperations.update(GlTxnSqlQueries.UPDATE_CURR_DR_BAL, paramMap);
			}
		return rowNum;
	}


}
