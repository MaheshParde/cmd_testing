package in.ecgc.smile.erp.accounts.model;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter(value = lombok.AccessLevel.PUBLIC)
public class TDSMaster {
	
	private String fiscalYr;
	private Double fromAmount;
	private Double toAmount;
	private Date fromDt;
	private Date toDt;
	private Float taxPer;
	private String createdBy;
	private Date createdDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	private Double amount;
	private Double limitEcgc;
	private Double surchargeAmt;
	private Float surchargePer;
	private char sex;
		
	public String getFiscalYr() {
		return fiscalYr;
	}
	public void setFiscalYr(String fiscalYr) {
		this.fiscalYr = fiscalYr;
	}
	public double getFromAmount() {
		return fromAmount;
	}
	public void setFromAmount(double fromAmount) {
		this.fromAmount = fromAmount;
	}
	public double getToAmount() {
		return toAmount;
	}
	public void setToAmount(double toAmount) {
		this.toAmount = toAmount;
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
	
	
	public Date getFromDt() {
		return fromDt;
	}
	public void setFromDt(Date fromDt) {
		this.fromDt = fromDt;
	}
	public Date getToDt() {
		return toDt;
	}
	public void setToDt(Date toDt) {
		this.toDt = toDt;
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
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public float getTaxPer() {
		return taxPer;
	}
	public void setTaxPer(float taxPer) {
		this.taxPer = taxPer;
	}
	public double getLimitEcgc() {
		return limitEcgc;
	}
	public void setLimitEcgc(double limitEcgc) {
		this.limitEcgc = limitEcgc;
	}
	public double getSurchargeAmt() {
		return surchargeAmt;
	}
	public void setSurchargeAmt(double surchargeAmt) {
		this.surchargeAmt = surchargeAmt;
	}
	public float getSurchargePer() {
		return surchargePer;
	}
	public void setSurchargePer(float surchargePer) {
		this.surchargePer = surchargePer;
	}
	@Override
	public String toString() {
		return "TDSMaster [fiscalYr=" + fiscalYr + ", fromAmount=" + fromAmount + ", toAmount=" + toAmount + ", fromDt="
				+ fromDt + ", toDt=" + toDt + ", taxPer=" + taxPer + ", createdBy=" + createdBy + ", createdDt="
				+ createdDt + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDt=" + lastUpdatedDt + ", amount="
				+ amount + ", limitEcgc=" + limitEcgc + ", surchargeAmt=" + surchargeAmt + ", surchargePer="
				+ surchargePer + ", sex=" + sex + "]";
	}
	
	
	
	
	

	
}
