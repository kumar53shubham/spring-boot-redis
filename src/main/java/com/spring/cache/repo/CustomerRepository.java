package com.spring.cache.repo;

import com.spring.cache.hash.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}
