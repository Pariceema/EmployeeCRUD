package com.employee.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employee.response.APIresponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmptyException.class)
	
	public ResponseEntity<APIresponse> emptyException(EmptyException emptyexception){
		String msg=emptyexception.getMessage();
		APIresponse ar=new APIresponse(msg,false);
		
		return new ResponseEntity<APIresponse>(ar,HttpStatus.NO_CONTENT);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	
	public ResponseEntity<Map<String,String>> methodArgumentnotvalidException(MethodArgumentNotValidException methodargumentnotvalidexception){
		Map<String, String> map=new HashMap<String, String>();
		methodargumentnotvalidexception.getBindingResult().getAllErrors().forEach((error)->{
			String field=((FieldError)error).getField();
			String msg=error.getDefaultMessage();
			
			map.put(field, msg);
			
			
		});	
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
		}
}
