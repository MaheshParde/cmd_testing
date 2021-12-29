package in.ecgc.smile.erp.accounts.controller;

import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.ecgc.smile.erp.accounts.exception.ScheduleCodeException;
import in.ecgc.smile.erp.accounts.model.BranchMaster;
import in.ecgc.smile.erp.accounts.model.ScheduleCodeMst;
import in.ecgc.smile.erp.accounts.service.ScheduleCodeMasterService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Faisal-LAPTOP9
 *
 */
@RestController
@RequestMapping("/schedule-mst")
@Api(value = "Schedule Master service")
@Slf4j
public class ScheduleCodeMasterController {
	@Autowired
	ScheduleCodeMasterService scheduleCodeMstService;
	
	@GetMapping
	@ApiOperation(value = "Get all Schedule", response = ResponseModel.class)
	public ResponseEntity<List<ScheduleCodeMst>> getAllSchedule() {
		List<ScheduleCodeMst> allSchedule = new ArrayList<ScheduleCodeMst>();
		allSchedule = scheduleCodeMstService.getAllSchedule();
		return new ResponseEntity<>(allSchedule,HttpStatus.OK);
	}
	@GetMapping("/{schedule_cd}/{schedule_item_cd}")
	@ApiOperation(value = "Get Schedule", response = ResponseEntity.class)
	public ResponseEntity<ScheduleCodeMst> getSchedule(@PathVariable("schedule_cd") String scheduleCode,
			@PathVariable("schedule_item_cd") String scheduleItemCode) {
		return new ResponseEntity<>(scheduleCodeMstService.getScheduleByScheduleCd(scheduleCode, scheduleItemCode),HttpStatus.OK);
	}
	@PostMapping
	@ApiOperation(value = "Add New Schedule", response = ResponseModel.class)
	public ResponseEntity<Integer> addSchedule(@Valid @RequestBody ScheduleCodeMst scheduleCodeMst){
		return new ResponseEntity<>(scheduleCodeMstService.addSchedule(scheduleCodeMst),HttpStatus.CREATED);
	}
	@PutMapping
	@ApiOperation(value = "Update Schedule", response = ResponseModel.class)
	public ResponseEntity<Integer> updateSchedule(@Valid @RequestBody ScheduleCodeMst scheduleCodeMst){
		return new ResponseEntity<>(scheduleCodeMstService.updateSchedule(scheduleCodeMst),HttpStatus.OK);
	}
	@DeleteMapping("/{schedule_cd}/{schedule_item_cd}")
	@ApiOperation(value = "Delete Schedule Code", response = ResponseModel.class)
	public ResponseEntity<Integer> deleteSchedule(@PathVariable("schedule_cd") String scheduleCode,
			@PathVariable("schedule_item_cd") String scheduleItemCode) {
		return new ResponseEntity<>(scheduleCodeMstService.deleteSchedule(scheduleCode, scheduleItemCode),HttpStatus.OK);
	}
		
}
