package com.bridgelabz.employeepayroll.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.employeepayroll.model.Employee;

public interface IEmployeePayrollService {
	
	public String getMessage(String name) ;
	public String postMessage(Employee employee);
	public String putMessage(String name) ;
	public String getWelcome();
	public Employee postDataToRepo(Employee employee);
	public List<Employee> getAllData();
	public Optional<Employee> getDataById(Integer id);
	public Employee updateDataById(Integer id,Employee employee);
	public String deleteDataById(Integer id);
}
