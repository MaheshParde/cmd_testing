package in.ecgc.smile.erp.accounts.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class Employee {
	
	/* Employee Id which is used for Employee reference */
	private int empId;
	
	/* Employee First Name*/
	@NotBlank(message = "{firstName.required}")
	private String firstName;
	
	/* Employee Middle Name*/
	@NotBlank(message = "{midName.required}")
	private String midName;
	
	/* Employee Last Name*/
	@NotBlank(message = "{lastName.required}")
	private String lastName;
	/* Employee DOB*/
	
	private Date dob;
	/* Employee Date of joining*/
	
	private Date doj;
	
	/* Employee Type*/
	@NotBlank(message = "{empType.required}")
	private String empType;
	
	/* Employee designation*/
	@NotBlank(message = "{designation.required}")
	private	String designation;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", midName=" + midName + ", lastName="
				+ lastName + ", dob=" + dob + ", doj=" + doj + ", empType=" + empType + ", designation=" + designation
				+ "]";
	}
	
	
	
}
