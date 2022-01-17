package com.crud.crudlogin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.crudlogin.entitesCategorys.Category;
import com.crud.crudlogin.repositories.CategoryRepository;

@Service  // Registrar a classe como componente que participa do sistema de injeção de dependencia
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) //
	public List<Category> catchAll(){
		return repository.findAll();
	}
}
