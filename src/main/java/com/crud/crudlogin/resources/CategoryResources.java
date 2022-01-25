package com.crud.crudlogin.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudlogin.dto.CategoryDTO;
import com.crud.crudlogin.entitesCategorys.Category;
import com.crud.crudlogin.service.CategoryService;

@RestController // Configurar coisas no codigo
@RequestMapping(value = "/categories")
public class CategoryResources extends Category {

	private static final long serialVersionUID = 1L;
	@Autowired
	private CategoryService service;
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
		CategoryDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}

