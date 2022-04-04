package com.example.Ba.sket.controllers;

import com.example.Ba.sket.exceptions.ResourceNotFoundException;
import com.example.Ba.sket.models.Product;
import com.example.Ba.sket.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost: 8080")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    //get all products rest api
    @GetMapping("/products")
    public List <Product> getAllProducts(Product product){
        return productRepository.findAll();
    }

    //create products
    @PostMapping("/products")
    public Product createProduct ( @RequestBody Product product ){
        return productRepository.save(product);
    }

    //get a product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById (@PathVariable Long id) {
        String errorString = "Product by id: "+id+" not found";
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));
        return ResponseEntity.ok(product);
    }

    //update products
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct (@PathVariable Long id, @RequestBody Product productDetail){
        String errorString = "Product by id: "+id+" not found";
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));
        product.setProductName(productDetail.getProductName());
        product.setProductPrice(productDetail.getProductPrice());
        product.setProductAmount(productDetail.getProductAmount());
        product.setCategory(productDetail.getCategory());
        product.setDateOfAddition(productDetail.getDateOfAddition());
        product.setCustomers(productDetail.getCustomers());

        Product updatedProduct = productRepository.save(product);

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>>  deleteProduct(@PathVariable Long id){
        String errorString = "Product by id: "+id+" not found";

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorString));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<String, Boolean>();

        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
