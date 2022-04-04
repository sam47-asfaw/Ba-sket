package com.example.Ba.sket.models;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity(name = "orders")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "cart_id")
    @OneToOne
    private Cart cart;

    @JoinColumn(name = "product_id")
    @ManyToMany
    private List <Product> productList;

    public Order() {
    }

    public Order(Cart cart, List<Product> productList) {
        this.cart = cart;
        this.productList = productList;
    }

    public Long getOrderId() {
        return id;
    }

    protected void setOrderId(Long id) {
        this.id = id;
    }

    public Cart getOrderCart() {
        return cart;
    }

    public void setOrderCart(Cart cart) {
        this.cart = cart;
    }

    public List<Product> getOrderProductList() {
        return productList;
    }

    public void setOrderProductList(List<Product> productList) {
        this.productList = productList;
    }
}
