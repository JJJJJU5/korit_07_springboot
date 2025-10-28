package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.dto.AccountCredentialsRecord;
import com.example.demo.dto.PasswordUpdate;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;


    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody AccountCredentialsRecord accountCredentialsRecord){
        return ResponseEntity.ok(userService.createUser(accountCredentialsRecord));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUser() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PatchMapping("/users/password")
    public ResponseEntity<User> updatePassword(@RequestBody PasswordUpdate passwordUpdate)  {
        userService.updatePassword(passwordUpdate);
        return ResponseEntity.ok().build();
    }
}
