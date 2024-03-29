package dev.yeray.sp.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import dev.yeray.sp.model.dto.ClientDTO;
import dev.yeray.sp.model.entity.Client;
import dev.yeray.sp.model.mapper.ClientMapper;
import dev.yeray.sp.repository.ClientRepository;

@SpringBootTest
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    private List<Client> mockClients;
    private List<ClientDTO> mockClientDTOs;


    @BeforeEach
    void setUp() {
        // Inicializa los mocks antes de cada test
        mockClients = Arrays.asList(
            new Client(1L, "John Doe", "johndoe@example.com", null, null),
            new Client(2L, "Jane Doe", "janedoe@example.com", null, null)
        );

        mockClientDTOs = Arrays.asList(
            new ClientDTO(1L, "John Doe", "johndoe@example.com", null, null),
            new ClientDTO(2L, "Jane Doe", "janedoe@example.com", null, null)
        );
        
        // Configura el comportamiento simulado del repositorio
        when(clientRepository.findAll(Sort.by("name"))).thenReturn(mockClients);
        
        // Configura el comportamiento simulado del mapper
        when(clientMapper.fromEntity(mockClients)).thenReturn(mockClientDTOs);
    }

    @Test
    void testGetAllClients() {
        // Llama al método que quieres probar
        List<ClientDTO> clients = clientService.findAll();
        
        // Verifica los resultados
        assertEquals(2, clients.size());
        verify(clientRepository, times(1)).findAll(Sort.by("name"));
    }
}
