package in.ecgc.smile.erp.accounts.model;

import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter(value = AccessLevel.PUBLIC) @Setter 
public class BranchMaster {

	private String logicalLocCode;
	private String branchCode;
	private String branchName;
	private String bankName;
	private String bankBranchAddress;
	private String gstinNumber;
	private String gstStateUt;
	private String gstStateUtCode;
	private String expenseAccountNumber ;
	private String expenseAccountIfscCode;
	private String expenseAccountsNeftCode;
	private String collectionAccountNumber;
	private String collectionAccountIfscCode;
	private String collectionAccountNeftCode;
	private String clientId;
	private String virtualId;
	private Boolean	active;
	private String createdBy;
	private Date createdDate;
	private String	lastUpdatedBy;
	private Date lastUpdatedDate;
	private String metaStatus;
	private String metaRemarks;
}
