package in.ecgc.smile.erp.accounts.util;


public interface ChqDishonorEntryQueries{
static String ADD_ChqDishonorEntry_DATA="INSERT INTO ecgc_acct_chq_dishonor_entry(logical_loc_cd,rcpt_no,dishonor_dt,instrument_no,instrument_type,dishonor_reason,fiscal_yr,created_by,created_dt) VALUES (:logicalLocCd,:rcptNo,:dishonorDt,:instrumentNo,:instrumentType,:dishonorReason,:fiscalYr,:createdBy,now())";
static String GET_ChqDishonorEntry_DATA="SELECT * FROM ecgc_acct_chq_dishonor_entry";
static String UPDATE_ChqDishonorEntry_DATA="UPDATE ecgc_acct_chq_dishonor_entry SET logical_loc_cd=:logicalLocCd, dishonor_dt=:dishonorDt, instrument_no=:instrumentNo, instrument_type=:instrumentType, dishonor_reason=:dishonorReason,  fiscal_yr=:fiscalYr, last_updated_by=:lastUpdatedBy, last_updated_dt=now() WHERE instrument_no=:instrumentNo";
static String GET_ChqDishonorEntry_BY_CHEQUE_NO="SELECT * FROM ecgc_acct_chq_dishonor_entry WHERE instrument_no=:instrumentNo";

	
}
