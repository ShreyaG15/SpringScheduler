package com.sg.springscheduler.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.springscheduler.jms.JmsConsumer;
import com.sg.springscheduler.service.CustomerService;
import openapi.api.ClientApiApi;
import openapi.model.CreateCustomerRequestDto;
import openapi.model.CreateCustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
@RequestMapping("rest/publish")
public class ProducerController implements ClientApiApi {

    @Autowired
    CustomerService customerService;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @Autowired
    JmsConsumer schedulerConsumer;


    @GetMapping("/{message}")
    public String publish(@PathVariable("message") final String message) {

        jmsTemplate.convertAndSend(queue, message);
        return "Published Successfully";
    }

    @Override
    public ResponseEntity<CreateCustomerResponseDto> postCustomers(CreateCustomerRequestDto createCustomerRequestDto) {

        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(createCustomerRequestDto);
            jmsTemplate.convertAndSend(queue, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
}
