package com.bridgelabz.employeepayroll.service;

import java.util.List;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.Employee;

public interface IEmployeePayrollService {
	
	public String getMessage(String name) ;
	public String postMessage(EmployeeDTO employeeDTO);
	public String putMessage(String name) ;
	public String getWelcome();
	public Employee postDataToRepo(EmployeeDTO employeeDTO);
	public List<Employee> getAllData();
	public Employee getDataById(Integer id);
	public Employee updateDataById(Integer id,EmployeeDTO employeeDTO);
	public String deleteDataById(Integer id);
}
