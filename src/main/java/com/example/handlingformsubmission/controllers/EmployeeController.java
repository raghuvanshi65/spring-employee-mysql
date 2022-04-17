package com.example.handlingformsubmission.controllers;

import com.example.handlingformsubmission.daos.EmployeeRepository;
import com.example.handlingformsubmission.entities.Employee;
import com.example.handlingformsubmission.models.DeleteModel;
import com.example.handlingformsubmission.models.EmployeeModel;
import com.example.handlingformsubmission.services.EmployeeService;
import com.example.handlingformsubmission.utility.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
public class EmployeeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private Auth auth;

	private boolean validateInput(EmployeeModel employeeModel) {
		if(employeeModel.getEmail()==null||employeeModel.getFirstname()==null||employeeModel.getPassword()==null) 
			return false;
		if(employeeModel.getEmail().isEmpty()||employeeModel.getFirstname().isEmpty()||employeeModel.getPassword().isEmpty())
			return false;
		return true;
	}

	@Transactional
	@PostMapping(path = "/registeruser")
	public ModelAndView registerEmployee(@ModelAttribute("employee") EmployeeModel employeeModel, Model model) {
		LOGGER.info("register user is being called");
		LOGGER.info(employeeModel.toString());
		ModelAndView md = new ModelAndView();
		try {
			if(!validateInput(employeeModel)) {
				LOGGER.info("not valid input");
				EmployeeModel employeeModel2 = new EmployeeModel();
				md.addObject("error" , true);
				md.addObject("employee2" , employeeModel2);
				md.setViewName("redirect:/register");
				return md;
			}
			employeeService.addEmployee(employeeModel);
			md.addObject("message" , "User with email"+employeeModel.getEmail()+" is added");
			md.setViewName("redirect:/success");
			return md;
		} catch (Exception e) {
			LOGGER.error("An exception occurred while saving employee");
			e.printStackTrace();
			md.addObject("error" , e.toString());
			md.setViewName("redirect:/error");
			return md;
		}
	}
	
	@Transactional
	@PostMapping("/login")
	public ModelAndView loginEmployee(@ModelAttribute("employee") EmployeeModel employeeModel ,Model model) {
		LOGGER.info("employee is being authenticated ");
		LOGGER.info(employeeModel.toString());
		ModelAndView md = new ModelAndView();
		try {
			if(employeeModel.getEmail().isEmpty()||employeeModel.getPassword().isEmpty()) {
				EmployeeModel employeeModel2 = new EmployeeModel();
				md.addObject("error" , "the input fields are not field correctly");
				md.addObject("employee2" , employeeModel2);
				md.setViewName("redirect:/register");
				return md;
			}
			Employee emp = employeeService.isEmployee(employeeModel);
			if(emp!=null) {
				md.addObject("id" , emp.getId());
				md.addObject("message" , "successful");
				md.setViewName("redirect:/users");
				return md;
			}
			md.addObject("message" , "unsuccessful");
			md.setViewName("redirect:/login");
			return md;
		} catch (Exception e) {
			LOGGER.error("An exception occurred while saving employee");
			e.printStackTrace();
			md.addObject("error" , e.toString());
			md.setViewName("redirect:/error");
			return md;
		}
	}
	
	
	@Transactional
	@PostMapping(path = "/delete")
	public ModelAndView deleteEmployee(@ModelAttribute("delete") DeleteModel delete, Model model) {
		ModelAndView md = new ModelAndView();
		try {
			if(!auth.isLoggedIn(delete.getId())) {
				md.setViewName("redirect:/login");
				return md;
			}
			md.addObject("fromdel",true);
			md.addObject("id" , delete.getId());
			md.addObject("message" , "successful");
			if(delete.getOption()==1) {
				employeeRepository.deleteById(delete.getId());
				md.addObject("del" , true);
				md.setViewName("redirect:/register");
				return md;
			}else {
				LOGGER.info("User chose to not delete the account with id: "+delete.getId());
				md.addObject("del" , false);
				md.setViewName("redirect:/users");
				return md;
			}
		} catch (Exception e) {
			LOGGER.error("An exception occurred while saving employee");
			e.printStackTrace();
			md.addObject("error" , e.toString());
			md.setViewName("redirect:/error");
			return md;
		}
	}
	
	@PostMapping("/edit")
	public ModelAndView editEmployee(@ModelAttribute("employee") EmployeeModel employeeModel, Model model) {
		ModelAndView md = new ModelAndView();
		try {
			if(!auth.isLoggedIn(employeeModel.getId())) {
				md.setViewName("redirect:/login");
				return md;
			}
			md.addObject("fromedit",true);
			md.addObject("id" , employeeModel.id);
			md.addObject("message" , "successful");
			Employee emp1 = employeeRepository.getById(employeeModel.id);
			emp1.setEmail(employeeModel.getEmail());
			emp1.setContact(Integer.parseInt(employeeModel.getContact()));
			emp1.setFirstname(employeeModel.getFirstname());
			emp1.setLastname(employeeModel.getLastname());
			employeeRepository.saveAndFlush(emp1);
			LOGGER.info("Employee edited successfully");
			md.addObject("edit" , true);
			md.setViewName("redirect:/users");
			return md;
			
		} catch (Exception e) {
			LOGGER.error("An exception occurred while saving employee");
			e.printStackTrace();
			md.addObject("error" , e.toString());
			md.setViewName("redirect:/error");
			return md;
		}
	}
	
}
