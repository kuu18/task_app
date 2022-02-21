package com.example.task_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
  /**
   * 期限によるソート
   * 
   * @return 期限でソートされた結果
   */
  public List<Task> findByUseridOrderByDeadLine(Integer userid);

  /**
	 * タスク名で検索 (曖昧検索)
	 *
	 * @param name
	 *            
	 * @return 検索結果
	 */
	public List<Task> findByNameContainingAndUserid(String name, Integer userid);

  /**
   * ステータスの更新
   * 
   * @return 
   */
  @Modifying
  @Query(value = "update task t set t.status = if(t.status=1,0,1) where t.taskid = :taskId", nativeQuery = true)
  public void updateStatus(@Param("taskId") Integer taskId);

}

