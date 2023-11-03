package dev.yeray.sp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.yeray.sp.model.mapper.ClientMapper;
import dev.yeray.sp.model.mapper.OrderMapper;
import dev.yeray.sp.model.mapper.ProductMapper;

@Configuration
public class AppConfig {

	@Bean
	ProductMapper productMapper() {
	    return new ProductMapper(modelMapper());
	}
	
	@Bean
	OrderMapper orderMapper() {
	    return new OrderMapper(modelMapper());
	}
	
	@Bean
	ClientMapper clientMapper() {
	    return new ClientMapper(modelMapper());
	}
	
	@Bean
	ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
