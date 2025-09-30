package com.example.cardatabase4.service;


import com.example.cardatabase4.entity.AppUser;
import com.example.cardatabase4.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;
import static org.springframework.security.core.userdetails.User.withUsername;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);

        UserBuilder builder = null;
        if(user.isPresent()){
            AppUser currentUser = user.get();  // user 자체는 Optional 자료형이지 AppUser가 아니기 때문에 get()을 통해서 꺼내야 와야 함
            builder = withUsername(username);
            builder.password(currentUser.getPassword()).roles(currentUser.getRole()).build();
        } else {
            throw new UsernameNotFoundException(("User Not found."));
        }
        return builder.build();
    }
}
