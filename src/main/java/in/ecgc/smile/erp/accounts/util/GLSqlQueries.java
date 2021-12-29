package in.ecgc.smile.erp.accounts.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import in.ecgc.smile.erp.accounts.model.EntityGL;

public interface GLSqlQueries {

public static final String ADD_GL_CODE = "INSERT INTO accounts.ecgc_acct_entity_gl_mst(entity_cd, maingl_cd, subgl_cd, gl_name, "
												+ "gl_is_group, gl_type, gl_subtype, zero_bal_flag, "
												+ "cashflow, logical_loc_cd, pl_level, t1, t2, t3, "
												+ "t4, t5, t6, t7, t8, t9, t10, t11, t12, irda_cd,"
												+ "irda_bap_cd, schedule6, financial_form_name, "
												+ "created_by, created_dt, last_updated_by, "
												+ "last_updated_dt, active, sub_bifurcation_level, "
												+ "cashaccount, old_cd, meta_remarks) "
												+ "VALUES('ECGC',:mainglCd, :subglCd,:glName,:glIsGroup,:glType, "
												+ " :glSubtype, :zeroBalFlg, :cashFlow, :logicalLocCd, :plLevel, :t1, :t2, "
												+ " :t3, :t4, :t5, :t6, :t7, :t8, :t9, :t10, :t11, :t12, "
												+ " :irdaCd, :irdaBapCd, :schedule6, :finFormName, :createdBy, now(), :lastUpdatedBy ,now(), :active, "
												+ " :subBifurcationLevel, :cashaccount, :oldCd, :metaRemarks);";

	public static final String ALL_GL_CODES = " SELECT * FROM accounts.ecgc_acct_entity_gl_mst where active ='Y'";

	public static final String LOAD_GLCODE = " SELECT * FROM accounts.ecgc_acct_entity_gl_mst WHERE maingl_cd = :mainGLCode and subgl_cd = :subGLCode and active ='Y'";

	public static final String UPDATE_GLCODE = "	UPDATE accounts.ecgc_acct_entity_gl_mst "
												+ "SET maingl_cd=:mainglCd, subgl_cd=:subglCd,"+
												" gl_name=:glName, gl_is_group=:glIsGroup, gl_type=:glType, gl_subtype=:glSubtype,"
												+" zero_bal_flag=:zeroBalFlg, cashflow=:cashFlow, logical_loc_cd=:logicalLocCd, pl_level=:plLevel,"
												+" t1=:t1, t2=:t2, t3=:t3, t4=:t4, t5=:t5, t6=:t6, t7=:t7, t8=:t8, t9=:t9,"
												+" t10=:t10, t11=:t11, t12=:t12, irda_cd=:irdaCd, irda_bap_cd=:irdaBapCd, schedule6=:schedule6,"
												+" financial_form_name=:finFormName, last_updated_by=:lastUpdatedBy , "
												+" last_updated_dt=now(), sub_bifurcation_level=:subBifurcationLevel, cashaccount=:cashaccount, old_cd=:oldCd, meta_remarks=:metaRemarks"
												+" WHERE maingl_cd = :mainglCd and subgl_cd =:subglCd and active ='Y'";

	public static final String DISABLE_GLCODE = "UPDATE accounts.ecgc_acct_entity_gl_mst SET active = 'N' WHERE maingl_cd = :mainglCd and subgl_cd =:subglCd";

	public static final String DISABLE_GLCODE_SUBGLCODE = "UPDATE accounts.ecgc_acct_entity_gl_mst SET active = 'N' WHERE entity_gl_cd IN "
			+ "(select entity_gl_cd from acct_entity_gl_mst WHERE maingl_cd = :mainglCd)";
	
	public static final String GET_SUB_BIFURCATION_CDS="select value from accounts.ecgc_acct_sub_bifurcations where sub_bifurcation_level =(select sub_bifurcation_level from accounts.ecgc_acct_entity_gl_mst where maingl_cd =:mainGl and subgl_cd=:subGl);";
	
