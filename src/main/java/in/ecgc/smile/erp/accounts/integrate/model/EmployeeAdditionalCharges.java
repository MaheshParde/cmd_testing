package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeAdditionalCharges {

	private Integer addChargeId;
	private Integer empNo;
	private String desigId;
	private String deptCd;
	
	private String officeId;
	private String respTypeCd;
	private Integer reptTo;
	private Date effectiveFrom;
	private Date effectiveTo;
	private String createdBy;
	private Date createdDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	private String responsibility;
	
}
