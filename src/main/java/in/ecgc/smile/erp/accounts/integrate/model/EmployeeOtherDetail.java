package in.ecgc.smile.erp.accounts.integrate.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeOtherDetail {

	private Integer empNo;

	private String addharNo;
	private Integer empOtherInfoId;
	private String nameOnAddhar;

	@NotNull(message = "Please provide a date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dobOnAddhar;

	private String addressOnAddhar;

	private Integer pinCdOnAddhar;

	private String panNo;

	private String nameOnPan;

	@NotNull(message = "Please provide a date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dobOnPan;

	private String empMaritalStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date empMarriageDt;

	private String empHeight;

	private String empWeight;

	private String identficationMark;

	private String identficationMarkDesc;

	private String compLiteracy;

	private String creditSocietyFlg;

	private String recreationClubFlg;

	private String associationFlg;

	private String emailId;

	private boolean wipsMember;

	private boolean disciplinaryAction;

	private boolean spouseWorkingInThisOrg;

	private Integer spouseEmpNo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date quitDt;

	private String marriageCerSub;

	private Integer pfAccountNo;

	private Integer fpsAccountNo;

	private boolean fpsFlag;

	private Date pfStartDt;

	private String createdBy;

	private Date createdDt;

	private String lastUpdatedBy;

	private Date lastUpdateDt;

	private String superAnnuationAge;

	private String polVerify;

	private String adharMappingId;

	private String panMappingId;

	private MultipartFile adharFile;

	private MultipartFile panFile;

}
