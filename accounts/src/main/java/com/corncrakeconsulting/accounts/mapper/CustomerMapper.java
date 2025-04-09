package com.corncrakeconsulting.accounts.mapper;

import com.corncrakeconsulting.accounts.dto.CustomerDto;
import com.corncrakeconsulting.accounts.entity.Customer;

public class CustomerMapper {

    private CustomerMapper() {
        // Private constructor to prevent instantiation
    }
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}