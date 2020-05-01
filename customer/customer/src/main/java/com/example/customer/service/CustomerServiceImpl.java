package com.example.customer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.customer.exception.CustomerDoesNotExisist;
import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.producer.Sender;
import com.example.customer.repository.CustomerRepository;
import com.google.common.collect.Lists;
@Component
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private Sender sender;
	
	@Value("${spring.kafka.topic.customerUpdated}")
    private String CUSTOMER_UPDATED_TOPIC;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public Customer updateCustomerAddress(String customerID, Address inputAddress) {
		Optional<Customer> existingCustomer = repository.findById(customerID);
		Customer retrievedCustomer = null;
		if(existingCustomer.isPresent()) {
		    retrievedCustomer = existingCustomer.get();
			retrievedCustomer.setAddress(inputAddress);
			repository.save(retrievedCustomer);
			//LOGGER.info("savedd");
			sender.send(CUSTOMER_UPDATED_TOPIC, retrievedCustomer);
		}
		else {
			throw new CustomerDoesNotExisist("Customer does not exist with customer ID " + customerID + " .");
		}
		return retrievedCustomer;
		
	}

	@Override
	public Customer addCustomer(Customer inputCustomer) {
		repository.save(inputCustomer);
		return inputCustomer;
	}

	@Override
	public List<Customer> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

}
