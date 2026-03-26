package com.kaua.todolist.controller;

import com.kaua.todolist.model.Task;
import com.kaua.todolist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(service.read());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTasksById(@PathVariable Long id) {
        Task task1 = service.findById(id);
        return ResponseEntity.ok(task1);
    }

    @PostMapping()
    public ResponseEntity<Task> postTask(@RequestBody Task task) {
        Task task1 = service.create(task);
        return ResponseEntity.status(201).body(task1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putTask(@PathVariable Long id, @RequestBody Task task) {
        Task task1 = service.update(id, task);
        return ResponseEntity.status(201).body(task1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
