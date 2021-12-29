package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.PostDatedCheque;

public interface PDCDao {
	Integer createPDCEntry(PostDatedCheque pdc);
	List<PostDatedCheque> listAllPDC();
	PostDatedCheque viewByChequeNo(Integer cheqId);
	List<PostDatedCheque> viewByStatus(Character status);
	Integer changeStatus(String chqNo, PostDatedCheque pdc);
	PostDatedCheque checkUnique(String chequeNo, String chequeDate);
}
