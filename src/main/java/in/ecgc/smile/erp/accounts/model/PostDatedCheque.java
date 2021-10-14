package in.ecgc.smile.erp.accounts.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/*
	Entry for post dated cheques
	@author Aakash Narayane
*/
public class PostDatedCheque {
	
	private Integer srNo;
	@NotBlank(message = "{logicalLocCode.required}")
	private String logicalLocCode;
	@NotBlank(message = "{receivedFromCode.required}")
	private String receivedFromCode;
	@NotBlank(message = "{receivedFromName.required}")
	private String receivedFromName;
	@NotBlank(message = "{receivedFromaddr.required}")
	private String receivedFromaddr;
	@NotBlank(message = "{instrumentType.required}")
	private String instrumentType;
	private String instrumentNo;
	@NotNull(message = "{instrumentDate.required}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date instrumentDate;
	@NotNull(message = "{instrumentAmount.required}")
	private Float instrumentAmount;
	private Character instrumentStatus;
	@NotBlank(message = "{drawnOn.required}")
	private String drawnOn;
	private Character metaStatus;
	private String metaRemarks;
	private String createdBy;
	private Date createdDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	
	
	public Integer getSrNo() {
		return srNo;
	}
	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}
	public String getLogicalLocCode() {
		return logicalLocCode;
	}
	public void setLogicalLocCode(String logicalLocCode) {
		this.logicalLocCode = logicalLocCode;
	}
	public String getReceivedFromCode() {
		return receivedFromCode;
	}
	public void setReceivedFromCode(String receivedFromCode) {
		this.receivedFromCode = receivedFromCode;
	}
	public String getReceivedFromName() {
		return receivedFromName;
	}
	public void setReceivedFromName(String receivedFromName) {
		this.receivedFromName = receivedFromName;
	}
	public String getReceivedFromaddr() {
		return receivedFromaddr;
	}
	public void setReceivedFromaddr(String receivedFromaddr) {
		this.receivedFromaddr = receivedFromaddr;
	}
	public String getInstrumentType() {
		return instrumentType;
	}
	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}
	public String getInstrumentNo() {
		return instrumentNo;
	}
	public void setInstrumentNo(String instrumentNo) {
		this.instrumentNo = instrumentNo;
	}
	public Date getInstrumentDate() {
		return instrumentDate;
	}
	public void setInstrumentDate(Date instrumentDate) {
		this.instrumentDate = instrumentDate;
	}
	
	public Float getInstrumentAmount() {
		return instrumentAmount;
	}
	public void setInstrumentAmount(Float instrumentAmount) {
		this.instrumentAmount = instrumentAmount;
	}
	public Character getInstrumentStatus() {
		return instrumentStatus;
	}
	public void setInstrumentStatus(Character instrumentStatus) {
		this.instrumentStatus = instrumentStatus;
	}
	public String getDrawnOn() {
		return drawnOn;
	}
	public void setDrawnOn(String drawnOn) {
		this.drawnOn = drawnOn;
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
		return "PostDatedCheque [logicalLocCode=" + logicalLocCode + ", receivedFromCode=" + receivedFromCode
				+ ", receivedFromName=" + receivedFromName + ", receivedFromaddr=" + receivedFromaddr
				+ ", instrumentType=" + instrumentType + ", instrumentNo=" + instrumentNo + ", instrumentDate="
				+ instrumentDate + ", instrumentStatus=" + instrumentStatus + ", drawnOn=" + drawnOn + ", metaStatus="
				+ metaStatus + ", metaRemarks=" + metaRemarks + ", createdBy=" + createdBy + ", createdDt=" + createdDt
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDt=" + lastUpdatedDt + "]";
	}


	
}
