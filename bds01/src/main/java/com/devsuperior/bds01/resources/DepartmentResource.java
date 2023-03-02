package com.devsuperior.bds01.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.service.DepartmentService;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentResource {

	@Autowired
	private DepartmentService service;
	
	@GetMapping
	public ResponseEntity<List<Department>> findAll( ){
		List<Department> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
}
