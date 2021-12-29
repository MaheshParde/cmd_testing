package in.ecgc.smile.erp.accounts.integrate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityMaster {
	private String cityId;
	private String cityName;
	private StateMaster state;

}
