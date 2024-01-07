package com.spring.cache.controllers;

import com.spring.cache.hash.Customer;
import com.spring.cache.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer){
        Customer saveCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(saveCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") int id,@RequestBody Customer customer){
        Customer updateCustomer = customerService.updateCustomer(id,customer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> list = customerService.getAllCustomers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id){
        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id){
        String deleteCustomer = customerService.deleteCustomer(id);
        return new ResponseEntity<>(deleteCustomer, HttpStatus.OK);
    }
}
