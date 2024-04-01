package com.qsp.employeemanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.employeemanagementsystem.dao.EmployeeDao;
import com.qsp.employeemanagementsystem.dto.Employee;
import com.qsp.employeemanagementsystem.exception.EmailNotFoundException;
import com.qsp.employeemanagementsystem.exception.IdNotFoundExceprtion;
import com.qsp.employeemanagementsystem.exception.NoDataAvailableException;
import com.qsp.employeemanagementsystem.exception.PhoneNotFoundException;
import com.qsp.employeemanagementsystem.util.ResponseStructure;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
		double salary = employee.getSalary();
		if (salary < 10000) {
			employee.setGrade('D');
		} else if (salary > 10000 && salary <= 20000) {
			employee.setGrade('C');
		} else if (salary > 20000 && salary <= 35000) {
			employee.setGrade('B');
		} else {
			employee.setGrade('A');
		}

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setMassage("Signup Success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveEmployee(employee));

		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEmployees(List<Employee> employees) {
		for (Employee employee : employees) {
			double salary = employee.getSalary();
			if (salary < 1000) {

				employee.setGrade('D');

			} else if (salary > 1000 && salary <= 20000) {
				employee.setGrade('C');
			} else if (salary > 20000 && salary <= 35000) {
				employee.setGrade('B');
			} else {
				employee.setGrade('A');
			}
		}
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		structure.setMassage("All Data Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveAllEmployees(employees));

		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Employee>> loginEmployee(String email, String password) {
		Employee employee = dao.findEmployee(email);
		ResponseStructure<Employee> structure = new ResponseStructure<>();

		if (employee != null) {
			if (password.equals(employee.getPassword())) {

				structure.setMassage("Login Success!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(employee);
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			} else {

				structure.setMassage("Login Failed! Incorrect password.");
				structure.setStatus(HttpStatus.UNAUTHORIZED.value());
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.UNAUTHORIZED);
			}
		} else {

//			structure.setMassage("Employee with the given email '" + email + "' not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
			// return new ResponseEntity<ResponseStructure<Employee>>(structure,
			// HttpStatus.NOT_FOUND);
			throw new EmailNotFoundException("Employee with the given email " + email + " not found");

		}
		// throw new EmailNotFoundException("Employee with the given email '" + email +
		// "' not found");

	}

	public ResponseEntity<ResponseStructure<Employee>> findEmployee(int id) {
		Employee employee = dao.findEmployee(id);
		if (employee != null) {
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMassage("Data found Successfully! ");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			structure.setMassage("Employee with the given id " + id + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);

			throw new IdNotFoundExceprtion("Employee with the given id " + id + " not found");

		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeeByName(String name) {
		List<Employee> employees = dao.findEmployeeByName(name);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();

		if (!employees.isEmpty()) {
			structure.setMassage("Data found successfully!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		} else {
//			structure.setMassage("Employees with the given name '" + name + "' not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);
			throw new NoDataAvailableException("No Users Found");
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> findEmployee(String email) {
		Employee employee = dao.findEmployee(email);
		if (employee != null) {

			ResponseStructure<Employee> structure = new ResponseStructure<>();
			structure.setMassage("Data found successfully!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dao.findEmployee(email));

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);

		} else {

//			ResponseStructure<Employee> structure = new ResponseStructure<>();
//			structure.setMassage("Employee with the given email " + email + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);

			throw new EmailNotFoundException("Employee with the given email '" + email + "' not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findEmployee(long phone) {
		Employee employee = dao.findEmployee(phone);
		if (employee != null) {
			ResponseStructure<Employee> structure = new ResponseStructure<>();
			structure.setMassage("Data found successfully!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {

//			ResponseStructure<Employee> structure = new ResponseStructure<>();
//			structure.setMassage("Employee with the given phone " + phone + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
			throw new PhoneNotFoundException("Employee with the given phone " + phone + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeeByAddress(String address) {
		List<Employee> employees = dao.findEmployeeByAddress(address);
		if (employees != null && !employees.isEmpty()) {
			ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
			structure.setMassage("Data found successfully!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		} else {

//			ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
//			structure.setMassage("Employees with the given address " + address + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			// structure.setData(Collections.emptyList());
//
//			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);
			throw new NoDataAvailableException("No Users Found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmployees() {
		List<Employee> list = dao.findAllEmployees();
		if (!list.isEmpty()) {
			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
			structure.setMassage("Data found Successfully! ");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dao.findAllEmployees());

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);

		} else {
			throw new NoDataAvailableException("No Users Found");
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(int id) {
		Employee employee = dao.deleteEmployee(id);
		if (employee != null) {
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMassage("Deleted Success!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {

//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			structure.setMassage("Employee with the given id " + id + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);

			throw new IdNotFoundExceprtion("Employee with the given id " + id + " not found");

		}
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(String email) {
		Employee employee = dao.deleteEmployee(email);
		if (employee != null) {
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMassage("Deleted Success!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);

		} else {
//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			structure.setMassage("Employee with the given email " + email + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);

			throw new EmailNotFoundException("Employee with the given email '" + email + "' not found");

		}
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(long phone) {
		Employee employee = dao.deleteEmployee(phone);
		if (employee != null) {
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMassage("Deleted Success");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);

		} else {

//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			structure.setMassage("Employee with the given phone " + phone + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
			throw new PhoneNotFoundException("Employee with the given phone " + phone + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id, double salary) {
		Employee dbEmployee = dao.updateEmployee(id, salary);
		if (dbEmployee != null) {
			double salary1 = dbEmployee.getSalary();

			if (salary1 < 10000) {
				dbEmployee.setGrade('D');
			} else if (salary1 > 10000 && salary1 <= 20000) {
				dbEmployee.setGrade('C');
			} else if (salary1 > 20000 && salary1 <= 35000) {
				dbEmployee.setGrade('B');
			} else {
				dbEmployee.setGrade('A');
			}
			Employee employee = dao.saveEmployee(dbEmployee);
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMassage("Update Success!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dao.saveEmployee(dbEmployee));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);

		} else {
//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			structure.setMassage("Employee with the given id " + id + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);

			throw new IdNotFoundExceprtion("Employee with the given id " + id + " not found");

		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id, String email) {
		Optional<Employee> dbEmployee = dao.updateEmployee(id, email);
		if (dbEmployee.isPresent()) {
			Employee employee = dbEmployee.get();
			employee.setEmail(email);
			Employee employee1 = dao.saveEmployee(employee);
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMassage("Update Success! ");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee1);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {

//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			structure.setMassage("Employee with the given id " + id + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
			throw new IdNotFoundExceprtion("Employee with the given id " + id + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id, long phone) {
		Employee dbEmployee = dao.updateEmployee(id, phone);
		if (dbEmployee != null) {
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMassage("Update Success! ");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dao.saveEmployee(dbEmployee));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {

//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			structure.setMassage("Employee with the given id " + id + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);

			throw new IdNotFoundExceprtion("Employee with the given id " + id + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeAddress(int id, String address) {
		Employee dbEmployee = dao.updateEmployeeAddress(id, address);
		if (dbEmployee != null) {

			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMassage("Update Success");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dao.saveEmployee(dbEmployee));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {

//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			structure.setMassage("Employee with the given id " + id + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);

			throw new IdNotFoundExceprtion("Employee with the given id " + id + " not found");

		}
	}

	// ****************************************************************************/

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id, Employee employee) {
		Employee dbEmployee = dao.updateEmployee(id, employee);
		ResponseStructure<Employee> structure = new ResponseStructure<>();

		if (dbEmployee != null) {

			double salary = dbEmployee.getSalary();
			if (salary < 10000) {
				dbEmployee.setGrade('D');
			} else if (salary <= 20000) {
				dbEmployee.setGrade('C');
			} else if (salary <= 35000) {
				dbEmployee.setGrade('B');
			} else {
				dbEmployee.setGrade('A');
			}
			Employee updatedEmployee = dao.saveEmployee(dbEmployee);
			structure.setMassage("Update Success!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(updatedEmployee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
//			structure.setMassage("Employee with the given id " + id + " not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);

			throw new IdNotFoundExceprtion("Employee with the given id " + id + " not found");

		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> employeesBySalLessThan(double salary) {
		List<Employee> list = dao.employeeBySalLessThan(salary);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (!list.isEmpty()) {
			structure.setMassage("Data Found Successful");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoDataAvailableException("No Users Found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> employeesBySalGreaterThan(double salary) {
		List<Employee> list = dao.employeeBySalGreterThan(salary);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (!list.isEmpty()) {
			structure.setMassage("Data Found Successful");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoDataAvailableException("No Users Found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> empSalBetween(double salary1, double salary2) {
		List<Employee> list = dao.employeeBySalBetween(salary1, salary2);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (!list.isEmpty()) {
			structure.setMassage("Data Found Successful");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoDataAvailableException("No Users Found");
		}
	}

}
