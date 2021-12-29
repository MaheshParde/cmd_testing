package in.ecgc.smile.erp.accounts.integrate.model;

import lombok.Data;

@Data
public class BankDetails {
	
	private String bankName;
	private String bankAccountNo;
	private String beneficiaryName;
	private String bankBranch;
	private String bankCity;
	private String bankState;
	private String serviceTaxNo;
	private String rtgs;
	private String bankAccountType;
	private Integer micrCode;
	private String ifscCode;
	private Integer vendorSerialNo;

}
