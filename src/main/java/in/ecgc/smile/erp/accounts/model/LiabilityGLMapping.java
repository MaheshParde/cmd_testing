/**
 * 
 */
package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author CMJ-SENG-LAPTOP9
 * Module wise GL mapping for libility 
 *
 */
@Data
public class LiabilityGLMapping {
	private String moduleCd;
	private String mappingCd;
	private String mappingName;
	private Integer srNo;
	private String mainGL;
	private String subGL;
	private String drCrFlag;
	private Float amtCalc;
	private String remarks;
	private String txnType;
	private String subBifurcation;
	private String createdBy;
	private LocalDate createdDt;
	private String lastUpdatedBy;
	private LocalDate lastUpdatedDt;
	private String metaRemarks;
	private String metaStatus;
}
