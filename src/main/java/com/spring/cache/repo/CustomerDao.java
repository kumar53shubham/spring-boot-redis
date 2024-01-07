package com.spring.cache.repo;

import com.spring.cache.hash.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {

    public static final String HASH_KEY = "Customer";
    @Autowired
    private RedisTemplate redisTemplate;

    public Customer addCustomer(Customer customer){
        redisTemplate.opsForHash().put(HASH_KEY,customer.getId(),customer);
        return customer;
    }

    public Customer updateCustomer(int id,Customer customer){
        Customer existingCustomer = (Customer) redisTemplate.opsForHash().get(HASH_KEY, id);
        if (existingCustomer!=null){
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setDob(customer.getDob());
            existingCustomer.setPhone(customer.getPhone());
            redisTemplate.opsForHash().put(HASH_KEY,id,existingCustomer);
            return existingCustomer;
        }else {
            throw new RuntimeException("Customer not found!!");
        }
    }

    public List<Customer> getAllCustomers(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Customer getCustomer(int id){
        return (Customer) redisTemplate.opsForHash().get(HASH_KEY,id);
    }

    public String deleteCustomer(int id){
        redisTemplate.opsForHash().delete(HASH_KEY,id);
        return "Customer "+id+" has been removed from system!!";
    }
}
