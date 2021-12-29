package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FiscalYearModel {
	
	@NotBlank(message = "{currFiscalYear.required}")
	@Size(max = 9)
	private String currFiscalYear;
	@NotNull(message = "{currFiscalYearStartDt.required}")
	private LocalDate currFiscalYearStartDt;
	@NotBlank(message = "{prevFiscalYear.required}")
	@Size(max = 9)
	private String prevFiscalYear;
	@NotNull(message = "{prevFiscalYearClosedDt.required}")
	private LocalDate prevFiscalYearClosedDt;
	@NotBlank(message = "{metaStatus.required}")
	@Size(max = 1)
	private Character metaStatus;
	private String metaRemarks;
	private String createdBy;
	private Date createdDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	public String getCurrFiscalYear() {
		return currFiscalYear;
	}
	public void setCurrFiscalYear(String currFiscalYear) {
		this.currFiscalYear = currFiscalYear;
	}
	public LocalDate getCurrFiscalYearStartDt() {
		return currFiscalYearStartDt;
	}
	public void setCurrFiscalYearStartDt(LocalDate currFiscalYearStartDt) {
		this.currFiscalYearStartDt = currFiscalYearStartDt;
	}
	public String getPrevFiscalYear() {
		return prevFiscalYear;
	}
	public void setPrevFiscalYear(String prevFiscalYear) {
		this.prevFiscalYear = prevFiscalYear;
	}
	public LocalDate getPrevFiscalYearClosedDt() {
		return prevFiscalYearClosedDt;
	}
	public void setPrevFiscalYearClosedDt(LocalDate prevFiscalYearClosedDt) {
		this.prevFiscalYearClosedDt = prevFiscalYearClosedDt;
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
		return "FiscalYearModel [currFiscalYear=" + currFiscalYear + ", currFiscalYearStartDt=" + currFiscalYearStartDt
				+ ", prevFiscalYear=" + prevFiscalYear + ", prevFiscalYearClosedDt=" + prevFiscalYearClosedDt
				+ ", metaStatus=" + metaStatus + ", metaRemarks=" + metaRemarks + ", createdBy=" + createdBy
				+ ", createdDt=" + createdDt + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDt=" + lastUpdatedDt
				+ "]";
	}
	
	
}
