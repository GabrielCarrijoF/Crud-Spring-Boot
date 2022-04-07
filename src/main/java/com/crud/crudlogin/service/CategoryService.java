package com.crud.crudlogin.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.crudlogin.dto.CategoryDTO;
import com.crud.crudlogin.entitesCategorys.Category;
import com.crud.crudlogin.repositories.CategoryRepository;
import com.crud.crudlogin.service.exceptions.DataBaseException;
import com.crud.crudlogin.service.exceptions.ResourceNotFoundException;

@Service  // Registrar a classe como componente que participa do sistema de injeção de dependencia
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) //
	public Page<CategoryDTO> catchAllPaged(final PageRequest pageRequest){
		
		Page<Category> list = repository.findAll(pageRequest);
		return list.map(x -> new CategoryDTO(x));//Transformando list category em lisDTO	
		
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(final Long id) {
		Optional<Category> obj = repository.findById(id);//Efetiva o acesso ao banco de dados
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Erro Interno do Servidor"));// Leva para minha nova exception
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO categoryDTO) {
		Category entity = new Category();
		entity.setName(categoryDTO.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id,CategoryDTO categoryDTO) {
	//Não mexe no banco e instancia um objeto provisorio com dados e o ID no objeto apenas quando salva ele vai no banco
		try{
		Category entity = repository.getOne(id);
		entity.setName(categoryDTO.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
		}
		catch(EntityNotFoundException entityNotFoundException){
			throw new ResourceNotFoundException("ID não encontrado"+ id);
		}
	}

	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException emptyResultDataAccessException) { //Caso tentem deletar um id que n existe
			throw new ResourceNotFoundException("ID "+ id + " não encontrado!");
		}
		catch(DataIntegrityViolationException dataIntegrityViolationException) { //Caso tentem deletar algo que n pode
			throw new DataBaseException("Violação de Integridade");
		}
	}
}

