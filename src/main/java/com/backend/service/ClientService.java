package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.backend.dao.ClientDao;
import com.backend.model.Client;
import com.backend.model.Person;

@Service
public class ClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	@Lazy
	private PersonService personService;
	
	public boolean removePerson(Client client, Person person) {
		client.removePerson(person);
		clientDao.save(client);
		return true;
	}
	
	public boolean addPerson(Client client, Person person) {
		client.addPerson(person);
		clientDao.save(client);
		return true;
	}
	
	public boolean addClient(Client client) {
		clientDao.save(client);	
		return true;
	}
	
	public List<Client> getClientList() {
		List<Client> list = clientDao.findAll();
		return list;
	} 
	
	public Client getClient(int id) {
		Client client = clientDao.findById(id).orElse(null);
		if (client == null) {
			return null;
		}
		return client;
	}
	
	public Client updateClient(Client c) {
		Client client = getClient(c.getId());
		client.setCompanyName(c.getCompanyName());
		client.setUrl(c.getUrl());
		client.setPhoneNumber(c.getPhoneNumber());
		client.setAddress(c.getAddress());		
		clientDao.save(client);
		return client;
	}

	public boolean deleteClient(Client client) {
		clientDao.delete(client);
		return true;
	}
}
