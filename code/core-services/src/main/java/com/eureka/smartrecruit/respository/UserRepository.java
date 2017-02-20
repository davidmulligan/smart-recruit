package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.QUser;
import com.eureka.smartrecruit.domain.User;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends QueryableRepository<User, QUser> {

    @Override
    default void customize(QuerydslBindings bindings, QUser user) {
        bindings.bind(user.skills.any().id).first((path, value) -> path.eq(value));
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}
