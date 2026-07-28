package com.employee.exception;

public class EmptyException extends RuntimeException{

	
	String msg;
	public EmptyException(String msg){
		super(String.format("%s",msg));
		this.msg=msg;
	}

}
