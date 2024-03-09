package dev.yeray.sp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.yeray.sp.exception.DataNotFoundException;
import dev.yeray.sp.model.dto.ProductDTO;
import dev.yeray.sp.model.mapper.ProductMapper;
import dev.yeray.sp.repository.ProductRepository;
import dev.yeray.sp.utils.Utils;

@Service
public class ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

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
				.orElseThrow(() -> new DataNotFoundException(id)));
	}

	public ProductDTO createProduct(ProductDTO productDTO) {
		return productMapper.fromEntity(this.productRepository.save(productMapper.fromDTO(productDTO)));
	}
	
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		ProductDTO oldProduct = this.findById(id);
		productDTO.setId(id);
		if (!Utils.isNotBlank(productDTO.getName())) productDTO.setName(oldProduct.getName());
		logger.info("Update Product: {}", productDTO);
		return productMapper.fromEntity(this.productRepository.save(productMapper.fromDTO(productDTO)));
	}

	public void deleteProduct(Long id) {
		this.productRepository.deleteById(id);
	}

	public boolean existsById(Long id) {
		return this.productRepository.existsById(id);
	}

	public List<ProductDTO> findByStockGreaterThan(int stock) {
		return productMapper.fromEntity(this.productRepository.findByStockGreaterThan(stock, Sort.by("name")));
	}

}
