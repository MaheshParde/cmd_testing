package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.Temp_Exporter;
import in.ecgc.smile.erp.accounts.model.Temp_TDS_DTL;


public interface Temp_ExternalService {
	
	public List<Temp_Exporter> getExporterList();
	public Temp_TDS_DTL getTDSDtl();
}
