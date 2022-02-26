package com.bridgelabz.employeepayroll.service;

import java.util.List;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.Employee;

public interface IEmployeePayrollService {
	
	public String getWelcome();
	public String postDataToRepo(EmployeeDTO employeeDTO);
	public List<Employee> getAllData();
	public List<Employee> getEmployeePayrollData(String token);
	public Employee getDataById(Integer id);
	public Employee getDataByToken(String token);
	public Employee updateDataById(Integer id,EmployeeDTO employeeDTO);
	public Employee updateDataByToken(String token,EmployeeDTO employeeDTO);
	public Employee deleteDataById(Integer id);
	public Employee deleteDataByToken(String token);
	public List<Employee> getDataByDepartment(String department);
}