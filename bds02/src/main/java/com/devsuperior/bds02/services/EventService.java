package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	public EventDTO update(Long id, EventDTO objDto) {
		try {
			Event obj = repository.getOne(id);
			updateData(obj, objDto);
			obj = repository.save(obj);
			return new EventDTO(obj);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " não encontrado");
		}
	}
	
	private void updateData(Event obj, EventDTO objDto) {
		obj.setName(objDto.getName());
		obj.setDate(objDto.getDate());
		obj.setUrl(objDto.getUrl());
		obj.setCity(new City(objDto.getCityId(), null));
	}

}
