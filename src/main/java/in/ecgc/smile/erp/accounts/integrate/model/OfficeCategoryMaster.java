package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeCategoryMaster {
	private String officeCategoryId;
	private String officeCategoryName;
	//private String status;
	private StatusMaster statusMaster;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

}
