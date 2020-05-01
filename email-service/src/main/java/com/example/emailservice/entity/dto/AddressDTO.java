package com.example.emailservice.entity.dto;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AddressDTO {
    private String streetAddress;
   
    private String city;
     
    
    private String state;
     
    
    private String zipCode;

}
