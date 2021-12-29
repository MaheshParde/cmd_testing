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
	
	@NotBlank(message = "Logical location code is required ")
	private String logicalLocCode;
	
	private Long processIsntanceId;
	
	private String remark;
	
	@NotNull(message = "Cash amount is required")
	private double cashAmt;
	
	@NotBlank(message = "Entity GL Cide required")
	private String entityCd;
	
	@NotBlank(message = "Fiscal year is required")
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
