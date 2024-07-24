package com.example.customer_service.mapper;

import com.example.customer_service.dto.CustomerRequestDTO;
import com.example.customer_service.dto.CustomerResponseDTO;
import com.example.customer_service.model.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends BaseMapper<CustomerRequestDTO, CustomerEntity> {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    CustomerResponseDTO customerEntityToCustomerResponseDTO(CustomerEntity customerEntity);
}