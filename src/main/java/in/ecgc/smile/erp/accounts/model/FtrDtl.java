package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FtrDtl {

	//@NotBlank(message = "{requestNo.required}")
	private Integer FTRRequestNo;
	@NotNull(message = "{requestAmount.required}")
	private Float FTRRequestAmount;
	@NotNull(message = "{FTRRequeatType.required}")
	private String FTRRequestType;
	@NotNull(message = "{FTRRequestReason.required}")
	private String FTRRequestReason;
	private String ecibIntlBankNAme;
	private String ecibClaimNo;
	private LocalDate expEcibDatePayment;
	private String ecibType;
	private LocalDate expPolicyDatePAyment;
	private String policyClaimNo;
	private String PolicyType;
	private String exporterName;
	private String ieCode;
	private Integer FTRRequestSrNo;
	@NotBlank(message = "branchCode.required")
	private String branchCode;
	@NotBlank(message = "logicaLocCode.required")
	private String logicalLocCode;
	private Character ecgcStatus;
	private String metaRemarks;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private String ftrFor;
	public Integer getFTRRequestNo() {
		return FTRRequestNo;
	}
	public void setFTRRequestNo(Integer fTRRequestNo) {
		FTRRequestNo = fTRRequestNo;
	}
	public Float getFTRRequestAmount() {
		return FTRRequestAmount;
	}
	public void setFTRRequestAmount(Float fTRRequestAmount) {
		FTRRequestAmount = fTRRequestAmount;
	}
	public String getFTRRequestType() {
		return FTRRequestType;
	}
	public void setFTRRequestType(String fTRRequestType) {
		FTRRequestType = fTRRequestType;
	}
	public String getFTRRequestReason() {
		return FTRRequestReason;
	}
	public void setFTRRequestReason(String fTRRequestReason) {
		FTRRequestReason = fTRRequestReason;
	}
	public String getEcibIntlBankNAme() {
		return ecibIntlBankNAme;
	}
	public void setEcibIntlBankNAme(String ecibIntlBankNAme) {
		this.ecibIntlBankNAme = ecibIntlBankNAme;
	}
	public String getEcibClaimNo() {
		return ecibClaimNo;
	}
	public void setEcibClaimNo(String ecibClaimNo) {
		this.ecibClaimNo = ecibClaimNo;
	}
	public LocalDate getExpEcibDatePayment() {
		return expEcibDatePayment;
	}
	public void setExpEcibDatePayment(LocalDate expEcibDatePayment) {
		this.expEcibDatePayment = expEcibDatePayment;
	}
	public String getEcibType() {
		return ecibType;
	}
	public void setEcibType(String ecibType) {
		this.ecibType = ecibType;
	}
	public LocalDate getExpPolicyDatePAyment() {
		return expPolicyDatePAyment;
	}
	public void setExpPolicyDatePAyment(LocalDate expPolicyDatePAyment) {
		this.expPolicyDatePAyment = expPolicyDatePAyment;
	}
	public String getPolicyClaimNo() {
		return policyClaimNo;
	}
	public void setPolicyClaimNo(String policyClaimNo) {
		this.policyClaimNo = policyClaimNo;
	}
	public String getPolicyType() {
		return PolicyType;
	}
	public void setPolicyType(String policyType) {
		PolicyType = policyType;
	}
	public String getExporterName() {
		return exporterName;
	}
	public void setExporterName(String exporterName) {
		this.exporterName = exporterName;
	}
	public String getIeCode() {
		return ieCode;
	}
	public void setIeCode(String ieCode) {
		this.ieCode = ieCode;
	}
	public Integer getFTRRequestSrNo() {
		return FTRRequestSrNo;
	}
	public void setFTRRequestSrNo(Integer fTRRequestSrNo) {
		FTRRequestSrNo = fTRRequestSrNo;
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
	public String getFtrFor() {
		return ftrFor;
	}
	public void setFtrFor(String ftrFor) {
		this.ftrFor = ftrFor;
	}
	@Override
	public String toString() {
		return "FtrDtl [FTRRequestNo=" + FTRRequestNo + ", FTRRequestAmount=" + FTRRequestAmount + ", FTRRequestType="
				+ FTRRequestType + ", FTRRequestReason=" + FTRRequestReason + ", ecibIntlBankNAme=" + ecibIntlBankNAme
				+ ", ecibClaimNo=" + ecibClaimNo + ", expEcibDatePayment=" + expEcibDatePayment + ", ecibType="
				+ ecibType + ", expPolicyDatePAyment=" + expPolicyDatePAyment + ", policyClaimNo=" + policyClaimNo
				+ ", PolicyType=" + PolicyType + ", exporterName=" + exporterName + ", ieCode=" + ieCode
				+ ", FTRRequestSrNo=" + FTRRequestSrNo + ", branchCode=" + branchCode + ", logicalLocCode="
				+ logicalLocCode + ", ecgcStatus=" + ecgcStatus + ", metaRemarks=" + metaRemarks + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn
				+ ", ftrFor=" + ftrFor + "]";
	}
	
	
		
	

}
