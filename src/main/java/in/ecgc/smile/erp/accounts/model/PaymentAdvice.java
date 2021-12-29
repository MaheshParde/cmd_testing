package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class PaymentAdvice {
	private PaymentAdviceTcodes payTcodes;
	
	
	public PaymentAdvice(PaymentAdviceTcodes payTcodes) {
		super();
		this.payTcodes = payTcodes;
	}
	public PaymentAdvice() {
		
	}

	@NotBlank(message = "{entityCd.required}")
	private String entityCd;
	@NotBlank(message = "{expenseHead.required}")
	private String expenseHead;
	@NotBlank(message = "{logicalLocCd.required}")
	private String logicalLocCd;
	@NotBlank(message = "{sectionCd.required}")
	private String sectionCd;
	private Integer adviceNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate adviceDate;
	private String billNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate billDt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate billIwdDt;
	private String billDesc;
	@Size(max = 4, message = "length cannot be greater than 4 characters")
	private String payToType;
	private String pymtPartyCd;
	private String pymtPartyName;
	@NotEmpty(message = "{currCd.required}")
	private String currCd; 
	private Double adviceAmt;
	private String adviceDesc;
	private String decBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate decDt; 	
	private String decRemark;
	private Double aprvAmt;
	@NotBlank(message = "{adviceStatus.required}")
	private String adviceStatus;
	private Character tdsApplicable;
	private Double taxRate;
	private Double surchargeRate;
	private Double taxDeducted;
	private String fiscalYear;
	private Double cessAmt;
	private Double cessRate;
	private String oldCd;
	private Double adviceAmtOtherCurrINR;
	private Integer currRate;
	private String userName;
	private Double apprBaseAmt;
	private Double serviceTaxAmt;
	private Double othAmt;
	private Integer pymtAprvId;
	private String pymtAprvName;
	private String pymtYear;
	private String noDeductionReason;
	private Character taxInfoFlag;
	private Character provisionFlag;
	private String noProvisionReason;
	private Double provisionalAmt;
	private String branchCd;
	private String liabilityGlTxnNo;
	private String liabilityGlTxnType;
	private Boolean delFlag;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private Integer createdBy;
	private Integer lastUpdatedBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastUpdatedDt;
	private String metaStatus;
	private String metaRemarks;
}
