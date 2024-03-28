package dev.yeray.sp.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.yeray.sp.constants.Constants;
import dev.yeray.sp.exception.DataNotFoundException;
import dev.yeray.sp.model.dto.ProductDTO;
import dev.yeray.sp.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		
		logger.info(Constants.ENTERING, "'ProductController.findAll'");
		logger.info(Constants.ENDPOINT, method, url);
		logger.info("Get Product List");
		return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();

		logger.info(Constants.ENTERING, "'ProductController.findById'");
		logger.info(Constants.ENDPOINT, method, url);
		logger.info("Find Product with Id: {}", id);
		return new ResponseEntity<>(this.productService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();

		logger.info(Constants.ENTERING, "'createProduct'");
		logger.info(Constants.ENDPOINT, method, url);
		productDTO.setId(null);
		logger.info("Create Product: {}", productDTO);
		return new ResponseEntity<>(this.productService.createProduct(productDTO), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO,
			HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();

		logger.info(Constants.ENTERING, "'updateProduct'");
		logger.info(Constants.ENDPOINT, method, url);

		if (!this.productService.existsById(id)) {
			logger.warn(Constants.NOT_FOUND, "Product", id);
			throw new DataNotFoundException(id);
		} else {
			return new ResponseEntity<>(this.productService.updateProduct(id, productDTO), HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();

		logger.info(Constants.ENTERING, "'deleteProduct'");
		logger.info(Constants.ENDPOINT, method, url);

		if (!this.productService.existsById(id)) {
			logger.warn(Constants.NOT_FOUND, "Product", id);
			throw new DataNotFoundException(id);
		} else {
			logger.info(Constants.DELETE, "product", id);
			this.productService.deleteProduct(id);
			return new ResponseEntity<>("Product with ID " + id + " deleted successfully", HttpStatus.OK);
		}
	}
	
	@GetMapping("/instock")
	public ResponseEntity<List<ProductDTO>> getProductsInStock(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		
		logger.info(Constants.ENTERING, "'ProductController.getProductsInStock'");
		logger.info(Constants.ENDPOINT, method, url);
		logger.info("Get Product in Stock List");
		return new ResponseEntity<>(this.productService.findByStockGreaterThan(0), HttpStatus.OK);
	}


}
