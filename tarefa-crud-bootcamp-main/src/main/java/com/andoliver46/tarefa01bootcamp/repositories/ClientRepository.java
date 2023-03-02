package com.andoliver46.tarefa01bootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andoliver46.tarefa01bootcamp.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
