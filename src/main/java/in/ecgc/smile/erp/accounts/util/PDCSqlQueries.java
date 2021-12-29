package in.ecgc.smile.erp.accounts.util;

public interface PDCSqlQueries {
	
	public static final String CREATE_PDC_ENTRY = 
			"INSERT INTO ecgc_acct_pdc\r\n"
			+ "(logical_loc_cd, rcvd_from_cd, rcvd_from_name, rcvd_from_addr, instrument_type, instrument_no,instrument_amount, instrument_dt, instrument_status, drawn_on, created_by, created_dt, meta_status, meta_remarks)\r\n"
			+ "VALUES(:logicalLocCode, :receivedFromCode, :receivedFromName, :receivedFromaddr , :instrumentType, :instrumentNo,:instrumentAmount, :instrumentDate, 'n', :drawnOn,:createdBy,now(),:metaStatus,:metaRemarks)";
	
	public static final String VIEW_ALL_PDC = 
			"select * from ecgc_acct_pdc";
	
	public static final String VIEW_BY_CHQ_NO =
			"select * from ecgc_acct_pdc where sr_no = :sr_no";
	
	public static final String CHECK_UNIQUE_ENTRY =
			"select * from ecgc_acct_pdc where instrument_no = :instrumentNo and instrument_dt = (select to_date(:instrument_dt,'YYYYMMDD'))";
	
	public static final String VIEW_BY_STATUS = 
			"select * from ecgc_acct_pdc where instrument_status = :instrumentStatus";
	
	public static final String CHANGE_STATUS = 
			"update ecgc_acct_pdc set instrument_status = :instrumentStatus,last_updated_by = :lastUpdatedBy, last_updated_dt = now() where instrument_no = :instrumentNo";
}
