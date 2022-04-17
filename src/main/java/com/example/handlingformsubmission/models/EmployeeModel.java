package com.example.handlingformsubmission.models;

import com.example.handlingformsubmission.entities.Employee;

public class EmployeeModel {
		
	public String id;
	private String email;
	private String firstname;
	private String lastname;
	private String contact;
	private String password;
	
	public EmployeeModel() {
	}
	
	public EmployeeModel(String email, String firstname, String lastname, String contact, String password) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.contact = contact;
		this.password = password;
	}

	public EmployeeModel(Employee employee){
		this.id = employee.getId();
		this.email = employee.getEmail();
		this.firstname = employee.getFirstname();
		this.lastname = employee.getLastname();
		this.contact = Integer.toString(employee.getContact());
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EmployeeModel [email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", contact="
				+ contact + ", password=" + password + "]";
	}
	
}
