package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PettyCashMaster {
	
	@NotBlank(message = "{logicalLocCode.required}")
	private String logicalLocCode;
	
	private String remark;
	
	@NotNull(message = "{cashAmt.required}")
	private double cashAmt;
	
	@NotBlank(message = "{entityCd.required}")
	private String entityCd;
	
	@NotBlank(message = "{fiscalYr.required}")
	private String fiscalYr;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate requestDt;
	
	private Integer requisitionNo;
	
	private String createdBy;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDt;
	
	private String reqStatus;
	
	private String lastUpdatedBy;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastUpdatedDt;

}
