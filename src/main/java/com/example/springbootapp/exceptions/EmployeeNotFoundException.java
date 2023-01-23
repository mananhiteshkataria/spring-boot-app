package com.example.springbootapp.exceptions;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception{
	public EmployeeNotFoundException(String error) {
		super(error);// in the catch block you can use getMessage()
			// to get the value of error
	}
}
