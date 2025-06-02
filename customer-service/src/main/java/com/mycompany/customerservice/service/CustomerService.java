package com.mycompany.customerservice.service;

import com.mycompany.customerservice.dao.Customer;
import com.mycompany.customerservice.dto.CustomerRequest;
import com.mycompany.customerservice.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse saveCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getCustomers();
    CustomerResponse getCustomerById(String customerId);

}
