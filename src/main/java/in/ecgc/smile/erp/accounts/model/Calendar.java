package in.ecgc.smile.erp.accounts.model;
 
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
   @Data
public class Calendar {

	@NotBlank(message = "{calendarId.required}")
	private String calendarId;
	@NotNull(message = "{closedStatus.required}")
	private Character closedStatus;
	@NotBlank(message = "branchCode.required")
	private String branchCode;
	@NotBlank(message = "logicaLocCode.required")
	private String logicalLocCode;
	@NotBlank(message = "fiscalYear.required")
	private String fiscalYear;
	@NotBlank(message = "month.required")
	private String month;
	@NotBlank(message = "{glTxnType.required}")
	private String glTxnType;
	private String txnTypeName;
	private String description;
	private Character ecgcStatus;
	private String metaRemarks;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private Boolean configFlag;
	
	
	
}
