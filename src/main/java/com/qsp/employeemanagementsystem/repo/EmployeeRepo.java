    package com.qsp.employeemanagementsystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.employeemanagementsystem.dto.Employee;
import com.qsp.employeemanagementsystem.util.ResponseStructure;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	@Query("select e from Employee e where e.password=?1")
	Employee loginEmployee(String email, String password);

	Employee findByEmail(String email);

	Employee findByPhone(long phone);

	List<Employee> findByName(String name);

	List<Employee> findByAddress(String address);

	List<Employee> findBySalaryLessThan(double salary);

	List<Employee>findBySalaryGreaterThan(double salary);

	@Query("select e from Employee e where e.salary between ?1 and ?2")
	List<Employee> findBySalaryBetween(double salary1, double salary2);

}
