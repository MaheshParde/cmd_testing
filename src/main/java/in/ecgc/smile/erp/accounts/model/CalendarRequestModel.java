package in.ecgc.smile.erp.accounts.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CalendarRequestModel {

	private Integer requestId;
	private String calendarId;
	
	private Integer requestedBy;
	private Integer approvedBy;
	private String fiscalYr;
	private String month;
	private String glTxnType;
	private String requestStatus;
	private String remark;
	private Character ecgcStatus;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdOn;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updatedOn;
	private String logicalLocCode;
	private String metaRemarks;
	private String branchCode;
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public Integer getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}
	public Integer getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getFiscalYr() {
		return fiscalYr;
	}
	public void setFiscalYr(String fiscalYr) {
		this.fiscalYr = fiscalYr;
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
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Character getEcgcStatus() {
		return ecgcStatus;
	}
	public void setEcgcStatus(Character ecgcStatus) {
		this.ecgcStatus = ecgcStatus;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getLogicalLocCode() {
		return logicalLocCode;
	}
	public void setLogicalLocCode(String logicalLocCode) {
		this.logicalLocCode = logicalLocCode;
	}
	public String getMetaRemarks() {
		return metaRemarks;
	}
	public void setMetaRemarks(String metaRemarks) {
		this.metaRemarks = metaRemarks;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	@Override
	public String toString() {
		return "CalendarRequestModel [requestId=" + requestId + ", calendarId=" + calendarId + ", requestedBy="
				+ requestedBy + ", approvedBy=" + approvedBy + ", fiscalYr=" + fiscalYr + ", month=" + month
				+ ", glTxnType=" + glTxnType + ", requestStatus=" + requestStatus + ", remark=" + remark
				+ ", ecgcStatus=" + ecgcStatus + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+ ", logicalLocCode=" + logicalLocCode + ", metaRemarks=" + metaRemarks + ", branchCode=" + branchCode
				+ "]";
	}
	
}
