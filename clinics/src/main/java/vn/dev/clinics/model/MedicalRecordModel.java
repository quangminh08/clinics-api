package vn.dev.clinics.model;

import java.util.Date;

public class MedicalRecordModel extends BaseModel {

	private Integer doctorId;
	private Integer patientId;
	private String title;
	private String image;
	private String description;

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MedicalRecordModel(Integer id, Date createDate, Date updateDate, Integer doctorId, Integer patientId,
			String title, String image, String description) {
		super(id, createDate, updateDate);
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.title = title;
		this.image = image;
		this.description = description;
	}

	public MedicalRecordModel() {
	}

}
