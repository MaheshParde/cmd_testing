package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogicalLocMst {

private String logicalLocCd;
	
	private String description;
	@JsonProperty(value = "logicalloc_incharge")
	private Integer logicalLocIncharge;
	
	@JsonProperty(value="logicalloc_type")
	private String logicalLocType;
	
	@JsonProperty(value="status")
	private String status;
	
	@JsonProperty(value="address")
	private String address;
	
	@JsonProperty(value="street")
	private String street;
	
	@JsonProperty(value="city")
	private String city;
	
	@JsonProperty(value="state")
	private String state;
	
	@JsonProperty(value="city_class")
	private String cityClass;
	
	@JsonProperty(value="phone")
	private String phone;
	
	@JsonProperty(value="fax")
	private String fax;
	
	@JsonProperty(value="email")
	private String email;
	
	@NotNull(message = "Please provide a date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonProperty(value="effective_from")
	private Date effFrom;
	
	@JsonProperty(value="old_cd")
	private String oldCd;
	
	@JsonProperty(value="region_cd")
	private String regionCd;
	
	@JsonProperty(value="branch_cd")
	private String branchCd;

	@JsonProperty(value="loc_cd")
	private String locCd;
	
	@JsonProperty(value="pf_stat")
	private String pfState;
	
	@JsonProperty(value="std_cd")
	private String stdCd;
	
	@JsonProperty(value="pin")
	private String pinCd;


}
