package com.example.CoffeeShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.entity.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
	
}
