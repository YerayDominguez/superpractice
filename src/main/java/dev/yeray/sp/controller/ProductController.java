package dev.yeray.sp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.yeray.sp.model.dto.ProductDTO;
import dev.yeray.sp.service.ProductService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		return ResponseEntity.ok(this.productService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.productService.findById(id));
	}
	
	@GetMapping("/instock")
	public ResponseEntity<List<ProductDTO>> getProductsInStock() {
		return ResponseEntity.ok(this.productService.findByStockGreaterThan(0));
	}

}
