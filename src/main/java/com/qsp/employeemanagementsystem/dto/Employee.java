package com.qsp.employeemanagementsystem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be null")
	private String name;
	@Column(unique = true)
	@Min(6000000000l)
	@Max(9000000000l)
	// @Pattern(regexp = "[6-9][0-9]{9}")// it is used to number data stored in
	// String type to validate
	private long phone;
	@Column(unique = true)
//	@NotNull(message = "Email can't be null")
//	@NotBlank(message = "Email can't be null")
	@Email(regexp = "[a-z._+$]+@[a-z]+\\.[a-z]{2,3}", message = "Invalid Email")
	private String email;
	private String password;
	private String address;
	@Min(1)
	private double salary;
	private char grade;

}
