package vn.dev.clinics.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_medical_record")
public class MedicalRecord extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="doctor_id")
	User doctorOfMedicalRecord;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="patient_id")
	User patientOfMedicalRecord;
	
	@Column(name = "title", length = 500, nullable = true )
	private String title;

	@Column(name = "image", length = 255, nullable = true )
	private String image;
	
	@Column(name = "description", nullable = true )
	private String description;

	public MedicalRecord(Integer id, Date createDate, Date updateDate, User doctorOfMedicalRecord,
			User patientOfMedicalRecord, String title, String image, String description) {
		super(id, createDate, updateDate);
		this.doctorOfMedicalRecord = doctorOfMedicalRecord;
		this.patientOfMedicalRecord = patientOfMedicalRecord;
		this.title = title;
		this.image = image;
		this.description = description;
	}

	public MedicalRecord() {}

	public User getDoctor() {
		return doctorOfMedicalRecord;
	}

	public void setDoctor(User doctor) {
		this.doctorOfMedicalRecord = doctor;
	}

	public User getPatient() {
		return patientOfMedicalRecord;
	}

	public void setPatient(User patient) {
		this.patientOfMedicalRecord = patient;
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

	public User getDoctorOfMedicalRecord() {
		return doctorOfMedicalRecord;
	}

	public void setDoctorOfMedicalRecord(User doctorOfMedicalRecord) {
		this.doctorOfMedicalRecord = doctorOfMedicalRecord;
	}

	public User getPatientOfMedicalRecord() {
		return patientOfMedicalRecord;
	}

	public void setPatientOfMedicalRecord(User patientOfMedicalRecord) {
		this.patientOfMedicalRecord = patientOfMedicalRecord;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
