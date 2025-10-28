package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.dto.AccountCredentialsRecord;
import com.example.demo.dto.PasswordUpdate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User Not Found."));
    }

    public User createUser(AccountCredentialsRecord accountCredentialsRecord) {
        User user = new User(accountCredentialsRecord.username(), passwordEncoder.encode(accountCredentialsRecord.password()));
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void updatePassword(PasswordUpdate passwordUpdate) {
        User user =  getCurrentUser();
        if (!passwordEncoder.matches(passwordUpdate.password(), user.getPassword())) {
            throw new EntityNotFoundException(passwordUpdate.password());
        }
        user.setPassword(passwordEncoder.encode(passwordUpdate.newPassword()));
        userRepository.save(user);
    }
}
