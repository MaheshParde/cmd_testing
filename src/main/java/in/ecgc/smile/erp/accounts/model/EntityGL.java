package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EntityGL {
	
	
	private String entityGlCd;
	
	@NotBlank(message = "MainGL Code is Required")
	private String mainglCd;
	
	@NotBlank(message = "Sub GL Code is Required")
	private String subglCd;
	
//	@NotBlank(message = "GL Name is Required")
	private String glName;
	
	private Character glIsGroup;
	
	@NotBlank(message = "GL Type is Required")
	private String glType;
	
//	@NotBlank(message = "GL Sub Type is Required")
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
	private Character t13;
	private Character t14;
	
//	@NotBlank(message = "IRDABPA Code is Required")
	private String irdaBpaCd;
	
//	@NotBlank(message = "IRDA Code is Required")
	private String irdaCd;
	
//	@NotBlank(message = "Schedule code is Required")
	private String schedule6;
	
//	@NotBlank(message = "Financial Form Name is Required")
	private String financialFormName;
	
//	@NotBlank(message = "Sub Bifurcation Level is Required")
	private String subBifurcationLevel;
	private Integer cashaccount;
	private String createdBy;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate createdDt;
	private String lastUpdatedBy;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate lastUpdatedDt;
	private String metaRemarks;
	private String oldCd;
	
}
