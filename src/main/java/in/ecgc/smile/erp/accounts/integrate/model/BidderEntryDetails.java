package in.ecgc.smile.erp.accounts.integrate.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class BidderEntryDetails {
	private Integer bidderNo;
	private String bidderName;
	private String bidderType;
	private String bidderAddress;
	private String bidderCity;
	private Integer bidderPincode;
	private String bidderState;
	private Long bidderMobileNo; 
	private String bidderFax;
	private String status;
	Quotation quote;
	Advertisement advertise;
	@NotEmpty
	  @Email
	private String bidderEmailId; 
	private String bidderCategory; 
	private String gemRegistered;
	private String gemRegisteredNo;
	private String caste; 
	private String msme; 
	private String msmeNo;
	private String womenEntrepreneur; 
	private String msmeSamadhan; 
	private String bidderCompanyName;
	private boolean submitFlag;

	
}
