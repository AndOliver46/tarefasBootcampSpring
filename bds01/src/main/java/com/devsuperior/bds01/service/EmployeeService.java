package com.devsuperior.bds01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.DepartmentRepository;
import com.devsuperior.bds01.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	@Autowired
	private DepartmentRepository departmentRepo;

	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAll() {
		List<Employee> list = repo.findAll(Sort.by("name"));
		Page<EmployeeDTO> page = new PageImpl<>(list.stream().map(x -> new EmployeeDTO(x)).toList());
		return page;
	}

	@Transactional
	public EmployeeDTO insert(EmployeeDTO objDto) {
		Employee obj = new Employee();
		updateData(obj, objDto);
		obj = repo.save(obj);
		return new EmployeeDTO(obj);
	}

	private void updateData(Employee obj, EmployeeDTO objDto) {
		obj.setName(objDto.getName());
		obj.setEmail(objDto.getEmail());
		obj.setDepartment(departmentRepo.findById(objDto.getDepartmentId()).get());
	}

}
