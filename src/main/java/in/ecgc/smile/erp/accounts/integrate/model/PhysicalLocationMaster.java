package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalLocationMaster {
	private String physicalLocationId;
	private String briefAddressName;
	private String address1;
	private String address2;
	private String pincode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	private StatusMaster statusMaster;
	private ContactMaster contact;

}
