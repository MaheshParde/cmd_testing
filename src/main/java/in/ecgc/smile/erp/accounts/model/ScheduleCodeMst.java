package in.ecgc.smile.erp.accounts.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class ScheduleCodeMst {
	private String schedule_cd;
	private String schedule_item_cd;
	private String description;
	private String title_detail_line;
	private String total;
	private String prefix;
	private String created_by;
	private LocalDateTime created_dt;
	private String last_updated_by;
	private LocalDateTime last_updated_dt;
	private String meta_status;
	private String meta_remarks;
	private String entity_cd;
}
