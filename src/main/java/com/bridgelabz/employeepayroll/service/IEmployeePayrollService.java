package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.model.Employee;

public interface IEmployeePayrollService {
	
	public String getMessage(String name) ;
	public String postMessage(Employee employee);
	public String putMessage(String name) ;
}
