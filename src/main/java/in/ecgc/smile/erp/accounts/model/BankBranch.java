package in.ecgc.smile.erp.accounts.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter @Getter @ToString
public class BankBranch {

	
	@NotBlank(message = "{logicalLocCode.required}")
	private String logicalLocCode ;
	@NotBlank(message = "{bankName.reqired}")
	private String bankName ;
	@Pattern(regexp = "^[\\p{L} .'-]+$", message="Special Characters are not Allowed")
	@NotBlank(message = "{bankBranchName.required}")
	private String bankBranchName ; 
	@Pattern(regexp = "^\\d{9,18}$" , message="Please Enter a Valid Account Number")
	@NotBlank(message = "{expenseAccountNumber.required}")
	private String expenseAccountNumber ;
	@Pattern( regexp = "^[A-Z|a-z]{4}[0][a-zA-Z0-9]{6}$", message = "Please Enter a Valid IFSC code")
	@NotBlank(message = "{expenseAcctIfscCode.required}")
	private  String expenseAccountIfscCode ;	
	@Pattern(regexp = "^\\d{9,18}$" , message="Please Enter a Valid Account Number")
	@NotBlank(message = "{collectionAccountNumber.required}")
	private String collectionAccountNumber ; 
	@Pattern( regexp = "^[A-Z|a-z]{4}[0][a-zA-Z0-9]{6}$", message = "Please Enter a Valid IFSC Code")
	@NotBlank(message = "{collectionAccountIfscCode.required}")
	private String collectionAccountIfscCode;
	@NotBlank(message = "{clientId.required}")
	private String clientId ;
	@NotBlank(message = "{virtualId.required}")
	private String virtualId ;
	@NotNull(message = "{active.required}")
	private Boolean active;
	
	private String gstin;
	
	private String createdBy;
	private Date createdDate ;
	private String lastUpdatedBy ;
	private Date lastUpdatedDate ;
	private String ecgcStatus;
	private String metaRemarks;
		
	public BankBranch() {
		super();
	}
	
	


}
