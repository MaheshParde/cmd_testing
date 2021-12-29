package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 
 * Stamp Parameter Model
 * 
 * @author Amitesh Banerjee
 * @version 1.0 27-April-2020
 *
 */
@Data
public class StampParameterModel {

	private Integer srNo; 
	@NotNull(message = "Stamp From Amount is required")
	@DecimalMin("0.00")
	@DecimalMax("9999999999.00")
	private Double fromAmount;
	@NotNull(message = "Stamp To Amount is required")
	@DecimalMin("0.00")
	@DecimalMax("9999999999.00")
	private Double toAmount;
	@NotNull(message = "Stamp Amount is required")
	@DecimalMin("0.00")
	@DecimalMax("9999999999.00")
	private Double stampAmount;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Effective date is required")
	private LocalDate effectiveDate;
	private Boolean active;
	private String createdBy;
	private LocalDateTime createdOn;
	private String updatedBy;
	private LocalDateTime updatedOn;
	private Character ecgcStatus;
	private String metaRemarks;
	
		
}
