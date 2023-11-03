package dev.yeray.sp.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.yeray.sp.exception.ClientNotFoundException;
import dev.yeray.sp.model.dto.ClientDTO;
import dev.yeray.sp.model.mapper.ClientMapper;
import dev.yeray.sp.repository.ClientRepository;

@Service
public class ClientService {

	protected ClientRepository clientRepository;
	protected ClientMapper clientMapper;

	public ClientService(final ClientRepository clientRepository, final ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	public List<ClientDTO> findAll() {
		return clientMapper.fromEntity(this.clientRepository.findAll(Sort.by("name")));
	}

	public ClientDTO findById(Long id) {
		return clientMapper.fromEntity(this.clientRepository.findById(id)
				.orElseThrow(() -> new ClientNotFoundException("Client not found with ID: " + id)));
	}

}
