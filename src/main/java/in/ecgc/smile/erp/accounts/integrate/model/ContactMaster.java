package in.ecgc.smile.erp.accounts.integrate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactMaster {
	private String contactId;
	private String contactNumber;
	private StatusMaster statusMaster;

}
