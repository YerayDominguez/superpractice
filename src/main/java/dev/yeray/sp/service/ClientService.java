package dev.yeray.sp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.yeray.sp.exception.DataNotFoundException;
import dev.yeray.sp.model.dto.ClientDTO;
import dev.yeray.sp.model.mapper.ClientMapper;
import dev.yeray.sp.repository.ClientRepository;
import dev.yeray.sp.utils.Utils;

@Service
public class ClientService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

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
				.orElseThrow(() -> new DataNotFoundException(id)));
	}

	public ClientDTO createClient(ClientDTO clientDTO) {
		return clientMapper.fromEntity(this.clientRepository.save(clientMapper.fromDTO(clientDTO)));
	}
	
	public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
		ClientDTO oldClient = this.findById(id);
		clientDTO.setId(id);
		if (!Utils.isNotBlank(clientDTO.getName())) clientDTO.setName(oldClient.getName());
		if (!Utils.isNotBlank(clientDTO.getSurname1())) clientDTO.setSurname1(oldClient.getSurname1());
		logger.info("Update Client: {}", clientDTO);
		return clientMapper.fromEntity(this.clientRepository.save(clientMapper.fromDTO(clientDTO)));
	}

	public void deleteClient(Long id) {
		this.clientRepository.deleteById(id);
	}

	public boolean existsById(Long id) {
		return this.clientRepository.existsById(id);
	}

}
