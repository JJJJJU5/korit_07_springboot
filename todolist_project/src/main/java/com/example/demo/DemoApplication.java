package com.example.demo;

import com.example.demo.domain.AppUser;
import com.example.demo.domain.Todo;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    private final AppUserRepository appUserRepository;
    private final TodoRepository todoRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        AppUser appUser = new AppUser("asd1",passwordEncoder.encode("1234"),"user");
        appUserRepository.save(appUser);
        Todo todo = new Todo("할일",appUser);
        todoRepository.save(new Todo("밥먹기",appUser));
        todoRepository.save(new Todo("자기",appUser));
        todoRepository.save(todo);


    }
}