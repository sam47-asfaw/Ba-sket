package com.example.Ba.sket.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name = "products")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "product_amount")
    private int productAmount;

    @Column(name = "additionDate")
    private Date dateOfAddition;

    @Column(name = "product_detail")
    private String product_detail;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @JoinColumn(name = "customer_id")
    @ManyToMany
    private List<Customer> customers;

    public Product() {
    }

    public Product(String productName, Double productPrice, int productAmount, Date dateOfAddition, List<Customer> customers, Category category)
    {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
        this.dateOfAddition = dateOfAddition;
        this.category = category;
        this.customers = customers;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public Date getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(Date dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

    public Category getCategory(){
        return this.category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
