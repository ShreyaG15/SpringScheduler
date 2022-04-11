package com.sg.springscheduler.service;
import com.sg.springscheduler.entity.Customer;
import com.sg.springscheduler.enumerator.Language;
import com.sg.springscheduler.enumerator.Status;
import com.sg.springscheduler.mapper.CustomerMapperImpl;
import com.sg.springscheduler.repository.CustomerRepo;
import openapi.model.CreateCustomerRequestDto;
import openapi.model.CreateCustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class CustomerService {

    CustomerMapperImpl customerMapperImpl;
    CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerMapperImpl customerMapperImpl, CustomerRepo customerRepo) {
        this.customerMapperImpl = customerMapperImpl;
        this.customerRepo = customerRepo;
    }

    public ResponseEntity<CreateCustomerResponseDto> postCustomers(CreateCustomerRequestDto createCustomerRequestDto) {

        Customer customer = getCustomer(createCustomerRequestDto);

        Customer savedCustomer = customerRepo.save(customer);

        CreateCustomerResponseDto createCustomerResponseDto = customerMapperImpl.mapEntityToResponseDto(savedCustomer);

        return new ResponseEntity<>(createCustomerResponseDto, HttpStatus.CREATED);
    }

    private Customer getCustomer(CreateCustomerRequestDto createCustomerRequestDto) {
        Customer customer = customerMapperImpl.mapToEntity(createCustomerRequestDto);
        if (customer == null) {
            throw new NullPointerException("Customer is null");
        }
        customer.setStatus(Status.ACTIVE);
        customer.setPreferredLanguage(Language.EN);
        customer.setCreatedBy("System");
        customer.setUpdatedBy("System");
        customer.setExternalId(customer.getUserName().concat("_ext"));
        return customer;
    }
}
