package com.example.handlingformsubmission.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.example.handlingformsubmission.daos.EmployeeRepository;
import com.example.handlingformsubmission.entities.Employee;
import com.example.handlingformsubmission.entities.LoginDetails;
import com.example.handlingformsubmission.models.EmployeeModel;
import com.example.handlingformsubmission.utility.PasswordEncrypter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private final EmployeeRepository employeeRepository;

	private final PasswordEncrypter passEncrypter = new PasswordEncrypter();

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void addEmployee(EmployeeModel employeeModel) throws Exception{
		try {
			Employee newEmployee = new Employee(employeeModel);
			LoginDetails loginDetails = new LoginDetails(false, new Timestamp(System.currentTimeMillis()) , newEmployee);
			String hash = passEncrypter.hashPassword(employeeModel.getPassword());
			newEmployee.setPassword(hash);
			newEmployee.setLoginDetails(loginDetails);
			employeeRepository.save(newEmployee);
			employeeModel.setPassword(hash);
		} catch (Exception e) {
			LOGGER.error("exception occurred in employee service");
			e.printStackTrace();
			throw e;
		}
	}

	public List<EmployeeModel> getAll(){
		try {
			List<Employee> employeeList = employeeRepository.findAll();
			return employeeList.stream().map(EmployeeModel::new).collect(Collectors.toList());
		}catch (Exception e){
			LOGGER.error("exception occurred in employee service");
			e.printStackTrace();
			return null;
		}
	}
	
	public Employee isEmployee(EmployeeModel employeeModel) {
		try {
			String hash = passEncrypter.hashPassword(employeeModel.getPassword());
			List<Employee> empList = employeeRepository.findByEmailAndPassword(employeeModel.getEmail(), hash);
			if(!empList.isEmpty()) {
				Employee emp = empList.get(0);
				LoginDetails newLogin = emp.getLoginDetails();
				newLogin.setLoggedIn(true);
				newLogin.setLastLoggedIn(new Timestamp(System.currentTimeMillis()));
				emp.setLoginDetails(newLogin);
				employeeRepository.save(emp);
				return emp;
			}
			return null;
		} catch (Exception e) {
			LOGGER.error("exception occurred in employee service");
			e.printStackTrace();
			return null;
		}
	}
	
	public EmployeeModel getById(String id) {
		try {
			Employee emp = employeeRepository.getById(id);
			if(emp!=null)
				return new EmployeeModel(emp);
			return null;
		} catch (Exception e) {
			LOGGER.error("exception occurred in employee service");
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean getLogById(String id) {
		try {
			Employee emp = employeeRepository.getById(id);
			if(emp!=null)
				return emp.getLoginDetails().getLoggedIn();
			return false;
		} catch (Exception e) {
			LOGGER.error("exception occurred in employee service");
			e.printStackTrace();
			return false;
		}
	}
	

}
