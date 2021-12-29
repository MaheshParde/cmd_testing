package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.ChqDishonorEntry;
public interface ChqDishonorEntryDao{
	public List<ChqDishonorEntry> getChqDishonorEntryList()throws Exception;
	public boolean addChqDishonorEntryData(ChqDishonorEntry  chqDishonorEntry);
	public boolean updateChqDishonorEntryData(ChqDishonorEntry  chqDishonorEntry);
	public ChqDishonorEntry getChqDishonorEntryByChequeNo(String instrumentNo);
}
