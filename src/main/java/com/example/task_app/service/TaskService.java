package com.example.task_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.example.task_app.repository.Task;
import com.example.task_app.repository.TaskRepository;

@Service
public class TaskService {
  @Autowired
  private TaskRepository taskRepository;

  public List<Task> findLatestTask() {
    return taskRepository.findAllByOrderByDeadLine().stream().filter((t -> !(t.getStatus()))).limit(3).collect(Collectors.toList());
  }

  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  public Task findOne(Integer taskId) {
    return taskRepository.findById(taskId).get();
  }

  @Transactional
  public Task save(Task task) {
    return taskRepository.save(task);
  }

  @Transactional
	public Task update(Task task) {
		return taskRepository.save(task);
	}

  @Transactional
  public void updateStatus(Integer taskId) {
    taskRepository.updateStatus(taskId);
  }

  @Transactional
	public void delete(int taskId) {
		taskRepository.deleteById(taskId);
	}
}
