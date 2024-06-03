package vn.dev.clinics.model;

public class SearchModel {

	String name;
	String phone;
	String date;

	public SearchModel() {
	}

	public SearchModel(String name, String phone, String date) {
		super();
		this.name = name;
		this.phone = phone;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
