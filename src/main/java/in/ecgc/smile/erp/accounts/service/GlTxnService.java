package in.ecgc.smile.erp.accounts.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import in.ecgc.smile.erp.accounts.exception.GlTxnNotFoundException;
import in.ecgc.smile.erp.accounts.exception.InvalidGLTxnDataException;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;

public interface GlTxnService {

	List<GlTxnHdr> getAllGlTxnHdrs(String LogicalLoc);
	List<GlTxnHdr> getAllGlTxnHdrs(String LogicalLoc, String glTxnType);
	GlTxnHdr getGlTxn(Integer glTxnno, String LogicalLoc, String glTxnType);
	public List<GlTxnHdr> getAllGlTxnByFromDtToDt(LocalDate fromDt , LocalDate toDt) throws GlTxnNotFoundException; 
	public Integer addGlTxn(GlTxnHdr glTxnHdr); 
	public Integer updateGlTxnDtl(GlTxnDtl glTxnDtl) throws InvalidGLTxnDataException; 
	public Integer reverseTransaction(GlTxnHdr glTxnHdr) throws InvalidGLTxnDataException; 

}
