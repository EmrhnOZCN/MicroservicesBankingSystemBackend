package com.example.customer_service.service;


import com.example.customer_service.dto.CustomerRequestDTO;
import com.example.customer_service.dto.CustomerResponseDTO;
import com.example.customer_service.dto.CustomerVO;
import com.example.customer_service.mapper.CustomerMapper;
import com.example.customer_service.model.CustomerEntity;
import com.example.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public CustomerRequestDTO createCustomer(CustomerRequestDTO requestDTO) {

        try {

            CustomerEntity customerEntity = CustomerMapper.INSTANCE.dtoToEntity(requestDTO);
            customerEntity = customerRepository.save(customerEntity);

            CustomerRequestDTO customerRequestDTO = customerMapper.entityToDto(customerEntity);

            return customerRequestDTO;

        }
        catch (Exception e){

            throw new RuntimeException("Failed to create customer: " + e.getMessage(), e);

        }

    }

    @Override
    public CustomerRequestDTO getCustomerById(UUID id) {

        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow();

        if (customerEntity == null){

            return null;
        }

        return customerMapper.entityToDto(customerEntity);
    }

    @Override
    public Page<CustomerResponseDTO> getAllCustomers(Pageable pageable) {
        try {
            Page<CustomerEntity> entities = customerRepository.findAll(pageable);
            return entities.map(customerMapper::customerEntityToCustomerResponseDTO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all customers: " + e.getMessage(), e);
        }
    }

    @Override
    public CustomerResponseDTO updateCustomer(UUID id, CustomerRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void deleteCustomer(UUID id) {

    }
    @Override
    public CustomerVO authenticate(String identityNumber, String password) {
        CustomerEntity customer = customerRepository.findByIdentityNumber(identityNumber);

        if (customer != null && customer.getPassword().equals(password)) {
            return new CustomerVO(
                    customer.getId().toString(),
                    customer.getIdentityNumber(),
                    customer.getPassword(),
                    "USER"
            );
        } else {
            return null; // Doğrulama başarısızsa null döndür
        }
    }



}