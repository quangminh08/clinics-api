package vn.dev.clinics.model;

import java.util.Date;

public class PatientModel extends BaseModel {

	private String name;
	private Date dateOfBirth;
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PatientModel(Integer id, Date createDate, Date updateDate, String name, Date dateOfBirth, String description) {
		super(id, createDate, updateDate);
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.description = description;
	}

	public PatientModel() {
	}

}
