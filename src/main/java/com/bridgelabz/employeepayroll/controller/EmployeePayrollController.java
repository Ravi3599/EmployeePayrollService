package com.bridgelabz.employeepayroll.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.dto.ResponseDTO;
import com.bridgelabz.employeepayroll.exception.EmployeePayrollException;
import com.bridgelabz.employeepayroll.model.Employee;
import com.bridgelabz.employeepayroll.service.IEmployeePayrollService;

//Created Controller class to call api
@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {
	//Autowired IEmployeePayrollService to inject its dependency here
	@Autowired
	private IEmployeePayrollService service;
	
	//Ability to display welcome message
	@GetMapping("")
	public ResponseEntity<String> getWelcome(){
		String welcome = service.getWelcome();
		return new ResponseEntity<String>(welcome,HttpStatus.OK);
	}
	//Ability to save employee data to repo
	@PostMapping("/create")
	public ResponseEntity<String> addDataToRepo(@Valid @RequestBody EmployeeDTO employeeDTO){
		String entity = service.postDataToRepo(employeeDTO);
		ResponseDTO dto = new ResponseDTO("Record Added Succesfully",entity);
		return new ResponseEntity(dto,HttpStatus.CREATED);
	}
	//Ability to get all employees' data by findAll() method
	@GetMapping("/get")
	public ResponseEntity<String>getAllDataFromRepo(){
		List<Employee> listOfEmployee = service.getAllData();
		ResponseDTO dto = new ResponseDTO("Record Retrieved Successfully", listOfEmployee);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to get employee data by id
	@GetMapping("/get/{id}")
	public ResponseEntity<String> getDataFromRepoById(@PathVariable Integer id) throws EmployeePayrollException{
		Employee newEmployee= service.getDataById(id);
		ResponseDTO dto = new ResponseDTO("Record for given ID Retrieved Successfully", newEmployee);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
	//Ability to update employee data for particular id
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateDataInRepo(@PathVariable Integer id,@Valid @RequestBody EmployeeDTO employeeDTO){
		Employee updatedEmployee = service.updateDataById(id, employeeDTO);
		ResponseDTO dto = new ResponseDTO("Record for particular ID Updated Successfully",updatedEmployee);
		return new ResponseEntity(dto,HttpStatus.ACCEPTED);
	}
	//Ability to delete employee data for particular id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDataInRepo(@PathVariable Integer id){
		ResponseDTO dto = new ResponseDTO("Record for particular ID Deleted Successfully", service.deleteDataById(id));
		return new ResponseEntity(dto,HttpStatus.ACCEPTED);
	}
	//Ability to get employee data by department name
	@GetMapping("/getbydepartment/{department}")
	public ResponseEntity<ResponseDTO> getRecordFromRepoByDepartment(@PathVariable String department)throws EmployeePayrollException{
		List<Employee> newEmployee= service.getDataByDepartment(department);
		ResponseDTO dto = new ResponseDTO("Record for given Department Retrieved Successfully", newEmployee);
		return new ResponseEntity(dto,HttpStatus.OK);
	}
}