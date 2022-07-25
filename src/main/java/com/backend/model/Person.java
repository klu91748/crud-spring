package com.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="gen") 
	private int id;
	
	@NotNull
	@Size(min = 1, max = 50, message = "Name is required with maximum length of 50")
	private String name;
	
	@NotNull
	@Size(min = 10, max = 10, message = "Invalid phone number")
	private long phoneNumber;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "persons"})
	private List<Client> clients;
	  
	public Person() {
		
	}

	public Person(String name, long phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.clients = new ArrayList();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void addClient(Client client) {
		clients.add(client);
	}	
	
	public void removeClient(Client client) {
		clients.remove(client);
	}
}
