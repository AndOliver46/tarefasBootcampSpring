package com.devsuperior.bds01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.repositories.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repo;
	
	@Transactional(readOnly = true)
	public List<Department> findAll() {
		List<Department> list = repo.findAll(Sort.by("name"));
		return list;
	}

	
	
}
