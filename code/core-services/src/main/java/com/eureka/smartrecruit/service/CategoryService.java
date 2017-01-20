package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Category;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void create(final Category category) {
        categoryRepository.save(category);
    }

    public void update(final Category category) {
        categoryRepository.save(category);
    }

    public void delete(final Long id) {
        categoryRepository.delete(id);
    }

    public Category findById(final Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find category with id: %s", id)));
    }

    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByNameAsc();
    }
}
