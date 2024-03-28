package dev.yeray.sp.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Name is required")
	private String name;
	private Integer stock;
	
	private Double price;
		
}