package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.backend.exception.BadRequestException;
import com.backend.exception.ClientNotFoundException;
import com.backend.exception.PersonNotFoundException;
import com.backend.model.Client;
import com.backend.model.Person;
import com.backend.model.idDto;
import com.backend.service.ClientService;
import com.backend.service.PersonService;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@Autowired
	PersonService personService;
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<Object> deletePerson(@PathVariable int id, @RequestBody idDto personIdDto) {
		Client client = clientService.getClient(id);	
		Person person = personService.getPerson(personIdDto.getId());
		if (client == null || person == null) {
			throw new BadRequestException();
		} 
		personService.removeClient(person, client);
		return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);
	} 
	
	@PostMapping("/person/{id}")  
	public ResponseEntity<Object> addPerson(@PathVariable int id, @RequestBody idDto personIdDto) {
		Client client = clientService.getClient(id);	
		Person person = personService.getPerson(personIdDto.getId());	
		if (client == null || person == null) {
			throw new BadRequestException();
		} 
		personService.addClient(person, client);
		return new ResponseEntity<>("Successfully added " + person.getName() + "!", HttpStatus.OK);
	} 
	
	@GetMapping("/all")
	public ResponseEntity<List<Client>> getClientList() {
		List<Client> list = clientService.getClientList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClient(@PathVariable(value="id") int id) {
		Client client = clientService.getClient(id);		
		if (client == null) {
			throw new ClientNotFoundException();
		}
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Client> postClient(@RequestBody Client client) {		
		if (client.getId() != 0 || 			
				client.getCompanyName().length() < 1 || 
				client.getCompanyName().length() > 50 ||
				client.getUrl().length() < 1 ||
				client.getUrl().length() > 50 ||
				client.getPhoneNumber() < 1000000000 || 
				client.getPhoneNumber() > 9999999999l || 
				client.getAddress().length() < 1 ||
				client.getAddress().length() > 50) {
			throw new BadRequestException();
		}
		clientService.addClient(client);
		return new ResponseEntity<>(client, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Client> updateClient(@RequestBody Client client) {
		Client c = clientService.getClient(client.getId());
		if (c == null) {
			throw new ClientNotFoundException();
		}
		
		if (client.getCompanyName().length() < 1 || 
				client.getCompanyName().length() > 50 ||
				client.getUrl().length() < 1 ||
				client.getUrl().length() > 50 ||
				client.getPhoneNumber() < 1000000000 || 
				client.getPhoneNumber() > 9999999999l || 
				client.getAddress().length() < 1 ||
				client.getAddress().length() > 50) {
			throw new BadRequestException();
		}
		c = clientService.updateClient(client);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	 
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable int id) {	
		Client client = clientService.getClient(id);
		if (client == null) {
			throw new ClientNotFoundException();
		}
		clientService.deleteClient(client);
		return new ResponseEntity<>("Successfully deleted " + client.getCompanyName() + "!", HttpStatus.OK);
	}
}
