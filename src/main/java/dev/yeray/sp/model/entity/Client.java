package dev.yeray.sp.model.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, precision = 38)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_seq", allocationSize = 1)  
	private Long id;

	@Column(length = 100)
	private String name;
	
	@Column(length = 100)
	private String surname1;
	
	@Column(nullable = true, length = 100)
	private String surname2;
	
	@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<Order> orders;
	
}