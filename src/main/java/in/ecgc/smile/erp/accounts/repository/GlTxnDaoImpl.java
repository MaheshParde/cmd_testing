package in.ecgc.smile.erp.accounts.repository;

import java.math.BigDecimal;
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

import com.netflix.discovery.converters.Auto;

import in.ecgc.smile.erp.accounts.exception.CalendarRecordNotFoundException;
import in.ecgc.smile.erp.accounts.exception.GLCodeNotFoundException;
import in.ecgc.smile.erp.accounts.exception.GLTxnDBFailedException;
import in.ecgc.smile.erp.accounts.exception.InvalidGLTxnDataException;
import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.DateOperation;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.service.CalendarService;
import in.ecgc.smile.erp.accounts.service.EntityGLMasterService;
import in.ecgc.smile.erp.accounts.util.GlTxnSqlQueries;
import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class GlTxnDaoImpl implements GlTxnDao {

	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
//	GlTxnDaoImpl txnDao; 
@Autowired 
EntityGLMasterService entitySrvice;
@Autowired
CalendarService calenderService;

	@Autowired
	public GlTxnDaoImpl(DataSource dataSource) {
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<GlTxnHdr> getAllGlTxn(String logicalLoc) {
		log.info("Inside GlTxnDaoImpl#getAllGlTxn");
		
		List<GlTxnHdr> glTxnHdrs =null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", 'V');
		paramMap.put("logicalLoc",logicalLoc);
		
		RowMapper<GlTxnHdr> rowMapper= GlTxnSqlQueries::mapRow;
		glTxnHdrs =namedParameterJdbcTemplate.query(GlTxnSqlQueries.GET_ALL_GL_TXN_HDR,paramMap,rowMapper);
		
		if(glTxnHdrs.isEmpty()) {
			log.error("Empty list found for logical location : {}",logicalLoc);
			return glTxnHdrs;
		}else {
			return glTxnHdrs;
		}
	}
	
	
	@Override
	public List<GlTxnHdr> getAllGlTxn(String logicalLoc , String glTxnType) {
		log.info("Inside GlTxnDaoImpl#getAllGlTxn");
		List<GlTxnHdr> glTxnHdrs =null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", 'V');
		paramMap.put("logicalLoc",logicalLoc);
		paramMap.put("gltxntype",glTxnType);
			
		RowMapper<GlTxnHdr> rowMapper= GlTxnSqlQueries::mapRow;
		glTxnHdrs =namedParameterJdbcTemplate.query(GlTxnSqlQueries.GET_ALL_GL_TXN_HDR_BY_TXNTYPE_AND_LOCATION,paramMap,rowMapper);
		if(glTxnHdrs==null) {
			log.error("Empty list found for logical location : {}",logicalLoc);
				return glTxnHdrs;
			}else {
				return glTxnHdrs;
		}
	}

	@Override
	public GlTxnHdr getGlTxn(Integer glTxnNo, String logicalLoc, String glTxnType) {
		log.info("Inside GlTxnDaoImpl#getGlTxn");
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
			return glTxn;
		}
		catch (Exception e) {
			log.info("Exception in GlTxnDaoImpl#getGlTxn : {}", e.getMessage());
			throw new GLTxnDBFailedException("No Gl transaction details found for logical location : "+logicalLoc+" and GL transaction type : "+glTxnType+" and GL transaction number : "+glTxnNo+"");
		}
	}
	
	@Override
	public List<GlTxnHdr> getAllGlTxnByFromDtToDt(LocalDate fromDt , LocalDate toDt) {
		log.info("Inside GlTxnDaoImpl#getAllGlTxnByFromDtToDt");
		List<GlTxnHdr> glTxnHdrs =null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", 'V');
		paramMap.put("fromDt",fromDt);
		paramMap.put("toDt",toDt);
	
		RowMapper<GlTxnHdr> rowMapper= GlTxnSqlQueries::mapRow;
		glTxnHdrs =namedParameterJdbcTemplate.query(GlTxnSqlQueries.GET_TXN_BY_FROM_TO_DT,paramMap,rowMapper);
		
		if(glTxnHdrs==null) {
			log.error("Empty list found for date between : {} and {}",fromDt,toDt);
				return glTxnHdrs;
			}else {
				return glTxnHdrs;
		}
	}
	
	@Override
	public Integer addGlTxn(GlTxnHdr glTxnHdr){
		log.info("Inside GlTxnDaoImpl#addGlTxn");
//		DateOperation dt = new DateOperation(glTxnHdr.getTxnDt().getMonthValue());
		Integer rowNum;
//		 Calendar calendar =  new Calendar();
//		 calendar = calenderService.getByGlTypeLogicalLoc(glTxnHdr.getFiscalYr(), dt.currentMonth, glTxnHdr.getLogicalLocCd(),glTxnHdr.getGlTxnType());
		
		try {
			log.info("Received GL txn header object :  {}",glTxnHdr.getGlTxnNo());
			Map<String, Object> paramMapForHdr = GlTxnSqlQueries.getParamMapForHdr(glTxnHdr);
			//if(calendar != null &&(calendar.getClosedStatus().equals('N') || calendar.getClosedStatus().equals('n'))) {
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
//			}
//			else {
//				System.out.println("Dao  Calander  Exception");
//
//				throw new CalendarRecordNotFoundException("Calender Not found with the given Logical Location Code"
//			+glTxnHdr.getLogicalLocCd()+", ficalYear- "+glTxnHdr.getFiscalYr()+" month "+dt.currentMonth+" txn type "+glTxnHdr.getGlTxnType());
//				}
		}
		catch (Exception e) {
			log.error("Exception in GlTxnDaoImpl#getGlTxnNo : {}",e.fillInStackTrace());
			throw new GLTxnDBFailedException("Failed to add new GL transaction detail : " + e.getMessage());
		}
	} 
	
	@Override
	public Integer getGlTxnNo(String logcalLoc,String glTxnType,String fiscalYr) {
		log.info("Inside GlTxnDaoImpl#getGlTxnNo");
		log.info("Generating GL Txn Sequence Number for LogicalLocation: {} TxnType: {} Fiscal Year: {}",
				logcalLoc,glTxnType,fiscalYr);
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
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
			return seq;
		}
		catch(Exception e) {
			log.error("Exception in Generating GL Txn Sequence Number for LogicalLocation: {} TxnType: {} Fiscal Year: {}",
					logcalLoc,glTxnType,fiscalYr);
			log.error("Exception in GlTxnDaoImpl#getGlTxnNo",e.fillInStackTrace());
			throw new GLTxnDBFailedException("Failed to Generate Sequence Number" + e.getMessage());
		}
	}

	@Override
	public Integer updateGlTxnNo(String logcalLoc, String glTxnType, String fiscalYr, Integer glTxnNo) {
		log.info("Inside GlTxnDaoImpl#updateGlTxnNo");
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("logicalLoc",logcalLoc);
			param.put("glTxnType",glTxnType);
			param.put("fiscalYr",fiscalYr);
			param.put("glTxnNo",glTxnNo);
			
			return namedParameterJdbcTemplate.update(GlTxnSqlQueries.UPDATE_SEQ, param);
			
		} catch (Exception e) {
			log.error("Exception in Updating GL Txn Sequence Number for GLTxn No : {}, LogicalLocation: {}, TxnType: {}, Fiscal Year: {}",
					glTxnNo, logcalLoc,glTxnType,fiscalYr);
			log.error("Exception in GlTxnDaoImpl#updateGlTxnNo",e.fillInStackTrace());
			throw new GLTxnDBFailedException("Failed to Update Sequence Number" + e.getMessage());
		}
	}

	
	@Override
	public Integer updateGlTxnDtl(GlTxnDtl glTxnDtl)  {
		log.info("Inside GlTxnDaoImpl#updateGlTxnDtl");
		Integer rowNum=0;
		try {
		Map<String, Object> paramMapForDtl = GlTxnSqlQueries.getParamMapForDtl(glTxnDtl);
		rowNum = namedParameterJdbcTemplate.update(GlTxnSqlQueries.UPDATE_GL_TXN, paramMapForDtl);
		return rowNum;
		}
		catch (Exception e) {
			log.error("Exception in Updating GL transaction detail : {}",glTxnDtl);
			log.error("Exception in GlTxnDaoImpl#updateGlTxnNo",e.fillInStackTrace());
			throw new GLTxnDBFailedException("Failed to Update GL transaction detail" + e.getMessage());
		}
	}

	@Override
	public Integer updateHdrOnRevarsal(GlTxnHdr glTxnHdr)throws InvalidGLTxnDataException {
		log.info("Inside GlTxnDaoImpl#updateHdrOnRevarsal");
		Integer rowNum=0;
		try {
		Map<String, Object> paramMapForHdr = GlTxnSqlQueries.getParamMapForHdr(glTxnHdr);
		System.out.println(paramMapForHdr);
		rowNum = namedParameterJdbcTemplate.update(GlTxnSqlQueries.UPDATE_HDR_FOR_REVARSAL, paramMapForHdr);
		return rowNum;
		}
		catch (Exception e) {
			log.error("Exception in Updating GL header on reversal : {}",glTxnHdr);
			log.error("Exception in GlTxnDaoImpl#updateHdrOnRevarsal",e.fillInStackTrace());
			throw new GLTxnDBFailedException("Failed to Update GL header on reversal" + e.getMessage());
		}
	}

	public Integer updateGLBrBal(GlTxnDtl glTxnDtl) {
		
		return 0;
	}

	@Override
	public Integer updateDrCrBrbal(List<GlTxnDtl> glTxnDtls,String fiscalYr,LocalDate txnDt) throws InvalidGLTxnDataException {
		log.info("Inside GlTxnDaoImpl#updateDrCrBrbal");
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
			paramMap.put("plCd", glDtl.getPlCd());
			if(glDtl.getDrCrFlag().equalsIgnoreCase("Cr"))
				try {
					
					System.err.println("dr cr Flag of gl txn detail :: "+ glDtl.getDrCrFlag());
					
					rowNum += namedParameterJdbcOperations.update(GlTxnSqlQueries.UPDATE_CURR_CR_BAL, paramMap);
				} catch (Exception e) {
					log.error("Exception in Updating current CR balance for GL detals : {} , Fical year : {} and transaction date : {}",glTxnDtls,fiscalYr,txnDt);
					log.error("Exception in GlTxnDaoImpl#updateDrCrBrbal",e.fillInStackTrace());
					throw new GLTxnDBFailedException("Failed to Update current CR balance" + e.getMessage());
				}
			else
				try {
					rowNum += namedParameterJdbcOperations.update(GlTxnSqlQueries.UPDATE_CURR_DR_BAL, paramMap);
				} catch (Exception e) {
					log.error("Exception in Updating current DR balance for GL detals : {} , Fical year : {} and transaction date : {}",glTxnDtls,fiscalYr,txnDt);
					log.error("Exception in GlTxnDaoImpl#updateDrCrBrbal",e.fillInStackTrace());
					throw new GLTxnDBFailedException("Failed to Update current DR balance" + e.getMessage());
				}
			}
		return rowNum;
	}	
	@Override
	public Integer updateDrCrBrbalYr(List<GlTxnDtl> glTxnDtls,String fiscalYr,LocalDate txnDt) throws InvalidGLTxnDataException {
		log.info("Inside GlTxnDaoImpl#updateDrCrBrbalYr");
		int rowNum = 0;
		DateOperation dt = new DateOperation(txnDt.getMonthValue());
		for(GlTxnDtl glDtl : glTxnDtls) {
			Map<String,Object> paramMap = GlTxnSqlQueries.getParamMapForDtl(glDtl);
			paramMap.put("logicalLoc",glDtl.getLogicalLocCd());
			paramMap.put("mainGlCd",glDtl.getMainGlCd());
			paramMap.put("subGlCd",glDtl.getSubGlCd());
			paramMap.put("fiscalYr",fiscalYr);
			//paramMap.put("month",dt.currentMonth);
			paramMap.put("amt",glDtl.getTxnAmt());
			paramMap.put("plCd", glDtl.getPlCd());
			if(glDtl.getDrCrFlag().equalsIgnoreCase("Cr"))
				try {					
					System.err.println("dr cr Flag of gl txn detail :: "+ glDtl.getDrCrFlag());
					
					rowNum += namedParameterJdbcOperations.update(GlTxnSqlQueries.UPDATE_CURR_CR_BAL_YR, paramMap);
				} catch (Exception e) {
					log.error("Exception in Updating current CR balance for GL detals : {} , Fical year : {} and transaction date : {}",glTxnDtls,fiscalYr,txnDt);
					log.error("Exception in GlTxnDaoImpl#updateDrCrBrbal",e.fillInStackTrace());
					throw new GLTxnDBFailedException("Failed to Update current CR balance" + e.getMessage());
				}
			else
				try {
					rowNum += namedParameterJdbcOperations.update(GlTxnSqlQueries.UPDATE_CURR_DR_BAL_YR, paramMap);
				} catch (Exception e) {
					log.error("Exception in Updating current DR Year balance for GL detals : {} , Fical year : {} and transaction date : {}",glTxnDtls,fiscalYr,txnDt);
					log.error("Exception in GlTxnDaoImpl#updateDrCrBrbal",e.fillInStackTrace());
					throw new GLTxnDBFailedException("Failed to Update current DR balance" + e.getMessage());
				}
			}
		return rowNum;
	}
	@Override
	public BigDecimal getTotalAmt(String mainGlCd , String subGlCd)  {		
		log.info("Inside GlTxnDaoImpl#getTotalAmt");
		Map<String, Object> paramMap = new HashMap<>();
		BigDecimal amount ;
			paramMap.put("mainGlCd",mainGlCd);
			paramMap.put("subGlCd",subGlCd);
			if (entitySrvice.findGLByGLCode(mainGlCd, subGlCd)!=null) {
				try {
					amount =namedParameterJdbcTemplate.queryForObject(GlTxnSqlQueries.GET_TOTAL_DEBIT_BAL,paramMap,new RowMapper<BigDecimal>() {
						@Override
						public BigDecimal mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getBigDecimal("sum");
						}
					});
				} catch (Exception e) {
					log.info("Exception in GlTxnDaoImpl#getTotalAmt");
					throw new GLTxnDBFailedException("Failed to get total DR balance : "+e.fillInStackTrace());
				}
			}else {
				log.info("Inside  GlTxnDaoImpl#getTotalAmt else block");
				log.info("NO entity GL code found for Main GL Code : {} and Sub GL COde : {} ",mainGlCd,subGlCd);
				throw new GLCodeNotFoundException("Exception in GlTxnDaoImpl#getTotalAmt ");
			}
		amount = new BigDecimal(0);
		return amount;				
	}

	
	@Override
	public BigDecimal getTotalCreditAmt(String mainGlCd , String subGlCd) {		
		log.info("Inside GlTxnDaoImpl#getTotalCreditAmt");
		Map<String, Object> paramMap = new HashMap<>();
		BigDecimal amount ;
		paramMap.put("mainGlCd",mainGlCd);
		paramMap.put("subGlCd",subGlCd);
			if (entitySrvice.findGLByGLCode(mainGlCd, subGlCd)!=null) {
				try {
					amount =namedParameterJdbcTemplate.queryForObject(GlTxnSqlQueries.GET_TOTAL_CREDIT_BAL,paramMap,new RowMapper<BigDecimal>() {
						@Override
						public BigDecimal mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getBigDecimal("sum");
						}
					});
				} catch (Exception e) {
					log.info("Exception in GlTxnDaoImpl#getTotalCreditAmt");
					throw new GLTxnDBFailedException("Failed to get total DR balance : "+e.fillInStackTrace());
				}
			}else {
				log.info("Inside  GlTxnDaoImpl#getTotalAmt else block");
				log.info("NO entity GL code found for Main GL Code : {} and Sub GL COde : {} ",mainGlCd,subGlCd);
				throw new GLCodeNotFoundException("Exception in GlTxnDaoImpl#getTotalCreditAmt ");
			}
		return amount;				
	}

	@Override
	public Integer deleteGlTxnDtl(Integer glTxnNo) {
	
		log.info("Inside GlTxnDaoImpl#deleteGlTxnDtl");
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("glTxnNo",glTxnNo);
			
			return namedParameterJdbcTemplate.update(GlTxnSqlQueries.ADD_GL_TXN_DTL, param);
			
		} catch (Exception e) {
			log.error("Exception in Delete GL Record from GlTxnDtl for GLTxn No :{} ",glTxnNo);
			log.error("Exception in GlTxnDaoImpl#deleteGlTxnDtl",e.fillInStackTrace());
			throw new GLTxnDBFailedException("Failed to Delete GL Record from GlTxnDtl" + e.getMessage());
		}
	}

	@Override
	public Integer deleteGlTxnHdr(Integer glTxnNo) {
		log.info("Inside GlTxnDaoImpl#deleteGlTxnHdr");
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("glTxnNo",glTxnNo);
			
			return namedParameterJdbcTemplate.update(GlTxnSqlQueries.ADD_GL_TXN_HDR, param);
			
		} catch (Exception e) {
			log.error("Exception in Delete GL Record from GlTxnHdr for GLTxn No :{} ",glTxnNo);
			log.error("Exception in GlTxnDaoImpl#deleteGlTxnHdr",e.fillInStackTrace());
			throw new GLTxnDBFailedException("Failed to Delete GL Record from GlTxnHdr" + e.getMessage());
		}
	}
	
	@Override
	public List<GlTxnHdr> getAllGltxnByFromDtLoc(LocalDate fromDt, LocalDate toDt, String logicallocation) {
		log.info("Inside GlTxnDaoImpl#getAllGltxnByFromDtLoc");
		List<GlTxnHdr> glTxnHdrs = null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDt", fromDt);
		paramMap.put("toDt", toDt);
		paramMap.put("logicalLoc", logicallocation);

		try 
		{
		// RowMapper<GlTxnHdr> rowMapper= GlTxnSqlQueries::mapRow;
		glTxnHdrs = namedParameterJdbcTemplate.query(GlTxnSqlQueries.GET_TXN_BY_FROM_TO_DT_AND_LOC, paramMap,
				new RowMapper<GlTxnHdr>() {

					@Override
					public GlTxnHdr mapRow(ResultSet rs, int rowNum) throws SQLException {
						GlTxnHdr glTxnHdr = new GlTxnHdr();
						glTxnHdr.setEntityCd(rs.getString("entity_cd"));
						glTxnHdr.setGlTxnNo(rs.getInt("gl_txn_no"));
						glTxnHdr.setGlTxnType(rs.getString("gl_txn_type"));
						glTxnHdr.setLogicalLocCd(rs.getString("logical_loc_cd"));
						if(rs.getDate("txn_dt") != null)
							glTxnHdr.setTxnDt(rs.getDate("txn_dt").toLocalDate());
						glTxnHdr.setReference(rs.getString("reference"));
						glTxnHdr.setRevarsalGlTxnType(rs.getString("reversal_txn_type"));
						glTxnHdr.setRevarsalGlTxnNo(rs.getInt("reversal_txn_no"));
						glTxnHdr.setReversalReason(rs.getString("reversal_reason"));
						glTxnHdr.setFiscalYr(rs.getString("fiscal_yr"));
						glTxnHdr.setCreatedBy(rs.getString("created_by"));
						glTxnHdr.setCreatedDt(rs.getDate("created_dt"));
						glTxnHdr.setLastUpdatedBy(rs.getString("last_updated_by"));
						glTxnHdr.setLastUpdatedDt(rs.getDate("last_updated_dt"));
						glTxnHdr.setMetaRemarks(rs.getString("meta_remarks"));
						glTxnHdr.setMetaStatus(rs.getString("meta_status"));
						return glTxnHdr;
					}
				});
		
		}
		catch (Exception e) {
			log.error("Exception in View GL Entry from GlTxnHdr by LogicalLocation {} And Date between : {} and {} ", logicallocation,fromDt,toDt);
			log.error("Exception in GlTxnDaoImpl#getAllGltxnByFromDtLoc", e.fillInStackTrace());
			throw new GLCodeNotFoundException("Exception in GlTxnDaoImpl#getAllGltxnByFromDtLoc ");
		}
		return glTxnHdrs;
	}
	
	@Override
	public List<GlTxnHdr> getAllGlTxnByTxnNoTxnTypeLoc(Integer glTxnNo, String glTxnType, String logicalLoc) {
		log.info("Inside GlTxnDaoImpl#getAllGlTxnByTxnNoTxnTypeLoc");
		List<GlTxnHdr> glTxnHdrs = null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glTxnNo",glTxnNo);
		paramMap.put("glTxnType", glTxnType);
		paramMap.put("logicalLoc", logicalLoc);
		
		try {
			glTxnHdrs = namedParameterJdbcTemplate.query(GlTxnSqlQueries.GET_ALL_GL_TXN_BY_TXNNO_TXNTYPE_LOC, paramMap,
					new RowMapper<GlTxnHdr>() {

						@Override
						public GlTxnHdr mapRow(ResultSet rs, int rowNum) throws SQLException {
							GlTxnHdr glTxnHdr = new GlTxnHdr();
							glTxnHdr.setEntityCd(rs.getString("entity_cd"));
							glTxnHdr.setGlTxnNo(rs.getInt("gl_txn_no"));
							glTxnHdr.setGlTxnType(rs.getString("gl_txn_type"));
							glTxnHdr.setLogicalLocCd(rs.getString("logical_loc_cd"));
							if(rs.getDate("txn_dt") != null)
							glTxnHdr.setTxnDt(rs.getDate("txn_dt").toLocalDate());
							glTxnHdr.setReference(rs.getString("reference"));
							glTxnHdr.setRevarsalGlTxnType(rs.getString("reversal_txn_type"));
							glTxnHdr.setRevarsalGlTxnNo(rs.getInt("reversal_txn_no"));
							glTxnHdr.setReversalReason(rs.getString("reversal_reason"));
							glTxnHdr.setFiscalYr(rs.getString("fiscal_yr"));
							glTxnHdr.setCreatedBy(rs.getString("created_by"));
							glTxnHdr.setCreatedDt(rs.getDate("created_dt"));
							glTxnHdr.setLastUpdatedBy(rs.getString("last_updated_by"));
							glTxnHdr.setLastUpdatedDt(rs.getDate("last_updated_dt"));
							glTxnHdr.setMetaRemarks(rs.getString("meta_remarks"));
							glTxnHdr.setMetaStatus(rs.getString("meta_status"));
							return glTxnHdr;
							
						}
			});
		}
		catch(Exception e) {
			log.error("Exception in view all GL Record from GlTxnHdr for GLTxn No  :{}, GLTxn Type :{},Location :{} ", glTxnNo,glTxnType,logicalLoc);
			log.error("Exception in GlTxnDaoImpl#deleteGlTxnHdr", e.fillInStackTrace());
		}
		
		if (glTxnHdrs == null) {
			log.error("Empty list found for gltxnno {}, gltxntype {} and gltaxno {}",glTxnNo, glTxnType, logicalLoc);
			return glTxnHdrs;
			} else {
			return glTxnHdrs;
			}
		
	}

}
