package vn.dev.clinics.model;

import java.util.Date;

public class ScheduleModel extends BaseModel {
	private int doctorId;
	private int patientId;
	private Date appointmentDate;
	private String description;

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ScheduleModel(int id, Date createDate, Date updateDate, int doctorId, int patientId, Date appointmentDate,
			String description) {
		super(id, createDate, updateDate);
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.appointmentDate = appointmentDate;
		this.description = description;
	}

	public ScheduleModel() {
	}

}
