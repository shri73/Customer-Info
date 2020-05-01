package com.example.emailservice.entity.dto;

import javax.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private String customerId;
	
	
	private String firstName;

	private String lastName;
	
	
	private String email;
	
	@Embedded
    private AddressDTO address;
	

	private String phoneNumber;

}
