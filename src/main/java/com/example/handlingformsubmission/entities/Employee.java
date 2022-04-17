/**
 * 
 */
package com.example.handlingformsubmission.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.handlingformsubmission.models.EmployeeModel;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author anura
 *
 */

@Entity(name = "Employee")
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid" , strategy = "uuid")
	@Column(name = "id")
	private String id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "contact")
	private Integer contact;
	
	@Column(name = "pass")
	private String password;
	
	@OneToOne(mappedBy = "employee" , cascade = CascadeType.ALL , 
			fetch = FetchType.LAZY , optional = false)
	private LoginDetails loginDetails;

	Employee(){}

	public Employee(String email, String firstname, String lastname, Integer contact, String password) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.contact = contact;
		this.password = password;
	}

	public Employee(EmployeeModel employeeModel) {
		this.email = employeeModel.getEmail();
		this.firstname = employeeModel.getFirstname();
		this.lastname = employeeModel.getLastname();
		this.contact = Integer.parseInt(employeeModel.getContact());
		this.password = employeeModel.getPassword();
	}

	public Employee(String firstname, String lastname, Integer contact, String password, LoginDetails loginDetails) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.contact = contact;
		this.password = password;
		this.loginDetails = loginDetails;
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

	public Integer getContact() {
		return contact;
	}

	public void setContact(Integer contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDetails getLoginDetails() {
		return loginDetails;
	}

	public void setLoginDetails(LoginDetails loginDetails) {
		if(loginDetails==null) {
			if(this.loginDetails!=null) {
				this.loginDetails.setEmployee(null);
			}
		}else {
			loginDetails.setEmployee(this);
		}
		this.loginDetails = loginDetails;
	}
	
}
