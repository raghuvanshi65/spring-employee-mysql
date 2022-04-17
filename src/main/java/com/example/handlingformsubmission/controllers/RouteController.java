package com.example.handlingformsubmission.controllers;

import com.example.handlingformsubmission.daos.EmployeeRepository;
import com.example.handlingformsubmission.entities.Employee;
import com.example.handlingformsubmission.entities.LoginDetails;
import com.example.handlingformsubmission.models.DeleteModel;
import com.example.handlingformsubmission.models.EmployeeModel;
import com.example.handlingformsubmission.services.EmployeeService;
import com.example.handlingformsubmission.utility.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class RouteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
	@Autowired
	private Auth auth;

    @RequestMapping(path = {"/","/index"})
    public String indexView(Model model){
        model.addAttribute("welcome" , "Hey Fellas Welcome to the App");
        return "index";
    }

    @RequestMapping(path = {"/register"} , method = RequestMethod.GET)
    public String registerView(Model model){
        LOGGER.info("Registration view is being called !!");
        EmployeeModel employee = new EmployeeModel();
        model.addAttribute("employee" , employee);
        model.addAttribute("title" , "Welcome to the Registration Page");
        return "register";
    }

    @RequestMapping(path = {"/login"} , method = RequestMethod.GET)
    public String loginView(Model model){
        EmployeeModel employee = new EmployeeModel();
        model.addAttribute("employee" , employee);
        model.addAttribute("title" , "Hey Fella! Please Login to Continue");
        return "login";
    }

    @RequestMapping(path = {"/users"})
    public String usersView(Model model){
        model.addAttribute("title" , "Checkout the list of users");
        model.addAttribute("list" , employeeService.getAll());
        return "users";
    }

    @RequestMapping(path = {"/success"})
    public String successView(Model model){
        model.addAttribute("title" , "User successfully registered");
        return "success";
    }
    
    @RequestMapping(path = {"/edit/{id}"} , method = RequestMethod.GET)
    public String editView(Model model, @PathVariable("id") String id){
    	LOGGER.info(id);
    	
		if(!auth.isLoggedIn(id))
			return "redirect:/login";
			
    	EmployeeModel empModel = employeeService.getById(id);
        model.addAttribute("title" , "User successfully editable");
        model.addAttribute("name" , empModel.getFirstname()+" "+empModel.getLastname());
        model.addAttribute("employee" , empModel);
        return "edit";
    }
    
    @RequestMapping(path = {"/delete/{id}"} , method = RequestMethod.GET)
    public String deleteView(Model model, @PathVariable("id") String id){
    	LOGGER.info(id);
    	
		if(!auth.isLoggedIn(id))
			return "redirect:/login";
    	
		Employee emp = employeeRepository.getById(id);
    	DeleteModel del = new DeleteModel(2 , id);
        model.addAttribute("title" , "User successfully Deletable");
        model.addAttribute("delete" , del);
        model.addAttribute("name" , emp.getFirstname()+" "+emp.getLastname());
        return "delete";
    }
    
    @RequestMapping(path = {"/signout/{id}"} , method = RequestMethod.GET)
    public String signout(Model model, @PathVariable("id") String id){
    	LOGGER.info(id);
    	Employee emp1 = employeeRepository.getById(id);
    	if(emp1!=null){
    		LoginDetails log = emp1.getLoginDetails();
    		log.setLoggedIn(false);
    		employeeRepository.save(emp1);
    	}
        return "redirect:/";
    }
}
