package com.bridgelabz.employeepayroll.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;


import lombok.Data;

//Created EmployeeDTO class and added validations to fields so entered data gets validated before storing
@Data
public class EmployeeDTO {
	@NotEmpty(message = "First Name cannot be empty!")
	@Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="Employee firstName is Invalid")
	private String firstName;
	
	@NotEmpty(message = "Last Name cannot be empty!")
	@Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="Employee lastName is Invalid")
	private String lastName;
	
	@NotEmpty(message = "The profile picture has to be provided")
	private String profilePic;
	
	@NotEmpty(message="Department name has to be provided")
	private List<String> department;
	
	@Min(value=500,message="Salary should be more than 500")
	private Long salary ;
	
	@PastOrPresent(message="Date should be past or todays date")
	private LocalDate date;
	private String notes;
	
	public EmployeeDTO() {
		super();
	}	
}