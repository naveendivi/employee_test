package com.test.employee.controller;
 
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.test.employee.entity.EmployeeDetails;
import com.test.employee.entity.EmployeeModel;
import com.test.employee.service.EmployeeService;
 
import jakarta.validation.Valid;
 
@RestController
@RequestMapping("api")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@PostMapping
	@RequestMapping("employees")
	public ResponseEntity<?> postEmployee(@RequestBody @Valid EmployeeModel employee ,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,String> errors = new HashMap<>();
			for(FieldError error :result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errors);
		}

		employeeService.EmployeeData(employee);
		return ResponseEntity.ok("Saved");
	}
	@GetMapping
	@RequestMapping("employees/{employeeId}/tax-deductions")
	public ResponseEntity<EmployeeDetails> getEmployeeDetails(@PathVariable("employeeId") String id)
			throws ParseException {
 
		EmployeeDetails emp = employeeService.processEmployee(id);
 
		return ResponseEntity.ok(emp);
	}
 
}