package com.employee.exception;

public class ResourceNotFoundException extends RuntimeException{
	String field;
	String type;
	Long value;
	
	public ResourceNotFoundException(String field,String type,Long value){
		super(String.format("%s Not found with %s=%d",field,type,value));
		this.field=field;
		this.type=type;
		this.value=value;
	}
	
	
}
