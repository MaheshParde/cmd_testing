package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Required;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GlTxnHdr {

	private String entityCd;
	private Integer glTxnNo;
	@NotBlank(message = "Logical Location Code is Required For Transaction")
	private String logicalLocCd;
	@NotBlank(message = "GL Txn Type is Required For Transaction")
	private String glTxnType;
	@NotBlank(message = "Txn Date is Required For Transaction")
	private LocalDate txnDt;
	private String reference;
	private String revarsalGlTxnType;
	private Integer revarsalGlTxnNo;
	private String reversalReason;
	//@NotBlank(message = " Required For Transaction")
	private String fiscalYr;
	private String metaStatus;
	private String metaRemarks;
	private String createdBy;
	private Date createdDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	private List<GlTxnDtl> glTxnDtls;
	
}
