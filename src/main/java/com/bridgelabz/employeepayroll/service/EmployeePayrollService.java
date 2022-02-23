package com.bridgelabz.employeepayroll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.exception.EmployeePayrollException;
import com.bridgelabz.employeepayroll.model.Employee;
import com.bridgelabz.employeepayroll.repository.EmployeePayrollRepository;

import lombok.extern.slf4j.Slf4j;

//Created EmployeePayrollService class to serve api calls done by controller layer
@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService{
	//Autowired EmployeePayrollRepository interface to inject its dependency here
	@Autowired
	EmployeePayrollRepository repo;

	//Abiltity to serve controller class api to return welcome message
	public String getWelcome() {
		return "Welcome to Employee Payroll !!!";	
	}
	//Ability to serve controller class api to store data 
	public Employee postDataToRepo(EmployeeDTO employeeDTO) {
		Employee newEmployee = new Employee(employeeDTO);
		repo.save(newEmployee);
		log.info("Record got saved");
		return newEmployee;
	}
	//Ability to serve controller class api to retrieve all records
	public List<Employee> getAllData(){
		List<Employee> list=repo.findAll();
		log.info("All records got retrived");
		return list;
	}
	//Ability to serve controller class api to retrieve record for particular id
	public Employee getDataById(Integer id) {
//		List<Employee> list = repo.findAll();
//		Employee newEmp=list.stream().filter(empData->empData.getId()==id)
//				.findFirst()
//				.orElseThrow(()->new EmployeePayrollException("Employee Not Found"));
		Optional<Employee> newEmp = repo.findById(id);
		if(newEmp.isEmpty()) {
			log.warn("Unable to find employee for given id: "+id);
			throw new EmployeePayrollException("Employee Not Found");
		}
		return newEmp.get();
	}
	//Ability to serve controller class api to update data for particular id
	public Employee updateDataById(Integer id,EmployeeDTO employeeDTO) {
		Optional<Employee> employee = repo.findById(id);
		if(employee.isEmpty()) {
			throw new EmployeePayrollException("Employee Not Found");
		}
		Employee newEmployee = new Employee(id,employeeDTO);
		repo.save(newEmployee);
		log.info("Record for given id "+id+" got updated");
		return newEmployee;
	}
	//Abiltity to serve controller class api to delete record for particular id
	public String deleteDataById(Integer id) {
//		List<Employee> list = repo.findAll();
//		Employee newEmp=list.stream().filter(empData->empData.getId()==id)
//				.findFirst()
//				.orElseThrow(()->new EmployeePayrollException("Employee Not Found"));
//		repo.delete(newEmp);
		Optional<Employee> newEmp = repo.findById(id);
		if(newEmp.isEmpty()) {
			log.warn("Unable to find employee for given id:"+id);
			throw new EmployeePayrollException("Employee Not Found");
		}
		else {
			repo.deleteById(id);
		}
		return null;
	}
	//Abilty to serve controller class api to retrieve data having particular department
	public List<Employee> getDataByDepartment(String department) {
//		List<Employee> list = repo.findAll();
//		List<Employee> newEmp=(List<Employee>) list.stream().filter(empData->empData.getDepartment()==department)
//				.findFirst()
//				.orElseThrow(()->new EmployeePayrollException("Employee Not Found"));
		List<Employee> newEmp = repo.findByDepartment(department);
		if(newEmp.isEmpty()) {
			log.warn("Unable to find employee for given department: "+department);
			throw new EmployeePayrollException("Employee Not Found");
		}
		return newEmp;	
	}
}