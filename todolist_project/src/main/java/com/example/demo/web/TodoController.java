package com.example.demo.web;

import com.example.demo.domain.AppUser;
import com.example.demo.domain.Todo;
import com.example.demo.service.TodoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/todos")
    public List<Todo> getTodo() {
        return todoService.getTodo();
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> putTodo(@PathVariable Long id) {
        return todoService.updateTodoStatus(id).map(updateTodo -> ResponseEntity.ok().body(updateTodo))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/todos/completed/{id}")
    public ResponseEntity<Todo> deleteTodos(@PathVariable Long id) {
        return todoService.deleteTodo(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}

