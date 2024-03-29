package dev.yeray.sp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.yeray.sp.model.entity.Client;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testCreateReadDelete() {
        Client client = new Client(null, "John", "Doe", null, null);
        clientRepository.save(client);

        Iterable<Client> clients = clientRepository.findAll();
        assertThat(clients).extracting(Client::getName).contains("John");

        clientRepository.deleteAll();
        assertThat(clientRepository.findAll()).isEmpty();
    }

    @Test
    void testUpdate() {
        Client client = new Client(null, "Jane", "Doe", null, null);
        client = clientRepository.save(client);
        client.setName("Janet");
        Client updatedClient = clientRepository.save(client);

        assertThat(updatedClient.getName()).isEqualTo("Janet");
    }

    @Test
    void testFindById() {
        Client client = new Client(null, "Jake", "Doe", null, null);
        client = clientRepository.save(client);
        Optional<Client> foundClient = clientRepository.findById(client.getId());

        assertThat(foundClient).isPresent().contains(client);
    }
}
