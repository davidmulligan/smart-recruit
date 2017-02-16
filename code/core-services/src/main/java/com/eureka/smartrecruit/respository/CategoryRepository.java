package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.database.BaseRepository;
import com.eureka.smartrecruit.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends BaseRepository<Category> {

    List<Category> findAllByOrderByNameAsc();

    List<Category> findPrincipalByOrderByNameAsc();
}
