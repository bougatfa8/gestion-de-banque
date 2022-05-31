package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Client;

public interface ClientRepository extends JpaRepository<Client,Long>{

}
