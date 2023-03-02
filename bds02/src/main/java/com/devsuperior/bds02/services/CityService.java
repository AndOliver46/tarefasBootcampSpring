package com.devsuperior.bds02.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DataIntegrityException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		List<CityDTO> listDto = list.stream().map(x -> new CityDTO(x)).toList();
		return listDto;
	}

	@Transactional
	public CityDTO insert(CityDTO objDto) {
		City obj = new City();
		updateData(obj, objDto);
		obj = repository.save(obj);
		return new CityDTO(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Há entidades associadas ao Id: " + id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id " + id + " não encontrado");
		}
	}
	
	private void updateData(City obj, CityDTO objDto) {
		obj.setName(objDto.getName());
	}
}
