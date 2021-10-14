package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class SubBifurcations {

	Integer srNo;
	String entityCd;
	String subBifurcationLevel;
	String value;
	String description;
	private String metaStatus;
	private String metaRemarks;
	private String createdBy;
	private LocalDate createdDt;
	private String lastUpdatedBy;
	private LocalDate lastUpdatedDt;
	
	
	
}
