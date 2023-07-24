package com.prostagers.todomanagement.service.impl;

import com.prostagers.todomanagement.dto.TodoDto;
import com.prostagers.todomanagement.entity.Todo;
import com.prostagers.todomanagement.exception.ResourceNotFound;
import com.prostagers.todomanagement.repository.TodoRepository;
import com.prostagers.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private ModelMapper modelMapper;
    private TodoRepository todoRepository;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo fetchedTodo = todoRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Todo not found with id "+id)
        );
        return modelMapper.map(fetchedTodo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> outputList = todoRepository.findAll();
        return outputList.stream().map((todo)-> modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Todo not found with id "+id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        todoRepository.save(todo);
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Todo not found with id "+id));
        todoRepository.deleteById(todo.getId());
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Todo not found with id "+id)
        );
        todo.setCompleted(true);
        Todo completedTodo = todoRepository.save(todo);
        return modelMapper.map(completedTodo,TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Todo not found with id "+id)
        );
        todo.setCompleted(false);
        Todo incompleteTodo = todoRepository.save(todo);
        return modelMapper.map(incompleteTodo,TodoDto.class);
    }

}
