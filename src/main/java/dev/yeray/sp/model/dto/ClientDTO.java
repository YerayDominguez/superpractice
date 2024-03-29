package dev.yeray.sp.model.dto;

import java.io.Serializable;
import java.util.List;

import dev.yeray.sp.model.entity.Order;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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