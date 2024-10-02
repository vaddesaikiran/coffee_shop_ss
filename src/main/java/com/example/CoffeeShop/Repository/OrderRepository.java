package com.example.CoffeeShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
