package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.User;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void create(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void update(final User user) {
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.delete(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User findById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find user with id: %s", id)));
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByUsernameAsc();
    }
}
