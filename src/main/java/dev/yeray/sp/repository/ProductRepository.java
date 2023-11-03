package dev.yeray.sp.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.yeray.sp.model.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByStockGreaterThan(int stock, Sort sort);
}