package dev.yeray.sp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.yeray.sp.exception.DataNotFoundException;
import dev.yeray.sp.model.dto.OrderDTO;
import dev.yeray.sp.model.mapper.OrderMapper;
import dev.yeray.sp.repository.OrderRepository;


@Service
public class OrderService {

	protected OrderRepository orderRepository;
	protected OrderMapper orderMapper;

	public OrderService(final OrderRepository orderRepository, final OrderMapper orderMapper) {
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
	}

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		return orderMapper.fromEntity(this.orderRepository.findAll());
	}

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		return orderMapper.fromEntity(this.orderRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Order not found with ID: " + id)));
	}

}
