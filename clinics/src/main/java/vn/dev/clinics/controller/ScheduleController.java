package vn.dev.clinics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.dev.clinics.model.ScheduleModel;
import vn.dev.clinics.service.ScheduleService;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping("/{id}")
	public ScheduleModel getSchedule(@PathVariable("id") Integer id) {
		return scheduleService.getModelById(id);
	}

	@GetMapping("")
	public List<ScheduleModel> getSchedules(){
		return scheduleService.findAllModel();
	}
	
	@PostMapping("")
	public ScheduleModel saveShedule(@RequestBody ScheduleModel model) {
		return scheduleService.saveSchdule(model);
	}
	
	@PutMapping("/{id}")
	public ScheduleModel updateShedule(@RequestBody ScheduleModel model, @PathVariable("id") Integer id) {
		return scheduleService.updateSchdule(model, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Integer id) {
		scheduleService.deleteById(id);
	}
}
