package com.bridgelabz.employeepayroll.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.exception.EmployeePayrollException;
import com.bridgelabz.employeepayroll.model.Employee;
import com.bridgelabz.employeepayroll.repository.EmployeePayrollRepository;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{
	@Autowired
	EmployeePayrollRepository repo;

	public String getMessage(String name) {
		return "Welcome "+name;
	}
	public String postMessage(EmployeeDTO employeeDTO) {
		return "Hello "+employeeDTO.getFirstName()+""+employeeDTO.getLastName()+"!";
	}
	public String putMessage(String name) {
		return "How are you, "+name;
	}
	public String getWelcome() {
		return "Welcome to Employee Payroll !!!";	
	}
	public Employee postDataToRepo(EmployeeDTO employeeDTO) {
		Employee newEmployee = new Employee(employeeDTO);
		repo.save(newEmployee);
		return newEmployee;
	}
	public List<Employee> getAllData(){
		List<Employee> list=repo.findAll();
		return list;
	}
	public Employee getDataById(Integer id) {
		//Employee employee = repo.findById();
		List<Employee> list = repo.findAll();
		Employee newEmp=list.stream().filter(empData->empData.getId()==id)
				.findFirst()
				.orElseThrow(()->new EmployeePayrollException("Employee Not Found"));
		return newEmp;
	}
	public Employee updateDataById(Integer id,EmployeeDTO employeeDTO) {
		Employee newEmployee = new Employee(id,employeeDTO);
		repo.save(newEmployee);
		return newEmployee;
	}
	public String deleteDataById(Integer id) {
		//repo.deleteById(id);
		List<Employee> list = repo.findAll();
		Employee newEmp=list.stream().filter(empData->empData.getId()==id)
				.findFirst()
				.orElseThrow(()->new EmployeePayrollException("Employee Not Found"));
		repo.delete(newEmp);
		return null;
	}
}
