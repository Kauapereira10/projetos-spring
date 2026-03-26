package com.kaua.todolist.service;

import com.kaua.todolist.model.Task;
import com.kaua.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(Task task) {
        return repository.save(task);
    }

    public List<Task> read() {
        return repository.findAll();
    }

    public Task update(Long id, Task newTask) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));

        task.setTitle(newTask.getTitle());
        task.setDescription(newTask.getDescription());
        task.setCompleted(newTask.isCompleted());

        return repository.save(task);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Task findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada."));
    }
}
