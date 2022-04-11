package com.sg.springscheduler.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.springscheduler.service.CustomerService;
import openapi.model.CreateCustomerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    CustomerService customerService;

//    @KafkaListener(topics = "mytopic", groupId = "my group")
//    public void consumeFromTopic(String message){
//        System.out.println("Consumed Message" +message);
//    }

    @KafkaListener(topics = "mytopic", groupId = "second group")
    public void consumeFromTopic2(String message){
        try {
            CreateCustomerRequestDto customer = new ObjectMapper().readValue(message,CreateCustomerRequestDto.class);
            System.out.println(customer);
            customerService.postCustomers(customer);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
