package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AutoGLTxnRequestModel {

	private Integer requestNo;
	private String moduleCd;
	private String accountCd;
	private Double baseAmt;
	private Double advanceAmt;
	private Double billAmt;
	private String logicalLogCd;
	private LocalDate txnDt;
	private String fiscalYr;
	private String reqStatus;
	private String t1;
	private String t2;
	private String t3;
	private String t4;
	private String t5;
	private String t6;
	private String t7;
	private String t8;
	private String t9;
	private String t10;
	private String t11;
	private String t12;
	private LocalDate ad1;
	private LocalDate ad2;
	private LocalDate ad3;
	private LocalDate ad4;
	private String remarks;
	private String createdBy;
	private String lastUpdatedBy;
	private String metaRemarks; 
}
