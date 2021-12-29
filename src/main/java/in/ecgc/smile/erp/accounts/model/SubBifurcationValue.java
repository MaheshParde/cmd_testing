package in.ecgc.smile.erp.accounts.model;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class SubBifurcationValue
{
	
	@NotBlank(message="{bifurcationLevelCode.required}")
//	@Size(min=0,max=2147483647,message="{bifurcationLevelCode.size}")
	private String bifurcationLevelCode;
	
	@NotBlank(message="{subBifurcationLevel.required}")
//	@Size(min=0,max=2147483647,message="{subBifurcationLevel.size}")
	private String subBifurcationLevel;
	
	@NotBlank(message="{bifurcationValue.required}")
//	@Size(min=0,max=2147483647,message="{bifurcationValue.size}")
	private String bifurcationValue;
	@NotBlank(message="{bifurcationValueCode.required}")
	@Size(min=0,max=6,message="{bifurcationValueCode.size}")
	private String bifurcationValueCode;
	
	//@Size(min=0,max=50,message="{createdBy.size}")
	private String createdBy;
	
//	@Size(min=0,max=29,message="{createdDt.size}")
	private LocalDate createdDt;
	
	//@Size(min=0,max=50,message="{lastUpdatedBy.size}")
	private String lastUpdatedBy;
	
//	@Size(min=0,max=29,message="{lastUpdatedDt.size}")
	private LocalDate lastUpdatedDt;
	
	//@Size(min=0,max=2147483647,message="{metaStatus.size}")
	private String metaStatus;
	
	//@Size(min=0,max=2147483647,message="{metaRemarks.size}")
	private String metaRemarks;
	
	private char active;
}
