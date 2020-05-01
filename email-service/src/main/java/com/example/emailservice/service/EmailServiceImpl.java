package com.example.emailservice.service;

import com.example.emailservice.entity.Mail;
import com.example.emailservice.entity.dto.CustomerDTO;
import com.example.emailservice.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * We transform the incoming payload, send the email and save it for future reference.
 */

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public MailRepository mailRepository;

    @Override
    public void sendSimpleMessage(CustomerDTO input) {
        try {

            Mail newMail = new Mail();
            newMail.setTo(input.getEmail());
            newMail.setSubject("You Requested Address Change");
            newMail.setText("You have updated your Address to" + input.getAddress().toString() + ".");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(newMail.getTo());
            message.setSubject(newMail.getSubject());
            message.setText(newMail.getText());

            mailRepository.save(newMail);
            emailSender.send(message);

        } catch (MailException exception) {
            exception.printStackTrace();
        }

    }

}