	public static EntityGL mapRow(ResultSet rs, int rowNum) throws SQLException {
		EntityGL entityGL = new EntityGL();
		//entityGL.setEntityGlCd(rs.getInt("entity_gl_cd"));
		entityGL.setMainglCd(rs.getString("maingl_cd"));
		entityGL.setSubglCd(rs.getString("subgl_cd"));
		entityGL.setGlName(rs.getString("gl_name"));
		if(rs.getString("gl_is_group") != null)
		entityGL.setGlIsGroup(rs.getString("gl_is_group").charAt(0));
		entityGL.setGlType(rs.getString("gl_type"));
		entityGL.setGlSubtype(rs.getString("gl_subtype"));
		//entityGL.setBalInd(rs.getString("bal_ind"));
		if(rs.getString("zero_bal_flag") != null)
		entityGL.setZeroBalFlg(rs.getString("zero_bal_flag").charAt(0));
		if(rs.getString("active") != null)
		entityGL.setActive(rs.getString("active").charAt(0));
		entityGL.setPlLevel(rs.getString("pl_level"));
		entityGL.setCashaccount(rs.getInt("cashaccount"));
		entityGL.setCashFlow(rs.getInt("cashflow"));
		entityGL.setLogicalLocCd(rs.getString("logical_loc_cd"));
		entityGL.setMetaRemarks(rs.getString("meta_remarks"));
		entityGL.setCreatedBy(rs.getString("created_by"));
		if(rs.getDate("created_dt") != null)
			entityGL.setCreatedDt(rs.getDate("created_dt").toLocalDate());
		entityGL.setLastUpdatedBy(rs.getString("last_updated_by"));
		if(rs.getDate("last_updated_dt") != null)
		entityGL.setLastUpdatedDt(rs.getDate("last_updated_dt").toLocalDate());
		entityGL.setOldCd(rs.getString("old_cd"));
		if(rs.getString("t1") != null)
		entityGL.setT1(rs.getString("t1").charAt(0));
		if(rs.getString("t2") != null)
			entityGL.setT2(rs.getString("t2").charAt(0));
		if(rs.getString("t3") != null)
			entityGL.setT3(rs.getString("t3").charAt(0));
		if(rs.getString("t4") != null)
			entityGL.setT4(rs.getString("t4").charAt(0));
		if(rs.getString("t5") != null)
			entityGL.setT5(rs.getString("t5").charAt(0));
		if(rs.getString("t6") != null)
			entityGL.setT6(rs.getString("t6").charAt(0));
		if(rs.getString("t7") != null)
			entityGL.setT7(rs.getString("t7").charAt(0));
		if(rs.getString("t8") != null)
			entityGL.setT8(rs.getString("t8").charAt(0));
		if(rs.getString("t9") != null)
			entityGL.setT9(rs.getString("t9").charAt(0));
		if(rs.getString("t10") != null)
			entityGL.setT10(rs.getString("t10").charAt(0));
		if(rs.getString("t11") != null)
			entityGL.setT11(rs.getString("t11").charAt(0));
		if(rs.getString("t12") != null)
			entityGL.setT12(rs.getString("t12").charAt(0));
		if(rs.getString("irda_cd") != null)
			entityGL.setIrdaCd(rs.getString("irda_cd"));
		if(rs.getString("irda_bap_cd") != null)
			entityGL.setIrdaBpaCd(rs.getString("irda_bap_cd"));
		if(rs.getString("schedule6") != null)
			entityGL.setSchedule6(rs.getString("schedule6"));
		if(rs.getString("financial_form_name") != null)
			entityGL.setFinancialFormName(rs.getString("financial_form_name"));
		if(rs.getString("sub_bifurcation_level") != null)
			entityGL.setSubBifurcationLevel(rs.getString("sub_bifurcation_level"));
		
		return entityGL;

	}
}