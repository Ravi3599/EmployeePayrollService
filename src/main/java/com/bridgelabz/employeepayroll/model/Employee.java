package com.bridgelabz.employeepayroll.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;

import lombok.Data;

//Created Employee class having different fields
@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	private String lastName;
	private String profilePic;
	
	@ElementCollection
	@CollectionTable(name="employee_department",joinColumns= @JoinColumn(name="id"))
	@Column(name="department")
	private List<String> department;
	private Long salary ;
	private LocalDate date;
	private String notes;
	
	public Employee() {
		super();
	}
	public Employee(EmployeeDTO dto) {
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		this.profilePic = dto.getProfilePic();
		this.department = dto.getDepartment();
		this.salary =dto.getSalary();
		this.date =dto.getDate();
		this.notes =dto.getNotes();
	}
	public Employee(Integer id, EmployeeDTO employeeDTO) {
		super();
		this.id = id;
		this.firstName = employeeDTO.getFirstName();
		this.lastName = employeeDTO.getLastName();
		this.profilePic = employeeDTO.getProfilePic();
		this.department = employeeDTO.getDepartment();
		this.salary =employeeDTO.getSalary();
		this.date =employeeDTO.getDate();
		this.notes =employeeDTO.getNotes();
	}
}