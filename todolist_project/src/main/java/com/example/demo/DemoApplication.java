package com.example.demo;

import com.example.demo.domain.AppUser;
import com.example.demo.domain.Todo;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;



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

        AppUser appUser1 = new AppUser("user",passwordEncoder.encode("1234"),"USER");
        Todo todo1 = new Todo("첫 번째 할일",appUser1);
        Todo todo2 = new Todo("두 번째 할일",appUser1);
        appUser1.addTodo(todo1);
        appUser1.addTodo(todo2);
        appUserRepository.save(appUser1);


    }
}