package in.ecgc.smile.erp.accounts.model;

import java.util.List;
import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

public class FTR {
	//@NotBlank(message = "ftrReqNo.required")
	private Integer ftrReqNo;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "{ftrReqDt.required}")
	private LocalDate ftrReqDt;
	private String ftrReqBranchCd;
	private Integer ftrReqBy;
	@NotBlank(message = "{ftrReqDeptCd.required}")
	private String ftrReqDeptCd;
	private String ftrReqStatus;
	@NotBlank(message = "logicaLocCode.required")
	private String logicalLocCode;
	private Character ecgcStatus;
	private String metaRemarks;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private Integer ftrApprBy;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "{ftrTrfDt.required}")
	private LocalDate ftrTrfDt;
	@NotBlank(message = "{ftrTrfAmt.required}")
	@PositiveOrZero(message = "{ftrTrfAmt.positive.required}")
	private Double ftrTrfAmt;
	private String pvStatus;
	@NotBlank(message = "{ftrType.required}")
	private String ftrType;
	private String reqTo;
	@NotBlank(message = "{ftrDtl.required}")
	private List<FtrDtl> ftrDtl;
	public Integer getFtrReqNo() {
		return ftrReqNo;
	}
	public void setFtrReqNo(Integer ftrReqNo) {
		this.ftrReqNo = ftrReqNo;
	}
	public LocalDate getFtrReqDt() {
		return ftrReqDt;
	}
	public void setFtrReqDt(LocalDate ftrReqDt) {
		this.ftrReqDt = ftrReqDt;
	}
	public String getFtrReqBranchCd() {
		return ftrReqBranchCd;
	}
	public void setFtrReqBranchCd(String ftrReqBranchCd) {
		this.ftrReqBranchCd = ftrReqBranchCd;
	}
	public Integer getFtrReqBy() {
		return ftrReqBy;
	}
	public void setFtrReqBy(Integer ftrReqBy) {
		this.ftrReqBy = ftrReqBy;
	}
	public String getFtrReqDeptCd() {
		return ftrReqDeptCd;
	}
	public void setFtrReqDeptCd(String ftrReqDeptCd) {
		this.ftrReqDeptCd = ftrReqDeptCd;
	}
	public String getFtrReqStatus() {
		return ftrReqStatus;
	}
	public void setFtrReqStatus(String ftrReqStatus) {
		this.ftrReqStatus = ftrReqStatus;
	}
	public String getLogicalLocCode() {
		return logicalLocCode;
	}
	public void setLogicalLocCode(String logicalLocCode) {
		this.logicalLocCode = logicalLocCode;
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
	public Integer getFtrApprBy() {
		return ftrApprBy;
	}
	public void setFtrApprBy(Integer ftrApprBy) {
		this.ftrApprBy = ftrApprBy;
	}
	public LocalDate getFtrTrfDt() {
		return ftrTrfDt;
	}
	public void setFtrTrfDt(LocalDate ftrTrfDt) {
		this.ftrTrfDt = ftrTrfDt;
	}
	public Double getFtrTrfAmt() {
		return ftrTrfAmt;
	}
	public void setFtrTrfAmt(Double ftrTrfAmt) {
		this.ftrTrfAmt = ftrTrfAmt;
	}
	public String getPvStatus() {
		return pvStatus;
	}
	public void setPvStatus(String pvStatus) {
		this.pvStatus = pvStatus;
	}
	public String getFtrType() {
		return ftrType;
	}
	public void setFtrType(String ftrType) {
		this.ftrType = ftrType;
	}
	public String getReqTo() {
		return reqTo;
	}
	public void setReqTo(String reqTo) {
		this.reqTo = reqTo;
	}
	public List<FtrDtl> getFtrDtl() {
		return ftrDtl;
	}
	public void setFtrDtl(List<FtrDtl> ftrDtl) {
		this.ftrDtl = ftrDtl;
	}
	@Override
	public String toString() {
		return "FTR [ftrReqNo=" + ftrReqNo + ", ftrReqDt=" + ftrReqDt + ", ftrReqBranchCd=" + ftrReqBranchCd
				+ ", ftrReqBy=" + ftrReqBy + ", ftrReqDeptCd=" + ftrReqDeptCd + ", ftrReqStatus=" + ftrReqStatus
				+ ", logicalLocCode=" + logicalLocCode + ", ecgcStatus=" + ecgcStatus + ", metaRemarks=" + metaRemarks
				+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn="
				+ updatedOn + ", ftrApprBy=" + ftrApprBy + ", ftrTrfDt=" + ftrTrfDt + ", ftrTrfAmt=" + ftrTrfAmt
				+ ", pvStatus=" + pvStatus + ", ftrType=" + ftrType + ", reqTo=" + reqTo + ", ftrDtl=" + ftrDtl + "]";
	}
	
	
	
	
	
	
		
}
