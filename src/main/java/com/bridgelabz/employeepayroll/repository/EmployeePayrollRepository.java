package com.bridgelabz.employeepayroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.employeepayroll.model.Employee;

public interface EmployeePayrollRepository extends JpaRepository<Employee, Integer> {

	@Query(value="select * from employee where department= :department",nativeQuery=true)
	public List<Employee> findByDepartment(String department);

}