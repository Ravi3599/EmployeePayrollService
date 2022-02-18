package com.bridgelabz.employeepayroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
