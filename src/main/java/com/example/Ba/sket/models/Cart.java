package com.example.Ba.sket.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity(name = "carts")
public class Cart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_amount")
    private int itemAmount;

    @Column(name = "total_amount")
    private Double totalAmount;

    @JoinColumn(name = "product_id")
    @OneToMany
    private List<Product> productList;

    @JoinColumn(name = "customer_id")
    @OneToOne
    private Customer customer;

    public Cart() {
    }

    public Cart(Long id, int itemAmount, Double totalAmount, List<Product> productList, Customer customer) {
        this.id = id;
        this.itemAmount = itemAmount;
        this.totalAmount = totalAmount;
        this.productList = productList;
        this.customer = customer;
    }

    public Long getCartId() {
        return id;
    }

    protected void setCartId(Long id) {
        this.id = id;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
