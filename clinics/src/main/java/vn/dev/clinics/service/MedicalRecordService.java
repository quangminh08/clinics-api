package vn.dev.clinics.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.dev.clinics.constants.Constants;
import vn.dev.clinics.entity.MedicalRecord;
import vn.dev.clinics.model.MedicalRecordModel;

@Service
public class MedicalRecordService extends BaseService<MedicalRecord> implements Constants{
	
	@Override
	public Class<MedicalRecord> clazz() {
		return MedicalRecord.class;
	}
		
		
	@Autowired
	private TransmitService transmitService;
	
	public MedicalRecordModel getModelById(Integer id) {
		MedicalRecord entity = super.getById(id);
		return transmitService.medicalRecordToModel(entity);
	}

	public List<MedicalRecordModel> findAllModel(){
		List<MedicalRecordModel> models = transmitService.medicalRecordEntitiesToModels(super.findAll());
		return models;
	}
	

	public boolean isEmptyUploadImage(MultipartFile avatarFile) {
		if(avatarFile == null || avatarFile.getOriginalFilename().isEmpty()) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public MedicalRecord saveMedicalRecord(MedicalRecordModel medicalRecordModel, MultipartFile fileUpload) throws IllegalStateException, IOException {
		MedicalRecord medicalRecord = transmitService.medicalRecordToEntity(medicalRecordModel);
		String path;
		// Save image
		if(!isEmptyUploadImage(fileUpload)) {
			//save 
			path = STORAGE_FOLDER + "/medical_record_photo/" + fileUpload.getOriginalFilename();
			fileUpload.transferTo(new File(path));
			
			// save path into database
			medicalRecord.setImage("medical_record_photo/" + fileUpload.getOriginalFilename());
		}
		medicalRecord.setCreateDate(new Date());
		return super.saveOrUpdate(medicalRecord);
	}
	
	// update
	@Transactional
	public MedicalRecord updateMedicalRecord(MedicalRecordModel medicalRecordModel, MultipartFile fileUpload, Integer id) throws IllegalStateException, IOException {
		MedicalRecord medicalRecord = transmitService.medicalRecordToEntity(medicalRecordModel);
		String path;
		
		MedicalRecord dbMedicalRecord = super.getById(id);
		
		// Save image
		if(!isEmptyUploadImage(fileUpload)) {
			File file = new File(STORAGE_FOLDER + dbMedicalRecord.getImage());
			file.delete();
			
			//save 
			path = STORAGE_FOLDER + "/medical_record_photo/" + fileUpload.getOriginalFilename();
			fileUpload.transferTo(new File(path));
			
			// save path into database
			medicalRecord.setImage("medical_record_photo/" + fileUpload.getOriginalFilename());
		}
		else {
			medicalRecord.setImage(dbMedicalRecord.getImage());
		}
		medicalRecord.setUpdateDate(new Date());
		return super.saveOrUpdate(medicalRecord);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		super.deleteById(id);
	}
}
