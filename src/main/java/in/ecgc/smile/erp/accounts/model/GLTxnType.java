package in.ecgc.smile.erp.accounts.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 
 * Entity for GL Transaction Type Master
 * 
 * @author sanjali Kesarkar
 * @version 1.0 28-May-2020
 *
 */
public class GLTxnType {
	
	@NotBlank(message = "{glTxnType.required}")
	@Size(min=2, max=3, message = "{glTxnType.size}")
	private String glTxnType;
	@NotBlank(message = "{glTxnType.name.required}")
	private String txnTypeName;
	@NotNull(message = "{glTxnType.active.required}")
	private Boolean active;
	private String description;
	@NotNull(message = "{glTxnType.isConfigurable.required}")
	private Boolean isConfigurable;
	private Integer openingDay;	
	private Character metaStatus;
	private String metaRemarks;
	private String createdBy;
	private Date createdDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	
	public String getGlTxnType() {
		return glTxnType;
	}
	public void setGlTxnType(String glTxnType) {
		this.glTxnType = glTxnType;
	}
	public String getTxnTypeName() {
		return txnTypeName;
	}
	public void setTxnTypeName(String txnTypeName) {
		this.txnTypeName = txnTypeName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsConfigurable() {
		return isConfigurable;
	}
	public void setIsConfigurable(Boolean isConfigurable) {
		this.isConfigurable = isConfigurable;
	}
	public Integer getOpeningDay() {
		return openingDay;
	}
	public void setOpeningDay(Integer openingDay) {
		if(this.isConfigurable == true)
			this.openingDay = openingDay;
	}
	public Character getMetaStatus() {
		return metaStatus;
	}
	public void setMetaStatus(Character metaStatus) {
		this.metaStatus = metaStatus;
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
	public Date getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdatedDt() {
		return lastUpdatedDt;
	}
	public void setLastUpdatedDt(Date lastUpdatedDt) {
		this.lastUpdatedDt = lastUpdatedDt;
	}
	@Override
	public String toString() {
		return "GLTxnType [glTxnType=" + glTxnType + ", txnTypeName=" + txnTypeName + ", active=" + active
				+ ", description=" + description + ", isConfigurable=" + isConfigurable + ", openingDay=" + openingDay
				+ ", metaStatus=" + metaStatus + ", metaRemarks=" + metaRemarks + ", createdBy=" + createdBy
				+ ", createdDt=" + createdDt + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDt=" + lastUpdatedDt + "]";
	}
}
