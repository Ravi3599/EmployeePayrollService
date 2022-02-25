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

	public String getWelcome() {
		return "Welcome to Employee Payroll !!!";	
	}
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
	public List<Employee> getEmployeePayRollData(String token) 
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
		Optional<Employee> employee = repo.findById(id);
		if(employee.isEmpty()) {
			throw new EmployeePayrollException("Employee Not Found");
		}
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