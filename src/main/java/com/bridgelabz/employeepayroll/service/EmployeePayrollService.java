package com.bridgelabz.employeepayroll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayroll.model.Employee;
import com.bridgelabz.employeepayroll.repository.EmployeePayrollRepository;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{
	@Autowired
	EmployeePayrollRepository repo;

	public String getMessage(String name) {
		return "Welcome "+name;
	}
	public String postMessage(Employee employee) {
		return "Hello "+employee.getFirstName()+""+employee.getLastName()+"!";
	}
	public String putMessage(String name) {
		return "How are you, "+name;
	}
	public String getWelcome() {
		return "Welcome to Employee Payroll !!!";	
	}
	public Employee postDataToRepo(Employee employee) {
		repo.save(employee);
		return employee;
	}
	public List<Employee> getAllData(){
		List<Employee> list=repo.findAll();
		return list;
	}
	public Optional<Employee> getDataById(Integer id) {
		Optional<Employee> newEmployee = repo.findById(id);
		return newEmployee;
	}
	public Employee updateDataById(Integer id,Employee employee) {
		Employee newEmployee = new Employee(id,employee.getFirstName(),employee.getLastName(),employee.getProfilePic(),employee.getDepartment(),employee.getSalary(),employee.getDate(),employee.getNotes());
		repo.save(newEmployee);
		return newEmployee;
	}
	public String deleteDataById(Integer id) {
		repo.deleteById(id);
		return "Employee with ID:"+id+" got deleted";
	}
}
