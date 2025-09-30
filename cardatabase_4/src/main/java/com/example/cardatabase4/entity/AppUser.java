package com.example.cardatabase4.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force = true)
public class AppUser {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false,updatable = false)
        private Long id;

        @Column(nullable = false,unique = true)
        private final String username;

        @Column(nullable = false)
        private final String password;

        @Column(nullable = false)
        private final String role;

        public AppUser(String username, String password, String role) {
                this.username = username;
                this.password = password;
                this.role = role;
        }
}

