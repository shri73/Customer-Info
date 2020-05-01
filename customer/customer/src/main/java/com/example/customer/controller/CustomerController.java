package com.example.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@ApiResponses(value = {
        @ApiResponse(code=400, message = "This is a bad request, please follow the API documentation for the proper request format."),
        @ApiResponse(code=401, message = "Due to security constraints, your access request cannot be authorized. "),
        @ApiResponse(code=500, message = "The server is down. Please make sure that the Customer microservice is running.")
})
@RequestMapping(value = "/customer")
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService service;
	
	 @ApiOperation(value = "View a list of all customers", response = List.class)
	 @GetMapping("/allCustomers")
	    public ResponseEntity<List<Customer>> getAllCustomers() {
	        List<Customer> list = service.findAll();
	        return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	    }
	 
	 @ApiOperation(value = "Add an customer")
	 @PostMapping("/add")
	 public ResponseEntity <Customer> createCustomer(
	     @ApiParam(value = "Customer object store in database table", required = true) @Valid @RequestBody Customer inputCustomer) {
	     service.addCustomer(inputCustomer);
		 return new ResponseEntity<Customer>(HttpStatus.CREATED);
	 }
	 
	 @ApiOperation(value = "Update an customer address")
	    @PutMapping("/update/address/{id}")
	    public ResponseEntity <Customer> updateCustomer(
	        @ApiParam(value = "Customer Id to update address object", required = true) @PathVariable(value = "id") String customerId,
	        @ApiParam(value = "Update employee object", required = true) @Valid @RequestBody Address addressDetails) {
		 	Customer updatedCustomer = service.updateCustomerAddress(customerId, addressDetails);
	        return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
	    }

}
