package com.example.customer.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	@Column(name = "id")
	private String customerId;
	
	@Column(name = "firstName", nullable = false)
	@NotBlank(message = "First Name is mandatory")
	private String firstName;
	
	@Column(name = "lastName", nullable = false)
	@NotBlank(message = "Last Name is mandatory")
	private String lastName;
	
	@Column(name = "email", nullable = false)
	@Email(message = "Email is incorrect")
	private String email;
	
	@Embedded
    private Address address;
	
	@Column(name = "phoneNumber", nullable = false)
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phoneNumber;

}
