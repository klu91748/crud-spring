package com.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Integer> {

}
