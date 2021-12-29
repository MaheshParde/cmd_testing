package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeMaster {

	private String officeId;
	private String officeName;
	private String officeNameHindi;
	private String emailId;
	private StatusMaster  statusMaster;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	private String description;
	private String descriptionHindi;
	private OfficeTypeMaster officeType;
	private CityMaster city;
	private StateMaster state;
	private ContactMaster contact;
	private PhysicalLocationMaster physicalLocation;
	//for backend services
	private OfficeCategoryMaster category;
	private DepartmentMaster dept;



}
