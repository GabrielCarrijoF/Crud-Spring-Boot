package com.crud.crudlogin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.crudlogin.dto.CategoryDTO;
import com.crud.crudlogin.entitesCategorys.Category;
import com.crud.crudlogin.repositories.CategoryRepository;
import com.crud.crudlogin.service.exceptions.EntityNotFoundException;

@Service  // Registrar a classe como componente que participa do sistema de injeção de dependencia
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) //
	public List<CategoryDTO> catchAll(){
		List<Category> list = repository.findAll();
		
		//Transformando list category em lisDTO	
		List<CategoryDTO> listDTO = new ArrayList<>();
		for(Category cat:list) {
			listDTO.add(new CategoryDTO(cat));
		}
		return listDTO;
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);//busca nunca sera nula
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Erro Interno do Servidor"));
		return new CategoryDTO(entity);
		
	}
}
