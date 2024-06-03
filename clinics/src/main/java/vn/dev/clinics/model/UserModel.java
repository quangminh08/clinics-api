package vn.dev.clinics.model;

import java.util.Date;

public class UserModel extends BaseModel {

	private String username;
	private String passwrod;
	private String name;
	private String avatar;
	private Date dateOfBirth;
	private String spectialty;
	private Double salary;
	private String description;
	private String phoneNumber;
	private String address;
	private String personId;

	private String role;
	private Boolean status;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public String getPasswrod() {
		if (this.passwrod == null) {
			return "";
		}
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
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

	public UserModel(Integer id, Date createDate, Date updateDate, String username, String passwrod, String name,
			String avatar, Date dateOfBirth, String spectialty, Double salary, String description, String phoneNumber,
			String address, String personId, String role, Boolean status) {
		super(id, createDate, updateDate);
		this.username = username;
		this.passwrod = passwrod;
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
	}

	public UserModel() {
		super();
	}

}
