package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PaymentAdviceTdsDtl {

	@NotBlank(message = "{entityCd.required}")
	private String entityCd;
	@NotBlank(message = "{expenseHead.required}")
	private String expenseHead;
	@NotBlank(message = "{logicalLocCd.required}")
	private String logicalLocCd;
	@NotBlank(message = "{sectionCd.required}")
	private	String	sectionCd;
	private	Integer	adviceNo;
	private	Integer	paymentNo;
	private	String	payToType;
	private	String	pymtPartyCd;
	private	String	fiscalYr;
	private	Double	taxAmt;
	private	Double	surchargeAmt;
	private	Integer	remittanceRefNo;
	private	String	status;
	private	Integer	tdsFromAdviceNo;
	private	String	tdsFromSecCd;
	private	Integer	createdBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private	LocalDate	lastUpdatedDt;
	private	Double	cessAmt;
	private	String	oldCd;
	private	String	tdsSection;
	private	String	tdsDesc;
	private	Double	tdsRate;
	private	String	othRateRemarks;
	private	Double	approvedBaseAmt;
	private	Double	serviceTaxAmt;
	private	Double	otherAmt;
	private	Character	revChargeApp;
	private	String	natureOfService;
	private	Double	rcServiceTaxAmt;
	private	Double	rcPrimEduCessAmt;
	private	Double	rcSecEduCessAmt;
	private	String	tdsRemarks;
	private	Double	rcServiceTaxRate;
	private	Double	rcServiceTaxPerc;
	private	Double	rcSwachBharatCessAmt;
	private	Double	rcKrishiKalyanCessAmt;
	private	String	gstType;
	private	Double	cgstTax;
	private	Double	sgstTax;
	private	Double	igsttax;
	private	Double	utgstTax;
	private	Double	cgstTaxAmt;
	private	Double	sgstTaxAmt;
	private	Double	igstTaxAmt;
	private	Double	utgstTaxAmt;
	private	String	gstSelState;
	private	String	invoiceNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private	LocalDate	createdDt;
	private	Integer	lastUpdatedBy;
	private	String	metaStatus;
	private	String	metaRemarks;
}
