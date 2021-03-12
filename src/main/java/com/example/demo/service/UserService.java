package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Sergey Hovhannisyan
 * @since : 12/15/2020, Tuesday, 7:17 PM
 **/
@Service
public class UserService implements UserDetailsService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("User with id: " + id + " does not exist");
        });
    }
}
