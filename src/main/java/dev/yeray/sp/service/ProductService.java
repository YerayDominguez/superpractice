package dev.yeray.sp.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.yeray.sp.exception.ProductNotFoundException;
import dev.yeray.sp.model.dto.ProductDTO;
import dev.yeray.sp.model.mapper.ProductMapper;
import dev.yeray.sp.repository.ProductRepository;

@Service
public class ProductService {

	protected ProductRepository productRepository;
	protected ProductMapper productMapper;

	public ProductService(final ProductRepository productRepository, final ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	public List<ProductDTO> findAll() {
		return productMapper.fromEntity(this.productRepository.findAll(Sort.by("name")));
	}

	public ProductDTO findById(Long id) {
		return productMapper.fromEntity(this.productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id)));
	}

	public List<ProductDTO> findByStockGreaterThan(int stock) {
		return productMapper.fromEntity(this.productRepository.findByStockGreaterThan(stock, Sort.by("name")));
	}

}
