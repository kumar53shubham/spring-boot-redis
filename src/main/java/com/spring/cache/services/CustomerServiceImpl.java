package com.spring.cache.services;

import com.spring.cache.hash.Customer;
import com.spring.cache.repo.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao customerDao;


    @Override
    public Customer saveCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    @Override
    public Customer updateCustomer(int id, Customer customer) {
        return customerDao.updateCustomer(id, customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomer(int id) {
        return customerDao.getCustomer(id);
    }

    @Override
    public String deleteCustomer(int id) {
        return customerDao.deleteCustomer(id);
    }
}
