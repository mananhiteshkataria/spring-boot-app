package com.example.springbootapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootapp.beans.Employee;
import com.example.springbootapp.dao.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository dao;
	// defined methods that calls save(),findById(),findAll()
	// @Transactional is required on the method that modifies entity
	
	public Employee getEmployee(int id)
	{
		Optional<Employee>optional=dao.findById(id);
		return optional.orElse(null); // returns object or null if id exists
	}
	
	public List<Employee> getEmployees(){
		return dao.findAll();// returns all employees
	}
	
	@Transactional
	
	public Employee store(Employee emp)
	{
		return dao.save(emp); // saves and returns the entity
	}
	
	
	
	
}
