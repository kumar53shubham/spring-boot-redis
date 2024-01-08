package com.spring.cache.services;

import com.spring.cache.hash.Customer;
import com.spring.cache.repo.CustomerDao;
import com.spring.cache.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@CacheConfig(cacheNames = "customers")
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @CachePut(key = "#customers.id")
    public Customer saveCustomer(Customer customer) {
        log.info("CustomerServiceImpl::saveCustomer() talking to DB");
//        return customerDao.addCustomer(customer);
        return customerRepository.save(customer);
    }

    @Override
    @CachePut(key = "#id")
    public Customer updateCustomer(int id, Customer customer) {
        log.info("CustomerServiceImpl::updateCustomer() talking to DB");
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
    @Cacheable
    public List<Customer> getAllCustomers() {
        log.info("CustomerServiceImpl::getAllCustomers() talking to DB");
//        return customerDao.getAllCustomers();

//        convert iterable to collection java 8 feature
        List<Customer> list = StreamSupport.stream(customerRepository.findAll().spliterator(), false).toList();
        return list;
    }

    @Override
    @Cacheable(key = "#id")
    public Customer getCustomer(int id) {
        log.info("CustomerServiceImpl::getCustomer() talking to DB");
//        return customerDao.getCustomer(id);
        return customerRepository.findById(id).get();
    }

    @Override
    @CacheEvict(key = "#id")
    public String deleteCustomer(int id) {
        log.info("CustomerServiceImpl::deleteCustomer() talking to DB");
//        return customerDao.deleteCustomer(id);
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!!"));
        customerRepository.delete(customer);
        return "Customer "+id+" has been removed from system!!";
    }
}
