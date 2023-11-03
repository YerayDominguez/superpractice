package dev.yeray.sp.model.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import dev.yeray.sp.model.dto.OrderDTO;
import dev.yeray.sp.model.entity.Order;


public class OrderMapper {
	
	protected ModelMapper modelMapper;

	public OrderMapper(final ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Order fromDTO(OrderDTO orderDTO) {
		return modelMapper.map(orderDTO, Order.class);
	}
	
	public List<Order> fromDTO(List<OrderDTO> ordenDTOList ) {
		return modelMapper.map(ordenDTOList, new TypeToken<List<Order>>(){}.getType());
	}
	
	public OrderDTO fromEntity(Order order) {
		return modelMapper.map(order, OrderDTO.class);
	}
	
	public List<OrderDTO> fromEntity(List<Order> orderList ) {
		return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
	}

}
