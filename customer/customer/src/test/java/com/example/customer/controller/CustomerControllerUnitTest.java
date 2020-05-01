package com.example.customer.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.customer.CustomerApplication;
import com.example.customer.model.Address;
import com.example.customer.service.CustomerService;
import com.google.gson.Gson;

@ContextConfiguration(classes=CustomerApplication.class)
@AutoConfigureMockMvc
@WebMvcTest(CustomerController.class)
public class CustomerControllerUnitTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	CustomerService service;
	
	
	Address address = new Address("123 ST", "Ottawa", "ON", "K2P2S2");
	Gson gson = new Gson();
    String json = gson.toJson(address);

	
	@Test
	public void getAllCustomers() throws Exception {
		
			mockMvc.perform(get("/customer/allCustomers/"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json("[]"));
		

		verify(service, times(1)).findAll();

	}
	
	@Test
	public void updateCustomer() throws Exception {
		mockMvc.perform( put("/customer/update/address/{id}", "123")
			      .contentType(MediaType.APPLICATION_JSON)
                  .content((json)))
			      .andExpect(status().isOk());
		
		verify(service, times(1)).updateCustomerAddress("123", address);   
			
	}

	}


	

