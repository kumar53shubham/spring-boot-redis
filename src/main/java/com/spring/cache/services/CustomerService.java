package com.spring.cache.services;

import com.spring.cache.hash.Customer;

import java.util.List;

public interface CustomerService {

    public Customer saveCustomer(Customer customer);
    public Customer updateCustomer(int id,Customer customer);
    public List<Customer> getAllCustomers();
    public Customer getCustomer(int id);
    public String deleteCustomer(int id);
}
