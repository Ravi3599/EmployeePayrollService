package com.bridgelabz.employeepayroll.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayroll.model.Employee;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{

	public String getMessage(String name) {
		return "Welcome "+name;
	}

	public String postMessage(Employee employee) {
		return "Hello "+employee.getFirstName()+""+employee.getLastName()+"!";
	}

	public String putMessage(String name) {
		return "How are you, "+name;
	}

}
