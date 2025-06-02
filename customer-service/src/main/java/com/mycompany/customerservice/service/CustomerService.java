package com.mycompany.customerservice.service;

import com.mycompany.customerservice.dto.CustomerRequest;
import com.mycompany.customerservice.dto.CustomerResponse;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerResponse saveCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getAllCustomers();
    Optional<CustomerResponse> getCustomerById(String customerId);


}
