package com.example.Ba.sket.controllers;

import com.example.Ba.sket.exceptions.ResourceNotFoundException;
import com.example.Ba.sket.models.Cart;
import com.example.Ba.sket.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost: 8080")
@RestController
@RequestMapping("/carts")
public class CartController extends ErrorString{

    private static final String strValue = "Cart";
    private static String errorString = "";
    private static final Map<String, Boolean> response = new HashMap<>();

    @Autowired
    private CartRepository cartRepository;

    //get all cart rest api
    @GetMapping("/carts")
    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    //create Cart rest api
    @PostMapping
    public Cart createCart(@RequestBody Cart cart){
        return cartRepository.save(cart);
    }

    //get cart by id rest api
    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id){
        errorString = ErrorString.getErrorString(strValue,id);
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));

        return ResponseEntity.ok(cart);
    }

    //update cart rest api
    @PutMapping("/carts/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cartDetails){
        errorString = ErrorString.getErrorString(strValue, id);
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));

        cart.setItemAmount(cartDetails.getItemAmount());
        cart.setTotalAmount(cartDetails.getTotalAmount());
        cart.setProductList(cartDetails.getProductList());
        cart.setCustomer(cartDetails.getCustomer());

        Cart updatedCart = cartRepository.save(cart);
        return ResponseEntity.ok(updatedCart);
    }

    //delete cart rest api
    @DeleteMapping("/carts/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCart(@PathVariable Long id){
        errorString = ErrorString.getErrorString(strValue, id);
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));

        cartRepository.delete(cart);
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}