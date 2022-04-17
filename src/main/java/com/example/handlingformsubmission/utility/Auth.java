package com.example.handlingformsubmission.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.handlingformsubmission.services.EmployeeService;

@Component
public class Auth {
	private static final Logger LOGGER = LoggerFactory.getLogger(Auth.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	public Boolean isLoggedIn(String id) {
		try {
			LOGGER.info("the id is: "+id);
			return employeeService.getLogById(id);
		} catch (Exception e) {
			LOGGER.error("Exception occurred while Authorizing the user");
			e.printStackTrace();
			return null;
		}
	}
}
