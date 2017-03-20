package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.database.BaseDomainObject;
import com.eureka.smartrecruit.database.BaseRepository;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface QueryableRepository<T extends BaseDomainObject, Q extends EntityPath<?>> extends BaseRepository<T>, QueryDslPredicateExecutor<T>, QuerydslBinderCustomizer<Q> {

    default Optional<T> tryFindOne(Predicate predicate) {
        return Optional.ofNullable(findOne(predicate));
    }

    @Override
    default void customize(QuerydslBindings bindings, Q root) {
    }
}