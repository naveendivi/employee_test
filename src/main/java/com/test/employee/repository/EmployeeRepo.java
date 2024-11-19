package com.test.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.employee.entity.EmployeeModel;

public interface EmployeeRepo extends JpaRepository<EmployeeModel ,Long> {
	
	
	public EmployeeModel findByemployeeId( String id);
	
	

}
