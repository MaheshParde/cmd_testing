package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SubBifurcations {

	///Integer srNo;
	//String entityCd;
	@NotBlank(message = "{subBifurcationLevel.required}")
	@Size(max = 3, min = 3 ,message = "Sub Bifurcation Level Code Should be of 3 Characters")
	String subBifurcationLevel;
	//@NotBlank(message = "{value.required}")
//	String value;
	String description;
	private String metaStatus;
	private String metaRemarks;
	private String createdBy;
	private LocalDate createdDt;
	private String lastUpdatedBy;
	private LocalDate lastUpdatedDt;
	private char active;
	
	
	
}
