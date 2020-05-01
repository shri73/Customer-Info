package com.example.emailservice.service;

import com.example.emailservice.entity.dto.CustomerDTO;

public interface EmailService {

    void sendSimpleMessage(CustomerDTO input);

}
