package com.sg.springscheduler.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.springscheduler.repo.CustomerRepository;
import com.sg.springscheduler.service.CustomerService;
import openapi.model.CreateCustomerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    @Autowired
    CustomerService customerService;


    @Autowired
    CustomerRepository customerRepository;

    @JmsListener(destination = "standalone.queue")
    public void consumeMessage(String message) {

        try {
            CreateCustomerRequestDto customer = new ObjectMapper().readValue(message, CreateCustomerRequestDto.class);
            System.out.println(customer);
            customerService.postCustomers(customer);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

//    private Customer mapToEntity(CreateCustomerRequestDto customer) {
//        return Customer.builder()
//                .email(customer.getEmail())
//                .firstName(customer.getFirstName())
//                .lastName(customer.getLastName())
//                .userName(customer.getUserName())
//                .phoneNumber(customer.getPhoneNumber())
//                .preferredLanguage(Language.valueOf(customer.getPreferredLanguage().getValue()))
//                .build();
//    }

//    public void dataEntry(Customer data) {
//        customerRepository.save(data);
//    }
}
