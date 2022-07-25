package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.backend.dao.PersonDao;
import com.backend.model.Client;
import com.backend.model.Person;

@Service
public class PersonService {

	@Autowired
	private PersonDao personDao;
	
	@Autowired
	@Lazy
	private ClientService clientService;
	
	public boolean removeClient(Person person, Client client) {
		person.removeClient(client);
		personDao.save(person);
		return true;
	}
	
	public boolean addClient(Person person, Client client) {
		person.addClient(client);
		personDao.save(person);
		return true;
	}
	
	public boolean addPerson(Person person) {
		personDao.save(person);
		return true;
	}
	
	public List<Person> getPersonList() {
		List<Person> list = personDao.findAll();
		return list;
	} 
	
	public Person getPerson(int id) {
		Person person = personDao.findById(id).orElse(null);
		if (person == null) { 
			return null;
		}
		return person; 
	}

	public Person updatePerson(Person p) {
		Person person = getPerson(p.getId());
		person.setName(p.getName());
		person.setPhoneNumber(p.getPhoneNumber());
		personDao.save(person);
		return person;
	}

	public boolean deletePerson(Person person) {
		personDao.delete(person);
		return true;
	}
}
