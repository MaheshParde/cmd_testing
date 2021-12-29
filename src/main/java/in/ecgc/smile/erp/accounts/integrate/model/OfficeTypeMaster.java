package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeTypeMaster {
	@NotBlank(message= "{officeTypeId.required}")
	private String  officeTypeId;
	@NotBlank(message= "{officeTypeName.required}")
	private String officeTypeName;
	private StatusMaster statusMaster;
	private OfficeCategoryMaster officeCategory;
	private Date date;


}
