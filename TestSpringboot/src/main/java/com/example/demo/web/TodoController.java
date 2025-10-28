package com.example.demo.web;


import com.example.demo.domain.Todo;
import com.example.demo.dto.TodoRequestDto;
import com.example.demo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(todoService.getTodos());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<List<Todo>> getCurrentTodos () {
        return ResponseEntity.ok(todoService.getTodosForCurrentUser());
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createdTodo(@RequestBody TodoRequestDto requestDto){
        Todo createdTodo = todoService.createTodo(requestDto);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    // 수정 파트
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoContent(@PathVariable Long id, @RequestBody TodoRequestDto updateDto) throws AccessDeniedException {
        return ResponseEntity.ok(todoService.updateTodoContent(id, updateDto));
    }

    // 삭제 파트
    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable Long id) throws AccessDeniedException {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    // 토글
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Todo> toggleTodo(@PathVariable Long id) throws AccessDeniedException {
        return ResponseEntity.ok(todoService.toggleTodoStatus(id));
    }

    // 일괄 삭제
    @DeleteMapping("/todos/completed")
    public ResponseEntity<Todo> clearTodo() {
        todoService.clearCompletedTodos();
        return ResponseEntity.noContent().build();
    }
}
