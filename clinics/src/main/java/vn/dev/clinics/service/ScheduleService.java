package vn.dev.clinics.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.dev.clinics.entity.Schedule;
import vn.dev.clinics.model.ScheduleModel;

@Service
public class ScheduleService extends BaseService<Schedule>{
	
	@Autowired
	private TransmitService transmitService;

	@Override
	public Class<Schedule> clazz() {
		return Schedule.class;
	}
	
	public ScheduleModel getModelById(Integer id) {
		ScheduleModel model = transmitService.scheduleToModel(super.getById(id));
		return model;
	}
	
	public List<ScheduleModel> findAllModel(){
		List<ScheduleModel> models = transmitService.scheduleEntitiesToModels(super.findAll());
		return models;		
	}
	
	@Transactional
	public ScheduleModel saveSchdule(ScheduleModel model) {
		Schedule entity = transmitService.scheduleToEntity(model);
		entity.setCreateDate(new Date());
		super.saveOrUpdate(entity);
		return model;
	}
	
	@Transactional
	public ScheduleModel updateSchdule(ScheduleModel model, Integer id) {
		Schedule entity = transmitService.scheduleToEntity(model);
		entity.setId(id);
		entity.setUpdateDate(new Date());
		super.saveOrUpdate(entity);
		return model;
	}
	
	@Transactional
	public void deleteById(Integer id) {
		super.deleteById(id);
	}

	public List<ScheduleModel> getModelByUserId(Integer id) {
		String sql = "SELECT * FROM clinics04.tbl_schedule where patient_id = " + id + ";";
		List<ScheduleModel> models = transmitService.scheduleEntitiesToModels(super.executeNativeSql(sql)); 
		return models;
	}
}
