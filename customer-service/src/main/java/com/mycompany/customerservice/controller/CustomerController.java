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

import static com.mycompany.customerservice.constants.CustomerServiceConstants.ROOT_PATH;

@RestController
@RequestMapping(value = ROOT_PATH)
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        LOGGER.info("customer details are {}", customerRequest);
        CustomerResponse customerResponse = customerService.saveCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomer() {
        LOGGER.info("fetching customers from a database");
        List<CustomerResponse> customerResponse = customerService.getCustomers();
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomers(@PathVariable("id") String customerId) {
        LOGGER.info("fetching customer with an id {}", customerId);
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }


}