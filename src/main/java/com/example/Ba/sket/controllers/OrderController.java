package com.example.Ba.sket.controllers;

import com.example.Ba.sket.exceptions.ResourceNotFoundException;
import com.example.Ba.sket.models.Order;
import com.example.Ba.sket.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost: 8080")
@RestController
@RequestMapping("/orders")
public class OrderController extends ErrorString{

    private static final String strValue = "Customer";
    private static String errorString = "";
    private static final Map<String, Boolean> response = new HashMap<>();

    @Autowired
    private OrderRepository orderRepository;

    //get orders rest api
    @GetMapping("/orders")
    public List <Order> getAllOrders(){
        return orderRepository.findAll();
    }

    //create order rest api
    @PostMapping("/orders")
    public Order createCustomers(@RequestBody Order order){
        return orderRepository.save(order);
    }

    //get order by id
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getCustomerById(@PathVariable Long id){
        errorString = ErrorString.getErrorString(strValue, id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));

        return ResponseEntity.ok(order);
    }

    //update order
    @PutMapping("/orders/{id}")
    public ResponseEntity <Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails){
        errorString = ErrorString.getErrorString(strValue, id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));

        order.setOrderCart(orderDetails.getOrderCart());
        order.setOrderProductList(orderDetails.getOrderProductList());

        Order updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    //delete order
    public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Long id){
        errorString = ErrorString.getErrorString(strValue, id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));

        orderRepository.delete(order);

        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
