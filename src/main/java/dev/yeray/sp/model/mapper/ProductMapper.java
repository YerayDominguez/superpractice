package dev.yeray.sp.model.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import dev.yeray.sp.model.dto.ProductDTO;
import dev.yeray.sp.model.entity.Product;



public class ProductMapper {
	
	protected ModelMapper modelMapper;

	public ProductMapper(final ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Product fromDTO(ProductDTO productDTO) {
		return modelMapper.map(productDTO, Product.class);
	}
	
	public List<Product> fromDTO(List<ProductDTO> personDTOList ) {
		return modelMapper.map(personDTOList, new TypeToken<List<Product>>(){}.getType());
	}
	
	public ProductDTO fromEntity(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}
	
	public List<ProductDTO> fromEntity(List<Product> productList ) {
		return modelMapper.map(productList, new TypeToken<List<ProductDTO>>(){}.getType());
	}

}
