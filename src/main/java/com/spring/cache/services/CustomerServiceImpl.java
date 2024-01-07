package com.spring.cache.services;

import com.spring.cache.hash.Customer;
import com.spring.cache.repo.CustomerDao;
import com.spring.cache.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
//        return customerDao.addCustomer(customer);
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(int id, Customer customer) {
//        return customerDao.updateCustomer(id, customer);
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!!"));
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setDob(customer.getDob());
        existingCustomer.setPhone(customer.getPhone());
        return existingCustomer;
    }

    @Override
    public List<Customer> getAllCustomers() {
//        return customerDao.getAllCustomers();

//        convert iterable to collection java 8 feature
        List<Customer> list = StreamSupport.stream(customerRepository.findAll().spliterator(), false).toList();
        return list;
    }

    @Override
    public Customer getCustomer(int id) {
//        return customerDao.getCustomer(id);
        return customerRepository.findById(id).get();
    }

    @Override
    public String deleteCustomer(int id) {
//        return customerDao.deleteCustomer(id);
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!!"));
        customerRepository.delete(customer);
        return "Customer "+id+" has been removed from system!!";
    }
}
