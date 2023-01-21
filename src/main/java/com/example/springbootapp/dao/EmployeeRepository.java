package com.example.springbootapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootapp.beans.Employee;

public interface EmployeeRepository 
extends JpaRepository<Employee, Integer>{
/*
 *  you can write methods depeneding on your needs 
 *	or a query that is not already present that needs to be executed
 *	SpringBoot automatically implements methods like save()
 * 	deleteById(),findById(),findAll()
 * 	springboot also creates objects of this interface and 
 * 	maintains in spring container , we only need to supply this 
 * 	object in service laye
*/
}
