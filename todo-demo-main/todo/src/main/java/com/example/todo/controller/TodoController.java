package com.example.todo.controller;

import com.example.todo.model.AppUser;
import com.example.todo.repository.AppUserRepository;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.model.request.TodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Todo> findByUserId(@PathVariable("id") Long id) {
        return todoRepository.findByAppUser_IdAndDeletedFalse(id);
    }

    @PostMapping
    public Todo save(@RequestBody @Valid TodoRequest todoRequest) {
        AppUser appUser = appUserRepository.getById(todoRequest.getUserId());
        Todo todo = Todo.TodoBuilder.aTodo()
                .title(todoRequest.getTitle())
                .done(todoRequest.isDone())
                .appUser(appUser)
                .deleted(false)
                .build();
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable("id") Long id, @RequestBody @Valid TodoRequest todoRequest) {
        AppUser appUser = appUserRepository.getById(todoRequest.getUserId());
        Todo todo = todoRepository.getById(id);
        todo.setTitle(todoRequest.getTitle());
        todo.setDone(todoRequest.isDone());
        todo.setAppUser(appUser);
        return todoRepository.save(todo);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        Todo todo = todoRepository.getById(id);
        todo.setDeleted(true);
        todoRepository.save(todo);
    }
}
