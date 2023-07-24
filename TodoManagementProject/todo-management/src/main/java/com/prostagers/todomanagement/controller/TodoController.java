package com.prostagers.todomanagement.controller;
import com.prostagers.todomanagement.dto.TodoDto;
import com.prostagers.todomanagement.service.TodoService;
import com.prostagers.todomanagement.service.impl.TodoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/todos")
public class TodoController {

    private TodoService todoService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto createdTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){
        TodoDto fetchedTodo = todoService.getTodo(id);
        return new ResponseEntity<>(fetchedTodo,HttpStatus.OK);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos,HttpStatus.OK);
    }
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable Long id){
        TodoDto updatedTodo = todoService.updateTodo(todoDto,id);
        return ResponseEntity.ok(updatedTodo);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted successfully !");
    }
    @PatchMapping("{id}/complete")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto completedTodo = todoService.completeTodo(id);
        return ResponseEntity.ok(completedTodo);
    }
    @PatchMapping("{id}/incomplete")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable Long id){
        TodoDto inCompleteTodo = todoService.incompleteTodo(id);
        return ResponseEntity.ok(inCompleteTodo);
    }
}
