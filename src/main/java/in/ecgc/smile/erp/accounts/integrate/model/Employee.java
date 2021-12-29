package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private EmployeeAddChrg empAddChrg;

	/* Employee Id which is used for Employee reference */
	private Integer empNo;

	private Integer isLop;

	private Integer leaveIndays;

	private String prefix;

	/* Employee First Name */
	// @Pattern(regexp="^[A-Za-z]+$", message="Enter Valid First Name")
	private String firstName;

	/* Employee Middle Name */
	private String midName;

	/* Employee Last Name */
	// @Pattern(regexp="^[A-Za-z]+$", message="Enter Valid Last Name")
	private String lastName;

	private String empAlias;

	private String gender;

	private String birthPlace;

	private String nationality;

	/* Employee DOB */
	// @NotNull(message = "Please provide a date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	/* Employee Confirmation date */
	// @NotNull(message = "Please provide a date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date confDate;

	private String empType1;

	private String bloodGrp;

	/* Employee Date of joining */
	// @NotNull(message = "Please provide a date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doj;

	/* Employee Type */
	private String empType;

	/* Employee Location */
	/*
	 * @NotBlank(message="Please provide Location") private String logicalLocCd;
	 */

	/* Employee office branch code */
	// @NotBlank(message="Please Select Employee Branch Code")
	private String branchCode;

	private String branchDesc;

	/* Employee office region code */
	// @NotBlank(message="Please Select Employee Region Code")
	private String regionCode;

	private String regionName;

	// @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date probToDate;

	/* Employee rank */
	// @NotBlank(message="Please Select Employee Rank")
	private String rankId;

	private String rankDesc;

	/* Employee Designation */
	// @NotBlank(message="Please Select Employee Designation")
	private String desigId;

	private String desigDesc;

	private String joinDesigId;

	private String joinDesigDesc;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastPrmDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastIncDate;

	/* Employee category */
	// @NotBlank(message="Please Select Employee Categoty")
	private String categoryCd;

	private String categoryDesc;

	/* Employee religion */
	private String religion;

	private String classCd;

	/* Employee handicap flag */
	// @NotBlank(message="Please Select Employee Handicap Field ")
	private String handicapFlg;

	/* Employee disability percentage */
	private long disabilityPerc;

	/* Employee handicap description */
	private String handicapDesc;

	/* Employee Status */
	private String statusCd;

	private String empStatusDesc;

	private String divCd;

	private String divDesc;

	private String deptCd;

	private String deptDesc;

	private String sectorCd;

	private String sectorDesc;

	private Integer reportingTo;

	/* Salary Details */
	private Double prsntBasicSal;

	private String salPayMode;

	private Double fixedDa;

	private Double persAllowance;

	private String permDeputation;

	private String payrollEmpStatus;

	private String createdBy;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDt;

	private String lastUpdatedBy;

	// private String designation;

	private String handicapChildFlag;

	private String compAccom;

	private String spclConvFlag;

	private String creditSocHsgFlag;

	private Date csJoiningDt;

	private String isGtiOpted;

	private String pensionOpted;

	private String npsOpted;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date lastUpdatedDt;

	private String officeId;

	private Integer attendance;

	private Integer outdoorduty;

	private String officeDesc;

	private List<EmployeeAdditionalCharges> employeeAdditionalCharges;

	private EmployeeOtherDetail employeeOtherDetail;

}
