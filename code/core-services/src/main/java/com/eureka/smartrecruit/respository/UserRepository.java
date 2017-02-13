package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.database.BaseRepository;
import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.domain.enumeration.UserType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByEmail(String email);

    List<User> findAllByOrderByEmailAsc();

    List<User> findByTypeOrderByLastNameAscFirstNameAsc(UserType userType);
}
