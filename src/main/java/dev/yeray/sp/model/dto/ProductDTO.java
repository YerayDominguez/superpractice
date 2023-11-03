package dev.yeray.sp.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;	
	private String name;
	private Integer stock;
	private Double price;
		
}