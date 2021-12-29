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
public class EmployeeAddChrg {

	private Integer empNo;

	private List<Integer> secAddChrgId;

	private List<Integer> sectoralAddChrgId;

	private List<String> secDesigId;

	private List<String> secDeptCd;

	private List<String> secOfficeId;

	private List<String> sectoralDesigId;

	private List<String> sectoralDeptCd;

	private List<String> sectoralOfficeId;

	private String logicalLocCd;

	private String resTypeCd;

	private List<Integer> secReptTo;

	private List<Integer> sectoralReptTo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date effFrom;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date effTo;

	private String createdBy;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;

	private String lastUpdatedBy;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastUpdatedDate;

}
