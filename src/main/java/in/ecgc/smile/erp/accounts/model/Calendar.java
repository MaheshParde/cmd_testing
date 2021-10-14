package in.ecgc.smile.erp.accounts.model;
 
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
   
public class Calendar {

	@NotBlank(message = "{calendarId.required}")
	private String calendarId;
	@NotNull(message = "{closedStatus.required}")
	private Character closedStatus;
	@NotBlank(message = "branchCode.required")
	private String branchCode;
	@NotBlank(message = "logicaLocCode.required")
	private String logicalLocCode;
	@NotBlank(message = "fiscalYear.required")
	private String fiscalYear;
	@NotBlank(message = "month.required")
	private String month;
	@NotBlank(message = "{glTxnType.required}")
	private String glTxnType;
	private String txnTypeName;
	private String description;
	private Character ecgcStatus;
	private String metaRemarks;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private Character configFlag;
	
	public Character getConfigFlag() {
		return configFlag;
	}
	public void setConfigFlag(Character configFlag) {
		this.configFlag = configFlag;
	}
	public Calendar() {
		super();
	}
	public Calendar(@NotBlank(message = "{calendarId.required}") String calendarId) {
		super();
		this.calendarId = calendarId;
	}
	
	
	public String getTxnTypeName() {
		return txnTypeName;
	}
	public void setTxnTypeName(String txnTypeName) {
		this.txnTypeName = txnTypeName;
	}
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public Character getClosedStatus() {
		return closedStatus;
	}
	public void setClosedStatus(Character closedStatus) {
		this.closedStatus = closedStatus;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getLogicalLocCode() {
		return logicalLocCode;
	}
	public void setLogicalLocCode(String logicalLocCode) {
		this.logicalLocCode = logicalLocCode;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getGlTxnType() {
		return glTxnType;
	}
	public void setGlTxnType(String glTxnType) {
		this.glTxnType = glTxnType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Character getEcgcStatus() {
		return ecgcStatus;
	}
	public void setEcgcStatus(Character ecgcStatus) {
		this.ecgcStatus = ecgcStatus;
	}
	public String getMetaRemarks() {
		return metaRemarks;
	}
	public void setMetaRemarks(String metaRemarks) {
		this.metaRemarks = metaRemarks;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	@Override
	public String toString() {
		return "Calendar [calendarId=" + calendarId + ", closedStatus=" + closedStatus + ", branchCode=" + branchCode
				+ ", logicalLocCode=" + logicalLocCode + ", fiscalYear=" + fiscalYear + ", month=" + month
				+ ", glTxnType=" + glTxnType + ", description=" + description + ", ecgcStatus=" + ecgcStatus
				+ ", metaRemarks=" + metaRemarks + ", createdBy=" + createdBy + ", createdOn=" + createdOn
				+ ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
	}
	
	
	
}
