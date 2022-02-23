package com.bridgelabz.employeepayroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.employeepayroll.model.Employee;

//Created EmployeePayrollRepository class extending JpaRepositoy so we can apply CRUD operations as well as use some custom query methods
public interface EmployeePayrollRepository extends JpaRepository<Employee, Integer> {

	@Query(value="select * from employee ,employee_department where employee.id=employee_department.id and department= :department",nativeQuery=true)
	public List<Employee> findByDepartment(String department);

}