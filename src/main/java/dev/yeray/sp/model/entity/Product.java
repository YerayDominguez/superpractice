package dev.yeray.sp.model.entity;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, precision = 38)
	private Long id;

	@Column(nullable = true, length = 250)
	private String name;
	
	@Column(nullable = true)
	private Integer stock;
	
	@Column(nullable = true)
	private Double price;
	
}