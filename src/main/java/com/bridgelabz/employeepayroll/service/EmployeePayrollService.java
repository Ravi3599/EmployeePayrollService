package com.bridgelabz.employeepayroll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.exception.EmployeePayrollException;
import com.bridgelabz.employeepayroll.model.Employee;
import com.bridgelabz.employeepayroll.repository.EmployeePayrollRepository;
import com.bridgelabz.employeepayroll.util.EmailSenderService;
import com.bridgelabz.employeepayroll.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService{
	@Autowired
	private EmployeePayrollRepository repo;
	
	@Autowired
	TokenUtil tokenutil;
	@Autowired
	private EmailSenderService sender;

	//Ability to serve controllers api call to return welcome message
	public String getWelcome() {
		return "Welcome to Employee Payroll !!!";	
	}
	//Ability to serve controllers api call save data to repo
	public String postDataToRepo(EmployeeDTO employeeDTO) {
		Employee newEmployee = new Employee(employeeDTO);
		repo.save(newEmployee);
		log.info("Record got saved");
		String token=tokenutil.createToken(newEmployee.getId());
		//emaillistner.sendMail();
		//sender.sendEmail("ravirenapurkar@gmail.com", "Test Email", "http://localhost:8080/employeepayrollservice/"+token);
		return token;
	}
	//Retrive all records of Employee Payroll data by token
	@Override
	public List<Employee> getEmployeePayrollData(String token) 
	{
		int id=tokenutil.decodeToken(token);
		Optional<Employee> newEmployee=repo.findById(id);
		if(newEmployee.isPresent()) {
			List<Employee> listEmployee=repo.findAll();
		return listEmployee;		
		}else {
			System.out.println("Exception ...Token not found!");	
			return null;	}	
	}
	//Ability to serve controllers api call to retrieve all data
	public List<Employee> getAllData(){
		List<Employee> list=repo.findAll();
		log.info("All records got retrived");
		return list;
	}
	//Ability to serve controllers api call to get  a record by id
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
	//Ability to serve controllers api call to get  a record by token
	public Employee getDataByToken(String token) {
		Integer id = tokenutil.decodeToken(token);
		Optional<Employee> newEmp = repo.findById(id);
		if(newEmp.isEmpty()) {
			log.warn("Unable to find employee for given id: "+id);
			throw new EmployeePayrollException("Employee Not Found");
		}
		return newEmp.get();
	}
	//Ability to serve controllers api call to update a record by id
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
	//Ability to serve controllers api call to update a record by token
	public Employee updateDataByToken(String token,EmployeeDTO employeeDTO) {
		Integer id = tokenutil.decodeToken(token);
		Optional<Employee> employee = repo.findById(id);
		if(employee.isEmpty()) {
			throw new EmployeePayrollException("Employee Not Found");
		}
		Employee newEmployee = new Employee(id,employeeDTO);
		repo.save(newEmployee);
		log.info("Record for given id "+id+" got updated");
		return newEmployee;
	}
	//Ability to serve controllers api call to delete a record by id
	public Employee deleteDataById(Integer id) {
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
		return newEmp.get();
	}
	//Ability to serve controllers api call to delete a record by token
	public Employee deleteDataByToken(String token) {
		Integer id = tokenutil.decodeToken(token);
		Optional<Employee> newEmp = repo.findById(id);
		if(newEmp.isEmpty()) {
			log.warn("Unable to find employee for given id:"+id);
			throw new EmployeePayrollException("Employee Not Found");
		}
		else {
			repo.deleteById(id);
		}
		return newEmp.get();
	}
	//Ability to serve controllers api call to get a record by city
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