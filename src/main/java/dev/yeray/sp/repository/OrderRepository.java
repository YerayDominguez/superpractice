package dev.yeray.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.yeray.sp.model.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
	
}