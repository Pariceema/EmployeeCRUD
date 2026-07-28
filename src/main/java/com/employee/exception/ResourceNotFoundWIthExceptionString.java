package com.employee.exception;

public class ResourceNotFoundWIthExceptionString extends RuntimeException{

	String field;
	String type;
	String value;
	
	public ResourceNotFoundWIthExceptionString(String field,String type,String value) {
		this.field=field;
		this.type=type;
		this.value=value;
		
	}
	
	
}
