package com.qsp.employeemanagementsystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.employeemanagementsystem.dto.Employee;
import com.qsp.employeemanagementsystem.repo.EmployeeRepo;
import com.qsp.employeemanagementsystem.util.ResponseStructure;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepo repo;

	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);
	}

	public List<Employee> saveAllEmployees(List<Employee> employees) {
		return repo.saveAll(employees);
	}

	public Employee findEmployee(int id) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public List<Employee> findEmployeeByName(String name) {
		return repo.findByName(name);
	}

	public Employee findEmployee(String email) {
		return repo.findByEmail(email);
	}

	public Employee findEmployee(long phone) {
		return repo.findByPhone(phone);
	}

	public List<Employee> findEmployeeByAddress(String address) {
		return repo.findByAddress(address);
	}

	public List<Employee> findAllEmployees() {
		return repo.findAll();
	}

	public Employee deleteEmployee(int id) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			Employee employee = optional.get();
			repo.delete(employee);
			return employee;
		} else {
			return null;
		}
	}

	public Employee deleteEmployee(String email) {
		Employee employee = repo.findByEmail(email);
		if (employee != null) {
			repo.delete(employee);
			return employee;
		} else {
			return null;
		}
	}

	public Employee deleteEmployee(long phone) {
		Employee employee = repo.findByPhone(phone);
		if (employee != null) {
			repo.delete(employee);
			return employee;
		} else {
			return null;
		}
	}

	public Employee updateEmployee(int id, Employee employee) {
		Optional<Employee> dbEmployee = repo.findById(id);
		if (dbEmployee!=null) {
			employee.setId(id);
			return repo.save(employee);
		} else {
			return null;
		}
	}

	public Employee updateEmployee(int id, long phone) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			Employee employee = optional.get();
			employee.setPhone(phone);
			return repo.save(employee);
		} else {
			return null;
		}
	}

	public Optional<Employee> updateEmployee(int id, String email) {
//		Optional<Employee> optional = repo.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setEmail(email);
//			return repo.save(employee);
//		} else {
//			return null;
//		}
		return repo.findById(id);
	}

	public Employee updateEmployee(int id, double salary) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			Employee employee = optional.get();
			employee.setSalary(salary);
			return repo.save(employee);
		} else {
			return null;
		}
	}

	public Employee updateEmployeeAddress(int id, String address) {
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			Employee employee = optional.get();
			employee.setAddress(address);
			return repo.save(employee);
		} else {
			return null;
		}
	}

	public List<Employee> employeeBySalLessThan(double salary) {
		return repo.findBySalaryLessThan(salary);
	}

	public List<Employee> employeeBySalGreterThan(double salary) {
		return repo.findBySalaryGreaterThan(salary);
	}

	public List<Employee> employeeBySalBetween(double salary1, double salary2) {
		return repo.findBySalaryBetween(salary1, salary2);
	}
	

}
