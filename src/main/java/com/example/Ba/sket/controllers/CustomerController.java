package com.example.Ba.sket.controllers;


import com.example.Ba.sket.exceptions.ResourceNotFoundException;
import com.example.Ba.sket.models.Customer;
import com.example.Ba.sket.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/customers")
public class CustomerController extends ErrorString{

    private static final String strValue = "Customer";
    private static String errorString = "";
    private static final Map<String, Boolean> response = new HashMap<>();

    @Autowired
    private CustomerRepository customerRepository;

    //get all customers rest api
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    //create customers rest api
    @PostMapping("/customers")
    public Customer createCustomers(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    //get a customer by id rest api
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        errorString = ErrorString.getErrorString(strValue, id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity <Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetail){
        errorString = ErrorString.getErrorString(strValue, id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));

        customer.setCustomerName(customerDetail.getCustomerName());
        customer.setCustomerEmailId(customerDetail.getCustomerEmailId());
        customer.setCustomerPhoneNumber(customerDetail.getCustomerPhoneNumber());
        customer.setAdmin(customerDetail.getAdmin());

        Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity <Map<String, Boolean>> deleteCustomer(@PathVariable Long id){
        errorString = ErrorString.getErrorString(strValue, id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));

        customerRepository.delete(customer);
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
