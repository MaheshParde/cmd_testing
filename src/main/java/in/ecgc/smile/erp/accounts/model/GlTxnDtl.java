package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class GlTxnDtl {	

	private Integer glTxnNo;
	@NotBlank(message = "Required For Transaction")
	private Integer srNo;
	@NotBlank(message = "Required For Transaction")
	private String drCrFlag;
	@NotBlank(message = "Required For Transaction")
	private Double txnAmt;
	private String oldCode;
	private String codeType;
	private String entityGlCd;
	@NotBlank(message = "Required For Transaction")
	private String mainGlCd;
	@NotBlank(message = "Required For Transaction")
	private String subGlCd;
	@NotBlank(message = "GL Txn Type is Required For Transaction")
	private String glTxnType;
	@NotBlank(message = "Required For Transaction")
	private String LogicalLocCd;
	private String plCd;
	private String subBiFurcationCd;
	private String remarks;
	private String t1;
	private String t2;
	private String t3;
	private String t4;
	private String t5;
	private String t6;
	private String t7;
	private String t8;
	private String t9;
	private String 	t10;
	private String t11;
	private String t12;
	LocalDate ad1;
	LocalDate ad2; 
	LocalDate ad3; 
	LocalDate ad4;	
	private String metaStatus;
	private String metaRemarks;
	private String createdBy;
	private Date createdDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	
	
}
