package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.ChqDishonorEntry;
public interface ChqDishonorEntryService{
	public List<ChqDishonorEntry> getChqDishonorEntryList();
	public boolean addChqDishonorEntryData(ChqDishonorEntry  chqDishonorEntry);
	public boolean updateChqDishonorEntryData(ChqDishonorEntry  chqDishonorEntry);
	public ChqDishonorEntry getChqDishonorEntryByChequeNo(String instrumentNo);
}
