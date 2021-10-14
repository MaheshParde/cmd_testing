package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PaymentAdviceTcodes {
	@NotBlank(message = "{entityCd.required}")
	private String entityCd;
	@NotBlank(message = "{logicalLocCd.required}")
	private String logicalLocCd;
	@NotBlank(message = "{sectionCd.required}")
	private String sectionCd;
	@NotNull(message = "{adviceNo.required}")
	private Integer adviceNo;
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate ad1;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate ad2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate ad3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate ad4;
	private Boolean delFlag;
		
	
	
}
