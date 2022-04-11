package com.sg.springscheduler.mapper;

import com.sg.springscheduler.entity.Customer;
import openapi.model.CreateCustomerRequestDto;
import openapi.model.CreateCustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "Spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    Customer mapToEntity (CreateCustomerRequestDto createCustomerRequestDto);
    CreateCustomerResponseDto mapEntityToResponseDto(Customer savedCustomer);
}
