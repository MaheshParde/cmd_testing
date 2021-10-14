package in.ecgc.smile.erp.accounts.model;

import java.util.Date;

/**
 * 
 * Stamp Parameter Model
 * 
 * @author Amitesh Banerjee
 * @version 1.0 27-April-2020
 *
 */

public class StampParameterModel {

	private Integer srNo; 
	private Double fromAmount;
	private Double toAmount;
	private Double stampAmount;
	private String description;
	private Date effectiveDate;
	private Boolean active;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private Character ecgcStatus;
	private String metaRemarks;
	
	public Integer getSrNo() {
		return srNo;
	}
	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}
	public Double getFromAmount() {
		return fromAmount;
	}
	public void setFromAmount(Double fromAmount) {
		this.fromAmount = fromAmount;
	}
	public Double getToAmount() {
		return toAmount;
	}
	public void setToAmount(Double toAmount) {
		this.toAmount = toAmount;
	}
	public Double getStampAmount() {
		return stampAmount;
	}
	public void setStampAmount(Double stampAmount) {
		this.stampAmount = stampAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
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
	public String getMetaRemarks() {
		return metaRemarks;
	}
	public void setMetaRemarks(String metaRemarks) {
		this.metaRemarks = metaRemarks;
	}
	@Override
	public String toString() {
		return "StampParameterModel [srNo=" + srNo + ", fromAmount=" + fromAmount + ", toAmount=" + toAmount
				+ ", stampAmount=" + stampAmount + ", description=" + description + ", effectiveDate=" + effectiveDate
				+ ", active=" + active + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ", ecgcStatus=" + ecgcStatus + ", metaRemarks=" + metaRemarks
				+ "]";
	}
	
}
