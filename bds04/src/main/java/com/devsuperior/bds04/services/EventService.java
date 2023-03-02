package com.devsuperior.bds04.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

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
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Id " + id + " não encontrado");
		}
	}

	public EventDTO insert(EventDTO objDto) {
		Event obj = new Event();
		updateData(obj, objDto);
		obj = repository.save(obj);
		return new EventDTO(obj);
	}

	public Page<EventDTO> findAll(Pageable pageable) {
		Page<Event> page = repository.findAll(pageable);
		return page.map(x -> new EventDTO(x));
	}

	private void updateData(Event obj, EventDTO objDto) {
		obj.setName(objDto.getName());
		obj.setDate(objDto.getDate());
		obj.setUrl(objDto.getUrl());
		obj.setCity(new City(objDto.getCityId(), null));
	}
}
