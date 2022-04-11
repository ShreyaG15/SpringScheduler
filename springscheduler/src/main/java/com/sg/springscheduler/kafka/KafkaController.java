package com.sg.springscheduler.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import openapi.api.ClientApiApi;
import openapi.model.CreateCustomerRequestDto;
import openapi.model.CreateCustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafkaApp")
public class KafkaController implements ClientApiApi {

    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping(value = "/post")
    public void sendMessage(@RequestParam ("msg") String msg){
    kafkaProducer.publishToTopic(msg);
    }

    @Override
    public ResponseEntity<CreateCustomerResponseDto> postCustomers(CreateCustomerRequestDto createCustomerRequestDto) {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(createCustomerRequestDto);
            kafkaProducer.publishToTopic(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
}
