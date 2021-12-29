package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Data
public class Receipt {
	
	private String logicalLocCode;
	private Integer receiptNumber ;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate receiptDate;	
	@NotBlank(message = "{iwdNumber.required}")
	private String iwdNumber;
	@NotBlank(message = "{remarks.required}")
	private String remarks;
	//@NotBlank(message="{receivedFromCode.required}")
	private String receivedFromCode;
//	@NotBlank(message = "{receivedFromName.required}")
	private String receivedFromName;
	//@NotBlank(message = "{receivedFromAddress.required}")
	private String receivedFromAddress;
	@NotNull(message = "{stampAmount.required}")
	private Double	stampAmount;
	@NotBlank(message = "{instrumentType.required}")
	private String instrumentType; 
	@NotBlank(message = "{instrumentNumber.required}")
	private String instrumentNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate instrumentDate;
	private String drawnOn; 
	private String glTxnType;
	private Integer glTxnNumber; // only numeric
	@NotBlank(message = "{receivedfromType.required}")
	private String receivedfromType;
	@NotNull(message = "{receiptAmount.required}")
	private Double receiptAmount; 
	private String glFlag;
	private String	fiscalYear; 
	private Integer oldReceiptNumber;
	private Integer glTxnNumberOld;	
	private String metaStatus; // changed from ecgcStatus to metaStatus
	private String metaRemarks;	
	private String payInSlip;
	private String entityCd;
	private String productDescription;
	private double hsn;
	private double uom;
	private double qty;
	private double rate;
	private double amount;
	private double discount;
	private double taxableDiscount;
	private String customerGstNo;
	private String ecgcGstNo;
	private String invoiceNo;
	private double cgstAmount;
	private double sgstAmount;
	private double igstAmount;
	private double utgstAmount;
	private double cgstTaxPer;
	private double sgstTaxPer;
	private double igstTaxPer;
	private double utgstTaxPer;
	private String expoState;
	private String branchState;
	private String taxType;
	private String oldInvoiceNo;
	private String updateFlag;
	private String sez;

	private String printFlag;
	private String createdBy;
	private LocalDate createdDate ;
	private String lastUpdatedBy ;
	private LocalDate lastUpdatedDate ;
			
}