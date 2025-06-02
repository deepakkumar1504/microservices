package com.mycompany.customerservice.service;

import com.mycompany.customerservice.dao.Customer;
import com.mycompany.customerservice.dto.CustomerRequest;
import com.mycompany.customerservice.dto.CustomerResponse;
import com.mycompany.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        // Convert request DTO to entity
        Customer customer = convertToEntity(customerRequest);
        // Save the entity
        Customer savedCustomer = customerRepository.save(customer);
        // Convert saved entity to response DTO
        return convertToResponse(savedCustomer);
    }

    private Customer convertToEntity(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        return customer;
    }

    private CustomerResponse convertToResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setAddress(customer.getAddress());
        return response;
    }

}
