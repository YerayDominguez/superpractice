package dev.yeray.sp.model.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import dev.yeray.sp.model.dto.ClientDTO;
import dev.yeray.sp.model.entity.Client;


public class ClientMapper {
	
	protected ModelMapper modelMapper;

	public ClientMapper(final ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Client fromDTO(ClientDTO clientDTO) {
		return modelMapper.map(clientDTO, Client.class);
	}
	
	public List<Client> fromDTO(List<ClientDTO> clientDTOList ) {
		return modelMapper.map(clientDTOList, new TypeToken<List<Client>>(){}.getType());
	}
	
	public ClientDTO fromEntity(Client product) {
		return modelMapper.map(product, ClientDTO.class);
	}
	
	public List<ClientDTO> fromEntity(List<Client> clientList ) {
		return modelMapper.map(clientList, new TypeToken<List<ClientDTO>>(){}.getType());
	}

}
