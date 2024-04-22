package com.onlineshopping.backend.repository;

import com.onlineshopping.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.email = ?1 AND o.id = ?2")
    Order findOrderByEmailAndOrderId(String email, Long orderId);

    List<Order> findAllByEmail(String emailId);
}
