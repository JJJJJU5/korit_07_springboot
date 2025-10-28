package com.example.demo.service;

import com.example.demo.domain.Todo;
import com.example.demo.domain.TodoRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.dto.TodoRequestDto;
import com.example.demo.dto.TodoRequestRecord;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User Not Found."));
    }

    // 본인 확인 로직 추가 . To-do 객체 내에 있는 User와 , 지금 요청을 보내는 User가 동일한지 확인
    private void checkOwnership(Todo todo) throws AccessDeniedException{
        if(!todo.getUser().equals(getCurrentUser())){
            throw new AccessDeniedException("해당 todo에 접근할 수 없습니다.");
        }
    }
    // 1. 사용자 구분 없이 모든 할 일 목록 조회
    @Transactional
    public List<Todo> getTodos() {

        return todoRepository.findAll();
    }

    // 2. 현재 사용자의 모든 할 일 목록 조회
    @Transactional
    public List<Todo> getTodosForCurrentUser(){
        User currentUser = getCurrentUser();

        return todoRepository.findByUserId(currentUser.getId());
    }

    // 3. 새로운 todo 추가 DTO 버전
    @Transactional
    public Todo createTodo(TodoRequestDto todoRequestDto) {
        User currentUser = getCurrentUser();
        Todo newtodo = new Todo(todoRequestDto.getContent(),currentUser);
        return todoRepository.save(newtodo);
    }

//    // 3. 새로운 todo 추가 Record 버전
//    @Transactional
//    public Todo createTodo2(TodoRequestRecord todoRequestRecord){
//        User currentUser = getCurrentUser();
//        Todo newtodo = new Todo(todoRequestRecord.content(),currentUser);
//
//        return todoRepository.save(newtodo);
//    }

    // 4. 할 일 내용 수정
    @Transactional
    public Todo updateTodoContent(Long id , TodoRequestDto updateDto) throws AccessDeniedException {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found User" + id));
        checkOwnership(todo);
        todo.setContent(updateDto.getContent());
        return todoRepository.save(todo);
    }

    // 5. 할 일 삭제
    @Transactional
    public void deleteTodo(Long id) throws AccessDeniedException{
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found User" + id));
        checkOwnership(todo);
        todoRepository.delete(todo);
    }

    // 6. 할 일 완료 상태 토글
    @Transactional
    public Todo toggleTodoStatus(Long id) throws AccessDeniedException{
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found User" + id));
        checkOwnership(todo);
        todo.setCompleted(!todo.isCompleted());
        return todoRepository.save(todo);
    }

    // 7. 완료한 할 일 전체 삭제하는 로직
    @Transactional
    public void clearCompletedTodos() {
        User currentUser = getCurrentUser();
        todoRepository.deleteByUserAndIsCompleted(currentUser, true);
    }

}
