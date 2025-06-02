package com.mycompany.customerservice.controller;

import com.mycompany.customerservice.dto.CustomerRequest;
import com.mycompany.customerservice.dto.CustomerResponse;
import com.mycompany.customerservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.mycompany.customerservice.constants.CustomerServiceConstants.ROOT_PATH;

@RestController
@RequestMapping(value = ROOT_PATH)
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        LOGGER.info("Received customer details: {}", customerRequest);
        CustomerResponse customerResponse = customerService.saveCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomer() {
        LOGGER.info("Received request to fetch all customers from the database");
        List<CustomerResponse> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(customers); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") String customerId) {
        LOGGER.info("Received request to fetch customer with ID: {}", customerId);
        return customerService.getCustomerById(customerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }

}