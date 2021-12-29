package in.ecgc.smile.erp.accounts.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class ChqDishonorEntry
{
	
	@NotBlank(message="{logicalLocCd.required}")
	private String logicalLocCd;
	
	@NotNull(message="{rcptNo.required}")
	private Integer rcptNo;
	
	private Integer oldRcptNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private  LocalDate dishonorDt;
	
	@NotBlank(message="{instrumentNo.required}")
	private String instrumentNo;
	
	@NotBlank(message="{instrumentType.required}")
	private String instrumentType;
	
	@NotBlank(message="{dishonorReason.required}")
	private String dishonorReason;
	
	private String glTxnNo;
	
	@NotBlank(message="{fiscalYr.required}")
	private String fiscalYr;
	private String createdBy;
	private LocalDate createdDt;
	private String lastUpdatedBy;
	private LocalDate lastUpdatedDt;
	private String metaStatus;
	private String metaRemarks;
	
	
}
