package com.qsp.employeemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.qsp.employeemanagementsystem.dao.EmployeeDao;
import com.qsp.employeemanagementsystem.dto.Employee;
import com.qsp.employeemanagementsystem.service.EmployeeService;
import com.qsp.employeemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeDao dao;

	@Autowired
	private EmployeeService service;

	@PostMapping("/signup")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@Valid @RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}

	@PostMapping("/save/all")
	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEmployees(
			@Valid @RequestBody List<Employee> employees) {
		return service.saveAllEmployees(employees);
	}

	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<Employee>> loginEmployee(@RequestParam String email,
			@RequestParam String password) {

		return service.loginEmployee(email, password);
	}

	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<Employee>> findEmployee(@RequestParam int id) {
		return service.findEmployee(id);
	}

	@GetMapping("/find/name")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeeByName(@RequestParam String name) {
		return service.findEmployeeByName(name);
	}

	@GetMapping("/find/email")
	public ResponseEntity<ResponseStructure<Employee>> findEmployee(@RequestParam String email) {
		return service.findEmployee(email);
	}

	@GetMapping("/find/phone")
	public ResponseEntity<ResponseStructure<Employee>> findEmployee(@RequestParam long phone) {
		return service.findEmployee(phone);
	}

	@GetMapping("/find/address")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeeByAddress(@RequestParam String address) {
		return service.findEmployeeByAddress(address);
	}

	@GetMapping("/find/all")
	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmployees() {
		return service.findAllEmployees();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(@RequestParam int id) {

		return service.deleteEmployee(id);
	}

	@DeleteMapping("/delete/email")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(@RequestParam String email) {
		return service.deleteEmployee(email);
	}

	@DeleteMapping("/delete/phone")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(@RequestParam long phone) {
		return service.deleteEmployee(phone);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestParam int id,
			@RequestBody Employee employee) {

		return service.updateEmployee(id, employee);
	}

	@PatchMapping("/update/email")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestParam int id,
			@RequestParam String email) {
		return service.updateEmployee(id, email);
	}

	@PatchMapping("/update/phone")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestParam int id, @RequestParam long phone) {
		return service.updateEmployee(id, phone);
	}

	@PatchMapping("/update/address")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeAddress(@RequestParam int id,
			@RequestParam String address) {

		return service.updateEmployeeAddress(id, address);
	}

	@PatchMapping("/update/salary")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestParam int id,
			@RequestParam double salary) {

		return service.updateEmployee(id, salary);
	}

	@GetMapping("sal/lessthan")
	public List<Employee> employeeBySalaryLessThan(@RequestParam double salary) {
		return dao.employeeBySalLessThan(salary);
	}

	@GetMapping("sal/greterthan")
	public List<Employee> employeeBySalaryGreterThan(@RequestParam double salary) {

		return dao.employeeBySalGreterThan(salary);

	}

	@GetMapping("/sal/between")
	public List<Employee> employeesalaryBetween(@RequestParam double salary1, @RequestParam double salary2) {
		return dao.employeeBySalBetween(salary1, salary2);

	}

}
