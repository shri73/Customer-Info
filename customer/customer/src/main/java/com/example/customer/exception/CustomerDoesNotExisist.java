package com.example.customer.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerDoesNotExisist extends RuntimeException {

	public CustomerDoesNotExisist (String message) {
		super(message);
	}
	
}
