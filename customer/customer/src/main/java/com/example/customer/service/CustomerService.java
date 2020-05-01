package com.example.customer.service;

import java.util.List;

import com.example.customer.model.Address;
import com.example.customer.model.Customer;

public interface CustomerService {
	Customer updateCustomerAddress(String customerID, Address inputAddress);
	Customer addCustomer(Customer inputCustomer);
	List<Customer> findAll();
	

}
