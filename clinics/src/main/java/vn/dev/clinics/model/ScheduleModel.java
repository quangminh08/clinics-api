package vn.dev.clinics.model;

import java.util.Date;

public class ScheduleModel extends BaseModel {
	private int doctorId;
	private int patientId;
	private Date appointmentDate;
	private Integer hour;
	private String description;
	private Boolean status;

	
	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}



	public ScheduleModel(Integer id, Date createDate, Date updateDate, int doctorId, int patientId,
			Date appointmentDate, Integer hour, String description, Boolean status) {
		super(id, createDate, updateDate);
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.appointmentDate = appointmentDate;
		this.hour = hour;
		this.description = description;
		this.status = status;
	}

	public ScheduleModel() {
	}

}
