package dev.yeray.sp.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.yeray.sp.constants.Constants;
import dev.yeray.sp.exception.DataNotFoundException;
import dev.yeray.sp.model.dto.ClientDTO;
import dev.yeray.sp.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(final ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		
		log.info(Constants.ENTERING, "'ClientController.findAll'");
		log.info(Constants.ENDPOINT, method, url);
		log.info("Get Client List");
		return new ResponseEntity<>(this.clientService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();

		log.info(Constants.ENTERING, "'ClientController.findById'");
		log.info(Constants.ENDPOINT, method, url);
		log.info("Find Client with Id: {}", id);
		return new ResponseEntity<>(this.clientService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();

		log.info(Constants.ENTERING, "'createClient'");
		log.info(Constants.ENDPOINT, method, url);
		clientDTO.setId(null);
		log.info("Create Client: {}", clientDTO);
		return new ResponseEntity<>(this.clientService.createClient(clientDTO), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO,
			HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();

		log.info(Constants.ENTERING, "'updateClient'");
		log.info(Constants.ENDPOINT, method, url);

		if (!this.clientService.existsById(id)) {
			log.warn(Constants.NOT_FOUND, "Client", id);
			throw new DataNotFoundException(id);
		} else {
			return new ResponseEntity<>(this.clientService.updateClient(id, clientDTO), HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable Long id, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String method = request.getMethod();

		log.info(Constants.ENTERING, "'deleteClient'");
		log.info(Constants.ENDPOINT, method, url);

		if (!this.clientService.existsById(id)) {
			log.warn(Constants.NOT_FOUND, "Client", id);
			throw new DataNotFoundException(id);
		} else {
			log.info(Constants.DELETE, "client", id);
			this.clientService.deleteClient(id);
			return new ResponseEntity<>("Client with ID " + id + " deleted successfully", HttpStatus.OK);
		}
	}

}
