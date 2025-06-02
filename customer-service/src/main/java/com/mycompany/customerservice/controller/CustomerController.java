package com.mycompany.customerservice.controller;

import com.mycompany.customerservice.dto.CustomerRequest;
import com.mycompany.customerservice.dto.CustomerResponse;
import com.mycompany.customerservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.mycompany.customerservice.constants.CustomerServiceConstants.ROOT_PATH;

@RestController
@RequestMapping(value = ROOT_PATH)
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        LOGGER.info("into createCustomer method");
        LOGGER.error("error message");
        CustomerResponse customerResponse = customerService.saveCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

}