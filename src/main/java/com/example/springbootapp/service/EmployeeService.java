package com.example.springbootapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootapp.beans.Employee;
import com.example.springbootapp.dao.EmployeeRepository;
import com.example.springbootapp.exceptions.EmployeeNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository dao;
	// defined methods that calls save(),findById(),findAll()
	// @Transactional is required on the method that modifies entity
	
	public Employee getEmployee(int id) throws EmployeeNotFoundException
	{
		Optional<Employee>optional=dao.findById(id);
		// if you are not using exception
		//return optional.orElse(null); // returns object or null if id exists
		Employee employee =optional.orElse(null);
		if(employee==null)
		{
			throw new EmployeeNotFoundException("Id " +id+ " not found!");
		}
		else {
			{
				return employee;
			}
		}
	}
	
	public List<Employee> getEmployees(){
		return dao.findAll();// returns all employees
	}
	
	@Transactional
	
	public Employee store(Employee emp)
	{
		return dao.save(emp); // saves and returns the entity
	}
	//delete by id 
	//update salalry by id
	
	@Transactional
	public Employee updEmployee(int id,double salary)
			throws EmployeeNotFoundException 
	{
		Employee employee = getEmployee(id);
		employee.setSalary(salary);
		return employee;
	}
	
	@Transactional
	public void delEmployee(int id) throws EmployeeNotFoundException
	{
		Employee employee= getEmployee(id);
		// if not using exception
		//dao.deleteById(id);
		dao.delete(employee);
	}
	
	
	
	
}
