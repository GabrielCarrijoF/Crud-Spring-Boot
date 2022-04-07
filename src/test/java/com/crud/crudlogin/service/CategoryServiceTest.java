package com.crud.crudlogin.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.hibernate.engine.internal.ForeignKeys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crud.crudlogin.dto.CategoryDTO;
import com.crud.crudlogin.entitesCategorys.Category;
import com.crud.crudlogin.repositories.CategoryRepository;
import com.crud.crudlogin.service.exceptions.ResourceNotFoundException;
import com.github.javafaker.Faker;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

  private final Faker faker = new Faker();

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private CategoryService categoryService;

  @Test
  void givenIdWhenCallFindByIdThenReturnCategoryDto() {
    //given
    final var id = faker.number().numberBetween(1L, 10L);
    final var name = faker.name().firstName();
    final var category = new Category();
    category.setId(id);
    category.setName(name);

    //when
    Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
    CategoryDTO dto = categoryService.findById(id);

    //then
    assertThat(dto.getId()).isEqualTo(category.getId());
    assertThat(dto.getName()).isEqualTo(category.getName());
  }

  @Test
  void givenIdWhenCallFindByIdThenThrowResourceNotFoundException() {
    //given
    final var id = faker.number().numberBetween(1L, 10L);

    //when - then
    Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.empty());
    assertThatThrownBy(() -> categoryService.findById(id))
        .isInstanceOf(ResourceNotFoundException.class)
        .hasMessage("Erro Interno do Servidor");
  }

  @Test
  void teste() {
    //given
    final var name = faker.name().firstName();
    final var entity = new Category();
    entity.setName(name);

    //when

  }

}
