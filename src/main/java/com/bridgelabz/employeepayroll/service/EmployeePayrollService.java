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

@Service
@Slf4j
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
		log.info("Record got saved");
		return newEmployee;
	}
	public List<Employee> getAllData(){
		List<Employee> list=repo.findAll();
		log.info("All records got retrived");
		return list;
	}
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
	public Employee updateDataById(Integer id,EmployeeDTO employeeDTO) {
		Employee newEmployee = new Employee(id,employeeDTO);
		repo.save(newEmployee);
		log.info("Record for given id "+id+" got updated");
		return newEmployee;
	}
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