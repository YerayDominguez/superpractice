package dev.yeray.sp.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import dev.yeray.sp.model.dto.ClientDTO;
import dev.yeray.sp.service.ClientService;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ClientService clientService;

    @Test
    void testFindAll() throws Exception {
        // Configura tu mock de clientService para devolver una lista de ClientDTOs
        when(clientService.findAll()).thenReturn(Arrays.asList(new ClientDTO(), new ClientDTO()));

        // Realiza una petición GET y verifica el resultado
        mockMvc.perform(get("/clients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Espera que el estado de la respuesta HTTP sea 200 OK
                .andExpect(jsonPath("$.size()").value(2)); // Espera que el tamaño de la lista de clientes sea 2

        // Verifica que se llamó al método findAll del service
        verify(clientService, times(1)).findAll();
    }
}