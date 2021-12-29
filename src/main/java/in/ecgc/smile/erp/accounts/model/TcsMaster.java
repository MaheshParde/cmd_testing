package in.ecgc.smile.erp.accounts.model;

import java.util.Date;
public class TcsMaster {

	private String finYear ;
	private Double fromAmount;
	private Double toAmount;
	private Float taxPercent;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Double amount;
	private Date fromDate;
	private Date toDate;
	
	private Double limitEcgc;
	private Double surchargeAmount; 
	private Float surchargePer;
	private String sex;
	
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
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
	public Float getTaxPercent() {
		return taxPercent;
	}
	public void setTaxPercent(Float taxPercent) {
		this.taxPercent = taxPercent;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Double getLimitEcgc() {
		return limitEcgc;
	}
	public void setLimitEcgc(Double limitEcgc) {
		this.limitEcgc = limitEcgc;
	}
	public Double getSurchargeAmount() {
		return surchargeAmount;
	}
	public void setSurchargeAmount(Double surchargeAmount) {
		this.surchargeAmount = surchargeAmount;
	}
	public Float getSurchargePer() {
		return surchargePer;
	}
	public void setSurchargePer(Float surcharge_per) {
		this.surchargePer = surcharge_per;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public TcsMaster(String finYear, Double fromAmount, Double toAmount, Float taxPercent, String createdBy,
			Date createdDate, String modifiedBy, Date modifiedDate, Double amount, Date fromDate, Date toDate,
			Double limitEcgc, Double surchargeAmount, Float surcharge_per, String sex) {
		super();
		this.finYear = finYear;
		this.fromAmount = fromAmount;
		this.toAmount = toAmount;
		this.taxPercent = taxPercent;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.amount = amount;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.limitEcgc = limitEcgc;
		this.surchargeAmount = surchargeAmount;
		this.surchargePer = surcharge_per;
		this.sex = sex;
	}
	
	public TcsMaster() {
		super();
	}
	
	@Override
	public String toString() {
		return "TcsMaster [finYear=" + finYear + ", fromAmount=" + fromAmount + ", toAmount=" + toAmount
				+ ", taxPercent=" + taxPercent + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", amount=" + amount + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", limitEcgc=" + limitEcgc + ", surchargeAmount=" + surchargeAmount
				+ ", surcharge_per=" + surchargePer + ", sex=" + sex + "]";
	} 
	
	
	
}
