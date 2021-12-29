package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Advertisement {
	private int tendorNo;  
	private String location;
	private String workSpecification;
	private String workType;
	private String advertisementType;
	private String advertisementStatus;
	private String employeeId;
	private String advertisementFor;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date advertisementDate;
	private String workDescription;
	private Double estimatedPrice;
	private String createdBy;
	private Date createdDate;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;
	private String metaStatus;
	private String metaRemarks;
	private Boolean submitFlag;
	private String worktype;

}
