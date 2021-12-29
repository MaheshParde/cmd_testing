package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExternalAgency {

	private int agencyId;

	private String agencyName;

	private String agencyRegistrationNo;

	private boolean agencyStatus;

	private String headQuarterCity;

	private String agencyAddress;

	private String agencyEmailId;

	private String agencyCountry;

	private String taxResidencyCertificate;

	private MultipartFile taxResidencyCertificateFile;

	private String typeOfBusiness;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date foundationDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date buMemberSinceDate;

	private String revenueInfo;

	private String ownership;

	private String srMgmt;

	private String parentCompany;
	private boolean financeFlag;
	private boolean insuranceFlag;
	private boolean regulatedFlag;
	private String regulatorName;
	private boolean subjectToTaxation;
	private boolean subjectToDividends;

	private String majorFacilities;

	private String metaStatus;
	private String createdBy;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDt;

	private String lastUpdatedBy;
	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastUpdatedDt;
	private String metaRemarks;
	private String trcDmsId;
	private boolean oecdMemberFlag;
	private boolean govtSupportFlag;
	private String reportingTo;
	private String agencyModel;
	private String capitalSource;
	private Double capitalValue;

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyRegistrationNo() {
		return agencyRegistrationNo;
	}

	public void setAgencyRegistrationNo(String agencyRegistrationNo) {
		this.agencyRegistrationNo = agencyRegistrationNo;
	}

	public boolean isAgencyStatus() {
		return agencyStatus;
	}

	public void setAgencyStatus(boolean agencyStatus) {
		this.agencyStatus = agencyStatus;
	}

	public String getHeadQuarterCity() {
		return headQuarterCity;
	}

	public void setHeadQuarterCity(String headQuarterCity) {
		this.headQuarterCity = headQuarterCity;
	}

	public String getAgencyAddress() {
		return agencyAddress;
	}

	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = agencyAddress;
	}

	public String getAgencyEmailId() {
		return agencyEmailId;
	}

	public void setAgencyEmailId(String agencyEmailId) {
		this.agencyEmailId = agencyEmailId;
	}

	public String getAgencyCountry() {
		return agencyCountry;
	}

	public void setAgencyCountry(String agencyCountry) {
		this.agencyCountry = agencyCountry;
	}

	public String getTaxResidencyCertificate() {
		return taxResidencyCertificate;
	}

	public void setTaxResidencyCertificate(String taxResidencyCertificate) {
		this.taxResidencyCertificate = taxResidencyCertificate;
	}

	public MultipartFile getTaxResidencyCertificateFile() {
		return taxResidencyCertificateFile;
	}

	public void setTaxResidencyCertificateFile(MultipartFile taxResidencyCertificateFile) {
		this.taxResidencyCertificateFile = taxResidencyCertificateFile;
	}

	public String getTypeOfBusiness() {
		return typeOfBusiness;
	}

	public void setTypeOfBusiness(String typeOfBusiness) {
		this.typeOfBusiness = typeOfBusiness;
	}

	public Date getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}

	public Date getBuMemberSinceDate() {
		return buMemberSinceDate;
	}

	public void setBuMemberSinceDate(Date buMemberSinceDate) {
		this.buMemberSinceDate = buMemberSinceDate;
	}

	public String getRevenueInfo() {
		return revenueInfo;
	}

	public void setRevenueInfo(String revenueInfo) {
		this.revenueInfo = revenueInfo;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getSrMgmt() {
		return srMgmt;
	}

	public void setSrMgmt(String srMgmt) {
		this.srMgmt = srMgmt;
	}

	public String getParentCompany() {
		return parentCompany;
	}

	public void setParentCompany(String parentCompany) {
		this.parentCompany = parentCompany;
	}

	public boolean isFinanceFlag() {
		return financeFlag;
	}

	public void setFinanceFlag(boolean financeFlag) {
		this.financeFlag = financeFlag;
	}

	public boolean isInsuranceFlag() {
		return insuranceFlag;
	}

	public void setInsuranceFlag(boolean insuranceFlag) {
		this.insuranceFlag = insuranceFlag;
	}

	public boolean isRegulatedFlag() {
		return regulatedFlag;
	}

	public void setRegulatedFlag(boolean regulatedFlag) {
		this.regulatedFlag = regulatedFlag;
	}

	public String getRegulatorName() {
		return regulatorName;
	}

	public void setRegulatorName(String regulatorName) {
		this.regulatorName = regulatorName;
	}

	public boolean isSubjectToTaxation() {
		return subjectToTaxation;
	}

	public void setSubjectToTaxation(boolean subjectToTaxation) {
		this.subjectToTaxation = subjectToTaxation;
	}

	public boolean isSubjectToDividends() {
		return subjectToDividends;
	}

	public void setSubjectToDividends(boolean subjectToDividends) {
		this.subjectToDividends = subjectToDividends;
	}

	public String getMajorFacilities() {
		return majorFacilities;
	}

	public void setMajorFacilities(String majorFacilities) {
		this.majorFacilities = majorFacilities;
	}

	public String getMetaStatus() {
		return metaStatus;
	}

	public void setMetaStatus(String metaStatus) {
		this.metaStatus = metaStatus;
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

	public String getMetaRemarks() {
		return metaRemarks;
	}

	public void setMetaRemarks(String metaRemarks) {
		this.metaRemarks = metaRemarks;
	}

	public String getTrcDmsId() {
		return trcDmsId;
	}

	public void setTrcDmsId(String trcDmsId) {
		this.trcDmsId = trcDmsId;
	}

	public boolean isOecdMemberFlag() {
		return oecdMemberFlag;
	}

	public void setOecdMemberFlag(boolean oecdMemberFlag) {
		this.oecdMemberFlag = oecdMemberFlag;
	}

	public boolean isGovtSupportFlag() {
		return govtSupportFlag;
	}

	public void setGovtSupportFlag(boolean govtSupportFlag) {
		this.govtSupportFlag = govtSupportFlag;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	public String getAgencyModel() {
		return agencyModel;
	}

	public void setAgencyModel(String agencyModel) {
		this.agencyModel = agencyModel;
	}

	public String getCapitalSource() {
		return capitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}

	public Double getCapitalValue() {
		return capitalValue;
	}

	public void setCapitalValue(Double capitalValue) {
		this.capitalValue = capitalValue;
	}

	@Override
	public String toString() {
		return "ExternalAgency [agencyId=" + agencyId + ", agencyName=" + agencyName + ", agencyRegistrationNo="
				+ agencyRegistrationNo + ", agencyStatus=" + agencyStatus + ", headQuarterCity=" + headQuarterCity
				+ ", agencyAddress=" + agencyAddress + ", agencyEmailId=" + agencyEmailId + ", agencyCountry="
				+ agencyCountry + ", taxResidencyCertificate=" + taxResidencyCertificate
				+ ", taxResidencyCertificateFile=" + taxResidencyCertificateFile + ", typeOfBusiness=" + typeOfBusiness
				+ ", foundationDate=" + foundationDate + ", buMemberSinceDate=" + buMemberSinceDate + ", revenueInfo="
				+ revenueInfo + ", ownership=" + ownership + ", srMgmt=" + srMgmt + ", parentCompany=" + parentCompany
				+ ", financeFlag=" + financeFlag + ", insuranceFlag=" + insuranceFlag + ", regulatedFlag="
				+ regulatedFlag + ", regulatorName=" + regulatorName + ", subjectToTaxation=" + subjectToTaxation
				+ ", subjectToDividends=" + subjectToDividends + ", majorFacilities=" + majorFacilities
				+ ", metaStatus=" + metaStatus + ", createdBy=" + createdBy + ", createdDt=" + createdDt
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDt=" + lastUpdatedDt + ", metaRemarks="
				+ metaRemarks + ", trcDmsId=" + trcDmsId + ", oecdMemberFlag=" + oecdMemberFlag + ", govtSupportFlag="
				+ govtSupportFlag + ", reportingTo=" + reportingTo + ", agencyModel=" + agencyModel + ", capitalSource="
				+ capitalSource + ", capitalValue=" + capitalValue + "]";
	}

}
