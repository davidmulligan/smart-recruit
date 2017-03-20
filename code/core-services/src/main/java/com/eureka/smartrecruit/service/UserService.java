package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.UserRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.eureka.smartrecruit.domain.QUser.user;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

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

    public List<User> findAll() {
        List<User> users = Seq.seq(userRepository.findAll(user.email.asc())).toList();
        removePasswords(users);
        return users;
    }

    public List<User> findByRole(String role) {
        List<User> users = Seq.seq(userRepository.findAll(user.roles.any().name.eq(role), user.email.asc())).toList();
        removePasswords(users);
        return users;
    }

    @Override
    public User loadUserByUsername(String email) {
        return userRepository.tryFindOne(user.email.eq(email)).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public List<User> find(Predicate predicate) {
        return Seq.seq(userRepository.findAll(predicate)).toList();
    }

    private static void removePasswords(List<User> users) {
        Seq.seq(users).forEach(user -> user.setPassword(null));
    }
}
