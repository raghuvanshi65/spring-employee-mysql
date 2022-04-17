package com.example.handlingformsubmission.daos;

import com.example.handlingformsubmission.entities.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
//	@Query("select e from Employee e where e.email = :email and e.pass = :pass")
	List<Employee> findByEmailAndPassword(String email ,String password);
}
