package com.example.task_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.task_app.repository.Task;
import com.example.task_app.repository.TaskRepository;

@Service
public class TaskService {
  @Autowired
  private TaskRepository taskDao;

  public List<Task> findLatestTask() {
    return taskDao.findAllByOrderByDeadLine().stream().filter((t -> !(t.getStatus()))).limit(3).collect(Collectors.toList());
  }

  public List<Task> findAll() {
    return taskDao.findAll();
  }
}
