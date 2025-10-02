package com.example.demo.service;

import com.example.demo.domain.AppUser;
import com.example.demo.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private  final AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);
        User.UserBuilder builder = null;
        if(user.isPresent()){
            AppUser currentUser = user.get();
            builder = withUsername(username);
            builder.password(currentUser.getPassword()).roles(currentUser.getRole()).build();
        } else {
            throw new UsernameNotFoundException(("User Not found."));
        }
        return builder.build();
    }
}

