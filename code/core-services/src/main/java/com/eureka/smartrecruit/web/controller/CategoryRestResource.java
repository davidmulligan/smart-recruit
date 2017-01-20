package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Category;
import com.eureka.smartrecruit.dto.CategoryDto;
import com.eureka.smartrecruit.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryRestResource {

    private final CategoryService categoryService;
    private final Mapper mapper;

    @RequestMapping(value="/categories", method=RequestMethod.POST, produces={ "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);
        categoryService.create(category);
    }

    @RequestMapping(value="/categories", method=RequestMethod.PUT, produces={ "application/json" })
    @ResponseStatus( HttpStatus.OK )
    public void update(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.findById(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setActive(categoryDto.isActive());
        category.setSubCategories(categoryDto.getSubCategories().stream().map(subCategory -> mapper.map(subCategory, Category.class)).collect(Collectors.toList()));
        categoryService.update(category);
    }

    @RequestMapping(value="/categories/{id}", method=RequestMethod.DELETE, produces={ "application/json" })
    @ResponseStatus( HttpStatus.OK )
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

    @RequestMapping(value="/categories", method=RequestMethod.GET, produces={ "application/json" })
    public List<CategoryDto> findAll() {
        return categoryService.findAll().stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }
}
