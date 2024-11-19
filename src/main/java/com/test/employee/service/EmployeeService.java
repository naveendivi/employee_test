package com.test.employee.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.employee.entity.EmployeeDetails;
import com.test.employee.entity.EmployeeModel;
import com.test.employee.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	public void EmployeeData(EmployeeModel employee) {

		employeeRepo.saveAndFlush(employee);
	}

	public EmployeeDetails processEmployee(String id) throws ParseException {
		EmployeeModel emp = employeeRepo.findByemployeeId(id);
		EmployeeDetails emps = new EmployeeDetails();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int daysWorked = calucalateWorking(emp.getDoj(), year);
		double requiredAmount = (emp.getSalary() / 365) * daysWorked;
		double taxAmount = calculateTax(requiredAmount);
		double cess = (requiredAmount > 2500000) ? (requiredAmount - 2500000) * 0.02 : 0;
		emps.setEmployeeId(emp.getEmployeeId());
		emps.setFirstName(emp.getFirstName());
		emps.setLastName(emp.getLastName());
		emps.setYearlySalary(String.valueOf(emp.getSalary()));
		emps.setTaxAmount(String.valueOf(Math.round(taxAmount * 100.0) / 100.0));
		emps.setCessAmount(String.valueOf(Math.round(cess * 100.0) / 100.0));

		return emps;

	}

	private static int calucalateWorking(String doj, int year) throws ParseException {

		SimpleDateFormat fomat = new SimpleDateFormat("YYYY-MM-DD");
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");

		Date dojdate = fomat.parse(doj);
		dojdate = format.parse(format.format(dojdate));
		Calendar date = Calendar.getInstance();
		date.setTime(dojdate);

		int startDat = date.get(Calendar.DAY_OF_YEAR);
		int daysinyear = date.getActualMaximum(Calendar.DAY_OF_YEAR);
		return daysinyear - startDat + 1;

	}

	private static double calculateTax(double salary) {
		double amount = 0;

		if (salary > 1000000) {
			amount += (salary - 1000000) * 0.02;
		}

		if (salary > 500000) {
			amount += (salary - 500000) * 0.1;
		}
		if (salary > 250000) {
			amount += (salary - 250000) * 0.05;
		}
		return amount;

	}

}
