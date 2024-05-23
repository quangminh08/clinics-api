package vn.dev.clinics.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.dev.clinics.entity.User;
import vn.dev.clinics.entity.MedicalRecord;
import vn.dev.clinics.entity.Message;
import vn.dev.clinics.entity.Schedule;
import vn.dev.clinics.model.UserModel;
import vn.dev.clinics.model.MedicalRecordModel;
import vn.dev.clinics.model.MessageModel;
import vn.dev.clinics.model.ScheduleModel;


@Service
public class TransmitService {	
	
	@Autowired
	private UserService userService;
	
	
	List<UserModel> userEntitiesToModels(List<User> doctorEntities){
		List<UserModel> doctors = doctorEntities.stream()
				.map(entity -> new UserModel(
						entity.getId(),
						entity.getCreateDate(),
						entity.getUpdateDate(),
						entity.getUsername(),
						entity.getPassword(),
						entity.getName(),
						entity.getDateOfBirth(),
						entity.getSpectialty(),
						entity.getSalary().doubleValue(),
						entity.getDescription(),
						entity.getPhoneNumber(),
						entity.getAddress(),
						entity.getStatus()
						)).collect(Collectors.toList());
		return doctors;
	}
	
	UserModel userToModel(User entity) {
				UserModel model = new UserModel();
				model.setId(entity.getId());
				model.setCreateDate(entity.getCreateDate());
				model.setUpdateDate(entity.getUpdateDate());
				model.setUsername(entity.getUsername());
				model.setPasswrod(entity.getPassword());
				model.setName(entity.getName());
				model.setDateOfBirth(entity.getDateOfBirth());
				model.setSpectialty(entity.getSpectialty());
				model.setSalary(entity.getSalary().doubleValue());
				model.setDescription(entity.getDescription());
				model.setPhoneNumber(entity.getPhoneNumber());
				model.setAddress(entity.getAddress());
				model.setStatus(entity.getStatus());
			return model;
	}
	
	User userToEntity(UserModel model) {
		User entity = new User();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setUsername(model.getUsername());
		entity.setPassword(model.getPasswrod());
		entity.setName(model.getName());
		entity.setDateOfBirth(model.getDateOfBirth());
		entity.setSpectialty(model.getSpectialty());
		entity.setSalary(BigDecimal.valueOf(model.getSalary()));
		entity.setDescription(model.getDescription());
		entity.setPhoneNumber(model.getPhoneNumber());
		entity.setAddress(model.getAddress());
		entity.setStatus(model.getStatus());
		return entity;
	}
	
	List<MessageModel> mesageEntitiesToModels(List<Message> mesageEntities){
		List<MessageModel> messages = mesageEntities.stream()
				.map(messageEntity -> new MessageModel(
						messageEntity.getId(),
						messageEntity.getCreateDate(),
						messageEntity.getUpdateDate(),						
						messageEntity.getUserOfMessage().getId(),
						messageEntity.getReceiverId(),
						messageEntity.getContent()
						)).collect(Collectors.toList());
		return messages;
	}
	
	MessageModel messageToModel(Message entity) {
		MessageModel model = new MessageModel();
			model.setId(entity.getId());
			model.setCreateDate(entity.getCreateDate());
			model.setUpdateDate(entity.getUpdateDate());
			model.setSenderId(entity.getUserOfMessage().getId());
			model.setReceiverId(entity.getReceiverId());
			model.setContent(entity.getContent());
		return model;
	}

	
	Message messsageToEntity(MessageModel model) {
		Message entity = new Message();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setUserOfMessage(userService.getById(model.getSenderId()));
		entity.setReceiverId(model.getReceiverId());
		entity.setContent(model.getContent());
		return entity;
	}
	
	
	List<MedicalRecordModel> medicalRecordEntitiesToModels(List<MedicalRecord> medicalRecordEntities){
		List<MedicalRecordModel> medicalRecords = medicalRecordEntities.stream()
				.map(entity -> new MedicalRecordModel(
						entity.getId(),
						entity.getCreateDate(),
						entity.getUpdateDate(),						
						entity.getDoctor().getId(),
						entity.getPatient().getId(),
						entity.getTitle(),
						entity.getImage(),
						entity.getDescription()
						)).collect(Collectors.toList());
		return medicalRecords;
	}
	
	MedicalRecordModel medicalRecordToModel(MedicalRecord entity) {
		MedicalRecordModel model = new MedicalRecordModel();
			model.setId(entity.getId());
			model.setCreateDate(entity.getCreateDate());
			model.setUpdateDate(entity.getUpdateDate());
			model.setDoctorId(entity.getDoctor().getId());
			model.setPatientId(entity.getPatient().getId());
			model.setTitle(entity.getTitle());
			model.setImage(entity.getImage());
			model.setDescription(entity.getDescription());
		return model;
	}

	MedicalRecord medicalRecordToEntity(MedicalRecordModel model) {
		MedicalRecord entity = new MedicalRecord();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setDoctor(userService.getById(model.getDoctorId()));
		entity.setPatient(userService.getById(model.getPatientId()));
		entity.setTitle(model.getTitle());
		entity.setImage(model.getImage());
		entity.setDescription(model.getDescription());
		return entity;
	}
	
	List<ScheduleModel> scheduleEntitiesToModels(List<Schedule> scheduleEntities){
		List<ScheduleModel> schedules = scheduleEntities.stream()
				.map(entity -> new ScheduleModel(
						entity.getId(),
						entity.getCreateDate(),
						entity.getUpdateDate(),						
						entity.getDoctorOfSchedule().getId(),
						entity.getPatientOfSchedule().getId(),
						entity.getAppointmentDate(),
						entity.getDescription()
						)).collect(Collectors.toList());
		return schedules;
	}
	
	ScheduleModel scheduleToModel(Schedule entity) {
		ScheduleModel model = new ScheduleModel();
			model.setId(entity.getId());
			model.setCreateDate(entity.getCreateDate());
			model.setUpdateDate(entity.getUpdateDate());
			model.setDoctorId(entity.getDoctorOfSchedule().getId());
			model.setPatientId(entity.getPatientOfSchedule().getId());
			model.setAppointmentDate(entity.getAppointmentDate());
			model.setDescription(entity.getDescription());
		return model;
	}

	
	Schedule scheduleToEntity(ScheduleModel model) {
		Schedule entity = new Schedule();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setDoctorOfSchedule(userService.getById(model.getDoctorId()));
		entity.setPatientOfSchedule(userService.getById(model.getPatientId()));
		entity.setAppointmentDate(model.getAppointmentDate());
		entity.setDescription(model.getDescription());
		return entity;
	}

}
