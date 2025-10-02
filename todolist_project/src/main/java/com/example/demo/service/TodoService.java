package com.example.demo.service;


import com.example.demo.domain.AppUser;
import com.example.demo.domain.Todo;
import com.example.demo.dto.TodoRecord;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final AppUserRepository appUserRepository;
//    private final TodoRecord todoRecord;

    public List<AppUser> getApp(){
        return appUserRepository.findAll();
    }

    public List<Todo> getTodo() {
        return todoRepository.findAll();
    }
    @Transactional
    public Optional<Todo> updateTodoStatus(Long id){
        if (todoRepository.findById(id).isPresent()){
            Todo todo = todoRepository.findById(id).get();
            todo.setCompleted(!todo.isCompleted());
            return Optional.of(todoRepository.save(todo));
        }else {
            return Optional.empty();
        }
    }
    @Transactional
    public boolean deleteTodo(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            if (todo.isCompleted()) {
                todoRepository.delete(todo);
                return true;
            }
        }
        return false;
    }
}

