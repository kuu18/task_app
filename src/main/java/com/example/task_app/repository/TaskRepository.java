package com.example.task_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
  /**
   * 期限によるソート
   * 
   * @return 期限でソートされた結果
   */
  public List<Task> findAllByOrderByDeadLine();
}
