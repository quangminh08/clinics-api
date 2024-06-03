package vn.dev.clinics.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity implements UserDetails{
	
	@Column(name = "username", length = 45, nullable = false )
	private String username;
	
	@Column(name = "password", length = 45, nullable = false )
	private String password;
	
	@Column(name = "name", length = 45, nullable = true )
	private String name;
	
	@Column(name = "avatar", length = 255, nullable = true )
	private String avatar;
	
	@Column(name = "date_of_birth", length = 45, nullable = true )
	private Date dateOfBirth;
	
	@Column(name = "spectialty", length = 500, nullable = true)
	private String spectialty;
	
	@Column(name = "salary", nullable = true )
	private BigDecimal salary;
	
	@Column(name = "description", length = 1000, nullable = true )
	private String description;
	
	@Column(name = "phone_number", length = 1000, nullable = true )
	private String phoneNumber;
	
	@Column(name = "address", length = 1000, nullable = true )
	private String address;
	
	@Column(name = "person_id", length = 45, nullable = true )
	private String personId;
	
	@Column(name = "role", length = 45, nullable = true )
	private String role;
	
	
	//inactive account
	@Column(name = "status", nullable = true)
	private Boolean status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userOfMessage")
	private Set<Message> messages = new HashSet<>();
	
	public void addRelationalMessage(Message message) {
		messages.add(message);
		message.setUserOfMessage(this);	
	}
	
	public void deleteRelationalMessage(Message message) {
		messages.remove(message);
		message.setUserOfMessage(null);	
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "doctorOfMedicalRecord")
	private Set<MedicalRecord> medicalRecordsByDoctor = new HashSet<>();
	
	public void addRelationalMedicalRecordByDoctor(MedicalRecord medicalRecord) {
		medicalRecordsByDoctor.add(medicalRecord);
		medicalRecord.setDoctor(this);	
	}
	
	public void deleteRelationalMedicalRecordByDoctor(MedicalRecord medicalRecord) {
		medicalRecordsByDoctor.remove(medicalRecord);
		medicalRecord.setDoctor(null);	
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patientOfMedicalRecord")
	private Set<MedicalRecord> medicalRecordsByPatient = new HashSet<>();
	
	public void addRelationalMedicalRecordByPatient(MedicalRecord medicalRecord) {
		medicalRecordsByPatient.add(medicalRecord);
		medicalRecord.setPatient(this);	
	}
	
	public void deleteRelationalMedicalRecordByPatient(MedicalRecord medicalRecord) {
		medicalRecordsByPatient.remove(medicalRecord);
		medicalRecord.setPatient(null);	
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "doctorOfSchedule")
	private Set<Schedule> schedulesByDoctor = new HashSet<>();
	
	public void addRelationalScheduleByDoctor(Schedule schedule) {
		schedulesByDoctor.add(schedule);
		schedule.setDoctorOfSchedule(this);	
	}
	
	public void deleteRelationalScheduleBYDoctor(Schedule schedule) {
		schedulesByDoctor.remove(schedule);
		schedule.setDoctorOfSchedule(null);	
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patientOfSchedule")
	private Set<Schedule> schedulesByPatient = new HashSet<>();
	
	public void addRelationalScheduleByPatient(Schedule schedule) {
		schedulesByPatient.add(schedule);
		schedule.setPatientOfSchedule(this);	
	}
	
	public void deleteRelationalScheduleByPatient(Schedule schedule) {
		schedulesByPatient.remove(schedule);
		schedule.setPatientOfSchedule(null);	
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	private Set<Role> roles = new HashSet<>();

	public void addRole(Role role) {
		role.getUsers().add(this);
		roles.add(role);
	}

	public void deleteRole(Role role) {
		role.getUsers().remove(this);
		roles.remove(role);
	}



	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public User(Integer id, Date createDate, Date updateDate, String username, String password, String name,
			String avatar, Date dateOfBirth, String spectialty, BigDecimal salary, String description,
			String phoneNumber, String address, String personId, String role, Boolean status, Set<Message> messages,
			Set<MedicalRecord> medicalRecordsByDoctor, Set<MedicalRecord> medicalRecordsByPatient,
			Set<Schedule> schedulesByDoctor, Set<Schedule> schedulesByPatient, Set<Role> roles) {
		super(id, createDate, updateDate);
		this.username = username;
		this.password = password;
		this.name = name;
		this.avatar = avatar;
		this.dateOfBirth = dateOfBirth;
		this.spectialty = spectialty;
		this.salary = salary;
		this.description = description;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.personId = personId;
		this.role = role;
		this.status = status;
		this.messages = messages;
		this.medicalRecordsByDoctor = medicalRecordsByDoctor;
		this.medicalRecordsByPatient = medicalRecordsByPatient;
		this.schedulesByDoctor = schedulesByDoctor;
		this.schedulesByPatient = schedulesByPatient;
		this.roles = roles;
	}

	public User() {
	}
	

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSpectialty() {
		return spectialty;
	}

	public void setSpectialty(String spectialty) {
		this.spectialty = spectialty;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<MedicalRecord> getMedicalRecordsByDoctor() {
		return medicalRecordsByDoctor;
	}

	public void setMedicalRecordsByDoctor(Set<MedicalRecord> medicalRecordsByDoctor) {
		this.medicalRecordsByDoctor = medicalRecordsByDoctor;
	}

	public Set<MedicalRecord> getMedicalRecordsByPatient() {
		return medicalRecordsByPatient;
	}

	public void setMedicalRecordsByPatient(Set<MedicalRecord> medicalRecordsByPatient) {
		this.medicalRecordsByPatient = medicalRecordsByPatient;
	}

	public Set<Schedule> getSchedulesByDoctor() {
		return schedulesByDoctor;
	}

	public void setSchedulesByDoctor(Set<Schedule> schedulesByDoctor) {
		this.schedulesByDoctor = schedulesByDoctor;
	}

	public Set<Schedule> getSchedulesByPatient() {
		return schedulesByPatient;
	}

	public void setSchedulesByPatient(Set<Schedule> schedulesByPatient) {
		this.schedulesByPatient = schedulesByPatient;
	}

	public Set<Schedule> getSchedules() {
		return schedulesByDoctor;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedulesByDoctor = schedules;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
