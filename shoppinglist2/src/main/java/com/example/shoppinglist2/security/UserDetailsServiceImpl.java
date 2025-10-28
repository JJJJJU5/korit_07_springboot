package com.example.shoppinglist2.security;

import com.example.shoppinglist2.domain.User;
import com.example.shoppinglist2.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;


import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        UserBuilder builder = null;
        if (user.isPresent()) {
            User currentUser = user.get();
            builder = withUsername(username);
            builder.username(currentUser.getUsername());
            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole());

        }else {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return builder.build();
    }

}
