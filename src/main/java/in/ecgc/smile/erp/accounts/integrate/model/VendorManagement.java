package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import lombok.Data;

@Data
public class VendorManagement {
	Advertisement advertise;
	BidderEntryDetails bidder;
	private String vendorCode;
	private String vendorName;
	private String vendorType;
	private String vendorAddress;
	private String vendorCity;
	private int vendorPincode;
	private String vendorState;
	private int vendorMobileno;
	private String vendorFaxNo;
	private String vendorEmail;
	private Date empanelementDate;
	private String workArea;
	private String gemRegistered;
	private String gemRegNo;
	private String womenEntrepreneur;
	private String msme;
	private String msmeSamadhan;
	private String caste;
	private Date startUpdate;
	private String vendorDesc;
	private String vendorStatus;
	private String personName;
	private String personAddress;
	private String personCity;
	private String personState;
	private int personPinCode;
	private int personPhone;
	private int personMobile;
	private int personFaxNo;
	private String personEmail;
	private String logicLocation;
	private String vendorAddress2;
	private String vendorAddress3;
	private int vendorPhoneNo;
	private Date vendorEstablishStartDate;
	private String treds;
	private String personAddress2;
	private String personAddress3;
	private String workType;
	private String oldCode;
	private String createdDate;
	private String createdBy;
	private String lastUpdatedBy;
	private String lastUpdateDate;
	private String metaStatus;
	private String metaRemarks;
	private String delFlag;
	private int vendorRegNo;
	private String userId;
	private Date lastTransactionDate;
	private String gstin;
	private String msmeNo;
	private String pan;
	private Date histLastTransDt;
	private Date transTime;
	private String tredsNo;
	BankDetails bank;
	private boolean submitFlag;
	

}
