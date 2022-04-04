package com.example.Ba.sket.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity(name = "order_details")
public class OrderDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "order_id")
    @OneToMany
    private List<Order> orderList;

    public OrderDetails() {
    }

    public OrderDetails( List<Order> orderList) {
        this.orderList = orderList;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
