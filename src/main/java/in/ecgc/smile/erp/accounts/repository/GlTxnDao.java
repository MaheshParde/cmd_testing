package in.ecgc.smile.erp.accounts.repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import in.ecgc.smile.erp.accounts.exception.GLCodeIncompleteDataException;
import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.exception.InvalidGLTxnDataException;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;

public interface GlTxnDao {

	public List<GlTxnHdr> getAllGlTxn(String logicalLoc) throws GlTxnNotFoundException; 
	public List<GlTxnHdr> getAllGlTxn(String logicalLoc , String glTxnType) throws GlTxnNotFoundException; 
	public GlTxnHdr getGlTxn(Integer glTxnNo, String logicalLoc , String glTxnType) throws GlTxnNotFoundException;
	public List<GlTxnHdr> getAllGlTxnByFromDtToDt(LocalDate fromDt , LocalDate toDt) throws GlTxnNotFoundException ;
	public Integer addGlTxn(GlTxnHdr glTxnHdr) throws SQLException; 
	public Integer updateGlTxnDtl(GlTxnDtl glTxnDtl) throws SQLException; 
	public Integer updateHdrOnRevarsal(GlTxnHdr glTxnHdr) throws InvalidGLTxnDataException;
	public Integer updateDrCrBrbal(List<GlTxnDtl> glTxnDtl,String fiscalYr,LocalDate txnDate) throws InvalidGLTxnDataException;
	public Integer getGlTxnNo(String logcalLoc,String glTxnType,String fiscalYr);

}