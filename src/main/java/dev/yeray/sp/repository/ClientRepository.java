package dev.yeray.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.yeray.sp.model.entity.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
	
}