package in.ecgc.smile.erp.accounts.model;

import java.util.List;
import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FTR {
	//@NotBlank(message = "ftrReqNo.required")
	private Integer ftrReqNo;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "{ftrReqDt.required}")
	private LocalDate ftrReqDt;
	private String ftrReqBranchCd;
	private Integer ftrReqBy;
	@NotBlank(message = "{ftrReqDeptCd.required}")
	private String ftrReqDeptCd;
	private String ftrReqStatus;
	@NotBlank(message = "logicaLocCode.required")
	private String logicalLocCode;
	private Character ecgcStatus;
	private String metaRemarks;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private Integer ftrApprBy;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "{ftrTrfDt.required}")
	private LocalDate ftrTrfDt;
	@NotBlank(message = "{ftrTrfAmt.required}")
	@PositiveOrZero(message = "{ftrTrfAmt.positive.required}")
	private Double ftrTrfAmt;
	private String pvStatus;
	@NotBlank(message = "{ftrType.required}")
	private String ftrType;
	private String reqTo;
	@NotBlank(message = "{ftrDtl.required}")
	private List<FtrDtl> ftrDtl;
	
	
	
	
	
	
	
		
}
