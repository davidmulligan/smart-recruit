package com.eureka.smartrecruit.web.controller;

import com.eureka.smartrecruit.domain.Category;
import com.eureka.smartrecruit.dto.CategoryDto;
import com.eureka.smartrecruit.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.jooq.lambda.Seq;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryResource {

    private final CategoryService categoryService;
    private final Mapper mapper;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CategoryDto categoryDto) {
        categoryService.create(mapper.map(categoryDto, Category.class));
    }

    @RequestMapping(method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.findById(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setPrincipal(categoryDto.isPrincipal());
        category.setActive(categoryDto.isActive());
        categoryService.update(category);
    }

    @RequestMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<CategoryDto> findAll() {
        return Seq.seq(categoryService.findAll()).map(category -> mapper.map(category, CategoryDto.class)).toList();
    }

    @RequestMapping(value="/principal", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public List<CategoryDto> findPrincipal() {
        return Seq.seq(categoryService.findPrincipal()).map(category -> mapper.map(category, CategoryDto.class)).toList();
    }
}
