package dev.yeray.sp.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.yeray.sp.exception.DataNotFoundException;
import dev.yeray.sp.model.dto.ProductDTO;
import dev.yeray.sp.model.mapper.ProductMapper;
import dev.yeray.sp.repository.ProductRepository;
import dev.yeray.sp.utils.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	protected ProductRepository productRepository;
	protected ProductMapper productMapper;

	public ProductService(final ProductRepository productRepository, final ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		return productMapper.fromEntity(this.productRepository.findAll(Sort.by("name")));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		return productMapper
				.fromEntity(this.productRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id)));
	}

	@Transactional
	public ProductDTO createProduct(ProductDTO productDTO) {
		return productMapper.fromEntity(this.productRepository.save(productMapper.fromDTO(productDTO)));
	}

	@Transactional
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		ProductDTO oldProduct = productMapper
				.fromEntity(this.productRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id)));
		productDTO.setId(id);
		if (!Utils.isNotBlank(productDTO.getName()))
			productDTO.setName(oldProduct.getName());
		log.info("Update Product: {}", productDTO);
		return productMapper.fromEntity(this.productRepository.save(productMapper.fromDTO(productDTO)));
	}

	@Transactional
	public void deleteProduct(Long id) {
		this.productRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean existsById(Long id) {
		return this.productRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> findByStockGreaterThan(int stock) {
		return productMapper.fromEntity(this.productRepository.findByStockGreaterThan(stock, Sort.by("name")));
	}

}
