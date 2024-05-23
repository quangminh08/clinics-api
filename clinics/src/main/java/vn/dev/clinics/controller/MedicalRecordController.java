package vn.dev.clinics.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.dev.clinics.model.MedicalRecordModel;
import vn.dev.clinics.service.MedicalRecordService;

@RestController
@RequestMapping("/api/v1/medicalrecords")
public class MedicalRecordController {
	
	@Autowired
	private MedicalRecordService medicalService;
	
	@GetMapping("/{id}")
	public MedicalRecordModel getMedicalRecord(@PathVariable("id") Integer id) {
		return medicalService.getModelById(id);
	}
	
	@GetMapping("")
	public List<MedicalRecordModel> getMedicalRecords() {
		return medicalService.findAllModel();
	}
	
	@PostMapping("")
	public MedicalRecordModel saveMedicalRecord(@RequestBody MedicalRecordModel model ,
								@RequestParam("file") MultipartFile uploadFile) throws IOException{
		if (model.getId() == null || model.getId() <= 0) {
			medicalService.saveMedicalRecord(model, uploadFile);
		}
		return null;
	}
	
	@PutMapping("/{id}")
	public MedicalRecordModel updateMedicalRecord(@RequestBody MedicalRecordModel model ,
								@RequestParam("file") MultipartFile uploadFile,
								@PathVariable("id") Integer id) throws IOException{
			medicalService.updateMedicalRecord(model, uploadFile, id);
		return model;
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Integer id) {
		medicalService.deleteById(id);
	}
	
}
