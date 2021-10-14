package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EntityGL {
	
	
	private Integer entityGlCd;
	
	@NotBlank(message = "This is Required")
	private String mainglCd;
	
	@NotBlank(message = "This is Required")
	private String subglCd;
	
	@NotBlank(message = "This is Required")
	private String glName;
	
	private Character glIsGroup;
	
	@NotBlank(message = "This is Required")
	private String glType;
	
	@NotBlank(message = "This is Required")
	private String glSubtype;
	private String balInd;
	private Character zeroBalFlg;
	private Character active;
	private Integer cashFlow;
	
	private String logicalLocCd;
	
	private String plLevel;
	private Character t1;
	private Character t2;
	private Character t3;
	private Character t4;
	private Character t5;
	private Character t6;
	private Character t7;
	private Character t8;
	private Character t9;
	private Character t10;
	private Character t11;
	private Character t12;
	private String irdaBpaCd;
	private String irdaCd;
	private String schedule6;
	private String financialFormName;
	private String subBifurcationLevel;
	private Integer cashaccount;
	private Integer createdBy;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate createdDt;
	private Integer lastUpdatedBy;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate lastUpdatedDt;
	private String metaRemarks;
	private String oldCd;
	
}
