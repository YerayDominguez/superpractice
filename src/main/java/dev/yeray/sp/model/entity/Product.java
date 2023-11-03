package dev.yeray.sp.model.entity;


import java.io.Serializable;
//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, precision = 38)
	private Long id;

	@Column(nullable = true, length = 250)
	private String name;
	
	@Column(nullable = true)
	private Integer stock;
	
	@Column(nullable = true)
	private Double price;
/*	
	//bi-directional many-to-one association to House
	@OneToMany(mappedBy="fire")
	private List<House> houses;
*/		
}