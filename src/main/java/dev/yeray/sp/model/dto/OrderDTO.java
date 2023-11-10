package dev.yeray.sp.model.dto;

import java.io.Serializable;

import dev.yeray.sp.model.entity.Client;
import lombok.Data;

@Data
public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;	
	private Integer paid;
	private Integer paymentMethod;
	private Integer delivered;
	private Client client;
		
}