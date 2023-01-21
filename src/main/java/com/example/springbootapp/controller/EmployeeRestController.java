package com.example.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootapp.beans.Employee;
import com.example.springbootapp.service.EmployeeService;

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
//	public ResponseEntity<Object> greetings(){
//		ResponseEntity<Object> response=null;
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("title", "Spring Boot");
//		map.put("message","Demo on SpringBoot");
//		response=ResponseEntity.status(200).body(map);
//		return response;
	}
