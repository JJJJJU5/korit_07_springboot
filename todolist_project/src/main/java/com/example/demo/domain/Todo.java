package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    private boolean isCompleted = false;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)

    private AppUser appUser;

    public Todo(String content, AppUser appUser) {
        this.content = content;
        this.appUser = appUser;
    }
}
