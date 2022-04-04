package com.example.Ba.sket.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity(name = "customers")
public class Customer  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column(name = "customer_name",nullable = false)
    private String customer_name;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailID;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "phoneNumber", length=50, nullable = false, unique = true)
    private String phoneNumber;

    @JoinColumn(name = "product_id")
    @ManyToMany
    private List<Product> products;

    @JoinColumn(name = "cart_id")
    @OneToOne
    private Cart cart;


    @Column(name = "isAdmin")
    @Enumerated(EnumType.STRING)
    private Admin admin;

    public Customer(){}

    public Customer(String customer_name, String emailID, String password, String phoneNumber, Admin admin, List<Product> products, Cart cart){
        this.customer_name = customer_name;
        this.password = password;
        this.emailID = emailID;
        this.phoneNumber = phoneNumber;
        this.admin = admin;
        this.products = products;
        this.cart = cart;
    }

    public Long getId(){
        return this.id;
    }

    protected void setId(Long id){
        this.id = id;
    }

     public String getCustomerName(){
        return this.customer_name;
     }

     public void setCustomerName(String customer_name){
        this.customer_name = customer_name;
     }

     public String getCustomerEmailId(){return this.emailID;}

     public void setCustomerEmailId(String emailID){
        this.emailID = emailID;
     }

     public String getCustomerPassword(){
        return this.password;
     }

     public void setCustomerPassword(String password){
        this.password = password;
     }

     public String getCustomerPhoneNumber(){
        return this.phoneNumber;
     }

     public void setCustomerPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
     }

     public Admin getAdmin(){
        return this.admin;
     }

     public void setAdmin(Admin admin){
        this.admin = admin;
     }

     public List<Product> getProducts(){
        return this.products;
     }

     public void setProducts(List<Product> products){
        this.products = products;
     }

     public Cart getCart(){
        return this.cart;
     }

     public void setCart(Cart cart){
        this.cart = cart;
     }

}
