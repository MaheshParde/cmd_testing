package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.PostDatedCheque;

public interface PDCService {

	Boolean createPDCEntry(PostDatedCheque pdc);
	List<PostDatedCheque> listAllPDC();
	PostDatedCheque viewByChequeNo(Integer chqNo);
	List<PostDatedCheque> viewByStatus(Character status);
	Integer changeStatus(String chqNo, PostDatedCheque pdc);
	PostDatedCheque checkUnique(String chequeNo, String chequeDate);
}
