package com.crud.crudlogin.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.crudlogin.entitesCategorys.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category ,Long>{
	
	
}