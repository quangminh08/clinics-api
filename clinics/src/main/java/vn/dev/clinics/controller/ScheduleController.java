package vn.dev.clinics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dev.clinics.model.ScheduleModel;
import vn.dev.clinics.service.ScheduleService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping("/{id}")
	public ScheduleModel getSchedule(@PathVariable("id") Integer id) {
		return scheduleService.getModelById(id);
	}
	
	@GetMapping("/search")
	public List<ScheduleModel> getSchedule(@RequestParam(value="name", required = false) String name, 
			@RequestParam(value="phone",required = false) String phone,
			@RequestParam(value="date",required = false) Integer date) {
		return scheduleService.getModelByModelSearch(name, phone, date);
	}
	
	@GetMapping("/user/{id}")
	public List<ScheduleModel> getScheduleByPatient(@PathVariable("id") Integer id) {
		return scheduleService.getModelByUserId(id);
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
	
	@PostMapping("/status/{id}")
	public ResponseEntity<Map<String, Object>> setStatus(@PathVariable("id") Integer id){
		scheduleService.setStatus(id);
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("status", "Success");
		return ResponseEntity.ok(jsonResult);
	}
}
