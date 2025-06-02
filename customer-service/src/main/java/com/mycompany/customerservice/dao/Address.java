package com.mycompany.customerservice.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {

    private String street;
    private String pinCode;
    private String state;
    private String country;

}
