package dev.yeray.sp.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.yeray.sp.exception.DataNotFoundException;
import dev.yeray.sp.model.dto.ClientDTO;
import dev.yeray.sp.model.mapper.ClientMapper;
import dev.yeray.sp.repository.ClientRepository;
import dev.yeray.sp.utils.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientService {

	protected ClientRepository clientRepository;
	protected ClientMapper clientMapper;

	public ClientService(final ClientRepository clientRepository, final ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		return clientMapper.fromEntity(this.clientRepository.findAll(Sort.by("name")));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		return clientMapper
				.fromEntity(this.clientRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id)));
	}

	@Transactional
	public ClientDTO createClient(ClientDTO clientDTO) {
		return clientMapper.fromEntity(this.clientRepository.save(clientMapper.fromDTO(clientDTO)));
	}

	@Transactional
	public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
		ClientDTO oldClient = clientMapper
				.fromEntity(this.clientRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id)));
		clientDTO.setId(id);
		if (!Utils.isNotBlank(clientDTO.getName()))
			clientDTO.setName(oldClient.getName());
		if (!Utils.isNotBlank(clientDTO.getSurname1()))
			clientDTO.setSurname1(oldClient.getSurname1());
		log.info("Update Client: {}", clientDTO);
		return clientMapper.fromEntity(this.clientRepository.save(clientMapper.fromDTO(clientDTO)));
	}

	@Transactional
	public void deleteClient(Long id) {
		this.clientRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean existsById(Long id) {
		return this.clientRepository.existsById(id);
	}

}
