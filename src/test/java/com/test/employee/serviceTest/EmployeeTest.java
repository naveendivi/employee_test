package com.test.employee.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.test.employee.entity.EmployeeModel;
import com.test.employee.repository.EmployeeRepo;
import com.test.employee.service.EmployeeService;

@DataJpaTest
public class EmployeeTest {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Test
	public void testSaveAndFlush() {
		EmployeeModel emp = new EmployeeModel();
		emp.setEmployeeId("ee121");
		emp.setFirstName("john");
		emp.setLastName("jount");
		emp.setEmail("john@email.com");
		emp.setPhoneNumbers(List.of("1221212121", "12121211212"));
		emp.setDoj("2023-12-12");
		emp.setSalary(50000.0);
		emp = employeeRepo.save(emp);

		EmployeeModel empDb = employeeRepo.findByemployeeId(emp.getEmployeeId());
		assertThat(empDb).isNotNull();
		assertThat(empDb.getEmployeeId()).isEqualTo("ee121");
		assertThat(empDb.getSalary()).isEqualTo(50000.0);

	}

	@Test
	public void testTaxAmount() {

		assertEquals(37500, employeeService.calculateTax(600000.0));

	}
	
	@Test
	public void testWorkingDays() throws ParseException {

		assertEquals(2, employeeService.calucalateWorking("2023-12-30",2024));

	}
	
	
	

}
