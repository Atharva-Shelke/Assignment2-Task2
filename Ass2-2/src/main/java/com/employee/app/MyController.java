package com.employee.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@Autowired
	EmployeeRepo employeeRepo;

	@GetMapping("getEmployees")
	public List<Employee> getEmployees() {
		List<Employee> employees = employeeRepo.findAll();
		return employees;
	}

	@RequestMapping("saveEmployees")
	public Employee saveEmployee(@RequestBody Employee employee) {
		try {
			return employeeRepo.save(employee);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping("getEmployee/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		try {
			Employee employee = employeeRepo.findById(id).get();
			return employee;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping("updateEmployee/{id}/{salary}/{role}")
	public Employee updateEmployee(@PathVariable int id, @PathVariable int salary,@PathVariable String role) {
		try {
			Employee employee = employeeRepo.findById(id).get();
			employee.salary = salary;
			employee.role=role;
			employeeRepo.save(employee);
			return employee;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}

	@RequestMapping("delete/{id}")
	public String deleteEmployee(@PathVariable int id) {
		try {
			employeeRepo.deleteById(id);
			return "Employee Deleted";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Employee not deleted";
		}

	}

}
