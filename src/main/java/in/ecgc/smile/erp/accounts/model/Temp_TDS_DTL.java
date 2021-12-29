package in.ecgc.smile.erp.accounts.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Temp_TDS_DTL {
	List<String> sectionOfTDS;
	Map<String, String> stateOfVendor;
	Map<String, String> stateOfSupply;
	Map<String, String> natureOfService;
	Map<String, String> gstType;
	
	public Temp_TDS_DTL(List<String> sectionOfTDS, Map<String, String> stateOfVendor, Map<String, String> stateOfSupply,
			Map<String, String> natureOfService, Map<String, String> gstType) {
		super();
		this.sectionOfTDS = sectionOfTDS;
		this.stateOfVendor = stateOfVendor;
		this.stateOfSupply = stateOfSupply;
		this.natureOfService = natureOfService;
		this.gstType = gstType;
	}
	
	
	
	

}
