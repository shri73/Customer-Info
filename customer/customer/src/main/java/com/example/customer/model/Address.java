package com.example.customer.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
	@Column(name = "streetAddress", nullable = false)
	@NotNull(message = "Street Address is mandatory")
    private String streetAddress;
     
    @Column(name = "city", nullable = false)
    @NotNull(message = "City is mandatory")
    private String city;
     
    @Column(name = "state", nullable = false)
    @NotNull(message = "State is mandatory")
    private String state;
     
    @Column(name = "zipCode", nullable = false)
    private String zipCode;

}
