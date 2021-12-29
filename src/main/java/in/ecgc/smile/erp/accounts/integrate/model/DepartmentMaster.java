package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentMaster {
	private String deptId;
	private String deptName;
	private String deptNameHindi;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;


}
