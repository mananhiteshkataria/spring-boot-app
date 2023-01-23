package com.example.springbootapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootapp.beans.Employee;
import com.example.springbootapp.exceptions.EmployeeNotFoundException;
import com.example.springbootapp.service.EmployeeService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api") //http://localhost:8080/api
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping (path = "/save",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> store(@RequestBody Employee employee)
	{
		Employee savedEntity=service.store(employee);
		return ResponseEntity.status(200).body(savedEntity);
		
	}
	@GetMapping(path = "/findAll")
	public ResponseEntity<Object> find(){
		List<Employee> employees = service.getEmployees();
		return ResponseEntity.status(200).body(employees);
				}
	@GetMapping(path="/{id}")
	public ResponseEntity<Object> find(@PathVariable("id")int id)
	{
		Employee employee;
		try {
			employee = service.getEmployee(id);
			return ResponseEntity.status(200).body(employee);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			 Map<String,String> map= new HashMap<String,String>();
			 map.put("message", e.getMessage());
			 return ResponseEntity.status(404).body(map);
		}
		
	}
	
	@PutMapping(path="/update/{id}/{salary}")
	public ResponseEntity<Object> update(@PathVariable("id") 
	int id,@PathVariable("salary")double salary)
	{
		try {
			Employee employee = service.updEmployee(id, salary);
			return ResponseEntity.status(200).body(employee);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			 Map<String,String> map= new HashMap<String,String>();
			 map.put("message", e.getMessage());
			 return ResponseEntity.status(404).body(map);
		}
	}
	
	@DeleteMapping(path="/delete/{id}")
	public ResponseEntity<Object>delete(@PathVariable("id")int id)
	{
		try {
				
			
			  service.delEmployee(id);
			  HashMap<String, String> map= new HashMap<String,String>();
			  map.put("message", "id "+id+"  is deleted");
			return ResponseEntity.status(200).body(map);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			 Map<String,String> map= new HashMap<String,String>();
			 map.put("message", e.getMessage());
			 return ResponseEntity.status(404).body(map);
		}
	}
	
	
//	public ResponseEntity<Object> greetings(){
//		ResponseEntity<Object> response=null;
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("title", "Spring Boot");
//		map.put("message","Demo on SpringBoot");
//		response=ResponseEntity.status(200).body(map);
//		return response;
	}
