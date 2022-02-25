package com.bridgelabz.employeepayroll.service;

import java.util.List;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.Employee;

public interface IEmployeePayrollService {
	
	public String getWelcome();
	public String postDataToRepo(EmployeeDTO employeeDTO);
	public List<Employee> getAllData();
	public List<Employee> getEmployeePayRollData(String token);
	public Employee getDataById(Integer id);
	public Employee updateDataById(Integer id,EmployeeDTO employeeDTO);
	public String deleteDataById(Integer id);
	public List<Employee> getDataByDepartment(String department);
}