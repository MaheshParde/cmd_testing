package in.ecgc.smile.erp.accounts.model;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
public class LOVMaster {
	
		@NotNull(message = "{lov_cd.required}")
		private String lov_cd;
		@NotNull(message = "{lov_sub_cd.required}")
		private String lov_sub_cd;
		@NotNull(message = "{lov_value.required}")
		private String lov_value;
		@NotNull(message = "{lov_desc.required}")
		private String lov_desc;
		
		private Timestamp created_dt;
		private String created_by;
		
		private Timestamp last_updated_dt;
		private String last_updated_by;
		
		
		
		public LOVMaster() {
			super();
		}
     
		public LOVMaster(@NotNull(message = "{lov_cd.required}")String lov_cd,
		@NotNull(message = "{lov_sub_cd.required}")String lov_sub_cd,
		@NotNull(message = "{lov_value.required}") String lov_value,
		@NotNull(message = "{lov_desc.required}") String lov_desc,
		Timestamp created_dt,
		String created_by,
		Timestamp last_updated_dt,
		String last_updated_by){
			super();
			this.lov_cd = lov_cd;
			this.lov_sub_cd = lov_sub_cd;
			this.lov_value = lov_value;
			this.lov_desc = lov_desc;
			this.created_dt=created_dt;
			this.created_by = created_by;
			this.last_updated_dt=last_updated_dt;
			this.last_updated_by=last_updated_by;
			
		}

		public String getLov_cd() {
			return lov_cd;
		}

		public void setLov_cd(String lov_cd) {
			this.lov_cd = lov_cd;
		}

		public String getLov_sub_cd() {
			return lov_sub_cd;
		}

		public void setLov_sub_cd(String lov_sub_cd) {
			this.lov_sub_cd = lov_sub_cd;
		}

		public String getLov_value() {
			return lov_value;
		}

		public void setLov_value(String lov_value) {
			this.lov_value = lov_value;
		}
		public String getLov_desc() {
			return lov_desc;
		}

		public void setLov_desc(String lov_desc) {
			this.lov_desc = lov_desc;
		}
		public String getCreated_by() {
			return created_by;
		}

		public void setCreated_by(String created_by) {
			this.created_by = created_by;
		}

		public Timestamp getCreated_dt() {
			return created_dt;
		}

		public void setCreated_dt(Timestamp created_dt) {
			this.created_dt = created_dt;
		}

		public String getLast_updated_by() {
			return last_updated_by;
		}

		public void setLast_updated_by(String last_updated_by) {
			this.last_updated_by = last_updated_by;
		}

		public Timestamp getLast_updated_dt() {
			return last_updated_dt;
		}

		public void setLast_updated_dt(Timestamp last_updated_dt) {
			this.last_updated_dt = last_updated_dt;
		}

		@Override
		public String toString() {
			return "LOVMaster [lov_cd=" + lov_cd + ", lov_sub_cd=" + lov_sub_cd + ", lov_value=" + lov_value
					+ ", lov_desc=" + lov_desc + ", created_dt=" + created_dt + ", created_by=" + created_by
					+ ", last_updated_dt=" + last_updated_dt + ", last_updated_by=" + last_updated_by + "]";
		}		

}
