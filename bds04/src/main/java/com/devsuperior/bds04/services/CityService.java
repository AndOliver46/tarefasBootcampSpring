package com.devsuperior.bds04.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

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

	private void updateData(City obj, CityDTO objDto) {
		obj.setName(objDto.getName());
	}
}
