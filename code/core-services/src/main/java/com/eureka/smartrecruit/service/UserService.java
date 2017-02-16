package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.domain.enumeration.UserType;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void create(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setActivationExpiry(LocalDateTime.now().plusDays(7));
        if (user.getRoles() != null) {
            user.getRoles().clear();
        }
        userRepository.save(user);
    }

    public void update(final User user) {
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.delete(id);
    }

    public User findById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find user with id: %s", id)));
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public List<User> findAll() {
        List<User> users = userRepository.findAllByOrderByEmailAsc();
        removePasswords(users);
        return users;
    }

    public List<User> findByType(UserType userType) {
        List<User> users = userRepository.findByTypeOrderByLastNameAscFirstNameAsc(userType);
        removePasswords(users);
        return users;
    }

    private static void removePasswords(List<User> users) {
        Seq.seq(users).forEach(user -> user.setPassword(null));
    }
}
