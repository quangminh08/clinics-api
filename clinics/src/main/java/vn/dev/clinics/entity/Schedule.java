package vn.dev.clinics.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Column(name = "description", length = 5000, nullable = true )
	private String description;

	public Schedule(Integer id, Date createDate, Date updateDate, User doctorOfSchedule, User patientOfSchedule,
			Date appointmentDate, String description) {
		super(id, createDate, updateDate);
		this.doctorOfSchedule = doctorOfSchedule;
		this.patientOfSchedule = patientOfSchedule;
		this.appointmentDate = appointmentDate;
		this.description = description;
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
	
}
