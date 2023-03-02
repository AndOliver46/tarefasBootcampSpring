package com.andoliver46.tarefa01bootcamp.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andoliver46.tarefa01bootcamp.dto.ClientDTO;
import com.andoliver46.tarefa01bootcamp.entities.Client;
import com.andoliver46.tarefa01bootcamp.repositories.ClientRepository;
import com.andoliver46.tarefa01bootcamp.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(Integer page, Integer linesPerPage, Direction direction, String orderBy) {
		PageRequest list = PageRequest.of(page, linesPerPage, direction, orderBy);
		Page<Client> listPaged = repo.findAll(list);
		return listPaged.map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repo.findById(id);
		return new ClientDTO(obj.orElseThrow(() -> new ResourceNotFoundException("ID " + id + " não foi encontrado.")));
	}

	@Transactional
	public ClientDTO insert(ClientDTO objDto) {
		Client obj = new Client();
		updateData(obj, objDto);
		obj = repo.save(obj);
		return new ClientDTO(obj);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO objDto) {
		try {
			Client obj = repo.getReferenceById(id);
			updateData(obj, objDto);
			obj = repo.save(obj);
			return new ClientDTO(obj);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID " + id + " não foi encontrado.");
		}
	}
	
	public void delete(Long id) {
		try {
			repo.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID " + id + " não foi encontrado.");
		}
	}

	private void updateData(Client obj, ClientDTO objDto) {
		obj.setName(objDto.getName());
		obj.setCpf(objDto.getCpf());
		obj.setIncome(objDto.getIncome());
		obj.setBirthDate(objDto.getBirthDate());
		obj.setChildren(objDto.getChildren());
	}
}
