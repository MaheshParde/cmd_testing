package in.ecgc.smile.erp.accounts.model;

import java.util.Date;

public class Schedule {

	private String scheduleCode;
	private String scheduleItemCode;
	private String description;
	private boolean titelDetailLine;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private Character ecgcStatus;
	private String metaRemark;
	private Integer srNo;
	private String prifix;
	private String total;
	
	
	
	/*
	 * public Schedule(String scheduleCode, String scheduleItemCode, String
	 * description, boolean titelDetailLine, Integer createdBy, Date createdOn,
	 * Integer updatedBy, Date updatedOn, Character ecgcStatus, String metaRemark,
	 * Integer srNo, String prifix, String total) { super(); this.scheduleCode =
	 * scheduleCode; this.scheduleItemCode = scheduleItemCode; this.description =
	 * description; this.titelDetailLine = titelDetailLine; this.createdBy =
	 * createdBy; this.createdOn = createdOn; this.updatedBy = updatedBy;
	 * this.updatedOn = updatedOn; this.ecgcStatus = ecgcStatus; this.metaRemark =
	 * metaRemark; this.srNo = srNo; this.prifix = prifix; this.total = total; }
	 */
	public String getScheduleCode() {
		return scheduleCode;
	}
	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
	public String getScheduleItemCode() {
		return scheduleItemCode;
	}
	public void setScheduleItemCode(String scheduleItemCode) {
		this.scheduleItemCode = scheduleItemCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isTitelDetailLine() {
		return titelDetailLine;
	}
	public void setTitelDetailLine(boolean titelDetailLine) {
		this.titelDetailLine = titelDetailLine;
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
	public Character getEcgcStatus() {
		return ecgcStatus;
	}
	public void setEcgcStatus(Character ecgcStatus) {
		this.ecgcStatus = ecgcStatus;
	}
	public String getMetaRemark() {
		return metaRemark;
	}
	public void setMetaRemark(String metaRemark) {
		this.metaRemark = metaRemark;
	}
	public Integer getSrNo() {
		return srNo;
	}
	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}
	public String getPrifix() {
		return prifix;
	}
	public void setPrifix(String prifix) {
		this.prifix = prifix;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Schedule [scheduleCode=" + scheduleCode + ", scheduleItemCode=" + scheduleItemCode + ", description="
				+ description + ", titelDetailLine=" + titelDetailLine + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + ", ecgcStatus=" + ecgcStatus
				+ ", metaRemark=" + metaRemark + ", srNo=" + srNo + ", prifix=" + prifix + ", total=" + total + "]";
	}
	
	
}
