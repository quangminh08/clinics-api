package vn.dev.clinics.service;

import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

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
		String sql = "SELECT * FROM clinics04.tbl_schedule order by create_date desc;";
		List<ScheduleModel> models = transmitService.scheduleEntitiesToModels(super.executeNativeSql(sql));
		return models;		
	}
	
	@Transactional
	public ScheduleModel saveSchdule(ScheduleModel model) {
		Schedule entity = transmitService.scheduleToEntity(model);
		entity.setCreateDate(new Date());
		entity.setStatus(true);
		super.saveOrUpdate(entity);
		return model;
	}
	
	@Transactional
	public ScheduleModel updateSchdule(ScheduleModel model, Integer id) {
		Schedule entity = super.getById(id);
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

	@Transactional
	public void setStatus(Integer id) {
		Schedule entity = super.getById(id);	
		entity.setStatus(!entity.getStatus());
		super.saveOrUpdate(entity);
	}

	public List<ScheduleModel> getModelByModelSearch(String name, String phone, Integer date) {
		String sql = "SELECT s.id, s.doctor_id, s.patient_id, s.appointment_date, s.hour, s.description, s.create_date, s.update_date, s.status ";
		sql += "FROM clinics04.tbl_schedule as s inner join clinics04.tbl_user as u on s.patient_id=u.id ";
			
			if (name != null && name.trim() != "") {
				sql = sql + " where u.name like '%" + name +"%' ";
				
				if(phone != null && phone.trim() != "") {
					sql = sql + " and u.phone_number='" + phone +"' ";
				}
			} else if(phone != null && phone.trim() != "") {
				sql = sql + " where phone_number='" + phone + "' ";
			}
			
			
			if(date != null) {
				if(name == null  && phone == null) {
					sql += " where hour(s.appointment_date)=" + (date + 7);
				}else {
					sql += " and hour(s.appointment_date)=" + (date + 7);
				}
			}
			
			
			 sql += " order by s.create_date desc;";	
			
			 System.out.println("======== " + sql);
			 List<Schedule> entities = super.executeNativeSql(sql);
		return transmitService.scheduleEntitiesToModels(entities);
	}
	
//	SELECT s.id, s.doctor_id, s.patient_id, s.appointment_date, s.description, s.create_date, s.update_date, s.status
//	FROM clinics04.tbl_schedule as s inner join clinics04.tbl_user as u on s.patient_id=u.id where u.name like "%pha%" and u.phone_number="0987654321";
//	and hour(s.appointment_date) = 14;
}
