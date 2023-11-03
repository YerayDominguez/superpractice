package dev.yeray.sp.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;	
	private String name;
	private String surname1;
	private String surname2;
		
}