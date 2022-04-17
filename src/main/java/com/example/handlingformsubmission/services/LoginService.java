package com.example.handlingformsubmission.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.handlingformsubmission.daos.LoginDetailsRepository;

@Service
public class LoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	public LoginDetailsRepository loginDetailsRepository;
}
