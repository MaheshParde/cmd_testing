package in.ecgc.smile.erp.accounts.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.exception.InvalidGLTxnDataException;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;

public interface GlTxnDao {

	public List<GlTxnHdr> getAllGlTxn(String logicalLoc); 
	public List<GlTxnHdr> getAllGlTxn(String logicalLoc , String glTxnType) ; 
	public GlTxnHdr getGlTxn(Integer glTxnNo, String logicalLoc , String glTxnType) throws GlTxnNotFoundException;
	public List<GlTxnHdr> getAllGlTxnByFromDtToDt(LocalDate fromDt , LocalDate toDt) ;
	public Integer addGlTxn(GlTxnHdr glTxnHdr); 
	public Integer updateGlTxnDtl(GlTxnDtl glTxnDtl) ; 
	public Integer updateHdrOnRevarsal(GlTxnHdr glTxnHdr) throws InvalidGLTxnDataException;
	public Integer updateDrCrBrbal(List<GlTxnDtl> glTxnDtl,String fiscalYr,LocalDate txnDate) throws InvalidGLTxnDataException;
	public Integer getGlTxnNo(String logcalLoc,String glTxnType,String fiscalYr);
	public Integer updateGlTxnNo(String logcalLoc,String glTxnType,String fiscalYr,Integer glTxnNo);
	public Integer deleteGlTxnDtl(Integer glTxnNo);
	public Integer deleteGlTxnHdr(Integer glTxnNo);
	public BigDecimal getTotalAmt(String mainGlCd , String subGlCd) ;
	public BigDecimal getTotalCreditAmt(String mainGlCd, String subGlCd);
	public Integer updateDrCrBrbalYr(List<GlTxnDtl> glTxnDtl,String fiscalYr,LocalDate txnDate) throws InvalidGLTxnDataException;
	public List<GlTxnHdr> getAllGltxnByFromDtLoc(LocalDate fromDt , LocalDate toDt, String logicallocation); 
	public List<GlTxnHdr> getAllGlTxnByTxnNoTxnTypeLoc(Integer glTxnNo,String glTxnType,String logicalLoc);

}