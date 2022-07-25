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
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="gen") 
	private int id;
	
	@NotNull
	@Size(min = 1, max = 50, message = "Company name is required with maximum length of 50")
	private String companyName;
	
	@NotNull
	@Size(min = 1, message = "Url is required")
	private String url;
	
	@NotNull
	@Size(min = 10, max = 10, message = "Invalid phone number")
	private long phoneNumber;
	
	@NotNull
	@Size(min = 1, max = 50, message = "Address is required with maximum length of 50")
	private String address;
	
	@ManyToMany(mappedBy = "clients", cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "clients"})
	private List<Person> persons;
	
	public Client() {
		
	}
	
	public Client(String companyName, String url, long phoneNumber, String address) {
		super();
		this.companyName = companyName;
		this.url = url;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.persons = new ArrayList();
	}

	public int getId() {
		return id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<Person> getPersons() {
		return persons;
	}

	public void addPerson(Person person) {
		persons.add(person);
	}
	
	public void removePerson(Person person) {
		persons.remove(person);
	}
}
