package com.example.Ba.sket.repositories;

import com.example.Ba.sket.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails,Long> {
}
