package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import lombok.Data;

@Data
public class Quotation {
	
	private int quoteNo;
	private Long offeredPrice; 
	private Long quotationFees; 
	private Date receivedDate;
	private Long earnestMoney;
	private Long inwardNo;
	private String modeOfPayment;

}
