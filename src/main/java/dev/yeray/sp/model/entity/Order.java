package dev.yeray.sp.model.entity;


import java.io.Serializable;
//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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