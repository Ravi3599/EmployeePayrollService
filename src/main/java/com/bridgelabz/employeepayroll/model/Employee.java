package com.bridgelabz.employeepayroll.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	private String lastName;
	private String profilePic;
	private String department;
	private Long salary ;
	private LocalDate date;
	private String notes;
	
	
	public Employee() {
		super();
	}
	public Employee(Integer id, Employee employee) {
		super();
		this.id = id;
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.profilePic = employee.getProfilePic();
		this.department = employee.getDepartment();
		this.salary =employee.getSalary();
		this.date =employee.getDate();
		this.notes =employee.getNotes();
	}
	public Employee(Employee employee) {
		super();
		this.id = employee.id;
		this.firstName = employee.firstName;
		this.lastName = employee.lastName;
		this.profilePic = employee.profilePic;
		this.department = employee.department;
		this.salary = employee.salary;
		this.date = employee.date;
		this.notes = employee.notes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
