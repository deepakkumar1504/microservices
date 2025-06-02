package com.mycompany.customerservice.service;

import com.mycompany.customerservice.controller.CustomerController;
import com.mycompany.customerservice.dao.Customer;
import com.mycompany.customerservice.dto.CustomerRequest;
import com.mycompany.customerservice.dto.CustomerResponse;
import com.mycompany.customerservice.exception.CustomerNotFoundException;
import com.mycompany.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    private Customer mapToCustomer(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();
    }

    private CustomerResponse mapToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }


    @Override
    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Customer customer = mapToCustomer(customerRequest);
        //todo : check if entered email is valid email address,unique email
        Customer savedCustomer = customerRepository.save(customer);
        LOGGER.info("Customer has saved with id {} ", savedCustomer.getId());
        return mapToCustomerResponse(savedCustomer);
    }

    @Override
    public List<CustomerResponse> getCustomers() {
        LOGGER.info("fetching customers from database");
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::mapToCustomerResponse).toList();
    }

    @Override
    public CustomerResponse getCustomerById(String customerId) {
        Optional<Customer> dbWrapper = customerRepository.findById(customerId);
        if (dbWrapper.isPresent()) {
            return mapToCustomerResponse(dbWrapper.get());
        } else {
            LOGGER.info("Customer with ID {} not present in database", customerId);
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }
    }

}
