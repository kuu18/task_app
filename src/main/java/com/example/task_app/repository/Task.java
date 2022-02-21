package com.example.task_app.repository;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "taskid")
  private Integer taskid;
  @Column(name = "name")
  private String name;
  @Column(name = "content")
  private String content;
  @Column(name = "deadline")
  private LocalDateTime deadLine;
  @Column(name = "status")
  private Boolean status;
  @Column(name = "userid")
  private Integer userid;
  @ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User user;

  public Integer getTaskId() {
    return this.taskid;
  }

  public void setTaskId(Integer taskId) {
    this.taskid = taskId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getDeadLine() {
    return this.deadLine;
  }

  public void setDeadLine(LocalDateTime deadLine) {
    this.deadLine = deadLine;
  }

  public Boolean getStatus() {
    return this.status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public Integer getUserid() {
    return this.userid;
  }

  public void setUserid(Integer userId) {
    this.userid = userId;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
