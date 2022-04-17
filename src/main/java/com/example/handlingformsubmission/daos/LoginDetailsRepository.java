package com.example.handlingformsubmission.daos;

import com.example.handlingformsubmission.entities.Employee;
import com.example.handlingformsubmission.entities.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginDetailsRepository extends JpaRepository<LoginDetails, String> {
	
}
