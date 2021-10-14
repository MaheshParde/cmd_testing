/**
 * 
 */
package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author CMJ-SENG-LAPTOP9
 *
 */
@Data
public class Liability {
	
	@NotBlank(message = "{moduleCd.required}")
	private String moduleCd;
	
	@NotBlank(message = "{mappingCd.required}")
	private String mappingCd;
	
	@NotBlank(message = "{baseAmt.required}")
	private Double baseAmt;
	
	@NotBlank(message = "{logicalLogCd.required}")
	private String logicalLogCd;
	
	private LocalDate txnDt;
	
	private String fiscalYr;
	
	@NotBlank(message = "{isAmtInInr.required}")
	private Boolean isAmtInInr;
	
	private String currCode;
	private String currName;
	private Float exchangeRate;
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
	private Integer userId;
}
