package com.example.Ba.sket.controllers;

import com.example.Ba.sket.exceptions.ResourceNotFoundException;
import com.example.Ba.sket.models.OrderDetails;
import com.example.Ba.sket.repositories.OrderDetailRepository;
import com.example.Ba.sket.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost: 8080")
@RestController
@RequestMapping("/order_Details")

public class OrderDetailController extends ErrorString{

    private static final String strValue = "Customer";
    private static String errorString = "";
    private static final Map<String, Boolean> response = new HashMap<>();

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    //get all orderDetails rest api
    @GetMapping("/order_details")
    public List<OrderDetails> getOrderDetails(){
        return orderDetailRepository.findAll();
    }

    //create orderDetails rest api
    @PostMapping("/order_details")
    public OrderDetails createOrderDetails(@RequestBody OrderDetails orderDetails){
        return orderDetailRepository.save(orderDetails);
    }

    //get orderdetail by id rest api

    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Long id){
        errorString = ErrorString.getErrorString(strValue, id);
        OrderDetails orderDetails = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));
        return ResponseEntity.ok(orderDetails);
    }

    //update orderDetail rest api

    public ResponseEntity<OrderDetails> updateOrderDetails (@PathVariable Long id, @RequestBody OrderDetails orderDetailEdited){
        errorString = ErrorString.getErrorString(strValue, id);
        OrderDetails orderDetails = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));

        orderDetails.setOrderList(orderDetailEdited.getOrderList());
        OrderDetails updatedOrderDetails = orderDetailRepository.save(orderDetails);
        return ResponseEntity.ok(updatedOrderDetails);
    }

    public ResponseEntity<Map<String, Boolean>> deleteOrderDetails(@PathVariable Long id) {
        errorString = ErrorString.getErrorString(strValue, id);
        OrderDetails orderDetails = orderDetailRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException(errorString));

        orderDetailRepository.delete(orderDetails);
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
