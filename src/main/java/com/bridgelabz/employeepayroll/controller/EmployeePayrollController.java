package com.bridgelabz.employeepayroll.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.employeepayroll.model.Employee;
import com.bridgelabz.employeepayroll.service.IEmployeePayrollService;

@RestController
public class EmployeePayrollController {
	@Autowired
	IEmployeePayrollService service;
	
	@GetMapping("/getMessage")
	public ResponseEntity<String> getMessage(@RequestParam String name){
		return new ResponseEntity<String>(service.getMessage(name),HttpStatus.OK);
	}
	@PostMapping("/postMessage")
	public ResponseEntity<String> postMessage(@RequestBody Employee employee){
		return new ResponseEntity<String>(service.postMessage(employee),HttpStatus.OK);
	}
	@PutMapping("/putMessage/{name}")
	public ResponseEntity<String> putMessage(@PathVariable String name){
		return new ResponseEntity<String>(service.putMessage(name),HttpStatus.OK);
	}
	@GetMapping("/employeepayrollservice")
	public ResponseEntity<String> getWelcome(){
		return new ResponseEntity<String>(service.getWelcome(),HttpStatus.OK);
	}
	@PostMapping("/employeepayrollservice/create")
	public ResponseEntity<Employee> saveDataToRepo(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(service.postDataToRepo(employee),HttpStatus.OK);
	}
	@GetMapping("/employeepayrollservice/get")
	public ResponseEntity<List<Employee>> getAllDataFromRepo(){
		return new ResponseEntity<List<Employee>>(service.getAllData(),HttpStatus.OK);
	}
	@GetMapping("/employeepayrollservice/get/{id}")
	public ResponseEntity<Optional<Employee>> getDataFromRepoById(@PathVariable Integer id){
		return new ResponseEntity<Optional<Employee>>(service.getDataById(id),HttpStatus.OK);
	}
	@PutMapping("/employeepayrollservice/update/{id}")
	public ResponseEntity<Employee> updateDataInRepo(@PathVariable Integer id,@RequestBody Employee employee){
		return new ResponseEntity<Employee>(service.updateDataById(id,employee),HttpStatus.OK);
	}
	@DeleteMapping("/employeepayrollservice/delete/{id}")
	public ResponseEntity<String> deleteDataInRepo(@PathVariable Integer id){
		return new ResponseEntity<String>(service.deleteDataById(id),HttpStatus.OK);
	}
}
