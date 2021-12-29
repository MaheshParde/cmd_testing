package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PaymentAdviceGstTdsDtl {

	@NotBlank(message = "{entityCd.required}")
	private String entityCd;
	@NotBlank(message = "{logicalLocCd.required}")
	private String logicalLocCd;
	@NotBlank(message = "{sectionCd.required}")
	private	String	sectionCd;
	private	Integer	adviceNo;
	private	Integer	paymentNo;
	private	String	payToType;
	private	String	pymtPartyCd;
	private	String	fiscalYr;
	private	String	status;
	private	Integer	createdBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private	LocalDate	lastUpdatedDt;
	private	Double	approvedBaseAmt;
	private	String	stateVendor;
	private	String	gstVen;
	private	String	stateSupply;
	private	Double	cgstTdsRate;
	private	Double	cgstTdsAmt;
	private	Double	sgstTdsRate;
	private	Double	sgstTdsAmt;
	private	Double	igstTdsRate;
	private	Double	igstTdsAmt;
	private	Double	utgstTdsRate;
	private	Double	utgstTdsAmt;
	private	Character	gstTdsApplicable;
	private	Double	taxRate;
	private	String	gstTdsType;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private	LocalDate	createdDt;
	private	Integer	lastUpdatedBy;
	private	String	metaStatus;
	private	String	metaRemarks;

}
