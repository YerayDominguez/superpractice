package dev.yeray.sp.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import dev.yeray.sp.model.entity.Order;
import lombok.Data;

@Data
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;	
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Surname is required")
	private String surname1;
	private String surname2;
	private List<Order> orders;
		
}