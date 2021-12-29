package in.ecgc.smile.erp.accounts.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;

public interface GlTxnService {

	List<GlTxnHdr> getAllGlTxnHdrs(String LogicalLoc) ;
	List<GlTxnHdr> getAllGlTxnHdrs(String LogicalLoc, String glTxnType);
	GlTxnHdr getGlTxn(Integer glTxnno, String LogicalLoc, String glTxnType);
	public List<GlTxnHdr> getAllGlTxnByFromDtToDt(LocalDate fromDt , LocalDate toDt); 
	public Integer addGlTxn(GlTxnHdr glTxnHdr); 
	public Integer updateGlTxnDtl(GlTxnDtl glTxnDtl); 
	public Integer reverseTransaction(GlTxnHdr glTxnHdr); 
	public BigDecimal getTotalAmt(String mainGlCd , String subGlCd) ;
	public BigDecimal getTotalCreditAmt(String mainGlCd, String subGlCd);
	public List<GlTxnHdr> getAllGltxnByFromDtLoc(LocalDate fromDt , LocalDate toDt, String logicallocation); 
	public List<GlTxnHdr> getAllGlTxnByTxnNoTxnTypeLoc(Integer glTxnNo,String glTxnType,String logicalLoc);
}
