package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.domain.enumeration.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findAllByOrderByEmailAsc();

    List<User> findByTypeOrderByLastNameAscFirstNameAsc(UserType userType);
}
