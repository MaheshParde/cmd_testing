package in.ecgc.smile.erp.accounts.model;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class LOVMaster {
	
	@NotNull(message = "{lov_cd.required}")
	private String lovCd;
	@NotNull(message = "{lov_sub_cd.required}")
	private String lovSubCd;
	@NotNull(message = "{lov_value.required}")
	private String lovValue;
	@NotNull(message = "{lov_desc.required}")
	private String lovDesc;
	
	private Date createdDt;
	private String createdBy;
	
	private Date lastUpdatedDt;
	private String lastUpdatedBy;
	
	private Boolean delFlag;
			

}
