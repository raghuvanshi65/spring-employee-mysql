/**
 * 
 */
package com.example.handlingformsubmission.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author anura
 *
 */

@Entity(name = "LoginDetails")
@Table(name = "login_details")
public class LoginDetails {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid" , strategy = "uuid")
	@Column(name = "id")
	private String id;
	
	@Column(name = "logged_in")
	private Boolean loggedIn;
	
	@Column(name = "last_logged_in")
	private Timestamp lastLoggedIn;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private Employee employee;

	public LoginDetails() {}
	
	public LoginDetails(Boolean loggedIn, Timestamp lastLoggedIn) {
		super();
		this.loggedIn = loggedIn;
		this.lastLoggedIn = lastLoggedIn;
	}
	
	public LoginDetails(Boolean loggedIn, Timestamp lastLoggedIn, Employee employee) {
		super();
		this.loggedIn = loggedIn;
		this.lastLoggedIn = lastLoggedIn;
		this.employee = employee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Timestamp getLastLoggedIn() {
		return lastLoggedIn;
	}

	public void setLastLoggedIn(Timestamp lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
