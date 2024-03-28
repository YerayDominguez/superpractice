package dev.yeray.sp.model.entity;


import java.io.Serializable;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ORDER1")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, precision = 38)
	private Long id;
	
	@Column(nullable = true)
	private Integer paid;
	
	@Column(nullable = true)
	private Integer paymentMethod;
	
	@Column(nullable = true)
	private Integer delivered;

	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "CLIENT_FK")
    private Client client;
    
}