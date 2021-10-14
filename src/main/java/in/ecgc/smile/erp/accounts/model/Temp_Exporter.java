package in.ecgc.smile.erp.accounts.model;

import lombok.Data;

@Data
public class Temp_Exporter {

	public String ieCd;
	public String expoName;
	public String expoCity;
	public String expoState;
	public Integer expoPinCd;
	public String expoType;
	public String expoPanNo;
	public String logicalLocCd;
	public String regionCd;
	public String branchCd;
	public Integer areaCd;
	
	public Temp_Exporter(String ieCd, String expoName, String expoCity, String expoState, Integer expoPinCd,
			String expoType, String expoPanNo, String logicalLocCd, String regionCd, String branchCd, Integer areaCd) {
		super();
		this.ieCd = ieCd;
		this.expoName = expoName;
		this.expoCity = expoCity;
		this.expoState = expoState;
		this.expoPinCd = expoPinCd;
		this.expoType = expoType;
		this.expoPanNo = expoPanNo;
		this.logicalLocCd = logicalLocCd;
		this.regionCd = regionCd;
		this.branchCd = branchCd;
		this.areaCd = areaCd;
	}

	
}
