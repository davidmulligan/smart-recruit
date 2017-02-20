package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Category;
import com.eureka.smartrecruit.domain.QCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends QueryableRepository<Category, QCategory> {
}
