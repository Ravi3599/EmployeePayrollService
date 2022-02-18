package com.bridgelabz.employeepayroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.employeepayroll.model.Employee;

public interface EmployeePayrollRepository extends JpaRepository<Employee, Integer> {

}
