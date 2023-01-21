package com.example.springbootapp.controller;

import java.util.HashMap; 
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") //http://localhost:8080/api
public class EmployeeRestController {
	@GetMapping(path="/greet")
	public ResponseEntity<Object> greetings(){
		ResponseEntity<Object> response=null;
		Map<String,String> map = new HashMap<String,String>();
		map.put("title", "Spring Boot");
		map.put("message","Demo on SpringBoot");
		response=ResponseEntity.status(200).body(map);
		return response;
	}
}
