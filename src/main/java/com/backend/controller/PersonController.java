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
import com.backend.exception.PersonNotFoundException;
import com.backend.model.Client;
import com.backend.model.Person;
import com.backend.model.idDto;
import com.backend.service.ClientService;
import com.backend.service.PersonService;

@RestController
@RequestMapping("/person")
@CrossOrigin
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@Autowired
	ClientService clientService;
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable int id, @RequestBody idDto clientIdDto) {
		Person person = personService.getPerson(id);
		Client client = clientService.getClient(clientIdDto.getId());		
		if (client == null || person == null) {
			throw new BadRequestException();
		}
		personService.removeClient(person, client);
		return new ResponseEntity<>("Successfully deleted " + client.getCompanyName() + "!", HttpStatus.OK);
	}
	 
	@PostMapping("/client/{id}")
	public ResponseEntity<Object> addClient(@PathVariable int id, @RequestBody idDto clientIdDto) {
		Person person = personService.getPerson(id);
		Client client = clientService.getClient(clientIdDto.getId());
		if (person == null || client == null) {
			throw new BadRequestException();
		}  
		personService.addClient(person, client);
		return new ResponseEntity<>("Successfully added " + client.getCompanyName() + "!", HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Person>> getPersonList() {
		List<Person> list = personService.getPersonList();		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	  
	@GetMapping("/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable int id) {
		Person person = personService.getPerson(id);
		if (person == null) {
			throw new PersonNotFoundException();
		}
		return new ResponseEntity<>(person, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Person> postPerson(@RequestBody Person person) {
		if (person.getId() != 0 ||
				person.getPhoneNumber() < 1000000000 ||
				person.getPhoneNumber() > 9999999999l ||
				person.getName().length() < 1 ||
				person.getName().length() > 50) {
			throw new BadRequestException();
		}
		personService.addPerson(person);
		return new ResponseEntity<>(person, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		Person p = personService.getPerson(person.getId());
		if (p == null) {
			throw new PersonNotFoundException();
		}
		
		if (person.getPhoneNumber() < 1000000000 ||
				person.getPhoneNumber() > 9999999999l ||
				person.getName().length() < 1 ||
				person.getName().length() > 50) {
			throw new BadRequestException();
		}
		p = personService.updatePerson(person);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePerson(@PathVariable int id) {	
		Person person = personService.getPerson(id);
		if (person == null) {
			throw new PersonNotFoundException();
		}
		personService.deletePerson(person);
		return new ResponseEntity<>("Successfully deleted " + person.getName() + "!", HttpStatus.OK);
	}
}
