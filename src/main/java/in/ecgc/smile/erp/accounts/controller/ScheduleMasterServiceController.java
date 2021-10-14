package in.ecgc.smile.erp.accounts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.ecgc.smile.erp.accounts.exception.ScheduleCodeException;
import in.ecgc.smile.erp.accounts.model.Schedule;
import in.ecgc.smile.erp.accounts.service.ScheduleService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Faisal-LAPTOP9
 *
 */
@RestController
@RequestMapping("/schedule")
@Api(value = "Schedule Master service")
public class ScheduleMasterServiceController {

	@Autowired
	ScheduleService scheduleService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EntityGlMasterController.class);

	@PostMapping("/add")
	@ApiOperation(value = "Add New Schedule", response = ResponseModel.class)
	public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule){
		ResponseEntity<Schedule> model;
		LOGGER.info("inside add schedule");
		try {
			LOGGER.info(schedule.toString());
			if ( scheduleService.addSchedule(schedule) == 1) {
			model = new ResponseEntity<Schedule>(schedule,HttpStatus.CREATED);
				LOGGER.debug("ScheduleMasterServiceController");
				}
			else {
				model = new ResponseEntity<Schedule>(HttpStatus.BAD_REQUEST);
			}
		}
		catch (ScheduleCodeException e) {
			model = new ResponseEntity<Schedule>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- ScheduleCodeException ..Incomplete Schedule Data provided");
		}
		return model;
	}
	
	@GetMapping("/getschedule/{cd}")
	@ApiOperation(value = "Get Schedule", response = ResponseEntity.class)
	public ResponseEntity<Schedule> getSchedule(@PathVariable("cd") String scheduleCode) {
		ResponseEntity<Schedule> model;
		Schedule schedule = null ;
		try {
			LOGGER.info("--insede get schedule--");
			if ((schedule = scheduleService.getScheduleByScheduleCd(scheduleCode)) != null) {
				model = new ResponseEntity<Schedule>(schedule,HttpStatus.OK);
				LOGGER.debug("--ScheduleMasterServiceController--");
				}
			else {
				model = new ResponseEntity<Schedule>(HttpStatus.NOT_FOUND);
			}
		}
		catch (ScheduleCodeException e) {
			model = new ResponseEntity<Schedule>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- ScheduleCodeException ..failed to fetch");
		}
		return model;


	}

	@GetMapping("/getallschedule")
	@ApiOperation(value = "Get all Schedule", response = ResponseModel.class)
	public ResponseEntity<List<Schedule>> getAllSchedule() {
		List<Schedule> allSchedule = null ;
		try {
			LOGGER.info("--insede get all schedule--");
			if ((allSchedule = scheduleService.getAllSchedule()) != null) {
				LOGGER.debug("--ScheduleMasterServiceController--");
				return new ResponseEntity<>(allSchedule,HttpStatus.OK);
				}
			else {
			return new ResponseEntity<List<Schedule>>(HttpStatus.NO_CONTENT);
			}
		}
		catch (ScheduleCodeException e) {
		LOGGER.error("Exception thrown- ScheduleCodeException ..failed to fetch");
			return new ResponseEntity<List<Schedule>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteschedule/{cd}")
	@ApiOperation(value = "Delete Schedule", response = ResponseModel.class)
	public ResponseEntity<Integer> deleteSchedule(@PathVariable("cd") String scheduleCode) {
		ResponseEntity<Integer> model ;
		try {
			LOGGER.info("--insede delete schedule--");
			if ((scheduleService.deleteSchedule(scheduleCode)) == 1) {
				model = new ResponseEntity<>(1,HttpStatus.OK);
				LOGGER.debug("--ScheduleMasterServiceController--");
				}
			else {
				model = new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
			}
		}
		catch (ScheduleCodeException e) {
			model = new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- ScheduleCodeException ..failed to delete");
		}
		return model;


	}
	
	@PostMapping("/update")
	@ApiOperation(value = "Update Schedule", response = ResponseModel.class)
	public ResponseEntity<Schedule> updateSchedule(@RequestBody Schedule schedule){
		ResponseEntity<Schedule> model;
		LOGGER.info("inside update Schedule");
		try {
			LOGGER.info(schedule.toString());
			if ( scheduleService.updateSchedule(schedule) == 1) {
				model = new ResponseEntity<Schedule>(schedule,HttpStatus.ACCEPTED);
				LOGGER.debug("ScheduleMasterServiceController");
				}
			else {
				model = new ResponseEntity<Schedule>(HttpStatus.BAD_REQUEST);
			}
		}
		catch (ScheduleCodeException e) {
			model = new ResponseEntity<Schedule>(HttpStatus.BAD_REQUEST);
			LOGGER.error("Exception thrown- ScheduleCodeException ..Incomplete Schedule Data provided");
		}
		return model;
	}

	
}
