package vn.dev.clinics.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_schedule")
public class Schedule extends BaseEntity {
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="doctor_id")
	User doctorOfSchedule;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="patient_id")
	User patientOfSchedule;
	
	@Column(name="appointment_date", nullable = true)
	private Date appointmentDate;
	
	@Column(name="hour", nullable = true)
	private Integer hour;
	
	@Column(name = "description", length = 5000, nullable = true )
	private String description;
	
	@Column(name = "status")
	private Boolean status = true;


	public Schedule(Integer id, Date createDate, Date updateDate, User doctorOfSchedule, User patientOfSchedule,
			Date appointmentDate, Integer hour, String description, Boolean status) {
		super(id, createDate, updateDate);
		this.doctorOfSchedule = doctorOfSchedule;
		this.patientOfSchedule = patientOfSchedule;
		this.appointmentDate = appointmentDate;
		this.hour = hour;
		this.description = description;
		this.status = status;
	}

	public Schedule() {	}

	public User getDoctorOfSchedule() {
		return doctorOfSchedule;
	}

	public void setDoctorOfSchedule(User doctorOfSchedule) {
		this.doctorOfSchedule = doctorOfSchedule;
	}

	public User getPatientOfSchedule() {
		return patientOfSchedule;
	}

	public void setPatientOfSchedule(User patientOfSchedule) {
		this.patientOfSchedule = patientOfSchedule;
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

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}
	
	
	
	
}
